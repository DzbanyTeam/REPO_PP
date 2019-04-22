package pl.polsl.pp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

    @Bean
    @Qualifier("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String ADMINS_QUERY = "select username, password, is_active from admin_accounts where username=?";
    private final String ROLES_ADMIN_QUERY = "select a.username, r.role from admin_accounts a inner join permissions ur on (a.id = ur.user_id) inner join roles r on (ur.role_id=r.role_id) where a.username=?";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .usersByUsernameQuery(ADMINS_QUERY)
                .authoritiesByUsernameQuery(ROLES_ADMIN_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**", "/admin/login").permitAll()
                .antMatchers("/**","/admin/**").hasAuthority("ROLE_ADMIN")
                .and().formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin", true)
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login")
                .and().csrf().disable();
    }
}
