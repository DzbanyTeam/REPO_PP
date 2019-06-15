package pl.polsl.pp.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca stok
 */
@Entity
@Table(name = "Slopes")
public class Slope {

    /**
     * Unikalne id stoku
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa stoku
     */
    @Column(length = 100)
    private String name;

    /**
     * Długość stoku
     */
    @Column
    private Integer length;

    /**
     * Wysokość terenu na początku stoku
     */
    @Column
    private Integer startElevation;

    /**
     * Wysokość terenu na końcu stoku
     */
    @Column
    private Integer endElevation;

    /**
     * Poziom trudności stoku
     */
    @ManyToOne
    @JoinColumn(name="difficulty_id", nullable = false)
    private Difficulty difficulty;

    /**
     * Statis aktywności stoku
     */
    @Column(nullable = false)
    private Boolean isActive;

    /**
     * Lista wyciągów prowadzących na stok
     */
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="Lifts_Slopes",
            joinColumns=@JoinColumn(name="slope_id", referencedColumnName="id", nullable = false),
            inverseJoinColumns=@JoinColumn(name="lift_id", referencedColumnName="id", nullable = false))
    private List<Lift> associatedLifts;

    @OneToMany(mappedBy = "slope", cascade = CascadeType.REMOVE)
    List<SlopeBusinessHours> slopeBusinessHours;

    /**
     * Tworzy nowy obiekt typu Slope.
     */
    public Slope() {
    }

    /**
     * Tworzy nowy obiekt typu Slope z podanymi parametrami.
     * @param name nazwa stoku
     * @param length długość stoku
     * @param startElevation początkowa wysokość terenu
     * @param endElevation wysokość terenu na końcu
     * @param difficulty poziom trudności
     * @param isActive czy stok jest aktywny
     */
    public Slope(String name, Integer length, Integer startElevation, Integer endElevation, Difficulty difficulty, Boolean isActive) {
        this.name = name;
        this.length = length;
        this.startElevation = startElevation;
        this.endElevation = endElevation;
        this.difficulty = difficulty;
        this.isActive = isActive;
    }

    /**
     * Zwraca id stoku.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia nowe id stoku.
     * @param id nowe id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę stoku.
     * @return nazwa stoku
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nową nazwę stoku.
     * @param name nowa nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca długość stoku.
     * @return długość
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Ustwaia nową długość stoku.
     * @param length nowa długość
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Zwraca wysokość terenu na początku stoku.
     * @return wysokość początkowa
     */
    public Integer getStartElevation() {
        return startElevation;
    }

    /**
     * Ustawia nową wysokość terenu na początku stoku.
     * @param startElevation nowa wysokość początkiwa
     */
    public void setStartElevation(Integer startElevation) {
        this.startElevation = startElevation;
    }

    /**
     * Zwraca wysokość terenu na końcu stoku.
     * @return wysokość końcowa
     */
    public Integer getEndElevation() {
        return endElevation;
    }

    /**
     * Ustawia nowa wysokość terenu na końcu stoku.
     * @param endElevation nowa wysokość końcowa
     */
    public void setEndElevation(Integer endElevation) {
        this.endElevation = endElevation;
    }

    /**
     * Zwraca poziom trudności stoku.
     * @return poziom trudności
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Ustawia nowy poziom trudności stoku.
     * @param difficulty nowy poziom trudności
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Zwraca status aktywnośći stoku.
     * @return true jeśli stok jest aktywny, false jeśli nie jest aktywny
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Ustawia status aktywnośći stoku.
     * @param active true jeśli stok ma być aktywowany, false jeśli ma być dezaktywowany
     */
    public void setIsActive(Boolean active) {
        isActive = active;
    }

    /**
     * Zwraca listę wyciągów prowadzących na stok.
     * @return lista wyciągów
     */
    public List<Lift> getAssociatedLifts() {
        return associatedLifts;
    }

    /**
     * Ustawia nową listę wyciągów prowadzących na stok.
     * @param associatedLifts nowa lista wyciągów
     */
    public void setAssociatedLifts(List<Lift> associatedLifts) {
        this.associatedLifts = associatedLifts;
    }

    /**
     * Zwraca listę godzin otwarcia stoku.
     * @return lista godzin otwarcia
     */
    public List<SlopeBusinessHours> getSlopeBusinessHours() {
        return slopeBusinessHours;
    }

    /**
     * Ustawia nową listę godzin otwarcia stoku.
     * @param slopeBusinessHours nowa lista
     */
    public void setSlopeBusinessHours(ArrayList<SlopeBusinessHours> slopeBusinessHours) {
        this.slopeBusinessHours = slopeBusinessHours;
    }
}
