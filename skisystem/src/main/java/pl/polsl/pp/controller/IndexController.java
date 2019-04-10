package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public String showIndexPage(){
        return "index";
    }

    @GetMapping("/prices")
    public String showPrices() {
        return "index";
    }

    @GetMapping("/lifts")
    public String showLifts() {
        return "index";
    }

    @GetMapping("/slopes")
    public String showSlopes() {
        return "index";
    }

    @GetMapping("/user/login")
    public String loginCustomer() {
        return "index";
    }

    @PostMapping("/user/login")
    public String submitLoginCustomer() {
        return "index";
    }

    @GetMapping("/user/register")
    public String registerCustomer() {
        return "index";
    }

    @PostMapping("/user/register")
    public String submitRegisterCustomer() {
        return "index";
    }

    @GetMapping("/user/remind-password")
    public String remindCustomerPassword() {
        return "index";
    }

    @PostMapping("/user/remind-password")
    public String submitRemindCustomerPassword() {
        return "index";
    }

    @GetMapping("/user/reset-password/{token}")
    public String resetCustomerPassword(@PathVariable String token) {
        return "index";
    }

    @PostMapping("/user/reset-password/")
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














}
