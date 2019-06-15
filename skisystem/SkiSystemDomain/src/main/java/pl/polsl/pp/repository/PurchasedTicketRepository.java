package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.PurchasedTicket;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu PurchasedTicket
 */
@Repository
public interface PurchasedTicketRepository extends CrudRepository<PurchasedTicket, Long> {
}
