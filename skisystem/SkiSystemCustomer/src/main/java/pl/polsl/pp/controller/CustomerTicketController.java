package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IPriceService;
import pl.polsl.pp.service.interfaces.IPurchasedTicketService;

import java.util.List;

@Controller
@RequestMapping("/customer/tickets")
public class CustomerTicketController {


    @Autowired
    @Qualifier("customerAccountServiceInterface")
    private ICustomerAccountService customerAccountService;

    @Autowired
    @Qualifier("purchasedTicketServiceInterface")
    private IPurchasedTicketService purchasedTicketService;

    @Autowired
    @Qualifier("priceServiceInterface")
    private IPriceService priceService;


    public CustomerTicketController() {

    }

    @GetMapping("/history")
    public String showTickets(Model model) {
        /**
         * TODO: zmienic zeby bralo id aktualnie zalogowanego customera
         */
        List<PurchasedTicket> tickets = purchasedTicketService.getAllPurchasedTicketsByCustomerId(1L);
        model.addAttribute("tickets", tickets);
        return "site/customer/history";
    }

    @GetMapping("/purchase/success")
    public String showTicketsPurchaseSuccess() {
        return "index";
    }

    @GetMapping("/purchase/error")
    public String showTicketsPurchaseError() {
        return "index";
    }

    @GetMapping("/purchase")
    public String showTicketsPurchase(Model model) {
        /**
         * TODO: zmienic zeby bralo id aktualnie zalogowanego customera
         */
        PurchasedTicket ticket = new PurchasedTicket();
        ticket.setCustomer(customerAccountService.getCustomerAccountById(1L));
        model.addAttribute("purchasedTicket", ticket);
        model.addAttribute("prices", priceService.getAllPrices());
        return "site/customer/purchase";
    }

    @PostMapping("/purchase")
    public String submitTicketsPurchase(@ModelAttribute("purchasedTicket") PurchasedTicket purchasedTicketRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        purchasedTicketService.savePurchasedTicket(purchasedTicketRequest);
        redirectAttributes.addFlashAttribute("alertText", "Zakupiono bilet.");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/customer/tickets/purchase";
    }
}
