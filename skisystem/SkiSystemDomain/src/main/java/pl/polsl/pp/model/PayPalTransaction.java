package pl.polsl.pp.model;

import javax.persistence.*;

@Entity
@Table(name = "PayPalTransactions")
public class PayPalTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false, unique = true)
    private String paymentId;

    @OneToOne
    @JoinColumn(name = "purchased_ticket_id", referencedColumnName = "id", nullable = false)
    private PurchasedTicket purchasedTicket;

    @Column(nullable = false)
    private Boolean successStatus;

    public PayPalTransaction(){

    }


    public PayPalTransaction(String paymentId, PurchasedTicket purchasedTicket) {
        this.paymentId = paymentId;
        this.purchasedTicket = purchasedTicket;
        this.successStatus = false;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPurchaseTicketId() {
        return purchasedTicket.getId();
    }

    public PurchasedTicket getPurchasedTicket() {
        return purchasedTicket;
    }

    public void setPurchasedTicket(PurchasedTicket purchasedTicket) {
        this.purchasedTicket = purchasedTicket;
    }

    public Boolean getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }
}
