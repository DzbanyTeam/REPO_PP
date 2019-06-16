package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.DayOfTheWeek;

/**
 * Interfejs pozwalający na wykonywanie operacji (np. zapisywanie i pobieranie obiektów) na bazie danych i obiektach typu DayOfTheWeek
 */
@Repository
public interface DayOfTheWeekRepository extends CrudRepository<DayOfTheWeek, Long> {
}
