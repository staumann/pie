CREATE TABLE IF NOT EXISTS users(
    id VARCHAR(50) PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;