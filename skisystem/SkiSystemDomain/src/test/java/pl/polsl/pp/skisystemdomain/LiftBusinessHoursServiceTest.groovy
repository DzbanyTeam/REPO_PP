package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.DayOfTheWeek
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService
import pl.polsl.pp.service.interfaces.ILiftBusinessHoursService
import pl.polsl.pp.service.interfaces.ILiftService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class LiftBusinessHoursServiceTest extends Specification {

    @Autowired
    private ILiftBusinessHoursService liftBusinessHoursService
    @Autowired
    private ILiftService liftService
    @Autowired
    private IDayOfTheWeekService dayOfTheWeekService

    //getLiftBusinessHoursById
    def "should return LiftBusinessHours by id"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)
        def lift = TestDataGenerator.createLift(0)
        def day = new DayOfTheWeek("day")

        liftService.saveLift(lift)
        dayOfTheWeekService.saveDayOfTheWeek(day)

        hour.setLift(lift)
        hour.setDayOfTheWeek(day)

        liftBusinessHoursService.saveLiftBusinessHours(hour)

        when:
        def LiftBusinessHours = liftBusinessHoursService.getLiftBusinessHoursById(hour.getId())

        then:
        LiftBusinessHours != null
        noExceptionThrown()
    }
    def "should not return LiftBusinessHours by id"() {
        when:
        liftBusinessHoursService.getLiftBusinessHoursById(0)
        then:
        thrown NoSuchElementException
    }

    //saveLiftBusinessHours
    def "should add new LiftBusinessHours"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)
        def lift = TestDataGenerator.createLift(0)
        def day = new DayOfTheWeek("day")

        liftService.saveLift(lift)
        dayOfTheWeekService.saveDayOfTheWeek(day)

        hour.setLift(lift)
        hour.setDayOfTheWeek(day)

        liftBusinessHoursService.saveLiftBusinessHours(hour)

        when:
        def success = liftBusinessHoursService.saveLiftBusinessHours(hour)

        then:
        success
        noExceptionThrown()
    }
    def "should not add new LiftBusinessHours"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)

        when:
        def success = liftBusinessHoursService.saveLiftBusinessHours(hour)

        then:
        !success
        noExceptionThrown()
    }

    //deleteLiftBusinessHours
    def "should delete LiftBusinessHours"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)
        def lift = TestDataGenerator.createLift(0)
        def day = new DayOfTheWeek("day")

        liftService.saveLift(lift)
        dayOfTheWeekService.saveDayOfTheWeek(day)

        hour.setLift(lift)
        hour.setDayOfTheWeek(day)

        def oldNumberOfLiftBusinessHours = liftBusinessHoursService.getAllLiftBusinessHours().size()

        liftBusinessHoursService.saveLiftBusinessHours(hour)

        when:
        liftBusinessHoursService.deleteLiftBusinessHours([hour.getId()])

        then:
        liftBusinessHoursService.getAllLiftBusinessHours().size() == oldNumberOfLiftBusinessHours
        noExceptionThrown()
    }

    //getAllLiftBusinessHours
    def "should return all LiftBusinessHours"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)
        def lift = TestDataGenerator.createLift(0)
        def day = new DayOfTheWeek("day")

        liftService.saveLift(lift)
        dayOfTheWeekService.saveDayOfTheWeek(day)

        hour.setLift(lift)
        hour.setDayOfTheWeek(day)

        def oldNumberOfLiftBusinessHours = liftBusinessHoursService.getAllLiftBusinessHours().size()

        when:
        liftBusinessHoursService.saveLiftBusinessHours(hour)

        then:
        liftBusinessHoursService.getAllLiftBusinessHours().size() == oldNumberOfLiftBusinessHours + 1
        noExceptionThrown()
    }

    //deleteLiftBusinnessHoursBySlopeIdAndDayId
    def "should delete LiftBusinessHours by SlopeId and DayId"() {
        given:
        def hour = TestDataGenerator.createLiftBusinessHour(0)
        def lift = TestDataGenerator.createLift(0)
        def day = new DayOfTheWeek("day")

        liftService.saveLift(lift)
        dayOfTheWeekService.saveDayOfTheWeek(day)

        hour.setLift(lift)
        hour.setDayOfTheWeek(day)

        liftBusinessHoursService.saveLiftBusinessHours(hour)

        def oldNumberOfHours = liftBusinessHoursService.getAllLiftBusinessHours().size()

        when:
        liftBusinessHoursService.deleteLiftBusinnesHoursBySlopeIdAndDayId(lift.getId(), day.getId())

        then:
        liftBusinessHoursService.getAllLiftBusinessHours().size() == oldNumberOfHours - 1
        noExceptionThrown()
    }
}
