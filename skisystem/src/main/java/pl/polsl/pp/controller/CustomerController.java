package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/register")
    public String showRegister() {
        return "site/auth/register";
    }

    @PostMapping("/register")
    public String submitRegister() {
        return "index";
    }

    @GetMapping("/remind-password")
    public String showRemindPassword() {
        return "index";
    }

    @PostMapping("/remind-password")
    public String submitRemindPassword() {
        return "index";
    }

    @GetMapping("/reset-password/{token}")
    public String showResetPassword(@PathVariable String token) {
        return "index";
    }

    @GetMapping("")
    public String showCustomer() {
        return "site/ski/lifts";
    }

    @GetMapping("/data")
    public String showData() {
        return "index";
    }

    @GetMapping("/stats")
    public String showStats() {
        return "index";
    }

    @GetMapping("/tickets}")
    public String showTickets() {
        return "index";
    }

    @GetMapping("/tickets/purchase")
    public String showTicketsPurchase() {
        return "index";
    }

    @GetMapping("/tickets/purchase/success")
    public String showTicketsPurchaseSuccess() {
        return "index";
    }

    @GetMapping("/tickets/purchase/error")
    public String showTicketsPurchaseError() {
        return "index";
    }

    @PostMapping("/reset-password/")
    public String submitResetCustomerPassword() {
        return "index";
    }

    @PostMapping("/api/use-ticket/{purchased_ticket_id}")
    public String purchaseTicket(@PathVariable Long purchased_ticket_id) {
        return "index";
    }

    @PostMapping("/api/payu-verify")
    public String verifyPayU() {
        return "index";
    }

    @PostMapping("/data")
    public String submitData() {
        return "index";
    }

    @PostMapping("/tickets/purchase")
    public String submitTicketsPurchase() { return "index"; }
}
