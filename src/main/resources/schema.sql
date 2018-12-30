CREATE TABLE IF NOT EXISTS users(
    id UUID,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone TEXT NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by UUID NOT NULL,
    PRIMARY KEY (id)
);

-- PostgreSQL
--CREATE TABLE IF NOT EXISTS user_roles(
--    user_id UUID,
--    roles TEXT NOT NULL,
--    CONSTRAINT user_roles_users_fk FOREIGN KEY (user_id)
--    REFERENCES users (id) MATCH SIMPLE
--);


CREATE TABLE IF NOT EXISTS user_roles(
    user_id UUID,
    roles TEXT NOT NULL
);

ALTER TABLE user_roles
    ADD FOREIGN KEY (user_id)
    REFERENCES users(id);