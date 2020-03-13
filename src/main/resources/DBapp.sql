-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: app
-- ------------------------------------------------------
-- Server version	5.7.29

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
-- Table structure for table `app_groups`
--

DROP TABLE IF EXISTS `app_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_groups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_groups`
--

LOCK TABLES `app_groups` WRITE;
/*!40000 ALTER TABLE `app_groups` DISABLE KEYS */;
INSERT INTO `app_groups` VALUES (1,'Create'),(2,'Read'),(3,'Update'),(4,'Delete'),(5,'List'),(6,'Approve'),(7,'Finalize');
/*!40000 ALTER TABLE `app_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_resource`
--

DROP TABLE IF EXISTS `app_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_resource` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ParentID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) DEFAULT NULL,
  `ResourceURL` varchar(100) DEFAULT NULL,
  `ResourceName` varchar(45) DEFAULT NULL,
  `MenuPlacement` int(11) DEFAULT '0',
  `ResourceGroupID` int(11) DEFAULT NULL,
  `Icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_resource`
--

LOCK TABLES `app_resource` WRITE;
/*!40000 ALTER TABLE `app_resource` DISABLE KEYS */;
INSERT INTO `app_resource` VALUES (1,0,NULL,'/acl/resources','Resources',1,1,'fas fa-prescription'),(2,0,NULL,'/acl/group','Group',2,1,'fas fa-object-group'),(3,0,NULL,'/acl/resource/group','Resource Group',NULL,1,'fab fa-researchgate'),(7,0,NULL,'/acl/role','Role',NULL,1,'fas fa-user-tag'),(11,0,NULL,'/acl/role/manage-role','Manage Role',NULL,1,'fas fa-users-cog'),(12,0,NULL,'/acl/role/restrict-role','Role Restriction',NULL,1,'fas fa-user-slash'),(13,0,NULL,'/acl/user/list','Users',0,1,'fas fa-users'),(14,0,NULL,'/docker/docker-list','Docker List',0,2,'fas fa-th-list'),(15,1,NULL,'/resource/add','Add Resource',0,NULL,NULL),(16,1,NULL,'/resource/list','List Resource',0,NULL,NULL),(17,1,NULL,'/resource/parents','Resource Parent',0,NULL,NULL),(18,0,NULL,'/eureka/status','Instances',0,4,'fas fa-cloud');
/*!40000 ALTER TABLE `app_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_resource_groups`
--

DROP TABLE IF EXISTS `app_resource_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_resource_groups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ResourceGroupName` varchar(45) DEFAULT NULL,
  `MenuPlacement` int(11) DEFAULT '0',
  `Icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_resource_groups`
--

LOCK TABLES `app_resource_groups` WRITE;
/*!40000 ALTER TABLE `app_resource_groups` DISABLE KEYS */;
INSERT INTO `app_resource_groups` VALUES (1,'ACL',1,'fab fa-app-store'),(2,'Docker',2,'fab fa-docker'),(3,'Config Server',3,NULL),(4,'Eureka',4,'fas fa-server');
/*!40000 ALTER TABLE `app_resource_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_restrictions`
--

DROP TABLE IF EXISTS `app_restrictions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_restrictions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleID` int(11) NOT NULL,
  `ResourceID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) DEFAULT '0',
  `ResourceGroupID` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RRGIDx` (`RoleID`,`ResourceID`,`GroupID`),
  KEY `RoleIDx` (`RoleID`),
  KEY `ResourceIDx` (`ResourceID`),
  KEY `GroupIDx` (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_restrictions`
--

LOCK TABLES `app_restrictions` WRITE;
/*!40000 ALTER TABLE `app_restrictions` DISABLE KEYS */;
INSERT INTO `app_restrictions` VALUES (10,2,0,0,1),(11,2,0,18,0),(12,2,16,0,0);
/*!40000 ALTER TABLE `app_restrictions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_roles`
--

DROP TABLE IF EXISTS `app_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Description` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_roles`
--

LOCK TABLES `app_roles` WRITE;
/*!40000 ALTER TABLE `app_roles` DISABLE KEYS */;
INSERT INTO `app_roles` VALUES (1,'ADMIN','Admin - Developer'),(2,'USER','User Role');
/*!40000 ALTER TABLE `app_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_session`
--

DROP TABLE IF EXISTS `app_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_session` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `JWT_TOKEN` varchar(300) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_session`
--

LOCK TABLES `app_session` WRITE;
/*!40000 ALTER TABLE `app_session` DISABLE KEYS */;
INSERT INTO `app_session` VALUES (1,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzU4NDg5MCwiZXhwIjoxNTgzNjEzNjkwfQ.nUQwjGccqspAvNtqi88fEFZv-r_6qxKRRxOF0-DK9g1KhA50wMGCN3GVt5VRonEOzM4W3GamXGDYaZQZr0UAuQ',1),(2,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzcyOTMxNSwiZXhwIjoxNTgzNzU4MTE1fQ.p-KJzQTUojH1zlCz-FurNDP3gaDVwYd0vnpm8C9tbrR9XCfQSBxHiGtH8qrSij0StukOdjN2wui0QeHEHYPnGA',1),(3,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzcyOTQ1MywiZXhwIjoxNTgzNzU4MjUzfQ.FkU6dhu1WrwaaymJWuqlSTHI9nUNcrzm1DElZ9Vs6F4AzcGCrQXPI409Uk9EVmjb48_aYpP-LLxiYqOgzOoxcQ',1),(4,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzcyOTU5MywiZXhwIjoxNTgzNzU4MzkzfQ.1RIkIgKmnZy-I-koiarq1dGueWSbYuh4Nc7V7BuF15ct6YF23_AUZEBFJ0uQNgYZHyowW6AWDkHUD7ztE2Fbbw',1),(5,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMTc5OCwiZXhwIjoxNTgzNzYwNTk4fQ.KWhlNdFgtzoqo-vrYF2GuVe1qwITjbS8wbhzoIeyNWjnSV4OCgA5zYMRMDQVqv0WOaLUKsO2FrhR6dUd40eSOQ',1),(6,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzAxNiwiZXhwIjoxNTgzNzYxODE2fQ.xzfeqUufxS2LXAt0bJxm0SuD1882gZnAT4FqycQTM2wYOdFWWBxDW6OuamtD12XxjDRoWOWG7athkFySeD5WBQ',1),(7,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzA3NCwiZXhwIjoxNTgzNzYxODc0fQ.PVWU8gnO5-lTPqAQNc6uzIZLVeTwZGP3VU8Ev-WdJOsIOxeQ-smuWZXmNvw7W2hUIcXDE5Dk1mEmhKu5FhHpkA',1),(8,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzIwNCwiZXhwIjoxNTgzNzYyMDA0fQ.0k_kvlieZF0YekjoAMS7vL-NgfE-24-w34CaBg-gNQh61IqU0CH42kCjmdhQ2BKNyRI7X9lYW1u7Q3B19tjODQ',1),(9,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzQzNCwiZXhwIjoxNTgzNzYyMjM0fQ.I0YGUaCmc77IkRv0HXQMI93Q3JtlVuPYOKnOR97NYAqXTHZ9H5gmww4lEuTo4O-KitI0X_Tjp9q-oQf6MJRs9Q',1),(10,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzU1NCwiZXhwIjoxNTgzNzYyMzU0fQ.iWsh4LxTQFSYo7tWa-3E_ZBDHKJz7ubpsVwfKrAtok59eIiDk-7Xnm4_w6Lv2i3RIDKbQoNG0MSY1_oTHkvKJg',1),(11,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczMzk4MCwiZXhwIjoxNTgzNzYyNzgwfQ.E_mbe9-WLeqt0EOZ6rcsLspNARpHJ69tf3yjOZHCg9kYvomlnh1WsNviP_j6GPURwd2u10x9Vq298z0IvVjUjg',1),(12,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczNDc3MiwiZXhwIjoxNTgzNzYzNTcyfQ.kPQm1qBzb89YmyQ_UkvgxY3bGExY5q8mvx1CiU4PXZ6LQe-BX0U5XV1k9l30zhNrCI_4H-ZG3CFsOXMJMYta9g',1),(13,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczNTMyNSwiZXhwIjoxNTgzNzY0MTI1fQ.kKqoTYpXopRR02KyUVX8gsdocQOYTVLm4VGAFkFqd7fspsPnx0nGE-hUGZ_dgZOdCAtol5KiijofalPfYcc1WQ',1),(14,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczNTYwOCwiZXhwIjoxNTgzNzY0NDA4fQ.pDS5cwEHZa_urFe0TTurmVn-O6pulJQuc7fN9uhDPzRP9QxX9zx36B5dPpqZOP5Oj-l76CCd7DinIsjO_7LEeQ',1),(15,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczNTg1OSwiZXhwIjoxNTgzNzY0NjU5fQ.fTZJQo-xWHLnE_j7Cxwas3dE4a_buy4MH-2vpiEDfsNDJybE0OxGRVvr_Hp67XXIRp-F_matThsMU1KKO5FsXA',1),(16,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczNjQzMSwiZXhwIjoxNTgzNzY1MjMxfQ.CeLVG6SOpH7pMI4qvdE3QeT1jO9jUQ4lgHtELq-1phaKr7e1CYLZP_6swpmAgUPi8rCihwAvP5jkb_y2Fv4eJQ',1),(17,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczOTUwNSwiZXhwIjoxNTgzNzY4MzA1fQ.pEBMiwyNoTq6WExNFqiOuToyyYZdU-5ErFIL7zQK7IH9BPIAhL0IjUfEAe_20VT62G__qIkhyayryrHAPLxAcw',1),(18,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzczOTgxNywiZXhwIjoxNTgzNzY4NjE3fQ.BvoEdWQShaEI_uF0zOz6u4az9e9mVbrs6e-KqJYZdkVUyzzhUwx0M36jN0mWkkoKoHIaiWUSKu4Gvj1Z0eeTdg',1),(19,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzc0MDU1NiwiZXhwIjoxNTgzNzY5MzU2fQ.Vu2md6hPNmpNUVUxVtkqYvrx0V9-jMzor_PZRU5iTiQVaP2uvLpoKNzgEXny7Rv5auMb3VZbH_GS9b_ZCQIX0g',1),(20,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkwOTYwMiwiZXhwIjoxNTgzOTM4NDAyfQ.qDGqa3jiQ1mbfXCF8YQJlI6yWpRdhVf1fXJUaPja28kOBBqTc_HhFsmgdySQpC_sDkA6-4WoMjc76YJFszeoHQ',1),(21,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkxMDI5MywiZXhwIjoxNTgzOTM5MDkzfQ.VMiOU5agb8fJjrtIu0tQmNMqPKGxH4RLGIseWdaDR6sdKkslEZOQLyo93E2PxvNJ9kBseuIeMdNmVpWDN-Wv4Q',1),(22,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkxMjY2NCwiZXhwIjoxNTgzOTQxNDY0fQ.VD14fxHc6f6AzHkXrpD0P4HWu7ss4mWip8SlHGFOW2NVClj4ZqlHcQWAPX03S5an1P7-sM98dCHEdue_eTHe1w',1),(23,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkxMzE2NSwiZXhwIjoxNTgzOTQxOTY1fQ._Wsd7k1H23PmH4XWMTbgOslKRvJrZl4k-AtzRcWlnj86radsF5mVUYEUnmtWV32IQvJL6AxjSqSChCMWsGNp2A',1),(24,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkxNDU1OSwiZXhwIjoxNTgzOTQzMzU5fQ.L0b6OkMF35_xAWKoVgmRMeYQnzkWA2CJXxYBGUw5aLXdU7J6Cq_LeKAc4c1Tskz7FVYB8uPCtTcI8kvMS-x3FA',1),(25,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkyOTE0MCwiZXhwIjoxNTgzOTU3OTQwfQ.qdAbzrAHvwzBM2pvrv9DasmQIp7BrYBFpZ1pk_j-JCtP5p-64DQluS5-z9s54G9KI1PyZBssNLVJ3rh_GvcibA',1),(26,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4MzkyOTI4NCwiZXhwIjoxNTgzOTU4MDg0fQ.M2F_hIbh4Fsu4jfmoJE2kmHTTmx4IibNF0J9HCz5zy5QDg_ZrHddkU7tXHoLH9o2F3k5ZStckhuJ5VsD1Lf-Tg',1),(27,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk4ODg2MSwiZXhwIjoxNTg0MDE3NjYxfQ.8dlW0t2t5c-hxfdddxCvuQe1-4oGEVAluO3i7mZmSVeAn86CNZxAF-GgUJCJ7y5lloquKanBudBwrwTmhr5beQ',1),(28,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk4OTM2MSwiZXhwIjoxNTg0MDE4MTYxfQ.967WuFLknkg6gwKGVcNBA2epWEa0B3iYxB3dExK-u9gG53k03ulCJnrLWAAoA9-uGXWMLMnWerkDjvC4VoV1tw',1),(29,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk4OTUxMSwiZXhwIjoxNTg0MDE4MzExfQ.z6QTHACBJCOdYlD4FLsSGNWyr4cAWXwDDsQ_aCfi0bxzSii2FmZ8OOv8ySNxAPasY2mvaH-ELDW-uonvc56q_Q',1),(30,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5MDA4MywiZXhwIjoxNTg0MDE4ODgzfQ.eOBBr7ciTrLw6f8wPd9_omkh1v9An9wflD0HTJWWnDFMBy1FAvWBwSO2HDQn6ZEf_fjQA409MPkvcl0-SpjYQg',1),(31,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5NTUwOCwiZXhwIjoxNTg0MDI0MzA4fQ.Jx5NqYP8pzEJJIhY14aEsdecqsK3nd_wVgw6-HnajYSah4JtQ5vX4B9i5QQZwiJ5SV-Q9tsZmH37depE809gXQ',1),(32,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5NTUyMCwiZXhwIjoxNTg0MDI0MzIwfQ.OmHKqmvEdJaTSo0e-wu_LNnZWBNud4iC-4NTXDors6b1nfIfLybqee5RMeIgEWXsxZGYX_ljt4t334ojNP31fA',1),(33,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5NTYyMywiZXhwIjoxNTg0MDI0NDIzfQ.eGWhML-v6EMJ9w6Z8cQ_T-VOUB1r6kGnzG5iosp_Np_eNEu9AkorC9sEVwa42UBZf30zhAntVJZzq4wh7Q9SOg',1),(34,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5NTcxNiwiZXhwIjoxNTg0MDI0NTE2fQ.lnrhlbkgfiHvi7d9CzMwyIpmTYOcsGXLRbOEX8H5jg76maDA4fBlcZ5h_5UfAm-k3rDEDGVfjGIR1zBK0StsVg',1),(35,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5NTg0MCwiZXhwIjoxNTg0MDI0NjQwfQ.Qb8DN32UJtt9GTaJuc8sDm4IbIe5ahTCpM9Gzx_aYML3Qg6-6G5p5C9BkXvGMlSboqIqCQs2zhy8jwwq4-zhqA',1),(36,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4Mzk5OTgzMywiZXhwIjoxNTg0MDI4NjMzfQ.v1pTtO7vD1fh4X2sPRWZzZp2UAHQE-i7NWxN5DEPWh7_GdOSdpkYmzoWYpIP0if4scqMOf5K4L1om_g0gnTdSQ',1),(37,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwMDIzODgsImV4cCI6MTU4NDAzMTE4OH0.00HpSmTNNhQQv1w5oSnlB_us0HgFXBiLIBkSqwOV_PJR1AIQdiDgXIi9CtM1yE7LwZQunXf5REysD2frFxFOrg',1),(38,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAwMjcxMywiZXhwIjoxNTg0MDMxNTEzfQ.Tn9KJfvyzMOdXomtredV-RcmnhHaFIRXFgmuqZlPCcAAwsgdvAhDKP4O1k9KKjUGwxFdbXzoBI2G9jU4Kp3PJw',1),(39,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwMDI3ODksImV4cCI6MTU4NDAzMTU4OX0.dvrsaKMkdwd7O3osJgCeiRJb3YWSPgdg0Zw8rj3IYgDEW8DTEus2bbSyHZPI2P2jPUauFNeU_fMBYwThzVaYYw',1),(40,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAwMjg3NSwiZXhwIjoxNTg0MDMxNjc1fQ.En2mEgg9HE6IrkbNqCAz3EPtGA6SqKRVWyzob7zEOsaAoQ0W6F0NI2IfnKXcwPYJsKiFmWpdKF1qwaQ-eVzpKA',1),(41,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAwMjkyMCwiZXhwIjoxNTg0MDMxNzIwfQ.oR_AWPijmbrnkIFCASn2h-gMKbd043pnoc0wEWYCzsrNtqCgCyIgGoLdFrcRauWDXEG_zvxh5CgOsvesRcMQVA',1),(42,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAwOTIyMywiZXhwIjoxNTg0MDM4MDIzfQ.QiIAhNgOdDRIVrSI89O9mkyqFTMebYkbOT7nMUtuA6RMTNWOzxK79uRVRul6MWJo8iQj2Jzz6o9ehu1hSXhTpw',1),(43,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAxNjA4MiwiZXhwIjoxNTg0MDQ0ODgyfQ.njkHIPMgSE93xI6hbkH9vGh2oDT4oY7I9Di2ldeu6pgtv41TQ22FRtrFfm2wfxEWnrPrmPh6_7s6pYgMFw0tYQ',1),(44,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAxNjIzMywiZXhwIjoxNTg0MDQ1MDMzfQ.BR9ZoH4TTgWbKx1WWBmHzorYQFqdqerOL46YXoqXjFuZRf6W4FzLhvDOzELavBI_zf295Ds97-Dk22RLZBDJkA',1),(45,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDAxNjM5MywiZXhwIjoxNTg0MDQ1MTkzfQ.9d0o5dBuZmsddvlW0cEYWDD_pezyoL234HytlGUuI5NzewB_Z_deEaa_YrEeS1jqYxmVB-ln2JIhK6VLQyMLDQ',1),(46,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3NjMzMiwiZXhwIjoxNTg0MTA1MTMyfQ.TaX63DVBiQxrxrJdpZJGDdfM8nZCs1bjf5PTfoh0XdvmxyFpTG6gU1GDdZiwOQU41y0qdjqdDojLzWskdikvJw',1),(47,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwNzY4MjAsImV4cCI6MTU4NDEwNTYyMH0.SrkJWEkiVq1OvkngvQI9TKqzMlqJa1KefnVwU1zFed5_cNlKYW92shg6n7HqBbfbHuA1Jug0xwfbKj7WnpIM_g',1),(48,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3NjgzNSwiZXhwIjoxNTg0MTA1NjM1fQ.nXY6fz3WwHKpuQh1GU9Odry-aW04NK5Ruy0OryYjm2oejhZdFHw4l4RCaJYIRsgnYVOdiPlbJ9nN-8n-hPUp_g',1),(49,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwNzY4NjEsImV4cCI6MTU4NDEwNTY2MX0.REB69AcM41NRkI59cx5VD2bNE4tTyGmdG59WthLYCAePsyjkvayy6a98dppBqs1A_nMc5ncZW9TgV5tcaBINTA',1),(50,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3Njg2NywiZXhwIjoxNTg0MTA1NjY3fQ.g6axSL10cqA9Ph0uprOoJbgfBQ2A_bvtM-v-TCcza2vgSjKhLQQ2engR2-GZSdR5pQapWMRB-nBuLZYjfwvz9w',1),(51,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwNzY4OTEsImV4cCI6MTU4NDEwNTY5MX0.ak413g4NBDg0iydJqNvBrbYDk7Q1-H91XejuZzBGr3A8s37rDiJnPlLb6qqMWiv1KDc-XYux5DAwUOgEz7SoHw',1),(52,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3NzE1NSwiZXhwIjoxNTg0MTA1OTU1fQ.JSkDr7T_DDXsmlYy6kCqbJOAJa9ABIIabca1fdwEi5dasSCmCPHBS73GcA-z4C4pdcfym6cLzQXdhf7YIQZx7A',1),(53,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwNzc3MjEsImV4cCI6MTU4NDEwNjUyMX0.AIzyu59rj9ZbU9ZaljYPYrDxrSzSZUG3OgoJ9M7Kx9JQ6NYpjFuv1FeU_G5X9uej1ZrjUlifar5WoMucYYWBmw',1),(54,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3Nzc2NSwiZXhwIjoxNTg0MTA2NTY1fQ.LGXc8-udKrDJeliIee4JxDspmyBUF0gSbd1fIYDEDKXkOhmNwNdktvgr4CiO32n4ZdtkMBu0jFalx2FbXmtPVg',1),(55,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3Nzc5MiwiZXhwIjoxNTg0MTA2NTkyfQ.So5GjT2RmG262FU4_p92E-zurSWebXk2rHsc81FFc50ARkFoA0iUGEr_zjXoHTeW6kOp3jPp_4faxRna1Yqw6w',1),(56,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwNzc4MDgsImV4cCI6MTU4NDEwNjYwOH0.iItBXs25HGptYeGNrZrHRzu5ws4NwwBHM9DfRQoVEJNUOjluyMGIy9nFLatriCbxZYYqwwZzEgVx2RMyojRw1g',1),(57,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3NzgxNiwiZXhwIjoxNTg0MTA2NjE2fQ.gVNYemLJ2yF8-TNf1xQWihpn5t7EMHt4wZ7KEZAb03jw2DfQEFU8aWjOz2tNcfI76WmJSIWI5BV3yfsGJnBoTQ',1),(58,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA3ODQ3NywiZXhwIjoxNTg0MTA3Mjc3fQ.-Un-rJ-owC3Xl4E_ddYpgplPOQdc9FzqZPikaiP0EuLPZii5GAV4JiWJiYr4fYqLdA_54J10VEjbOpqoc3_MZw',1),(59,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwODI1MzUsImV4cCI6MTU4NDExMTMzNX0.89Zw0OLWt_3vk83Z-6M4OP8OVbUmIiH2MnPyrbokfSdeZBnjRYQmidBMkPIbKkMKizBlqGshLtRkqxHafyvF-w',1),(60,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwODI5NDgsImV4cCI6MTU4NDExMTc0OH0.2JNfg5l9_dV3FCw_vATLirHJ64hQbvZnTwC2f287amUmqHbAO48nPli7jG8HmmndxTP_z1NDRs5bcfOBcKhhZw',1),(61,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA4Mjk1OCwiZXhwIjoxNTg0MTExNzU4fQ.tXox4pewFrhTCaeM6tG_dE0yZPpSxdv5YuACPAwS_kQiWWhvu-aaUdY7dhqOyQrEVXgGXvXtgUaTaMkpQCfiEA',1),(62,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwODI5NzksImV4cCI6MTU4NDExMTc3OX0.1Ydbweg71Ti6EpUK9RKQJkaxFOXWRGW4hdK5QPlL2VlMTMcl9ksrB5JW10ZvsQx4G7NinmlDg7V-Nlogem9ofw',1),(63,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA4Mjk4NywiZXhwIjoxNTg0MTExNzg3fQ.V7hOtDSXV0Oc66SBKQupDK_aotfTbYWyvG_ENfyRgSr94zCd_3KYV3wRu65rNGpHjh7L1_AZWuUazWFW-UrG1A',1),(64,2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrdW5hbC5pbmdvbGVAY3JvcGRhdGEuaW4iLCJpYXQiOjE1ODQwODQ1NTYsImV4cCI6MTU4NDExMzM1Nn0.Cp2LkJwU9PvMumSY9iM1PC1iMeysE9kllEh4SloBSuqA2VgXDYv-CamVex1egHSot-zMkvk9PTSO-wT40bbrug',1),(65,1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXZlay5nYWpiaGl5ZUBjcm9wZGF0YS5pbiIsImlhdCI6MTU4NDA4NDgxNSwiZXhwIjoxNTg0MTEzNjE1fQ.N_d6lA0ON0fTop0kG8ZKTJ57WdQtzDiLZQ_OoKlTkJdI7qxjEBotmFdwwh5r4LZ6ENZQcHYUyQDXUr8HFw2_Vg',1);
/*!40000 ALTER TABLE `app_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_users`
--

DROP TABLE IF EXISTS `app_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleID` int(11) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(250) DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Status` enum('Active','Inactive') DEFAULT 'Inactive',
  `Designation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `EmailIDx` (`Email`),
  KEY `RoleIDx` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_users`
--

LOCK TABLES `app_users` WRITE;
/*!40000 ALTER TABLE `app_users` DISABLE KEYS */;
INSERT INTO `app_users` VALUES (1,1,'Vivek Gajbhiye','vivek.gajbhiye@cropdata.in','$2a$10$wkcPbk.mbwgFSwOQK.R4q.2ZBCqUSaHTzVvzR8znsRsGB5B9/gppO','2020-01-29 05:20:08','2020-02-14 05:18:31','Active','Java Developer'),(2,2,'kunal Ingole','kunal.ingole@cropdata.in','$2a$10$vtQFMw1Um3oJFMG91Oo.p.bVAcQiitQY5A.HKr.fpDFIZjfQvXziu',NULL,NULL,'Active',NULL);
/*!40000 ALTER TABLE `app_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-13 15:10:20
