-- Active: 1690463718001@@123.60.177.28@3306@sanxiamuseum
drop table if exists RelicData;
CREATE TABLE RelicData(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,  
    envId VARCHAR(255) COMMENT 'envId',
    envType VARCHAR(255) COMMENT "envType",
    envName VARCHAR(255) COMMENT 'envName',
    relicId VARCHAR(255) COMMENT 'Relic ID',
    updateDate VARCHAR(255) COMMENT 'Update Date',
    museumName VARCHAR(255) COMMENT 'Museum Name',
    relicCode VARCHAR(255) COMMENT 'Relic Code',
    relicName VARCHAR(255) COMMENT 'Relic Name',
    relicYears VARCHAR(255) COMMENT 'Relic Years',
    relicLevel VARCHAR(255) COMMENT 'Relic Level',
    relicTexture VARCHAR(255) COMMENT 'Relic Texture',
    relicState VARCHAR(255) COMMENT 'Relic State',
    relicImage VARCHAR(255) COMMENT 'Relic Image'
) COMMENT '文物表' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE INDEX id_1 on RelicData(id);