package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.AdminAccount;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu AdminAccount
 */
@Repository
public interface AdminAccountRepository extends CrudRepository<AdminAccount, Long> {
}
