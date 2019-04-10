package pl.polsl.pp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String ADMINS_QUERY = "select username, password, is_active from admin_accounts where username=?";
    private final String CUSTOMERS_QUERY = "select username, password, is_active from customer_accounts where username=?";
    private final String ROLES_ADMIN_QUERY = "select a.username, r.role from admin_accounts a inner join permissions ur on (a.id = ur.user_id) inner join roles r on (ur.role_id=r.role_id) where a.username=?";
    private final String ROLES_CUSTOMER_QUERY = "select a.username, r.role from customer_accounts a inner join permissions ur on (a.id = ur.user_id) inner join roles r on (ur.role_id=r.role_id) where a.username=?";

    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .usersByUsernameQuery(ADMINS_QUERY)
                .authoritiesByUsernameQuery(ROLES_ADMIN_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);

        auth.jdbcAuthentication()
                .usersByUsernameQuery(CUSTOMERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_CUSTOMER_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/slopes/**","/lifts","/prices","/resources/**").permitAll()

                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/user","/user/login","/user/register","/user/remind-password/**","/user/reset-password").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")

                .anyRequest().authenticated()
                .and().csrf().disable()

                .formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin",true)
                .and().formLogin()
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").permitAll();
    }

    @Bean
    @Qualifier("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}