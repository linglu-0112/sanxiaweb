-- Active: 1690463718001@@123.60.177.28@3306@sanxiamuseum
drop table if exists envData;
CREATE TABLE envData(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    envId VARCHAR(255) COMMENT 'Env ID',
    parentId VARCHAR(255) COMMENT 'Parent ID',
    envName VARCHAR(255) COMMENT 'Env Name',    
    envType VARCHAR(255) COMMENT 'Env Type',
    envTypeName VARCHAR(255) COMMENT 'Env Type Name',
    envCoverUrl VARCHAR(255) COMMENT 'Env Cover Url',
    collectTime DATETIME COMMENT 'Collect Time',
    envirParamType VARCHAR(20) COMMENT 'Envir Param Type',
    envirParamValue VARCHAR(20) COMMENT 'Envir Param Value'
) COMMENT '保存空间环境数据' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE INDEX id_1 on envData(id);