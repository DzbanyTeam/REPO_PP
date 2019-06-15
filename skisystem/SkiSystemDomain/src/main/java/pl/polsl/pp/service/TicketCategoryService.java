package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.repository.TicketCategoryRepository;
import pl.polsl.pp.service.interfaces.ITicketCategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu TicketCategory
 */
public class TicketCategoryService implements ITicketCategoryService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli ticket_categories
     */
    @Autowired
    @Qualifier("ticketCategoryRepository")
    private TicketCategoryRepository ticketCategoryRepository;

    /**
     * Zwraca obiekt TicketCategory z danym id
     * @param id TicketCategory id
     * @return TicketCategory
     */
    @Override
    public TicketCategory getTicketCategoryById(Long id) {
        return ticketCategoryRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt TicketCategory do bazy danych
     * @param ticketCategory obiekt TicketCategory do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveTicketCategory(TicketCategory ticketCategory) {
        try{
            ticketCategoryRepository.save(ticketCategory);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty TicketCategory z podanymi id z bazy danych
     * @param ids lista TicketCategory id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteTicketCategories(List<Long> ids) {
        try{
            ids.forEach(id -> ticketCategoryRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów TicketCategory
     * @return lista TicketCategory
     */
    @Override
    public List<TicketCategory> getAllTicketCategories() {
        List<TicketCategory> ticketCategoryList = new ArrayList<>();
        ticketCategoryRepository.findAll().forEach(t -> ticketCategoryList.add(t));
        return ticketCategoryList;
    }
}
