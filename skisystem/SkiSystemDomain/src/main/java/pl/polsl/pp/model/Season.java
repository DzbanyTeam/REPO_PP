package pl.polsl.pp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa reprezentująca sezon (okres czasu dla którego zdefionowane są ceny biletów)
 */
@Entity
@Table(name="Seasons")
public class Season {

    /**
     * Unikalne id sezonu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa sezonu
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Data początku sezonu
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startDatetime;

    /**
     * Data końca sezonu
     */
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date endDatetime;

    /**
     * Status aktywności sezonu
     */
    @Column(nullable = false)
    private Boolean isActive;

    /**
     * Tworzy nowy obiekt typu Season.
     */
    public Season() {
    }

    /**
     * Tworzy nowy obiekt typu Season z podanymi parametrami.
     * @param name nazwa sezonu
     * @param startDatetime data początku sezonu
     * @param endDatetime data końca sezonu
     * @param isActive czy sezon jest aktywny
     */
    public Season(String name, Date startDatetime, Date endDatetime, Boolean isActive) {
        this.name = name;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.isActive = isActive;
    }

    /**
     * Zwraca id sezonu.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id sezonu.
     * @param id nowe id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę sezonu.
     * @return nazwa
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę sezonu.
     * @param name nowa nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca datę początku sezonu.
     * @return data początku
     */
    public Date getStartDatetime() {
        return startDatetime;
    }

    /**
     * Zwraca datę początku sezonu jako String.
     * @return data początku
     */
    public String getStartDatetimeString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(startDatetime);
    }

    /**
     * Ustawia nową datę początku sezonu.
     * @param startDatetime nowa data początku
     */
    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    /**
     * Zwraca datę końca sezonu.
     * @return data końca
     */
    public Date getEndDatetime() {
        return endDatetime;
    }

    /**
     * Zwraca datę końca sezonu jako String.
     * @return data końca
     */
    public String getEndDatetimeString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(endDatetime);
    }

    /**
     * Ustawia nową datę końca sezonu.
     * @param endDatetime nowa data końca
     */
    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    /**
     * Zwraca status aktywowania sezonu.
     * @return true jeśli sezon jest aktywny, false jeśli nie jest aktywny
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustawia status aktywowania sezonu.
     * @param active true jeśli sezon ma być aktywowany, false jeśli seezon ma być dezaktywowany
     */
    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
