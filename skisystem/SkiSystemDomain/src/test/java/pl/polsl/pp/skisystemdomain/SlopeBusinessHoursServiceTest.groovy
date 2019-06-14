package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.DayOfTheWeek
import pl.polsl.pp.model.Difficulty
import pl.polsl.pp.model.Slope
import pl.polsl.pp.model.SlopeBusinessHours
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService
import pl.polsl.pp.service.interfaces.IDifficultyService
import pl.polsl.pp.service.interfaces.ISlopeBusinessHoursService
import pl.polsl.pp.service.interfaces.ISlopeService
import spock.lang.Specification

import javax.transaction.Transactional
import java.sql.Time

@SpringBootTest
@Transactional
class SlopeBusinessHoursServiceTest extends Specification{

    @Autowired
    private ISlopeBusinessHoursService slopeBusinessHoursService
    @Autowired
    private IDayOfTheWeekService dayOfTheWeekService
    @Autowired
    private ISlopeService slopeServices
    @Autowired
    private IDifficultyService difficultyService

    private def createSlopeBusinessHours() {
        def day = new DayOfTheWeek("DAY")
        def difficulty = new Difficulty("eZ")
        def slope = new Slope("Stok", 300, 300, 200, difficulty, true)

        dayOfTheWeekService.saveDayOfTheWeek(day)
        difficultyService.saveDifficulty(difficulty)
        slopeServices.saveSlope(slope)

        new SlopeBusinessHours(day, new Time(13, 30, 25), new Time(21, 37, 25), slope)
    }

    //getSlopeBusinessHoursById
    def "should return SlopeBusinessHours"() {
        given:
        def hours = createSlopeBusinessHours()

        slopeBusinessHoursService.saveSlopeBusinessHours(hours)

        when:
        def returnedHours = slopeBusinessHoursService.getSlopeBusinessHoursById(hours.getId())

        then:
        returnedHours != null
        noExceptionThrown()
    }

    //deleteSlopeBusinessHours
    def "should delete SlopeBusinessHours" () {
        given:
        def oldNumberOfHours = slopeBusinessHoursService.getAllSlopeBusinessHours().size()
        def hours = createSlopeBusinessHours()

        slopeBusinessHoursService.saveSlopeBusinessHours(hours)

        when:
        slopeBusinessHoursService.deleteSlopeBusinessHours([hours.getId()])

        then:
        slopeBusinessHoursService.getAllSlopeBusinessHours().size() == oldNumberOfHours
    }

    //getAllSlopeBusinessHours
    def "should return all SlopeBusinessHours"() {
        given:
        def oldNumberOfHours = slopeBusinessHoursService.getAllSlopeBusinessHours().size()
        def hours = createSlopeBusinessHours()

        when:
        slopeBusinessHoursService.saveSlopeBusinessHours(hours)

        then:
        slopeBusinessHoursService.getAllSlopeBusinessHours().size() == oldNumberOfHours + 1
    }

    //updateSlopeBusinnesHoursBySlopeIdAndDayId
    def "should update SlopeBusinessHours by SlopeId and DayId"() {
        given:
        def opening = new Time(12, 30, 0)
        def closing = new Time(17, 30, 0)
        def hour = createSlopeBusinessHours()

        slopeBusinessHoursService.saveSlopeBusinessHours(hour)

        when:
        slopeBusinessHoursService.
                updateSlopeBusinnesHoursBySlopeIdAndDayId(
                        hour.getSlope().getId(), hour.getDayOfTheWeek().getId(), opening, closing)

        then:
        hour.getOpeningHour() == opening
        hour.getClosingHour() == closing
    }

    //deleteSlopeBusinnesHoursBySlopeIdAndDayId
    def "should delete SlopeBusinessHours by SlopeId and DayId"() {
        def hour1 = createSlopeBusinessHours()
        def hour2 = createSlopeBusinessHours()

        slopeBusinessHoursService.saveSlopeBusinessHours(hour1)
        slopeBusinessHoursService.saveSlopeBusinessHours(hour2)

        def oldNumberOfHours = slopeBusinessHoursService.getAllSlopeBusinessHours().size()

        when:
        slopeBusinessHoursService.deleteSlopeBusinnesHoursBySlopeIdAndDayId(
                hour2.getSlope().getId(), hour2.getDayOfTheWeek().getId())

        then:
        slopeBusinessHoursService.getAllSlopeBusinessHours().size() == oldNumberOfHours - 1
    }
}
