package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketType;
import pl.polsl.pp.repository.TicketTypeRepository;
import pl.polsl.pp.service.interfaces.ITicketTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu TicketCategories
 */
public class TicketTypeService implements ITicketTypeService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli ticket_types
     */
    @Autowired
    @Qualifier("ticketTypeRepository")
    private TicketTypeRepository ticketTypeRepository;

    /**
     * Zwraca obiekt TicketCategories z danym id
     * @param id TicketCategories id
     * @return TicketCategories
     */
    @Override
    public TicketType getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt TicketCategories do bazy danych
     * @param ticketType obiekt TicketCategories do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveTicketType(TicketType ticketType) {
        try{
            ticketTypeRepository.save(ticketType);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty TicketCategories z podanymi id z bazy danych
     * @param ids lista TicketCategories id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteTicketTypes(List<Long> ids) {
        try{
            ids.forEach(id -> ticketTypeRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty TicketCategories z podanym id
     * @param ids lista TicketCategories id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateTicketTypes(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    /**
     * Dezaktywuje obiekty TicketCategories z podanym id
     * @param ids lista TicketCategories id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateTicketTypes(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    /**
     * Aktywuje/dezaktywuje obiekty TicketCategories z podanym id
     * @param ids lista TicketCategories id
     * @param newStatus true jeśli obiekty mają być aktywowane, false jeśli mają być dezaktywowane
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<TicketType> ticketTypeList = (List<TicketType>)ticketTypeRepository.findAllById(ids);
            ticketTypeList.forEach(l -> {
                l.setIsActive(newStatus);
            });
            ticketTypeRepository.saveAll(ticketTypeList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów TicketCategories
     * @return lista TicketCategories
     */
    @Override
    public List<TicketType> getAllTicketTypes() {
        List<TicketType> ticketTypeList = new ArrayList<>();
        ticketTypeRepository.findAll().forEach(t -> ticketTypeList.add(t));
        return ticketTypeList;
    }
}
