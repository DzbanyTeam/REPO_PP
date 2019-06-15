package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketUse;
import pl.polsl.pp.repository.TicketUseRepository;
import pl.polsl.pp.service.interfaces.ITicketUseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu TicketUse
 */
public class TicketUseService implements ITicketUseService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli ticket_usages
     */
    @Autowired
    @Qualifier("ticketUseRepository")
    private TicketUseRepository ticketUseRepository;

    /**
     * Zwraca obiekt TicketUse z danym id
     * @param id TicketUse id
     * @return TicketUse
     */
    @Override
    public TicketUse getTicketUseById(Long id) {
        return ticketUseRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt TicketUse do bazy danych
     * @param ticketUse obiekt TicketUse do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveTicketUse(TicketUse ticketUse) {
        try{
            ticketUseRepository.save(ticketUse);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty TicketUse z podanymi id z bazy danych
     * @param ids lista TicketUse id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteTicketUses(List<Long> ids) {
        try{
            ids.forEach(id -> ticketUseRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów TicketUse
     * @return lista TicketUse
     */
    @Override
    public List<TicketUse> getAllTicketUses() {
        List<TicketUse> ticketUseList = new ArrayList<>();
        ticketUseRepository.findAll().forEach(t -> ticketUseList.add(t));
        return ticketUseList;
    }
}
