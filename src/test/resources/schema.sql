CREATE TABLE bucket (
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    position INT NOT NULL,
    name VARCHAR NOT NULL
);