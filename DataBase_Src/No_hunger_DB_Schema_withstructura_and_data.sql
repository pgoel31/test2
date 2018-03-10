CREATE DATABASE  IF NOT EXISTS `world` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `world`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: world
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `donation_dtls`
--

DROP TABLE IF EXISTS `donation_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donation_dtls` (
  `Donation_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `Contact_Number` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Email_Id` varchar(255) DEFAULT NULL,
  `Item_Food` varchar(255) DEFAULT NULL,
  `Item_Clothing` varchar(255) DEFAULT NULL,
  `Item_HouseHold` varchar(255) DEFAULT NULL,
  `Other_Item_dtl` varchar(255) DEFAULT NULL,
  `Enquired` varchar(10) DEFAULT NULL,
  `Still_Available` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Donation_id`),
  KEY `User_id` (`User_id`),
  CONSTRAINT `donation_dtls_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `login_details` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation_dtls`
--

LOCK TABLES `donation_dtls` WRITE;
/*!40000 ALTER TABLE `donation_dtls` DISABLE KEYS */;
INSERT INTO `donation_dtls` VALUES (1,8,'VIpul','9873222222','Pune','Add',NULL,'N','Y','N','Medicines',NULL,'N'),(2,8,'Devendra','9004004653','Mumbai','Mumbai',NULL,'Y','N','N','Toys',NULL,'N'),(3,9,'Rahul','9873620774','Pune','D-168, Sec-23, Sanjay Nagar, Ghaziabad',NULL,'Y','N','N','Groceries',NULL,'Y'),(4,9,'Mohit','9873620774','Haryana','Gurgaon DLF cyber city',NULL,'Y','N','N','TV',NULL,'Y'),(5,9,'Mohin','9873620774','Pune','EON IT PARK Pune',NULL,'Y','N','N','Sweets',NULL,'Y'),(6,9,'Reshab','9999999999','Pune','Viman nagar Pune',NULL,'Y','N','N','Cake',NULL,'Y'),(7,9,'SS','9999999900','pUNE','Women helpline Pune',NULL,'Y','N','N','Shoes',NULL,'Y'),(8,8,'Test','9876789988','Delhi','Ram monohar lohiya hospital',NULL,'Y','N','N','Blankets',NULL,'N'),(9,8,'Juice shop','9087665555','Mumb','Mumbai',NULL,'Y','N','N','Books',NULL,'Y'),(13,8,'Oters_testing','9875777777','Pune','Others_address',NULL,'Y','N','N','Furniture',NULL,'Y');
/*!40000 ALTER TABLE `donation_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_details` (
  `User_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Contact_Number` varchar(255) NOT NULL,
  `Password` varchar(255) CHARACTER SET big5 DEFAULT NULL,
  PRIMARY KEY (`User_id`),
  UNIQUE KEY `Contact_Number_UNIQUE` (`Contact_Number`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES (5,'Vipul Goel','9988776655','9988776655'),(8,'priya','9873620774','9873620774'),(9,'abc','9090909090','9090909090'),(11,'vb','9920131766','9920131766'),(13,'Vipul Bansal','9920131764','9920131764'),(15,'Priya Goel','9873620744','priyagoel'),(16,'Priya Goel','9873620700','priyagoel');
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'world'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-10 14:12:52
