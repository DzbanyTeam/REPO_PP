package pl.polsl.pp.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa reprezentująca zakupiony bilet
 */
@Entity
@Table(name = "Purchased_Tickets")
public class PurchasedTicket {

    /**
     * Unikalne id zakupionego biletu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Klient, który zakupił bilet
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    private CustomerAccount customer;

    /**
     * Pozycja biletu w cenniku
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="price_id", nullable = false)
    private Price price;

    /**
     * Data zakupu biletu
     */
    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date purchaseDatetime;

    /**
     * Data utraty ważności biletu
     */
    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date expirationDatetime;

    /**
     * Status aktywnośći biletu
     */
    @Column(nullable = false)
    boolean isActive;

    /**
     * Zwraca obiekt typu PurchasedTicket.
     */
    public PurchasedTicket() {
    }

    /**
     * Zwraca obiekt typu PurchasedTicket z podanymi parametrami.
     * @param customer klient
     * @param price pozycja
     * @param purchaseDatetime data zakupu
     * @param expirationDatetime data utraty ważności
     * @param isActive czy bilet jest aktywny
     */
    public PurchasedTicket(CustomerAccount customer, Price price, Date purchaseDatetime, Date expirationDatetime, boolean isActive) {
        this.customer = customer;
        this.price = price;
        this.purchaseDatetime = purchaseDatetime;
        this.expirationDatetime = expirationDatetime;
        this.isActive = isActive;
    }

    /**
     * Zwraca id biletu.
     * @return id biletu
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id biletu.
     * @param id nowe id biletu
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca klienta, który zakupił bilet.
     * @return klient
     */
    public CustomerAccount getCustomer() {
        return customer;
    }

    /**
     * Ustawia nowego klienta, który zakupił bilet.
     * @param customer nowy klient
     */
    public void setCustomer(CustomerAccount customer) {
        this.customer = customer;
    }

    /**
     * Zwraca pozycję biletu w cenniku.
     * @return pozycja
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Ustawia nową pozycję biletu w cenniku.
     * @param price nowa pozycja
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * Zwraca datę zakupu biletu.
     * @return data zakupu
     */
    public Date getPurchaseDatetime() {
        return purchaseDatetime;
    }

    /**
     * Zwraca datę zapkupu biletu jako String.
     * @return data zakupu
     */
    public String getPurchaseDatetimeString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(purchaseDatetime);
    }

    /**
     * Ustawia nową datę zakupu biletu.
     * @param purchaseDatetime nowa data zakupu
     */
    public void setPurchaseDatetime(Date purchaseDatetime) {
        this.purchaseDatetime = purchaseDatetime;
    }

    /**
     * Zwraca datę utraty ważności biletu.
     * @return data utraty ważności
     */
    public Date getExpirationDatetime() {
        return expirationDatetime;
    }

    /**
     * Zwraca datę utraty ważności biletu jako String.
     * @return data utraty ważności
     */
    public String getExpirationDatetimeString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(expirationDatetime);
    }

    /**
     * Ustawia nową datę utraty ważności biletu.
     * @param expirationDatetime nowa data utraty ważności
     */
    public void setExpirationDatetime(Date expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }

    /**
     * Zwraca status aktywności biletu.
     * @return true jeśli bilet jest aktywny, false jeśli bilet nie jest aktywny
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustawia status aktywności biletu.
     * @param active true jeśli bilet ma być aktywowany, false jeśli bilet ma być dezaktywowany
     */
    public void setIsActive(boolean active) {
        isActive = active;
    }
}
