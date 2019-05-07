package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.SlopeBusinessHours;

@Repository
public interface SlopeBusinessHoursRepository extends CrudRepository<SlopeBusinessHours, Long> {
}
