package pl.polsl.pp.model;

import javax.persistence.*;

/**
 * Klasa reprezentująca rolę nadawaną przez uprawnienie
 */
@Entity
@Table(name="Roles")
public class Role {

    /**
     * Unikalne id roli
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private int id;

    /**
     * Unikalna nazwa roli
     */
    @Column(name="role", nullable = false, unique = true)
    private String role;

    /**
     * Tworzy nowy obiekt Role.
     */
    public Role(){
        ;
    }

    /**
     * Tworzy nowy obiekt Role z podanymi parametrami.
     * @param role nazwa roli
     */
    public Role(String role) {
        this.role = role;
    }

    /**
     * Zwraca id roli.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Ustawia nowe id roli.
     * @param id nowe id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę roli.
     * @return nazwa
     */
    public String getRole() {
        return role;
    }

    /**
     * Ustawia nową nazwę roli.
     * @param role nowa nazwa
     */
    public void setRole(String role) {
        this.role = role;
    }
}