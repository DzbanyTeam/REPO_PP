package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.TicketType;

@Repository
public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {
}
