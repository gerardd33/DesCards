
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password BYTEA NOT NULL
);

CREATE TABLE sessions (
    username VARCHAR(50) PRIMARY KEY,
    expires TIMESTAMP NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');
