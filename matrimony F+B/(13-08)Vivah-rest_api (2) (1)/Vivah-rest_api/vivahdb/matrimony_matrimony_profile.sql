CREATE DATABASE  IF NOT EXISTS `matrimony` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `matrimony`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: matrimony
-- ------------------------------------------------------
-- Server version	8.0.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `matrimony_profile`
--

DROP TABLE IF EXISTS `matrimony_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matrimony_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `about_me` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `income` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `religion_pref` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matrimony_profile`
--

LOCK TABLES `matrimony_profile` WRITE;
/*!40000 ALTER TABLE `matrimony_profile` DISABLE KEYS */;
INSERT INTO `matrimony_profile` VALUES (33,'daisd aidhaowidwa','temurda','MBA','aniket@Mali.com','Aniket','','3000000','Tiwari','Single','134198472','Manager','Any Religion','1999-03-07',15,NULL),(23,'gsdfadsf','warra','BE','pallavi@kmkaf.com','pallavi','','9999','mahure','Married','24524524531','Developer','','1996-12-25',5,NULL),(24,'kajndskawd','Warora','MCA','bhushan@daisd.cok','bhushan','','10000000','matte','Married','983749183','Developer','','2000-02-02',6,NULL),(25,'i am smart','jabalpur,mp','MCA','jitu@gmail.com','jitrendra','','250000','sharma','single','6985741236','Student','','1997-12-25',7,NULL),(26,'','vadgaon,pune','MBA','rahul@gmail.com','rahul','','','pawar','single','78965412236','sale ','Any Religion','2000-08-12',8,NULL),(27,'mai apne papa ki pari hoon','akola','MCA','rupalidhakite@gmail.com','rupali','Female','580000','dhakite','single','9632587412','Developer','','2000-05-04',9,NULL),(28,'i am lawyer','warora','llb','komalumare@gmail.com','komal','Female','1000000','umare','single','8523697412','lawyer','','2000-03-17',10,NULL),(29,'i am businessman','warora','m.com','ravivarma@gmail.com','ravi','','580000','varma','single','8547963215','business','Any Religion','1998-05-12',11,NULL),(30,'Adventurer','nashik','MBA','purviwagh@gmail.com','purvi','Female','450000','wagh','single','6589321478','business','','1995-08-04',12,NULL),(31,'Adventurer','nagpur','MCA','','yash','','850000','menkudle','single','7854963256','Developer','','2000-09-15',13,NULL),(32,'Hindu Man','Warora','BE','shami@gmail.com','shami','','890000','maloo','single','8547123698','business','','1993-07-08',14,NULL),(34,'akskjnawld fasfnwl ','Bhushan photo studio','MBBS','dolly@123.com','Dolly','Female','3990099','Matte','Single','643452333','Doctor','Hindu','1995-10-11',18,NULL),(35,'I am a doctor','warora','MBBS','harshuds2','Harshu','Female','199938','Matte','single','34298387','Doctor','Hindu','1994-03-16',19,NULL),(36,'dyvasyddgasdb','pune','MCA','nikhil101@gmail.com','nikhil','Male','2000000','mahajan','single','9822458745','Developer','Hindu','2000-01-04',20,NULL),(37,'i am good doctor','kolhapur','MBBS','vishmane@gmail.com','vishu','Male','1000000000','mane','single','1236547896','Doctor','Hindu','2001-02-10',21,NULL);
/*!40000 ALTER TABLE `matrimony_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-08 14:54:46
