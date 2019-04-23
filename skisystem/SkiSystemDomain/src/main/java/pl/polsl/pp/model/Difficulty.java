package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca poziomy trudności tras (stoków)
 */
@Entity
@Table(name = "Difficulties")
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    public Difficulty(String name) {
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
