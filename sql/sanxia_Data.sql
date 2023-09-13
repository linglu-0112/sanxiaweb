-- Active: 1690463718001@@123.60.177.28@3306@sanxiamuseum

DROP TABLE IF EXISTS sanxia_Data;
CREATE TABLE sanxia_Data(  
    `sanxia_Data_PK` mediumint(9) NOT NULL AUTO_INCREMENT,
    `locationID` varchar(255) DEFAULT NULL,
    `parentId` VARCHAR(255) DEFAULT NULL,
    `locationName` varchar(255) DEFAULT NULL,
    `envType` VARCHAR(20) DEFAULT NULL,
    `envTypeName` VARCHAR(255) DEFAULT NULL,
    `envCoverUrl` VARCHAR(20),
    `sensorPhysicalID` varchar(20) NOT NULL,
    `cnName` varchar(20) NOT NULL,
    `units` varchar(20) NOT NULL,
    `sensorPhysicalValue` double(20,2) NOT NULL,
    `sensorState` double(6,0) ,
    `state` varchar(20) DEFAULT "1",
    `timestamps` datetime NOT NULL,
    `showType` varchar(20) DEFAULT NULL,
    `zoneID` varchar(20) DEFAULT NULL,
    `nodeType` varchar(20) DEFAULT NULL,
    `lowvoltage` varchar(20) DEFAULT NULL,
    `anomaly` varchar(20) DEFAULT NULL,
    `amp` double(20,6) DEFAULT NULL,
    `cum` int(11) DEFAULT NULL,
    `score` double(20,6) DEFAULT NULL,
    PRIMARY KEY (`sanxia_Data_PK`),
    KEY `sanxia_Data_PK_1` (`sanxia_Data_PK`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;