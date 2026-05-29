-- ============================================================
-- V1 — Users, Roles, User-Roles join table
-- ============================================================

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE roles (
    id   BIGINT       PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(30)  NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES ('ROLE_STUDENT'), ('ROLE_ADMIN');

CREATE TABLE users (
    id              BIGINT        PRIMARY KEY DEFAULT nextval('user_seq'),
    username        VARCHAR(100)  NOT NULL UNIQUE,
    password_hash   VARCHAR(255)  NOT NULL,
    oauth2_provider VARCHAR(50),
    oauth2_subject  VARCHAR(255),
    enabled         BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);
