CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE p_slack
(
    id                  UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
    slack_send_email    VARCHAR(255)                               NOT NULL,
    slack_receive_email VARCHAR(255)                               NOT NULL,
    user_send_id        UUID                                       NOT NULL,
    user_receive_id     UUID                                       NOT NULL,
    message             TEXT                                       NOT NULL,
    created_at          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by          UUID                                       NULL,
    updated_at          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by          UUID                                       NULL,
    deleted_at          TIMESTAMP                                  NULL,
    deleted_by          UUID                                       NULL,
    is_deleted          BOOLEAN          DEFAULT FALSE             NOT NULL
);

