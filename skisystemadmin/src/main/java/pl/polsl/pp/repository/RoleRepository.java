package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
}
