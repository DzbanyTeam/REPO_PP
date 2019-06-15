package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.Season;
import pl.polsl.pp.repository.PriceRepository;
import pl.polsl.pp.repository.SeasonRepository;
import pl.polsl.pp.service.interfaces.IPriceService;
import pl.polsl.pp.service.interfaces.ISeasonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu Price
 */
public class PriceService implements IPriceService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli prices
     */
    @Autowired
    @Qualifier("priceRepository")
    private PriceRepository priceRepository;

    /**
     * Obiekt pozwalający na wykonywanie operacji na obiektach typu Season
     */
    @Autowired
    @Qualifier("seasonService")
    private ISeasonService seasonService;

    /**
     * Zwraca obiekt Price z danym id
     * @param id Price id
     * @return Price
     */
    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).get();
    }

    /**
     * Zwraca obiekt Price z danym id typu i kategorii biletu
     * @param typeId TicketType id
     * @param categoryId TicketCategory id
     * @return Price
     */
    @Override
    public Price getPriceByTypeAndCategory(Long typeId, Long categoryId) {


        for(Price price: this.getAllPrices()) {
            if(price.getTicketType().getId().equals(typeId) && price.getTicketCategory().getId().equals(categoryId)) {
                return price;
            }
        }
        return null;
    }

    /**
     * Zwraca obiekt Price z danym id sezonu, typu i kategorii biletu
     * @param typeId TicketType id
     * @param categoryId TicketCategory id
     * @param seasonId Season id
     * @return Price
     */
    @Override
    public Price getPriceByTypeAndCategoryAndSeason(Long typeId, Long categoryId, Long seasonId) {

        for(Price price: this.getAllPrices()) {
            if(price.getTicketType().getId().equals(typeId) && price.getTicketCategory().getId().equals(categoryId) && price.getSeason().getId().equals(seasonId)) {
                return price;
            }
        }
        return null;

    }

    /**
     * Zwraca listę obiektów Price dla których sezon jest aktywny
     * @return list Price
     */
    @Override
    public List<Price> getAllPricesInActiveSeasons() {
        List<Price> priceList = new ArrayList<>();
        List<Season> activeSeasons = seasonService.getAllActiveSeasons();
        priceRepository.findAll().forEach(p -> {
            if(activeSeasons.contains(p.getSeason())){
                priceList.add(p);
            }
        });
        return priceList;
    }

    /**
     * Dodaje dany obiekt Price do bazy danych
     * @param price obiekt Price do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean savePrice(Price price) {
        try{
            priceRepository.save(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty Price z podanymi id z bazy danych
     * @param ids lista Price id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deletePrices(List<Long> ids) {
        try{
            ids.forEach(id -> priceRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów Price
     * @return lista Price
     */
    @Override
    public List<Price> getAllPrices() {
        List<Price> priceList = new ArrayList<>();
        priceRepository.findAll().forEach(p -> priceList.add(p));
        return priceList;
    }
}
