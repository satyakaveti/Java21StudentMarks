package com.studentresults.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * AuditLog entity — records every create / update / delete action.
 * Written synchronously by AuditAspect via @EventListener.
 */
@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
    @SequenceGenerator(name = "audit_seq", sequenceName = "audit_seq", allocationSize = 50)
    private Long id;

    @Column(name = "entity_name", nullable = false, length = 100)
    private String entityName;

    @Column(name = "entity_id")
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false, length = 20)
    private Action action;

    @Column(name = "performed_by", length = 100)
    private String performedBy;

    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;

    /** JSON snapshot of changed fields (stored as text). */
    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    /** Duration of the operation in milliseconds. */
    @Column(name = "duration_ms")
    private Long durationMs;

    // ── Constructors ───────────────────────────────────────────────
    protected AuditLog() {}

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }
    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
    public LocalDateTime getPerformedAt() { return performedAt; }
    public void setPerformedAt(LocalDateTime performedAt) { this.performedAt = performedAt; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public Long getDurationMs() { return durationMs; }
    public void setDurationMs(Long durationMs) { this.durationMs = durationMs; }

    // ── Action enum ────────────────────────────────────────────────
    public enum Action {
        CREATE, UPDATE, DELETE
    }
}
