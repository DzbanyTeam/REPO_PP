package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
