/* 2023-08-01 20:44:50 [30 ms] */ 
CREATE TABLE table1(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    envId VARCHAR(20),
    envName VARCHAR(20),
    parentId VARCHAR(20),
    envType VARCHAR(20),
    envTypeName VARCHAR(20),
    envCoverUrl VARCHAR(20)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/* 2023-08-01 20:45:10 [19 ms] */ 
CREATE INDEX id_1 on table1(id);
/* 2023-08-28 09:50:49 [38 ms] */ 
drop table if exists environment_indicator;
/* 2023-08-28 09:50:50 [26 ms] */ 
CREATE TABLE environment_indicator(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    envirParamType VARCHAR(20),
    envirDataUnit VARCHAR(20),
    envirParamName VARCHAR(20),
    envirParamCode VARCHAR(20)
) COMMENT '环境因子数据表' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/* 2023-08-28 09:50:51 [17 ms] */ 
CREATE INDEX id_1 on environment_indicator(id);
/* 2023-08-28 09:56:21 [42 ms] */ 
drop table if exists environmentTypes;
/* 2023-08-28 09:56:24 [20 ms] */ 
CREATE TABLE environmentTypes(  
    id MEDIUMINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    envirParamType VARCHAR(20),
    name VARCHAR(255)
) COMMENT '环境类型数据表' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/* 2023-08-28 09:56:26 [19 ms] */ 
CREATE INDEX id_1 on environmentTypes(id);
/* 2023-08-28 10:11:54 [12 ms] */ 
drop table if exists device_list;
/* 2023-08-28 10:11:55 [20 ms] */ 
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
/* 2023-08-28 10:11:55 [20 ms] */ 
CREATE INDEX id_1 on device_list(id);
/* 2023-08-28 10:50:13 [16 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100);
/* 2023-08-28 10:50:18 [12 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200);
/* 2023-08-28 10:50:21 [12 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300);
/* 2023-08-28 10:50:24 [13 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400);
/* 2023-08-28 10:50:29 [12 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444);
/* 2023-08-28 16:54:49 [14 ms] */ 
DELETE FROM `environment_indicator` WHERE `id` IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
/* 2023-08-29 15:04:42 [12 ms] */ 
DELETE FROM `environment_indicator` WHERE `id` IN (25,26,27,28,29,30,31,32,33,34,35,36);
/* 2023-08-29 15:05:36 [11 ms] */ 
DELETE FROM `environment_indicator` WHERE `id` IN (37,38,39,40,41,42,43,44,45,46,47,48,49);
/* 2023-08-29 15:17:37 [12 ms] */ 
DELETE FROM `environment_indicator` WHERE `id` IN (50,51,52,53,54,55,56,57,58,59,60,61,62);
/* 2023-08-29 16:06:31 [13 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544);
/* 2023-08-29 16:06:39 [21 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644);
/* 2023-08-29 16:06:41 [13 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,739,740,741,742,743,744);
/* 2023-08-29 16:06:45 [15 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844);
/* 2023-08-29 16:07:03 [71 ms] */ 
DELETE FROM `sanxia_museum_levelInfor` WHERE `id` IN (845,846,847,848,849,850,851,852,853,854,855,856);
/* 2023-08-29 16:32:27 [12 ms] */ 
DELETE FROM `environmentTypes` WHERE `id` IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28);
/* 2023-08-29 16:39:14 [13 ms] */ 
DELETE FROM `device_list` WHERE `id` IN (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100);
/* 2023-08-29 16:39:17 [12 ms] */ 
DELETE FROM `device_list` WHERE `id` IN (101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200);
/* 2023-08-29 16:39:20 [13 ms] */ 
DELETE FROM `device_list` WHERE `id` IN (201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230);
/* 2023-08-29 16:40:14 [51 ms] */ 
DELETE FROM `environment_indicator` WHERE `id` IN (63,64,65,66,67,68,69,70,71,72,73,74,75);
/* 2023-09-04 10:47:48 [29 ms] */ 
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
/* 2023-09-04 10:47:54 [19 ms] */ 
CREATE INDEX id_1 on envData(id);
/* 2023-09-04 10:48:26 [20 ms] */ 
drop table if exists envData;
/* 2023-09-04 10:48:28 [20 ms] */ 
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
/* 2023-09-04 10:48:29 [18 ms] */ 
CREATE INDEX id_1 on envData(id);
