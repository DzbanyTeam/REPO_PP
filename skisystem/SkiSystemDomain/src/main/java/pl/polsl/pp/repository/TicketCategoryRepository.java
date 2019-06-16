package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.TicketCategory;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu TicketCategory
 */
@Repository
public interface TicketCategoryRepository extends CrudRepository<TicketCategory, Long> {
}
