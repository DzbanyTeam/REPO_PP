package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Klasa bazowa reprezentująca godziny otwarcia w zależności od dnia
 */
@MappedSuperclass
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long dayId;

    @Column(nullable = false)
    private Time openingHour;

    @Column(nullable = false)
    private Time closingHour;

    public BusinessHours() {
    }

    public BusinessHours(Long dayId, Time openingHour, Time closingHour) {
        this.dayId = dayId;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    public Time getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Time closingHour) {
        this.closingHour = closingHour;
    }

    public DayOfTheWeek getDayOfTheWeek()
    {
        return new DayOfTheWeek(); // TODO implement
    }
}
