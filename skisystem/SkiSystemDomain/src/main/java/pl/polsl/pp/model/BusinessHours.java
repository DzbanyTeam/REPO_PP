package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Klasa bazowa reprezentująca godziny otwarcia w zależności od dnia
 */
@MappedSuperclass
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="day_id", nullable = false)
    private DayOfTheWeek dayOfTheWeek;

    @Column(nullable = false)
    private Time openingHour;

    @Column(nullable = false)
    private Time closingHour;

    public BusinessHours() {
    }

    public BusinessHours(DayOfTheWeek dayOfTheWeek, Time openingHour, Time closingHour) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public String getOpeningHourString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(openingHour);
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    public Time getClosingHour() {
        return closingHour;
    }

    public String getClosingHourString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(closingHour);
    }

    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
