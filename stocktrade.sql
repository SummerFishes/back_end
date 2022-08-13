
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for date_data
-- ----------------------------
DROP TABLE IF EXISTS `date_data`;
CREATE TABLE `date_data`  (
  `date_date_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '统计数据主键',
  `data_date` date NULL DEFAULT NULL COMMENT '当日时间',
  `buy_num` int(0) NULL DEFAULT NULL COMMENT '买的数量',
  `sell_num` int(0) NULL DEFAULT NULL COMMENT '卖的数量',
  `flag` int(0) NULL DEFAULT NULL COMMENT '是否已经计算，如果页面需要显示当天的信息则这个flag值为0；当这天过去所有信息都完整时再计算则值为1.',
  PRIMARY KEY (`date_date_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `stock_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '股票表主键',
  `issuer_sector_id` int(0) NULL DEFAULT NULL COMMENT '机构',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '种类',
  `RIC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票名字',
  `stock_price` float NULL DEFAULT NULL COMMENT '股票价格',
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
-- Table structure for stock_type
-- ----------------------------
DROP TABLE IF EXISTS `stock_type`;
CREATE TABLE `stock_type`  (
  `type_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '股票类型表主键',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
