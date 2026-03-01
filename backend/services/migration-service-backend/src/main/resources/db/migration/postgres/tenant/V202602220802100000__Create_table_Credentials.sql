CREATE TABLE IF NOT EXISTS credentials (
    user_id UUID PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,

    CONSTRAINT fk_credentials_user
    FOREIGN KEY (user_id) REFERENCES users(id)
);
