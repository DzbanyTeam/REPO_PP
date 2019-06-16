package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca wpis o użyciu biletu
 */
@Entity
@Table(name = "Ticket_Usages")
public class TicketUse {

    /**
     * Id wpisu użycia biletu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użyty bilet
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_id", nullable = false)
    private PurchasedTicket ticket;

    /**
     * Wyciąg, na którym był użyty bilet
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lift_id", nullable = false)
    private Lift lift;

    /**
     * Data użycia biletu
     */
    @Column(nullable = false)
    private Date datetime;

    /**
     * Tworzy nowy obiekt TicketUse.
     */
    public TicketUse() {
    }

    /**
     * Tworzy nowy obiekt TicketUse z podanymi parametrami.
     * @param ticket użyty bilet
     * @param lift wyciąg
     * @param datetime data użycia
     */
    public TicketUse(PurchasedTicket ticket, Lift lift, Date datetime) {
        this.ticket = ticket;
        this.lift = lift;
        this.datetime = datetime;
    }

    /**
     * Zwraca id wpisu o użyciu biletu.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id wpisu o użyciu biletu.
     * @param id nowe id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca użyty we wpisie bilet.
     * @return bilet
     */
    public PurchasedTicket getTicket() {
        return ticket;
    }

    /**
     * Ustawia nowy bilet użyty we wpisie.
     * @param ticket nowy bilet
     */
    public void setTicket(PurchasedTicket ticket) {
        this.ticket = ticket;
    }

    /**
     * Zwraca wyciąg, na którym był użyty bilet.
     * @return wyciąg
     */
    public Lift getLift() {
        return lift;
    }

    /**
     * Ustawia nowy wyciąg, na którym był użyty bilet.
     * @param lift nowy wyciąg
     */
    public void setLift(Lift lift) {
        this.lift = lift;
    }

    /**
     * Zwraca datę użycia biletu.
     * @return data
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Ustawia nową datę użycia biletu.
     * @param datetime nowa data
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
