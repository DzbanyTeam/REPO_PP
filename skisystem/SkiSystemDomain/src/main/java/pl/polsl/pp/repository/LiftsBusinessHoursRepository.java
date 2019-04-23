package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.LiftsBusinessHours;

@Repository
public interface LiftsBusinessHoursRepository extends CrudRepository<LiftsBusinessHours, Long> {
}
