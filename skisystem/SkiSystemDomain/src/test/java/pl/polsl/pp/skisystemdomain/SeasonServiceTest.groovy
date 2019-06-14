package pl.polsl.pp.skisystemdomain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.polsl.pp.service.interfaces.ISeasonService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class SeasonServiceTest extends Specification {

    @Autowired
    private ISeasonService seasonService

    //getSeasonById
    def "should return Season"() {
        given:
        def season = TestDataGenerator.createSeason()
        seasonService.saveSeason(season)

        when:
        def returnedSeason =  seasonService.getSeasonById(season.getId())

        then:
        returnedSeason != null
        noExceptionThrown()
    }
    def "should not return Season"() {
        when:
        seasonService.getSeasonById(0)

        then:
        thrown NoSuchElementException
    }

    //saveSeason
    def "should add new Season"() {
        given:
        def season = TestDataGenerator.createSeason()

        when:
        def success = seasonService.saveSeason(season)

        then:
        success
    }

    //deleteSeasons
    def "should delete Seasons"() {
        given:
        def season = TestDataGenerator.createSeason()
        def oldNumberOfSeasons = seasonService.getAllSeasons().size()

        seasonService.saveSeason(season)

        when:
        seasonService.deleteSeasons([season.getId()])

        then:
        seasonService.getAllSeasons().size() == oldNumberOfSeasons
    }

    //activateSeasons
    def "should activate Seasons" () {
        given:
        def season = TestDataGenerator.createSeason()
        season.setIsActive(false)

        seasonService.saveSeason(season)

        when:
        def success = seasonService.activateSeasons([season.getId()])

        then:
        success
        season.getIsActive()
        noExceptionThrown()
    }

    //deactivateSeasons
    def "should deactivate Seasons" () {
        given:
        def season = TestDataGenerator.createSeason()

        seasonService.saveSeason(season)

        when:
        def success = seasonService.deactivateSeasons([season.getId()])

        then:
        success
        !season.getIsActive()
        noExceptionThrown()
    }

    //getAllSeasons
    def "should return all Seasons"() {
        given:
        def oldNumberOfSeasons = seasonService.getAllSeasons().size()

        when:
        seasonService.saveSeason(TestDataGenerator.createSeason())

        then:
        seasonService.getAllSeasons().size() == oldNumberOfSeasons + 1
    }

    //getAllActiveSeasons
    def "should return all active Seasons"() {
        given:
        def oldNumberOfActiveSeasons = seasonService.getAllActiveSeasons().size()
        def season = TestDataGenerator.createSeason()
        def seasonInactive = TestDataGenerator.createSeason()
        seasonInactive.setIsActive(false)

        when:
        seasonService.saveSeason(season)
        seasonService.saveSeason(seasonInactive)

        then:
        seasonService.getAllActiveSeasons().size() == oldNumberOfActiveSeasons + 1
    }
}
