CREATE TABLE IF NOT EXISTS auth.roles (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES auth.tenant(id),
    name VARCHAR(50) NOT NULL,
    description TEXT,
    UNIQUE (tenant_id, name)
);
