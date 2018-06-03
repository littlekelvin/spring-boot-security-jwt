package com.kelvin.oocl.springsecurityjwt.model;

import javax.persistence.*;

@Entity
@Table(name = "app_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_user_id")
    private Long userId;
    @Column(name = "app_role")
    private String role;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
