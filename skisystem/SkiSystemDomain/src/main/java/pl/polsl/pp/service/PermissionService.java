package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Permission;
import pl.polsl.pp.repository.PermissionRepository;
import pl.polsl.pp.service.interfaces.IPermissionService;

import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu Permission
 */
public class PermissionService implements IPermissionService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli permissions
     */
    @Autowired
    @Qualifier("permissionRepository")
    private PermissionRepository permissionRepository;

    /**
     * Nadaje danemu użytkownikowi uprawnienia (role)
     * @param userId AdminAccount/CustomerAccount id
     * @param roleId Role id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean savePermission(long userId, long roleId) {
        try {
            Permission userRole = new Permission(userId,roleId);
            permissionRepository.save(userRole);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

    /**
     * Usuwa wybranym użytkownikom uprawienia
     * @param userIds lista AdminAccount/CustomerAccount id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deletePermissions(List<Long> userIds) {
        try {
            List<Permission> permissionList =  (List<Permission>) permissionRepository.findAll();
            for(Permission p : permissionList) {
                if(userIds.contains(p.getUserId())) {
                    permissionRepository.delete(p);
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
