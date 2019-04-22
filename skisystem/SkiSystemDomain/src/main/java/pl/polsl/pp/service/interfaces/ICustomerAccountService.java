package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.CustomerAccount;

import java.util.List;

public interface ICustomerAccountService {

    CustomerAccount getCustomerAccountById(Long id);
    CustomerAccount getCustomerAccountByUsername(String username);
    boolean saveCustomerAccount(CustomerAccount customerAccount);
    boolean deleteCustomerAccounts(List<Long> ids);
    boolean deactivateCustomerAccounts(List<Long> ids);
    boolean activateCustomerAccounts(List<Long> ids);
    List<CustomerAccount> getAllCustomerAccounts();

}
