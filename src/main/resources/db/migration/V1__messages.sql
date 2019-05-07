CREATE TABLE IF NOT EXISTS message (

id SERIAL PRIMARY KEY ,
sender varchar,
content varchar,
created_at TIMESTAMP,
updated_at TIMESTAMP

)