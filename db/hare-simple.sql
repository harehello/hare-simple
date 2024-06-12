-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hare-simple
-- ------------------------------------------------------
-- Server version	8.0.21

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `age` tinyint DEFAULT NULL COMMENT '年龄',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `status` varchar(100) NOT NULL COMMENT '状态：正常、限制',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (7,'admin','$2a$10$qxPghqfHsc.SpQZ6iLKB3OkwMqXnbu0A6hfttHp1g.5VS.i/gn5QC','管理员','admin',NULL,'',NULL,'正常','系统管理员'),(8,'XH123','$2a$10$I3cOeTPA8ub./SLvm9TJpe0ovEYqAHaJeJxK0r8SchjPGP2/Xi7Ti','张乐乐','student',7,'女','13256786789','正常',''),(9,'15987651234','$2a$10$TTZGu14nvHNFM/wj4JTRR.epfJPt.05V/nkiWuepznSO6E5NbKEUi','周数','teacher',30,'男','15987651234','正常','无');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
