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

    @Column
    private Long purchaseTicketId;

    @Column
    private Boolean successStatus;

    public PayPalTransaction(){

    }


    public PayPalTransaction(String paymentId, Long purchaseTicketId) {
        this.paymentId = paymentId;
        this.purchaseTicketId = purchaseTicketId;
        this.successStatus = false;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPurchaseTicketId() {
        return purchaseTicketId;
    }

    public void setPurchaseTicketId(Long purchaseTicketId) {
        this.purchaseTicketId = purchaseTicketId;
    }

    public Boolean getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }
}
