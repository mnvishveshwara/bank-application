SELECT * FROM bank_valuator.agency_bank_mapping;


ALTER TABLE bank_valuator.application_master 
ADD COLUMN `assignment_type` ENUM('AGENCY', 'INTERNAL') DEFAULT 'AGENCY',
ADD COLUMN `internal_valuator_id` varchar(12) DEFAULT NULL,
ADD CONSTRAINT `FK_internal_valuator` FOREIGN KEY (`internal_valuator_id`) REFERENCES `admin_master` (`id`);


ALTER TABLE bank_valuator.admin_master
MODIFY COLUMN `role` ENUM('USER','MANAGER','BROKER','AGENT','EMPLOYEE','ADMIN','AGENCY','AGENCY_VALUATOR','BANK_VALUATOR') NOT NULL;

-- 2. Add assignment logic to application_master
ALTER TABLE `application_master` 
ADD COLUMN `assignment_type` ENUM('AGENCY', 'INTERNAL') DEFAULT 'AGENCY',
ADD COLUMN `internal_valuator_id` varchar(12) DEFAULT NULL,
ADD CONSTRAINT `FK_internal_valuator` FOREIGN KEY (`internal_valuator_id`) REFERENCES `admin_master` (`id`);


--  Query to get the valutors of the bank
SELECT id, first_name, last_name 
FROM admin_master am
JOIN admin_bank_mapping abm ON am.id = abm.admin_id
WHERE am.role = 'BANK_VALUATOR' 
AND abm.bank_id = [CURRENT_BANKER_BANK_ID];