INSERT INTO users (username, password, created_at)
VALUES ('test', '$2a$10$xqlp0fdTauP6zEN9XUiSKuJROvfOo/TDW3XI29473Y6IVpr1NAIZy', CURRENT_TIMESTAMP);

INSERT INTO accounts (user_id, balance, created_at)
VALUES (1, 8.00, CURRENT_TIMESTAMP);