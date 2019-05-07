package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa modelu reprezentująca kategorie biletów (np. normalny, ulgowy itd.)
 */
@Entity
@Table(name="Ticket_Categories")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column //default length is 255
    private String description;

    public TicketCategory() {
    }

    public TicketCategory(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
