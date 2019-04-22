package pl.polsl.pp.model;

import javax.persistence.*;

@Entity
@Table(name="Roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private int id;

    @Column(name="role", nullable = false, unique = true)
    private String role;

    public Role(){
        ;
    }

    public Role(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}