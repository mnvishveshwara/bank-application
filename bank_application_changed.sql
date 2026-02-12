-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: bank_application_changed
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `admin_bank_mapping`
--

DROP TABLE IF EXISTS `admin_bank_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_bank_mapping` (
  `admin_id` varchar(12) NOT NULL,
  `bank_id` bigint NOT NULL,
  PRIMARY KEY (`admin_id`,`bank_id`),
  KEY `FKc7o5x2bi1efrup27epx5pp9k` (`bank_id`),
  CONSTRAINT `FK8e2adnlw1cpkge3xbfkvb7mmn` FOREIGN KEY (`admin_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKc7o5x2bi1efrup27epx5pp9k` FOREIGN KEY (`bank_id`) REFERENCES `bank_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_bank_mapping`
--

LOCK TABLES `admin_bank_mapping` WRITE;
/*!40000 ALTER TABLE `admin_bank_mapping` DISABLE KEYS */;
INSERT INTO `admin_bank_mapping` VALUES ('ADM5Y2AAUFLH',1),('ADM9U8EX0T4G',1),('ADMIEGG0HVZ1',1),('AGNF6K73481J',1),('AGNLJTVYQRCC',1),('GENH8SNGFTQN',1),('ADM5Y2AAUFLH',2),('ADM9U8EX0T4G',2),('ADMIEGG0HVZ1',2),('AGNF6K73481J',2),('ADM9U8EX0T4G',3),('AGNF6K73481J',3),('AGNLJTVYQRCC',3),('GEN212KQVKN1',3),('ADM9U8EX0T4G',4),('AGNLJTVYQRCC',9),('GENKHXLT7DEX',9);
/*!40000 ALTER TABLE `admin_bank_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_master`
--

DROP TABLE IF EXISTS `admin_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_master` (
  `id` varchar(12) NOT NULL,
  `agency_id` bigint DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','AGENCY_VALUATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8yh1c2wc8lv1tt8m5q65gat8d` (`email`),
  KEY `FKbpldrktbp5gmwl6sbyp05wk2b` (`agency_id`),
  CONSTRAINT `FKbpldrktbp5gmwl6sbyp05wk2b` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_master`
--

LOCK TABLES `admin_master` WRITE;
/*!40000 ALTER TABLE `admin_master` DISABLE KEYS */;
INSERT INTO `admin_master` VALUES ('ADM5Y2AAUFLH',NULL,'2026-01-27 07:03:46.864574','admin1@gmail.com','Vishvesh','MN','$2a$10$r7gdllEjSxszUdV9Etw3FeMMZj/6MEbJZhFrRREhSG2sMcDLip46a','9876543210','ADMIN'),('ADM9U8EX0T4G',NULL,'2026-01-27 07:06:41.944015','superadmin@gmail.com','Super','Admin','$2a$10$aK0vAta6pgh3EDghAJ9m.eV.0JI97L/HbK4uehdKGAS.l9etKL7hu','8888888888','ADMIN'),('ADMIEGG0HVZ1',NULL,'2026-01-27 07:04:20.961740','admin2@gmail.com','Vishveshwara','MN','$2a$10$VvHV0nXQSC544hTlVgISv.rCk4hvP0JsHQBSAqWJBUmSBYu6sK4Z.','9876543218','ADMIN'),('AGNF6K73481J',1,'2026-01-27 07:13:59.496853','primeagency@gmail.com','Ramesh Kumar','','$2a$10$g9Je2ZURDln1Qx9twjLTkefIuk3k0wfvpGGAy8vU1p9A9PDGEkwNy','9876543210','AGENCY'),('AGNLJTVYQRCC',2,'2026-02-06 11:05:06.776097','securehome.agency@gmail.com','Anita Sharma','','$2a$10$wvTmnlqwINZIRrYgK7JgIuGGCeO3l7BZXogXrGPBUOsqj/x7nilem','9123456789','AGENCY'),('GEN212KQVKN1',NULL,'2026-01-27 07:05:45.938008','sbi.manager@gmail.com','SBI','Manager','$2a$10$f7zl8l02JO1vZPaBh97zO.dmuGCe1VZygAQH50fSs/X6Jcl54HEkO','9999999999','MANAGER'),('GENH8SNGFTQN',NULL,'2026-01-29 07:04:14.339348','hdfcbank@gmail.com','HDFC','Bank Admin','$2a$10$YMf8o1GUhGzbXDeHfyjBEO387PtKolW6Ry.oZrOx5CTNnoNjGW7hO','9876543210','MANAGER'),('GENKHXLT7DEX',NULL,'2026-02-06 10:52:17.219656','unionbank@gmail.com','Union','Bank','$2a$10$F68lUXSooboofBS8h2Qwp.PgcLDo7BgXnwce/DCKzqEPFrlQGvj2y','8095380158','MANAGER'),('VAL1LU6QP2HM',1,'2026-02-06 09:59:27.705102','ammu@gmail.com','Ammu','vishwa','$2a$10$97fLEatqrC3XBJZhwnJGGeCBscMbxm24QWZRXBr8AQq22vj2yx19m','8050139422','AGENCY_VALUATOR'),('VAL714J0RACR',1,'2026-01-28 06:53:03.319786','rahul.sharma@gmail.com','Rahul','Sharma','$2a$10$MM/BQtpUcTzs27sBpoLo4.5MxI97dIHkTVkoI0NwwpvN7APPNpnM.','9876543210','AGENCY_VALUATOR'),('VAL8YYPOME3K',2,'2026-02-06 11:09:42.202967','rahul.verma@securehome.com','Rahul','Verma','$2a$10$V3B7dMLit5QGq9NKSB3SEOYSqKIq/xVXPgQmqnIfBixkiBmv1YP/y','9988776655','AGENCY_VALUATOR'),('VALD8D5T3QUZ',1,'2026-02-05 12:20:28.748635','abhi@gmail.com','Abhi','Kumar','$2a$10$C4PJyHUZWzUkZelS3a0dFe0Z6fRhnU3LGZ.sU2Tigd1I9HvY5Ggd6','8576958468','AGENCY_VALUATOR'),('VALG2YPK6CYN',1,'2026-01-28 07:34:18.554094','dhoni@gmail.com','Mahendra','Singh Dhoni','$2a$10$vOcLRwozXgBMgqw0kQldp.Gk8elFgfW2OZVfmAeWXYROjHmpRIJbi','8579685466','AGENCY_VALUATOR'),('VALGSKGGARUM',1,'2026-01-28 07:08:46.744130','rohith.sharma@gmail.com','Rohith','Sharma','$2a$10$OdAUK3eckY8L58cl4hsbAe//iQOygR6xIOsttFEc7LttWGtYw3VCS','9876543212','AGENCY_VALUATOR'),('VALIXZUOJPP0',1,'2026-01-28 07:37:23.892080','virat@gmail.com','Virat','Kohli','$2a$10$1r2l.BvzJVW.Dxf57FVEPuwKBkGEvOPuTlP4Tv5BdeuCqxqZbvXKS','7584968755','AGENCY_VALUATOR'),('VALK2TZPT41S',1,'2026-02-03 10:29:13.233720','nagesh@gmail.com','nagesh','u','$2a$10$H6.Mh4He2hYhSkdmi4TxveUBEgzB5q2Qu/A.w4PQQrPA.XQa6qN1O','9059521841','AGENCY_VALUATOR'),('VALTMBC2N4IS',1,'2026-02-06 11:09:46.736315','siddu@gmail.com','siddu','h','$2a$10$c1NZYRx6Pv4wopdjKI6Ylu2vZxcdJobuLhB.9oeM5NLbhitKujOOC','9972216772','AGENCY_VALUATOR');
/*!40000 ALTER TABLE `admin_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_bank_mapping`
--

DROP TABLE IF EXISTS `agency_bank_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_bank_mapping` (
  `agency_id` bigint NOT NULL,
  `bank_id` bigint NOT NULL,
  PRIMARY KEY (`agency_id`,`bank_id`),
  KEY `FKd77j7isp0nqhnp6neijaps5dd` (`bank_id`),
  CONSTRAINT `FK9fvxjmp66e75ksgufa4shbv1i` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`),
  CONSTRAINT `FKd77j7isp0nqhnp6neijaps5dd` FOREIGN KEY (`bank_id`) REFERENCES `bank_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_bank_mapping`
--

LOCK TABLES `agency_bank_mapping` WRITE;
/*!40000 ALTER TABLE `agency_bank_mapping` DISABLE KEYS */;
INSERT INTO `agency_bank_mapping` VALUES (1,1),(2,1),(1,2),(1,3),(2,3),(2,9);
/*!40000 ALTER TABLE `agency_bank_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_master`
--

DROP TABLE IF EXISTS `agency_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agency_name` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `map_url` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street_line_1` varchar(255) DEFAULT NULL,
  `street_line_2` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(12) DEFAULT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK783n8d7bqsmttrp0nmqk6a9tr` (`agency_name`),
  KEY `FK2qw3jee28001cdnjiwl0m0l85` (`created_by`),
  KEY `FKqgw1gn8iasugbl38vw2r9bn5b` (`updated_by`),
  CONSTRAINT `FK2qw3jee28001cdnjiwl0m0l85` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKqgw1gn8iasugbl38vw2r9bn5b` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_master`
--

LOCK TABLES `agency_master` WRITE;
/*!40000 ALTER TABLE `agency_master` DISABLE KEYS */;
INSERT INTO `agency_master` VALUES (1,'Prime Property Valuation','Bangalore','Ramesh Kumar','9876543210','2026-01-27 07:13:59.367825',12.9716,77.5946,'https://maps.google.com/?q=12.9716,77.5946','560001','Karnataka','No.45, MG Road','Near Metro Station','2026-01-27 07:13:59.367825','ADM9U8EX0T4G','ADM9U8EX0T4G'),(2,'Secure Home Valuations','Hyderabad','Anita Sharma','9123456789','2026-02-06 11:05:06.703102',17.4483,78.3915,'https://maps.google.com/?q=17.4483,78.3915','500081','Telangana','3rd Floor, Omega Towers','Opposite Tech Park','2026-02-06 11:05:06.703102','ADM9U8EX0T4G','ADM9U8EX0T4G');
/*!40000 ALTER TABLE `agency_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_agency_assignment`
--

DROP TABLE IF EXISTS `application_agency_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_agency_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `agency_id` bigint NOT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKo7ui04p9qoxafqo3d4rvs0bg3` (`application_id`),
  KEY `FKrik187xwno6xldb3xw5oe7b4l` (`agency_id`),
  KEY `FKg62bkhou7mpctsq9vqirwq6tt` (`created_by_id`),
  KEY `FK3y826nahijs2ey0cs4bitdcxx` (`updated_by_id`),
  CONSTRAINT `FK3y826nahijs2ey0cs4bitdcxx` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKg62bkhou7mpctsq9vqirwq6tt` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKqak8xnu6oufset718k8w2752i` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKrik187xwno6xldb3xw5oe7b4l` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_agency_assignment`
--

LOCK TABLES `application_agency_assignment` WRITE;
/*!40000 ALTER TABLE `application_agency_assignment` DISABLE KEYS */;
INSERT INTO `application_agency_assignment` VALUES (1,'2026-01-27 12:44:20.096251','ntng','2026-01-27 12:53:12.709733',1,'GENJU0W6K659','ADM9U8EX0T4G','ADM9U8EX0T4G'),(2,'2026-01-28 07:06:57.272951','please check the application','2026-01-28 07:06:57.272951',1,'GENGTDTP9B6E','ADM9U8EX0T4G','ADM9U8EX0T4G'),(3,'2026-01-28 10:08:18.113766','complete the application','2026-02-03 10:01:30.549447',1,'GENC18YQ7W22','ADM9U8EX0T4G','GENH8SNGFTQN'),(4,'2026-01-29 10:23:23.740304','validate ','2026-01-29 10:23:23.740304',1,'GENITHG4GA6F','GENH8SNGFTQN','GENH8SNGFTQN'),(5,'2026-01-29 11:00:07.243554','validate the application','2026-01-29 11:00:07.243554',1,'GENG0WS5JIRE','GENH8SNGFTQN','GENH8SNGFTQN'),(6,'2026-02-02 05:47:58.966120','azazaz','2026-02-02 05:47:58.966120',1,'GENUO9MXXG32','ADM9U8EX0T4G','ADM9U8EX0T4G'),(7,'2026-02-02 07:11:14.042169','ddd','2026-02-02 07:11:14.042169',1,'GEN2FN3SSEIM','ADM9U8EX0T4G','ADM9U8EX0T4G'),(8,'2026-02-02 11:12:18.726567','sale','2026-02-02 11:12:18.726567',1,'GEN2NNSZBW8C','ADM9U8EX0T4G','ADM9U8EX0T4G'),(9,'2026-02-03 10:15:09.423825','loan','2026-02-03 10:15:09.423825',1,'GENTJ9ZCHTZQ','GENH8SNGFTQN','GENH8SNGFTQN'),(10,'2026-02-05 11:53:11.017185','yes','2026-02-05 11:53:11.017185',1,'GENPXYYUDB9Y','GEN212KQVKN1','GEN212KQVKN1'),(11,'2026-02-05 12:38:15.688553','yes','2026-02-05 12:38:15.688553',1,'GENV8RSMDROU','GEN212KQVKN1','GEN212KQVKN1'),(12,'2026-02-06 11:07:18.793013','secure assigned','2026-02-06 11:07:18.793013',2,'GENLAEZT649C','ADM9U8EX0T4G','ADM9U8EX0T4G');
/*!40000 ALTER TABLE `application_agency_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_customer_details`
--

DROP TABLE IF EXISTS `application_customer_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_customer_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bank_id` bigint NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `lead_source` varchar(255) DEFAULT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `primary_contact_number` varchar(255) DEFAULT NULL,
  `property_reference_no` varchar(255) DEFAULT NULL,
  `property_sub_type` varchar(255) DEFAULT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `secondary_contact_number` varchar(255) DEFAULT NULL,
  `spock_name` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2n7si84ma0xs5naoornbmu6sg` (`application_id`),
  KEY `FKi4f0v9ibs4bkeasgw37xe9243` (`bank_id`),
  KEY `FK658xwmohb3ttod51svm98lln0` (`created_by_id`),
  KEY `FKsqgr54r8rcr9qhqhhn1f8t281` (`updated_by_id`),
  CONSTRAINT `FK658xwmohb3ttod51svm98lln0` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKgnkja65d1epkslgpywla5aspk` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKi4f0v9ibs4bkeasgw37xe9243` FOREIGN KEY (`bank_id`) REFERENCES `bank_master` (`id`),
  CONSTRAINT `FKsqgr54r8rcr9qhqhhn1f8t281` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_customer_details`
--

LOCK TABLES `application_customer_details` WRITE;
/*!40000 ALTER TABLE `application_customer_details` DISABLE KEYS */;
INSERT INTO `application_customer_details` VALUES (1,3,'2026-01-27 08:54:45.387538','customer@gmail.com','customer','One','Direct','Home Loan','','8659766692','856970','Residential Independent Building','Building','remarks','','Murthy R','2026-01-27 10:19:31.917384','GENAHL9SUHEK','ADM9U8EX0T4G','ADM9U8EX0T4G'),(2,2,'2026-01-27 08:55:46.892625','customer@gmail.com','customer','One','Direct','Home Loan','','8659766692','856970','Residential Independent Building','Building','remarks','','Murthy R','2026-01-27 08:55:46.892625','GENSPFQKR6M2','ADM9U8EX0T4G','ADM9U8EX0T4G'),(3,2,'2026-01-27 08:57:21.788257','customer@gmail.com','customer','One','Direct','Home Loan','','8659766692','856970','Residential Independent Building','Building','remarks','','Murthy R','2026-01-27 08:57:21.788257','GEN9ELO87YK6','ADM9U8EX0T4G','ADM9U8EX0T4G'),(4,2,'2026-01-27 09:09:40.487255','customer@gmail.com','customer','One','Direct','Home Loan','','8659766692','856970','Residential Independent Building','Building','remarks','','Murthy R','2026-01-27 09:09:40.487255','GENWCG4OK5UQ','ADM9U8EX0T4G','ADM9U8EX0T4G'),(5,1,'2026-01-27 09:14:23.849704','dd@gmail.com','uyds','s','Direct','LAP','','8888408985','857695','Residential Independent Building','Building','fghjk,.','','Murthy R','2026-01-28 07:05:46.229055','GENGTDTP9B6E','ADM9U8EX0T4G','ADM9U8EX0T4G'),(6,2,'2026-01-27 09:16:13.793074','s@gmail.com','lol','sq','Direct','LAP','','7584968585','754952','Residential Independent Building','Building','remarks','','Suresh K','2026-01-27 12:23:51.077465','GENJU0W6K659','ADM9U8EX0T4G','ADM9U8EX0T4G'),(7,1,'2026-01-28 10:07:30.235600','s@gmail.com','s','s','Agent','LAP','','5557382835','7352','Residential Independent Building','Building','dfcd','','Suresh K','2026-01-28 10:07:30.235600','GENC18YQ7W22','ADM9U8EX0T4G','ADM9U8EX0T4G'),(8,1,'2026-01-29 10:21:10.075742','newuser@gmail.com','new','user','Agent','LAP','','7348820425','7864','Residential Independent Building','Building','remarks','','Murthy R','2026-01-29 10:21:10.075742','GENITHG4GA6F','GENH8SNGFTQN','GENH8SNGFTQN'),(9,1,'2026-01-29 10:59:15.850068','newuser@gmail.com','new','user','Agent','LAP','','7348820425','788420','Residential Independent Building','Building','remarks','','Murthy R','2026-01-29 10:59:15.850068','GENG0WS5JIRE','GENH8SNGFTQN','GENH8SNGFTQN'),(10,1,'2026-02-02 05:46:40.025929','pramodvc7204@gmail.com','Pramod','V C','Direct','Home Loan','','9353422469','778779','Apartment','Building','szaxza','','Murthy R','2026-02-02 05:46:40.025929','GENUO9MXXG32','ADM9U8EX0T4G','ADM9U8EX0T4G'),(11,3,'2026-02-02 07:10:39.237886','vish@gmail.com','vish','mn','Direct','LAP','','8579648562','852963','Residential Independent Building','Building','good','','Suresh K','2026-02-04 07:15:59.105536','GEN2FN3SSEIM','ADM9U8EX0T4G','GEN212KQVKN1'),(12,3,'2026-02-02 11:08:29.857572','siddu@gmail.com','siddu','k','Direct','LAP','h','9972216772','101098','Residential Independent Building','Building','no','','Suresh K','2026-02-02 11:08:29.857572','GEN2NNSZBW8C','ADM9U8EX0T4G','ADM9U8EX0T4G'),(13,1,'2026-02-03 10:07:37.423087','nagesh@gmail.com','urukundhappa','nagesh','Agent','LAP','gari','9059521841','4435fdfgg','Apartment','Building','noo','9052050345','Murthy R','2026-02-03 10:07:37.423087','GENTJ9ZCHTZQ','GENH8SNGFTQN','GENH8SNGFTQN'),(14,1,'2026-02-04 09:52:44.711904','s@gmail.com','q','d','Direct','Home Loan','d','7965466566','465633','Residential Independent Building','Building','cec','','Murthy R','2026-02-04 09:52:44.711904','GENMGVSDJC1U','GENH8SNGFTQN','GENH8SNGFTQN'),(15,1,'2026-02-04 09:53:34.263450','c@gmail.com','cec','cc','Agent','LAP','','8572242525','322','Non RCC','Building','cfc','','Suresh K','2026-02-04 09:53:34.263450','GENCFI8CWSZH','GENH8SNGFTQN','GENH8SNGFTQN'),(16,3,'2026-02-05 05:37:52.272806','g@gmail.com','fsdf','gr','Agent','Home Loan','','7846531256','656852','Residential Independent Building','Land','frefe','','Suresh K','2026-02-05 05:37:52.272806','GENWRUVPU8U2','ADM9U8EX0T4G','ADM9U8EX0T4G'),(17,3,'2026-02-05 11:50:26.124029','siddu@gmail.com','ssss','k','Online','Home Loan','h','9972216772','12345','Residential Independent Building','Building','yes','','Suresh K','2026-02-05 11:50:26.124029','GENPXYYUDB9Y','GEN212KQVKN1','GEN212KQVKN1'),(18,3,'2026-02-05 12:36:17.554609','vinod@gmail.com','vinod','N','Direct','LAP','','9663856412','12345','Residential Independent Building','Building','yes','','Murthy R','2026-02-05 12:36:17.554609','GENV8RSMDROU','GEN212KQVKN1','GEN212KQVKN1'),(19,3,'2026-02-06 11:06:03.883662','fd@gmail.com','dc','cec','Direct','LAP','','9782934250','65','Commercial','Building','fc','','Murthy R','2026-02-06 11:06:03.883662','GENLAEZT649C','ADM9U8EX0T4G','ADM9U8EX0T4G'),(20,9,'2026-02-06 11:15:16.464858','khkjhk@gmail.com','jh','hk','Agent','Home Loan','jhk','7357544540','785412','Apartment','Building','hnhn','','Suresh K','2026-02-06 11:15:16.464858','GENKCPS3MG4G','GENKHXLT7DEX','GENKHXLT7DEX');
/*!40000 ALTER TABLE `application_customer_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_document_details`
--

DROP TABLE IF EXISTS `application_document_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_document_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nxt1jipi0tj06m4wbh4s9i8hy` (`application_id`),
  CONSTRAINT `FK2bmpcba07ebyoq1nuubau8wdq` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_document_details`
--

LOCK TABLES `application_document_details` WRITE;
/*!40000 ALTER TABLE `application_document_details` DISABLE KEYS */;
INSERT INTO `application_document_details` VALUES (7,'GEN2FN3SSEIM'),(8,'GEN2NNSZBW8C'),(3,'GENC18YQ7W22'),(5,'GENG0WS5JIRE'),(2,'GENGTDTP9B6E'),(4,'GENITHG4GA6F'),(1,'GENJU0W6K659'),(12,'GENLAEZT649C'),(10,'GENPXYYUDB9Y'),(9,'GENTJ9ZCHTZQ'),(6,'GENUO9MXXG32'),(11,'GENV8RSMDROU');
/*!40000 ALTER TABLE `application_document_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_master`
--

DROP TABLE IF EXISTS `application_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_master` (
  `id` varchar(12) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `agency_remarks` varchar(1000) DEFAULT NULL,
  `bank_id` bigint NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `planned_site_visit_date` date DEFAULT NULL,
  `resched_site_visit_date` date DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `assigned_to_id` varchar(12) DEFAULT NULL,
  `client_id` varchar(12) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `reassigned_valuator_id` varchar(12) DEFAULT NULL,
  `valuator_id` varchar(12) DEFAULT NULL,
  `status` enum('CUSTOMER_DETAILS','PROPERTY_DETAILS','DOCUMENTS_UPLOADED','ASSIGN_AGENCY','APPLICATION_APPLIED') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5via16bmxxw4f1u6tt1iivj1o` (`assigned_to_id`),
  KEY `FKquqfnyp262e2exotdc9ln3rfs` (`bank_id`),
  KEY `FK3i1wa7yaq1aotlrqmvw02on44` (`client_id`),
  KEY `FKnyxvhlv92mqgjeglnc0mgwht3` (`created_by_id`),
  KEY `FKeyxi4emt2wg0vv5dnk4cr4iym` (`reassigned_valuator_id`),
  KEY `FK6eln6s9dejl6mj18jvedtou1k` (`valuator_id`),
  CONSTRAINT `FK3i1wa7yaq1aotlrqmvw02on44` FOREIGN KEY (`client_id`) REFERENCES `customer_master` (`id`),
  CONSTRAINT `FK5via16bmxxw4f1u6tt1iivj1o` FOREIGN KEY (`assigned_to_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK6eln6s9dejl6mj18jvedtou1k` FOREIGN KEY (`valuator_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKeyxi4emt2wg0vv5dnk4cr4iym` FOREIGN KEY (`reassigned_valuator_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKnyxvhlv92mqgjeglnc0mgwht3` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKquqfnyp262e2exotdc9ln3rfs` FOREIGN KEY (`bank_id`) REFERENCES `bank_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_master`
--

LOCK TABLES `application_master` WRITE;
/*!40000 ALTER TABLE `application_master` DISABLE KEYS */;
INSERT INTO `application_master` VALUES ('GEN2FN3SSEIM',_binary '',NULL,3,'2026-02-02 07:10:39.130862',NULL,NULL,'2026-02-04 07:21:28.042541','AGNF6K73481J','GENZI7YPOWX4','ADM9U8EX0T4G',NULL,NULL,NULL),('GEN2NNSZBW8C',_binary '',NULL,3,'2026-02-02 11:08:29.767603',NULL,NULL,'2026-02-02 11:25:00.525198','AGNF6K73481J','GENHGP2FT1ZE','ADM9U8EX0T4G',NULL,'VALIXZUOJPP0',NULL),('GEN9ELO87YK6',_binary '',NULL,2,'2026-01-27 08:57:21.779261',NULL,NULL,'2026-01-27 08:57:21.784257',NULL,'GENOCWN7GX78','ADM9U8EX0T4G',NULL,NULL,NULL),('GENAHL9SUHEK',_binary '',NULL,2,'2026-01-27 08:54:45.236536',NULL,NULL,'2026-01-27 10:19:31.903382',NULL,'GENOCWN7GX78','ADM9U8EX0T4G',NULL,NULL,NULL),('GENC18YQ7W22',_binary '',NULL,1,'2026-01-28 10:07:30.169604',NULL,NULL,'2026-02-03 10:01:30.549447','AGNF6K73481J','GENTB2FJNAXR','ADM9U8EX0T4G',NULL,'VALIXZUOJPP0',NULL),('GENCFI8CWSZH',_binary '',NULL,1,'2026-02-04 09:53:34.149111',NULL,NULL,'2026-02-04 09:53:34.262261',NULL,'GENLMW27B4KH','GENH8SNGFTQN',NULL,NULL,NULL),('GENG0WS5JIRE',_binary '',NULL,1,'2026-01-29 10:59:15.845036',NULL,NULL,'2026-02-02 06:05:42.442781','AGNF6K73481J','GEN8RVHU5LT7','GENH8SNGFTQN',NULL,'VALIXZUOJPP0',NULL),('GENGTDTP9B6E',_binary '',NULL,1,'2026-01-27 09:14:23.766706',NULL,NULL,'2026-01-28 07:09:26.640527','AGNF6K73481J','GENI4YNRDQNY','ADM9U8EX0T4G',NULL,'VALGSKGGARUM',NULL),('GENITHG4GA6F',_binary '',NULL,1,'2026-01-29 10:21:09.915746',NULL,NULL,'2026-02-09 06:00:49.764679','AGNF6K73481J','GEN8RVHU5LT7','GENH8SNGFTQN',NULL,'VALIXZUOJPP0',NULL),('GENJU0W6K659',_binary '',NULL,2,'2026-01-27 09:16:13.691109',NULL,NULL,'2026-01-28 06:55:24.508735','AGNF6K73481J','GENTB2FJNAXR','ADM9U8EX0T4G',NULL,'VAL714J0RACR',NULL),('GENKCPS3MG4G',_binary '',NULL,9,'2026-02-06 11:15:16.380860',NULL,NULL,'2026-02-06 11:15:16.462858',NULL,'GENPUGZJM3VB','GENKHXLT7DEX',NULL,NULL,NULL),('GENLAEZT649C',_binary '',NULL,3,'2026-02-06 11:06:03.749655',NULL,NULL,'2026-02-06 11:09:54.875687','AGNLJTVYQRCC','GENY9Y0H63KF','ADM9U8EX0T4G',NULL,'VAL8YYPOME3K',NULL),('GENMGVSDJC1U',_binary '',NULL,1,'2026-02-04 09:52:44.678472',NULL,NULL,'2026-02-04 09:52:44.711904',NULL,'GENTB2FJNAXR','GENH8SNGFTQN',NULL,NULL,NULL),('GENPXYYUDB9Y',_binary '',NULL,3,'2026-02-05 11:50:26.045876',NULL,NULL,'2026-02-05 11:53:11.027425','AGNF6K73481J','GENHGP2FT1ZE','GEN212KQVKN1',NULL,NULL,NULL),('GENSPFQKR6M2',_binary '',NULL,2,'2026-01-27 08:55:46.876624',NULL,NULL,'2026-01-27 08:55:46.887627',NULL,'GENOCWN7GX78','ADM9U8EX0T4G',NULL,NULL,NULL),('GENTJ9ZCHTZQ',_binary '',NULL,1,'2026-02-03 10:07:37.293440',NULL,NULL,'2026-02-05 12:27:41.996035','AGNF6K73481J','GENIFHVHOK3S','GENH8SNGFTQN',NULL,'VALD8D5T3QUZ',NULL),('GENUO9MXXG32',_binary '',NULL,1,'2026-02-02 05:46:39.854056',NULL,NULL,'2026-02-04 10:44:10.942434','AGNF6K73481J','GENC7SUKT1WA','ADM9U8EX0T4G',NULL,NULL,NULL),('GENV8RSMDROU',_binary '',NULL,3,'2026-02-05 12:36:17.442403',NULL,NULL,'2026-02-05 12:38:15.694878','AGNF6K73481J','GENC06H8SIW7','GEN212KQVKN1',NULL,NULL,NULL),('GENWCG4OK5UQ',_binary '',NULL,2,'2026-01-27 09:09:40.478255',NULL,NULL,'2026-01-27 09:09:40.484255',NULL,'GENOCWN7GX78','ADM9U8EX0T4G',NULL,NULL,NULL),('GENWRUVPU8U2',_binary '',NULL,3,'2026-02-05 05:37:52.123326',NULL,NULL,'2026-02-05 05:37:52.239435',NULL,'GENG3POCXIZA','ADM9U8EX0T4G',NULL,NULL,NULL);
/*!40000 ALTER TABLE `application_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_property_details`
--

DROP TABLE IF EXISTS `application_property_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_property_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prop_building_or_apartment_name` varchar(255) DEFAULT NULL,
  `prop_city` varchar(255) DEFAULT NULL,
  `prop_country` varchar(255) DEFAULT NULL,
  `prop_door_or_apartment_number` varchar(255) DEFAULT NULL,
  `prop_pincode` varchar(255) DEFAULT NULL,
  `prop_state` varchar(255) DEFAULT NULL,
  `prop_street_line_1` varchar(255) DEFAULT NULL,
  `prop_street_line_2` varchar(255) DEFAULT NULL,
  `res_building_or_apartment_name` varchar(255) DEFAULT NULL,
  `res_city` varchar(255) DEFAULT NULL,
  `res_country` varchar(255) DEFAULT NULL,
  `res_door_or_apartment_number` varchar(255) DEFAULT NULL,
  `res_pincode` varchar(255) DEFAULT NULL,
  `res_state` varchar(255) DEFAULT NULL,
  `res_street_line_1` varchar(255) DEFAULT NULL,
  `res_street_line_2` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6g6nrt05td345vhy6d0mx0ji9` (`application_id`),
  CONSTRAINT `FK1ftmi4k1rq52ts9gv21a0evy6` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_property_details`
--

LOCK TABLES `application_property_details` WRITE;
/*!40000 ALTER TABLE `application_property_details` DISABLE KEYS */;
INSERT INTO `application_property_details` VALUES (1,'cfc','Tiptur','India','cfdc','572201','Karnataka','cfdc','','cfc','Tiptur','India','cf','572201','Karnataka','cfc','','GENJU0W6K659'),(2,'f','Turuvekere','India','s','572221','Karnataka','f','','jk','Turuvekere','India','hibk','572221','Karnataka','bk','kj','GENGTDTP9B6E'),(3,'dsc','Tiptur','India','dc','572201','Karnataka','cdc','cdec','c','Tiptur','India','dc','572201','Karnataka','dc','c','GENC18YQ7W22'),(4,'cefc','Tiptur','India','add','572201','Karnataka','str','','ww','Tiptur','India','ddd','572201','Karnataka','dsc','','GENITHG4GA6F'),(5,'address1','Tiptur','India','address1','572201','Karnataka','address1','','address1','Tiptur','India','address1','572201','Karnataka','address1','','GENG0WS5JIRE'),(6,'xx','Bangalore South','India','xx','560050','Karnataka','xx','11','22','Bangalore South','India','12','560050','Karnataka','Banglore','','GENUO9MXXG32'),(7,'d','Tiptur','India','d','572201','Karnataka','d','','s','Tiptur','India','s','572201','Karnataka','s','','GEN2FN3SSEIM'),(8,'home','Arsikere','India','#002','573144','Karnataka','near temple','','home','Arsikere','India','#002','573144','Karnataka','near temple','','GEN2NNSZBW8C'),(9,'ternekal','Kurnool','India','2/72','518302','Andhra Pradesh','ob','adoni','ternekal','Kurnool','India','2/72','518302','Andhra Pradesh','ob','adoni','GENTJ9ZCHTZQ'),(10,'home03','Tumkur','India','#003','572102','Karnataka','1 cross','4 th main','home03','Arsikere','India','#003','573144','Karnataka','1 cross','4 th main','GENPXYYUDB9Y'),(11,'home04','Tumkur','India','#004','572102','Karnataka','1 st cross','2 nd main','homev','Tumkur','India','#098','572102','Karnataka','2 nd cross','7 main','GENV8RSMDROU'),(12,'d','Tiptur','India','d','572201','Karnataka','cd','d','dc','Shaikpet','India','d','500081','Telangana','d','','GENLAEZT649C');
/*!40000 ALTER TABLE `application_property_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_stage_current`
--

DROP TABLE IF EXISTS `application_stage_current`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_stage_current` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `stage` enum('CUSTOMER_DETAILS','PROPERTY_DETAILS','DOCUMENTS_UPLOADED','ASSIGN_AGENCY','APPLICATION_APPLIED') NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by_id` varchar(12) DEFAULT NULL,
  `updated_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrqmc7v07g6524ntv53f010re` (`application_id`),
  KEY `FKsnrmgmp869y3k56n1irsk2485` (`created_by_id`),
  KEY `FKtpsct6oyf0vm72mm1nfhurd34` (`updated_by_id`),
  CONSTRAINT `FKsnrmgmp869y3k56n1irsk2485` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKsrqmc7v07g6524ntv53f010re` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKtpsct6oyf0vm72mm1nfhurd34` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_current`
--

LOCK TABLES `application_stage_current` WRITE;
/*!40000 ALTER TABLE `application_stage_current` DISABLE KEYS */;
INSERT INTO `application_stage_current` VALUES (1,'2026-01-27 08:54:45.418579','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-01-27 10:19:31.912384','GENAHL9SUHEK','ADM9U8EX0T4G','ADM9U8EX0T4G'),(2,'2026-01-27 08:55:46.902624','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-01-27 08:55:46.902624','GENSPFQKR6M2','ADM9U8EX0T4G','ADM9U8EX0T4G'),(3,'2026-01-27 08:57:21.797259','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-01-27 08:57:21.797259','GEN9ELO87YK6','ADM9U8EX0T4G','ADM9U8EX0T4G'),(4,'2026-01-27 09:09:40.493255','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-01-27 09:09:40.493255','GENWCG4OK5UQ','ADM9U8EX0T4G','ADM9U8EX0T4G'),(5,'2026-01-27 09:14:23.856708','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-01-28 07:07:07.926211','GENGTDTP9B6E','ADM9U8EX0T4G','ADM9U8EX0T4G'),(6,'2026-01-27 09:16:13.804087','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-01-28 05:42:15.569696','GENJU0W6K659','ADM9U8EX0T4G','ADM9U8EX0T4G'),(7,'2026-01-28 10:07:30.268596','Updated to: ASSIGN_AGENCY','ASSIGN_AGENCY','2026-02-03 10:01:30.549447','GENC18YQ7W22','ADM9U8EX0T4G','GENH8SNGFTQN'),(8,'2026-01-29 10:21:10.128741','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-02-03 07:52:47.830402','GENITHG4GA6F','GENH8SNGFTQN','GENH8SNGFTQN'),(9,'2026-01-29 10:59:15.855033','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-01-29 11:00:16.700038','GENG0WS5JIRE','GENH8SNGFTQN','GENH8SNGFTQN'),(10,'2026-02-02 05:46:40.078735','Updated to: PROPERTY_DETAILS','PROPERTY_DETAILS','2026-02-04 10:44:12.876770','GENUO9MXXG32','ADM9U8EX0T4G','GENH8SNGFTQN'),(11,'2026-02-02 07:10:39.246853','Updated to: PROPERTY_DETAILS','PROPERTY_DETAILS','2026-02-04 07:21:30.441126','GEN2FN3SSEIM','ADM9U8EX0T4G','GEN212KQVKN1'),(12,'2026-02-02 11:08:29.867575','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-02-02 11:17:50.634345','GEN2NNSZBW8C','ADM9U8EX0T4G','ADM9U8EX0T4G'),(13,'2026-02-03 10:07:37.447829','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-02-03 10:18:09.274917','GENTJ9ZCHTZQ','GENH8SNGFTQN','GENH8SNGFTQN'),(14,'2026-02-04 09:52:44.745582','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-02-04 09:52:44.745582','GENMGVSDJC1U','GENH8SNGFTQN','GENH8SNGFTQN'),(15,'2026-02-04 09:53:34.263450','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-02-04 09:53:34.263450','GENCFI8CWSZH','GENH8SNGFTQN','GENH8SNGFTQN'),(16,'2026-02-05 05:37:52.322945','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-02-05 05:37:52.322945','GENWRUVPU8U2','ADM9U8EX0T4G','ADM9U8EX0T4G'),(17,'2026-02-05 11:50:26.155002','Updated to: ASSIGN_AGENCY','ASSIGN_AGENCY','2026-02-05 11:53:11.030576','GENPXYYUDB9Y','GEN212KQVKN1','GEN212KQVKN1'),(18,'2026-02-05 12:36:17.568055','Updated to: ASSIGN_AGENCY','ASSIGN_AGENCY','2026-02-05 12:38:15.697847','GENV8RSMDROU','GEN212KQVKN1','GEN212KQVKN1'),(19,'2026-02-06 11:06:03.896655','Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2026-02-06 11:07:24.498265','GENLAEZT649C','ADM9U8EX0T4G','ADM9U8EX0T4G'),(20,'2026-02-06 11:15:16.477831','Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2026-02-06 11:15:16.477831','GENKCPS3MG4G','GENKHXLT7DEX','GENKHXLT7DEX');
/*!40000 ALTER TABLE `application_stage_current` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_stage_history`
--

DROP TABLE IF EXISTS `application_stage_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_stage_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` enum('IN_PROGRESS','APPROVED','REJECTED','ON_HOLD','VALUATOR_ASSIGNED','SITE_VISIT_SCHEDULED','SITE_VISIT_COMPLETED','SITE_VISIT_IN_PROGRESS','REVIEWING_APPLICATION','SITE_VISIT_FORM_SUBMITTED','VALUATOR_REASSIGNED','SITE_VISIT_RESCHEDULED','APPLICATION_APPLIED') NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpid1k9d40v1j81u1b98p41xm` (`application_id`),
  KEY `FKmaje5q5g4k1c9fxr8ic1ya31e` (`updated_by_id`),
  CONSTRAINT `FKjpid1k9d40v1j81u1b98p41xm` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKmaje5q5g4k1c9fxr8ic1ya31e` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_history`
--

LOCK TABLES `application_stage_history` WRITE;
/*!40000 ALTER TABLE `application_stage_history` DISABLE KEYS */;
INSERT INTO `application_stage_history` VALUES (1,'2026-01-27 08:54:45.426565','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-01-27 10:19:31.916382','GENAHL9SUHEK','ADM9U8EX0T4G'),(2,'2026-01-27 08:55:46.907626','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-01-27 08:55:46.907626','GENSPFQKR6M2','ADM9U8EX0T4G'),(3,'2026-01-27 08:57:21.802267','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-01-27 08:57:21.802267','GEN9ELO87YK6','ADM9U8EX0T4G'),(4,'2026-01-27 09:09:40.502253','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-01-27 09:09:40.502253','GENWCG4OK5UQ','ADM9U8EX0T4G'),(5,'2026-01-27 09:14:23.861708','Assigned to valuator','VALUATOR_ASSIGNED','2026-01-28 07:09:26.643515','GENGTDTP9B6E','AGNF6K73481J'),(6,'2026-01-27 09:16:13.810109','Assigned to valuator','VALUATOR_ASSIGNED','2026-01-28 06:55:24.512717','GENJU0W6K659','AGNF6K73481J'),(7,'2026-01-28 10:07:30.276600','Stage updated to: ASSIGN_AGENCY','IN_PROGRESS','2026-02-03 10:01:30.549447','GENC18YQ7W22','GENH8SNGFTQN'),(8,'2026-01-29 10:21:10.140741','Assigned to valuator','VALUATOR_ASSIGNED','2026-02-09 06:00:49.781115','GENITHG4GA6F','AGNF6K73481J'),(9,'2026-01-29 10:59:15.859031','Assigned to valuator','VALUATOR_ASSIGNED','2026-02-02 06:05:42.458405','GENG0WS5JIRE','AGNF6K73481J'),(10,'2026-02-02 05:46:40.078735','Stage updated to: PROPERTY_DETAILS','IN_PROGRESS','2026-02-04 10:44:12.876770','GENUO9MXXG32','GENH8SNGFTQN'),(11,'2026-02-02 07:10:39.252852','Stage updated to: PROPERTY_DETAILS','IN_PROGRESS','2026-02-04 07:21:30.441126','GEN2FN3SSEIM','GEN212KQVKN1'),(12,'2026-02-02 11:08:29.873575','Basic valuation details captured','SITE_VISIT_IN_PROGRESS','2026-02-09 06:34:45.532049','GEN2NNSZBW8C','VALIXZUOJPP0'),(13,'2026-02-03 10:07:37.450830','Assigned to valuator','VALUATOR_ASSIGNED','2026-02-05 12:27:42.000032','GENTJ9ZCHTZQ','AGNF6K73481J'),(14,'2026-02-04 09:52:44.762121','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-02-04 09:52:44.762121','GENMGVSDJC1U','GENH8SNGFTQN'),(15,'2026-02-04 09:53:34.280039','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-02-04 09:53:34.280039','GENCFI8CWSZH','GENH8SNGFTQN'),(16,'2026-02-05 05:37:52.322945','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-02-05 05:37:52.322945','GENWRUVPU8U2','ADM9U8EX0T4G'),(17,'2026-02-05 11:50:26.155002','Stage updated to: ASSIGN_AGENCY','IN_PROGRESS','2026-02-05 11:53:11.030576','GENPXYYUDB9Y','GEN212KQVKN1'),(18,'2026-02-05 12:36:17.572054','Stage updated to: ASSIGN_AGENCY','IN_PROGRESS','2026-02-05 12:38:15.700878','GENV8RSMDROU','GEN212KQVKN1'),(19,'2026-02-06 11:06:03.902668','Assigned to valuator','VALUATOR_ASSIGNED','2026-02-06 11:09:54.877690','GENLAEZT649C','AGNLJTVYQRCC'),(20,'2026-02-06 11:15:16.481864','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2026-02-06 11:15:16.481864','GENKCPS3MG4G','GENKHXLT7DEX');
/*!40000 ALTER TABLE `application_stage_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_summary`
--

DROP TABLE IF EXISTS `application_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_summary` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reviewed_date` datetime(6) DEFAULT NULL,
  `summary_text` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `reviewed_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4vdbkuerla86ifnqadso62kfq` (`application_id`),
  KEY `FK4nnhacbuqayddljvcijgcjaxy` (`reviewed_by_id`),
  CONSTRAINT `FK4nnhacbuqayddljvcijgcjaxy` FOREIGN KEY (`reviewed_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKcxdd7gfquslysc5ef134c0xuv` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_summary`
--

LOCK TABLES `application_summary` WRITE;
/*!40000 ALTER TABLE `application_summary` DISABLE KEYS */;
INSERT INTO `application_summary` VALUES (1,'2026-01-28 05:42:15.564674','remarks','GENJU0W6K659','ADM9U8EX0T4G'),(2,'2026-01-28 07:07:07.919214','validated the info','GENGTDTP9B6E','ADM9U8EX0T4G'),(3,'2026-01-28 10:08:25.756746','ntng','GENC18YQ7W22','ADM9U8EX0T4G'),(4,'2026-02-03 07:52:47.830402','ty','GENITHG4GA6F','GENH8SNGFTQN'),(5,'2026-01-29 11:00:16.697039','valiate','GENG0WS5JIRE','GENH8SNGFTQN'),(6,'2026-02-02 05:48:05.785259','qaa','GENUO9MXXG32','ADM9U8EX0T4G'),(7,'2026-02-02 07:11:17.098827','dddd','GEN2FN3SSEIM','ADM9U8EX0T4G'),(8,'2026-02-02 11:17:50.631345','no','GEN2NNSZBW8C','ADM9U8EX0T4G'),(9,'2026-02-03 10:18:09.266912','good','GENTJ9ZCHTZQ','GENH8SNGFTQN'),(10,'2026-02-06 11:07:24.489268','jnmhgtyjm','GENLAEZT649C','ADM9U8EX0T4G');
/*!40000 ALTER TABLE `application_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_uploaded_documents`
--

DROP TABLE IF EXISTS `application_uploaded_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_uploaded_documents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document_type` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_sizekb` bigint DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `document_details_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgg1mx59coehx4cqkbahh5hu05` (`document_details_id`),
  CONSTRAINT `FKgg1mx59coehx4cqkbahh5hu05` FOREIGN KEY (`document_details_id`) REFERENCES `application_document_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_uploaded_documents`
--

LOCK TABLES `application_uploaded_documents` WRITE;
/*!40000 ALTER TABLE `application_uploaded_documents` DISABLE KEYS */;
INSERT INTO `application_uploaded_documents` VALUES (1,'Registered Sale Deed','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Registered Sale Deed.pdf',1),(2,'Property Tax Receipt','saxwxdssdx.pdf',93,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Property Tax Receipt.pdf',1),(3,'Electricity / Water Bill','Application_Summary_GENVSTXYITN9wdwxx.pdf',110,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Electricity _ Water Bill.pdf',1),(4,'Electricity / Water Bill','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Electricity _ Water Bill.pdf',1),(5,'KYC / Income Proof','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\KYC _ Income Proof.pdf',1),(6,'Society NOC','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Society NOC.pdf',1),(7,'Property Tax Receipt','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Property Tax Receipt.pdf',1),(8,'Property Tax Receipt','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Property Tax Receipt.pdf',1),(9,'Society NOC','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENJU0W6K659\\application-documents\\Society NOC.pdf',1),(10,'Property Tax Receipt','signature (2).png',1,'image/png','C:\\app\\uploads\\GENGTDTP9B6E\\application-documents\\Property Tax Receipt.png',2),(11,'Property Tax Receipt','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENC18YQ7W22\\application-documents\\Property Tax Receipt.pdf',3),(12,'Electricity / Water Bill','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENITHG4GA6F\\application-documents\\Electricity _ Water Bill.pdf',4),(13,'Registered Sale Deed','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENG0WS5JIRE\\application-documents\\Registered Sale Deed.pdf',5),(14,'Registered Sale Deed','87902163431117005324.pdf',16,'application/pdf','C:\\app\\uploads\\GENUO9MXXG32\\application-documents\\Registered Sale Deed.pdf',6),(15,'Registered Sale Deed','DC.png',80,'image/png','C:\\app\\uploads\\GENUO9MXXG32\\application-documents\\Registered Sale Deed.png',6),(16,'Electricity / Water Bill','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GEN2FN3SSEIM\\application-documents\\Electricity _ Water Bill.pdf',7),(17,'Registered Sale Deed','eligibility list cti-rpc to web.pdf',119,'application/pdf','C:\\app\\uploads\\GEN2NNSZBW8C\\application-documents\\Registered Sale Deed.pdf',8),(18,'Property Tax Receipt','File_Download_View.pdf',947,'application/pdf','C:\\app\\uploads\\GEN2NNSZBW8C\\application-documents\\Property Tax Receipt.pdf',8),(19,'Registered Sale Deed','Karthik Expenseve.pdf',7,'application/pdf','C:\\app\\uploads\\GENITHG4GA6F\\application-documents\\Registered Sale Deed.pdf',4),(20,'KYC / Income Proof','Copilot_20250830_152905.png',2411,'image/png','C:\\app\\uploads\\GENC18YQ7W22\\application-documents\\KYC _ Income Proof.png',3),(21,'Electricity / Water Bill','RICE-Edu4.png',11,'image/png','C:\\app\\uploads\\GENTJ9ZCHTZQ\\application-documents\\Electricity _ Water Bill.png',9),(22,'Registered Sale Deed','HealthCareClaimForm.pdf',1051,'application/pdf','C:\\app\\uploads\\GENPXYYUDB9Y\\application-documents\\Registered Sale Deed.pdf',10),(23,'Registered Sale Deed','CCCC.pdf',242,'application/pdf','C:\\app\\uploads\\GENV8RSMDROU\\application-documents\\Registered Sale Deed.pdf',11),(24,'Registered Sale Deed','Application_Summary_GENVSTXYITN9 dvrv.pdf',86,'application/pdf','C:\\app\\uploads\\GENLAEZT649C\\application-documents\\Registered Sale Deed.pdf',12);
/*!40000 ALTER TABLE `application_uploaded_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_valuator_assignment`
--

DROP TABLE IF EXISTS `application_valuator_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_valuator_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `assigned_date` datetime(6) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `assigned_by` varchar(12) NOT NULL,
  `valuator_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKejs6d9j74abr0ra43cq10iw6h` (`application_id`),
  KEY `FKb9ecyfsv2a0iuir4bg6pqjhns` (`assigned_by`),
  KEY `FKkgwiyor063lato54cf212rshe` (`valuator_id`),
  CONSTRAINT `FKb9ecyfsv2a0iuir4bg6pqjhns` FOREIGN KEY (`assigned_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKejs6d9j74abr0ra43cq10iw6h` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKkgwiyor063lato54cf212rshe` FOREIGN KEY (`valuator_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_valuator_assignment`
--

LOCK TABLES `application_valuator_assignment` WRITE;
/*!40000 ALTER TABLE `application_valuator_assignment` DISABLE KEYS */;
INSERT INTO `application_valuator_assignment` VALUES (1,'2026-01-28 06:55:24.503735','vbnm,.','2026-01-28 06:55:24.503735','GENJU0W6K659','AGNF6K73481J','VAL714J0RACR'),(2,'2026-01-28 07:09:26.638514','validated the application','2026-01-28 07:09:26.638514','GENGTDTP9B6E','AGNF6K73481J','VALGSKGGARUM'),(3,'2026-01-28 10:38:02.427094','lol','2026-01-28 10:38:02.427094','GENC18YQ7W22','AGNF6K73481J','VALIXZUOJPP0'),(4,'2026-01-29 11:01:12.794817','ddd','2026-02-09 06:00:49.781115','GENITHG4GA6F','AGNF6K73481J','VALIXZUOJPP0'),(5,'2026-02-02 06:05:42.442781','eds','2026-02-02 06:05:42.442781','GENG0WS5JIRE','AGNF6K73481J','VALIXZUOJPP0'),(6,'2026-02-02 11:25:00.521197','please complete my site visit','2026-02-02 11:25:00.521197','GEN2NNSZBW8C','AGNF6K73481J','VALIXZUOJPP0'),(7,'2026-02-05 12:27:41.992072','dddd','2026-02-05 12:27:41.992072','GENTJ9ZCHTZQ','AGNF6K73481J','VALD8D5T3QUZ'),(8,'2026-02-06 11:09:54.872689','reh','2026-02-06 11:09:54.872689','GENLAEZT649C','AGNLJTVYQRCC','VAL8YYPOME3K');
/*!40000 ALTER TABLE `application_valuator_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_master`
--

DROP TABLE IF EXISTS `bank_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bank_code` varchar(20) NOT NULL,
  `bank_name` varchar(150) NOT NULL,
  `bank_type` varchar(30) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `head_office_address` varchar(500) DEFAULT NULL,
  `ifsc_prefix` varchar(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `swift_code` varchar(20) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK18et9xg1ghscxs2j79f7ad3j6` (`bank_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_master`
--

LOCK TABLES `bank_master` WRITE;
/*!40000 ALTER TABLE `bank_master` DISABLE KEYS */;
INSERT INTO `bank_master` VALUES (1,'HDFC001','HDFC Bank Ltd','PRIVATE','support@hdfcbank.com','+91-22-61606161','India','2026-01-27 07:00:46.041936','HDFC Bank House, Senapati Bapat Marg, Lower Parel, Mumbai','HDFC',_binary '','HDFCINBB',NULL,'https://www.hdfcbank.com'),(2,'ICICI001','ICICI Bank Ltd','PRIVATE','customer.care@icicibank.com','+91-22-33667777','India','2026-01-27 07:01:22.682403','ICICI Bank Towers, Bandra-Kurla Complex, Mumbai, Maharashtra','ICIC',_binary '','ICICINBB',NULL,'https://www.icicibank.com'),(3,'SBI001','State Bank of India','PUBLIC','support@sbi.co.in','+91-1800-1234','India','2026-01-27 07:01:29.099403','State Bank Bhavan, Madam Cama Road, Mumbai, Maharashtra','SBIN',_binary '','SBININBB',NULL,'https://sbi.co.in'),(4,'AXIS001','Axis Bank Ltd','PRIVATE','support@axisbank.com','+91-22-24252525','India','2026-01-27 07:01:37.482965','Axis House, Wadia International Centre, Mumbai','UTIB',_binary '','AXISINBB',NULL,'https://www.axisbank.com'),(5,'AXIS002','Axis Bank Ltl','PRIVATE','support1@axisbank.com','+91-22-24252521','India','2026-02-05 12:53:16.876832','Axis House, Wadia International Centre, Mumbai','UTIT',_binary '','AXISINBC',NULL,'https://www.axisbank1.com'),(9,'0929280','Union Bank Of India','PUBLIC','unionbank@gmail.com','8095380158','India','2026-02-06 10:52:17.095392','297 OPP HCMG PU College, Tiptur Huliyar Road, Halkurke, Tumkur, Karnataka, 572201','UBIN0929280',_binary '','UBININBBALK',NULL,' https://www.unionbankofindia.bank.in');
/*!40000 ALTER TABLE `bank_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic_valuation_details`
--

DROP TABLE IF EXISTS `basic_valuation_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basic_valuation_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `loan_type` varchar(100) DEFAULT NULL,
  `person_met_at_site` varchar(150) DEFAULT NULL,
  `property_owner_name` varchar(150) NOT NULL,
  `relationship_with_applicant` varchar(100) DEFAULT NULL,
  `report_date` date NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbcm95qaio0aihx4q5jisoatyy` (`application_id`),
  KEY `FKmsv3rkjxu7194bto7lvbu83ij` (`created_by`),
  KEY `FKp8ym4kaei6ovscua4cfij11f8` (`updated_by`),
  CONSTRAINT `FKbcm95qaio0aihx4q5jisoatyy` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKmsv3rkjxu7194bto7lvbu83ij` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKp8ym4kaei6ovscua4cfij11f8` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_valuation_details`
--

LOCK TABLES `basic_valuation_details` WRITE;
/*!40000 ALTER TABLE `basic_valuation_details` DISABLE KEYS */;
INSERT INTO `basic_valuation_details` VALUES (1,'2026-01-29 11:05:12.617024','LAP','akay','anushka','wife','2026-01-29','2026-01-29 11:05:12.617024','GENITHG4GA6F','VALIXZUOJPP0','VALIXZUOJPP0'),(2,'2026-02-02 12:01:36.320307','LAP','viswa','siddu H K','self','2026-02-02','2026-02-09 06:34:45.532049','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `basic_valuation_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic_valuation_documents`
--

DROP TABLE IF EXISTS `basic_valuation_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basic_valuation_documents` (
  `basic_valuation_id` bigint NOT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  KEY `FKkvvdbmls905kim9gfrvwef6y8` (`basic_valuation_id`),
  CONSTRAINT `FKkvvdbmls905kim9gfrvwef6y8` FOREIGN KEY (`basic_valuation_id`) REFERENCES `basic_valuation_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_valuation_documents`
--

LOCK TABLES `basic_valuation_documents` WRITE;
/*!40000 ALTER TABLE `basic_valuation_documents` DISABLE KEYS */;
INSERT INTO `basic_valuation_documents` VALUES (1,'Sale Deed Copy'),(1,'Aadhaar Copy'),(2,'Aadhaar Copy');
/*!40000 ALTER TABLE `basic_valuation_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_master` (
  `id` varchar(12) NOT NULL,
  `bank_id` bigint DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','AGENCY_VALUATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_snf65l86t4b0xj6v0f9nymegs` (`email`),
  KEY `FKa7ixe84griaudxpjdhcgl7kyo` (`bank_id`),
  CONSTRAINT `FKa7ixe84griaudxpjdhcgl7kyo` FOREIGN KEY (`bank_id`) REFERENCES `bank_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
INSERT INTO `customer_master` VALUES ('GEN8RVHU5LT7',1,'2026-01-29 10:21:10.025733','newuser@gmail.com','new','user','$2a$10$5TnOAQeQ9dYvIZ.i8dXeku/0Ux7fnQ0ML323bypwTx2g4YImJjwOq','7348820425','USER'),('GENC06H8SIW7',3,'2026-02-05 12:36:17.522938','vinod@gmail.com','vinod','N','$2a$10$ZLE1eb0n6ZAL69rxBcjCSerxOlDVcCDv3m3d3Y/l9VmqJqlcjLUUC','9663856412','USER'),('GENC7SUKT1WA',1,'2026-02-02 05:46:39.979057','pramodvc7204@gmail.com','Pramod','V C','$2a$10$jQYUNuSVfQ14hdRuPytH5uGCwoMQf9YvbRS9Pbx9763YNmGeCyn3C','9353422469','USER'),('GENG3POCXIZA',3,'2026-02-05 05:37:52.239435','g@gmail.com','fsdf','gr','$2a$10$pGnxmx/W.NJqDgg84gs4CeNMFGyqN3XAume0jqfWy.DqXweQVlBTO','7846531256','USER'),('GENHGP2FT1ZE',3,'2026-02-02 11:08:29.849572','siddu@gmail.com','ssss','k','$2a$10$FKwLjPgfPQzjMjZPg9KHROT34FLzQwGHGg11IgT1OWox2nAJcHFKG','9972216772','USER'),('GENI4YNRDQNY',1,'2026-01-27 09:14:23.844701','dd@gmail.com','uyds','s','$2a$10$7X05ueEBVLRgRUrxMfICSOwxDTsdVwHC6r1B/mCljI3jEcika6GvC','8888408985','USER'),('GENIFHVHOK3S',1,'2026-02-03 10:07:37.398325','nagesh@gmail.com','urukundhappa','nagesh','$2a$10$Np4EL4PsTj3C78vRFnrFCuURsUkm6/1lPmjop5Gk2j5/goTWiP5I.','9059521841','USER'),('GENLMW27B4KH',1,'2026-02-04 09:53:34.246599','c@gmail.com','cec','cc','$2a$10$eQbCsgUzYYb5cMZsJs.lEOXHgphtK/zl8nrLF16PazMvGOI4OTvia','8572242525','USER'),('GENOCWN7GX78',3,'2026-01-27 08:54:45.337540','customer@gmail.com','customer','One','$2a$10$q3ZpGVoGbcQ9FYRwCUtuheaueE2Q2ZnUOri9MCCRkytDQSnZP52XC','8659766692','USER'),('GENPUGZJM3VB',9,'2026-02-06 11:15:16.460858','khkjhk@gmail.com','jh','hk','$2a$10$gy8dBfP3N.OP7e9kz1gZlOh8P6gFY3hRzwwr2PXguHijHlBUh8gia','7357544540','USER'),('GENTB2FJNAXR',1,'2026-01-27 09:16:13.783107','s@gmail.com','q','d','$2a$10$IV//cmxelABTgo8X/1iQhurLtT.et5v.STMDmsUyX0fkftC8nSjSi','7965466566','USER'),('GENY9Y0H63KF',3,'2026-02-06 11:06:03.839689','fd@gmail.com','dc','cec','$2a$10$rLJyARKjNpARtnv.cqCUa.HAGYz9sOMdxos1JlbKXVpeo4ouBD8QG','9782934250','USER'),('GENZI7YPOWX4',3,'2026-02-02 07:10:39.229854','vish@gmail.com','vish','mn','$2a$10$pZhtA1gKziDNhh1C2sdJx.WWPN2jSpNgRe3xIlCbQFC2vbn60CRfy','8579648562','USER');
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inbox_message`
--

DROP TABLE IF EXISTS `inbox_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inbox_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attachment_url` varchar(255) DEFAULT NULL,
  `message_text` text,
  `sender_type` enum('BANK','AGENCY','SYSTEM') DEFAULT NULL,
  `sent_at` datetime(6) DEFAULT NULL,
  `thread_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKciqwsy8ggm7k0woonh39rqcrd` (`thread_id`),
  CONSTRAINT `FKciqwsy8ggm7k0woonh39rqcrd` FOREIGN KEY (`thread_id`) REFERENCES `inbox_thread` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbox_message`
--

LOCK TABLES `inbox_message` WRITE;
/*!40000 ALTER TABLE `inbox_message` DISABLE KEYS */;
INSERT INTO `inbox_message` VALUES (1,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-01-28 10:08:18.130768',1),(2,NULL,'ok','AGENCY','2026-01-28 10:11:28.713170',1),(3,'saxwxdssdx.pdf','lol','AGENCY','2026-01-28 10:29:18.872841',1),(4,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-01-29 10:23:23.758326',2),(5,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-01-29 11:00:07.253556',3),(6,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-02 05:47:58.997368',4),(7,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-02 07:11:14.053168',5),(8,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-02 11:12:18.745532',6),(9,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-03 10:15:09.453073',7),(10,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-05 11:53:11.030576',8),(11,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-05 12:38:15.704879',9),(12,NULL,'A new application has been assigned to your agency. Please proceed with valuation.','SYSTEM','2026-02-06 11:07:18.821003',10);
/*!40000 ALTER TABLE `inbox_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inbox_thread`
--

DROP TABLE IF EXISTS `inbox_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inbox_thread` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agency_id` bigint DEFAULT NULL,
  `application_id` varchar(255) DEFAULT NULL,
  `bank_id` bigint DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `unread_for_agency` bit(1) NOT NULL,
  `unread_for_bank` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbox_thread`
--

LOCK TABLES `inbox_thread` WRITE;
/*!40000 ALTER TABLE `inbox_thread` DISABLE KEYS */;
INSERT INTO `inbox_thread` VALUES (1,1,'GENC18YQ7W22',1,'2026-01-28 10:29:18.886097','New Application Assigned - GENC18YQ7W22',_binary '\0',_binary ''),(2,1,'GENITHG4GA6F',1,'2026-01-29 10:23:23.755325','New Application Assigned - GENITHG4GA6F',_binary '\0',_binary '\0'),(3,1,'GENG0WS5JIRE',1,'2026-01-29 11:00:07.252553','New Application Assigned - GENG0WS5JIRE',_binary '\0',_binary '\0'),(4,1,'GENUO9MXXG32',1,'2026-02-02 05:47:58.981749','New Application Assigned - GENUO9MXXG32',_binary '\0',_binary '\0'),(5,1,'GEN2FN3SSEIM',3,'2026-02-02 07:11:14.052169','New Application Assigned - GEN2FN3SSEIM',_binary '\0',_binary '\0'),(6,1,'GEN2NNSZBW8C',3,'2026-02-02 11:12:18.742528','New Application Assigned - GEN2NNSZBW8C',_binary '\0',_binary '\0'),(7,1,'GENTJ9ZCHTZQ',1,'2026-02-03 10:15:09.450072','New Application Assigned - GENTJ9ZCHTZQ',_binary '\0',_binary '\0'),(8,1,'GENPXYYUDB9Y',3,'2026-02-05 11:53:11.030576','New Application Assigned - GENPXYYUDB9Y',_binary '\0',_binary '\0'),(9,1,'GENV8RSMDROU',3,'2026-02-05 12:38:15.702877','New Application Assigned - GENV8RSMDROU',_binary '\0',_binary '\0'),(10,2,'GENLAEZT649C',3,'2026-02-06 11:07:18.816009','New Application Assigned - GENLAEZT649C',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `inbox_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_building_details`
--

DROP TABLE IF EXISTS `site_visit_building_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_building_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approved_plan_available` bit(1) DEFAULT NULL,
  `construction_quality` varchar(255) DEFAULT NULL,
  `construction_type` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `floor_type` varchar(255) DEFAULT NULL,
  `plan_approval_date` datetime(6) DEFAULT NULL,
  `plan_approval_number` varchar(255) DEFAULT NULL,
  `plan_deviation_observed` bit(1) DEFAULT NULL,
  `plan_deviation_remarks` varchar(255) DEFAULT NULL,
  `plan_issuing_authority` varchar(255) DEFAULT NULL,
  `roof_type` varchar(255) DEFAULT NULL,
  `star_type` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfktguum256sxjg536tu8b9y4` (`application_id`),
  KEY `FKkrhjbqh699pmqvqra3t8a0grl` (`created_by`),
  KEY `FK83xn985r6ivmxiwmpjrjehr73` (`updated_by`),
  CONSTRAINT `FK83xn985r6ivmxiwmpjrjehr73` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKkrhjbqh699pmqvqra3t8a0grl` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKnfg342iif3ajuwxb8qx6g02w3` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_building_details`
--

LOCK TABLES `site_visit_building_details` WRITE;
/*!40000 ALTER TABLE `site_visit_building_details` DISABLE KEYS */;
INSERT INTO `site_visit_building_details` VALUES (1,_binary '','Average','Load Bearing','2026-01-29 11:06:36.866880','Granite',NULL,'',_binary '','cdwc','BBMP','Sheet Roof','2 Star',NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,_binary '','','','2026-02-02 12:20:05.683508','',NULL,'',_binary '','remarks','BBMP','','','2026-02-04 07:45:12.714191','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_building_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_image_files`
--

DROP TABLE IF EXISTS `site_visit_image_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_image_files` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_category` enum('PROPERTY_SPECIFIC','UNIT_SPECIFIC','COMPARISON') NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_size` bigint DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `image_group_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8eq8tfk0gj6p9wsy1bmlufqm` (`image_group_id`),
  CONSTRAINT `FK8eq8tfk0gj6p9wsy1bmlufqm` FOREIGN KEY (`image_group_id`) REFERENCES `site_visit_images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_image_files`
--

LOCK TABLES `site_visit_image_files` WRITE;
/*!40000 ALTER TABLE `site_visit_image_files` DISABLE KEYS */;
INSERT INTO `site_visit_image_files` VALUES (1,'PROPERTY_SPECIFIC','property_specific-1769684967772.jpg','C:\\app\\uploads\\GENITHG4GA6F\\site-visit\\images\\property_specific\\property_specific-1769684967772.jpg',206545,'image/jpeg',1),(2,'UNIT_SPECIFIC','unit_specific-1769684967786.jpg','C:\\app\\uploads\\GENITHG4GA6F\\site-visit\\images\\unit_specific\\unit_specific-1769684967786.jpg',50338,'image/jpeg',1),(3,'COMPARISON','comparison-1769684967790.jpg','C:\\app\\uploads\\GENITHG4GA6F\\site-visit\\images\\comparison\\comparison-1769684967790.jpg',71851,'image/jpeg',1);
/*!40000 ALTER TABLE `site_visit_image_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_images`
--

DROP TABLE IF EXISTS `site_visit_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7x7x38wywcdmla7lvhgnpivt4` (`application_id`),
  CONSTRAINT `FK7sh68v3h17pacrv31n42xypvs` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_images`
--

LOCK TABLES `site_visit_images` WRITE;
/*!40000 ALTER TABLE `site_visit_images` DISABLE KEYS */;
INSERT INTO `site_visit_images` VALUES (1,'GENITHG4GA6F');
/*!40000 ALTER TABLE `site_visit_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_infrastructure_details`
--

DROP TABLE IF EXISTS `site_visit_infrastructure_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_infrastructure_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accessibility_type` varchar(255) DEFAULT NULL,
  `accessible_through` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `electricity` bit(1) DEFAULT NULL,
  `number_of_lifts` int DEFAULT NULL,
  `road_width` varchar(255) DEFAULT NULL,
  `sewerage_system` bit(1) DEFAULT NULL,
  `site_accessibility` bit(1) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `water_supply` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p34pvxwvf7jj83cuc2ptudwx4` (`application_id`),
  KEY `FK6vj5lnlxrhiyhw1qg3qge8h2o` (`created_by`),
  KEY `FK5hfay8a0w33yu66yy1s3t23u2` (`updated_by`),
  CONSTRAINT `FK3ikh16pf31k30589635p0kt2` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FK5hfay8a0w33yu66yy1s3t23u2` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK6vj5lnlxrhiyhw1qg3qge8h2o` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_infrastructure_details`
--

LOCK TABLES `site_visit_infrastructure_details` WRITE;
/*!40000 ALTER TABLE `site_visit_infrastructure_details` DISABLE KEYS */;
INSERT INTO `site_visit_infrastructure_details` VALUES (1,'Two Wheeler','Road','2026-01-29 11:06:47.017569',_binary '',0,'< 10 ft',_binary '',_binary '',NULL,'Borewell','GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'Four Wheeler','Road','2026-02-02 12:20:58.225170',_binary '',2,'10 - 20 ft',_binary '',_binary '','2026-02-04 07:47:10.512705','BWSSB','GEN2NNSZBW8C','VALIXZUOJPP0',NULL);
/*!40000 ALTER TABLE `site_visit_infrastructure_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_boundary_details`
--

DROP TABLE IF EXISTS `site_visit_property_boundary_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_boundary_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `boundary_matching` bit(1) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `current_zoning` varchar(255) DEFAULT NULL,
  `earthquake_resistant` bit(1) DEFAULT NULL,
  `east_legal_doc` varchar(255) DEFAULT NULL,
  `east_site_visit` varchar(255) DEFAULT NULL,
  `east_match` bit(1) DEFAULT NULL,
  `north_legal_doc` varchar(255) DEFAULT NULL,
  `north_site_visit` varchar(255) DEFAULT NULL,
  `north_match` bit(1) DEFAULT NULL,
  `property_facing` varchar(255) DEFAULT NULL,
  `property_identification` bit(1) DEFAULT NULL,
  `south_legal_doc` varchar(255) DEFAULT NULL,
  `south_site_visit` varchar(255) DEFAULT NULL,
  `south_match` bit(1) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `west_legal_doc` varchar(255) DEFAULT NULL,
  `west_site_visit` varchar(255) DEFAULT NULL,
  `west_match` bit(1) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cb0xf8lflhiddc4t3puhmgxs4` (`application_id`),
  KEY `FKmjft6fxpe7tf7g6epl4q9lbp4` (`created_by`),
  KEY `FKaigyqtn3rq3b5jfe36q1f9g2s` (`updated_by`),
  CONSTRAINT `FKaigyqtn3rq3b5jfe36q1f9g2s` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKb6akreq7seirp1n3t6lwa1umv` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKmjft6fxpe7tf7g6epl4q9lbp4` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_boundary_details`
--

LOCK TABLES `site_visit_property_boundary_details` WRITE;
/*!40000 ALTER TABLE `site_visit_property_boundary_details` DISABLE KEYS */;
INSERT INTO `site_visit_property_boundary_details` VALUES (1,_binary '','2026-01-29 11:06:22.270643','central',_binary '','address1','address1',_binary '','address1','address1',_binary '','East',_binary '','address1','address1',_binary '','2026-01-29 11:06:22.273643','address1','address1',_binary '','GENITHG4GA6F','VALIXZUOJPP0','VALIXZUOJPP0'),(2,_binary '','2026-02-02 12:18:24.914439','4',_binary '\0','road','road',_binary '','commercial building','commercial building',_binary '','East',_binary '','home','home',_binary '','2026-02-04 07:30:22.934072','home','home',_binary '','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_property_boundary_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_details`
--

DROP TABLE IF EXISTS `site_visit_property_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `distance_from_city_centre` double DEFAULT NULL,
  `doc_building_name` varchar(255) DEFAULT NULL,
  `doc_city` varchar(255) DEFAULT NULL,
  `doc_door_no` varchar(255) DEFAULT NULL,
  `doc_pin_code` varchar(255) DEFAULT NULL,
  `doc_state` varchar(255) DEFAULT NULL,
  `doc_street_line1` varchar(255) DEFAULT NULL,
  `doc_street_line2` varchar(255) DEFAULT NULL,
  `jurisdiction` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `nearby_landmark` varchar(255) DEFAULT NULL,
  `postal_building_name` varchar(255) DEFAULT NULL,
  `postal_city` varchar(255) DEFAULT NULL,
  `postal_door_no` varchar(255) DEFAULT NULL,
  `postal_pin_code` varchar(255) DEFAULT NULL,
  `postal_state` varchar(255) DEFAULT NULL,
  `postal_street_line1` varchar(255) DEFAULT NULL,
  `postal_street_line2` varchar(255) DEFAULT NULL,
  `property_sub_type` varchar(255) DEFAULT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dn0e98tlxa2krvtrpq88hnxfe` (`application_id`),
  KEY `FKprqp0dwuwd447gs6d8t0ea4n9` (`created_by`),
  KEY `FKdvvv3pw2xeb7loijnu8a8c9q8` (`updated_by`),
  CONSTRAINT `FKdvvv3pw2xeb7loijnu8a8c9q8` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKfsi96bm1dyij4ged793q7ka7h` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKprqp0dwuwd447gs6d8t0ea4n9` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_details`
--

LOCK TABLES `site_visit_property_details` WRITE;
/*!40000 ALTER TABLE `site_visit_property_details` DISABLE KEYS */;
INSERT INTO `site_visit_property_details` VALUES (1,'2026-01-29 11:06:01.186214',NULL,'address1','Tiptur','address1','572201','Karnataka','address1','','gramPanchayat',12.966144,77.59608,'Temple','address1','Tiptur','address1','572201','Karnataka','address1','address1','residential','independentHouse',NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'2026-02-02 12:16:24.533729',NULL,'home','Arsikere','#002','573144','Karnataka','near Temple','','gramPanchayat',11.234,82.9876,'temple','home','Adoni','#002','518301','Andhra Pradesh','near temple ','','residential','independentHouse','2026-02-04 07:30:10.667943','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_property_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_value_assessment`
--

DROP TABLE IF EXISTS `site_visit_property_value_assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_value_assessment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amenities_considered_value` decimal(38,2) DEFAULT NULL,
  `amenities_total_value` decimal(38,2) DEFAULT NULL,
  `amenity_category` varchar(255) DEFAULT NULL,
  `amenity_impact` varchar(255) DEFAULT NULL,
  `amenity_rating` varchar(255) DEFAULT NULL,
  `building_considered_value` decimal(38,2) DEFAULT NULL,
  `building_total_value` decimal(38,2) DEFAULT NULL,
  `consideration_price_per_sqft` decimal(38,2) DEFAULT NULL,
  `consideration_value_actual` decimal(38,2) DEFAULT NULL,
  `consideration_value_document` decimal(38,2) DEFAULT NULL,
  `consideration_value_layout` decimal(38,2) DEFAULT NULL,
  `distressed_value_actual` decimal(38,2) DEFAULT NULL,
  `distressed_value_document` decimal(38,2) DEFAULT NULL,
  `distressed_value_layout` decimal(38,2) DEFAULT NULL,
  `fair_market_value_actual` decimal(38,2) DEFAULT NULL,
  `fair_market_value_document` decimal(38,2) DEFAULT NULL,
  `fair_market_value_layout` decimal(38,2) DEFAULT NULL,
  `final_value_considered` decimal(38,2) DEFAULT NULL,
  `fmv_completion_actual` decimal(38,2) DEFAULT NULL,
  `fmv_completion_document` decimal(38,2) DEFAULT NULL,
  `fmv_completion_layout` decimal(38,2) DEFAULT NULL,
  `fmv_date_actual` decimal(38,2) DEFAULT NULL,
  `fmv_date_document` decimal(38,2) DEFAULT NULL,
  `fmv_date_layout` decimal(38,2) DEFAULT NULL,
  `government_price_per_sqft` decimal(38,2) DEFAULT NULL,
  `govt_value_actual` decimal(38,2) DEFAULT NULL,
  `govt_value_document` decimal(38,2) DEFAULT NULL,
  `govt_value_layout` decimal(38,2) DEFAULT NULL,
  `grand_final_value` decimal(38,2) DEFAULT NULL,
  `guideline_value_actual` decimal(38,2) DEFAULT NULL,
  `guideline_value_document` decimal(38,2) DEFAULT NULL,
  `guideline_value_layout` decimal(38,2) DEFAULT NULL,
  `insurance_value_actual` decimal(38,2) DEFAULT NULL,
  `insurance_value_document` decimal(38,2) DEFAULT NULL,
  `insurance_value_layout` decimal(38,2) DEFAULT NULL,
  `land_area_actual` decimal(38,2) DEFAULT NULL,
  `land_area_document` decimal(38,2) DEFAULT NULL,
  `land_area_layout` decimal(38,2) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qm6eff8ygd7gyvgt0abmnxqxv` (`application_id`),
  CONSTRAINT `FKr7t1qdlhxj291fua2rilqpks6` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment`
--

LOCK TABLES `site_visit_property_value_assessment` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_visit_property_value_assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_value_assessment_amenities`
--

DROP TABLE IF EXISTS `site_visit_property_value_assessment_amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_value_assessment_amenities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `total_amenities_value` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3n08upqjdn9xlcct84ss2ydg` (`application_id`),
  KEY `FK4lyik7f57qap1yb4voi17psfx` (`created_by`),
  KEY `FK4y5xwtc0tdu1xy0d051bs1u14` (`updated_by`),
  CONSTRAINT `FK4lyik7f57qap1yb4voi17psfx` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK4y5xwtc0tdu1xy0d051bs1u14` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKbi1i7lbgbw3k26s3s0c0lpco1` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment_amenities`
--

LOCK TABLES `site_visit_property_value_assessment_amenities` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment_amenities` DISABLE KEYS */;
INSERT INTO `site_visit_property_value_assessment_amenities` VALUES (1,'2026-01-29 11:08:55.109584',12000,'2026-01-29 11:08:55.109584','GENITHG4GA6F','VALIXZUOJPP0','VALIXZUOJPP0'),(2,'2026-02-03 10:46:14.184681',545445,'2026-02-03 10:46:14.184681','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_property_value_assessment_amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_value_assessment_amenity_items`
--

DROP TABLE IF EXISTS `site_visit_property_value_assessment_amenity_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_value_assessment_amenity_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amenity_category` varchar(255) NOT NULL,
  `amenity_impact` varchar(255) NOT NULL,
  `amenity_rating` varchar(255) NOT NULL,
  `amenity_value` double DEFAULT NULL,
  `amenities_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm2wdmd0qog5tqi84b6ww192cw` (`amenities_id`),
  CONSTRAINT `FKm2wdmd0qog5tqi84b6ww192cw` FOREIGN KEY (`amenities_id`) REFERENCES `site_visit_property_value_assessment_amenities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment_amenity_items`
--

LOCK TABLES `site_visit_property_value_assessment_amenity_items` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment_amenity_items` DISABLE KEYS */;
INSERT INTO `site_visit_property_value_assessment_amenity_items` VALUES (1,'Lift','Low','2 Star',12000,1),(2,'Gym','Medium','3 Star',545445,2);
/*!40000 ALTER TABLE `site_visit_property_value_assessment_amenity_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_value_assessment_final_valuation`
--

DROP TABLE IF EXISTS `site_visit_property_value_assessment_final_valuation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_value_assessment_final_valuation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `distressed_value_actual` double DEFAULT NULL,
  `distressed_value_document` double DEFAULT NULL,
  `distressed_value_layout` double DEFAULT NULL,
  `final_value_considered` double DEFAULT NULL,
  `fmv_on_completion_actual` double DEFAULT NULL,
  `fmv_on_completion_document` double DEFAULT NULL,
  `fmv_on_completion_layout` double DEFAULT NULL,
  `fmv_on_date_actual` double DEFAULT NULL,
  `fmv_on_date_document` double DEFAULT NULL,
  `fmv_on_date_layout` double DEFAULT NULL,
  `guideline_value_actual` double DEFAULT NULL,
  `guideline_value_document` double DEFAULT NULL,
  `guideline_value_layout` double DEFAULT NULL,
  `insurance_value_actual` double DEFAULT NULL,
  `insurance_value_document` double DEFAULT NULL,
  `insurance_value_layout` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK307hqvupw0ykcxmjea1wqusmv` (`application_id`),
  KEY `FK70w2qedlhix1bmgmbnocu8925` (`created_by`),
  KEY `FKe4d4ht8ni49n9k938rdxcwmqe` (`updated_by`),
  CONSTRAINT `FK4pulhiinswh0dr0lij5muygbl` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FK70w2qedlhix1bmgmbnocu8925` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKe4d4ht8ni49n9k938rdxcwmqe` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment_final_valuation`
--

LOCK TABLES `site_visit_property_value_assessment_final_valuation` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment_final_valuation` DISABLE KEYS */;
INSERT INTO `site_visit_property_value_assessment_final_valuation` VALUES (1,'2026-01-29 11:09:06.301418',1,1,1,1,1,1,1,12,1,1,1,1,1,1,1,1,NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'2026-02-03 10:48:16.876034',854750,524512,365245,451242,84514,45874,54125,417854,458724,452175,458745,458712,455545,25487,455454,584214,NULL,'GEN2NNSZBW8C','VALIXZUOJPP0',NULL);
/*!40000 ALTER TABLE `site_visit_property_value_assessment_final_valuation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_property_value_assessment_land`
--

DROP TABLE IF EXISTS `site_visit_property_value_assessment_land`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_property_value_assessment_land` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `consideration_rate_per_sqft` double DEFAULT NULL,
  `consideration_total_value_actual` double DEFAULT NULL,
  `consideration_total_value_document` double DEFAULT NULL,
  `consideration_total_value_layout` double DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `fair_market_value_actual` double DEFAULT NULL,
  `fair_market_value_document` double DEFAULT NULL,
  `fair_market_value_layout` double DEFAULT NULL,
  `government_rate_per_sqft` double DEFAULT NULL,
  `govt_total_value_actual` double DEFAULT NULL,
  `govt_total_value_document` double DEFAULT NULL,
  `govt_total_value_layout` double DEFAULT NULL,
  `land_area_actual` double DEFAULT NULL,
  `land_area_document` double DEFAULT NULL,
  `land_area_layout_plan` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKaic2h1aaadhw1qtylfimoonhd` (`application_id`),
  KEY `FKifiolx8j1i76kp0vxmgmnu8uh` (`created_by`),
  KEY `FKjqfsx6778h0q48o6pp95y28ob` (`updated_by`),
  CONSTRAINT `FKaevl40imt3jouwlqs2tl1thw5` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKifiolx8j1i76kp0vxmgmnu8uh` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKjqfsx6778h0q48o6pp95y28ob` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment_land`
--

LOCK TABLES `site_visit_property_value_assessment_land` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment_land` DISABLE KEYS */;
INSERT INTO `site_visit_property_value_assessment_land` VALUES (1,10,100,100,100,'2026-01-29 11:08:43.236354',100,100,100,10,100,100,100,10,10,10,NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,456745,548094000,548094000,548094000,'2026-02-03 10:45:54.307923',548094000,548094000,548094000,286502,343802400,343802400,343802400,1200,1200,1200,NULL,'GEN2NNSZBW8C','VALIXZUOJPP0',NULL);
/*!40000 ALTER TABLE `site_visit_property_value_assessment_land` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_additional`
--

DROP TABLE IF EXISTS `site_visit_technical_additional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_additional` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age_of_property` int DEFAULT NULL,
  `community_sensitivity` varchar(255) DEFAULT NULL,
  `construction_progress_percent` int DEFAULT NULL,
  `construction_progress_remarks` varchar(500) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `demolition_notification` bit(1) DEFAULT NULL,
  `development_in_vicinity_percent` int DEFAULT NULL,
  `document_executed_on` date DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `earlier_valuation_done` bit(1) DEFAULT NULL,
  `in_favour_of` varchar(255) DEFAULT NULL,
  `market_feedback` varchar(255) DEFAULT NULL,
  `municipal_notification` varchar(255) DEFAULT NULL,
  `negative_area_as_per_local_norms` bit(1) DEFAULT NULL,
  `recommended_funding_percent` int DEFAULT NULL,
  `rera_details` varchar(255) DEFAULT NULL,
  `residual_age_of_property` int DEFAULT NULL,
  `risk_of_demolition` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK47veqt3e18dqrp9i1f0ejh5xs` (`application_id`),
  KEY `FK9os64e282rnuwpef4rwcxbvo9` (`created_by`),
  KEY `FK5ogn9b9qdu6a4150iep847kqv` (`updated_by`),
  CONSTRAINT `FK5ogn9b9qdu6a4150iep847kqv` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK9os64e282rnuwpef4rwcxbvo9` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKpec6i7o3k8mom5dvmwcfcm1vr` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_additional`
--

LOCK TABLES `site_visit_technical_additional` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_additional` DISABLE KEYS */;
INSERT INTO `site_visit_technical_additional` VALUES (1,30,'medium',19,'work in progress','2026-01-29 11:08:28.910848',_binary '\0',43,'2026-01-29','78235','saleDeed',_binary '','self','ntng','yes',_binary '\0',15,'reraYes',30,'NO',NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,14,'low',0,'2nd floor details','2026-02-03 09:50:39.608078',_binary '',0,'2026-02-04','qwer22','giftDeed',_binary '\0','4','','',_binary '',90,'',24,'MAYBE','2026-02-03 10:40:59.731260','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_technical_additional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_bua`
--

DROP TABLE IF EXISTS `site_visit_technical_bua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_bua` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `basements` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `floors` int DEFAULT NULL,
  `non_rcc` int DEFAULT NULL,
  `total_bua_actual` double DEFAULT NULL,
  `total_bua_approved` double DEFAULT NULL,
  `total_bua_document` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk4dgpbehjjlvr9vuhm2i9ibgs` (`application_id`),
  KEY `FK31p44h6t8qf2fv8tw616p71lm` (`created_by`),
  KEY `FKqat6thdppxntof3tgy7uliuey` (`updated_by_id`),
  CONSTRAINT `FK31p44h6t8qf2fv8tw616p71lm` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKegrlxtvalm1n6hqo7tg87akco` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKqat6thdppxntof3tgy7uliuey` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_bua`
--

LOCK TABLES `site_visit_technical_bua` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_bua` DISABLE KEYS */;
INSERT INTO `site_visit_technical_bua` VALUES (1,1,'2026-01-29 11:07:28.807172',1,1,1202,3,3,'2026-01-29 11:07:28.807172','GENITHG4GA6F','VALIXZUOJPP0','VALIXZUOJPP0'),(2,0,'2026-02-03 09:46:36.777988',3,1,49038,48178,49253,'2026-02-03 10:40:52.535201','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_technical_bua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_bua_levels`
--

DROP TABLE IF EXISTS `site_visit_technical_bua_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_bua_levels` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `area_actual` double DEFAULT NULL,
  `area_approved` double DEFAULT NULL,
  `area_document` double DEFAULT NULL,
  `level_order` int DEFAULT NULL,
  `level_type` varchar(255) NOT NULL,
  `bua_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcytlx7pao9bsdtae9iau0j51c` (`bua_id`),
  CONSTRAINT `FKcytlx7pao9bsdtae9iau0j51c` FOREIGN KEY (`bua_id`) REFERENCES `site_visit_technical_bua` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_bua_levels`
--

LOCK TABLES `site_visit_technical_bua_levels` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_bua_levels` DISABLE KEYS */;
INSERT INTO `site_visit_technical_bua_levels` VALUES (1,1200,1,1,0,'BASEMENT',1),(2,1,1,1,1,'GROUND_FLOOR',1),(3,1,1,1,2,'NON_RCC',1),(9,1200,1200,1200,0,'GROUND_FLOOR',2),(10,1200,1200,1200,1,'FIRST_FLOOR',2),(11,1200,1234,1200,2,'SECOND_FLOOR',2),(12,45438,44544,45653,3,'NON_RCC',2);
/*!40000 ALTER TABLE `site_visit_technical_bua_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_details`
--

DROP TABLE IF EXISTS `site_visit_technical_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age_of_property` int DEFAULT NULL,
  `no_of_basements` int DEFAULT NULL,
  `community_sensitivity` varchar(255) DEFAULT NULL,
  `construction_progress_percent` int DEFAULT NULL,
  `construction_progress_remarks` varchar(500) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `demolition_notification` bit(1) DEFAULT NULL,
  `development_in_vicinity_percent` int DEFAULT NULL,
  `document_executed_on` date DEFAULT NULL,
  `document_number` varchar(255) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `earlier_valuation_done` bit(1) DEFAULT NULL,
  `first_basement_actual` double DEFAULT NULL,
  `first_basement_approved` double DEFAULT NULL,
  `first_basement_document` double DEFAULT NULL,
  `first_floor_actual` double DEFAULT NULL,
  `first_floor_approved` double DEFAULT NULL,
  `first_floor_document` double DEFAULT NULL,
  `no_of_floors` int DEFAULT NULL,
  `ground_floor_actual` double DEFAULT NULL,
  `ground_floor_approved` double DEFAULT NULL,
  `ground_floor_document` double DEFAULT NULL,
  `holding_type` varchar(255) DEFAULT NULL,
  `in_favour_of` varchar(255) DEFAULT NULL,
  `land_area_actual` double DEFAULT NULL,
  `land_area_document` double DEFAULT NULL,
  `land_area_layout_plan` double DEFAULT NULL,
  `land_area_match` bit(1) DEFAULT NULL,
  `market_feedback` varchar(255) DEFAULT NULL,
  `municipal_notification` varchar(255) DEFAULT NULL,
  `negative_area_local_norms` bit(1) DEFAULT NULL,
  `no_of_non_rcc` int DEFAULT NULL,
  `non_rcc_actual` double DEFAULT NULL,
  `non_rcc_approved` double DEFAULT NULL,
  `non_rcc_document` double DEFAULT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  `recommended_funding_percent` int DEFAULT NULL,
  `rera_details` varchar(255) DEFAULT NULL,
  `residual_age_of_property` int DEFAULT NULL,
  `risk_of_demolition` varchar(255) DEFAULT NULL,
  `sbua_actual` double DEFAULT NULL,
  `sbua_approved` double DEFAULT NULL,
  `sbua_document` double DEFAULT NULL,
  `second_floor_actual` double DEFAULT NULL,
  `second_floor_approved` double DEFAULT NULL,
  `second_floor_document` double DEFAULT NULL,
  `total_bua_actual` double DEFAULT NULL,
  `total_bua_approved` double DEFAULT NULL,
  `total_bua_document` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b3ccdjywqw0p7sqvo14q63upe` (`application_id`),
  KEY `FKow6ypn3mbkh0c298t0w3qor54` (`created_by`),
  KEY `FKe7plfdy8ol65afic5flr7a91p` (`updated_by`),
  CONSTRAINT `FKe7plfdy8ol65afic5flr7a91p` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKjvr6wnrqrx602r2pahrgkm7kj` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKow6ypn3mbkh0c298t0w3qor54` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_details`
--

LOCK TABLES `site_visit_technical_details` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_visit_technical_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_land`
--

DROP TABLE IF EXISTS `site_visit_technical_land`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_land` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `holding_type` varchar(255) DEFAULT NULL,
  `land_area_as_per_actual` double DEFAULT NULL,
  `land_area_as_per_document` double DEFAULT NULL,
  `land_area_as_per_layout_plan` double DEFAULT NULL,
  `land_area_match` bit(1) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKi346tgybrcgeg3cmws5wq53ge` (`application_id`),
  KEY `FK3pg75oqqcidr94jml433pb85r` (`created_by`),
  KEY `FKqp0qs6mg8mduk22qec7t5hlel` (`updated_by`),
  CONSTRAINT `FK3pg75oqqcidr94jml433pb85r` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKlw1ebf6lpv8l6dc7vr5fjlpp5` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKqp0qs6mg8mduk22qec7t5hlel` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_land`
--

LOCK TABLES `site_visit_technical_land` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_land` DISABLE KEYS */;
INSERT INTO `site_visit_technical_land` VALUES (1,'2026-01-29 11:07:10.026151','Freehold',1200,1200,1200,_binary '',NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'2026-02-02 12:37:31.285923','Freehold',34000,33750,33750,_binary '\0','2026-02-03 10:40:41.790668','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_technical_land` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_land_images`
--

DROP TABLE IF EXISTS `site_visit_technical_land_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_land_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint DEFAULT NULL,
  `uploaded_date` datetime(6) DEFAULT NULL,
  `technical_land_id` bigint NOT NULL,
  `uploaded_by` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkm62n9mkhet0788opro5erpsi` (`technical_land_id`),
  KEY `FK5chlxiesbj6hxexqp0p0845g2` (`uploaded_by`),
  CONSTRAINT `FK5chlxiesbj6hxexqp0p0845g2` FOREIGN KEY (`uploaded_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKkm62n9mkhet0788opro5erpsi` FOREIGN KEY (`technical_land_id`) REFERENCES `site_visit_technical_land` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_land_images`
--

LOCK TABLES `site_visit_technical_land_images` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_land_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_visit_technical_land_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_plot`
--

DROP TABLE IF EXISTS `site_visit_technical_plot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_plot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `east_legal_document` varchar(255) DEFAULT NULL,
  `east_site_visit` varchar(255) DEFAULT NULL,
  `east_match` bit(1) DEFAULT NULL,
  `north_legal_document` varchar(255) DEFAULT NULL,
  `north_site_visit` varchar(255) DEFAULT NULL,
  `north_match` bit(1) DEFAULT NULL,
  `property_type` varchar(255) NOT NULL,
  `south_legal_document` varchar(255) DEFAULT NULL,
  `south_site_visit` varchar(255) DEFAULT NULL,
  `south_match` bit(1) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `west_legal_document` varchar(255) DEFAULT NULL,
  `west_site_visit` varchar(255) DEFAULT NULL,
  `west_match` bit(1) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa942tbfa7j9r97sllnk8ft015` (`application_id`),
  KEY `FKdt1lu4iqdsicely92gc26h74d` (`created_by`),
  KEY `FKtipte0ine9peg3sqretp4et4j` (`updated_by`),
  CONSTRAINT `FKdt1lu4iqdsicely92gc26h74d` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKg4tq6faxvcnekh24ma96ujfk1` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKtipte0ine9peg3sqretp4et4j` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_plot`
--

LOCK TABLES `site_visit_technical_plot` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_plot` DISABLE KEYS */;
INSERT INTO `site_visit_technical_plot` VALUES (1,'2026-01-29 11:06:58.296208','address1','address1',_binary '','address1','address1',_binary '','Independent','address1','address1',_binary '',NULL,'address1','address1',_binary '','GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'2026-02-02 12:35:16.388866','road','road',_binary '','commercial building','commercial building',_binary '','Independent','home','home',_binary '','2026-02-04 09:17:10.793490','home','home',_binary '','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_technical_plot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_technical_sbua`
--

DROP TABLE IF EXISTS `site_visit_technical_sbua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_technical_sbua` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `sbua_actual` double DEFAULT NULL,
  `sbua_approved` double DEFAULT NULL,
  `sbua_document` double DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) NOT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqqddjte5m3bgldo0q0k8e8aak` (`application_id`),
  KEY `FKt4ql5s0y7cogwb4him5r7kvcp` (`created_by`),
  KEY `FKllraf8vbb4uy329dlg5glxtxa` (`updated_by`),
  CONSTRAINT `FKj02yjuocpq6wfyr73fy88phkp` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKllraf8vbb4uy329dlg5glxtxa` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKt4ql5s0y7cogwb4him5r7kvcp` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_sbua`
--

LOCK TABLES `site_visit_technical_sbua` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_sbua` DISABLE KEYS */;
INSERT INTO `site_visit_technical_sbua` VALUES (1,'2026-01-29 11:07:36.266333',1200,1200,1200,NULL,'GENITHG4GA6F','VALIXZUOJPP0',NULL),(2,'2026-02-03 09:47:01.657307',1231,1425,12324,'2026-02-03 10:40:55.484935','GEN2NNSZBW8C','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_technical_sbua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_valuer_details`
--

DROP TABLE IF EXISTS `site_visit_valuer_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_valuer_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `organisation_seal_file_name` varchar(255) DEFAULT NULL,
  `organisation_seal_file_path` varchar(255) DEFAULT NULL,
  `organisation_seal_file_size` bigint DEFAULT NULL,
  `organisation_seal_file_type` varchar(255) DEFAULT NULL,
  `valuer_signature_file_name` varchar(255) DEFAULT NULL,
  `valuer_signature_file_path` varchar(255) DEFAULT NULL,
  `valuer_signature_file_size` bigint DEFAULT NULL,
  `valuer_signature_file_type` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_etig71baqvvg1bxx5v8h6bpam` (`application_id`),
  CONSTRAINT `FKhnm4ypqmquy65gwwfawr2au4d` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_valuer_details`
--

LOCK TABLES `site_visit_valuer_details` WRITE;
/*!40000 ALTER TABLE `site_visit_valuer_details` DISABLE KEYS */;
INSERT INTO `site_visit_valuer_details` VALUES (1,'download.jpg','C:\\app\\uploads\\GENITHG4GA6F\\site-visit\\valuer-details\\download.jpg',206545,'image/jpeg','manga-one-piece-wallpaper-preview.jpg','C:\\app\\uploads\\GENITHG4GA6F\\site-visit\\valuer-details\\manga-one-piece-wallpaper-preview.jpg',50338,'image/jpeg','GENITHG4GA6F'),(2,'Screenshot 2025-08-03 12-34-02.png','C:\\app\\uploads\\GEN2NNSZBW8C\\site-visit\\valuer-details\\Screenshot 2025-08-03 12-34-02.png',726195,'image/png','Screenshot 2025-09-11 11-53-35.png','C:\\app\\uploads\\GEN2NNSZBW8C\\site-visit\\valuer-details\\Screenshot 2025-09-11 11-53-35.png',1351108,'image/png','GEN2NNSZBW8C');
/*!40000 ALTER TABLE `site_visit_valuer_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_visit_valuer_remarks`
--

DROP TABLE IF EXISTS `site_visit_valuer_remarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_visit_valuer_remarks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `critical_actionable_remarks` tinytext,
  `general_remarks` tinytext,
  `insights` tinytext,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `created_by` varchar(12) DEFAULT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pgb7xub7y0gan6jj4jpesnaxq` (`application_id`),
  KEY `FKjl3t4hyuhfuhi80w6u7iv1btr` (`created_by`),
  KEY `FKh3bm788k4e517smsou0mmblk7` (`updated_by`),
  CONSTRAINT `FKh3bm788k4e517smsou0mmblk7` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKjl3t4hyuhfuhi80w6u7iv1btr` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKn3locg9e9w0o88u6huqja5o2b` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_valuer_remarks`
--

LOCK TABLES `site_visit_valuer_remarks` WRITE;
/*!40000 ALTER TABLE `site_visit_valuer_remarks` DISABLE KEYS */;
INSERT INTO `site_visit_valuer_remarks` VALUES (1,'2026-01-29 11:09:39.737342','ntng','ntng','ntng','2026-01-29 11:09:39.737342','GENITHG4GA6F','VALIXZUOJPP0','VALIXZUOJPP0');
/*!40000 ALTER TABLE `site_visit_valuer_remarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valuator_master`
--

DROP TABLE IF EXISTS `valuator_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valuator_master` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `valuator_last_name` varchar(255) NOT NULL,
  `valuator_name` varchar(255) NOT NULL,
  `agency_id` bigint NOT NULL,
  `created_by` varchar(12) DEFAULT NULL,
  `login_user_id` varchar(12) DEFAULT NULL,
  `updated_by` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_icloiepu9r66x05163jioxmvd` (`email`),
  UNIQUE KEY `UK_g9itj4mfumk1y945tuuiv9hiw` (`login_user_id`),
  KEY `FKmkpeqycof110jvgm0ya6oc739` (`agency_id`),
  KEY `FKldu12cruewjppmxjy9fs70k2t` (`created_by`),
  KEY `FKqaxwhf08k9e8a560n67rqwt48` (`updated_by`),
  CONSTRAINT `FKks8luwwh61kk8f1cmfmu1vg1k` FOREIGN KEY (`login_user_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKldu12cruewjppmxjy9fs70k2t` FOREIGN KEY (`created_by`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKmkpeqycof110jvgm0ya6oc739` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`),
  CONSTRAINT `FKqaxwhf08k9e8a560n67rqwt48` FOREIGN KEY (`updated_by`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valuator_master`
--

LOCK TABLES `valuator_master` WRITE;
/*!40000 ALTER TABLE `valuator_master` DISABLE KEYS */;
INSERT INTO `valuator_master` VALUES (1,'9876543210','2026-01-28 06:53:03.330787','rahul.sharma@gmail.com','2026-01-28 06:53:03.330787','Sharma','Rahul',1,'AGNF6K73481J','VAL714J0RACR','AGNF6K73481J'),(2,'9876543212','2026-01-28 07:08:46.747089','rohith.sharma@gmail.com','2026-01-28 07:08:46.747089','Sharma','Rohith',1,'AGNF6K73481J','VALGSKGGARUM','AGNF6K73481J'),(3,'8579685466','2026-01-28 07:34:18.556116','dhoni@gmail.com','2026-01-28 07:34:18.556116','Singh Dhoni','Mahendra',1,'AGNF6K73481J','VALG2YPK6CYN','AGNF6K73481J'),(4,'7584968755','2026-01-28 07:37:23.894087','virat@gmail.com','2026-01-28 07:37:23.894087','Kohli','Virat',1,'AGNF6K73481J','VALIXZUOJPP0','AGNF6K73481J'),(5,'9059521841','2026-02-03 10:29:13.233720','nagesh@gmail.com','2026-02-03 10:29:13.233720','u','nagesh',1,'AGNF6K73481J','VALK2TZPT41S','AGNF6K73481J'),(6,'8576958468','2026-02-05 12:20:28.764613','abhi@gmail.com','2026-02-05 12:20:28.764613','Kumar','Abhi',1,'AGNF6K73481J','VALD8D5T3QUZ','AGNF6K73481J'),(7,'8050139422','2026-02-06 09:59:27.727868','ammu@gmail.com','2026-02-06 09:59:27.727868','vishwa','Ammu',1,'AGNF6K73481J','VAL1LU6QP2HM','AGNF6K73481J'),(8,'9988776655','2026-02-06 11:09:42.204948','rahul.verma@securehome.com','2026-02-06 11:09:42.204948','Verma','Rahul',2,'AGNLJTVYQRCC','VAL8YYPOME3K','AGNLJTVYQRCC'),(9,'9972216772','2026-02-06 11:09:46.737289','siddu@gmail.com','2026-02-06 11:09:46.737289','h','siddu',1,'AGNF6K73481J','VALTMBC2N4IS','AGNF6K73481J');
/*!40000 ALTER TABLE `valuator_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-09 12:52:41
