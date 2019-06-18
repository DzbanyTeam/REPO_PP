package pl.polsl.pp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.polsl.pp.service.*;


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
    @Qualifier("customerAccountService")
    public CustomerAccountService customerAccountServiceInterface() {
        return new CustomerAccountService();
    }

    @Bean
    @Qualifier("roleService")
    public RoleService RoleServiceInterface() {
        return new RoleService();
    }

    @Bean
    @Qualifier("permissionService")
    public PermissionService IUserRoleService() {
        return new PermissionService();
    }

    @Bean
    @Qualifier("difficultyService")
    public DifficultyService difficultyServiceInterface() { return new DifficultyService(); }

    @Bean
    @Qualifier("liftService")
    public LiftService liftServiceInterface(){return new LiftService(); }

    @Bean
    @Qualifier("slopeService")
    public SlopeService slopeServiceInterface(){return new SlopeService(); }

    @Bean
    @Qualifier("ticketCategoryService")
    public TicketCategoryService ticketCategoryServiceInterface() {return new TicketCategoryService();}

    @Bean
    @Qualifier("ticketTypeService")
    public TicketTypeService ticketTypeServiceInterface() {return new TicketTypeService();}

    @Bean
    @Qualifier("priceService")
    public PriceService priceServiceInterface() {return new PriceService();}

    @Bean
    @Qualifier("purchasedTicketService")
    public PurchasedTicketService purchasedTicketServiceInterface() {return new PurchasedTicketService();}

    @Bean
    @Qualifier("ticketUseService")
    public TicketUseService ticketUseServiceInterface() {return new TicketUseService();}

    @Bean
    @Qualifier("liftBusinessHoursService")
    public LiftBusinessHoursService liftBusinessHoursServiceInterface() {return new LiftBusinessHoursService();}

    @Bean
    @Qualifier("slopeBusinessHoursService")
    public SlopeBusinessHoursService slopeBusinessHoursServiceInterface() {return new SlopeBusinessHoursService();}

    @Bean
    @Qualifier("dayOfTheWeekService")
    public DayOfTheWeekService dayOfTheWeekServiceInterface() {return new DayOfTheWeekService();}

    @Bean
    @Qualifier("seasonService")
    public SeasonService seasonServiceInterface(){return new SeasonService(); }

    @Bean
    @Qualifier("payPalTransactionService")
    public PayPalTransactionService payPalTransactionServiceInterface(){return new PayPalTransactionService(); }
}
