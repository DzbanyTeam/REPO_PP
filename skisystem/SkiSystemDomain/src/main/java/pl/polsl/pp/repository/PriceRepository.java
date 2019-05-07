package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
}
