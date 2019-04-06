package pl.polsl.pp.service;


import pl.polsl.pp.model.AdminAccount;
import java.util.List;

public interface AdminAccountServiceInterface {


    AdminAccount getAdminAccountById(Long id);
    AdminAccount getAdminAccountByUsername(String username);
    boolean saveAdminAccount(AdminAccount adminAccount);
    boolean saveAdminAccountWithoutHashing(AdminAccount adminAccount);
    boolean deleteAdminAccount(Long id);
    boolean deleteAdminAccounts(List<Long> ids);
    boolean deactivateAdminAccounts(List<Long> ids);
    boolean activateAdminAccounts(List<Long> ids);
    List<AdminAccount> getAllAdminAccounts();

}
