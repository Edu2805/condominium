CREATE TABLE IF NOT EXISTS auth.user_tenant (
    user_id UUID REFERENCES auth.user(id),
    tenant_id UUID REFERENCES auth.tenant(id),
    PRIMARY KEY (user_id, tenant_id)
);
