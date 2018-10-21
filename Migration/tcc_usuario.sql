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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `usuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `senha` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `foto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'stahlschmidt@gmail.com','202cb962ac59075b964b07152d234b70',NULL),(2,'juannicolas2712@gmail.com','202cb962ac59075b964b07152d234b70',NULL),(3,'walter@walter','202cb962ac59075b964b07152d234b70',NULL),(9,'razer@mail','202cb962ac59075b964b07152d234b70','pedro.jpg'),(10,'teste@mail','202cb962ac59075b964b07152d234b70',NULL),(11,'','d41d8cd98f00b204e9800998ecf8427e','taras-bulba.jpg'),(12,'tudo@vaidade','81dc9bdb52d04dc20036dbd8313ed055',NULL),(13,'joao@silva','81dc9bdb52d04dc20036dbd8313ed055',NULL),(14,'pedro@pedra','81dc9bdb52d04dc20036dbd8313ed055','pedro.jpg'),(15,'vol@vol','81dc9bdb52d04dc20036dbd8313ed055',NULL),(18,'help@you','81dc9bdb52d04dc20036dbd8313ed055','panda.jpg'),(19,'mary@jane','202cb962ac59075b964b07152d234b70',NULL),(20,'cap@america','81dc9bdb52d04dc20036dbd8313ed055',NULL),(21,'razer2@teste','202cb962ac59075b964b07152d234b70','pedro.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
