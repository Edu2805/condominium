CREATE TABLE IF NOT EXISTS auth.role_permissions (
    role_id UUID REFERENCES auth.roles(id),
    permission_id UUID REFERENCES auth.permissions(id),
    PRIMARY KEY (role_id, permission_id)
);
