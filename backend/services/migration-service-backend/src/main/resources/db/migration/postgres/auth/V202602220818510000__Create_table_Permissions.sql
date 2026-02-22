CREATE TABLE IF NOT EXISTS auth.permissions (
    id UUID PRIMARY KEY,
    resource_id UUID REFERENCES auth.resources(id),
    action_id UUID REFERENCES auth.actions(id),
    UNIQUE (resource_id, action_id)
);
