package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Klasa reprezentująca wyciągi
 */
@Entity
@Table(name= "Lifts")
public class Lift {

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

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToMany(mappedBy = "associatedLifts")
    private List<Slope> associatedSlopes;

    public Lift() {
    }

    public Lift(String name, Integer length, Integer startElevation, Integer endElevation, Boolean isActive) {
        this.name = name;
        this.length = length;
        this.startElevation = startElevation;
        this.endElevation = endElevation;
        this.isActive = isActive;
        this.associatedSlopes = associatedSlopes;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<Slope> getAssociatedSlopes() {
        return associatedSlopes;
    }

    public void setAssociatedSlopes(List<Slope> associatedSlopes) {
        this.associatedSlopes = associatedSlopes;
    }
}
