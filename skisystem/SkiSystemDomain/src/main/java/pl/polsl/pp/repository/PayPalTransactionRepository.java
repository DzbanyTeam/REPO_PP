package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.PayPalTransaction;

@Repository
public interface PayPalTransactionRepository extends CrudRepository<PayPalTransaction, Long> {
}
