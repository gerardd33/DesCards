
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE session (
    id INTEGER PRIMARY KEY,
    login VARCHAR(50) UNIQUE,
    expires TIMESTAMP NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');
