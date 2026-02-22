-- V2__create_users.sql
CREATE TABLE IF NOT EXISTS auth.user (
    id UUID PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
