package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.PayPalTransaction;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu PayPalTransaction
 */
@Repository
public interface PayPalTransactionRepository extends CrudRepository<PayPalTransaction, Long> {
}
