package com.studentresults.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject entity — seeded at startup: MATHS, SCIENCE, ENGLISH.
 */
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
    @SequenceGenerator(name = "subject_seq", sequenceName = "subject_seq", allocationSize = 10)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    // ── Relationships ──────────────────────────────────────────────
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mark> marks = new ArrayList<>();

    // ── Constructors ───────────────────────────────────────────────
    protected Subject() {}

    // ── Getters & Setters ──────────────────────────────────────────
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Mark> getMarks() { return marks; }
}
