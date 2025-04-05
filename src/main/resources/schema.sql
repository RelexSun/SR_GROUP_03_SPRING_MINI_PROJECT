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

CREATE TABLE achievements(
                             achievement_id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
                             title VARCHAR(100) NOT NULL,
                             description VARCHAR(100) NOT NULL,
                             badge VARCHAR(100) NOT NULL,
                             xp_required INT not null
);

CREATE TABLE app_user_achievement(
                                     app_user_achievement_id serial primary key,
                                     app_user_id uuid not null ,
                                     achievement_id uuid not null ,
                                     CONSTRAINT fk_app_user FOREIGN KEY (app_user_id) REFERENCES app_users (app_user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT fk_achievements FOREIGN KEY (achievement_id) REFERENCES achievements (achievement_id) ON DELETE CASCADE ON UPDATE CASCADE
);