/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : stocktrade

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 29/08/2022 09:27:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for date_data
-- ----------------------------
DROP TABLE IF EXISTS `date_data`;
CREATE TABLE `date_data`  (
  `date_date_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '统计数据主键',
  `data_date` date NULL DEFAULT NULL COMMENT '当日时间',
  `buy_num` float NULL DEFAULT NULL COMMENT '买的总价钱',
  `sell_num` float NULL DEFAULT NULL COMMENT '卖的总价钱',
  `flag` int(0) NULL DEFAULT NULL COMMENT '是否已经计算，如果页面需要显示当天的信息则这个flag值为0；当这天过去所有信息都完整时再计算则值为1.',
  PRIMARY KEY (`date_date_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of date_data
-- ----------------------------
INSERT INTO `date_data` VALUES (1, '2022-06-27', 197600, 0, 0);
INSERT INTO `date_data` VALUES (2, '2022-08-23', 20800, 0, 0);
INSERT INTO `date_data` VALUES (3, '2022-08-25', 13000, 6500, 0);
INSERT INTO `date_data` VALUES (4, '2022-08-26', 26000, 13000, 0);
INSERT INTO `date_data` VALUES (5, '2022-08-29', 5200, 0, 0);

-- ----------------------------
-- Table structure for issuer_sector
-- ----------------------------
DROP TABLE IF EXISTS `issuer_sector`;
CREATE TABLE `issuer_sector`  (
  `issuer_sector_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `issuer_sector_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名',
  PRIMARY KEY (`issuer_sector_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of issuer_sector
-- ----------------------------
INSERT INTO `issuer_sector` VALUES (1, 'A ');

-- ----------------------------
-- Table structure for ownership
-- ----------------------------
DROP TABLE IF EXISTS `ownership`;
CREATE TABLE `ownership`  (
  `ownership_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '持有量表主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户',
  `stock_id` int(0) NULL DEFAULT NULL COMMENT '股票',
  `ownership_num` int(0) NULL DEFAULT NULL COMMENT '持有量',
  PRIMARY KEY (`ownership_id`) USING BTREE,
  INDEX `stock_relation`(`stock_id`) USING BTREE,
  INDEX `user_relation`(`user_id`) USING BTREE,
  CONSTRAINT `stock_relation` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_relation` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ownership
-- ----------------------------
INSERT INTO `ownership` VALUES (2, 1, 1, 83);

-- ----------------------------
-- Table structure for sale_person
-- ----------------------------
DROP TABLE IF EXISTS `sale_person`;
CREATE TABLE `sale_person`  (
  `sale_person_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '交易员表主键',
  `person_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易员姓名',
  `person_authority` int(0) NULL DEFAULT NULL COMMENT '交易员权限',
  `person_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易员密码（本系统密码）',
  `person_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易员手机号',
  `person_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '交易员是否被删除  0否 1是',
  PRIMARY KEY (`sale_person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale_person
-- ----------------------------
INSERT INTO `sale_person` VALUES (1, 'ctq', NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `stock_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '股票表主键',
  `issuer_sector_id` int(0) NULL DEFAULT NULL COMMENT '机构',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '种类',
  `RIC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票名字',
  `stock_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '股票价格',
  `release_num` int(0) NULL DEFAULT NULL COMMENT '发行数',
  `stock_limit` int(0) NULL DEFAULT NULL COMMENT '股票限额（单账号）',
  `flag` int(0) NULL DEFAULT NULL COMMENT '股票是否可以交易  0否 1是',
  PRIMARY KEY (`stock_id`) USING BTREE,
  INDEX `type_relation`(`type_id`) USING BTREE,
  INDEX `issuer_sector_relation`(`issuer_sector_id`) USING BTREE,
  CONSTRAINT `issuer_sector_relation` FOREIGN KEY (`issuer_sector_id`) REFERENCES `issuer_sector` (`issuer_sector_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type_relation` FOREIGN KEY (`type_id`) REFERENCES `stock_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (1, 1, 1, 'ric', '腾讯', 100.00, 1000, 300, NULL);

-- ----------------------------
-- Table structure for stock_type
-- ----------------------------
DROP TABLE IF EXISTS `stock_type`;
CREATE TABLE `stock_type`  (
  `type_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '股票类型表主键',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rate` float NULL DEFAULT NULL COMMENT '1该货币可换取多少美元',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_type
-- ----------------------------
INSERT INTO `stock_type` VALUES (1, 'USD', '美元', 1);
INSERT INTO `stock_type` VALUES (2, 'HKD', '港币', 0.127444);
INSERT INTO `stock_type` VALUES (3, 'SGD', '新币', 0.717695);
INSERT INTO `stock_type` VALUES (4, 'KRW', '韩币', 0.00074527);
INSERT INTO `stock_type` VALUES (5, 'JPY', '日元', 0.00727431);
INSERT INTO `stock_type` VALUES (6, 'EUR', '欧元', 0.997506);
INSERT INTO `stock_type` VALUES (7, 'GBP', '英镑', 1.17312);
INSERT INTO `stock_type` VALUES (8, 'INR', '卢比', 0.0125056);
INSERT INTO `stock_type` VALUES (9, 'RUB', '卢币', 0.0167926);
INSERT INTO `stock_type` VALUES (10, 'CHF', '法郎', 1.03541);
INSERT INTO `stock_type` VALUES (11, 'CNY', '人民币', 0.145529);

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `trade_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '交易表主键  交易号',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id 外键',
  `stock_id` int(0) NULL DEFAULT NULL COMMENT '股票id 外键',
  `sale_person_id` int(0) NULL DEFAULT NULL COMMENT '操作员id 外键',
  `trade_size` int(0) NULL DEFAULT NULL COMMENT '单次交易数量  整数',
  `trade_date` date NULL DEFAULT NULL COMMENT '交易日期',
  `trade_time` datetime(0) NULL DEFAULT NULL COMMENT '交易时间   有利于后端实现',
  `trade_type` int(0) NULL DEFAULT NULL COMMENT '0买 1卖',
  `trade_method` int(0) NULL DEFAULT NULL COMMENT '0HT 1PT',
  `trade_per_price` float NULL DEFAULT NULL COMMENT '交易时单价',
  PRIMARY KEY (`trade_id`) USING BTREE,
  INDEX `trade_stock_relation`(`stock_id`) USING BTREE,
  INDEX `trade_user_relation`(`user_id`) USING BTREE,
  INDEX `trade_saleperson_relation`(`sale_person_id`) USING BTREE,
  CONSTRAINT `trade_saleperson_relation` FOREIGN KEY (`sale_person_id`) REFERENCES `sale_person` (`sale_person_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_stock_relation` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_user_relation` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade
-- ----------------------------
INSERT INTO `trade` VALUES (12, 1, 1, 1, 40, '2022-06-27', '2022-06-27 21:37:53', 0, 0, 1300);
INSERT INTO `trade` VALUES (13, 1, 1, 1, 4, '2022-06-27', '2022-06-27 21:57:34', 0, 0, 1300);
INSERT INTO `trade` VALUES (14, 1, 1, 1, 4, '2022-06-27', '2022-06-27 21:59:34', 0, 0, 1300);
INSERT INTO `trade` VALUES (15, 1, 1, 1, 4, '2022-08-23', '2022-08-23 20:43:47', 0, 0, 1300);
INSERT INTO `trade` VALUES (16, 1, 1, 1, 4, '2022-08-23', '2022-08-23 20:44:00', 0, 0, 1300);
INSERT INTO `trade` VALUES (17, 1, 1, 1, 4, '2022-08-23', '2022-08-23 21:09:37', 0, 0, 1300);
INSERT INTO `trade` VALUES (18, 1, 1, 1, 4, '2022-08-23', '2022-08-23 21:59:33', 0, 0, 1300);
INSERT INTO `trade` VALUES (19, 1, 1, 1, 10, '2022-08-25', '2022-08-25 08:55:36', 0, 0, 1300);
INSERT INTO `trade` VALUES (20, 1, 1, 1, 5, '2022-08-25', '2022-08-25 09:54:21', 1, 1, 1300);
INSERT INTO `trade` VALUES (21, 1, 1, 1, 20, '2022-08-26', '2022-08-26 08:50:05', 0, 0, 1300);
INSERT INTO `trade` VALUES (22, 1, 1, 1, 10, '2022-08-26', '2022-08-26 08:51:13', 1, 0, 1300);
INSERT INTO `trade` VALUES (23, 1, 1, 1, 4, '2022-08-29', '2022-08-29 09:12:14', 0, 0, 1300);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码（非本系统密码）',
  `flag` int(0) NULL DEFAULT 0 COMMENT '标记位 是否删除，0未删除，1删除',
  `authority` int(0) NULL DEFAULT NULL COMMENT '权限',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'client name', NULL, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
