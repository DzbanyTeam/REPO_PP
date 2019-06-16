package pl.polsl.pp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Klasa bazowa reprezentująca godziny otwarcia w zależności od dnia
 */
@MappedSuperclass
public class BusinessHours {

    /**
     * Unikalne id godzin otwarcia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Referencja na dzień tygodnia, od którego zależą godziny otwarcia
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="day_id", nullable = false)
    private DayOfTheWeek dayOfTheWeek;

    /**
     * Godzina otwarcia
     */
    @Column(nullable = false)
    private Time openingHour;

    /**
     * Godzina zamknięcia
     */
    @Column(nullable = false)
    private Time closingHour;

    /**
     * Tworzy nowy obiekt BusinessHours.
     */
    public BusinessHours() {
    }

    /**
     * Tworzy nowy obiekt BusinessHours z podanymi parametrami.
     * @param dayOfTheWeek dzień tygodnia
     * @param openingHour godzina otwarcia
     * @param closingHour godzina zamknięcia
     */
    public BusinessHours(DayOfTheWeek dayOfTheWeek, Time openingHour, Time closingHour) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    /**
     * Zwraca id godzin otwarcia.
     * @return id godzin otwarcia
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id godzin otwarcia.
     * @param id nowe id godzin otwarcia
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca godzinę otwarcia.
     * @return godzina otwarcia
     */
    public Time getOpeningHour() {
        return openingHour;
    }

    /**
     * Zwraca godzinę otwarcia jako String.
     * @return godzina otwarcia
     */
    public String getOpeningHourString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(openingHour);
    }

    /**
     * Ustawia nową godzinę otwarcia.
     * @param openingHour godzina otwarcia
     */
    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    /**
     * Zwraca godzinę zamknięcia.
     * @return godzina zamknięcia
     */
    public Time getClosingHour() {
        return closingHour;
    }

    /**
     * Zwraca godzinę zamknięcia jako String.
     * @return godzina zamknięcia
     */
    public String getClosingHourString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(closingHour);
    }

    /**
     * Ustawia nową godzinę zamknięcia.
     * @param closingHour nowa godzina zamknięcia
     */
    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
    }

    /**
     * Zwraca dzień tygodnia od którego zależą godziny otwarcia.
     * @return dzień tygodnia
     */
    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Ustawia nowy dzień tygodnia, od którego zależą godziny otwarcia.
     * @param dayOfTheWeek nowy dzień tygodnia
     */
    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
