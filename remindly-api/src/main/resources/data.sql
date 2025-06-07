INSERT INTO users (id, username, email) VALUES (1, 'jishnu', 'jishnu@example.com');
INSERT INTO users (id, username, email) VALUES (2, 'alice', 'alice@example.com');
INSERT INTO users (id, username, email) VALUES (3, 'bob', 'bob@example.com');

-- Insert sample lists
INSERT INTO lists (id, name, user_id, description) VALUES (1, 'Groceries', 1, 'Weekly grocery shopping list');
INSERT INTO lists (id, name, user_id, description) VALUES (2, 'Work Tasks', 1, 'Tasks for the upcoming sprint');
INSERT INTO lists (id, name, user_id, description) VALUES (3, 'Vacation Planning', 2, 'Plan trip to Goa');

-- Insert sample items
INSERT INTO items (id, list_id, item) VALUES (1, 1, 'Buy 2 liters of milk');
INSERT INTO items (id, list_id, item) VALUES (2, 1, 'One dozen eggs');
INSERT INTO items (id, list_id, item) VALUES (3, 2, 'Finalize endpoints for Remindly');
INSERT INTO items (id, list_id, item) VALUES (4, 3, 'Check best deals online');
INSERT INTO items (id, list_id, item) VALUES (5, 3, 'Find beachside stay');
