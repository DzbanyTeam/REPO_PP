package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Permission;
import pl.polsl.pp.repository.PermissionRepository;
import pl.polsl.pp.service.interfaces.IPermissionService;

import java.util.List;

public class PermissionService implements IPermissionService {

    @Autowired
    @Qualifier("permissionRepository")
    private PermissionRepository permissionRepository;

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
