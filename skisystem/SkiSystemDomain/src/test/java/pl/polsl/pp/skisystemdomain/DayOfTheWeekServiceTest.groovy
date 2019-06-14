package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.DayOfTheWeek
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class DayOfTheWeekServiceTest extends Specification {

    @Autowired
    IDayOfTheWeekService dayOfTheWeekService

    //getDayOfTheWeekById
    def "should return DayOfTheWeek by id"() {
        given:
        def day = new DayOfTheWeek("2sday")
        dayOfTheWeekService.saveDayOfTheWeek(day)

        when:
        def dayOfTheWeek = dayOfTheWeekService.getDayOfTheWeekById(day.getId())

        then:
        dayOfTheWeek != null
        noExceptionThrown()
    }
    def "should not return DayOfTheWeek by id"() {
        when:
        dayOfTheWeekService.getDayOfTheWeekById(0)
        then:
        thrown NoSuchElementException
    }

    //saveDayOfTheWeek
    def "should add new DayOfTheWeek"() {
        when:
        def success = dayOfTheWeekService.saveDayOfTheWeek(new DayOfTheWeek("Today"))

        then:
        success
        noExceptionThrown()
    }
    def "should not add new DayOfTheWeek"() {
        when:
        def success = dayOfTheWeekService.saveDayOfTheWeek(new DayOfTheWeek(null))

        then:
        !success
        noExceptionThrown()
    }

    //deleteDaysOfTheWeek
    def "should delete DaysOfTheWeek"() {
        given:
        def day1 = new DayOfTheWeek("day1")
        def day2 = new DayOfTheWeek("day2")

        def oldNumberOfDays = dayOfTheWeekService.getAllDaysOfTheWeek().size()

        dayOfTheWeekService.saveDayOfTheWeek(day1)
        dayOfTheWeekService.saveDayOfTheWeek(day2)

        when:
        dayOfTheWeekService.deleteDaysOfTheWeek([day1.getId(), day2.getId()])
        def numberOfDays = dayOfTheWeekService.getAllDaysOfTheWeek().size()

        then:
        numberOfDays == oldNumberOfDays
        noExceptionThrown()
    }

    //getAllDaysOfTheWeek
    def "should return all DaysOfTheWeek"() {
        given:
        def day1 = new DayOfTheWeek("day1")
        def day2 = new DayOfTheWeek("day2")

        def oldNumberOfDays = dayOfTheWeekService.getAllDaysOfTheWeek().size()

        when:
        dayOfTheWeekService.saveDayOfTheWeek(day1)
        dayOfTheWeekService.saveDayOfTheWeek(day2)

        then:
        dayOfTheWeekService.getAllDaysOfTheWeek().size() == oldNumberOfDays + 2
        noExceptionThrown()
    }
}
