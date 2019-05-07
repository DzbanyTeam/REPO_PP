package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca wpis o użyciu biletu
 */
@Entity
@Table(name = "Ticket_Usages")
public class TicketUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_id", nullable = false)
    private PurchasedTicket ticket;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lift_id", nullable = false)
    private Lift lift;

    @Column(nullable = false)
    private Date datetime;

    public TicketUse() {
    }

    public TicketUse(PurchasedTicket ticket, Lift lift, Date datetime) {
        this.ticket = ticket;
        this.lift = lift;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchasedTicket getTicket() {
        return ticket;
    }

    public void setTicket(PurchasedTicket ticket) {
        this.ticket = ticket;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
