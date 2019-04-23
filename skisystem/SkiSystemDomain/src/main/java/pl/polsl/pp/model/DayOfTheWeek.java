package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca kategorie dni, wykorzystywana do definiowania godzin otwarcia
 */
@Entity
@Table(name="Days_Of_The_Week")
public class DayOfTheWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    public DayOfTheWeek() {
    }

    public DayOfTheWeek(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
