package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa modelu reprezentująca kategorie biletów (np. normalny, ulgowy itd.)
 */
@Entity
@Table(name="Ticket_Categories")
public class TicketCategory {

    /**
     * Unikalne id kategorii biletu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa kategorii biletu
     */
    @Column(length = 100,nullable = false)
    private String name;

    /**
     * Opis kategorii biletu
     */
    @Column //default length is 255
    private String description;

    /**
     * Tworzy nowy obiekt typu TicketCategory.
     */
    public TicketCategory() {
    }

    /**
     * Tworzy nowy obiekt typu TicketCategory z podanymi parametrami.
     * @param name nazwa
     * @param description opis
     */
    public TicketCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Zwraca id kategorii biletu.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id kategorii biletu.
     * @param id nowe id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę kategorii biletu.
     * @return nazwa
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę kategorii biletu.
     * @param name nowa nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca opis kategori biletu.
     * @return opis
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia nowy opis kategori biletu.
     * @param description nowy opis
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
