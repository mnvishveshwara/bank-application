-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: bank_application
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `admin_master`
--

DROP TABLE IF EXISTS `admin_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_master` (
  `id` varchar(12) NOT NULL,
  `bank` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN') NOT NULL,
  `agency_id` bigint DEFAULT NULL,
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
INSERT INTO `admin_master` VALUES ('NTJMPM05O7GQ','HDFC','2025-11-27 09:49:58.709885','admin@example.com','Super','Admin','$2a$10$hKkooE/JW5GZ5Hyxd/4Nk.PEhmHo4JsIuqpBkCuywqGNXNqGbyL8W','8888888888','ADMIN',NULL);
/*!40000 ALTER TABLE `admin_master` ENABLE KEYS */;
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
  `created_by` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `map_url` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street_line_1` varchar(255) DEFAULT NULL,
  `street_line_2` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `owner_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK783n8d7bqsmttrp0nmqk6a9tr` (`agency_name`),
  KEY `FK6p7s3eeqg0m7ec9rcwktkx44l` (`owner_id`),
  CONSTRAINT `FK6p7s3eeqg0m7ec9rcwktkx44l` FOREIGN KEY (`owner_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_master`
--

LOCK TABLES `agency_master` WRITE;
/*!40000 ALTER TABLE `agency_master` DISABLE KEYS */;
INSERT INTO `agency_master` VALUES (1,'ABC Home Loans','Bengaluru','Rohit Sharma','9876543210','2025-11-28 11:01:23.632408','anonymousUser',12.934533,77.626579,'https://maps.google.com/?q=12.934533,77.626579','560076','Karnataka','12th Main Road','BTM 2nd Stage','2025-11-28 11:01:23.632408','anonymousUser',NULL),(2,'ABC Home Loan','Bengaluru','Rohit Sharma','9876543210','2025-11-28 11:02:22.028217','anonymousUser',12.934533,77.626579,'https://maps.google.com/?q=12.934533,77.626579','560076','Karnataka','12th Main Road','BTM 2nd Stage','2025-11-28 11:02:22.029182','anonymousUser',NULL),(3,'ABC Home','Bengaluru','Rohit Sharma','9876543210','2025-11-28 11:04:11.307425','anonymousUser',12.934533,77.626579,'https://maps.google.com/?q=12.934533,77.626579','560076','Karnataka','12th Main Road','BTM 2nd Stage','2025-11-28 11:04:11.307425','anonymousUser',NULL);
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
  `agency_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `assigned_by_id` varchar(12) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `agency_id` bigint NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o7ui04p9qoxafqo3d4rvs0bg3` (`application_id`),
  UNIQUE KEY `UKo7ui04p9qoxafqo3d4rvs0bg3` (`application_id`),
  KEY `FKdn09s1voqmsp0hhht1lix7stx` (`assigned_by_id`),
  KEY `FKrik187xwno6xldb3xw5oe7b4l` (`agency_id`),
  KEY `FKg62bkhou7mpctsq9vqirwq6tt` (`created_by_id`),
  KEY `FK3y826nahijs2ey0cs4bitdcxx` (`updated_by_id`),
  CONSTRAINT `FK3y826nahijs2ey0cs4bitdcxx` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKdn09s1voqmsp0hhht1lix7stx` FOREIGN KEY (`assigned_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKg62bkhou7mpctsq9vqirwq6tt` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKqak8xnu6oufset718k8w2752i` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKrik187xwno6xldb3xw5oe7b4l` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_agency_assignment`
--

LOCK TABLES `application_agency_assignment` WRITE;
/*!40000 ALTER TABLE `application_agency_assignment` DISABLE KEYS */;
INSERT INTO `application_agency_assignment` VALUES (1,NULL,NULL,'YLIN39EED91B',NULL,'2025-11-28 11:07:47.194832','Assigned to agency for verification','2025-11-28 11:07:47.194832',1,'NTJMPM05O7GQ','NTJMPM05O7GQ');
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
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2n7si84ma0xs5naoornbmu6sg` (`application_id`),
  CONSTRAINT `FKgnkja65d1epkslgpywla5aspk` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_customer_details`
--

LOCK TABLES `application_customer_details` WRITE;
/*!40000 ALTER TABLE `application_customer_details` DISABLE KEYS */;
INSERT INTO `application_customer_details` VALUES (1,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','2BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','YLIN39EED91B'),(2,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','DH9SZFUZB0PG'),(3,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','DWJCDECN4J24'),(4,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','D5G13YAM4QA2'),(5,'karan.siddu@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','1111111111','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','siddu','G4F2N164QD4D'),(6,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','S8YLPPEL493H'),(7,'basavaraj184@gmail.com','Basavaraj','S','HDFC','','D','09731477948','123456','','','Good','09731477948','Ravi','KTT4NZIXNZPH'),(8,'karan.yadav@example.com','Karan','Yadav','HDFC','HomeLoan','Singh','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Ravi','6X3X9SG1Y1GQ');
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
  `aadhaar_uploaded` bit(1) DEFAULT NULL,
  `income_proof_uploaded` bit(1) DEFAULT NULL,
  `pan_uploaded` bit(1) DEFAULT NULL,
  `property_doc_uploaded` bit(1) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nxt1jipi0tj06m4wbh4s9i8hy` (`application_id`),
  CONSTRAINT `FK2bmpcba07ebyoq1nuubau8wdq` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_document_details`
--

LOCK TABLES `application_document_details` WRITE;
/*!40000 ALTER TABLE `application_document_details` DISABLE KEYS */;
INSERT INTO `application_document_details` VALUES (1,NULL,NULL,NULL,NULL,NULL,'YLIN39EED91B');
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
  `associated_bank` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `assigned_to_id` varchar(12) DEFAULT NULL,
  `client_id` varchar(12) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5via16bmxxw4f1u6tt1iivj1o` (`assigned_to_id`),
  KEY `FK3i1wa7yaq1aotlrqmvw02on44` (`client_id`),
  KEY `FKnyxvhlv92mqgjeglnc0mgwht3` (`created_by_id`),
  CONSTRAINT `FK3i1wa7yaq1aotlrqmvw02on44` FOREIGN KEY (`client_id`) REFERENCES `customer_master` (`id`),
  CONSTRAINT `FK5via16bmxxw4f1u6tt1iivj1o` FOREIGN KEY (`assigned_to_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKnyxvhlv92mqgjeglnc0mgwht3` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_master`
--

LOCK TABLES `application_master` WRITE;
/*!40000 ALTER TABLE `application_master` DISABLE KEYS */;
INSERT INTO `application_master` VALUES ('6X3X9SG1Y1GQ',_binary '','HDFC','2025-12-03 06:59:18.406595','2025-12-03 06:59:18.406595',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('D5G13YAM4QA2',_binary '','HDFC','2025-11-29 06:59:43.989914','2025-11-29 06:59:43.989914',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('DH9SZFUZB0PG',_binary '','HDFC','2025-11-27 10:49:06.907655','2025-11-27 10:49:06.907655',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('DWJCDECN4J24',_binary '','HDFC','2025-11-27 11:53:54.416810','2025-11-27 11:53:54.416810',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('EG5UWXOAG0QD',_binary '','HDFC','2025-12-01 06:30:11.027872','2025-12-01 06:30:11.027872',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('G4F2N164QD4D',_binary '','HDFC','2025-12-01 06:32:13.495446','2025-12-01 06:32:13.495446',NULL,'IX4VQDTG3305','NTJMPM05O7GQ'),('KTT4NZIXNZPH',_binary '','HDFC','2025-12-03 06:57:50.970708','2025-12-03 06:57:50.970708',NULL,'MR9AP1YVNRLE','NTJMPM05O7GQ'),('S8YLPPEL493H',_binary '','HDFC','2025-12-03 06:57:36.839304','2025-12-03 06:57:36.839304',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ'),('YLIN39EED91B',_binary '','HDFC','2025-11-27 09:53:40.798569','2025-11-27 09:53:40.798569',NULL,'HKC9G4WVIK71','NTJMPM05O7GQ');
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
  `application_id` varchar(12) NOT NULL,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6g6nrt05td345vhy6d0mx0ji9` (`application_id`),
  CONSTRAINT `FK1ftmi4k1rq52ts9gv21a0evy6` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_property_details`
--

LOCK TABLES `application_property_details` WRITE;
/*!40000 ALTER TABLE `application_property_details` DISABLE KEYS */;
INSERT INTO `application_property_details` VALUES (1,'YLIN39EED91B','Tech Park Residency','Bengaluru','India','202','560100','Karnataka','Electronic City Phase 1','Near Infosys','Green Homes','Bengaluru','India','101','560100','Karnataka','Main Road','Sector 5'),(2,'DWJCDECN4J24','Sunshine','Bengaluru','India','305','560102','Karnataka','Main Street','Sector 2','Green Residencyss','Bengaluru','India','12A','560045','Karnataka','1st Cross Road','Near Park');
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
  `remark` varchar(255) DEFAULT NULL,
  `stage` enum('CUSTOMER_DETAILS','PROPERTY_DETAILS','DOCUMENTS_UPLOADED','ASSIGN_AGENCY','SUMMARY') NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `application_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrqmc7v07g6524ntv53f010re` (`application_id`),
  KEY `FKtpsct6oyf0vm72mm1nfhurd34` (`updated_by_id`),
  CONSTRAINT `FKsrqmc7v07g6524ntv53f010re` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKtpsct6oyf0vm72mm1nfhurd34` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_current`
--

LOCK TABLES `application_stage_current` WRITE;
/*!40000 ALTER TABLE `application_stage_current` DISABLE KEYS */;
INSERT INTO `application_stage_current` VALUES (4,'Updated to: ASSIGN_AGENCY','ASSIGN_AGENCY','2025-11-29 07:11:11.535582','YLIN39EED91B','NTJMPM05O7GQ'),(5,'Updated to: PROPERTY_DETAILS','PROPERTY_DETAILS','2025-11-27 11:54:32.244221','DWJCDECN4J24','NTJMPM05O7GQ'),(6,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-11-29 06:59:44.079383','D5G13YAM4QA2','NTJMPM05O7GQ'),(7,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-01 06:32:13.632445','G4F2N164QD4D','NTJMPM05O7GQ'),(8,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-03 06:57:36.917422','S8YLPPEL493H','NTJMPM05O7GQ'),(9,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-03 06:57:51.064455','KTT4NZIXNZPH','NTJMPM05O7GQ'),(10,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-03 06:59:18.425724','6X3X9SG1Y1GQ','NTJMPM05O7GQ');
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
  `status` enum('IN_PROGRESS','APPROVED','REJECTED','ON_HOLD','CANCELLED') NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpid1k9d40v1j81u1b98p41xm` (`application_id`),
  KEY `FKmaje5q5g4k1c9fxr8ic1ya31e` (`updated_by_id`),
  CONSTRAINT `FKjpid1k9d40v1j81u1b98p41xm` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKmaje5q5g4k1c9fxr8ic1ya31e` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_history`
--

LOCK TABLES `application_stage_history` WRITE;
/*!40000 ALTER TABLE `application_stage_history` DISABLE KEYS */;
INSERT INTO `application_stage_history` VALUES (4,'2025-11-27 11:53:37.660371','Stage updated to: ASSIGN_AGENCY','IN_PROGRESS','2025-11-29 07:11:11.561573','YLIN39EED91B','NTJMPM05O7GQ'),(5,'2025-11-27 11:53:54.450771','Stage updated to: PROPERTY_DETAILS','IN_PROGRESS','2025-11-27 11:54:32.264205','DWJCDECN4J24','NTJMPM05O7GQ'),(6,'2025-11-29 06:59:44.089401','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-11-29 06:59:44.089401','D5G13YAM4QA2','NTJMPM05O7GQ'),(7,'2025-12-01 06:32:13.647462','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-01 06:32:13.646461','G4F2N164QD4D','NTJMPM05O7GQ'),(8,'2025-12-03 06:57:36.933047','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-03 06:57:36.933047','S8YLPPEL493H','NTJMPM05O7GQ'),(9,'2025-12-03 06:57:51.080064','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-03 06:57:51.080064','KTT4NZIXNZPH','NTJMPM05O7GQ'),(10,'2025-12-03 06:59:18.429702','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-03 06:59:18.429702','6X3X9SG1Y1GQ','NTJMPM05O7GQ');
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
  `final_approved_amount` double DEFAULT NULL,
  `reviewed_date` datetime(6) DEFAULT NULL,
  `summary_text` varchar(255) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `reviewed_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4vdbkuerla86ifnqadso62kfq` (`application_id`),
  KEY `FK4nnhacbuqayddljvcijgcjaxy` (`reviewed_by_id`),
  CONSTRAINT `FK4nnhacbuqayddljvcijgcjaxy` FOREIGN KEY (`reviewed_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKcxdd7gfquslysc5ef134c0xuv` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_summary`
--

LOCK TABLES `application_summary` WRITE;
/*!40000 ALTER TABLE `application_summary` DISABLE KEYS */;
INSERT INTO `application_summary` VALUES (1,6000000,'2025-11-29 07:12:57.610782','Approved after verification','YLIN39EED91B','NTJMPM05O7GQ');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_uploaded_documents`
--

LOCK TABLES `application_uploaded_documents` WRITE;
/*!40000 ALTER TABLE `application_uploaded_documents` DISABLE KEYS */;
INSERT INTO `application_uploaded_documents` VALUES (2,'Addhar','WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',255,'image/jpeg','uploads\\bb719593-5607-44a9-bd91-c32cc486f3eb-WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',1),(3,'Pan','WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',255,'image/jpeg','uploads\\32636cf0-2b29-4ccb-a67a-14f87930ee3a-WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',1),(4,'Bank','WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',255,'image/jpeg','uploads\\ab7712bd-d6e8-49fa-829f-d89eb84ad87a-WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',1),(5,'General','Screenshot 2025-09-24 165638.png',11,'image/png','uploads\\9530577c-22d1-4a7a-be51-ce57cd692fb0-Screenshot 2025-09-24 165638.png',1),(6,'Site','Screenshot 2025-09-16 161519.png',54,'image/png','uploads\\8b9a7109-9d0f-42a7-96bf-1896d112c893-Screenshot 2025-09-16 161519.png',1),(7,'Document','Screenshot 2025-09-24 153750.png',20,'image/png','uploads\\2edbd4ab-b33a-4c2b-b622-fdcfc06df6bd-Screenshot 2025-09-24 153750.png',1);
/*!40000 ALTER TABLE `application_uploaded_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_master` (
  `id` varchar(12) NOT NULL,
  `bank` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_snf65l86t4b0xj6v0f9nymegs` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
INSERT INTO `customer_master` VALUES ('HKC9G4WVIK71','HDFC','2025-11-27 09:53:40.878589','karan.yadav@example.com','Karan','Yadav','$2a$10$bkJYgMHwsQivtM87NvNfkeIWQ4d0qADzz9fZvOB/W/HlhpsL5FAdK','9876543210','USER'),('IX4VQDTG3305','HDFC','2025-12-01 06:32:13.581460','karan.siddu@example.com','Karan','Yadav','$2a$10$gBj4jx5Woa1JlDV5ZI/7fuyFDGqWNe.YNaYlBH2ieu4JcC07tS1D2','1111111111','USER'),('MR9AP1YVNRLE','HDFC','2025-12-03 06:57:51.048829','basavaraj184@gmail.com','Basavaraj','S','$2a$10$qKRIJJxayAfSH2TbVe2t4OCIvsTTYc.7q9v13NipOdY3N5KUujEG6','09731477948','USER');
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-03 12:33:54
