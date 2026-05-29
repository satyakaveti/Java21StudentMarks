-- ============================================================
-- V5 — Audit log table
-- Records every create / update / delete action
-- ============================================================

CREATE SEQUENCE audit_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE audit_log (
    id            BIGINT       PRIMARY KEY DEFAULT nextval('audit_seq'),
    entity_name   VARCHAR(100) NOT NULL,
    entity_id     BIGINT,
    action        VARCHAR(20)  NOT NULL CHECK (action IN ('CREATE', 'UPDATE', 'DELETE')),
    performed_by  VARCHAR(100),
    performed_at  TIMESTAMP    NOT NULL,
    details       TEXT,
    duration_ms   BIGINT
);
