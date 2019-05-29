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

    @Column(nullable = false)
    private Boolean isSeason;

    public Price() {
    }

    public Price(TicketType ticketType, TicketCategory ticketCategory, BigDecimal priceValue, Boolean isSeason) {
        this.ticketType = ticketType;
        this.ticketCategory = ticketCategory;
        this.priceValue = priceValue;
        this.isSeason = isSeason;
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


    public Boolean getIsSeason() {
        return isSeason;
    }

    public void setIsSeason(Boolean season) {
        isSeason = season;
    }

    public String toString() {
        return (ticketType == null ? "" : ticketType.getName())
            + ", "
            + (ticketCategory == null ? "" : ticketCategory.getName())
            + ", "
            + (priceValue == null ? "" : priceValue.toString())
            + ", "
            + (isSeason == null ? "" : (isSeason ? "w sezonie" : "poza sezonem"));
    }
}
