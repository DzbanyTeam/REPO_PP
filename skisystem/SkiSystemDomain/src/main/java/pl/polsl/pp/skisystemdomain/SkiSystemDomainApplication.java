package pl.polsl.pp.skisystemdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.polsl.pp.config")
public class SkiSystemDomainApplication {

    public static void main(String[] args) {

        SpringApplication.run(SkiSystemDomainApplication.class, args);

    }
}
