-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: tcc
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `voluntariocausa`
--

DROP TABLE IF EXISTS `voluntariocausa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `voluntariocausa` (
  `usuario` int(11) NOT NULL,
  `causa` int(11) NOT NULL,
  `ordem` int(11) NOT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  KEY `fk_usuario_idx` (`usuario`),
  KEY `fk_volunt_causa_idx` (`causa`),
  CONSTRAINT `fk_voluntcausa_causa` FOREIGN KEY (`causa`) REFERENCES `causa` (`causa`),
  CONSTRAINT `fk_voluntcausa_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voluntariocausa`
--

LOCK TABLES `voluntariocausa` WRITE;
/*!40000 ALTER TABLE `voluntariocausa` DISABLE KEYS */;
INSERT INTO `voluntariocausa` VALUES (9,1,1,'comentario 1'),(9,2,2,'comentario 2'),(9,3,3,'comentario 3'),(11,2,1,'111'),(11,11,2,'4454'),(11,21,3,'789'),(14,14,1,'abcd'),(14,16,2,'abcd'),(14,10,3,'abcd'),(21,9,1,'abca'),(21,21,2,'abc'),(21,5,3,'abc');
/*!40000 ALTER TABLE `voluntariocausa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-21 19:21:55
