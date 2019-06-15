package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca typ biletu (np. 1-dniowy, 8-godzinny itd.)
 */
@Entity
@Table(name="Ticket_Types")
public class TicketType {

    /**
     * Unikalne id typu biletu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa typu biletu
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Czas trwania typu biletu
     */
    @Column(nullable = false)
    private Integer numberOfHours;

    /**
     * Opis typu biletu
     */
    @Column // default length is 255
    private String description;

    /**
     * Status aktywności typu biletu
     */
    @Column(nullable = false)
    private Boolean isActive;

    /**
     * Tworzy nowy obiekt TicketType.
     */
    public TicketType() {
    }

    /**
     * Tworzy nowy obiekt TicketType z podanymi parametrami.
     * @param name nazwa
     * @param numberOfHours czast trwania
     * @param description opis
     * @param isActive czy jest aktywny
     */
    public TicketType(String name, int numberOfHours, String description, boolean isActive) {
        this.name = name;
        this.numberOfHours = numberOfHours;
        this.description = description;
        this.isActive = isActive;
    }

    /**
     * Zwraca id typu biletu.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id typu biletu.
     * @param id nowe id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę typu biletu.
     * @return nazwa
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę typu biletu.
     * @param name nowa nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca czas trwania typu biletu.
     * @return czas trwania
     */
    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    /**
     * Ustawia nowy czas trwania typu biletu.
     * @param numberOfHours nowy czas trwania
     */
    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    /**
     * Zwraca opis typu biletu.
     * @return opis
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia nowy opis typu biletu
     * @param description nowy opis
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Zwraca status aktywności typu biletu.
     * @return true jeśli jest aktywny, false jeśli nie jest aktywny
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustawia status aktywności typu biletu.
     * @param active true jeśli typ biletu ma być aktywowany, false jeśli ma być dezaktywowany
     */
    public void setIsActive(boolean active) {
        isActive = active;
    }
}
