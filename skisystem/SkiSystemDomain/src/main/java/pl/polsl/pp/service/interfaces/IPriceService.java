package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Price;

import java.util.List;

public interface IPriceService {

    Price getPriceById(Long id);
    Price getPriceByTypeAndCategory(Long typeId, Long categoryId);
    boolean savePrice(Price price);
    boolean deletePrices(List<Long> ids);
    List<Price> getAllPrices();
}
