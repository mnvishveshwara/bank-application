-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: loan_application_db
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
  `id` varchar(12) NOT NULL COMMENT 'Unique user ID',
  `bank` varchar(255) NOT NULL COMMENT 'Associated bank name',
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL COMMENT 'Encrypted password',
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','BANKER','VALUATOR') NOT NULL,
  `is_active` bit(1) DEFAULT b'1' COMMENT 'Active status',
  `agency_id` bigint DEFAULT NULL COMMENT 'Reference to agency if Agent/Valuator',
  `created_by_id` varchar(12) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_admin_email` (`email`),
  KEY `idx_admin_role` (`role`),
  KEY `idx_admin_agency` (`agency_id`),
  KEY `FK_admin_created_by` (`created_by_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Admin, Banker, Agent, Valuator users';
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
  `agency_code` varchar(50) DEFAULT NULL COMMENT 'Unique agency code',
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `street_line_1` varchar(255) DEFAULT NULL,
  `street_line_2` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `map_url` varchar(500) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `owner_id` varchar(12) DEFAULT NULL COMMENT 'Agency owner reference',
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_agency_name` (`agency_name`),
  UNIQUE KEY `UK_agency_code` (`agency_code`),
  KEY `FK_agency_owner` (`owner_id`),
  KEY `idx_agency_city` (`city`),
  KEY `idx_agency_active` (`is_active`),
  CONSTRAINT `FK_agency_owner` FOREIGN KEY (`owner_id`) REFERENCES `admin_master` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Valuation agencies';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_master`
--

LOCK TABLES `agency_master` WRITE;
/*!40000 ALTER TABLE `agency_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `agency_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_staff_mapping`
--

DROP TABLE IF EXISTS `agency_staff_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_staff_mapping` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `agency_id` bigint NOT NULL,
  `staff_id` varchar(12) NOT NULL,
  `staff_role` enum('AGENT','VALUATOR','MANAGER') NOT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `is_primary_contact` bit(1) DEFAULT b'0',
  `is_active` bit(1) DEFAULT b'1',
  `assigned_date` datetime(6) NOT NULL,
  `removed_date` datetime(6) DEFAULT NULL,
  `removal_reason` varchar(500) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_agency_staff` (`agency_id`,`staff_id`,`is_active`),
  KEY `FK_agency_staff_agency` (`agency_id`),
  KEY `FK_agency_staff_staff` (`staff_id`),
  KEY `FK_agency_staff_created_by` (`created_by_id`),
  KEY `FK_agency_staff_updated_by` (`updated_by_id`),
  KEY `idx_staff_role` (`staff_role`),
  KEY `idx_staff_active` (`is_active`),
  CONSTRAINT `FK_agency_staff_agency` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_agency_staff_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_agency_staff_staff` FOREIGN KEY (`staff_id`) REFERENCES `admin_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_agency_staff_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Agency and staff relationship';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_staff_mapping`
--

LOCK TABLES `agency_staff_mapping` WRITE;
/*!40000 ALTER TABLE `agency_staff_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `agency_staff_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_agency_assignment`
--

DROP TABLE IF EXISTS `application_agency_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_agency_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` varchar(12) NOT NULL,
  `agency_id` bigint NOT NULL,
  `agency_name` varchar(255) DEFAULT NULL COMMENT 'Denormalized for history',
  `assigned_by_id` varchar(12) NOT NULL,
  `assigned_date` datetime(6) NOT NULL,
  `assignment_type` enum('NEW','REASSIGNED','TRANSFERRED') DEFAULT 'NEW',
  `priority` enum('LOW','MEDIUM','HIGH','URGENT') DEFAULT 'MEDIUM',
  `expected_completion_date` datetime(6) DEFAULT NULL,
  `actual_completion_date` datetime(6) DEFAULT NULL,
  `assignment_status` enum('ASSIGNED','ACCEPTED','IN_PROGRESS','VALUATOR_ASSIGNED','COMPLETED','CANCELLED') DEFAULT 'ASSIGNED',
  `comments` varchar(500) DEFAULT NULL,
  `remarks` text,
  `created_at` datetime(6) NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_app_agency_active` (`application_id`,`assignment_status`) COMMENT 'One active assignment per application',
  KEY `FK_agency_assign_application` (`application_id`),
  KEY `FK_agency_assign_agency` (`agency_id`),
  KEY `FK_agency_assign_assigned_by` (`assigned_by_id`),
  KEY `FK_agency_assign_created_by` (`created_by_id`),
  KEY `FK_agency_assign_updated_by` (`updated_by_id`),
  KEY `idx_agency_status` (`agency_id`,`assignment_status`),
  KEY `idx_assignment_date` (`assigned_date`),
  CONSTRAINT `FK_agency_assign_agency` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `FK_agency_assign_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_agency_assign_assigned_by` FOREIGN KEY (`assigned_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_agency_assign_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_agency_assign_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Application assignment to agency';
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
  `application_id` varchar(12) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `primary_contact_number` varchar(20) NOT NULL,
  `secondary_contact_number` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` enum('MALE','FEMALE','OTHER') DEFAULT NULL,
  `marital_status` enum('SINGLE','MARRIED','DIVORCED','WIDOWED') DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `annual_income` decimal(15,2) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `employment_type` enum('SALARIED','SELF_EMPLOYED','BUSINESS','RETIRED','OTHER') DEFAULT NULL,
  `pan_number` varchar(20) DEFAULT NULL,
  `aadhaar_number` varchar(20) DEFAULT NULL,
  `loan_type` varchar(100) DEFAULT NULL,
  `property_type` varchar(100) DEFAULT NULL,
  `property_sub_type` varchar(100) DEFAULT NULL,
  `property_reference_no` varchar(100) DEFAULT NULL,
  `lead_source` varchar(100) DEFAULT NULL COMMENT 'Where lead came from',
  `spock_name` varchar(255) DEFAULT NULL COMMENT 'Spouse/Co-applicant name',
  `remarks` text,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_app_customer_details` (`application_id`),
  KEY `FK_app_cust_application` (`application_id`),
  KEY `FK_app_cust_created_by` (`created_by_id`),
  KEY `FK_app_cust_updated_by` (`updated_by_id`),
  KEY `idx_cust_email` (`email`),
  KEY `idx_cust_phone` (`primary_contact_number`),
  CONSTRAINT `FK_app_cust_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_app_cust_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_app_cust_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Customer details in application';
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
  `aadhaar_uploaded` bit(1) DEFAULT b'0',
  `pan_uploaded` bit(1) DEFAULT b'0',
  `income_proof_uploaded` bit(1) DEFAULT b'0',
  `property_doc_uploaded` bit(1) DEFAULT b'0',
  `bank_statement_uploaded` bit(1) DEFAULT b'0',
  `other_doc_uploaded` bit(1) DEFAULT b'0',
  `total_documents` int DEFAULT '0',
  `all_mandatory_uploaded` bit(1) DEFAULT b'0',
  `remarks` varchar(500) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_app_doc_details` (`application_id`),
  KEY `FK_app_doc_application` (`application_id`),
  KEY `FK_app_doc_created_by` (`created_by_id`),
  KEY `FK_app_doc_updated_by` (`updated_by_id`),
  CONSTRAINT `FK_app_doc_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_app_doc_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_app_doc_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Document upload status';
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
  `id` varchar(12) NOT NULL COMMENT 'Unique application ID',
  `application_number` varchar(50) DEFAULT NULL COMMENT 'Human-readable application number',
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `associated_bank` varchar(255) DEFAULT NULL,
  `loan_amount_requested` decimal(15,2) DEFAULT NULL,
  `loan_type` varchar(100) DEFAULT NULL COMMENT 'Home Loan, Property Loan, etc.',
  `loan_purpose` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `submitted_date` datetime(6) DEFAULT NULL COMMENT 'When fully submitted',
  `client_id` varchar(12) NOT NULL COMMENT 'Reference to customer',
  `created_by_id` varchar(12) NOT NULL COMMENT 'Admin/Banker who created',
  `assigned_to_id` varchar(12) DEFAULT NULL COMMENT 'Currently assigned to',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_application_number` (`application_number`),
  KEY `FK_app_client` (`client_id`),
  KEY `FK_app_created_by` (`created_by_id`),
  KEY `FK_app_assigned_to` (`assigned_to_id`),
  KEY `idx_app_created_date` (`created_date`),
  KEY `idx_app_bank` (`associated_bank`),
  KEY `idx_app_active` (`is_active`),
  CONSTRAINT `FK_app_assigned_to` FOREIGN KEY (`assigned_to_id`) REFERENCES `admin_master` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK_app_client` FOREIGN KEY (`client_id`) REFERENCES `customer_master` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `FK_app_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Main application table';
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
  `application_id` varchar(12) NOT NULL,
  `res_door_or_apartment_number` varchar(100) DEFAULT NULL,
  `res_building_or_apartment_name` varchar(255) DEFAULT NULL,
  `res_street_line_1` varchar(255) DEFAULT NULL,
  `res_street_line_2` varchar(255) DEFAULT NULL,
  `res_city` varchar(100) DEFAULT NULL,
  `res_state` varchar(100) DEFAULT NULL,
  `res_pincode` varchar(10) DEFAULT NULL,
  `res_country` varchar(100) DEFAULT 'India',
  `res_landmark` varchar(255) DEFAULT NULL,
  `res_latitude` decimal(10,8) DEFAULT NULL,
  `res_longitude` decimal(11,8) DEFAULT NULL,
  `prop_door_or_apartment_number` varchar(100) DEFAULT NULL,
  `prop_building_or_apartment_name` varchar(255) DEFAULT NULL,
  `prop_street_line_1` varchar(255) DEFAULT NULL,
  `prop_street_line_2` varchar(255) DEFAULT NULL,
  `prop_city` varchar(100) DEFAULT NULL,
  `prop_state` varchar(100) DEFAULT NULL,
  `prop_pincode` varchar(10) DEFAULT NULL,
  `prop_country` varchar(100) DEFAULT 'India',
  `prop_landmark` varchar(255) DEFAULT NULL,
  `prop_latitude` decimal(10,8) DEFAULT NULL,
  `prop_longitude` decimal(11,8) DEFAULT NULL,
  `property_type` varchar(100) DEFAULT NULL COMMENT 'Apartment, Villa, Plot, etc.',
  `property_sub_type` varchar(100) DEFAULT NULL,
  `property_age_years` int DEFAULT NULL,
  `floor_number` varchar(20) DEFAULT NULL,
  `total_floors` int DEFAULT NULL,
  `bhk_type` varchar(20) DEFAULT NULL COMMENT '1BHK, 2BHK, etc.',
  `furnished_status` enum('FURNISHED','SEMI_FURNISHED','UNFURNISHED') DEFAULT NULL,
  `ownership_type` enum('FREEHOLD','LEASEHOLD','COOPERATIVE') DEFAULT NULL,
  `remarks` text,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_app_property_details` (`application_id`),
  KEY `FK_app_prop_application` (`application_id`),
  KEY `FK_app_prop_created_by` (`created_by_id`),
  KEY `FK_app_prop_updated_by` (`updated_by_id`),
  KEY `idx_prop_city` (`prop_city`),
  KEY `idx_prop_pincode` (`prop_pincode`),
  CONSTRAINT `FK_app_prop_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_app_prop_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_app_prop_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Property details in application';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_property_details`
--

LOCK TABLES `application_property_details` WRITE;
/*!40000 ALTER TABLE `application_property_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_property_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_uploaded_documents`
--

DROP TABLE IF EXISTS `application_uploaded_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_uploaded_documents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document_details_id` bigint NOT NULL,
  `application_id` varchar(12) NOT NULL COMMENT 'Denormalized for quick access',
  `document_type` enum('AADHAAR','PAN','INCOME_PROOF','SALARY_SLIP','BANK_STATEMENT','PROPERTY_DOCUMENT','SALE_DEED','PHOTO','OTHER') NOT NULL,
  `document_category` varchar(100) DEFAULT NULL COMMENT 'Additional categorization',
  `file_name` varchar(255) NOT NULL,
  `file_original_name` varchar(255) NOT NULL,
  `file_path` varchar(500) NOT NULL,
  `file_size_kb` bigint DEFAULT NULL,
  `file_type` varchar(100) DEFAULT NULL COMMENT 'MIME type',
  `file_extension` varchar(10) DEFAULT NULL,
  `document_number` varchar(100) DEFAULT NULL COMMENT 'Document ID if applicable',
  `document_date` date DEFAULT NULL,
  `is_verified` bit(1) DEFAULT b'0',
  `verified_by_id` varchar(12) DEFAULT NULL,
  `verified_date` datetime(6) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `uploaded_at` datetime(6) NOT NULL,
  `uploaded_by_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_upload_doc_details` (`document_details_id`),
  KEY `FK_upload_application` (`application_id`),
  KEY `FK_upload_uploaded_by` (`uploaded_by_id`),
  KEY `FK_upload_verified_by` (`verified_by_id`),
  KEY `idx_doc_type` (`document_type`),
  KEY `idx_doc_application` (`application_id`,`document_type`),
  CONSTRAINT `FK_upload_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_upload_doc_details` FOREIGN KEY (`document_details_id`) REFERENCES `application_document_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_upload_uploaded_by` FOREIGN KEY (`uploaded_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_upload_verified_by` FOREIGN KEY (`verified_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Individual uploaded documents';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_uploaded_documents`
--

LOCK TABLES `application_uploaded_documents` WRITE;
/*!40000 ALTER TABLE `application_uploaded_documents` DISABLE KEYS */;
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
  `application_id` varchar(12) NOT NULL,
  `agency_assignment_id` bigint NOT NULL COMMENT 'Reference to agency assignment',
  `agency_id` bigint NOT NULL COMMENT 'Denormalized for quick access',
  `valuator_id` varchar(12) NOT NULL,
  `valuator_name` varchar(255) DEFAULT NULL COMMENT 'Denormalized for history',
  `assigned_by_id` varchar(12) NOT NULL COMMENT 'Agent who assigned',
  `assigned_date` datetime(6) NOT NULL,
  `assignment_type` enum('NEW','REASSIGNED','REPLACEMENT') DEFAULT 'NEW',
  `priority` enum('LOW','MEDIUM','HIGH','URGENT') DEFAULT 'MEDIUM',
  `expected_completion_date` datetime(6) DEFAULT NULL,
  `actual_acceptance_date` datetime(6) DEFAULT NULL,
  `actual_completion_date` datetime(6) DEFAULT NULL,
  `assignment_status` enum('ASSIGNED','ACCEPTED','REJECTED_BY_VALUATOR','SITE_VISIT_SCHEDULED','SITE_VISIT_IN_PROGRESS','VALUATION_SUBMITTED','COMPLETED','CANCELLED') DEFAULT 'ASSIGNED',
  `rejection_reason` varchar(500) DEFAULT NULL,
  `special_instructions` text,
  `remarks` text,
  `created_at` datetime(6) NOT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_app_valuator_active` (`application_id`,`assignment_status`) COMMENT 'One active assignment per application',
  KEY `FK_val_assign_application` (`application_id`),
  KEY `FK_val_assign_agency_assignment` (`agency_assignment_id`),
  KEY `FK_val_assign_agency` (`agency_id`),
  KEY `FK_val_assign_valuator` (`valuator_id`),
  KEY `FK_val_assign_assigned_by` (`assigned_by_id`),
  KEY `FK_val_assign_created_by` (`created_by_id`),
  KEY `FK_val_assign_updated_by` (`updated_by_id`),
  KEY `idx_valuator_status` (`valuator_id`,`assignment_status`),
  KEY `idx_agency_valuator` (`agency_id`,`valuator_id`),
  KEY `idx_assignment_date` (`assigned_date`),
  CONSTRAINT `FK_val_assign_agency` FOREIGN KEY (`agency_id`) REFERENCES `agency_master` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `FK_val_assign_agency_assignment` FOREIGN KEY (`agency_assignment_id`) REFERENCES `application_agency_assignment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_val_assign_application` FOREIGN KEY (`application_id`) REFERENCES `application_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_val_assign_assigned_by` FOREIGN KEY (`assigned_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_val_assign_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_val_assign_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_val_assign_valuator` FOREIGN KEY (`valuator_id`) REFERENCES `admin_master` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Application assignment to valuator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_valuator_assignment`
--

LOCK TABLES `application_valuator_assignment` WRITE;
/*!40000 ALTER TABLE `application_valuator_assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_valuator_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_master`
--

DROP TABLE IF EXISTS `customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_master` (
  `id` varchar(12) NOT NULL COMMENT 'Unique customer ID',
  `bank` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `role` enum('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN') NOT NULL DEFAULT 'USER',
  `is_active` bit(1) DEFAULT b'1',
  `created_by_id` varchar(12) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_customer_email` (`email`),
  KEY `idx_customer_phone` (`phone_number`),
  KEY `FK_customer_created_by` (`created_by_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Loan applicant customers';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_master`
--

LOCK TABLES `customer_master` WRITE;
/*!40000 ALTER TABLE `customer_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valuator_profile`
--

DROP TABLE IF EXISTS `valuator_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valuator_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `valuator_id` varchar(12) NOT NULL,
  `employee_code` varchar(50) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL COMMENT 'e.g., Residential, Commercial, Agricultural',
  `experience_years` int DEFAULT NULL,
  `certification_number` varchar(100) DEFAULT NULL,
  `certification_authority` varchar(255) DEFAULT NULL,
  `certification_valid_till` date DEFAULT NULL,
  `is_available` bit(1) DEFAULT b'1',
  `max_concurrent_assignments` int DEFAULT '5',
  `current_assignments_count` int DEFAULT '0',
  `total_valuations_completed` int DEFAULT '0',
  `average_rating` decimal(3,2) DEFAULT NULL,
  `preferred_locations` text COMMENT 'Comma-separated cities/areas',
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by_id` varchar(12) NOT NULL,
  `updated_by_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_valuator_profile` (`valuator_id`),
  UNIQUE KEY `UK_employee_code` (`employee_code`),
  KEY `idx_valuator_available` (`is_available`),
  KEY `FK_valuator_profile_admin` (`valuator_id`),
  KEY `FK_valuator_created_by` (`created_by_id`),
  KEY `FK_valuator_updated_by` (`updated_by_id`),
  CONSTRAINT `FK_valuator_created_by` FOREIGN KEY (`created_by_id`) REFERENCES `admin_master` (`id`),
  CONSTRAINT `FK_valuator_profile_admin` FOREIGN KEY (`valuator_id`) REFERENCES `admin_master` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_valuator_updated_by` FOREIGN KEY (`updated_by_id`) REFERENCES `admin_master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Valuator detailed profile';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valuator_profile`
--

LOCK TABLES `valuator_profile` WRITE;
/*!40000 ALTER TABLE `valuator_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `valuator_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-03 14:22:09
