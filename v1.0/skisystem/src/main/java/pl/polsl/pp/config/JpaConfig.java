package pl.polsl.pp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.polsl.pp.service.AdminAccountService;
import pl.polsl.pp.service.RoleService;


@Configuration
@EntityScan(basePackages = {"pl.polsl.pp.model" })
@EnableJpaRepositories(basePackages = {"pl.polsl.pp.repository"})
@EnableTransactionManagement
public class JpaConfig {
    @Bean
    @Qualifier("adminAccountService")
    public AdminAccountService adminAccountServiceInterface() {
        return new AdminAccountService();
    }

    @Bean
    @Qualifier("roleService")
    public RoleService RoleServiceInterface() {
        return new RoleService();
    }


}
