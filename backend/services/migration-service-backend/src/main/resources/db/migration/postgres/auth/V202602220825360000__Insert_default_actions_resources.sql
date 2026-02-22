INSERT INTO auth.actions (id, code) VALUES
    (gen_random_uuid(), 'VIEW'),
    (gen_random_uuid(), 'CREATE'),
    (gen_random_uuid(), 'UPDATE'),
    (gen_random_uuid(), 'DELETE'),
    (gen_random_uuid(), 'EXECUTE');

INSERT INTO auth.resources (id, code, description) VALUES
    (gen_random_uuid(), 'USER_API', 'User management'),
    (gen_random_uuid(), 'TENANT_API', 'Tenant management'),
    (gen_random_uuid(), 'MENU_ADMIN', 'Admin menu');
