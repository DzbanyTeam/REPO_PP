package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.model.Difficulty
import pl.polsl.pp.service.interfaces.IDifficultyService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class DifficultyServiceTest extends Specification {

    @Autowired
    private IDifficultyService difficultyService

    //getDifficultyById
    def "should return Difficulty"() {
        given:
        def difficulty = new Difficulty("Very Hard")
        difficultyService.saveDifficulty(difficulty)

        when:
        def id = difficultyService.getDifficultyById(difficulty.getId()).getId()

        then:
        id == difficulty.getId()
        noExceptionThrown()
    }
    def "should not return Difficulty"() {
        when:
        difficultyService.getDifficultyById(0)

        then:
        thrown NoSuchElementException
    }

    //saveDifficulty
    def "should add new Difficulty"() {
        when:
        def success = difficultyService.saveDifficulty(new Difficulty("Very Hard"))

        then:
        success
        noExceptionThrown()
    }
    def "should not add new Difficulty"() {
        when:
        def fail = difficultyService.saveDifficulty(new Difficulty(null))

        then:
        !fail
        noExceptionThrown()
    }

    //deleteDifficulties
    def "should delete Difficulties"() {
        given:
        def difficulty = new Difficulty("diff")
        difficultyService.saveDifficulty(difficulty)
        def oldNumberOfDifficulties = difficultyService.getAllDifficulties().size()

        when:
        def success = difficultyService.deleteDifficulties([difficulty.getId()])
        def numberOfDifficulties = difficultyService.getAllDifficulties().size()

        then:
        success
        numberOfDifficulties == oldNumberOfDifficulties - 1
    }
    def "should not delete Difficulties"() {
        when:
        def fail = difficultyService.deleteDifficulties([0])

        then:
        !fail
        noExceptionThrown()
    }

    //getAllDifficulties
    def "should return all Difficulties"() {
        given:
        def difficulty1 = new Difficulty("diff1")
        def difficulty2 = new Difficulty("diff2")

        def oldNumberOfDifficulties = difficultyService.getAllDifficulties().size()

        when:
        difficultyService.saveDifficulty(difficulty1)
        difficultyService.saveDifficulty(difficulty2)

        def numberOfDifficulties = difficultyService.getAllDifficulties().size()

        then:
        numberOfDifficulties == oldNumberOfDifficulties + 2
        noExceptionThrown()
    }
}
