CREATE TABLE IF NOT EXISTS users(
    id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at DATE DEFAULT(CURRENT_DATE)
);

CREATE TABLE IF NOT EXISTS categories(
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS shops(
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id VARCHAR(50) NOT NULL,
    FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS bills(
    id VARCHAR(50) PRIMARY KEY,
    payed_by VARCHAR(50) NOT NULL,
    shop_id VARCHAR(50) NOT NULL,
    billing_date DATE NOT NULL,
    FOREIGN KEY(payed_by) REFERENCES users(id),
    FOREIGN KEY(shop_id) REFERENCES shops(id)
);

CREATE TABLE IF NOT EXISTS positions(
    id VARCHAR(50) PRIMARY KEY,
    bill_id VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL,
    name VARCHAR(100) NOT NULL,
    comment VARCHAR(255),
    target_id VARCHAR(50) NOT NULL,
    category_id VARCHAR(50) NOT NULL,
    FOREIGN KEY(category_id) REFERENCES categories(id),
    FOREIGN KEY(bill_id) REFERENCES bills(id),
    FOREIGN KEY (target_id) REFERENCES users(id)
);