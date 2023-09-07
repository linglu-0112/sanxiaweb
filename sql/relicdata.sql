-- Active: 1691725592907@@123.60.177.28@3306@sanxiamuseum
/* relicId,updateDate,museumName,relicCode,relicName,relicYears,relicLevel,relicTexture,relicState,relicImage */
drop table if exists RelicData;
CREATE TABLE RelicData(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,    create_time DATETIME COMMENT 'Create Time',
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