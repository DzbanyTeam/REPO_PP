package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Permission;
import pl.polsl.pp.repository.PermissionRepository;
import pl.polsl.pp.service.interfaces.IPermissionService;

public class PermissionService implements IPermissionService {

    @Autowired
    @Qualifier("permissionRepository")
    private PermissionRepository userRoleRepository;

    @Override
    public boolean savePermission(long userId, long roleId) {
        try {
            Permission userRole = new Permission(userId,roleId);
            userRoleRepository.save(userRole);
            return true;
        }catch(Exception ex) {
            return false;
        }
    }

}
