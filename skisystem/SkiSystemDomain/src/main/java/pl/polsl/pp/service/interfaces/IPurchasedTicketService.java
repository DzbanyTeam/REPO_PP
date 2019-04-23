package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.PurchasedTicket;

import java.util.List;

public interface IPurchasedTicketService {

    PurchasedTicket getPurchasedTicketById(Long id);
    boolean savePurchasedTicket(PurchasedTicket purchasedTicket);
    boolean deletePurchasedTickets(List<Long> ids);
    boolean activatePurchasedTickets(List<Long> ids);
    boolean deactivatePurchasedTickets(List<Long> ids);
    List<PurchasedTicket> getAllPurchasedTickets();
}
