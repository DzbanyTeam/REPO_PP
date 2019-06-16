package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.repository.PurchasedTicketRepository;
import pl.polsl.pp.service.interfaces.IPurchasedTicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu PurchasedTicket
 */
public class PurchasedTicketService implements IPurchasedTicketService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli purchased_tickets
     */
    @Autowired
    @Qualifier("purchasedTicketRepository")
    private PurchasedTicketRepository purchasedTicketRepository;

    /**
     * Zwraca obiekt PurchasedTicket z danym id
     * @param id PurchasedTicket id
     * @return PurchasedTicket
     */
    @Override
    public PurchasedTicket getPurchasedTicketById(Long id) {
        return purchasedTicketRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt PurchasedTicket do bazy danych
     * @param purchasedTicket obiekt PurchasedTicket do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean savePurchasedTicket(PurchasedTicket purchasedTicket) {
        try{
            purchasedTicketRepository.save(purchasedTicket);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty PurchasedTicket z podanymi id z bazy danych
     * @param ids lista PurchasedTicket id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deletePurchasedTickets(List<Long> ids) {
        try{
            ids.forEach(id -> purchasedTicketRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty PurchasedTicket z podanym id
     * @param ids lista PurchasedTicket id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activatePurchasedTickets(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    /**
     * Dezaktywuje obiekty PurchasedTicket z podanym id
     * @param ids lista PurchasedTicket id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivatePurchasedTickets(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    /**
     * Aktywuje/dezaktywuje obiekty PurchasedTicket z podanym id
     * @param ids lista PurchasedTicket id
     * @param newStatus true jeśli obiekty mają być aktywowane, false jeśli mają być dezaktywowane
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<PurchasedTicket> purchasedTicketList = (List<PurchasedTicket>)purchasedTicketRepository.findAllById(ids);
            purchasedTicketList.forEach(l -> {
                l.setIsActive(newStatus);
            });
            purchasedTicketRepository.saveAll(purchasedTicketList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów PurchasedTicket
     * @return lista PurchasedTicket
     */
    @Override
    public List<PurchasedTicket> getAllPurchasedTickets() {
        List<PurchasedTicket> purchasedTicketList = new ArrayList<>();
        purchasedTicketRepository.findAll().forEach(p -> purchasedTicketList.add(p));
        return purchasedTicketList;
    }

    /**
     * Zwraca listę wszystkich obiektów PurchasedTicket z podanym id klienta
     * @param customerId CustomerAccount id
     * @return lista PurchasedTicket
     */
    @Override
    public List<PurchasedTicket> getAllPurchasedTicketsByCustomerId(Long customerId) {

        List<PurchasedTicket> purchasedTicketOfCustomer = new ArrayList<>();

        for(PurchasedTicket purchasedTicket: this.getAllPurchasedTickets()) {
            if(purchasedTicket.getCustomer().getId().equals(customerId)) {
                purchasedTicketOfCustomer.add(purchasedTicket);
            }
        }
        return purchasedTicketOfCustomer;
    }
}
