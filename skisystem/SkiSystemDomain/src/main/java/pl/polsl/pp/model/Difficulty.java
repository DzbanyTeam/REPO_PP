package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca poziomy trudności tras (stoków)
 */
@Entity
@Table(name = "Difficulties")
public class Difficulty {

    /**
     * Unikalne id poziomu trudności
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa poziomu trudności
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * Tworzy nowy obiekt Difficulty.
     */
    public Difficulty() {
    }

    /**
     * Tworzy nowy obiekt Difficulty z podanymi parametrami.
     * @param name nazwa poziomu trudności
     */
    public Difficulty(String name) {
        this.name = name;
    }

    /**
     * Zwraca id poziomu trudności trasy.
     * @return id poziomu trudności
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id poziomu trudności trasy.
     * @param id nowe id poziomu trudniści
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę poziomu trudności trasy.
     * @return nazwa poziomu trudności
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę poziomu trudnośći trasy.
     * @param name nowa nazwa poziomu trudności
     */
    public void setName(String name) {
        this.name = name;
    }
}
