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
    private BigDecimal priceValue;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    public Price() {
    }

    public Price(TicketType ticketType, TicketCategory ticketCategory, BigDecimal price, Season season) {
        this.ticketType = ticketType;
        this.ticketCategory = ticketCategory;
        this.price = price;
        this.season = season;
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

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String toString() {
        return (ticketType == null ? "" : ticketType.getName())
            + ", "
            + (ticketCategory == null ? "" : ticketCategory.getName())
            + " "
            + (season == null ? "" : season.getName());
    }
}
