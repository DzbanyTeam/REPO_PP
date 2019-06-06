package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.IDifficultyService
import spock.lang.Specification

@SpringBootTest
class LoadBeanTest extends Specification {

    @Autowired
    @Qualifier("difficultyService")
    private IDifficultyService iDifficultyService

    def "should correctly load service bean XD"() {
        expect: "the service bean is loaded"
        iDifficultyService
    }
}
