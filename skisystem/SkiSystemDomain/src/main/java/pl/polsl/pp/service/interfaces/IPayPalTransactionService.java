package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.PayPalTransaction;

public interface IPayPalTransactionService {

    boolean saveTransaction(PayPalTransaction payPalTransaction);
    boolean setTransactionSuccessStatusByPaymentId(String paymentId, Boolean successStatus);
    Long getPurchasedTicketIdByPaymentId(String paymentId);

}
