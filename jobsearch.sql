-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: jobsearch
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `fk_user` bigint(20) DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resume` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_user_UNIQUE` (`fk_user`),
  CONSTRAINT `FK77fv3fujfupewux2nho9s7taa` FOREIGN KEY (`fk_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (26,'Lajkovac bb',25,35,'Jon Doe','http://localhost:8080/api/resume/download/DEJAN_CODEUP_CV.pdf'),(27,'Beograd bb',24,36,'Petar Petrovic','http://localhost:8080/api/resume/download/DEJAN_CODEUP_CV.pdf');
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no_of_employees` int(11) DEFAULT NULL,
  `fk_industry` bigint(20) DEFAULT NULL,
  `fk_user` bigint(20) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_user_UNIQUE` (`fk_user`),
  KEY `FKjenvafrtmlo2gyucuawblor8i` (`fk_industry`),
  CONSTRAINT `FKj0wn2elw2bekwylr5srhiaslb` FOREIGN KEY (`fk_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjenvafrtmlo2gyucuawblor8i` FOREIGN KEY (`fk_industry`) REFERENCES `industry` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Niš','Asseco SEE',45,1,1,'Asseco SEE Group (ASEE Group) is one of the largest IT companies in South-Eastern Europe, employing more than 2700 experts in the region. '),(13,'Beograd','Quantox',120,1,39,NULL),(15,'Beograd','Nordeus',120,4,41,'We\'re a mobile games company based in Belgrade, Serbia. Fiercely independent, we make games that challenge you to be a champion.');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (53);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry`
--

DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'Software Development'),(4,'Web and mobile development'),(5,'Graphic Design');
/*!40000 ALTER TABLE `industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deadline_date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `published_date` datetime DEFAULT NULL,
  `fk_company` bigint(20) DEFAULT NULL,
  `fk_position` bigint(20) DEFAULT NULL,
  `fk_seniority` bigint(20) DEFAULT NULL,
  `fk_status` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4odww0qjynfdc9jjas37n1lso` (`fk_company`),
  KEY `FKd6bg68e8a5rwaykbhtx7pulul` (`fk_position`),
  KEY `FKqvh5lrvn0i41mucnb2x4kxayx` (`fk_seniority`),
  KEY `FKfd91n5fckedps0ggv90s4snuy` (`fk_status`),
  CONSTRAINT `FK4odww0qjynfdc9jjas37n1lso` FOREIGN KEY (`fk_company`) REFERENCES `company` (`id`),
  CONSTRAINT `FKd6bg68e8a5rwaykbhtx7pulul` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FKfd91n5fckedps0ggv90s4snuy` FOREIGN KEY (`fk_status`) REFERENCES `status` (`id`),
  CONSTRAINT `FKqvh5lrvn0i41mucnb2x4kxayx` FOREIGN KEY (`fk_seniority`) REFERENCES `seniority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (2,'2021-05-26 22:00:00','WebApplication development with React','Lead React Developer','2021-05-15 22:00:00',1,3,4,3),(27,'2021-05-14 22:00:00','dasbjkdnasldn','Test JOb','2021-05-14 22:00:00',13,2,1,3),(28,'2021-05-30 22:00:00','We are currently looking for a Senior Frontend (Angular) Developer with strong skills and proven experience who is ready to dive into the TestingTeam team, for a full-time engagement, to join our team of passionate IT professionals.','Medior Angular Developer','2021-05-15 22:00:00',1,1,2,2),(46,'2021-06-29 22:00:00','As a Junior Software Quality Engineer in our International Development – Digital Origination team, you will be involved in creating detailed, comprehensive and well-structured test plans and test cases.','Junior Software Quality Engineer','2021-05-28 22:00:00',1,6,1,1),(47,'2021-06-29 22:00:00','As a Software Developer in our Banking Sales & Servicing team, you will develop and program of programming modules in order to fulfill requirements according to the specification and project plan','Software Developer','2021-05-28 22:00:00',1,7,2,1),(48,'2021-06-29 22:00:00','We are looking for experienced Java engineer to grow our Belgrade Insurance Application Team. SAP Insurance application is a cloud multi-tenant application that enables customers to model their own business processes in the cloud.','Java Developer','2021-05-28 22:00:00',1,5,3,1),(49,'2021-06-22 22:00:00','Quantox offers an open and transparent environment for all our clients around the world. This trust can improve the ease of doing business with us, and enable work with an extremely professional team, and a high-quality product.','React Developer','2021-05-28 22:00:00',13,2,1,1),(50,'2021-06-29 22:00:00','At Quantox, you will work with a team of 300 experts in 10 development centres across 4 countries, who possess significant knowledge and extensive experience in software development for various platforms. ','DevOps Engineer','2021-05-28 22:00:00',13,3,2,1),(51,'2021-06-29 22:00:00','We’re looking for a Junior Software Engineer to join our small yet highly-capable group of engineers and provide support for all our games across the organization.','Junior Software Engineer','2021-05-28 22:00:00',15,8,1,1);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_application`
--

DROP TABLE IF EXISTS `job_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_application` (
  `candidate_id` bigint(20) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `created_on` datetime NOT NULL,
  PRIMARY KEY (`candidate_id`,`job_id`),
  KEY `FKdepcvxeq3gyb4438ws0qjycc7` (`job_id`),
  CONSTRAINT `FKdepcvxeq3gyb4438ws0qjycc7` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKgq7rbq1q2388owxysfur67tfh` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_application`
--

LOCK TABLES `job_application` WRITE;
/*!40000 ALTER TABLE `job_application` DISABLE KEYS */;
INSERT INTO `job_application` VALUES (26,2,'2021-05-08 22:36:59'),(27,2,'2021-05-16 15:16:21'),(27,27,'2021-05-16 15:16:21');
/*!40000 ALTER TABLE `job_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_technologies`
--

DROP TABLE IF EXISTS `job_technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_technologies` (
  `id_job` bigint(20) NOT NULL,
  `id_technology` bigint(20) NOT NULL,
  KEY `FKej7pw7e383bufmf1otjw8ejwy` (`id_technology`),
  KEY `FKd7satwqgrglyaxnt90761mmch` (`id_job`),
  CONSTRAINT `FKd7satwqgrglyaxnt90761mmch` FOREIGN KEY (`id_job`) REFERENCES `job` (`id`),
  CONSTRAINT `FKej7pw7e383bufmf1otjw8ejwy` FOREIGN KEY (`id_technology`) REFERENCES `technology` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_technologies`
--

LOCK TABLES `job_technologies` WRITE;
/*!40000 ALTER TABLE `job_technologies` DISABLE KEYS */;
INSERT INTO `job_technologies` VALUES (27,5),(28,4),(28,1),(2,3),(2,2),(2,4),(2,11),(46,8),(46,12),(47,1),(47,9),(47,12),(47,13),(47,14),(47,15),(48,8),(48,9),(48,1),(49,3),(49,12),(49,2),(50,6),(50,7),(50,13),(51,13),(51,10);
/*!40000 ALTER TABLE `job_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'ADMIN'),(2,'COMPANY'),(3,'CANDIDATE');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Angular Developer'),(2,'React Developer'),(3,'DevOps Engineer'),(4,'Full Stack Developer'),(5,'Java Developer'),(6,'QA tester'),(7,'.Net Developer'),(8,'Software Engineer');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seniority`
--

DROP TABLE IF EXISTS `seniority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seniority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seniority`
--

LOCK TABLES `seniority` WRITE;
/*!40000 ALTER TABLE `seniority` DISABLE KEYS */;
INSERT INTO `seniority` VALUES (1,'Junior'),(2,'Medior'),(3,'Senior'),(4,'Lead');
/*!40000 ALTER TABLE `seniority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Active'),(2,'Inactive'),(3,'Expired');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology`
--

DROP TABLE IF EXISTS `technology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technology` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology`
--

LOCK TABLES `technology` WRITE;
/*!40000 ALTER TABLE `technology` DISABLE KEYS */;
INSERT INTO `technology` VALUES (1,'Angular'),(2,'MongoDB'),(3,'React'),(4,'Ionic'),(5,'NestJS'),(6,'Jenkins'),(7,'Docker'),(8,'Java'),(9,'PostgreSQL'),(10,'MySQL'),(11,'Python'),(12,'JavaScript'),(13,'C#'),(14,'HTML'),(15,'CSS');
/*!40000 ALTER TABLE `technology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permission` (
  `id_user` bigint(20) NOT NULL,
  `id_permission` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_permission`),
  KEY `FKo47t1we6do84ku6rb9jcey2s9` (`id_permission`),
  CONSTRAINT `FKo47t1we6do84ku6rb9jcey2s9` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKprpp02ivhe66b5nrc0a3a4lk8` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
INSERT INTO `user_permission` VALUES (1,2),(39,2),(41,2),(35,3),(36,3);
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '',_binary '',_binary '',_binary '','$2a$16$/3mbHyHlM6cXhWYdVGImWuxpb9zUL5mWkmD/4OhueCl9thyOzyGia','dejan@gmail.com'),(35,_binary '',_binary '',_binary '',_binary '','$2a$16$Qg5ROanPNLJEjjvJcN8E.eR/CAIbwd3MaTr9LyyhWIGw.T2P.Fw9i','cifow47659@drluotan.com'),(36,_binary '',_binary '',_binary '',_binary '','$2a$16$3lEoK8hzKMbxr2nmrb8IdeLN85ADuhCinxYlbJ2AIEC0p6RNagJoy','livoka7108@dvdoto.com'),(39,_binary '',_binary '',_binary '',_binary '','$2a$16$Fl/wAZTC6CIp8Z9X8Tr6o.eO/HeqBVLdzRQO10Y8gjF6ADOdUp7tO','cekep51798@troikos.com'),(41,_binary '',_binary '',_binary '',_binary '','$2a$16$/3mbHyHlM6cXhWYdVGImWuxpb9zUL5mWkmD/4OhueCl9thyOzyGia','vitaro1154@troikos.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `token_id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `verification_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FK3asw9wnv76uxu3kr1ekq4i1ld` (`user_id`),
  CONSTRAINT `FK3asw9wnv76uxu3kr1ekq4i1ld` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'jobsearch'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-29 11:39:39
