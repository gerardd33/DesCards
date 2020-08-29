
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE sessions (
    username VARCHAR(50) PRIMARY KEY,
    expires TIMESTAMP NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');
