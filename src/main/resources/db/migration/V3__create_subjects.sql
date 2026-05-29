-- ============================================================
-- V3 — Subjects table + seed rows
-- ============================================================

CREATE SEQUENCE subject_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE subjects (
    id          BIGINT       PRIMARY KEY DEFAULT nextval('subject_seq'),
    name        VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- Seed the 3 fixed subjects used throughout the application
INSERT INTO subjects (name, description) VALUES
    ('MATHS',   'Mathematics — algebra, geometry, calculus'),
    ('SCIENCE',  'Science — physics, chemistry, biology'),
    ('ENGLISH',  'English language and literature');
