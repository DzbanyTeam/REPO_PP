package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca kategorie dni, wykorzystywana do definiowania godzin otwarcia
 */
@Entity
@Table(name="Days_Of_The_Week")
public class DayOfTheWeek {

    /**
     * Unikalne Id dnia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa dnia
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * Tworzy nowy obiekt DayOfTheWeek.
     */
    public DayOfTheWeek() {
    }

    /**
     * Tworzy nowy obiekt DayOfTheWeek z podanymi parametrami.
     * @param name nazwa dnia
     */
    public DayOfTheWeek(String name) {
        this.name = name;
    }

    /**
     * Zwraca id dnia.
     * @return id dnia
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id dnia.
     * @param id nowe id dnia
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę dnia.
     * @return nazwa dnia
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę dnia.
     * @param name nowa nazwa dnia
     */
    public void setName(String name) {
        this.name = name;
    }
}
