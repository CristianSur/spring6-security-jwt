CREATE TABLE IF NOT EXISTS users
(
    id       int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email    TEXT,
    username TEXT,
    password TEXT,
    role     VARCHAR(20)
);

