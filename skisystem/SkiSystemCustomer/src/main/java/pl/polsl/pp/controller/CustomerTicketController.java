package pl.polsl.pp.controller;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IPayPalTransactionService;
import pl.polsl.pp.service.interfaces.IPriceService;
import pl.polsl.pp.service.interfaces.IPurchasedTicketService;
import pl.polsl.pp.util.PayPalUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer/tickets")
public class CustomerTicketController {

    @Autowired
    private PayPalUtil payPalUtil;

    @Autowired
    @Qualifier("customerAccountServiceInterface")
    private ICustomerAccountService customerAccountService;

    @Autowired
    @Qualifier("purchasedTicketServiceInterface")
    private IPurchasedTicketService purchasedTicketService;

    @Autowired
    @Qualifier("priceServiceInterface")
    private IPriceService priceService;

    @Autowired
    @Qualifier("payPalTransactionServiceInterface")
    IPayPalTransactionService payPalTransactionService;


    public CustomerTicketController() {

    }

    @GetMapping("/history")
    public String showTickets(Model model) {
        Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        List<PurchasedTicket> tickets = purchasedTicketService.getAllPurchasedTicketsByCustomerId(requestCustomerAccountId);
        model.addAttribute("tickets", tickets);
        return "site/customer/history";
    }

    @GetMapping("/purchase/success")
    public String showTicketPurchaseSuccess(Model model, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try{
            payPalUtil.executePayment(paymentId,payerId);
            payPalTransactionService.setTransactionSuccessStatusByPaymentId(paymentId, true);
            Long purchaseTicketId = payPalTransactionService.getPurchasedTicketIdByPaymentId(paymentId);
            if(purchaseTicketId != null){
                List<Long> ids = new ArrayList<>();
                ids.add(purchaseTicketId);
                purchasedTicketService.activatePurchasedTickets(ids);
            }
            Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
            List<PurchasedTicket> tickets = purchasedTicketService.getAllPurchasedTicketsByCustomerId(requestCustomerAccountId);
            model.addAttribute("tickets", tickets);
            model.addAttribute("alertText", "Transakcja została sfinalizowana - bilet został aktywowany.");
            model.addAttribute("alertType", "success");
            return "site/customer/history";
        } catch(PayPalRESTException ex){
            Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
            List<PurchasedTicket> tickets = purchasedTicketService.getAllPurchasedTicketsByCustomerId(requestCustomerAccountId);
            model.addAttribute("tickets", tickets);
            model.addAttribute("alertText", "Zakup biletu nie powiódł się z powodu problemów technicznych serwisu PayPal");
            model.addAttribute("alertType", "danger");
            return "site/customer/history";
        }
    }

    @GetMapping("/purchase/error")
    public String showTicketPurchaseError(Model model) {
        Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        List<PurchasedTicket> tickets = purchasedTicketService.getAllPurchasedTicketsByCustomerId(requestCustomerAccountId);
        model.addAttribute("tickets", tickets);
        model.addAttribute("alertText", "Transakcja nie została sfinalizowana - bilet nie został aktywowany.");
        model.addAttribute("alertType", "danger");
        return "site/customer/history";

    }

    @GetMapping("/purchase")
    public String showTicketsPurchase(Model model) {
        PurchasedTicket ticket = new PurchasedTicket();
        Long requestCustomerAccountId = customerAccountService.getCustomerAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        ticket.setCustomer(customerAccountService.getCustomerAccountById(requestCustomerAccountId));
        model.addAttribute("purchasedTicket", ticket);
        model.addAttribute("prices", priceService.getAllPricesInActiveSeasons());
        return "site/customer/purchase";
    }

    @PostMapping("/purchase")
    public String submitTicketsPurchase(@ModelAttribute("purchasedTicket") PurchasedTicket purchasedTicketRequest, final RedirectAttributes redirectAttributes) {
        purchasedTicketService.savePurchasedTicket(purchasedTicketRequest);
        return "redirect:"+payPalUtil.preparePaymentAndReturnRedirectUrl(purchasedTicketRequest);
    }
}
