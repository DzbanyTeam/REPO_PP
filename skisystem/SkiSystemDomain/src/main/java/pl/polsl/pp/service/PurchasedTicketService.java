package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.repository.PurchasedTicketRepository;
import pl.polsl.pp.service.interfaces.IPurchasedTicketService;

import java.util.ArrayList;
import java.util.List;

public class PurchasedTicketService implements IPurchasedTicketService {

    @Autowired
    @Qualifier("purchasedTicketRepository")
    private PurchasedTicketRepository purchasedTicketRepository;

    @Override
    public PurchasedTicket getPurchasedTicketById(Long id) {
        return purchasedTicketRepository.findById(id).get();
    }

    @Override
    public boolean savePurchasedTicket(PurchasedTicket purchasedTicket) {
        try{
            purchasedTicketRepository.save(purchasedTicket);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deletePurchasedTickets(List<Long> ids) {
        try{
            ids.forEach(id -> purchasedTicketRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activatePurchasedTickets(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    @Override
    public boolean deactivatePurchasedTickets(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<PurchasedTicket> purchasedTicketList = (List<PurchasedTicket>)purchasedTicketRepository.findAllById(ids);
            purchasedTicketList.forEach(l -> {
                l.setActive(newStatus);
            });
            purchasedTicketRepository.saveAll(purchasedTicketList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<PurchasedTicket> getAllPurchasedTickets() {
        List<PurchasedTicket> purchasedTicketList = new ArrayList<>();
        purchasedTicketRepository.findAll().forEach(p -> purchasedTicketList.add(p));
        return purchasedTicketList;
    }
}
