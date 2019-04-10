package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.CustomerAccountRepository;
import pl.polsl.pp.repository.PermissionRepository;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IPermissionService;

import java.util.List;

public class CustomerAccountService implements ICustomerAccountService {

    @Autowired
    @Qualifier("customerAccountRepository")
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    @Qualifier("permissionService")
    private IPermissionService permissionService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomerAccount getCustomerAccountById(Long id) {
        return null;
    }

    @Override
    public CustomerAccount getCustomerAccountByUsername(String username) {
        return null;
    }

    @Override
    public boolean saveCustomerAccount(CustomerAccount customerAccount) {
        try {
            String newPassword = customerAccount.getPassword();
            if(newPassword.isEmpty()) {
                String oldPassword = getCustomerAccountById(customerAccount.getId()).getPassword();
                customerAccount.setPassword(oldPassword);
            }
            else {
                customerAccount.setPassword(passwordEncoder.encode(newPassword));
            }
            customerAccountRepository.save(customerAccount);
            Role role = roleService.getByRole("ROLE_USER");
            permissionService.savePermission(customerAccount.getId(),role.getId());
            return true;

        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteCustomerAccounts(List<Long> ids) {
        return false;
    }

    @Override
    public boolean deactivateCustomerAccounts(List<Long> ids) {
        return false;
    }

    @Override
    public boolean activateCustomerAccounts(List<Long> ids) {
        return false;
    }

    @Override
    public List<CustomerAccount> getAllCustomerAccounts() {
        return null;
    }
}
