/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
DROP TABLE IF EXISTS sanxia_Data;
CREATE TABLE `sanxia_Data` (
  `sanxia_Data_PK` mediumint(9) NOT NULL AUTO_INCREMENT,
  `locationID` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `locationName` varchar(255) DEFAULT NULL,
  `envType` varchar(20) DEFAULT NULL,
  `envTypeName` varchar(255) DEFAULT NULL,
  `envCoverUrl` varchar(20) DEFAULT NULL,
  `sensorPhysicalID` varchar(20) NOT NULL,
  `cnName` varchar(20) NOT NULL,
  `units` varchar(20) NOT NULL,
  `sensorPhysicalValue` double(20,2) NOT NULL,
  `sensorState` double(6,0) DEFAULT NULL,
  `state` varchar(20) DEFAULT '1',
  `timestamps` datetime NOT NULL,
  `showType` varchar(20) DEFAULT NULL,
  `zoneID` varchar(20) DEFAULT NULL,
  `nodeType` varchar(20) DEFAULT NULL,
  `lowvoltage` varchar(20) DEFAULT NULL,
  `anomaly` varchar(20) DEFAULT NULL,
  `amp` double(20,6) DEFAULT '0.000000',
  `cum` int(11) DEFAULT '0',
  `score` double(20,6) DEFAULT '0.000000',
  PRIMARY KEY (`sanxia_Data_PK`),
  KEY `sanxia_Data_PK_1` (`sanxia_Data_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=99601 DEFAULT CHARSET=utf8mb4;