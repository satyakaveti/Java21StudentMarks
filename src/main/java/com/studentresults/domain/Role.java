package com.studentresults.domain;

import jakarta.persistence.*;

/**
 * Role entity — values: ROLE_STUDENT, ROLE_ADMIN.
 * Stored in roles table; linked to User via user_roles join table.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private RoleName name;

    // ── Constructors ───────────────────────────────────────────────
    protected Role() {}

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public RoleName getName() { return name; }
    public void setName(RoleName name) { this.name = name; }

    // ── Enum ───────────────────────────────────────────────────────
    public enum RoleName {
        ROLE_STUDENT,
        ROLE_ADMIN
    }
}
