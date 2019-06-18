package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca transakcje z wykorzystaniem PayPala
 */
@Entity
@Table(name = "PayPalTransactions")
public class PayPalTransaction {

    /**
     * Unikalne id transakcji w bazie danych
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    /**
     * Unikalne id transakcji w serwisie PayPal
     */
    @Column(nullable = false, unique = true)
    private String paymentId;

    /**
     * Id kupionego biletu, do którego odnosi się transakcja
     */
    @Column
    private Long purchaseTicketId;

    /**
     * Status poprawności zakończenia transakcji
     */
    @Column
    private Boolean successStatus;

    /**
     * Tworzy nowy obiekt PayPalTransaction.
     */
    public PayPalTransaction(){

    }

    /**
     * Tworzy nowy obiekt PayPalTransaction z podanymi parametrami.
     * @param paymentId Id transakcji w serwisie PayPal
     * @param purchaseTicketId Id zakupionego biletu
     */
    public PayPalTransaction(String paymentId, Long purchaseTicketId) {
        this.paymentId = paymentId;
        this.purchaseTicketId = purchaseTicketId;
        this.successStatus = false;
    }

    /**
     * Zwraca id transakcji w serwisie PayPal.
     * @return id transakcji
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Ustawia nowe id transakcji w serwisie PayPal.
     * @param paymentId nowe id transakcji
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Zwraca id zakupionego biletu, do którego odnosi się transakcja.
     * @return id biletu
     */
    public Long getPurchaseTicketId() {
        return purchaseTicketId;
    }

    /**
     * Ustawia nowe id zakupionego biletu, do którego odnosi się transakcja.
     * @param purchaseTicketId
     */
    public void setPurchaseTicketId(Long purchaseTicketId) {
        this.purchaseTicketId = purchaseTicketId;
    }

    /**
     * Zwraca status poprawności zakończenia transakcji.
     * @return true, jeśli transakcja zakończyła się sukcesem, false jeśli nie zakończyła się sukcesem
     */
    public Boolean getSuccessStatus() {
        return successStatus;
    }

    /**
     * Ustawia nowy status poprawności zakończenia transakcji
     * @param successStatus nowy status
     */
    public void setSuccessStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }
}
