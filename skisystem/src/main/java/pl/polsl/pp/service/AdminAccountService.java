package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.AdminAccountRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AdminAccountService implements AdminAccountServiceInterface {


    @Autowired
    @Qualifier("adminAccountRepository")
    private AdminAccountRepository adminAccountRepository;

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
    public boolean saveAdminAccount(AdminAccount adminAccount) {

        try {
            adminAccount.setPassword(passwordEncoder.encode(adminAccount.getPassword()));
            Role userRole = roleService.getByRole("ROLE_ADMIN");
            if(userRole!=null){
                adminAccount.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
                adminAccountRepository.save(adminAccount);
                return true;
            }
            else{
                return false;
            }
        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean saveAdminAccountWithoutHashing(AdminAccount adminAccount) {

        try {
            adminAccountRepository.save(adminAccount);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteAdminAccount(Long id) {

        try {
            adminAccountRepository.delete(this.getAdminAccountById(id));
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
                adminAccount.setActive(false);
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
                adminAccount.setActive(true);
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
