CREATE TABLE role_actions (
    role_id UUID NOT NULL,
    action_id UUID NOT NULL,

    PRIMARY KEY (role_id, action_id),

    CONSTRAINT fk_role_action_role
    FOREIGN KEY (role_id)
    REFERENCES roles(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_role_action_action
    FOREIGN KEY (action_id)
    REFERENCES actions(id)
    ON DELETE CASCADE
);
