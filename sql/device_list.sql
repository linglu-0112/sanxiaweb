-- Active: 1690463718001@@123.60.177.28@3306@sanxiamuseum
drop table if exists device_list;
CREATE TABLE device_list(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    deviceId VARCHAR(255) COMMENT 'Device ID',
    deviceName VARCHAR(255) COMMENT 'Device Name',
    envId VARCHAR(255) COMMENT 'Env ID',
    envName VARCHAR(255) COMMENT 'Env Name',    
    deviceType VARCHAR(255) COMMENT 'Device Type',
    deviceTypeName VARCHAR(255) COMMENT 'Device Type Name',
    deviceFactory VARCHAR(255) COMMENT 'Device Factory',
    envirParamType VARCHAR(20) COMMENT 'Envir Param Type',
    collectTime DATETIME COMMENT 'Collect Time',
    envirDataUnit VARCHAR(20) COMMENT 'Envir Data Unit',
    envirParamName VARCHAR(20) COMMENT 'Envir Param Name',
    envirParamCode VARCHAR(20) COMMENT 'Envir Param Code'
) COMMENT '设备列表' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE INDEX id_1 on device_list(id);