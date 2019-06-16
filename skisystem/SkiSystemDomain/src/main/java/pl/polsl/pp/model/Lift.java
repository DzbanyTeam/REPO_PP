package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca wyciągi
 */
@Entity
@Table(name= "Lifts")
public class Lift {

    /**
     * Unikalne id wyciągu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa wyciągu
     */
    @Column(length = 100)
    private String name;

    /**
     * Długość wyviągu
     */
    @Column
    private Integer length;

    /**
     * Wysokość terenu na początku wyciągu
     */
    @Column
    private Integer startElevation;

    /**
     * Wysokość terenu na końcu wyciągu
     */
    @Column
    private Integer endElevation;

    /**
     * Status aktywności wyciągu
     */
    @Column(nullable = false)
    private Boolean isActive;

    /**
     * Lista stoków, na które prowadzi wyciąg
     */
    @ManyToMany(mappedBy = "associatedLifts", cascade = CascadeType.REMOVE)
    private List<Slope> associatedSlopes;

    /**
     * Lista godzin otwarcia wyciągu
     */
    @OneToMany(mappedBy = "lift", cascade = CascadeType.REMOVE)
    List<LiftBusinessHours> liftBusinessHours;

    /**
     * Tworzy nowy obiekt Lift.
     */
    public Lift() {
    }

    /**
     * Tworzy nowy obiekt Lift z podanymi parametrami.
     * @param name nazwa wyciągu
     * @param length długość wyciągu
     * @param startElevation wysokość terenu na początku wyciągu
     * @param endElevation wysokość terenu na końcu wyciągu
     * @param isActive czy wyciąg jest aktywny
     */
    public Lift(String name, Integer length, Integer startElevation, Integer endElevation, Boolean isActive) {
        this.name = name;
        this.length = length;
        this.startElevation = startElevation;
        this.endElevation = endElevation;
        this.isActive = isActive;
        this.associatedSlopes = associatedSlopes;
    }

    /**
     * Zwraca id wyciągu.
     * @return id wyciągu
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id wyciągu.
     * @param id nowe id wyciągu
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę wyciagu.
     * @return nazwa wyciągu
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę wyciągu.
     * @param name nowa nazwa wyciągu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca długość wyciągu.
     * @return długość wyciągu
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Ustawia nową długość wyciągu.
     * @param length nowa długość wyciągu
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Zwraca wysokość terenu na początku wyciągu.
     * @return wysokość terenu
     */
    public Integer getStartElevation() {
        return startElevation;
    }

    /**
     * Ustawia nową wysokość terenu na początku wyciągu.
     * @param startElevation nowa wysokość terenu
     */
    public void setStartElevation(Integer startElevation) {
        this.startElevation = startElevation;
    }

    /**
     * Zwraca wysokość terenu na końcu wyciągu.
     * @return wysokość terenu
     */
    public Integer getEndElevation() {
        return endElevation;
    }

    /**
     * Ustawia nową wysokość terenu na końcu wyciągu.
     * @param endElevation nowa wysokość terenu
     */
    public void setEndElevation(Integer endElevation) {
        this.endElevation = endElevation;
    }

    /**
     * Zwraca status aktywowania wyciągu.
     * @return true jeśli wyciąg jest aktywny, false jeśli nie jest aktywny
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustawia status aktywowania wyciągu.
     * @param active true jeśli wyciąg ma być aktywowany, false jeśli wyciąg ma być dezaktywowany
     */
    public void setIsActive(Boolean active) {
        isActive = active;
    }

    /**
     * Zwraca listę stoków na które prowadzi wyciąg.
     * @return lista stoków
     */
    public List<Slope> getAssociatedSlopes() {
        return associatedSlopes;
    }

    /**
     * Ustawia nową listę stoków, na które prowadzi wyciąg.
     * @param associatedSlopes nowa lista stoków
     */
    public void setAssociatedSlopes(List<Slope> associatedSlopes) {
        this.associatedSlopes = associatedSlopes;
    }

    /**
     * Zwraca listę godzin otwarcia wyciągu
     * @return lista godzin otwarcia
     */
    public List<LiftBusinessHours> getLiftBusinessHours() {
        return liftBusinessHours;
    }

    /**
     * Ustawia nową listę godzin otwarcia wyciągu
     * @param liftBusinessHours nowa lista godzin otwarcia
     */
    public void setLiftBusinessHours(ArrayList<LiftBusinessHours> liftBusinessHours) {
        this.liftBusinessHours = liftBusinessHours;
    }
}
