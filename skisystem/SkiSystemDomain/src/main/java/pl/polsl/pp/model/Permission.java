package pl.polsl.pp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Klasa reprezentująca uprawnienia użytkownika
 */
@Entity
@Table(name = "Permissions")
public class Permission implements Serializable {

    /**
     * Unikalne id uprawnienia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    /**
     * Id użytkownika, któremu nadane jest uprawnienie
     */
    @Column(nullable = false,unique = true)
    private long userId;

    /**
     * Id roli, którą nadaje uprawnienie
     */
    @Column(nullable = false)
    private long roleId;

    /**
     * Tworzy nowy obiekt Permission.
     */
    public Permission() {
    }

    /**
     * Tworzy nowy obiekt Permission z podanymi parametrami.
     * @param userId id użytkownika
     * @param roleId id roli
     */
    public Permission(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * Zwraca id uprawnienia.
     * @return id uprawnienia
     */
    public long getId() {
        return id;
    }

    /**
     * Zwraca id użytkownika z nadanym uprawnieniem.
     * @return id użytkownika
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Ustawia nowe id użytkownika z nadanym uprawnieniem.
     * @param userId nowe id użytkownika
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Zwraca id roli, którą nadaje uprawnienie.
     * @return id roli
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Ustawia nowe id roli, którą nadaje uprawnienie.
     * @param roleId nowe id roli
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
