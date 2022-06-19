create table IF NOT EXISTS public.task(
    id SERIAL,
    title VARCHAR(255) NOT NULL,
    initial_date DATE NOT NULL,
    final_date DATE NOT NULL,
    priority VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    time TIME NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES public.project (id) ON DELETE CASCADE
);