CREATE TABLE IF NOT EXISTS public.privilege
(
    id   VARCHAR(255) DEFAULT RANDOM_UUID() NOT NULL,
    name VARCHAR(255)                       NOT NULL,
    CONSTRAINT privilege_id_pk PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.role
(
    id   VARCHAR(255) DEFAULT RANDOM_UUID() NOT NULL,
    name VARCHAR(255)                       NOT NULL,
    CONSTRAINT role_id_pk PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.role_privilege
(
    role_id      VARCHAR(255) NOT NULL,
    privilege_id VARCHAR(255) NOT NULL,
    CONSTRAINT role_privilege_role_id_privilege_id_pk PRIMARY KEY (role_id, privilege_id),
    CONSTRAINT role_privilege_privilege_id_fk FOREIGN KEY (privilege_id) REFERENCES public.privilege,
    CONSTRAINT role_privilege_role_id_fk FOREIGN KEY (role_id) REFERENCES public.role
);


CREATE TABLE IF NOT EXISTS public.user
(
    id        VARCHAR(255) DEFAULT RANDOM_UUID() NOT NULL,
    email     VARCHAR(255)                       NOT NULL,
    enabled   BOOLEAN                            NOT NULL,
    password  VARCHAR(255)                       NOT NULL,
    user_name VARCHAR(255)                       NOT NULL,
    CONSTRAINT user_user_id_pk PRIMARY KEY (id),
    CONSTRAINT user_user_name_uk UNIQUE (user_name),
    CONSTRAINT user_email_uk UNIQUE (email)
);


CREATE TABLE IF NOT EXISTS PUBLIC.user_role
(
    user_id VARCHAR(255) NOT NULL,
    role_id VARCHAR(255) NOT NULL,
    CONSTRAINT user_role_user_id_role_id_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES public.role,
    CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES public.user
);