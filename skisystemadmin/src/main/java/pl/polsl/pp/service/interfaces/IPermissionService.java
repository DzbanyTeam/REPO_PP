package pl.polsl.pp.service.interfaces;

import java.util.List;

public interface IPermissionService {
    boolean savePermission(long userId, long roleId);
    boolean deletePermissions(List<Long> userIds);
}
