package pl.polsl.pp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Klasa reprezentująca pozycję cennika
 */
@Entity
@Table(name = "Prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_category_id", nullable = false)
    private TicketCategory ticketCategory;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String startDatetime;

    @Column
    private String endDatetime;

    public Price() {
    }

    public Price(TicketType ticketType, TicketCategory ticketCategory, BigDecimal price, String startDatetime, String endDatetime) {
        this.ticketType = ticketType;
        this.ticketCategory = ticketCategory;
        this.price = price;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String toString() {
        return (ticketType == null ? "" : ticketType.getName())
            + " "
            + (ticketCategory == null ? "" : ticketCategory.getName())
            + " (od "
            + startDatetime
            + " do "
            + endDatetime
            + ")";
    }
}
