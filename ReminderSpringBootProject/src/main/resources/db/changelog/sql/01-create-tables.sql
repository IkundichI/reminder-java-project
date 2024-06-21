CREATE TABLE reminders (
                           id UUID PRIMARY KEY NOT NULL,
                           title VARCHAR(255) NOT NULL,
                           description TEXT,
                           reminder_time TIMESTAMP NOT NULL,
                           user_id UUID NOT NULL
);

CREATE TABLE users (
                       id UUID PRIMARY KEY NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       telegram_contact VARCHAR(255),
                       telegram_chat_id VARCHAR(255)
);
ALTER TABLE reminders
    ADD CONSTRAINT fk_reminder_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE;


ALTER TABLE reminders
    ADD COLUMN is_sent BOOLEAN DEFAULT FALSE;

