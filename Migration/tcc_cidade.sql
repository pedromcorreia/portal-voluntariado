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
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cidade` (
  `cidade` int(11) NOT NULL AUTO_INCREMENT,
  `UF` char(2) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cidade`),
  KEY `fk_cidade_uf_idx` (`UF`),
  CONSTRAINT `fk_cidade_uf` FOREIGN KEY (`UF`) REFERENCES `uf` (`uf`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'AC','Rio Branco'),(2,'AC','Cruzeiro do Sul'),(3,'AC','Sena Madureira'),(4,'AC','Tarauacá'),(5,'AC','Feijó'),(6,'AC','Brasiléia'),(7,'AC','Senador Guiomard'),(8,'AC','Plácido de Castro'),(9,'AC','Xapuri'),(10,'AC','Rodrigues Alves'),(11,'AL','Maceió'),(12,'AL','Arapiraca'),(13,'AL','Rio Largo'),(14,'AL','Palmeira dos Índios'),(15,'AL','União dos Palmares'),(16,'AL','Penedo'),(17,'AL','São Miguel dos Campos'),(18,'AL','Coruripe'),(19,'AL','Campo Alegre'),(20,'AL','Delmiro Gouveia'),(21,'AP','Macapá'),(22,'AP','Santana'),(23,'AP','Laranjal do Jari'),(24,'AP','Oiapoque'),(25,'AP','Porto Grande'),(26,'AP','Mazagão'),(27,'AP','Tartarugalzinho'),(28,'AP','Pedra Branca do Amapari'),(29,'AP','Vitória do Jari'),(30,'AP','Calçoene'),(31,'AM','Manaus'),(32,'AM','Parintins'),(33,'AM','Itacoatiara'),(34,'AM','Manacapuru'),(35,'AM','Coari'),(36,'AM','Tabatinga'),(37,'AM','Maués'),(38,'AM','Tefé'),(39,'AM','Manicoré'),(40,'AM','Humaitá'),(41,'BA','Salvador'),(42,'BA','Feira de Santana'),(43,'BA','Vitória da Conquista'),(44,'BA','Camaçari'),(45,'BA','Itabuna'),(46,'BA','Juazeiro'),(47,'BA','Ilhéus'),(48,'BA','Lauro de Freitas'),(49,'BA','Jequié'),(50,'BA','Alagoinhas'),(51,'CE','Fortaleza'),(52,'CE','Caucaia'),(53,'CE','Juazeiro do Norte'),(54,'CE','Maracanaú'),(55,'CE','Sobral'),(56,'CE','Crato'),(57,'CE','Itapipoca'),(58,'CE','Maranguape'),(59,'CE','Iguatu'),(60,'CE','Quixadá'),(61,'DF','Brasília'),(62,'DF','Ceilândia'),(63,'DF','Taguatinga'),(64,'DF','Planaltina'),(65,'DF','Samambaia'),(66,'DF','Recanto das Emas'),(67,'DF','Águas Claras'),(68,'DF','Gama'),(69,'DF','Santa Maria'),(70,'DF','Guará'),(71,'ES','Vitória'),(72,'ES','Serra'),(73,'ES','Vila Velha'),(74,'ES','Cariacica'),(75,'ES','Cachoeiro de Itapemirim'),(76,'ES','Linhares'),(77,'ES','São Mateus'),(78,'ES','Colatina'),(79,'ES','Guarapari'),(80,'ES','Aracruz'),(81,'GO','Goiânia'),(82,'GO','Aparecida de Goiânia'),(83,'GO','Anápolis'),(84,'GO','Rio Verde'),(85,'GO','Luziânia'),(86,'GO','Águas Lindas de Goiás'),(87,'GO','Valparaíso de Goiás'),(88,'GO','Trindade'),(89,'GO','Formosa'),(90,'GO','Novo Gama'),(91,'MA','São Luís'),(92,'MA','Imperatriz'),(93,'MA','São José de Ribamar'),(94,'MA','Timon'),(95,'MA','Caxias'),(96,'MA','Codó'),(97,'MA','Paço do Lumiar'),(98,'MA','Açailândia'),(99,'MA','Bacabal'),(100,'MA','Balsas'),(101,'MT','Cuiabá'),(102,'MT','Várzea Grande'),(103,'MT','Rondonópolis'),(104,'MT','Sinop'),(105,'MT','Tangará da Serra'),(106,'MT','Cáceres'),(107,'MT','Sorriso'),(108,'MT','Lucas do Rio Verde'),(109,'MT','Primavera do Leste'),(110,'MT','Barra do Garças'),(111,'MS','Campo Grande'),(112,'MS','Dourados'),(113,'MS','Três Lagoas'),(114,'MS','Corumbá'),(115,'MS','Ponta Porã'),(116,'MS','Sidrolândia'),(117,'MS','Naviraí'),(118,'MS','Nova Andradina'),(119,'MS','Aquidauana'),(120,'MS','Maracaju'),(121,'MG','Belo Horizone'),(122,'MG','Uberlândia'),(123,'MG','Contagem'),(124,'MG','Juiz de Fora'),(125,'MG','Betim'),(126,'MG','Montes Claros'),(127,'MG','Ribeirão das Neves'),(128,'MG','Uberaba'),(129,'MG','Governador Valadares'),(130,'MG','Ipatinga'),(131,'PA','Belém'),(132,'PA','Ananindeua'),(133,'PA','Santarém'),(134,'PA','Marabé'),(135,'PA','Parauapebas'),(136,'PA','Castanhal'),(137,'PA','Abaetetuba'),(138,'PA','Cametá'),(139,'PA','Marituba'),(140,'PA','Bragança'),(141,'PB','João Pessoa'),(142,'PB','Campina Grande'),(143,'PB','Santa Rita'),(144,'PB','Patos'),(145,'PB','Bayeux'),(146,'PB','Sousa'),(147,'PB','Cabedelo'),(148,'PB','Cajazeiras'),(149,'PB','Guarabira'),(150,'PB','Sapé'),(151,'PR','Curitiba'),(152,'PR','Londrina'),(153,'PR','Maringá'),(154,'PR','Ponta Grossa'),(155,'PR','Cascavel'),(156,'PR','São José dos Pinhais'),(157,'PR','Foz do Iguaçu'),(158,'PR','Colombo'),(159,'PR','Gurapuava'),(160,'PR','Paranaguá'),(161,'PE','Recife'),(162,'PE','Jaboatão dos Guararapes'),(163,'PE','Olinda'),(164,'PE','Caruara'),(165,'PE','Petrolina'),(166,'PE','Paulista'),(167,'PE','Cabo de Santo Agostinho'),(168,'PE','Camaragibe'),(169,'PE','Garanhuns'),(170,'PE','Vitória de Santo Antão'),(171,'RJ','Rio de Janeiro'),(172,'RJ','São Gonçalo'),(173,'RJ','Duque de Caxias'),(174,'RJ','Nova Iguaçu'),(175,'RJ','Niterói'),(176,'RJ','São João de Meriti'),(177,'RJ','Belford Roxo'),(178,'RJ','Campos dos Goytacazes'),(179,'RJ','Petrópolis'),(180,'RJ','Volta Redonda'),(181,'RN','Natal'),(182,'RN','Mossoró'),(183,'RN','Parnamirim'),(184,'RN','São Gonçalo do Amarante'),(185,'RN','Macaíba'),(186,'RN','Ceará-Mirim'),(187,'RN','Caicó'),(188,'RN','Assu'),(189,'RN','Currais Novos'),(190,'RN','São João de Mipibu'),(191,'RS','Porto Alegre'),(192,'RS','Caxias do Sul'),(193,'RS','Pelotas'),(194,'RS','Canoas'),(195,'RS','Santa Maria'),(196,'RS','Gravataí'),(197,'RS','Viamão'),(198,'RS','Novo Hamburgo'),(199,'RS','São Leopoldo'),(200,'RS','Rio Grande'),(201,'RO','Porto Velho'),(202,'RO','Ji-Paraná'),(203,'RO','Ariquemes'),(204,'RO','Vilhena'),(205,'RO','Cacoal'),(206,'RO','Rolim de Moura'),(207,'RO','Jaru'),(208,'RO','Guajará-Mirim'),(209,'RO','Pimenta Bueno'),(210,'RO','Ouro Preto do Oeste'),(211,'RR','Boa Vista'),(212,'RR','Rorainópolis'),(213,'RR','Caracaraí'),(214,'RR','Pacaraima'),(215,'RR','Cantá'),(216,'RR','Alto Alegre'),(217,'RR','Mucajaí'),(218,'RR','Bonfim'),(219,'RR','Amajari'),(220,'RR','Iracema'),(221,'SC','Florianópolis'),(222,'SC','Joinville'),(223,'SC','Blumenau'),(224,'SC','São José'),(225,'SC','Chapecó'),(226,'SC','Itajaí'),(227,'SC','Criciúma'),(228,'SC','Jaraguá do Sul'),(229,'SC','Palhoça'),(230,'SC','Lages'),(231,'SP','São Paulo'),(232,'SP','Guarulhos'),(233,'SP','Campinas'),(234,'SP','São Bernardo do Campo'),(235,'SP','Santo André'),(236,'SP','Osasco'),(237,'SP','São José dos Campos'),(238,'SP','Ribeirão Preto'),(239,'SP','Sorocaba'),(240,'SP','Mauá'),(241,'SE','Aracajú'),(242,'SE','Nossa Senhora do Socorro'),(243,'SE','Lagarto'),(244,'SE','Itabaiana'),(245,'SE','São Cristóvão'),(246,'SE','Estância'),(247,'SE','Tobias Barreto'),(248,'SE','Itabaianinha'),(249,'SE','Simão Dias'),(250,'SE','Nossa Senhora da Glória'),(251,'TO','Palmas'),(252,'TO','Araguaína'),(253,'TO','Gurupi'),(254,'TO','Porto Nacional'),(255,'TO','Paraíso dos Tocantins'),(256,'TO','Araguatins'),(257,'TO','Colinas do Tocantins'),(258,'TO','Guaraí'),(259,'TO','Tocantinópolis'),(260,'TO','Dianópolis');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
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
