package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.TicketUse;

@Repository
public interface TicketUseRepository extends CrudRepository<TicketUse, Long> {
}
