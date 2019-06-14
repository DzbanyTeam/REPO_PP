package pl.polsl.pp.skisystemdomain

import org.checkerframework.checker.units.qual.A
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Lift
import pl.polsl.pp.model.Price
import pl.polsl.pp.model.PurchasedTicket
import pl.polsl.pp.model.TicketUse
import pl.polsl.pp.service.interfaces.ICustomerAccountService
import pl.polsl.pp.service.interfaces.ILiftService
import pl.polsl.pp.service.interfaces.IPriceService
import pl.polsl.pp.service.interfaces.IPurchasedTicketService
import pl.polsl.pp.service.interfaces.ISeasonService
import pl.polsl.pp.service.interfaces.ITicketCategoryService
import pl.polsl.pp.service.interfaces.ITicketTypeService
import pl.polsl.pp.service.interfaces.ITicketUseService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class TicketUseServiceTest extends Specification {

    @Autowired
    private ITicketUseService ticketUseService
    @Autowired
    private ICustomerAccountService customerAccountService
    @Autowired
    private ITicketTypeService ticketTypeService
    @Autowired
    private ITicketCategoryService ticketCategoryService
    @Autowired
    private ISeasonService seasonService
    @Autowired
    private IPriceService priceService
    @Autowired
    private IPurchasedTicketService purchasedTicketService
    @Autowired
    private ILiftService liftService

    private def createTicketUse() {
        def customer = TestDataGenerator.createCustomerAccount()
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def lift = new Lift("TestLift", 400, 400, 600, true)

        customerAccountService.saveCustomerAccount(customer)
        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        liftService.saveLift(lift)

        def price = new Price(ticketType, ticketCategory, new BigDecimal(30.00d), season)
        def purchasedTicket = new PurchasedTicket(
                customer, price, new Date(2000, 12, 12), new Date(2000, 12, 24), true)

        priceService.savePrice(price)
        purchasedTicketService.savePurchasedTicket(purchasedTicket)

        new TicketUse(purchasedTicket, lift, new Date(2040, 12, 21))
    }

    //getTicketUseById
    def "should return TicketUse"() {
        given:
        def ticketUse = createTicketUse()
        ticketUseService.saveTicketUse(ticketUse)

        when:
        def returnedTicketUse =  ticketUseService.getTicketUseById(ticketUse.getId())

        then:
        returnedTicketUse != null
        noExceptionThrown()
    }
    def "should not return TicketUse"() {
        when:
        ticketUseService.getTicketUseById(0)

        then:
        thrown NoSuchElementException
    }

    //saveTicketUse
    def "should add new TicketUse"() {
        given:
        def ticketUse = createTicketUse()

        when:
        def success = ticketUseService.saveTicketUse(ticketUse)

        then:
        success
    }

    //deleteTicketUses
    def "should delete TicketUses"() {
        given:
        def ticketUse = createTicketUse()
        def oldNumberOfTicketUses = ticketUseService.getAllTicketUses().size()

        ticketUseService.saveTicketUse(ticketUse)

        when:
        ticketUseService.deleteTicketUses([ticketUse.getId()])

        then:
        ticketUseService.getAllTicketUses().size() == oldNumberOfTicketUses
    }

    //getAllTicketUses
    def "should return all TicketUses"() {
        given:
        def oldNumberOfTicketUses = ticketUseService.getAllTicketUses().size()

        when:
        ticketUseService.saveTicketUse(createTicketUse())

        then:
        ticketUseService.getAllTicketUses().size() == oldNumberOfTicketUses + 1
    }
}
