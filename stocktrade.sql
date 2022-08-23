-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stocktrade
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `date_data`
--

DROP TABLE IF EXISTS `date_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `date_data` (
  `date_date_id` int NOT NULL AUTO_INCREMENT COMMENT '统计数据主键',
  `data_date` date DEFAULT NULL COMMENT '当日时间',
  `buy_num` float DEFAULT NULL COMMENT '买的总额',
  `sell_num` float DEFAULT NULL COMMENT '卖的总额',
  `flag` int DEFAULT NULL COMMENT '是否已经计算，如果页面需要显示当天的信息则这个flag值为0；当这天过去所有信息都完整时再计算则值为1.',
  PRIMARY KEY (`date_date_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date_data`
--

LOCK TABLES `date_data` WRITE;
/*!40000 ALTER TABLE `date_data` DISABLE KEYS */;
INSERT INTO `date_data` VALUES (5,'2022-08-11',2600000,0,0),(6,'2022-08-13',5200000,2600000,0);
/*!40000 ALTER TABLE `date_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issuer_sector`
--

DROP TABLE IF EXISTS `issuer_sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issuer_sector` (
  `issuer_sector_id` int NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `issuer_sector_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名',
  PRIMARY KEY (`issuer_sector_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuer_sector`
--

LOCK TABLES `issuer_sector` WRITE;
/*!40000 ALTER TABLE `issuer_sector` DISABLE KEYS */;
INSERT INTO `issuer_sector` VALUES (1,'天地银行');
/*!40000 ALTER TABLE `issuer_sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ownership`
--

DROP TABLE IF EXISTS `ownership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ownership` (
  `ownership_id` int NOT NULL AUTO_INCREMENT COMMENT '持有量表主键',
  `user_id` int DEFAULT NULL COMMENT '用户',
  `stock_id` int DEFAULT NULL COMMENT '股票',
  `ownership_num` int DEFAULT NULL COMMENT '持有量',
  PRIMARY KEY (`ownership_id`) USING BTREE,
  KEY `stock_relation` (`stock_id`) USING BTREE,
  KEY `user_relation` (`user_id`) USING BTREE,
  CONSTRAINT `stock_relation` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_relation` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ownership`
--

LOCK TABLES `ownership` WRITE;
/*!40000 ALTER TABLE `ownership` DISABLE KEYS */;
INSERT INTO `ownership` VALUES (13,1,1,0),(14,2,1,0);
/*!40000 ALTER TABLE `ownership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_person`
--

DROP TABLE IF EXISTS `sale_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_person` (
  `sale_person_id` int NOT NULL AUTO_INCREMENT COMMENT '交易员表主键',
  `person_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易员姓名',
  `person_authority` int DEFAULT NULL COMMENT '交易员权限',
  `person_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易员密码（本系统密码）',
  `person_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易员手机号',
  `person_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '交易员是否被删除  0否 1是',
  PRIMARY KEY (`sale_person_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_person`
--

LOCK TABLES `sale_person` WRITE;
/*!40000 ALTER TABLE `sale_person` DISABLE KEYS */;
INSERT INTO `sale_person` VALUES (1,'ctq',NULL,'123',NULL,'0');
/*!40000 ALTER TABLE `sale_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `stock_id` int NOT NULL AUTO_INCREMENT COMMENT '股票表主键',
  `issuer_sector_id` int DEFAULT NULL COMMENT '机构',
  `type_id` int DEFAULT NULL COMMENT '种类',
  `RIC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '股票名字',
  `stock_price` float DEFAULT NULL COMMENT '股票价格',
  `release_num` int DEFAULT NULL COMMENT '发行数',
  `stock_limit` int DEFAULT NULL COMMENT '股票限额（单账号）',
  `flag` int DEFAULT NULL COMMENT '股票是否可以交易  0否 1是',
  PRIMARY KEY (`stock_id`) USING BTREE,
  KEY `type_relation` (`type_id`) USING BTREE,
  KEY `issuer_sector_relation` (`issuer_sector_id`) USING BTREE,
  CONSTRAINT `issuer_sector_relation` FOREIGN KEY (`issuer_sector_id`) REFERENCES `issuer_sector` (`issuer_sector_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type_relation` FOREIGN KEY (`type_id`) REFERENCES `stock_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,1,1,'ric','A股',1000,3000,2000,1);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_type`
--

DROP TABLE IF EXISTS `stock_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_type` (
  `type_id` int NOT NULL AUTO_INCREMENT COMMENT '股票类型表主键',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_type`
--

LOCK TABLES `stock_type` WRITE;
/*!40000 ALTER TABLE `stock_type` DISABLE KEYS */;
INSERT INTO `stock_type` VALUES (1,'美股');
/*!40000 ALTER TABLE `stock_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `trade_id` int NOT NULL AUTO_INCREMENT COMMENT '交易表主键  交易号',
  `user_id` int DEFAULT NULL COMMENT '用户id 外键',
  `stock_id` int DEFAULT NULL COMMENT '股票id 外键',
  `sale_person_id` int DEFAULT NULL COMMENT '操作员id 外键',
  `trade_size` int DEFAULT NULL COMMENT '单次交易数量  整数',
  `trade_date` date DEFAULT NULL COMMENT '交易日期',
  `trade_time` datetime DEFAULT NULL COMMENT '交易时间   有利于后端实现',
  `trade_type` int DEFAULT NULL COMMENT '0买 1卖',
  `trade_method` int DEFAULT NULL COMMENT '0HT 1PT',
  `trade_per_price` float DEFAULT NULL COMMENT '交易时单价',
  PRIMARY KEY (`trade_id`) USING BTREE,
  KEY `trade_stock_relation` (`stock_id`) USING BTREE,
  KEY `trade_user_relation` (`user_id`) USING BTREE,
  KEY `trade_saleperson_relation` (`sale_person_id`) USING BTREE,
  CONSTRAINT `trade_saleperson_relation` FOREIGN KEY (`sale_person_id`) REFERENCES `sale_person` (`sale_person_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_stock_relation` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_user_relation` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (10,1,1,1,2000,'2022-08-11','2022-08-11 17:59:22',0,0,1300),(12,2,1,1,2000,'2022-08-13','2022-08-13 10:41:43',0,0,1300),(13,2,1,1,2000,'2022-08-13','2022-08-13 10:44:43',1,1,1300),(14,2,1,1,2000,'2022-08-13','2022-08-13 10:54:37',0,0,1300),(15,2,1,1,2000,'2022-08-13','2022-08-13 10:56:53',1,1,1300),(16,2,1,1,2000,'2022-08-13','2022-08-13 11:03:57',0,1,1300),(17,2,1,1,2000,'2022-08-13','2022-08-13 11:04:17',1,0,1300);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码（非本系统密码）',
  `flag` int DEFAULT '0' COMMENT '标记位 是否删除，0未删除，1删除',
  `authority` int DEFAULT NULL COMMENT '权限',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'cfq','123',0,NULL,NULL),(2,'yh','123',0,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-23 12:17:39
