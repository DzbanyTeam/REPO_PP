package pl.polsl.pp.skisystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.repository.RoleRepository;
import pl.polsl.pp.service.RoleServiceInterface;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("pl.polsl.pp.config")
@ComponentScan("pl.polsl.pp.validator")
public class SkiSystemApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SkiSystemApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(SkiSystemApplication.class, args);

	}
}
