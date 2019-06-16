package pl.polsl.pp.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Klasa reprezentująca godziny otwarcia stoku w zależności od dnia
 */
@Entity
@Table(name="Slopes_Business_Hours")
public class SlopeBusinessHours extends BusinessHours {

    /**
     * Stok, do którego odnoszą się godziny otwarcia
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="slope_id", nullable = false)
    private Slope slope;

    /**
     * Tworzy nowy obiekt typu SlopeBusinessHours.
     */
    public SlopeBusinessHours() {
    }

    /**
     * Tworzy nowy obiekt typu SlopeBusinessHours z podanymi parametrami.
     * @param dayOfTheWeek dzień tygodnia
     * @param openingHour godzina otwarcia
     * @param closingHour godzina zamknięcia
     * @param slope stok
     */
    public SlopeBusinessHours(DayOfTheWeek dayOfTheWeek, Time openingHour, Time closingHour, Slope slope) {
        super(dayOfTheWeek, openingHour, closingHour);
        this.slope = slope;
    }

    /**
     * Zwraca stok, do którego odnoszą się godziny otwarcia.
     * @return stok
     */
    public Slope getSlope() {
        return slope;
    }

    /**
     * Ustawia nowy, do którego odnoszą się godziny otwarcia.
     * @param slope nowy stok
     */
    public void setSlope(Slope slope) {
        this.slope = slope;
    }
}
