package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.LiftBusinessHours;

@Repository
public interface LiftBusinessHoursRepository extends CrudRepository<LiftBusinessHours, Long> {
}
