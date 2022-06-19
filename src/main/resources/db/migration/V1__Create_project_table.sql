--create schema IF NOT EXISTS task_manager;

create table IF NOT EXISTS public.project(
    id SERIAL,
    title VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
