CREATE TABLE users(
    userId SERIAL,
    name VARCHAR(80) NOT NULL,
    mailAddress VARCHAR(80) NOT NULL,
    password VARCHAR(80) NOT NULL,
    dataId INT NOT NULL REFERENCES datas(dataId)
);