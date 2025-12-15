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
  `agency_id` bigint DEFAULT NULL,
  `bank` varchar(255) NOT NULL,
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
INSERT INTO `admin_master` VALUES ('ADM6EER5Q34U',NULL,'HDFC','2025-12-10 06:15:56.183603','admin@example.com','Super','Admin','$2a$10$Gnp7.uN7vi7jPjBvE9aYYOmfyMb/yVu6NNHVv.KOnPy8piARsKGlm','8888888888','ADMIN'),('AGNPGCAAEXLN',1,'HDFC','2025-12-10 06:19:01.158543','agency1@gmail.com','Agency1','','$2a$10$pN/rkevsQuC7DDYlhzVK..WJZ5BmaIHuHFxLwrmMa2Bjvqd6dM1Ty','9876543210','AGENCY'),('VALU1QQS1IOP',1,'HDFC','2025-12-10 06:26:28.176153','Agent1@agency.com','Agent1','','$2a$10$2WkADDYp4SwkIq5MDIoCLe4v/qpKsi4Nqwcwp1OG5ZgWUpfTkaRBK','9876543211','AGENCY_VALUATOR');
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
  `bank` varchar(255) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_master`
--

LOCK TABLES `agency_master` WRITE;
/*!40000 ALTER TABLE `agency_master` DISABLE KEYS */;
INSERT INTO `agency_master` VALUES (1,'Agency1','HDFC','Bangalore','Agency1','9876543210','2025-12-10 06:19:01.077544',12.9716,77.5946,'https://maps.google.com/location','560001','Karnataka','MG Road','Near Metro Station','2025-12-10 06:19:01.077544','ADM6EER5Q34U','ADM6EER5Q34U');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_agency_assignment`
--

LOCK TABLES `application_agency_assignment` WRITE;
/*!40000 ALTER TABLE `application_agency_assignment` DISABLE KEYS */;
INSERT INTO `application_agency_assignment` VALUES (1,'2025-12-10 06:50:13.052548','Assigned to agency for verification','2025-12-10 06:50:13.052548',1,'GENGWAF90FFB','ADM6EER5Q34U','ADM6EER5Q34U'),(2,'2025-12-13 09:25:08.294518','Assigned to agency for verification','2025-12-13 09:25:08.294518',1,'GENE1PHPHS79','ADM6EER5Q34U','ADM6EER5Q34U'),(3,'2025-12-15 05:28:58.225452','Assigned to agency for verification','2025-12-15 05:28:58.225452',1,'GEN7Z0YPGPXE','ADM6EER5Q34U','ADM6EER5Q34U');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_customer_details`
--

LOCK TABLES `application_customer_details` WRITE;
/*!40000 ALTER TABLE `application_customer_details` DISABLE KEYS */;
INSERT INTO `application_customer_details` VALUES (1,'customer@example.com','Customer','one','HDFC','HomeLoan','','9876543210','PROP-67891','3BHK','Apartment','Customer interested in home loan, salary credited in HDFC','9876500001','Customer','GENGWAF90FFB'),(2,'basavaraj184@gmail.com','Basavaraj','S','HDFC','House ing','D','09731477948','123456','Hotel','Real estate','Good','','Ravi','GENAX7LV1J46'),(3,'','','','HDFC','House ing','','','','Hotel','Real estate','','','Ravi','GEN87JQ17SSA'),(4,'basavaraj184@gmail.com','Basavaraj','S','HDFC','House ing','D','09731477948','456123','Hotel','Real estate','Bad','','Ravi','GENE1PHPHS79'),(5,'basavaraj184@gmail.com','Basavaraj','S','HDFC','House ing','D','09731477948','1234','Hotel','Real estate','Hiiiii','','Ravi','GEN7Z0YPGPXE');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_document_details`
--

LOCK TABLES `application_document_details` WRITE;
/*!40000 ALTER TABLE `application_document_details` DISABLE KEYS */;
INSERT INTO `application_document_details` VALUES (3,'GEN7Z0YPGPXE'),(2,'GENE1PHPHS79'),(1,'GENGWAF90FFB');
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
  `valuator_id` varchar(12) DEFAULT NULL,
  `planned_site_visit_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5via16bmxxw4f1u6tt1iivj1o` (`assigned_to_id`),
  KEY `FK3i1wa7yaq1aotlrqmvw02on44` (`client_id`),
  KEY `FKnyxvhlv92mqgjeglnc0mgwht3` (`created_by_id`),
  KEY `FK6eln6s9dejl6mj18jvedtou1k` (`valuator_id`),
  CONSTRAINT `FK3i1wa7yaq1aotlrqmvw02on44` FOREIGN KEY (`client_id`) REFERENCES `customer_master` (`id`),
  CONSTRAINT `FK5via16bmxxw4f1u6tt1iivj1o` FOREIGN KEY (`assigned_to_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK6eln6s9dejl6mj18jvedtou1k` FOREIGN KEY (`valuator_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FKnyxvhlv92mqgjeglnc0mgwht3` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_master`
--

LOCK TABLES `application_master` WRITE;
/*!40000 ALTER TABLE `application_master` DISABLE KEYS */;
INSERT INTO `application_master` VALUES ('GEN7Z0YPGPXE',_binary '','HDFC','2025-12-15 04:50:44.098559','2025-12-15 05:28:58.283451','AGNPGCAAEXLN','GEN4S2980AMB','ADM6EER5Q34U',NULL,NULL),('GEN87JQ17SSA',_binary '','HDFC','2025-12-13 06:15:33.091175','2025-12-13 06:15:33.091175',NULL,'GEN0IZBI9IUH','ADM6EER5Q34U',NULL,NULL),('GENAX7LV1J46',_binary '','HDFC','2025-12-13 06:04:30.306551','2025-12-13 06:04:30.306551',NULL,'GEN4S2980AMB','ADM6EER5Q34U',NULL,NULL),('GENE1PHPHS79',_binary '','HDFC','2025-12-13 07:37:51.552837','2025-12-13 09:25:08.319409','AGNPGCAAEXLN','GEN4S2980AMB','ADM6EER5Q34U',NULL,NULL),('GENGWAF90FFB',_binary '','HDFC','2025-12-10 06:31:57.636853','2025-12-10 09:16:33.905072','AGNPGCAAEXLN','GENRCC00QMRU','ADM6EER5Q34U','VALU1QQS1IOP','2025-12-15');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_property_details`
--

LOCK TABLES `application_property_details` WRITE;
/*!40000 ALTER TABLE `application_property_details` DISABLE KEYS */;
INSERT INTO `application_property_details` VALUES (1,'Tech Park Residency','Bengaluru','India','202','560100','Karnataka','Electronic City Phase 1','Near Infosys','Green Homes','Bengaluru','India','101','560100','Karnataka','Main Road','Sector 5','GENGWAF90FFB'),(2,'3p','3p','India','3p','3p','3p','3p','3p',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'GENE1PHPHS79'),(3,'1p','1p','India','1p','1p','1p','1p','1p','1d','1d','India','1d','1d','1d','1d','1d','GEN7Z0YPGPXE');
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
  `stage` enum('CUSTOMER_DETAILS','PROPERTY_DETAILS','DOCUMENTS_UPLOADED','ASSIGN_AGENCY','APPLICATION_APPLIED','SUMMARY','ASSIGN_VALUATOR') NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `application_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrqmc7v07g6524ntv53f010re` (`application_id`),
  KEY `FKtpsct6oyf0vm72mm1nfhurd34` (`updated_by_id`),
  CONSTRAINT `FKsrqmc7v07g6524ntv53f010re` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKtpsct6oyf0vm72mm1nfhurd34` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_current`
--

LOCK TABLES `application_stage_current` WRITE;
/*!40000 ALTER TABLE `application_stage_current` DISABLE KEYS */;
INSERT INTO `application_stage_current` VALUES (1,'Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2025-12-10 07:00:31.542174','GENGWAF90FFB','ADM6EER5Q34U'),(2,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-13 06:04:30.452430','GENAX7LV1J46','ADM6EER5Q34U'),(3,'Updated to: CUSTOMER_DETAILS','CUSTOMER_DETAILS','2025-12-13 06:15:33.199800','GEN87JQ17SSA','ADM6EER5Q34U'),(4,'Updated to: PROPERTY_DETAILS','PROPERTY_DETAILS','2025-12-13 11:25:05.092845','GENE1PHPHS79','ADM6EER5Q34U'),(5,'Updated to: APPLICATION_APPLIED','APPLICATION_APPLIED','2025-12-15 07:35:52.402424','GEN7Z0YPGPXE','ADM6EER5Q34U');
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
  `status` enum('IN_PROGRESS','APPROVED','REJECTED','ON_HOLD','CANCELLED','VALUATOR_ASSIGNED','ASSIGN_VALUATOR','SITE_VISIT_SCHEDULED','SITE_VISIT_COMPLETED','REPORT_SUBMITTED','SITE_VISIT_IN_PROGRESS') NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `application_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpid1k9d40v1j81u1b98p41xm` (`application_id`),
  KEY `FKmaje5q5g4k1c9fxr8ic1ya31e` (`updated_by_id`),
  CONSTRAINT `FKjpid1k9d40v1j81u1b98p41xm` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`),
  CONSTRAINT `FKmaje5q5g4k1c9fxr8ic1ya31e` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_history`
--

LOCK TABLES `application_stage_history` WRITE;
/*!40000 ALTER TABLE `application_stage_history` DISABLE KEYS */;
INSERT INTO `application_stage_history` VALUES (4,'2025-12-13 06:04:30.459596','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-13 06:04:30.459596','GENAX7LV1J46','ADM6EER5Q34U'),(5,'2025-12-13 06:15:33.207763','Stage updated to: CUSTOMER_DETAILS','IN_PROGRESS','2025-12-13 06:15:33.207763','GEN87JQ17SSA','ADM6EER5Q34U'),(7,'2025-12-13 07:37:51.592833','Stage updated to: PROPERTY_DETAILS','IN_PROGRESS','2025-12-13 11:25:05.106844','GENE1PHPHS79','ADM6EER5Q34U'),(8,'2025-12-15 04:50:44.192338','Stage updated to: APPLICATION_APPLIED','IN_PROGRESS','2025-12-15 07:35:52.418430','GEN7Z0YPGPXE','ADM6EER5Q34U'),(9,'2025-12-15 10:46:20.863934','Site visit completed with valuer remarks','SITE_VISIT_COMPLETED','2025-12-15 10:46:20.863934','GENGWAF90FFB','VALU1QQS1IOP');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_summary`
--

LOCK TABLES `application_summary` WRITE;
/*!40000 ALTER TABLE `application_summary` DISABLE KEYS */;
INSERT INTO `application_summary` VALUES (1,'2025-12-10 07:00:31.513911','Approved after verification','GENGWAF90FFB','ADM6EER5Q34U'),(2,'2025-12-15 07:35:52.387426','Approved after verification','GEN7Z0YPGPXE','ADM6EER5Q34U');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_uploaded_documents`
--

LOCK TABLES `application_uploaded_documents` WRITE;
/*!40000 ALTER TABLE `application_uploaded_documents` DISABLE KEYS */;
INSERT INTO `application_uploaded_documents` VALUES (1,'Document','WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',255,'image/jpeg','uploads\\091ce392-a64b-4af0-bf95-be999462229c-WhatsApp Image 2025-09-12 at 9.57.59 AM (1).jpeg',1),(2,'AADHAAR','DBBPLB3_Aug2025.csv',38,'text/csv','uploads\\f0513ef6-ff4a-4263-85a3-09b785ddd5ed-DBBPLB3_Aug2025.csv',2),(3,'PAN','DBBPLPSKS_Aug2025.csv',2,'text/csv','uploads\\9ec2361a-0f5a-4125-823e-d8eb711136ae-DBBPLPSKS_Aug2025.csv',2),(4,'EKATHA','Mall(Dec 01 to Dec 09).csv',1044,'text/csv','uploads\\3d4a649f-9842-4667-a24b-f887d65994b1-Mall(Dec 01 to Dec 09).csv',2),(5,'EXTRA_1','Mall_Nove.csv',3501,'text/csv','uploads\\084f4a7c-3c5e-4268-9970-50809467af03-Mall_Nove.csv',2),(6,'EXTRA_2','PPLKOC2.csv',658,'text/csv','uploads\\f2039c83-b6c7-4480-ade3-456952fcb946-PPLKOC2.csv',2),(7,'AADHAAR','Mall(Dec 01 to Dec 09).csv',1044,'text/csv','uploads\\ab571cd7-a1a2-4e51-bac0-9b55d8a6d775-Mall(Dec 01 to Dec 09).csv',2),(8,'PAN','PPLKOC1.csv',500,'text/csv','uploads\\aefef9e3-77c6-4ceb-8e9a-fbd3c59b513e-PPLKOC1.csv',2),(9,'EKATHA','Railways(Dec 01 to Dec 09).csv',72,'text/csv','uploads\\b5169164-51b2-464b-99fb-d29f4e259c79-Railways(Dec 01 to Dec 09).csv',2),(10,'EXTRA_1','Temp_Nove.csv',184,'text/csv','uploads\\a172f72c-62fa-44bf-95f8-f09a5379f7a4-Temp_Nove.csv',2),(11,'EXTRA_2','PPLKOC2.csv',658,'text/csv','uploads\\9eb893f3-7dd4-4b9c-b911-cd51ee6ddcfb-PPLKOC2.csv',2),(12,'AADHAAR','file-sample_150kB- Copy(1).pdf',139,'application/pdf','uploads\\be487d55-b57c-4150-b48a-1e33cf45da40-file-sample_150kB- Copy(1).pdf',3),(13,'PAN','file-sample_150kB - Copy (2).pdf',139,'application/pdf','uploads\\68eb9229-8605-412f-89b6-d8faf4fe1d1f-file-sample_150kB - Copy (2).pdf',3),(14,'EKATHA','file-sample_150kB - Copy (3).pdf',139,'application/pdf','uploads\\5c703558-8c80-406a-bf32-1bb754c18ee1-file-sample_150kB - Copy (3).pdf',3),(15,'EXTRA_1','file-sample_150kB - Copy(5).pdf',139,'application/pdf','uploads\\0aff1eb8-8984-4f2c-b45f-5f81bbe2e088-file-sample_150kB - Copy(5).pdf',3),(16,'EXTRA_2','file-sample_150kB- Copy(1).pdf',139,'application/pdf','uploads\\d0c94264-a979-4eb7-a91c-8853b0df5eac-file-sample_150kB- Copy(1).pdf',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_valuator_assignment`
--

LOCK TABLES `application_valuator_assignment` WRITE;
/*!40000 ALTER TABLE `application_valuator_assignment` DISABLE KEYS */;
INSERT INTO `application_valuator_assignment` VALUES (10,'2025-12-10 08:50:41.461054','Assigning to valuator for site inspection','2025-12-10 08:50:41.461054','GENGWAF90FFB','AGNPGCAAEXLN','VALU1QQS1IOP');
/*!40000 ALTER TABLE `application_valuator_assignment` ENABLE KEYS */;
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
INSERT INTO `basic_valuation_details` VALUES (2,'2025-12-13 07:27:22.153098','HomeLoan','Customer  one','Customer  one','Self','2025-12-15','2025-12-13 07:27:22.153098','GENGWAF90FFB','VALU1QQS1IOP','VALU1QQS1IOP');
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
INSERT INTO `basic_valuation_documents` VALUES (2,'Sale Deed Copy'),(2,'EC Certificate'),(2,'Property Tax Receipt'),(2,'Aadhaar Copy');
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
  `bank` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','AGENCY_VALUATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_snf65l86t4b0xj6v0f9nymegs` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
INSERT INTO `customer_master` VALUES ('GEN0IZBI9IUH','HDFC','2025-12-13 06:15:33.177441','','','','$2a$10$QOj7.Fx9GS8y3d0hLpsmruAyml9d3anTXflQgXi/OgDC861D1AsEy','','USER'),('GEN4S2980AMB','HDFC','2025-12-13 06:04:30.404962','basavaraj184@gmail.com','Basavaraj','S','$2a$10$w1nREBrqmaM2yWdruT4fhevHvbA2XQCyyOQhrm55wf.gQfoRHAsC2','09731477948','USER'),('GENRCC00QMRU','HDFC','2025-12-10 06:31:57.720602','customer@example.com','Customer','one','$2a$10$W8.ntH3tBr7CkWsktNM.7OQ5djquKJeqmB1MC1bdboYzsoledWA8S','9876543210','USER');
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_building_details`
--

LOCK TABLES `site_visit_building_details` WRITE;
/*!40000 ALTER TABLE `site_visit_building_details` DISABLE KEYS */;
INSERT INTO `site_visit_building_details` VALUES (1,_binary '','Good','RCC','2025-12-13 10:53:45.193504','Vitrified Tiles','2022-08-14 18:30:00.000000','BBMP/PLAN/2022/4587',_binary '\0','','BBMP','RCC','3 Star','2025-12-13 10:55:05.224841','GENGWAF90FFB','VALU1QQS1IOP','VALU1QQS1IOP');
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
INSERT INTO `site_visit_image_files` VALUES (1,'PROPERTY_SPECIFIC','property_specific-1765787930649.png','C:\\app\\uploads\\GENGWAF90FFB\\site-visit\\images\\property_specific\\property_specific-1765787930649.png',20602,'image/png',1),(2,'UNIT_SPECIFIC','unit_specific-1765787930661.png','C:\\app\\uploads\\GENGWAF90FFB\\site-visit\\images\\unit_specific\\unit_specific-1765787930661.png',55704,'image/png',1),(3,'COMPARISON','comparison-1765787930666.png','C:\\app\\uploads\\GENGWAF90FFB\\site-visit\\images\\comparison\\comparison-1765787930666.png',20602,'image/png',1);
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
INSERT INTO `site_visit_images` VALUES (1,'GENGWAF90FFB');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_infrastructure_details`
--

LOCK TABLES `site_visit_infrastructure_details` WRITE;
/*!40000 ALTER TABLE `site_visit_infrastructure_details` DISABLE KEYS */;
INSERT INTO `site_visit_infrastructure_details` VALUES (1,'Concrete Road','Main Road','2025-12-13 11:41:32.109657',_binary '',2,'30 Feet',_binary '',_binary '',NULL,'Municipal Corporation','GENGWAF90FFB','VALU1QQS1IOP',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_boundary_details`
--

LOCK TABLES `site_visit_property_boundary_details` WRITE;
/*!40000 ALTER TABLE `site_visit_property_boundary_details` DISABLE KEYS */;
INSERT INTO `site_visit_property_boundary_details` VALUES (1,_binary '\0','2025-12-13 10:36:07.550422','Residential Zone',_binary '','30 ft road','30 ft road',_binary '','Private property','Private property',_binary '','East',_binary '','Residential building','Residential building',_binary '','2025-12-13 10:36:07.552422','Drain','Open land',_binary '\0','GENGWAF90FFB','VALU1QQS1IOP','VALU1QQS1IOP');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_details`
--

LOCK TABLES `site_visit_property_details` WRITE;
/*!40000 ALTER TABLE `site_visit_property_details` DISABLE KEYS */;
INSERT INTO `site_visit_property_details` VALUES (1,'2025-12-13 10:34:16.064544',7.5,'Sai Residency','Bangalore','12A','560045','Karnataka','Chiranjeevi Layout, Hebbal','Near Bus Stop','BBMP',13.035542,77.5971,'Hebbal Flyover','Sai Residency','Bangalore','12A','560045','Karnataka','Chiranjeevi Layout, Hebbal','Near Bus Stop','Apartment','Residential',NULL,'GENGWAF90FFB','VALU1QQS1IOP',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_property_value_assessment`
--

LOCK TABLES `site_visit_property_value_assessment` WRITE;
/*!40000 ALTER TABLE `site_visit_property_value_assessment` DISABLE KEYS */;
INSERT INTO `site_visit_property_value_assessment` VALUES (1,250000.00,250000.00,'Swimming Pool','Low','3 Star',3600000.00,3800000.00,2200.00,5280000.00,5500000.00,5720000.00,4800000.00,5000000.00,5200000.00,6000000.00,6200000.00,6500000.00,6500000.00,6800000.00,7000000.00,7300000.00,6000000.00,6200000.00,6500000.00,1800.00,4320000.00,4500000.00,4680000.00,4050000.00,4500000.00,4700000.00,4900000.00,4000000.00,4200000.00,4400000.00,2400.00,2500.00,2600.00,'GENGWAF90FFB');
/*!40000 ALTER TABLE `site_visit_property_value_assessment` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_technical_details`
--

LOCK TABLES `site_visit_technical_details` WRITE;
/*!40000 ALTER TABLE `site_visit_technical_details` DISABLE KEYS */;
INSERT INTO `site_visit_technical_details` VALUES (1,5,1,'Low',75,'First floor slab completed','2025-12-13 12:18:36.875123',_binary '\0',80,'2020-05-20','SD/12345/2020','Sale Deed',_binary '\0',NULL,NULL,NULL,900,900,900,2,1000,1000,1000,'Freehold','Ramesh Kumar',1200,1200,1200,_binary '','Good demand','None',_binary '\0',0,NULL,NULL,NULL,'Independent House',70,'RERA12345',55,'No',2200,2200,2200,NULL,NULL,NULL,1900,1900,1900,'2025-12-15 05:47:55.269693','GENGWAF90FFB','VALU1QQS1IOP',NULL);
/*!40000 ALTER TABLE `site_visit_technical_details` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_visit_valuer_details`
--

LOCK TABLES `site_visit_valuer_details` WRITE;
/*!40000 ALTER TABLE `site_visit_valuer_details` DISABLE KEYS */;
INSERT INTO `site_visit_valuer_details` VALUES (1,'organisation-seal.png','C:\\app\\uploads\\GENGWAF90FFB\\site-visit\\valuer-details\\organisation-seal.png',70318,'image/png','valuer-signature.png','C:\\app\\uploads\\GENGWAF90FFB\\site-visit\\valuer-details\\valuer-signature.png',11847,'image/png','GENGWAF90FFB');
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
INSERT INTO `site_visit_valuer_remarks` VALUES (1,'2025-12-15 08:53:04.982896','Minor structural cracks observed on the north-facing wall. Immediate repair is recommended.','The property is located in a well-developed residential area with good road connectivity.','Considering the location and future infrastructure plans, the property value is expected to appreciate.','2025-12-15 10:46:20.858975','GENGWAF90FFB','VALU1QQS1IOP','VALU1QQS1IOP');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valuator_master`
--

LOCK TABLES `valuator_master` WRITE;
/*!40000 ALTER TABLE `valuator_master` DISABLE KEYS */;
INSERT INTO `valuator_master` VALUES (1,'9876543211','2025-12-10 06:26:28.195150','Agent1@agency.com','2025-12-10 06:26:28.195150','Agent1',1,'AGNPGCAAEXLN','VALU1QQS1IOP','AGNPGCAAEXLN');
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

-- Dump completed on 2025-12-15 17:01:23
