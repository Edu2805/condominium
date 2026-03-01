CREATE TABLE IF NOT EXISTS actions (
    id UUID PRIMARY KEY,
    resource_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT fk_action_resource
    FOREIGN KEY (resource_id) REFERENCES resources(id),

    CONSTRAINT uk_action UNIQUE(resource_id, name)
);
