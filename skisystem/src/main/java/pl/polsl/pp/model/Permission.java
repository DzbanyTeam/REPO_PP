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
    private long userId;

    @Column(nullable = false)
    private long roleId;

    public Permission() {
    }

    public Permission(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
