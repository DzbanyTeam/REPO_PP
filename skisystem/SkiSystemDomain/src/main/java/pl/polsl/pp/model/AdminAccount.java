package pl.polsl.pp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Klasa bazowa reprezentująca konto administratora serwisu
 */
@Entity
@Table(name = "AdminAccounts")
@SequenceGenerator(name="users_sequence", allocationSize = 1)
public class AdminAccount implements Serializable {

    /**
     * Unikalne Id konta administratora
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "users_sequence")
    long id;

    /**
     * Unikalna nazwa użytkownika konta administratora
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Hasło do konta administratora
     */
    @Column
    private String password;

    /**
     * Status aktywowania konta administratora
     */
    @Column
    private Boolean isActive;

    /**
     * Imię administratora
     */
    @Column
    private String name;

    /**
     * Nazwisko administartora
     */
    @Column
    private String surname;

    /**
     * Unikalny Adres e-mail administratora
     */
    @Column
    private String email;

    /**
     * Numer telefonu administratora
     */
    @Column
    private String phoneNumber;


    /**
     * Tworzy nowy obiekt AdminAccount z podanymi parametrami.
     * @param username nazwa użytkownika
     * @param password hasło
     * @param isActive czy konto jest aktywne
     * @param name imię
     * @param surname nazwisko
     * @param email adres e-mail
     * @param phoneNumber numer telefonu
     */
    public AdminAccount(String username, String password, boolean isActive, String name, String surname, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Tworzy nowy obiekt AdminAccount.
     */
    public AdminAccount() {
    }

    /**
     * Zwraca id konta.
     * @return id konta
     */
    public Long getId() { return id; }

    /**
     * Ustawia nowe id konta.
     * @param id nowe konta
     */
    public void setId(Long id) { this.id = id; }

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
    public void setUsername(String username) { this.username = username; }

    /**
     * Zwraca hasło konta.
     * @return hasło użytkownika
     */
    public String getPassword() { return password; }

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
    public void setIsActive(boolean active) {
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

}