package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Klasa reprezentująca godziny otwarcia wyciągu w zależności od dnia
 */
@Entity
@Table(name="Lifts_Business_Hours")
public class LiftBusinessHours extends BusinessHours {

    /**
     * Wyciąg, do którego odnoszą się godziny otwarcia
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lift_id",nullable = false)
    private Lift lift;

    /**
     * Tworzy nowy obiekt LiftBusinessHours.
     */
    public LiftBusinessHours() {
    }

    /**
     * Tworzy nowy obiekt LiftBusinessHours z podanymi parametrami.
     * @param dayOfTheWeek dzień tygodnia
     * @param openingHour godzina otwarcia
     * @param closingHour godzina zamknięcia
     * @param lift wyciąg
     */
    public LiftBusinessHours(DayOfTheWeek dayOfTheWeek, Time openingHour, Time closingHour, Lift lift) {
        super(dayOfTheWeek, openingHour, closingHour);
        this.lift = lift;
    }

    /**
     * Zwraca wyciąg, do którego odnoszą się godziny otwarcia.
     * @return wyciąg
     */
    public Lift getLift() {
        return lift;
    }

    /**
     * Ustawia nowy wyciąg, do którego odnoszą się godziny otwarcia
     * @param lift nowy wyciąg
     */
    public void setLift(Lift lift) {
        this.lift = lift;
    }
}
