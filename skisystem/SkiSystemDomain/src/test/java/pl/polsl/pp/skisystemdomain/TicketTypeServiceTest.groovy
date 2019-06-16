package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.ITicketTypeService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class TicketTypeServiceTest extends Specification{

    @Autowired
    private ITicketTypeService ticketTypeService

    //getTicketTypeById
    def "should return TicketType"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        ticketTypeService.saveTicketType(ticketType)

        when:
        def returnedTicketType =  ticketTypeService.getTicketTypeById(ticketType.getId())

        then:
        returnedTicketType != null
        noExceptionThrown()
    }
    def "should not return TicketType"() {
        when:
        ticketTypeService.getTicketTypeById(0)

        then:
        thrown NoSuchElementException
    }

    //saveTicketType
    def "should add new TicketType"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()

        when:
        def success = ticketTypeService.saveTicketType(ticketType)

        then:
        success
    }

    //deleteTicketTypes
    def "should delete TicketTypes"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def oldNumberOfTicketTypes = ticketTypeService.getAllTicketTypes().size()

        ticketTypeService.saveTicketType(ticketType)

        when:
        ticketTypeService.deleteTicketTypes([ticketType.getId()])

        then:
        ticketTypeService.getAllTicketTypes().size() == oldNumberOfTicketTypes
    }

    //activateTicketTypes
    def "should activate TicketTypes" () {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        ticketType.setIsActive(false)

        ticketTypeService.saveTicketType(ticketType)

        when:
        def success = ticketTypeService.activateTicketTypes([ticketType.getId()])

        then:
        success
        ticketType.getIsActive()
        noExceptionThrown()
    }

    //deactivateTicketTypes
    def "should deactivate TicketTypes" () {
        given:
        def ticketType = TestDataGenerator.createTicketType()

        ticketTypeService.saveTicketType(ticketType)

        when:
        def success = ticketTypeService.deactivateTicketTypes([ticketType.getId()])

        then:
        success
        !ticketType.getIsActive()
        noExceptionThrown()
    }

    //getAllTicketTypes
    def "should return all TicketTypes"() {
        given:
        def oldNumberOfTicketTypes = ticketTypeService.getAllTicketTypes().size()

        when:
        ticketTypeService.saveTicketType(TestDataGenerator.createTicketType())

        then:
        ticketTypeService.getAllTicketTypes().size() == oldNumberOfTicketTypes + 1
    }
}
