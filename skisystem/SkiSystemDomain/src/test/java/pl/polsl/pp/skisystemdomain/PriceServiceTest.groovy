package pl.polsl.pp.skisystemdomain

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.method.P
import pl.polsl.pp.model.Price
import pl.polsl.pp.service.interfaces.IPriceService
import pl.polsl.pp.service.interfaces.ISeasonService
import pl.polsl.pp.service.interfaces.ITicketCategoryService
import pl.polsl.pp.service.interfaces.ITicketTypeService
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
class PriceServiceTest extends Specification {

    @Autowired
    private IPriceService priceService
    @Autowired
    private  ITicketTypeService ticketTypeService
    @Autowired
    private ITicketCategoryService ticketCategoryService
    @Autowired
    private ISeasonService seasonService
    //getPriceById
    def "should return Price"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, new BigDecimal(30d), season)

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)

        when:
        def returnedPrice = priceService.getPriceById(price.getId())

        then:
        returnedPrice != null
        noExceptionThrown()
    }

    //getPriceByTypeAndCategory
    def "should return Price by Type&Category"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, new BigDecimal(30d), season)

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)

        when:
        def returnedPrice = priceService.getPriceByTypeAndCategory(price.getTicketType().getId(), price.getTicketCategory().getId())

        then:
        returnedPrice != null
        noExceptionThrown()
    }

    //getPriceByTypeAndCategoryAndSeason
    def "should return Price by Type&Category&Season"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, new BigDecimal(30d), season)

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)

        when:
        def returnedPrice = priceService.getPriceByTypeAndCategoryAndSeason(price.getTicketType().getId(), price.getTicketCategory().getId(), price.getSeason().getId())

        then:
        returnedPrice != null
        noExceptionThrown()
    }

    //getAllPricesInActiveSeasons
    def "should return Prices in active Seasons"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def seasonInactive = TestDataGenerator.createSeason()
        seasonInactive.setIsActive(false)
        def price = new Price(ticketType, ticketCategory, new BigDecimal(30d), season)
        def priceNotActive = new Price(ticketType, ticketCategory, new BigDecimal(30d), seasonInactive)
        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        seasonService.saveSeason(seasonInactive)
        priceService.savePrice(price)

        when:
        priceService.savePrice(price)
        priceService.savePrice(priceNotActive)

        then:
        priceService.getAllPricesInActiveSeasons().size() == 1
    }

    //savePrice
    def "should add new Price"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, BigDecimal.valueOf(30d), season)

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)

        when:
        def success = priceService.savePrice(price)

        then:
        success
    }

    //deletePrices
    def "should delete Prices"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, BigDecimal.valueOf(30d), season)
        def oldNumberOfPrices = priceService.getAllPrices().size()

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)

        when:
        priceService.deletePrices([price.getId()])

        then:
        priceService.getAllPrices().size() == oldNumberOfPrices
    }

    //getAllPrices
    def "should return all Prices"() {
        given:
        def ticketType = TestDataGenerator.createTicketType()
        def ticketCategory = TestDataGenerator.createTicketCategory()
        def season = TestDataGenerator.createSeason()
        def price = new Price(ticketType, ticketCategory, BigDecimal.valueOf(30d), season)
        def oldNumberOfPrices = priceService.getAllPrices().size()

        ticketTypeService.saveTicketType(ticketType)
        ticketCategoryService.saveTicketCategory(ticketCategory)
        seasonService.saveSeason(season)
        priceService.savePrice(price)

        when:
        priceService.savePrice(price)

        then:
        priceService.getAllPrices().size() == oldNumberOfPrices + 1
    }
}
