package pl.polsl.pp.skisystemcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.polsl.pp.config")
@ComponentScan("pl.polsl.pp.validator")
public class SkiSystemCustomerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SkiSystemDomainApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(SkiSystemDomainApplication.class, args);

	}
}
