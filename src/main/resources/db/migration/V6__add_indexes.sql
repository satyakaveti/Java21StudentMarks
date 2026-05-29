-- ============================================================
-- V6 — Performance indexes
-- ============================================================

-- Marks: fast lookup by student (most common query)
CREATE INDEX idx_marks_student_id  ON marks(student_id);

-- Marks: fast lookup by subject
CREATE INDEX idx_marks_subject_id  ON marks(subject_id);

-- Students: fast lookup by email (login + uniqueness check)
CREATE INDEX idx_students_email    ON students(email);

-- Students: fast lookup by student_code
CREATE INDEX idx_students_code     ON students(student_code);

-- Audit log: fast lookup by entity
CREATE INDEX idx_audit_entity      ON audit_log(entity_name, entity_id);

-- Audit log: fast lookup by performer
CREATE INDEX idx_audit_performed_by ON audit_log(performed_by);

-- Users: fast lookup by username (login)
CREATE INDEX idx_users_username    ON users(username);
