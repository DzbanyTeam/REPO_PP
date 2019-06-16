package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.ITicketCategoryService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class TicketCategoryServiceTest extends Specification {

    @Autowired
    private ITicketCategoryService ticketCategoryService

    //getTicketCategoryById
    def "should return TicketCategory"() {
        given:
        def ticketCategory = TestDataGenerator.createTicketCategory()
        ticketCategoryService.saveTicketCategory(ticketCategory)

        when:
        def returnedTicketCategory =  ticketCategoryService.getTicketCategoryById(ticketCategory.getId())

        then:
        returnedTicketCategory != null
        noExceptionThrown()
    }
    def "should not return TicketCategory"() {
        when:
        ticketCategoryService.getTicketCategoryById(0)

        then:
        thrown NoSuchElementException
    }

    //saveTicketCategory
    def "should add new TicketCategory"() {
        given:
        def ticketCategory = TestDataGenerator.createTicketCategory()

        when:
        def success = ticketCategoryService.saveTicketCategory(ticketCategory)

        then:
        success
    }

    //deleteTicketCategories
    def "should delete TicketCategories"() {
        given:
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def oldNumberOfTicketCategories = ticketCategoryService.getAllTicketCategories().size()

        ticketCategoryService.saveTicketCategory(ticketCategory)

        when:
        ticketCategoryService.deleteTicketCategories([ticketCategory.getId()])

        then:
        ticketCategoryService.getAllTicketCategories().size() == oldNumberOfTicketCategories
    }

    //getAllTicketCategories
    def "should return all TicketCategories"() {
        given:
        def oldNumberOfTicketCategories = ticketCategoryService.getAllTicketCategories().size()

        when:
        ticketCategoryService.saveTicketCategory(TestDataGenerator.createTicketCategory())

        then:
        ticketCategoryService.getAllTicketCategories().size() == oldNumberOfTicketCategories + 1
    }
}
