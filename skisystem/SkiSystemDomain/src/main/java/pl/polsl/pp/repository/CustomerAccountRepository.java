package pl.polsl.pp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.model.CustomerAccount;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount,Long> {
}
