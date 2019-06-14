package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.ILiftService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class LiftServiceTest extends  Specification {

    @Autowired
    private ILiftService liftService

    //getLiftById
    def "should return Lift"() {
        given:
        def lift = TestDataGenerator.createLift(0)
        liftService.saveLift(lift)

        when:
        def returnedLift =  liftService.getLiftById(lift.getId())

        then:
        returnedLift != null
        noExceptionThrown()
    }
    def "should not return Lift"() {
        when:
        liftService.getLiftById(0)

        then:
        thrown NoSuchElementException
    }

    //saveLift
    def "should add new Lift"() {
        given:
        def lift = TestDataGenerator.createLift(0)

        when:
        def success = liftService.saveLift(lift)

        then:
        success
    }

    //deleteLifts
    def "should delete Lifts"() {
        given:
        def lift = TestDataGenerator.createLift(0)
        def oldNumberOfLifts = liftService.getAllLifts().size()

        liftService.saveLift(lift)

        when:
        liftService.deleteLifts([lift.getId()])

        then:
        liftService.getAllLifts().size() == oldNumberOfLifts
    }

    //activateLifts
    def "should activate Lifts" () {
        given:
        def lift = TestDataGenerator.createLift(0)
        lift.setIsActive(false)

        liftService.saveLift(lift)

        when:
        def success = liftService.activateLifts([lift.getId()])

        then:
        success
        lift.getIsActive()
        noExceptionThrown()
    }

    //deactivateLifts
    def "should deactivate Lifts" () {
        given:
        def lift = TestDataGenerator.createLift(0)

        liftService.saveLift(lift)

        when:
        def success = liftService.deactivateLifts([lift.getId()])

        then:
        success
        !lift.getIsActive()
        noExceptionThrown()
    }

    //getAllLifts
    def "should return all Lifts"() {
        given:
        def oldNumberOfLifts = liftService.getAllLifts().size()

        when:
        liftService.saveLift(TestDataGenerator.createLift(0))

        then:
        liftService.getAllLifts().size() == oldNumberOfLifts + 1
    }
}
