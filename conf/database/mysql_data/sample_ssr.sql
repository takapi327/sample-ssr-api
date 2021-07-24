CREATE DATABASE `sample_ssr` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `sample_ssr`;

DROP TABLE IF EXISTS `plant`;

CREATE TABLE `plant` (
  `id` BIGINT(64) unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10, 3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `plant` WRITE;
INSERT INTO `plant` VALUES (1, 'ガジュマル', 1800);
INSERT INTO `plant` VALUES (2, 'モンステラ', 2800);
INSERT INTO `plant` VALUES (3, 'ハエトリソウ', 3800);
INSERT INTO `plant` VALUES (4, 'ウツボカヅラ', 4800);
INSERT INTO `plant` VALUES (5, 'アロマカティス', 5800);
