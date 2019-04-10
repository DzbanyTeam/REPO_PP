package pl.polsl.pp.service.interfaces;


import pl.polsl.pp.model.AdminAccount;
import java.util.List;

public interface IAdminAccountService {


    AdminAccount getAdminAccountById(Long id);
    AdminAccount getAdminAccountByUsername(String username);
    boolean saveAdminAccount(AdminAccount adminAccount);
    boolean deleteAdminAccounts(List<Long> ids);
    boolean deactivateAdminAccounts(List<Long> ids);
    boolean activateAdminAccounts(List<Long> ids);
    List<AdminAccount> getAllAdminAccounts();

}
