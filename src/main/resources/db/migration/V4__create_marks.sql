-- ============================================================
-- V4 — Marks table
-- FK → students + subjects
-- Optimistic locking via version column
-- Unique constraint: one mark per student per subject
-- ============================================================

CREATE SEQUENCE mark_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE marks (
    id          BIGINT   PRIMARY KEY DEFAULT nextval('mark_seq'),
    student_id  BIGINT   NOT NULL REFERENCES students(id) ON DELETE CASCADE,
    subject_id  BIGINT   NOT NULL REFERENCES subjects(id),
    score       INTEGER  NOT NULL CHECK (score >= 0 AND score <= 100),
    grade       VARCHAR(2),
    exam_date   DATE     NOT NULL,
    version     BIGINT   NOT NULL DEFAULT 0,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    created_by  VARCHAR(100),
    updated_by  VARCHAR(100),
    CONSTRAINT uq_student_subject UNIQUE (student_id, subject_id)
);
