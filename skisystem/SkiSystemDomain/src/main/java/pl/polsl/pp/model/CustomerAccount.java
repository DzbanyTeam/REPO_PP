package pl.polsl.pp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Klasa bazowa reprezentująca konto klienta serwisu
 */
@Entity
@Table(name = "CustomerAccounts")
public class CustomerAccount implements Serializable {

    /**
     * Unikalne Id konta klienta
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "users_sequence")
    long id;

    /**
     * Unikalna nazwa użytkownika konta klienta
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Hasło do konta klienta
     */
    @Column
    private String password;

    /**
     * Status aktywowania konta klienta
     */
    @Column
    private Boolean isActive;

    /**
     * Imię klienta
     */
    @Column
    private String name;

    /**
     * Nazwisko klienta
     */
    @Column
    private String surname;

    /**
     * Unikalny adres e-mail klienta
     */
    @Column
    private String email;

    /**
     * Numer telefonu klienta
     */
    @Column
    private String phoneNumber;

    /**
     * Tworzy nowy obiekt CustomerAccount
     */
    public CustomerAccount() {
    }

    /**
     * Tworzy nowy obiekt CustomerAccount z podanymi parametrami.
     * @param username nazwa użytkownika
     * @param password hasło
     * @param isActive czy konto jest aktywne
     * @param name imię
     * @param surname nazwisko
     * @param email adres e-mail
     * @param phoneNumber numer telefonu
     */
    public CustomerAccount(String username, String password, Boolean isActive, String name, String surname, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Zwraca id konta.
     * @return id konta
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id konta.
     * @param id nowe konta
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę użytkownika konta.
     * @return nazwę użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Ustawia nową nazwę użytkownika konta.
     * @param username nowa nazwa uzytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Zwraca hasło konta.
     * @return hasło użytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Ustawia nowe hasło użytkownika konta.
     * @param password nowe hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Zwraca status aktywowania konta.
     * @return true jeśli konto jest aktywne, false jeśli nie jest aktywne
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustatwia status aktywowania konta.
     * @param active true jeśli konto ma być aktywowane, false jeśli konto ma być dezaktywowane
     */
    public void setIsActive(Boolean active) {
        isActive = active;
    }

    /**
     * Zwraca imię użytkownika konta.
     * @return imię użytkownika
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nowe imię użytkownika konta.
     * @param name nowe imię
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca nazwisko użytkownika konta.
     * @return nazwisko użytkownika
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Ustawia nowe nazwisko użytkownika konta.
     * @param surname nowe nazwisko
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Zwraca adres e-mail użytkownika konta.
     * @return adres e-mail użytkownika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Ustawia nowy adres e-mail użytkownika konta.
     * @param email nowy adres e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Zwraca numer telefonu użytkownika konta.
     * @return numer telefonu użytkownika
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Ustawia nowy numer telefonu użytkownika konta.
     * @param phoneNumber nowy numer telefonu
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Zwraca nową listę zakupionych biletów.
     * @return lista zakupionytch biletów
     */
    public List<PurchasedTicket> getPurchasedTickets() {
        return new LinkedList<>();
    }

}
