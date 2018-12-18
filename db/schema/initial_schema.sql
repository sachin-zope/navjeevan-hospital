CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indoor_register_id` int(11) NOT NULL,
  `serial_no` int(11) NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `indoor_hospital_charges` int(11) DEFAULT NULL,
  `sonography` int(11) DEFAULT NULL,
  `consultant_charges` int(11) DEFAULT NULL,
  `blood_transfusion_charges` int(11) DEFAULT NULL,
  `procedure_charges` int(11) DEFAULT NULL,
  `operation_charges` int(11) DEFAULT NULL,
  `episiotomy_charges` int(11) DEFAULT NULL,
  `nursing_charges` int(11) DEFAULT NULL,
  `ot_charges` int(11) DEFAULT NULL,
  `other_charges` int(11) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bill_type` varchar(50) DEFAULT NULL,
  `cheque_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2509 DEFAULT CHARSET=latin1;


CREATE TABLE `delivery_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_date` date NOT NULL,
  `episiotomy` varchar(45) DEFAULT NULL,
  `delivery_type` varchar(45) NOT NULL,
  `sex_of_child` varchar(45) NOT NULL,
  `birth_weight` varchar(45) NOT NULL,
  `birth_time` varchar(45) NOT NULL,
  `indication` varchar(512) DEFAULT NULL,
  `remarks` varchar(1024) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1835 DEFAULT CHARSET=utf8;

CREATE TABLE `mtp_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mtp_serial_no` int(11) DEFAULT NULL,
  `religion` varchar(45) NOT NULL,
  `indication` varchar(512) DEFAULT NULL,
  `mtp_procedure` varchar(512) DEFAULT NULL,
  `duration_of_pregnancy` int(11) NOT NULL,
  `along_with` varchar(256) DEFAULT NULL,
  `male_childrens` int(11) DEFAULT NULL,
  `female_childrens` int(11) DEFAULT NULL,
  `operation_date` date NOT NULL,
  `done_by_dr` varchar(512) DEFAULT NULL,
  `opinion_given_by` varchar(512) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `batch_no` varchar(256) DEFAULT NULL,
  `married` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=803 DEFAULT CHARSET=utf8;

CREATE TABLE `mtp_register_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mtp_register_id` int(11) NOT NULL,
  `patient_name` varchar(512) NOT NULL,
  `address` varchar(2048) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `fees` decimal(8,2) DEFAULT NULL,
  `remarks` varchar(512) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `mtp_register_details_fk_idx` (`mtp_register_id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8;

CREATE TABLE `ot_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_of_surgeon` varchar(512) NOT NULL,
  `assistant` varchar(512) DEFAULT NULL,
  `anaesthetist` varchar(512) DEFAULT NULL,
  `operation_date` date NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1673 DEFAULT CHARSET=utf8;

CREATE TABLE `indoor_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admit_date` date NOT NULL,
  `discharge_date` date DEFAULT NULL,
  `patient_name` varchar(512) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `address` varchar(2048) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `diagnosis` varchar(512) NOT NULL,
  `treatment` varchar(512) NOT NULL,
  `fees` decimal(8,2) DEFAULT NULL,
  `remarks` varchar(1024) DEFAULT NULL,
  `delivery_register_id` int(11) DEFAULT NULL,
  `ot_register_id` int(11) DEFAULT NULL,
  `mtp_register_id` int(11) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_bill_generated` tinyint(1) NOT NULL DEFAULT '0',
  `dr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `indoor_ot_id_idx` (`ot_register_id`),
  KEY `mtp_fk_idx` (`mtp_register_id`),
  KEY `delivery_fk_idx` (`delivery_register_id`),
  CONSTRAINT `` FOREIGN KEY (`ot_register_id`) REFERENCES `ot_register` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3265 DEFAULT CHARSET=utf8;
