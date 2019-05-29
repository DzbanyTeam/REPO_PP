package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca typ biletu (np. 1-dniowy, 8-godzinny itd.)
 */
@Entity
@Table(name="Ticket_Types")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer numberOfHours;

    @Column // default length is 255
    private String description;

    @Column(nullable = false)
    private Boolean isActive;

    public TicketType() {
    }

    public TicketType(String name, int numberOfHours, String description, boolean isActive) {
        this.name = name;
        this.numberOfHours = numberOfHours;
        this.description = description;
        this.isActive = isActive;
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

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
