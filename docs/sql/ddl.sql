-- Generated 2024-02-27 15:32:56-0700 for database version 1

CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `created`      INTEGER                           NOT NULL,
    `oauth_key`    TEXT                              NOT NULL,
    `display_name` TEXT                              NOT NULL COLLATE NOCASE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_user_oauth_key` ON `user` (`oauth_key`);

CREATE UNIQUE INDEX IF NOT EXISTS `index_user_display_name` ON `user` (`display_name`);

CREATE TABLE IF NOT EXISTS `game_result`
(
    `game_result_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `timestamp`      INTEGER                           NOT NULL,
    `length`         INTEGER                           NOT NULL,
    `guess_count`    INTEGER                           NOT NULL,
    `duration`       INTEGER                           NOT NULL,
    `user_id`        INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_game_result_guess_count_duration` ON `game_result` (`guess_count`, `duration`);

CREATE INDEX IF NOT EXISTS `index_game_result_timestamp` ON `game_result` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_game_result_length` ON `game_result` (`length`);

CREATE INDEX IF NOT EXISTS `index_game_result_guess_count` ON `game_result` (`guess_count`);

CREATE INDEX IF NOT EXISTS `index_game_result_duration` ON `game_result` (`duration`);

CREATE INDEX IF NOT EXISTS `index_game_result_user_id` ON `game_result` (`user_id`);