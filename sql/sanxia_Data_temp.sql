/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
DROP TABLE IF EXISTS 2022_henan_Data_temp;
CREATE TABLE `2022_henan_Data_temp` (
  `henan_Data_PK` mediumint(9) NOT NULL AUTO_INCREMENT,
  `locationID` varchar(20) DEFAULT NULL,
  `locationName` varchar(20) DEFAULT NULL,
  `sensorPhysicalID` varchar(20) NOT NULL,
  `cnName` varchar(20) NOT NULL,
  `units` varchar(20) NOT NULL,
  `sensorPhysicalValue` double(20,2) NOT NULL,
  `sensorState` double(6,0) NOT NULL,
  `state` varchar(20) NOT NULL,
  `timestamps` datetime NOT NULL,
  `showType` varchar(20) NOT NULL,
  `zoneID` varchar(20) DEFAULT NULL,
  `nodeType` varchar(20) NOT NULL,
  `lowvoltage` varchar(20) NOT NULL,
  `anomaly` varchar(20) NOT NULL,
  `amp` double(20,6) NOT NULL,
  `cum` int(11) NOT NULL,
  `score` double(20,6) NOT NULL,
  PRIMARY KEY (`henan_Data_PK`),
  KEY `henan_Data_PK_1` (`henan_Data_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;