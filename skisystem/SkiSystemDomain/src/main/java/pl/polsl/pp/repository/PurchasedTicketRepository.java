package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.PurchasedTicket;

@Repository
public interface PurchasedTicketRepository extends CrudRepository<PurchasedTicket, Long> {
}
