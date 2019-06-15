package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.AdminAccountRepository;
import pl.polsl.pp.service.interfaces.IAdminAccountService;
import pl.polsl.pp.service.interfaces.IPermissionService;
import pl.polsl.pp.service.interfaces.IRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu AdminAccount
 */
@Service
public class AdminAccountService implements IAdminAccountService {


    /**
     * Repozytorium pozwalające na aktualizowanie tabeli admin_accounts
     */
    @Autowired
    @Qualifier("adminAccountRepository")
    private AdminAccountRepository adminAccountRepository;

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli permissions
     */
    @Autowired
    @Qualifier("permissionService")
    private IPermissionService permissionService;

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli roles
     */
    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    /**
     * Obiekt pozwalający na szyfrowanie haseł
     */
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Tworzy nowy obiekt AdminAccountService
     */
    public AdminAccountService() {
        super();
    }

    /**
     * Zwraca obiekt AdminAccount z danym id
     * @param id AdminAccount id
     * @return AdminAccount
     */
    @Override
    public AdminAccount getAdminAccountById(Long id) {

        return adminAccountRepository.findById(id).get();
    }

    /**
     * Zwraca obiekt AdminAccount z daną nazwą użytkownika
     * @param username AdminAccount username
     * @return AdminAccount
     */
    @Override
    public AdminAccount getAdminAccountByUsername(String username) {
        List<AdminAccount> adminAccountList = (List<AdminAccount>) adminAccountRepository.findAll();
        for (AdminAccount adminAccount : adminAccountList) {
            if(adminAccount.getUsername().equals(username)) {
                return adminAccount;
            }
        }
        return null;
    }

    /**
     * Dodaje dany obiekt AdminAccount do bazy danych
     * @param adminAccount obiekt AdminAccount do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveAdminAccount(AdminAccount adminAccount) {

        try {
            String newPassword = adminAccount.getPassword();
            if(newPassword.isEmpty()) {
                String oldPassword = getAdminAccountById(adminAccount.getId()).getPassword();
                adminAccount.setPassword(oldPassword);
            }
            else {
                adminAccount.setPassword(passwordEncoder.encode(newPassword));
            }
            adminAccountRepository.save(adminAccount);
            Role role = roleService.getByRole("ROLE_ADMIN");
            permissionService.savePermission(adminAccount.getId(),role.getId());
            return true;

        } catch(Exception ex) {
            return false;
        }
    }

    /**
     * Usuwa obiekty AdminAccount z podanymi id z bazy danych
     * @param ids lista AdminAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteAdminAccounts(List<Long> ids) {

        try {
            List<AdminAccount> adminAccountList;
            adminAccountList = (List<AdminAccount>) adminAccountRepository.findAllById(ids);
            adminAccountRepository.deleteAll(adminAccountList);

            permissionService.deletePermissions(ids);

            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    /**
     * Dezaktywuje obiekty AdminAccount z podanymi id
     * @param ids lista AdminAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateAdminAccounts(List<Long> ids) {

        try {
            List<AdminAccount> adminAccountList;
            adminAccountList = (List<AdminAccount>) adminAccountRepository.findAllById(ids);

            for (AdminAccount adminAccount : adminAccountList) {
                adminAccount.setIsActive(false);
                adminAccountRepository.save(adminAccount);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty AdminAccount z podanymi id
     * @param ids lista AdminAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateAdminAccounts(List<Long> ids) {

        try {
            List<AdminAccount> adminAccountList;
            adminAccountList = (List<AdminAccount>) adminAccountRepository.findAllById(ids);

            for (AdminAccount adminAccount : adminAccountList) {
                adminAccount.setIsActive(true);
                adminAccountRepository.save(adminAccount);
            }
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów AdminAccount
     * @return lista AdminAccount
     */
    @Override
    public List<AdminAccount> getAllAdminAccounts() {

        List<AdminAccount> adminAccountList = new ArrayList<>();
        adminAccountRepository.findAll().forEach(aa -> adminAccountList.add(aa));
        return adminAccountList;
    }
}
