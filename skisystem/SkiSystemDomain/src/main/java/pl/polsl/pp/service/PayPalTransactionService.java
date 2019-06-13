package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.pp.model.PayPalTransaction;
import pl.polsl.pp.repository.PayPalTransactionRepository;
import pl.polsl.pp.service.interfaces.IPayPalTransactionService;

import java.util.List;

public class PayPalTransactionService implements IPayPalTransactionService {


    @Autowired
    PayPalTransactionRepository payPalTransactionRepository;

    @Override
    public boolean saveTransaction(PayPalTransaction payPalTransaction) {
        try{
            payPalTransactionRepository.save(payPalTransaction);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean setTransactionSuccessStatusByPaymentId(String paymentId,Boolean successStatus) {

        List<PayPalTransaction> payPalTransactions = (List<PayPalTransaction>) payPalTransactionRepository.findAll();
        for (PayPalTransaction payPalTransaction : payPalTransactions) {
            if(payPalTransaction.getPaymentId().equals(paymentId)) {
                payPalTransaction.setSuccessStatus(successStatus);
                payPalTransactionRepository.save(payPalTransaction);
                return true;
            }
        }
        return false;
    }

    @Override
    public Long getPurchasedTicketIdByPaymentId(String paymentId) {
        List<PayPalTransaction> payPalTransactions = (List<PayPalTransaction>) payPalTransactionRepository.findAll();
        for (PayPalTransaction payPalTransaction : payPalTransactions) {
            if(payPalTransaction.getPaymentId().equals(paymentId)) {
                return payPalTransaction.getPurchaseTicketId();
            }
        }
        return null;
    }
}
