package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Klasa reprezentująca godziny otwarcia wyciągu w zależności od dnia
 */
@Entity
@Table(name="Lifts_Business_Hours")
public class LiftsBusinessHours extends BusinessHours {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lift_id",nullable = false)
    private Lift lift;

    public LiftsBusinessHours(Long dayId, Time openingHour, Time closingHour, Lift lift) {
        super(dayId, openingHour, closingHour);
        this.lift = lift;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }
}
