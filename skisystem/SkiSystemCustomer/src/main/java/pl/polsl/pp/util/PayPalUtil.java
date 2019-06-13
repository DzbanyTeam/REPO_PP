package pl.polsl.pp.util;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import pl.polsl.pp.model.PayPalTransaction;
import pl.polsl.pp.model.PurchasedTicket;
import pl.polsl.pp.service.interfaces.IPayPalTransactionService;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayPalUtil {

    private static String payPalId = "ID";
    private static String payPalSecret = "SECRET";
    private static String executionMode = "sandbox";

    @Autowired
    @Qualifier("payPalTransactionServiceInterface")
    IPayPalTransactionService payPalTransactionService;

    public String preparePaymentAndReturnRedirectUrl(PurchasedTicket purchasedTicket) {

        String ticketName = purchasedTicket.getPrice().getString();
        String ticketCost = purchasedTicket.getPrice().getPriceValue().toString();


        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/customer/tickets/purchase/error");
        redirectUrls.setReturnUrl("http://localhost:8080/customer/tickets/purchase/success");

        // Payment amount
        Amount amount = new Amount();
        amount.setCurrency("PLN");
        /**
         * TODO NIE WIDAC CENY
         */
        amount.setTotal(ticketCost);

        // Transaction information
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Przedmiot: "+ticketName+"\n Cena: "+ticketCost);

        // Add transaction to a list
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Add payment details
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);

        // Pass the clientID, secret and mode. The easiest, and most widely used option.
        APIContext apiContext = new APIContext(payPalId, payPalSecret, executionMode);

        // Create payment
        try {
            Payment createdPayment = payment.create(apiContext);

            List<Links> links = createdPayment.getLinks();
            for (Links link : links) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    payPalTransactionService.saveTransaction(new PayPalTransaction(createdPayment.getId(),purchasedTicket.getId()));
                    return link.getHref();
                }
            }
            return "/customer/tickets/purchase/error";
        } catch (PayPalRESTException e) {
            return "/customer/tickets/purchase/error";
        }
    }

    public void executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        APIContext apiContext = new APIContext(payPalId, payPalSecret, executionMode);

        Payment createdPayment = payment.execute(apiContext, paymentExecution);
    }
}