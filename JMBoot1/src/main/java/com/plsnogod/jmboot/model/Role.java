package com.plsnogod.jmboot.model;


import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_role")
    private String nameRoles;

    public Role() {
    }

    public Role(long id, String nameRoles) {
        this.id = id;
        this.nameRoles = nameRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRoles() {
        return nameRoles;
    }

    public void setNameRoles(String nameRoles) {
        this.nameRoles = nameRoles;
    }

    @Override
    public String getAuthority() {
        return getNameRoles();
    }
}