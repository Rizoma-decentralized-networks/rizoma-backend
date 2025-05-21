CREATE SEQUENCE IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS category_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS tag_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mark_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
    user_id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    category_id INT PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE tags (
    tag_id INT PRIMARY KEY,
    tag VARCHAR(255) NOT NULL
);

CREATE TABLE marks (
    mark_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    user_id INT,
    category_id INT,
    tag_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);
