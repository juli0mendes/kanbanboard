CREATE TABLE bucket (
    id SERIAL PRIMARY KEY,
    uuid UUID UNIQUE NOT NULL,
    position INT UNIQUE NOT NULL,
    name VARCHAR UNIQUE NOT NULL
);

INSERT INTO bucket(uuid, position, name) values ('8d5732c8-cc85-11ea-87d0-0242ac130003', 100, 'EXISTENT');