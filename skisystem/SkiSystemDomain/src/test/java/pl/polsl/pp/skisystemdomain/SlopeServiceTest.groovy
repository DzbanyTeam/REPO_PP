package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Difficulty
import pl.polsl.pp.model.Slope
import pl.polsl.pp.service.interfaces.IDifficultyService
import pl.polsl.pp.service.interfaces.ISlopeService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class SlopeServiceTest extends Specification {

    @Autowired
    private ISlopeService slopeService
    @Autowired
    private IDifficultyService difficultyService

    private def createSlope() {
        def difficulty = new Difficulty("eZ")

        difficultyService.saveDifficulty(difficulty)

        new Slope("Stok", 300, 300, 200, difficulty, true)
    }

    //getSlopeById
    def "should return Slope"() {
        given:
        def slope = createSlope()
        slopeService.saveSlope(slope)

        when:
        def returnedSlope =  slopeService.getSlopeById(slope.getId())

        then:
        returnedSlope != null
        noExceptionThrown()
    }
    def "should not return Slope"() {
        when:
        slopeService.getSlopeById(0)

        then:
        thrown NoSuchElementException
    }

    //saveSlope
    def "should add new Slope"() {
        given:
        def slope = createSlope()

        when:
        def success = slopeService.saveSlope(slope)

        then:
        success
    }

    //deleteSlopes
    def "should delete Slopes"() {
        given:
        def slope = createSlope()
        def oldNumberOfSlopes = slopeService.getAllSlopes().size()

        slopeService.saveSlope(slope)

        when:
        slopeService.deleteSlopes([slope.getId()])

        then:
        slopeService.getAllSlopes().size() == oldNumberOfSlopes
    }

    //activateSlopes
    def "should activate Slopes" () {
        given:
        def slope = createSlope()
        slope.setIsActive(false)

        slopeService.saveSlope(slope)

        when:
        def success = slopeService.activateSlopes([slope.getId()])

        then:
        success
        slope.getIsActive()
        noExceptionThrown()
    }

    //deactivateSlopes
    def "should deactivate Slopes" () {
        given:
        def slope = createSlope()

        slopeService.saveSlope(slope)

        when:
        def success = slopeService.deactivateSlopes([slope.getId()])

        then:
        success
        !slope.getIsActive()
        noExceptionThrown()
    }

    //getAllSlopes
    def "should return all Slopes"() {
        given:
        def oldNumberOfSlopes = slopeService.getAllSlopes().size()

        when:
        slopeService.saveSlope(createSlope())

        then:
        slopeService.getAllSlopes().size() == oldNumberOfSlopes + 1
    }
}
