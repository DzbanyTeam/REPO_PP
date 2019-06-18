package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.pp.model.PayPalTransaction;
import pl.polsl.pp.repository.PayPalTransactionRepository;
import pl.polsl.pp.service.interfaces.IPayPalTransactionService;

import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu PayPalTransaction
 */
public class PayPalTransactionService implements IPayPalTransactionService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli pay_pal_transactions
     */
    @Autowired
    PayPalTransactionRepository payPalTransactionRepository;

    /**
     * Dodaje dany obiekt payPalTransaction do bazy danych
     * @param payPalTransaction obiekt Lift do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveTransaction(PayPalTransaction payPalTransaction) {
        try{
            payPalTransactionRepository.save(payPalTransaction);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Ustawia nowy status poprawności wykonania dla transakcji o podanym id transakcji w serwisie PayPal
     * @param paymentId id transakcji w serwisie PayPal
     * @param successStatus nowy status, true jeśli transakcja została poprawnie wykonana, false w przeciwnym wypadku
     * @return status (true lub false) transakcji
     */
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

    /**
     * Zwraca id zakupionego biletu, do którego odnosi się transakcja na podstawie podanego id transakcji w serwisie PayPal
     * @param paymentId id transakcji
     * @return id zakupionego biletu
     */
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
