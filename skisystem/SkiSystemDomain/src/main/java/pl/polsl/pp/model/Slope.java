package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca stok
 */
@Entity
@Table(name = "Slopes")
public class Slope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column
    private Integer length;

    @Column
    private Integer startElevation;

    @Column
    private Integer endElevation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="difficulty_id", nullable = false)
    private Difficulty difficulty;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name="Lifts_Slopes",
            joinColumns=@JoinColumn(name="slope_id", referencedColumnName="id", nullable = false),
            inverseJoinColumns=@JoinColumn(name="lift_id", referencedColumnName="id", nullable = false))
    private List<Lift> associatedLifts;

    public Slope() {
    }

    public Slope(String name, Integer length, Integer startElevation, Integer endElevation, Difficulty difficulty, Boolean isActive) {
        this.name = name;
        this.length = length;
        this.startElevation = startElevation;
        this.endElevation = endElevation;
        this.difficulty = difficulty;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStartElevation() {
        return startElevation;
    }

    public void setStartElevation(Integer startElevation) {
        this.startElevation = startElevation;
    }

    public Integer getEndElevation() {
        return endElevation;
    }

    public void setEndElevation(Integer endElevation) {
        this.endElevation = endElevation;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<Lift> getAssociatedLifts() {
        return associatedLifts;
    }

    public void setAssociatedLifts(List<Lift> associatedLifts) {
        this.associatedLifts = associatedLifts;
    }

    public List<SlopeBusinessHours> getSlopeBusinessHours() {
        return new ArrayList<>(); // TODO Implement
    }
}
