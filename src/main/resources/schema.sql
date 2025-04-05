CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE app_users
(
    app_user_id uuid PRIMARY KEY DEFAULT uuid_generate_v4() ,
    username  VARCHAR(50) NOT NULL UNIQUE ,
    email     VARCHAR(50) NOT NULL UNIQUE ,
    password  VARCHAR(255) NOT NULL,
    level INT DEFAULT 0,
    xp INT DEFAULT 0,
    profile_image_url VARCHAR,
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE habits (
    habit_id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    description VARCHAR,
    frequency VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    app_user_id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_app_user FOREIGN KEY (app_user_id) REFERENCES app_users (app_user_id)
                    ON DELETE CASCADE
                    ON UPDATE CASCADE
);

