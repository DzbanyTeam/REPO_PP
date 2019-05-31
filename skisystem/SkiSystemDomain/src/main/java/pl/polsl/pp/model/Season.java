package pl.polsl.pp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Klasa reprezentująca sezon (okres czasu dla którego zdefionowane są ceny biletów)
 */
@Entity
@Table(name="Seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Date startDatetime;

    @Column
    private Date endDatetime;

    @Column(nullable = false)
    private Boolean isActive;

    public Season() {
    }

    public Season(String name, Date startDatetime, Date endDatetime, Boolean isActive) {
        this.name = name;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
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

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
