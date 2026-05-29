package com.studentresults.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity — holds login credentials.
 * One-to-one with Student (a student has exactly one user account).
 * Roles stored in user_roles join table.
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 50)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "oauth2_provider", length = 50)
    private String oauth2Provider;   // "google", null for local auth

    @Column(name = "oauth2_subject", length = 255)
    private String oauth2Subject;    // OAuth2 subject ID

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    // ── Relationships ──────────────────────────────────────────────
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // ── Audit fields ───────────────────────────────────────────────
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Constructors ───────────────────────────────────────────────
    protected User() {}

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getOauth2Provider() { return oauth2Provider; }
    public void setOauth2Provider(String oauth2Provider) { this.oauth2Provider = oauth2Provider; }
    public String getOauth2Subject() { return oauth2Subject; }
    public void setOauth2Subject(String oauth2Subject) { this.oauth2Subject = oauth2Subject; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
