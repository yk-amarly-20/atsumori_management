CREATE TABLE users(
    userId INT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    dataId INT NOT NULL REFERENCES datas(dataId)
);