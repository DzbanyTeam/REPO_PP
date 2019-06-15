package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Price
import pl.polsl.pp.model.PurchasedTicket
import pl.polsl.pp.service.interfaces.ICustomerAccountService
import pl.polsl.pp.service.interfaces.IPriceService
import pl.polsl.pp.service.interfaces.IPurchasedTicketService
import pl.polsl.pp.service.interfaces.ISeasonService
import pl.polsl.pp.service.interfaces.ITicketCategoryService
import pl.polsl.pp.service.interfaces.ITicketTypeService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class PurchasedTicketServiceTest extends Specification {

    @Autowired
    private IPurchasedTicketService purchasedTicketService
    @Autowired
    private ICustomerAccountService customerAccountService
    @Autowired
    private IPriceService priceService
    @Autowired
    private  ITicketTypeService ticketTypeService
    @Autowired
    private ITicketCategoryService ticketCategoryService
    @Autowired
    private ISeasonService seasonService

    private def createTicket() {
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, new BigDecimal(30d), season)
        def customer = TestDataGenerator.createCustomerAccount()

        def purchase = new Date(2000, 2, 20)
        def expiration = new Date(2000, 2, 25)

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)
        customerAccountService.saveCustomerAccount(customer)

        new PurchasedTicket(customer, price, purchase, expiration, true)
    }

    //getPurchasesTicketById
    def "should return PurchasedTicket"() {
        given:
        def ticket = createTicket()

        purchasedTicketService.savePurchasedTicket(ticket)

        when:
        def returnedTicket = purchasedTicketService.getPurchasedTicketById(ticket.getId())

        then:
        returnedTicket != null
        noExceptionThrown()
    }

    //savePurchasedTicket
    def "should add new PurchasedTicket"() {
        given:
        def ticket = createTicket()

        when:
        def success = purchasedTicketService.savePurchasedTicket(ticket)

        then:
        success
        noExceptionThrown()
    }

    //deletePurchasedTickets
    def "should delete PurchasedTicket"() {
        given:
        def ticket = createTicket()
        def oldNumberOfPurchasedTickets = purchasedTicketService.getAllPurchasedTickets().size()

        purchasedTicketService.savePurchasedTicket(ticket)

        when:
        def success = purchasedTicketService.deletePurchasedTickets([ticket.getId()])

        then:
        success
        purchasedTicketService.getAllPurchasedTickets().size() == oldNumberOfPurchasedTickets
    }

    //activatePurchasedTickets
    def "should activate PurchasedTickets"() {
        given:
        def ticket = createTicket()
        ticket.setIsActive(false)

        purchasedTicketService.savePurchasedTicket(ticket)

        when:
        purchasedTicketService.activatePurchasedTickets([ticket.getId()])

        then:
        ticket.getIsActive()
    }

    //deactivatePurchasedTickets
    def "should deactivate PurchasedTickets"() {
        given:
        def ticket = createTicket()

        purchasedTicketService.savePurchasedTicket(ticket)

        when:
        purchasedTicketService.deactivatePurchasedTickets([ticket.getId()])

        then:
        !ticket.getIsActive()
    }

    //getAllPurchasedTickets
    def "should return all PurchasedTickets"() {
        given:
        def ticket = createTicket()
        def oldNumberOfPurchasedTickets = purchasedTicketService.getAllPurchasedTickets().size()

        when:
        purchasedTicketService.savePurchasedTicket(ticket)

        then:
        purchasedTicketService.getAllPurchasedTickets().size() == oldNumberOfPurchasedTickets + 1
    }

    //getAllPurchasedTicketsByCustomerId
    def "should return all PurchasedTickets by Customer Id"() {
        given:
        def ticket = createTicket()
        def ticket1 = createTicket()

        when:
        purchasedTicketService.savePurchasedTicket(ticket)
        purchasedTicketService.savePurchasedTicket(ticket1)

        then:
        purchasedTicketService.getAllPurchasedTicketsByCustomerId(ticket.getCustomer().getId()).size() == 1
    }
}
