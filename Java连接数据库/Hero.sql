/*
 Navicat Premium Data Transfer

 Source Server         : Yin
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : Hero

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 06/05/2020 12:23:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 英雄信息
-- ----------------------------
DROP TABLE IF EXISTS `英雄信息`;
CREATE TABLE `英雄信息` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `英雄名` char(5) NOT NULL DEFAULT '' COMMENT '英雄名字',
  `职业` char(2) NOT NULL DEFAULT '',
  `熟练度` int(4) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
