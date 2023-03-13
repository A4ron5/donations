CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    link_picture VARCHAR(255)
);

CREATE TABLE user_settings (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    percent_fee DECIMAL(5,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    timezone VARCHAR(50) NOT NULL,
    language VARCHAR(50) NOT NULL,
    secret_token VARCHAR(255) NOT NULL,
    CONSTRAINT fk_user_settings_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE donate_settings (
    id SERIAL PRIMARY KEY,
    user_settings_id INT NOT NULL,
    min_sum DECIMAL(10,2) NOT NULL,
    max_sum DECIMAL(10,2) NOT NULL,
    max_count_of_symbols INT NOT NULL,
    is_remove_links BOOLEAN NOT NULL,
    is_message_moderate BOOLEAN NOT NULL,
    black_list TEXT,
    CONSTRAINT fk_donate_settings_user_settings_id FOREIGN KEY (user_settings_id) REFERENCES user_settings (id)
);

CREATE TABLE payout_settings (
    id SERIAL PRIMARY KEY,
    user_settings_id INT NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_payout_user_settings_id FOREIGN KEY (user_settings_id) REFERENCES user_settings (id)
);

CREATE TABLE payout_methods (
    id SERIAL PRIMARY KEY,
    payout_settings_id INT NOT NULL,
    is_main BOOLEAN NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
    fee DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_payout_methods_payout_settings_id FOREIGN KEY (payout_settings_id) REFERENCES payout_settings (id)
);

CREATE TABLE donates (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    sum DECIMAL(10,2) NOT NULL,
    donate_date TIMESTAMP NOT NULL DEFAULT NOW(),
    text TEXT,
    from_user VARCHAR(255),
    fee_coverage BOOLEAN NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_donates_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE payouts (
    id SERIAL PRIMARY KEY ,
    user_id INT NOT NULL,
    payout_method_id INT NOT NULL,
    sum DECIMAL(10,2) NOT NULL,
    payout_date TIMESTAMP NOT NULL DEFAULT NOW(),
    fee DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_payouts_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_payouts_payout_method_id FOREIGN KEY (payout_method_id) REFERENCES payout_methods (id)
);

CREATE INDEX idx_donates_from_user ON donates(from_user);
CREATE INDEX idx_donates_sum ON donates(sum DESC);