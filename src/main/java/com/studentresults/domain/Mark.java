package com.studentresults.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Mark entity — links Student ↔ Subject.
 * @Version enables optimistic locking to prevent concurrent overwrites.
 */
@Entity
@Table(name = "marks",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id"}))
@EntityListeners(AuditingEntityListener.class)
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_seq")
    @SequenceGenerator(name = "mark_seq", sequenceName = "mark_seq", allocationSize = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "score", nullable = false)
    private Integer score;  // 0–100

    @Column(name = "exam_date")
    private LocalDate examDate;

    /** Computed grade — derived from score, stored for query convenience. */
    @Column(name = "grade", length = 2)
    private String grade;  // A, B, C, F

    /** Optimistic locking — prevents concurrent mark overwrites. */
    @Version
    @Column(name = "version")
    private Long version;

    // ── Audit fields ───────────────────────────────────────────────
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 100)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    // ── Constructors ───────────────────────────────────────────────
    protected Mark() {}

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public Long getVersion() { return version; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getCreatedBy() { return createdBy; }
    public String getUpdatedBy() { return updatedBy; }
}
