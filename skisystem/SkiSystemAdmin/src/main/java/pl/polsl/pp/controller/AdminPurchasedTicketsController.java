package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.PurchasedTicket;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/purchased-tickets")
public class AdminPurchasedTicketsController {

    @GetMapping("{customerId}")
    public String showPurchasedTickets(Model model, @PathVariable Long customerId) {
        model.addAttribute("purchasedTickets", new ArrayList<PurchasedTicket>()); // TODO: implement as such: purchasedTicketService.getAllPurchasedTicketsByCustomerId(customerId)
        model.addAttribute("customerAccount", new CustomerAccount()); // TODO: get the customer account object
        return "cms/purchasedTicket/list";
    }

    @GetMapping("/add/{customerId}")
    public String showPurchasedTicketsAdd(Model model, @PathVariable Long customerId) {
        PurchasedTicket ticket = new PurchasedTicket();
        // TODO: set ticket's customer according to customerId path variable
        model.addAttribute("purchasedTicket", ticket);
        model.addAttribute("prices", new ArrayList<Price>()); // TODO: get all prices
        return "cms/purchasedTicket/edit";
    }

    @GetMapping("/edit/{id}")
    public String showPurchasedTicketsEdit(Model model, @PathVariable Long id) {
        model.addAttribute("purchasedTicket", new PurchasedTicket()); // TODO: get the ticket
        model.addAttribute("prices", new ArrayList<Price>()); // TODO: get all prices
        return "cms/purchasedTicket/edit";
    }

    @PostMapping("/submit")
    public String submitPurchasedTicket(@ModelAttribute("purchasedTicket") @Validated PurchasedTicket purchasedTicketRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/purchasedTickets/edit/" + purchasedTicketRequest.getId();
    }

    @GetMapping("/update")
    public String showPurchasedTicketsUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        Long customerId = null; // TODO: get first purchasedTicket's customer ID

        return "redirect:/admin/purchased-tickets/" + customerId.toString();
    }
}
