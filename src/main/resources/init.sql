DROP DATABASE IF EXISTS games;
CREATE DATABASE IF NOT EXISTS games;

USE games;

CREATE TABLE `genre`
(
    `genre_id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`genre_id`)
);

CREATE TABLE `game`
(
    `game_id`     BIGINT       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `rating`      INT          NOT NULL,
    PRIMARY KEY (`game_id`)
);

CREATE TABLE `user`
(
    `user_id`  BIGINT       NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `game_genre`
(
    `game_id`  BIGINT NOT NULL,
    `genre_id` BIGINT NOT NULL,
    PRIMARY KEY (`game_id`, `genre_id`)
);

CREATE TABLE `user_game`
(
    `user_id` BIGINT NOT NULL,
    `game_id` BIGINT NOT NULL,
    `score`   INT    NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`, `game_id`)
);

ALTER TABLE `game_genre`
    ADD CONSTRAINT `fk_game_genre_game`
        FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`);

ALTER TABLE `game_genre`
    ADD CONSTRAINT `fk_game_genre_genre`
        FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`);

ALTER TABLE `user_game`
    ADD CONSTRAINT `fk_user_game_user`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `user_game`
    ADD CONSTRAINT `fk_user_game_game`
        FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`);


USE games;

-- Genres
INSERT INTO genre (name, description) VALUES
                                          ('Action', 'Fast-paced games with combat and reflex challenges'),
                                          ('Adventure', 'Story-driven exploration games'),
                                          ('RPG', 'Role-playing games with character progression'),
                                          ('Strategy', 'Games focused on planning and tactics'),
                                          ('Sports', 'Sports simulation and arcade games');

-- Games
INSERT INTO game (title, description, rating) VALUES
                                                  ('Shadow Strike', 'A stealth-based action game', 85),
                                                  ('Kingdoms of Eldoria', 'An epic fantasy RPG', 92),
                                                  ('Galactic Tactics', 'Turn-based space strategy game', 88),
                                                  ('Street Champions', 'Urban sports competition game', 78),
                                                  ('Lost Realms', 'Adventure game set in ancient ruins', 81);

-- Users
INSERT INTO user (username) VALUES
                                ('alice'),
                                ('bob'),
                                ('charlie'),
                                ('diana'),
                                ('eve');

-- Game ↔ Genre relationships
INSERT INTO game_genre (game_id, genre_id) VALUES
                                               (1, 1), -- Shadow Strike → Action
                                               (2, 3), -- Kingdoms of Eldoria → RPG
                                               (3, 4), -- Galactic Tactics → Strategy
                                               (4, 5), -- Street Champions → Sports
                                               (5, 2), -- Lost Realms → Adventure
                                               (2, 2), -- Kingdoms of Eldoria → Adventure
                                               (1, 2); -- Shadow Strike → Adventure

-- User ↔ Game scores
INSERT INTO user_game (user_id, game_id, score) VALUES
                                                    (1, 1, 90),
                                                    (1, 2, 95),
                                                    (2, 1, 70),
                                                    (2, 3, 85),
                                                    (3, 2, 88),
                                                    (3, 5, 80),
                                                    (4, 4, 75),
                                                    (5, 3, 92),
                                                    (5, 5, 86);