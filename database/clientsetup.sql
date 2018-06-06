CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
  `client_name` varchar(100) NOT NULL,
  `client_code` varchar(8) NOT NULL,
  `client_logo_filepath` varchar(1000) NULL,
  `contact_person` varchar(60) NULL,
  `contact_phone` varchar(15) NULL,
  `contact_email` varchar(80) NULL,
  `client_website` varchar(200) NULL,
  `client_address` varchar(80) NULL,
  `client_city` varchar(60) NULL,
  `client_state` varchar(60) NULL,
  `client_zip` varchar(60) NULL,
  `client_timezone` varchar(100) NULL,
  `billing_name` varchar(100) NULL,
  `billing_email` varchar(80) NULL,
  `billing_ccemail` varchar(80) NULL,
  `hide_scheduler_input` char(1) NOT NULL DEFAULT 'N',
  `hide_reminders_input` char(1) NOT NULL DEFAULT 'N',
  `hide_locations_input` char(1) NOT NULL DEFAULT 'N',
  `hide_services_input` char(1) NOT NULL DEFAULT 'N',
  `hide_list_of_doc_bring` char(1) NOT NULL DEFAULT 'N',
  `online_scheduler` char(1) NULL,
  `phone_scheduler` char(1) NULL,
  `preferred_area_code` varchar(3) NULL,
  `preferred_city` varchar(200) NULL,
  `preferred_county` varchar(200) NULL,
  `phone_reminder` char(1) NULL,
  `text_reminder` char(1) NULL,
  `email_reminder` char(1) NULL,
  `status` int(4) NOT NULL COMMENT '1-PendingClientInput, 2-Ready, 3-PendingDBSetup, 4-SetupComplete',
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_code` (`client_code`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
  `client_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `time_zone` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  FOREIGN KEY (client_id) REFERENCES `client`(id) ON DELETE CASCADE
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
  `client_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `duration` int(4) NOT NULL,
  `allow_duplicate_appts` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  FOREIGN KEY (client_id) REFERENCES `client`(id) ON DELETE CASCADE
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `scheduler_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
  `client_id` int(11) NOT NULL,
  `online_scheduler_url` varchar(1000) NOT NULL,
  `database` varchar(50) NOT NULL,
  `calendar_block_time` int(4) NOT NULL,
  `day_start_time` time NOT NULL,
  `day_end_time` time NOT NULL,
  `call_center_logic` char(1) NOT NULL DEFAULT 'Y',
  `user_name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `db_template` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `database` (`database`),
  FOREIGN KEY (client_id) REFERENCES `client`(id) ON DELETE CASCADE
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table `list_of_things_bring` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
    `client_id` int(11) NOT NULL,
	`lang` varchar (18),
	`service_ids` varchar (30),
	`display_text` text 
); 

create table `list_of_things_bring` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`client_id` int(11) NOT NULL,
	`device` VARCHAR(20) NOT NULL DEFAULT 'online',
	`lang` varchar (18) NOT NULL DEFAULT 'us-en',
	`service_id` INT(10) NULL,
	`display_text` text,
	PRIMARY KEY (`id`)
); 