package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.repository.PriceRepository;
import pl.polsl.pp.service.interfaces.IPriceService;

import java.util.ArrayList;
import java.util.List;

public class PriceService implements IPriceService {

    @Autowired
    @Qualifier("priceRepository")
    private PriceRepository priceRepository;

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).get();
    }

    @Override
    public Price getPriceByTypeAndCategory(Long typeId, Long categoryId) {


        for(Price price: this.getAllPrices()) {
            if(price.getTicketType().getId().equals(typeId) && price.getTicketCategory().getId().equals(categoryId)) {
                return price;
            }
        }
        return null;
    }

    @Override
    public Price getPriceByTypeAndCategoryAndSeason(Long typeId, Long categoryId, Long seasonId) {

        for(Price price: this.getAllPrices()) {
            if(price.getTicketType().getId().equals(typeId) && price.getTicketCategory().getId().equals(categoryId) && price.getSeason().getId().equals(seasonId)) {
                return price;
            }
        }
        return null;

    }

    @Override
    public boolean savePrice(Price price) {
        try{
            priceRepository.save(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deletePrices(List<Long> ids) {
        try{
            ids.forEach(id -> priceRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Price> getAllPrices() {
        List<Price> priceList = new ArrayList<>();
        priceRepository.findAll().forEach(p -> priceList.add(p));
        return priceList;
    }
}
