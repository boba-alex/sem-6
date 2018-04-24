CREATE DATABASE  IF NOT EXISTS `receipts_database` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `receipts_database`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: receipts_database
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receipt_service_id` int(10) DEFAULT NULL,
  `date_of_receipt_service` date DEFAULT NULL,
  `receipt_customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receipt_customer_idx` (`receipt_customer_id`),
  KEY `fk_service_idx` (`receipt_service_id`),
  CONSTRAINT `fk_receipt_customer` FOREIGN KEY (`receipt_customer_id`) REFERENCES `receipt_customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_service` FOREIGN KEY (`receipt_service_id`) REFERENCES `receipt_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (2,1,'2018-02-14',1),(3,1,'2018-02-15',1),(5,1,'2017-12-31',1),(6,1,'2018-03-31',1),(7,1,'2018-04-01',1),(9,1,'0002-02-02',1),(10,1,'0002-02-02',1),(11,1,'0003-03-23',1);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_customer`
--

DROP TABLE IF EXISTS `receipt_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_customer`
--

LOCK TABLES `receipt_customer` WRITE;
/*!40000 ALTER TABLE `receipt_customer` DISABLE KEYS */;
INSERT INTO `receipt_customer` VALUES (1,'Ivan','Bolny'),(2,'Lala','Vava');
/*!40000 ALTER TABLE `receipt_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_service`
--

DROP TABLE IF EXISTS `receipt_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_service`
--

LOCK TABLES `receipt_service` WRITE;
/*!40000 ALTER TABLE `receipt_service` DISABLE KEYS */;
INSERT INTO `receipt_service` VALUES (1,'REPAIR PRODUCTS'),(2,'ARTISTIC DARNING'),(3,'FITTING PRODUCTS ON THE FIGURE'),(4,'KNITTING'),(5,'SEWING PRODUCTS'),(6,'THE DESIGN AND TAILORING OF CURTAINS'),(7,'REPAIR DRESSES');
/*!40000 ALTER TABLE `receipt_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'receipts_database'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-21  1:44:10
