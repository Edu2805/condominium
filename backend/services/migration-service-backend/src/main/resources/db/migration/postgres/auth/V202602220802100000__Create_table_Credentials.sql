CREATE TABLE IF NOT EXISTS auth.credentials (
    user_id UUID PRIMARY KEY REFERENCES auth.user(id),
    password_hash TEXT NOT NULL,
    password_expires_at TIMESTAMP NOT NULL
);
