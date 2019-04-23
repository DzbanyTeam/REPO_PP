package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.DayOfTheWeek;

@Repository
public interface DayOfTheWeekRepository extends CrudRepository<DayOfTheWeek, Long> {
}
