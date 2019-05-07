package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.TicketCategory;

@Repository
public interface TicketCategoryRepository extends CrudRepository<TicketCategory, Long> {
}
