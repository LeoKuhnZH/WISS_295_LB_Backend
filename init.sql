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
