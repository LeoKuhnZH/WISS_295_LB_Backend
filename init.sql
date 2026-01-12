DROP DATABASE IF EXISTS games;
CREATE DATABASE IF NOT EXISTS games;

USE games;

CREATE TABLE `genre`
(
    `genre_id`    INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`genre_id`)
);

CREATE TABLE `game`
(
    `game_id`     INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `rating`      INT          NOT NULL,
    `genre_id`    INT          NOT NULL,
    PRIMARY KEY (`game_id`)
);

CREATE TABLE `user`
(
    `user_id`  INT          NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `game_id`  INT          NOT NULL,
    `score`    INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`)
);

ALTER TABLE `game`
    ADD CONSTRAINT `fk_game_genre`
        FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`);

ALTER TABLE `user`
    ADD CONSTRAINT `fk_user_game`
        FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`);