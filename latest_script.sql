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
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','VALUATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8yh1c2wc8lv1tt8m5q65gat8d` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_master`
--

LOCK TABLES `admin_master` WRITE;
/*!40000 ALTER TABLE `admin_master` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_master`
--

LOCK TABLES `agency_master` WRITE;
/*!40000 ALTER TABLE `agency_master` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_agency_assignment`
--

LOCK TABLES `application_agency_assignment` WRITE;
/*!40000 ALTER TABLE `application_agency_assignment` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_customer_details`
--

LOCK TABLES `application_customer_details` WRITE;
/*!40000 ALTER TABLE `application_customer_details` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_document_details`
--

LOCK TABLES `application_document_details` WRITE;
/*!40000 ALTER TABLE `application_document_details` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_property_details`
--

LOCK TABLES `application_property_details` WRITE;
/*!40000 ALTER TABLE `application_property_details` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_current`
--

LOCK TABLES `application_stage_current` WRITE;
/*!40000 ALTER TABLE `application_stage_current` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_stage_history`
--

LOCK TABLES `application_stage_history` WRITE;
/*!40000 ALTER TABLE `application_stage_history` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_summary`
--

LOCK TABLES `application_summary` WRITE;
/*!40000 ALTER TABLE `application_summary` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_uploaded_documents`
--

LOCK TABLES `application_uploaded_documents` WRITE;
/*!40000 ALTER TABLE `application_uploaded_documents` DISABLE KEYS */;
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
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','VALUATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_snf65l86t4b0xj6v0f9nymegs` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
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

-- Dump completed on 2025-12-04 14:51:27
