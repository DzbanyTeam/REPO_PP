package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.Season;

@Repository
public interface SeasonRepository extends CrudRepository<Season,Long> {
}
