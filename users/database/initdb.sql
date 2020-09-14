
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password BYTEA NOT NULL
);

CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    userid INTEGER,
    expires TIMESTAMP NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', '\x2432622431322432494d5051414c4635722f48745976436d35696f2f4f4c57645a4c416f326278576b42544b534f46386e35586e7367326b4d337543'::bytea);
