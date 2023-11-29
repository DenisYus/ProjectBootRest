package ru.denis.katacourse.ProjectBoot.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role", unique = true)
    private String userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public Role() {
    }

    public Role(String userRole) {
        this.userRole = userRole;
    }
    @Override
    public String getAuthority() {
        return userRole;
    }

    @Override
    public String toString() {
        return userRole.substring(5);
    }
}
