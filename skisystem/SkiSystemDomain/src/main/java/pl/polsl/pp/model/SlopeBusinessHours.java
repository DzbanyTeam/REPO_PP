package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Klasa reprezentująca godziny otwarcia stoku w zależności od dnia
 */
@Entity
@Table(name="Slopes_Business_Hours")
public class SlopeBusinessHours extends BusinessHours {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="slope_id", nullable = false)
    private Slope slope;

    public SlopeBusinessHours() {
    }

    public SlopeBusinessHours(DayOfTheWeek dayOfTheWeek, Time openingHour, Time closingHour, Slope slope) {
        super(dayOfTheWeek, openingHour, closingHour);
        this.slope = slope;
    }

    public Slope getSlope() {
        return slope;
    }

    public void setSlope(Slope slope) {
        this.slope = slope;
    }
}
