package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.CustomerAccountRepository;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IPermissionService;

import java.util.ArrayList;
import java.util.List;

public class CustomerAccountService implements ICustomerAccountService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli customer_accounts
     */
    @Autowired
    @Qualifier("customerAccountRepository")
    private CustomerAccountRepository customerAccountRepository;

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
    private RoleService roleService;

    /**
     * Obiekt pozwalający na szyfrowanie haseł
     */
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Zwraca obiekt CustomerAccount z danym id
     * @param id CustomerAccount id
     * @return CustomerAccount
     */
    @Override
    public CustomerAccount getCustomerAccountById(Long id) {
        return customerAccountRepository.findById(id).get();
    }

    /**
     * Zwraca obiekt CustomerAccount z daną nazwą użytkownika
     * @param username CustomerAccount username
     * @return CustomerAccount
     */
    @Override
    public CustomerAccount getCustomerAccountByUsername(String username) {
        List<CustomerAccount> customerAccountList = (List<CustomerAccount>) customerAccountRepository.findAll();
        for (CustomerAccount customerAccount : customerAccountList) {
            if(customerAccount.getUsername().equals(username)) {
                return customerAccount;
            }
        }
        return null;
    }

    /**
     * Dodaje dany obiekt CustomerAccount do bazy danych
     * @param customerAccount obiekt CustomerAccount do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
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
            Role role = roleService.getByRole("ROLE_CUSTOMER");
            permissionService.savePermission(customerAccount.getId(),role.getId());
            return true;

        } catch(Exception ex) {
            return false;
        }
    }

    /**
     * Usuwa obiekty CustomerAccount z podanymi id z bazy danych
     * @param ids lista CustomerAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteCustomerAccounts(List<Long> ids) {
        try {
            List<CustomerAccount> customerAccountList = (List<CustomerAccount>) customerAccountRepository.findAllById(ids);
            customerAccountRepository.deleteAll(customerAccountList);

            permissionService.deletePermissions(ids);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Dezaktywuje obiekty CustomerAccount z podanymi id
     * @param ids lista CustomerAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateCustomerAccounts(List<Long> ids) {


        try {
            List<CustomerAccount> customerAccountList;
            customerAccountList = (List<CustomerAccount>) customerAccountRepository.findAllById(ids);

            for (CustomerAccount customerAccount : customerAccountList) {
                customerAccount.setIsActive(false);
                customerAccountRepository.save(customerAccount);
            }
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty CustomerAccount z podanymi id
     * @param ids lista CustomerAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateCustomerAccounts(List<Long> ids) {

        try {
            List<CustomerAccount> customerAccountList;
            customerAccountList = (List<CustomerAccount>) customerAccountRepository.findAllById(ids);

            for (CustomerAccount customerAccount : customerAccountList) {
                customerAccount.setIsActive(true);
                customerAccountRepository.save(customerAccount);
            }
            return true;
        } catch(Exception ex) {
            return false;
        }

    }

    /**
     * Zwraca listę wszystkich obiektów CustomerAccount
     * @return lista CustomerAccount
     */
    @Override
    public List<CustomerAccount> getAllCustomerAccounts() {
        List<CustomerAccount> customerAccountList = new ArrayList<>();
        customerAccountRepository.findAll().forEach(ca -> customerAccountList.add(ca));
        return customerAccountList;
    }
}
