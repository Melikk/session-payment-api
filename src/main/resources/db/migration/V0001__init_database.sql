CREATE TABLE users
(
    id               SERIAL PRIMARY KEY,
    username         VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    attempted_logins INTEGER      NOT NULL DEFAULT 0,
    unlock_at        TIMESTAMP(6),
    created_at       TIMESTAMP(6),
    updated_at       TIMESTAMP(6),
    deactivated_at   TIMESTAMP(6),
    CONSTRAINT unique_users_username UNIQUE (username)
);

CREATE TABLE session
(
    id             SERIAL PRIMARY KEY,
    user_id        BIGINT       NOT NULL,
    expired_at     TIMESTAMP(6) NOT NULL,
    uuid_key       UUID         UNIQUE,
    created_at     TIMESTAMP(6),
    updated_at     TIMESTAMP(6),
    deactivated_at TIMESTAMP(6),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE accounts
(
    id             SERIAL PRIMARY KEY,
    user_id        BIGINT         NOT NULL,
    balance        DECIMAL(19, 2) NOT NULL,
    created_at     TIMESTAMP(6),
    updated_at     TIMESTAMP(6),
    deactivated_at TIMESTAMP(6),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE transactions
(
    id               SERIAL PRIMARY KEY,
    account_id       BIGINT         NOT NULL,
    amount           DECIMAL(19, 2) NOT NULL,
    transaction_type VARCHAR(20) CHECK (transaction_type IN ('WITHDRAWAL', 'DEPOSIT')),
    created_at       TIMESTAMP(6),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);



