/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
DROP TABLE IF EXISTS 2022_henan_exhibition;
CREATE TABLE `2022_henan_exhibition` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `num` varchar(20) DEFAULT NULL,
  `zoneID` varchar(100) NOT NULL,
  `locationID` varchar(20) DEFAULT NULL,
  `locationName` varchar(20) DEFAULT NULL,
  `sensorPhysicalID` varchar(200) NOT NULL,
  `volum` double(6,2) DEFAULT NULL,
  `culturalRelic` varchar(100) DEFAULT NULL,
  `texture` varchar(20) DEFAULT NULL,
  `tempMax` double(6,0) DEFAULT NULL,
  `tempMin` double(6,0) DEFAULT NULL,
  `humMax` double(6,0) DEFAULT NULL,
  `humMin` double(6,0) DEFAULT NULL,
  `illuminance` double(6,0) DEFAULT NULL,
  `controlWay` varchar(100) DEFAULT NULL,
  `dosage` varchar(100) DEFAULT NULL,
  `sensorState` int(11) DEFAULT NULL,
  `exhibitionState` int(11) DEFAULT NULL,
  `culturalRelicLevel` varchar(100) DEFAULT NULL,
  `disease` varchar(100) DEFAULT NULL,
  `culturalRelicID` varchar(100) DEFAULT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;