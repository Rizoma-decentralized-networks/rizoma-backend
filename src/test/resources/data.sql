CREATE SEQUENCE IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS category_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS tag_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mark_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY, 
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS tags (
    tag_id INT PRIMARY KEY,
    tag VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS marks (
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

INSERT INTO users (user_id, username, email, password, user_image_url) 
VALUES (NEXT VALUE FOR user_id_sequence, 'testuser', 'test@example.com', 'password123', 'https://example.com/image.jpg');


INSERT INTO categories (category_id, category, description)
VALUES (NEXT VALUE FOR category_id_sequence, 'Iniciative', 'Descripción de prueba');
INSERT INTO categories (category_id, category, description)
VALUES (NEXT VALUE FOR category_id_sequence, 'Proposal', 'Otra descripción');
INSERT INTO categories (category_id, category, description)
VALUES (NEXT VALUE FOR category_id_sequence, 'Conflict', 'Conflictos');


INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Environment');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Feminist');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Public Service');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Tenement');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Urbanism');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Mobility');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Culture');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Economy and employment');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Sport');
INSERT INTO tags (tag_id, tag) VALUES (NEXT VALUE FOR tag_id_sequence, 'Democracy memory');

INSERT INTO marks (mark_id, title, description, location, image_url, user_id, category_id, tag_id) 
VALUES (
    NEXT VALUE FOR mark_id_sequence,
    'Restaurante Prueba',
    'Un restaurante de prueba',
    'Calle Falsa 123',
    'https://example.com/image.jpg',
    1,
    1,
    1
);
