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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (3,1,'2018-02-15',1),(5,1,'2017-12-31',1),(14,5,'0001-01-01',5),(15,5,'0001-01-01',5),(16,5,'0001-01-01',5),(17,5,'0001-01-01',5),(18,5,'0001-01-01',5),(19,5,'0001-01-01',5),(20,5,'0001-01-01',5),(22,1,'0001-01-01',1),(23,1,'0001-01-01',1),(24,1,'0001-01-01',1),(26,1,'0001-01-01',1),(27,1,'0001-01-01',1),(28,1,'0001-01-01',1),(29,1,'0001-01-01',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_customer`
--

LOCK TABLES `receipt_customer` WRITE;
/*!40000 ALTER TABLE `receipt_customer` DISABLE KEYS */;
INSERT INTO `receipt_customer` VALUES (1,'Ivan','Bolny'),(2,'Lala','Vava'),(3,'eee','eee'),(4,'eee','eee'),(5,'algerd','byk'),(6,'sasha','good'),(7,'Vadzim','Urievich'),(8,'sasha','sasha'),(9,'sasha','sasha'),(10,'asdas','asdsad');
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 23:19:43
