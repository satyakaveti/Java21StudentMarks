-- ============================================================
-- V2 — Students table (soft-deletable)
-- ============================================================

CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE students (
    id             BIGINT       PRIMARY KEY DEFAULT nextval('student_seq'),
    student_code   VARCHAR(20)  NOT NULL UNIQUE,
    first_name     VARCHAR(100) NOT NULL,
    last_name      VARCHAR(100) NOT NULL,
    email          VARCHAR(200) NOT NULL UNIQUE,
    date_of_birth  DATE,
    deleted        BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    created_by     VARCHAR(100),
    updated_by     VARCHAR(100)
);

-- Link users.student_id → students
ALTER TABLE users ADD COLUMN student_id BIGINT UNIQUE REFERENCES students(id);
