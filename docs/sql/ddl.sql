-- Generated 2024-04-02 10:03:34-0600 for database version 1

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
    `size`           INTEGER                           NOT NULL,
    `guess_count`    INTEGER                           NOT NULL,
    `duration`       INTEGER                           NOT NULL,
    `user_id`        INTEGER,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_game_result_guess_count_duration` ON `game_result` (`guess_count`, `duration`);

CREATE INDEX IF NOT EXISTS `index_game_result_timestamp` ON `game_result` (`timestamp`);

CREATE INDEX IF NOT EXISTS `index_game_result_size` ON `game_result` (`size`);

CREATE INDEX IF NOT EXISTS `index_game_result_guess_count` ON `game_result` (`guess_count`);

CREATE INDEX IF NOT EXISTS `index_game_result_duration` ON `game_result` (`duration`);

CREATE INDEX IF NOT EXISTS `index_game_result_user_id` ON `game_result` (`user_id`);