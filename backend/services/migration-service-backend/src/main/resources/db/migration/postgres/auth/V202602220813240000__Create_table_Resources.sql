CREATE TABLE IF NOT EXISTS auth.resources (
    id UUID PRIMARY KEY,
    code VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);
