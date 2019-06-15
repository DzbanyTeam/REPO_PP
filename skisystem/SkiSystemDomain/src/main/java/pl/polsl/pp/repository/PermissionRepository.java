package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.Permission;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu Permission
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
