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

    /**
     * Id pozycji
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Typ biletu w pozycji
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_type_id", nullable = false)
    private TicketType ticketType;

    /**
     * Kategoria biletu w pozycji
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_category_id", nullable = false)
    private TicketCategory ticketCategory;

    /**
     * Cena pozycji
     */
    @Column(nullable = false)
    private BigDecimal priceValue;

    /**
     * Sezon, w którym pozycja jest aktywna
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="season_id", nullable = false)
    private Season season;

    /**
     * Tworzy nowy obiekt Price.
     */
    public Price() {
    }

    /**
     * Tworzy nowy obiekt Price z podanymi parametrami.
     * @param ticketType typ biletu
     * @param ticketCategory kategoria biletu
     * @param priceValue cena
     * @param season sezon
     */
    public Price(TicketType ticketType, TicketCategory ticketCategory, BigDecimal priceValue, Season season) {
        this.ticketType = ticketType;
        this.ticketCategory = ticketCategory;
        this.priceValue = priceValue;
        this.season = season;
    }

    /**
     * Zwraca id pozycji.
     * @return id pozycji
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id pozycji.
     * @param id nowe id pozycji
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca typ biletu w pozycji.
     * @return typ biletu
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Ustawia nowy typ biletu w pozycji.
     * @param ticketType typ biletu
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Zwraca kategorię biletu w pozycji.
     * @return kategorię biletu
     */
    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    /**
     * Ustawia nową kategorię biletu w pozycji.
     * @param ticketCategory typ biletu
     */
    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    /**
     * Zwraca cenę.
     * @return cena
     */
    public BigDecimal getPriceValue() {
        return priceValue;
    }

    /**
     * Ustawia nową cenę.
     * @param priceValue nowa cena
     */
    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    /**
     * Zwraca sezon, w którym pozycja jest aktywna.
     * @return sezon
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Ustawia nowy sezon, w którym pozycja jest aktywna.
     * @param season nowy sezon
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Zwraca typ biletu, kategorię biletu i sezon jako String.
     * @return typ biletu, kategoria biletu, (sezon)
     */
    public String getString() {
        return (ticketType == null ? "" : ticketType.getName())
            + ", "
            + (ticketCategory == null ? "" : ticketCategory.getName())
            + " ("
            + (season == null ? "" : season.getName())
            + ")";
    }
}
