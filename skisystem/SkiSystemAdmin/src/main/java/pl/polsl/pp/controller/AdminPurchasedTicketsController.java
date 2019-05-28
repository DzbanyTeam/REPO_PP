package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IPriceService;
import pl.polsl.pp.service.interfaces.IPurchasedTicketService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/purchased-tickets")
public class AdminPurchasedTicketsController {

    @Autowired
    @Qualifier("customerAccountServiceInterface")
    private ICustomerAccountService customerAccountService;

    @Autowired
    @Qualifier("purchasedTicketServiceInterface")
    private IPurchasedTicketService purchasedTicketService;

    @Autowired
    @Qualifier("priceServiceInterface")
    private IPriceService priceService;

    @GetMapping("{customerId}")
    public String showPurchasedTickets(Model model, @PathVariable Long customerId) {
        model.addAttribute("purchasedTickets", purchasedTicketService.getAllPurchasedTicketsByCustomerId(customerId)); // DONE: TODO: implement as such: purchasedTicketService.getAllPurchasedTicketsByCustomerId(customerId)
        model.addAttribute("customerAccount", customerAccountService.getCustomerAccountById(customerId)); // DONE:  TODO: get the customer account object
        return "cms/purchasedTicket/list";
    }

    @GetMapping("/add/{customerId}")
    public String showPurchasedTicketsAdd(Model model, @PathVariable Long customerId) {
        PurchasedTicket ticket = new PurchasedTicket();
        // DONE: TODO: set ticket's customer according to customerId path variable

        ticket.setCustomer(customerAccountService.getCustomerAccountById(customerId));
        model.addAttribute("purchasedTicket", ticket);
        model.addAttribute("prices", priceService.getAllPrices()); // DONE: TODO: get all prices
        return "cms/purchasedTicket/edit";
    }

    @GetMapping("/edit/{id}")
    public String showPurchasedTicketsEdit(Model model, @PathVariable Long id) {
        model.addAttribute("purchasedTicket", purchasedTicketService.getPurchasedTicketById(id)); // DONE: TODO: get the ticket
        model.addAttribute("prices", priceService.getAllPrices()); // DONE: TODO: get all prices
        return "cms/purchasedTicket/edit";
    }

    @PostMapping("/submit")
    public String submitPurchasedTicket(@ModelAttribute("purchasedTicket") PurchasedTicket purchasedTicketRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        purchasedTicketService.savePurchasedTicket(purchasedTicketRequest);
        return "redirect:/admin/purchased-tickets/edit/" + purchasedTicketRequest.getId();
    }

    @GetMapping("/update")
    public String showPurchasedTicketsUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        Long customerId = null; // DONE: TODO: get first purchasedTicket's customer ID
        int size = ids.size();
        if(size > 0) {
            customerId = purchasedTicketService.getPurchasedTicketById(ids.get(0)).getCustomer().getId();
        }
        switch (action) {
            case "delete":
                purchasedTicketService.deletePurchasedTickets(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto bilet." : ("Usunięto " + size + " bilety."));
                break;
            case "activate":
                purchasedTicketService.activatePurchasedTickets(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano bilet." : ("Aktywowano " + size + "  bilety."));
                break;
            case "deactivate":
                purchasedTicketService.deactivatePurchasedTickets(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano bilet." : ("Dezaktywowano " + size + " bilety."));
                break;
        }
        return "redirect:/admin/purchased-tickets/" + customerId.toString();
    }
}
