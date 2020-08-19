CREATE DATABASE  IF NOT EXISTS `HRM` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `HRM`;

/*Create the Admin User*/
DROP USER IF EXISTS 'admin_hrm'@'localhost';
CREATE USER 'admin_hrm'@'localhost' IDENTIFIED BY '4g&<ic4HVR';
GRANT ALL PRIVILEGES ON HRM.* TO 'admin_hrm'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: HRM
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `middle_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `full_name` varchar(75) NOT NULL,
  `dob` date NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `marital_status` enum('Single','Married','Divorced','Widowed') NOT NULL,
  `mobile_phone` varchar(20) NOT NULL,
  `joined_date` date NOT NULL,
  `terminated_date` date DEFAULT NULL,
  `job_title` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `supervisor_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'E000000001','Henry','','Smith','Henry Smith','1980-05-10','Male','Single','8213253815','2020-05-29','2999-12-31','Systems Administrator','Development',2),(2,'E000000002','Arthur','','Jones','Arthur Jones','1970-01-29','Male','Single','8321787715','2020-05-29','2999-12-31','Manager','Administration',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `errors`
--

DROP TABLE IF EXISTS `errors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `errors` (
  `error_id` int(11) NOT NULL AUTO_INCREMENT,
  `error_description` varchar(255) NOT NULL,
  `error_package` varchar(30) NOT NULL,
  `error_class` varchar(30) NOT NULL,
  `error_method` varchar(200) NOT NULL,
  `error_type` varchar(10) NOT NULL,
  PRIMARY KEY (`error_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `errors`
--

LOCK TABLES `errors` WRITE;
/*!40000 ALTER TABLE `errors` DISABLE KEYS */;
/*!40000 ALTER TABLE `errors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `modules` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) NOT NULL,
  `submodule_name` varchar(50) DEFAULT NULL,
  `user_level` enum('Admin','Manager','Employee') NOT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modules`
--

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
INSERT INTO `modules` VALUES (1,'Employees',NULL,'Admin'),(2,'Employees',NULL,'Manager'),(3,'Users',NULL,'Admin'),(4,'Travel Requests','Change Status','Admin'),(5,'Travel Requests','Change Status','Manager'),(6,'Travel Requests',NULL,'Employee');
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel_requests`
--

DROP TABLE IF EXISTS `travel_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `travel_requests` (
  `travel_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `transportation` enum('Airplane','Train','Taxi','OwnVehicle','RentedVehicle') NOT NULL,
  `purpose` varchar(30) NOT NULL,
  `travel_from` varchar(50) NOT NULL,
  `travel_to` varchar(50) NOT NULL,
  `travel_date` date NOT NULL,
  `return_date` date NOT NULL,
  `notes` varchar(50) DEFAULT NULL,
  `currency` enum('Euros','PoundSterling','AmericanDollars','CanadianDollars','MexicanPesos') NOT NULL,
  `total_funding` float(10,2) NOT NULL,
  `status` enum('Pending','Approved','Rejected') NOT NULL,
  `status_notes` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`travel_request_id`),
  KEY `fk_emp_2` (`employee_id`),
  CONSTRAINT `fk_emp_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_requests`
--

LOCK TABLES `travel_requests` WRITE;
/*!40000 ALTER TABLE `travel_requests` DISABLE KEYS */;
INSERT INTO `travel_requests` VALUES (2,'T000000001',1,'Airplane','Business','Madrid, Spain','Rome, Italy','2020-05-31','2020-06-04','','Euros',1500.00,'Approved','Business Trip Purpose Reviewed.'),(3,'T000000002',1,'Airplane','Business','Dublin, Ireland','Madrid, Spain','2020-07-28','2020-07-29','','Euros',500.00,'Approved','Travel Request Approved.'),(4,'T000000003',1,'Airplane','business','dublin, ireland','madrid, spain','2020-07-28','2020-07-29','','Euros',500.00,'Approved','TR approved');
/*!40000 ALTER TABLE `travel_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` blob NOT NULL,
  `key_string` blob NOT NULL,
  `email` varchar(50) NOT NULL,
  `user_level` enum('Admin','Manager','Employee') NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_emp_1` (`employee_id`),
  CONSTRAINT `fk_emp_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
insert into users(`user_id`, `username`, `password`, `key_string`, `email`, `user_level`, `employee_id`) 
values (1, 'hsmith', aes_encrypt('12345', unhex(sha2('12345',512))), unhex(sha2('12345',512)), 'hsmith@hotmail.com', 'Admin', 1);

insert into users(`user_id`, `username`, `password`, `key_string`, `email`, `user_level`, `employee_id`) 
values (2, 'ajones', aes_encrypt('123', unhex(sha2('123',512))), unhex(sha2('123',512)), 'ajones@hotmail.com', 'Manager', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'HRM'
--

--
-- Dumping routines for database 'HRM'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-30 13:37:46
