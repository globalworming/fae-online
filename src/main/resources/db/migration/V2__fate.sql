CREATE TABLE IF NOT EXISTS world (

id SERIAL PRIMARY KEY ,
name varchar,
created_at TIMESTAMP,
updated_at TIMESTAMP

);

insert into world VALUES (0, 'world0', now(), now());

ALTER TABLE message
ADD COLUMN world INTEGER NOT NULL DEFAULT 0;