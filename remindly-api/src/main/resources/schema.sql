CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE lists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    description TEXT,
    foreign key (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    list_id BIGINT NOT NULL,
    item VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (list_id) REFERENCES lists(id) ON DELETE CASCADE
);

-- CREATE TABLE user_lists (
--     user_id BIGINT NOT NULL,
--     list_id BIGINT NOT NULL,
--     PRIMARY KEY (user_id, list_id),
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (list_id) REFERENCES lists(id) ON DELETE CASCADE
-- );

-- CREATE TABLE user_items (
--     user_id BIGINT NOT NULL,
--     item_id BIGINT NOT NULL,
--     PRIMARY KEY (user_id, item_id),
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE tags (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL UNIQUE
-- );

-- CREATE TABLE item_tags (
--     item_id BIGINT NOT NULL,
--     tag_id BIGINT NOT NULL,
--     PRIMARY KEY (item_id, tag_id),
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
--     FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
-- );

-- CREATE TABLE comments (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     user_id BIGINT NOT NULL,
--     content TEXT NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

-- CREATE TABLE notifications (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     user_id BIGINT NOT NULL,
--     message TEXT NOT NULL,
--     is_read BOOLEAN DEFAULT FALSE,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

-- CREATE TABLE settings (
--     user_id BIGINT PRIMARY KEY,
--     theme VARCHAR(50) DEFAULT 'light',
--     language VARCHAR(50) DEFAULT 'en',
--     notifications_enabled BOOLEAN DEFAULT TRUE,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

-- CREATE TABLE activity_logs (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     user_id BIGINT NOT NULL,
--     action VARCHAR(255) NOT NULL,
--     item_id BIGINT,
--     list_id BIGINT,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
--     FOREIGN KEY (list_id) REFERENCES lists(id) ON DELETE CASCADE
-- );

-- CREATE TABLE attachments (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     file_path VARCHAR(255) NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE item_history (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     change_type VARCHAR(50) NOT NULL,
--     change_description TEXT,
--     changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE item_priorities (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     priority_level VARCHAR(50) NOT NULL,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE item_status (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     status VARCHAR(50) NOT NULL,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE item_reminders (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     item_id BIGINT NOT NULL,
--     reminder_time TIMESTAMP NOT NULL,
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
-- );

-- CREATE TABLE item_collaborators (
--     item_id BIGINT NOT NULL,
--     user_id BIGINT NOT NULL,
--     PRIMARY KEY (item_id, user_id),
--     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

