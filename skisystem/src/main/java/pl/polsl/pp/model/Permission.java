package pl.polsl.pp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Permissions")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false,unique = true)
    private long user_id;

    @Column(nullable = false)
    private long role_id;

    public Permission() {
    }

    public Permission(long user_id, long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
