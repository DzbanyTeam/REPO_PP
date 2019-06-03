package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.validator.CustomerAccountValidator;

import java.util.Collection;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    @Qualifier("customerAccountService")
    private ICustomerAccountService customerAccountService;

    @Autowired
    private CustomerAccountValidator customerAccountValidator;

    @InitBinder
    protected void initBinderCustomer(WebDataBinder binder) {
        binder.addValidators(customerAccountValidator);
    }

    public CustomerController() {

    }

    @GetMapping("/register")
    public String showRegister() {
        return "site/auth/register";
    }

    @PostMapping("/register")
    public String submitRegister(@ModelAttribute("customerAccount") @Validated CustomerAccount customerAccountRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) { //tutaj odbywa sie rowniez walidacja czy taki username istnieje
            model.addAttribute("alertText", "Nie można zapisać klienta. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "site/auth/register";
        }

        if (customerAccountRequest.getIsActive() != null) { //sprawdzenie czy uzytkownik wyrazil zgode na przetwarzanie danych osobowych
            customerAccountRequest.setIsActive(false);
            customerAccountService.saveCustomerAccount(customerAccountRequest);
            redirectAttributes.addFlashAttribute("alertText", "Zapisano klienta.");
            redirectAttributes.addFlashAttribute("alertType", "success");
        } else {
            redirectAttributes.addFlashAttribute("alertText", "Zaakceptuj zgodę na przetwarzanie danych osobowych.");
            redirectAttributes.addFlashAttribute("alertType", "danger");
        }

        return "redirect:/customer/register";
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
    public String showCustomer(Model model) {

        Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        CustomerAccount customerAccount = customerAccountService.getCustomerAccountById(requestCustomerAccountId);
        model.addAttribute("customerAccount", customerAccount);
        return "site/customer/panel";
    }

    @GetMapping("/data")
    public String showData(Model model) {
        Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        CustomerAccount customerAccount = customerAccountService.getCustomerAccountById(requestCustomerAccountId);
        model.addAttribute("customerAccount", customerAccount);
        return "site/customer/edit";
    }

    @GetMapping("/stats")
    public String showStats() {
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
    public String submitData(@ModelAttribute("customerAccount") @Validated CustomerAccount customerAccountRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alertText", "Nie można zapisać klienta. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "site/customer/edit";
        }
        customerAccountService.saveCustomerAccount(customerAccountRequest);
        redirectAttributes.addFlashAttribute("alertText", "Zmieniono dane.");
        redirectAttributes.addFlashAttribute("alertType", "success");
        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customerAccountRequest.getUsername(), customerAccountRequest.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/customer/data";
    }

}
