-- Active: 1694594017448@@123.60.177.28@3306@sanxiamuseum
DROP TABLE IF EXISTS `sanxia_exhibition`;
CREATE TABLE `sanxia_exhibition` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `num` varchar(20) DEFAULT NULL,
  `zoneID` varchar(100) DEFAULT NULL,
  `locationID` varchar(20) DEFAULT NULL,
  `locationName` varchar(20) DEFAULT NULL,
  `sensorPhysicalID` varchar(200) DEFAULT '{"32":{"max":"60","min":"50"},"33":{"max":"22","min":"18"},"43":{"max":"13.89","min":"7.43"},"36":{},"41":{},"42":{}}',
  `volum` double(6,2) DEFAULT NULL,
  `culturalRelic` varchar(100) DEFAULT NULL,
  `texture` varchar(20) DEFAULT NULL,
  `tempMax` double(6,0) DEFAULT 22,
  `tempMin` double(6,0) DEFAULT 18,
  `humMax` double(6,0) DEFAULT 60,
  `humMin` double(6,0) DEFAULT 50,
  `illuminance` double(6,0) DEFAULT 150,
  `controlWay` varchar(100) DEFAULT '被动',
  `dosage` varchar(100) DEFAULT NULL,
  `sensorState` int(11) DEFAULT 0,
  `exhibitionState` int(11) DEFAULT 0,
  `culturalRelicLevel` varchar(100),
  `disease` varchar(100) DEFAULT '暂无',
  `culturalRelicID` varchar(100) DEFAULT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;