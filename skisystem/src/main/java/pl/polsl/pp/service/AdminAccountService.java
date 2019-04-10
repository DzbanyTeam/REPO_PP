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

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAccountService implements IAdminAccountService {


    @Autowired
    @Qualifier("adminAccountRepository")
    private AdminAccountRepository adminAccountRepository;

    @Autowired
    @Qualifier("permissionService")
    private IPermissionService permissionService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    public AdminAccountService() {
        super();
    }


    @Override
    public AdminAccount getAdminAccountById(Long id) {

        return adminAccountRepository.findById(id).get();
    }

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

    @Override
    public boolean deleteAdminAccounts(List<Long> ids) {

        try {
            List<AdminAccount> adminAccountList;
            adminAccountList = (List<AdminAccount>) adminAccountRepository.findAllById(ids);
            adminAccountRepository.deleteAll(adminAccountList);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

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


    @Override
    public List<AdminAccount> getAllAdminAccounts() {

        List<AdminAccount> adminAccountList = new ArrayList<>();
        adminAccountRepository.findAll().forEach(aa -> adminAccountList.add(aa));
        return adminAccountList;
    }
}
