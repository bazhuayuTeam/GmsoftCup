/*
Navicat MySQL Data Transfer

Source Server         : wsf
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : gmsoftcup

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-06 08:35:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for academic
-- ----------------------------
DROP TABLE IF EXISTS `academic`;
CREATE TABLE `academic` (
  `academicID` varchar(255) NOT NULL,
  `academicCode` varchar(255) DEFAULT NULL,
  `academicName` varchar(255) DEFAULT NULL,
  `academicShort` varchar(255) DEFAULT NULL,
  `academicType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`academicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of academic
-- ----------------------------
INSERT INTO `academic` VALUES ('01', '01', '数学与统计学院', '数学与统计学院', 'BMType0001');
INSERT INTO `academic` VALUES ('02', '02', '经济与贸易学院', '经济与贸易学院', 'BMType0001');
INSERT INTO `academic` VALUES ('03', '03', '计算机科学与工程学院', '计算机科学与工程学院', 'BMType0001');
INSERT INTO `academic` VALUES ('04', '04', '车辆工程学院', '车辆工程学院', 'BMType0001');
INSERT INTO `academic` VALUES ('05', '05', '思想政治教育学院', '思想政治教育学院', 'BMType0001');
INSERT INTO `academic` VALUES ('06', '06', '会计学院', '会计学院', 'BMType0001');
INSERT INTO `academic` VALUES ('07', '07', '电子信息与自动化学院', '电子信息与自动化学院', 'BMType0001');
INSERT INTO `academic` VALUES ('08', '08', '管理学院', '管理学院', 'BMType0001');
INSERT INTO `academic` VALUES ('09', '09', '材料科学与工程学院', '材料科学与工程学院', 'BMType0001');
INSERT INTO `academic` VALUES ('10', '10', '药学与生物工程学院', '药学与生物工程学院', 'BMType0001');
INSERT INTO `academic` VALUES ('11', '11', '语言学院', '语言学院', 'BMType0001');
INSERT INTO `academic` VALUES ('12', '12', '机械工程学院', '机械工程学院', 'BMType0001');
INSERT INTO `academic` VALUES ('14', '14', '体育教学部', '体育教学部', 'BMType0001');
INSERT INTO `academic` VALUES ('15', '15', '化学化工学院', '化学化工学院', 'BMType0001');
INSERT INTO `academic` VALUES ('16', '16', '光电信息学院', '光电信息学院', 'BMType0001');
INSERT INTO `academic` VALUES ('17', '17', '重庆知识产权学院', '重庆知识产权学院', 'BMType0001');
INSERT INTO `academic` VALUES ('19', '19', '两江国际学院', '两江国际学院', 'BMType0001');
INSERT INTO `academic` VALUES ('20', '20', '商贸信息学院', '商贸信息学院', 'BMType0001');
INSERT INTO `academic` VALUES ('21', '21', '应用技术学院', '应用技术学院', 'BMType0001');
INSERT INTO `academic` VALUES ('54', '54', '两江校区管理委员会', '两江校区管理委员会', 'BMType0001');

-- ----------------------------
-- Table structure for acceptdetail
-- ----------------------------
DROP TABLE IF EXISTS `acceptdetail`;
CREATE TABLE `acceptdetail` (
  `acceptId` varchar(255) NOT NULL,
  `messageId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`acceptId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of acceptdetail
-- ----------------------------

-- ----------------------------
-- Table structure for codetable
-- ----------------------------
DROP TABLE IF EXISTS `codetable`;
CREATE TABLE `codetable` (
  `codeTableCode` varchar(255) NOT NULL,
  `chineseChar` varchar(255) DEFAULT NULL,
  `codeTableName` varchar(255) DEFAULT NULL,
  `codeTableValue` varchar(255) DEFAULT NULL,
  `codeType` varchar(255) DEFAULT NULL,
  `hasChild` bit(1) DEFAULT NULL,
  `level0` int(11) DEFAULT NULL,
  `parentCode` varchar(255) DEFAULT NULL,
  `createId` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codeTableCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of codetable
-- ----------------------------
INSERT INTO `codetable` VALUES ('BMType', null, '部门类型', null, 'BMType', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('BMType0001', null, '教学', null, 'BMType', '\0', '1', 'BMType', null, null);
INSERT INTO `codetable` VALUES ('BMType0002', null, '行政', null, 'BMType', '\0', '1', 'BMType', null, null);
INSERT INTO `codetable` VALUES ('education', null, '学历', null, 'education', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('education0001', null, '本科', null, 'education', '\0', '1', 'education', null, null);
INSERT INTO `codetable` VALUES ('education0002', null, '研究生', null, 'education', '\0', '1', 'education', null, null);
INSERT INTO `codetable` VALUES ('education0003', null, '专科', null, 'education', '\0', '1', 'education', null, null);
INSERT INTO `codetable` VALUES ('gameStep', null, '竞赛类别', null, 'gameStep', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('gameStep0001', null, '创意阶段', null, 'gameStep', '\0', '1', 'gameStep', null, null);
INSERT INTO `codetable` VALUES ('gameStep0002', null, '作品阶段', null, 'gameStep', '\0', '1', 'gameStep', null, null);
INSERT INTO `codetable` VALUES ('gameStep0003', null, '推广阶段', null, 'gameStep', '\0', '1', 'gameStep', null, null);
INSERT INTO `codetable` VALUES ('GameType', null, '竞赛类型', null, 'GameType', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('GameType0001', null, '数据建模', null, 'GameType', '\0', '1', 'GameType', null, null);
INSERT INTO `codetable` VALUES ('GameType0002', null, '程序设计', null, 'GameType', '\0', '1', 'GameType', null, null);
INSERT INTO `codetable` VALUES ('GameType0003', null, '其他', null, 'GameType', '\0', '1', 'GameType', null, null);
INSERT INTO `codetable` VALUES ('profession', null, '职称', null, 'profession', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('profession0001', null, '讲师', null, 'profession', '\0', '1', 'profession', null, null);
INSERT INTO `codetable` VALUES ('profession0002', null, '副教授', null, 'profession', '\0', '1', 'profession', null, null);
INSERT INTO `codetable` VALUES ('profession0003', null, '教授', null, 'profession', '\0', '1', 'profession', null, null);
INSERT INTO `codetable` VALUES ('task', null, '竞赛任务', null, 'task', '', '0', null, null, null);
INSERT INTO `codetable` VALUES ('task0001', null, '初赛', null, 'task', '\0', '1', 'task', null, null);
INSERT INTO `codetable` VALUES ('task0002', null, '决赛', null, 'task', '\0', '1', 'task', null, null);

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
  `columnID` varchar(255) NOT NULL,
  `columnName` varchar(255) DEFAULT NULL,
  `columnURL` varchar(255) DEFAULT NULL,
  `icon` int(11) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  `showMenuPage` bit(1) DEFAULT NULL,
  `showOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`columnID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of columns
-- ----------------------------

-- ----------------------------
-- Table structure for comparam
-- ----------------------------
DROP TABLE IF EXISTS `comparam`;
CREATE TABLE `comparam` (
  `comParamId` varchar(255) NOT NULL,
  `comParamLevel` varchar(255) DEFAULT NULL,
  `comParamName` varchar(255) DEFAULT NULL,
  `comParamNo` varchar(255) DEFAULT NULL,
  `comParamType` varchar(255) DEFAULT NULL,
  `parentComParamNo` varchar(255) DEFAULT NULL,
  `useId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comParamId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comparam
-- ----------------------------

-- ----------------------------
-- Table structure for crew
-- ----------------------------
DROP TABLE IF EXISTS `crew`;
CREATE TABLE `crew` (
  `crewID` varchar(255) NOT NULL,
  `teamId` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `membernAcademy` varchar(255) DEFAULT NULL,
  `membernName` varchar(255) DEFAULT NULL,
  `membernNo` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `telePhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crewID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crew
-- ----------------------------
INSERT INTO `crew` VALUES ('20170321234810540', '20170321234810416', '0', '11303080129', null, null, null, null, null, null);
INSERT INTO `crew` VALUES ('20170322002341633', '20170322002341297', '0', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for desktop
-- ----------------------------
DROP TABLE IF EXISTS `desktop`;
CREATE TABLE `desktop` (
  `desktopID` varchar(255) NOT NULL,
  `columnID` varchar(255) DEFAULT NULL,
  `operatorID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`desktopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desktop
-- ----------------------------

-- ----------------------------
-- Table structure for discipline
-- ----------------------------
DROP TABLE IF EXISTS `discipline`;
CREATE TABLE `discipline` (
  `disciplineID` varchar(255) NOT NULL,
  `academicID` varchar(255) DEFAULT NULL,
  `curMaster` varchar(255) DEFAULT NULL,
  `curStudent` varchar(255) DEFAULT NULL,
  `degreeType` varchar(255) DEFAULT NULL,
  `disciplineName` varchar(255) DEFAULT NULL,
  `disciplineShort` varchar(255) DEFAULT NULL,
  `disciplineType` varchar(255) DEFAULT NULL,
  `enrollTime` varchar(255) DEFAULT NULL,
  `graduate` varchar(255) DEFAULT NULL,
  `lengthOfschool` varchar(255) DEFAULT NULL,
  `majorCode` varchar(255) DEFAULT NULL,
  `propertyType` varchar(255) DEFAULT NULL,
  `recruitType` varchar(255) DEFAULT NULL,
  `undergraduate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`disciplineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discipline
-- ----------------------------
INSERT INTO `discipline` VALUES ('0101', '01', null, null, null, '数学与应用数学（数理金融）', '数学与应用数学（数理金融）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0102', '01', null, null, null, '信息与计算科学（工程计算与数据处理）', '信息与计算科学（工程计算与数据处理）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0103', '01', null, null, null, '应用统计学（应用数理统计）', '应用统计学（应用数理统计）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0104', '01', null, null, null, '金融数学', '金融数学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0201', '02', null, null, null, '金融工程', '金融工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0202', '02', null, null, null, '人力资源管理Z', '人力资源管理Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0203', '02', null, null, null, '经济学类', '经济学类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0204', '02', null, null, null, '经济学', '经济学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0205', '02', null, null, null, '金融学[教改]', '金融学[教改]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0206', '02', null, null, null, '国际经济与贸易', '国际经济与贸易', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0209', '02', null, null, null, '金融学', '金融学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0213', '02', null, null, null, '人力资源管理', '人力资源管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0215', '02', null, null, null, '劳动与社会保障', '劳动与社会保障', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0301', '03', null, null, null, '物联网工程', '物联网工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0302', '03', null, null, null, '网络工程', '网络工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0303', '03', null, null, null, '计算机科学与技术(卓越工程师)', '计算机科学与技术(卓越工程师)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0304', '03', null, null, null, '计算机科学与技术Z', '计算机科学与技术Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0305', '03', null, null, null, '计算机科学与技术', '计算机科学与技术', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0306', '03', null, null, null, '软件工程', '软件工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0307', '03', null, null, null, '信息管理与信息系统', '信息管理与信息系统', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0308', '03', null, null, null, '计算机科学与技术（教改）', '计算机科学与技术（教改）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0309', '03', null, null, null, '计算机类', '计算机类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0401', '04', null, null, null, '汽车服务工程', '汽车服务工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0402', '04', null, null, null, '能源与动力工程（汽车发动机）', '能源与动力工程（汽车发动机）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0403', '04', null, null, null, '装甲车辆工程', '装甲车辆工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0405', '04', null, null, null, '汽车服务工程Z', '汽车服务工程Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0415', '04', null, null, null, '工业设计', '工业设计', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0416', '04', null, null, null, '车辆工程', '车辆工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0601', '06', null, null, null, '会计学（会计信息化）', '会计学（会计信息化）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0602', '06', null, null, null, '审计学', '审计学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0603', '06', null, null, null, '会计学(ACCA)', '会计学(ACCA)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0604', '06', null, null, null, '财务管理(CIMA)', '财务管理(CIMA)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0605', '06', null, null, null, '会计学（信息化）Z', '会计学（信息化）Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0606', '06', null, null, null, '财务管理Z', '财务管理Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0607', '06', null, null, null, '会计学', '会计学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0608', '06', null, null, null, '会计学（教改）', '会计学（教改）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0609', '06', null, null, null, '财务管理', '财务管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0701', '07', null, null, null, '电气电子类', '电气电子类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0702', '07', null, null, null, '电气工程及其自动化Z', '电气工程及其自动化Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0703', '07', null, null, null, '自动化Z', '自动化Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0704', '07', null, null, null, '测控技术与仪器', '测控技术与仪器', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0705', '07', null, null, null, '电气工程及其自动化', '电气工程及其自动化', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0706', '07', null, null, null, '自动化', '自动化', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0708', '07', null, null, null, '电子信息工程', '电子信息工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0709', '07', null, null, null, '通信工程', '通信工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0710', '07', null, null, null, '电气工程及其自动化[卓越工程师]', '电气工程及其自动化[卓越工程师]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0801', '08', null, null, null, '工商管理（ABE）', '工商管理（ABE）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0802', '08', null, null, null, '广告学', '广告学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0803', '08', null, null, null, '旅游管理Z', '旅游管理Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0804', '08', null, null, null, '物流管理Z', '物流管理Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0805', '08', null, null, null, '市场营销Z', '市场营销Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0806', '08', null, null, null, '工商管理Z', '工商管理Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0807', '08', null, null, null, '工商管理类', '工商管理类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0808', '08', null, null, null, '行政管理', '行政管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0809', '08', null, null, null, '工商管理', '工商管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0810', '08', null, null, null, '产品设计', '产品设计', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0813', '08', null, null, null, '市场营销', '市场营销', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0817', '08', null, null, null, '旅游管理', '旅游管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0818', '08', null, null, null, '物流管理', '物流管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0819', '08', null, null, null, '土地资源管理', '土地资源管理', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0901', '09', null, null, null, '高分子材料与工程', '高分子材料与工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0902', '09', null, null, null, '焊接技术与工程', '焊接技术与工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0903', '09', null, null, null, '材料成型及控制工程Z', '材料成型及控制工程Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0904', '09', null, null, null, '材料科学与工程', '材料科学与工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0905', '09', null, null, null, '材料成型及控制工程', '材料成型及控制工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('0906', '09', null, null, null, '材料类', '材料类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1001', '10', null, null, null, '药学', '药学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1002', '10', null, null, null, '生物医学工程', '生物医学工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1006', '10', null, null, null, '制药工程', '制药工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1007', '10', null, null, null, '生物工程', '生物工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1008', '10', null, null, null, '药学类', '药学类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1101', '11', null, null, null, '汉语国际教育（对外汉语）', '汉语国际教育（对外汉语）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1102', '11', null, null, null, '英语Z', '英语Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1103', '11', null, null, null, '英语', '英语', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1104', '11', null, null, null, '商务英语', '商务英语', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1201', '12', null, null, null, '理论与应用力学', '理论与应用力学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1202', '12', null, null, null, '机械设计制造及其自动化Z', '机械设计制造及其自动化Z', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1203', '12', null, null, null, '机械设计制造及其自动化', '机械设计制造及其自动化', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1204', '12', null, null, null, '工业工程', '工业工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1205', '12', null, null, null, '机械电子工程', '机械电子工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1206', '12', null, null, null, '机械类', '机械类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1501', '15', null, null, null, '应用化学', '应用化学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1502', '15', null, null, null, '过程装备与控制工程', '过程装备与控制工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1503', '15', null, null, null, '化学工程与工艺', '化学工程与工艺', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1504', '15', null, null, null, '化工类', '化工类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1601', '16', null, null, null, '电子信息科学与技术', '电子信息科学与技术', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1602', '16', null, null, null, '光电信息科学与工程', '光电信息科学与工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1603', '16', null, null, null, '应用物理学', '应用物理学', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1604', '16', null, null, null, '新能源科学与工程', '新能源科学与工程', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1605', '16', null, null, null, '电子信息类', '电子信息类', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1701', '17', null, null, null, '知识产权（3＋2）', '知识产权（3＋2）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1703', '17', null, null, null, '知识产权', '知识产权', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1704', '17', null, null, null, '社会工作', '社会工作', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1705', '17', null, null, null, '电子商务及法律', '电子商务及法律', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1937', '19', null, null, null, '计算机科学与技术[KAIST]', '计算机科学与技术[KAIST]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('1972', '19', null, null, null, '电子信息工程[KAIST]', '电子信息工程[KAIST]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2001', '20', null, null, null, '酒店管理（双文凭）', '酒店管理（双文凭）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2003', '20', null, null, null, '人力资源管理（商贸专双）', '人力资源管理（商贸专双）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2004', '20', null, null, null, '市场营销（商贸专）', '市场营销（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2007', '20', null, null, null, '会计电算化（商贸专）', '会计电算化（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2008', '20', null, null, null, '计算机应用技术（商贸专）', '计算机应用技术（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2011', '20', null, null, null, '电子商务（商贸专）', '电子商务（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2012', '20', null, null, null, '旅游管理（商贸专）', '旅游管理（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2013', '20', null, null, null, '工商企业管理（商贸专）', '工商企业管理（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2014', '20', null, null, null, '物流管理（商贸专）', '物流管理（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2015', '20', null, null, null, '物流管理（双文凭）', '物流管理（双文凭）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2016', '20', null, null, null, '计算机应用技术（双文凭）', '计算机应用技术（双文凭）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2017', '20', null, null, null, '财务管理（商贸专）', '财务管理（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2018', '20', null, null, null, '财务管理（双文凭）', '财务管理（双文凭）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2019', '20', null, null, null, '人力资源管理（商贸专）', '人力资源管理（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2020', '20', null, null, null, '商务英语（商贸专）', '商务英语（商贸专）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2101', '21', null, null, null, '金融学（金融事务与管理）', '金融学（金融事务与管理）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2102', '21', null, null, null, '会计学(会计电算化-专升本)', '会计学(会计电算化-专升本)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2103', '21', null, null, null, '材料成型及控制工程（模具设计与制造方向）', '材料成型及控制工程（模具设计与制造方向）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2104', '21', null, null, null, '电气工程及其自动化（电力拖动方向）', '电气工程及其自动化（电力拖动方向）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2105', '21', null, null, null, '车辆工程（汽车技术服务方向）', '车辆工程（汽车技术服务方向）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2106', '21', null, null, null, '电气工程及其自动化(电力系统及其自动化)', '电气工程及其自动化(电力系统及其自动化)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2107', '21', null, null, null, '会计学(会计电算化方向)(教改本)', '会计学(会计电算化方向)(教改本)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2110', '21', null, null, null, '金融学[应用本科]', '金融学[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2111', '21', null, null, null, '软件工程[应用本科]', '软件工程[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2112', '21', null, null, null, '机械设计制造及其自动化[应用本科]', '机械设计制造及其自动化[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2113', '21', null, null, null, '会计学-会计信息化[应用本科]', '会计学-会计信息化[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2114', '21', null, null, null, '电气工程及其自动化[应用本科]', '电气工程及其自动化[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2115', '21', null, null, null, '物流管理[应用本科]', '物流管理[应用本科]', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2139', '21', null, null, null, '会计学（会计电算化）', '会计学（会计电算化）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2140', '21', null, null, null, '旅游管理(酒店管理方向)', '旅游管理(酒店管理方向)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2141', '21', null, null, null, '机械制造及自动化(计算机辅助设计方向)', '机械制造及自动化(计算机辅助设计方向)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2142', '21', null, null, null, '机械设计制造及其自动化（数控方向）', '机械设计制造及其自动化（数控方向）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2145', '21', null, null, null, '软件工程（应用软件开发方向）', '软件工程（应用软件开发方向）', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2147', '21', null, null, null, '市场营销(销售管理方向)', '市场营销(销售管理方向)', null, null, null, null, null, null, null, null);
INSERT INTO `discipline` VALUES ('2148', '21', null, null, null, '物流管理(采购与供应管理方向)', '物流管理(采购与供应管理方向)', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for expert
-- ----------------------------
DROP TABLE IF EXISTS `expert`;
CREATE TABLE `expert` (
  `expertID` varchar(255) NOT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operatorID` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `addId` varchar(255) DEFAULT NULL,
  `addTime` varchar(255) DEFAULT NULL,
  `judgesState` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expertID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expert
-- ----------------------------

-- ----------------------------
-- Table structure for expertscore
-- ----------------------------
DROP TABLE IF EXISTS `expertscore`;
CREATE TABLE `expertscore` (
  `expertScoreID` varchar(255) NOT NULL,
  `expertTargetDistributeID` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `teamId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expertScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expertscore
-- ----------------------------

-- ----------------------------
-- Table structure for experttargetdistribute
-- ----------------------------
DROP TABLE IF EXISTS `experttargetdistribute`;
CREATE TABLE `experttargetdistribute` (
  `expertTargetDistributeID` varchar(255) NOT NULL,
  `expertID` varchar(255) DEFAULT NULL,
  `gameStepDetailID` varchar(255) DEFAULT NULL,
  `teamID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expertTargetDistributeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of experttargetdistribute
-- ----------------------------

-- ----------------------------
-- Table structure for followmanager
-- ----------------------------
DROP TABLE IF EXISTS `followmanager`;
CREATE TABLE `followmanager` (
  `followId` varchar(255) NOT NULL,
  `followTime` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`followId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of followmanager
-- ----------------------------

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `gameID` varchar(255) NOT NULL,
  `gameName` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `browseVolume` varchar(255) DEFAULT NULL,
  `checkRemark` varchar(255) DEFAULT NULL,
  `checkTime` varchar(255) DEFAULT NULL,
  `checkUserId` varchar(255) DEFAULT NULL,
  `competitionEndTime` varchar(255) DEFAULT NULL,
  `competitionImage` varchar(255) DEFAULT NULL,
  `competitionMovie` varchar(255) DEFAULT NULL,
  `competitionRemark` varchar(255) DEFAULT NULL,
  `competitionStartTime` varchar(255) DEFAULT NULL,
  `competitionType` varchar(255) DEFAULT NULL,
  `createPersonId` varchar(255) DEFAULT NULL,
  `enclosure` varchar(255) DEFAULT NULL,
  `gameType` varchar(255) DEFAULT NULL,
  `isMultiStage` varchar(255) DEFAULT NULL,
  `leastNumbe` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `maxNumber` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `propagandaPath` varchar(255) DEFAULT NULL,
  `sigeUpEndTime` varchar(255) DEFAULT NULL,
  `signUpStartTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gameID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('20170321234645901', '大家杯', '2017', '1490889600000', '1490025600000', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `game` VALUES ('20170404115541450', '测试大赛', null, null, null, '1', null, null, null, null, null, null, null, null, null, '0', null, null, 'GameType0001', '0', '1', '0', '3', null, 'uploadFile/2017/04/04/Tulips.jpg,uploadFile/2017/04/04/Jellyfish.jpg', '1493460652000', '1493136000000');
INSERT INTO `game` VALUES ('20170404124124007', '测试大赛', null, null, null, '0', null, null, null, null, null, null, null, null, null, '0', null, null, 'GameType0002', '0', '1', '0', '3', null, 'uploadFile/2017/04/04/Jellyfish.jpg', '1492531200000', '1492012800000');

-- ----------------------------
-- Table structure for gameresult
-- ----------------------------
DROP TABLE IF EXISTS `gameresult`;
CREATE TABLE `gameresult` (
  `code` varchar(255) NOT NULL,
  `gameStepDetailID` varchar(255) DEFAULT NULL,
  `teamId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gameresult
-- ----------------------------

-- ----------------------------
-- Table structure for gamestep
-- ----------------------------
DROP TABLE IF EXISTS `gamestep`;
CREATE TABLE `gamestep` (
  `gameStepID` varchar(255) NOT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `gameID` varchar(255) DEFAULT NULL,
  `gameStep` varchar(255) DEFAULT NULL,
  `LIMITS` int(11) DEFAULT NULL,
  `prizeID` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `gameStepName` varchar(255) DEFAULT NULL,
  `leastNumber` varchar(255) DEFAULT NULL,
  `maxNumber` varchar(255) DEFAULT NULL,
  `siGetPro` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gameStepID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gamestep
-- ----------------------------
INSERT INTO `gamestep` VALUES ('20170321234656996', '1490716800000', '20170321234645901', 'gameStep0001', '0', null, '1490025600000', null, '初赛', null, null, null, null);

-- ----------------------------
-- Table structure for gamestepdetail
-- ----------------------------
DROP TABLE IF EXISTS `gamestepdetail`;
CREATE TABLE `gamestepdetail` (
  `gameStepDetailID` varchar(255) NOT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `gameStepID` varchar(255) DEFAULT NULL,
  `isPublish` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `checkEndTime` varchar(255) DEFAULT NULL,
  `checkStartTime` varchar(255) DEFAULT NULL,
  `standardVersionID` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `fileID` varchar(255) DEFAULT NULL,
  `gameTime` varchar(255) DEFAULT NULL,
  `parentID` varchar(255) DEFAULT NULL,
  `processID` varchar(255) DEFAULT NULL,
  `works` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gameStepDetailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gamestepdetail
-- ----------------------------
INSERT INTO `gamestepdetail` VALUES ('20170321234707899', '1490716800000', '20170321234656996', null, null, 'task0001', '1490716800000', '1490025600000', '20170405222007888', '1490025600000', '0', null, '1490025600000', '20170321234656996', 'task0002', '1');

-- ----------------------------
-- Table structure for hostunit
-- ----------------------------
DROP TABLE IF EXISTS `hostunit`;
CREATE TABLE `hostunit` (
  `hostUnitId` varchar(255) CHARACTER SET utf8 NOT NULL,
  `gameId` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hostUnitContact` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hostUnitContactMail` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hostUnitContactPerson` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hostUnitName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`hostUnitId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hostunit
-- ----------------------------
INSERT INTO `hostunit` VALUES ('20170404115309006', '20170404115244339', null, null, null, '0', '11');
INSERT INTO `hostunit` VALUES ('20170404115311813', '20170404115244339', null, null, null, '0', '22');
INSERT INTO `hostunit` VALUES ('20170404115319917', '20170404115244339', null, null, null, '1', '33');
INSERT INTO `hostunit` VALUES ('20170404115321998', '20170404115244339', null, null, null, '1', '44');
INSERT INTO `hostunit` VALUES ('20170404124124391', '20170404124124007', null, null, null, '0', '222');
INSERT INTO `hostunit` VALUES ('20170404124124486', '20170404124124007', null, null, null, '0', '333');
INSERT INTO `hostunit` VALUES ('20170404124124587', '20170404124124007', null, null, null, '1', '444');
INSERT INTO `hostunit` VALUES ('20170404124124640', '20170404124124007', null, null, null, '1', '5555');
INSERT INTO `hostunit` VALUES ('20170404181101933', '20170404115541450', null, null, null, '0', '44');
INSERT INTO `hostunit` VALUES ('20170404181102502', '20170404115541450', null, null, null, '0', '55');
INSERT INTO `hostunit` VALUES ('20170404181102759', '20170404115541450', null, null, null, '1', '66');
INSERT INTO `hostunit` VALUES ('20170404181102839', '20170404115541450', null, null, null, '1', '77');
INSERT INTO `hostunit` VALUES ('223323', '20170321234645901', '测试', 'test@qq.com', '张三', '0', '测试大赛主办方');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageID` varchar(255) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `messageType` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for messagenotification
-- ----------------------------
DROP TABLE IF EXISTS `messagenotification`;
CREATE TABLE `messagenotification` (
  `messageId` varchar(255) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `messageTime` varchar(255) DEFAULT NULL,
  `messageType` varchar(255) DEFAULT NULL,
  `noticeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of messagenotification
-- ----------------------------

-- ----------------------------
-- Table structure for messagereceive
-- ----------------------------
DROP TABLE IF EXISTS `messagereceive`;
CREATE TABLE `messagereceive` (
  `messageReceiveID` varchar(255) NOT NULL,
  `isRead` int(11) DEFAULT NULL,
  `messageID` varchar(255) DEFAULT NULL,
  `operatorID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageReceiveID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messagereceive
-- ----------------------------

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `modulecode` varchar(255) NOT NULL,
  `hasChild` bit(1) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `isEndOfModuleLevel` bit(1) DEFAULT NULL,
  `isOpened` bit(1) DEFAULT NULL,
  `level0` int(11) DEFAULT NULL,
  `moduleContent` varchar(255) DEFAULT NULL,
  `moduleType` varchar(255) DEFAULT NULL,
  `modulename` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  `showOrder` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`modulecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('0001', '', '', null, '\0', '0', '', 'ModuleType0001', '系统管理员', '', '0', ' module/role/roleManager.jsp');
INSERT INTO `module` VALUES ('00010001', '', '', null, '\0', '1', '', 'ModuleType0001', '系统设置', '0001', '5', '');
INSERT INTO `module` VALUES ('000100010001', '\0', '', null, '\0', '2', '', 'ModuleType0001', '模块管理', '00010001', '0', 'module/module/moduleManager.jsp');
INSERT INTO `module` VALUES ('000100010002', '\0', '', null, '\0', '2', '', 'ModuleType0001', '角色管理', '00010001', '1', 'module/roleManager/roleManager.jsp');
INSERT INTO `module` VALUES ('000100010003', '\0', '', null, '\0', '2', '', 'ModuleType0001', '操作员管理', '00010001', '2', 'module/operatorManager/operatorManager.jsp');
INSERT INTO `module` VALUES ('000100010005', '\0', '', null, '\0', '2', '', 'ModuleType0001', '权限管理', '00010001', '4', 'module/roleManager/rolePermissionAssign.jsp');
INSERT INTO `module` VALUES ('000100010010', '\0', '', null, '\0', '2', '', 'ModuleType0001', '码表管理', '00010001', '0', 'module/codetable/codetableManager.jsp');
INSERT INTO `module` VALUES ('000100010011', '\0', '', null, '\0', '2', '', 'ModuleType0001', '角色分配', '00010001', '1', 'module/roleManager/roleAssign.jsp');
INSERT INTO `module` VALUES ('00010002', '', '', null, '\0', '1', '', 'ModuleType0001', '评审任务设置', '0001', '2', 'moule');
INSERT INTO `module` VALUES ('000100020001', '\0', '', null, '\0', '2', '', 'ModuleType0001', '评审管理', '00010002', '0', 'module/reviewManagement/reviewManagement.jsp');
INSERT INTO `module` VALUES ('000100020006', '\0', '', null, '\0', '2', '', 'ModuleType0001', '指定评委', '00010002', '5', 'module/arrangeExpertManager/arrangeExpertManager.jsp');
INSERT INTO `module` VALUES ('00010003', '', null, null, '\0', '1', null, 'ModuleType0001', '评审标准设置', '0001', '1', '评审标准设置');
INSERT INTO `module` VALUES ('000100030001', '\0', null, null, '\0', '2', null, 'ModuleType0001', '评审指标设置', '00010003', '0', 'module/targetManager/targetManager.jsp');
INSERT INTO `module` VALUES ('000100030002', '\0', null, null, '\0', '2', null, 'ModuleType0001', '评审标准管理', '00010003', null, 'module/targetSystemManager/targetSystemManager.jsp');
INSERT INTO `module` VALUES ('00010004', '', '', null, '\0', '1', '', 'ModuleType0001', '基础数据设置', '0001', '0', '1');
INSERT INTO `module` VALUES ('000100040001', '\0', '', null, '\0', '2', '', 'ModuleType0001', '专业管理', '00010004', '2', 'module/discipline/disciplineManager.jsp');
INSERT INTO `module` VALUES ('000100040002', '\0', '', null, '\0', '2', '', 'ModuleType0001', '部门管理', '00010004', '1', 'module/academic/academicManager.jsp');
INSERT INTO `module` VALUES ('000100040005', '\0', '', null, '\0', '2', '', 'ModuleType0001', '评委管理', '00010004', '3', 'module/expertManager/expertManager.jsp');
INSERT INTO `module` VALUES ('00010005', '', '', null, '\0', '1', '', 'ModuleType0001', '大赛设置', '0001', '3', '2');
INSERT INTO `module` VALUES ('000100050001', '\0', '', null, '\0', '2', '', 'ModuleType0001', '竞赛管理', '00010005', '0', 'module/gameManager/gameManager.jsp');
INSERT INTO `module` VALUES ('000100050002', '\0', '', null, '\0', '2', '', 'ModuleType0001', '竞赛阶段管理', '00010005', '1', 'module/gameStepManager/gameStepManager.jsp');
INSERT INTO `module` VALUES ('00010006', '', null, null, '\0', '1', null, 'ModuleType0001', '网站管理', '0001', '1', '');
INSERT INTO `module` VALUES ('000100060001', '\0', null, null, '\0', '2', null, 'ModuleType0001', '报名管理', '00010006', '1', 'module/signUpManager/signUpManager.jsp');
INSERT INTO `module` VALUES ('000100060002', '\0', null, null, '\0', '2', null, 'ModuleType0001', '文档管理', '00010006', '2', 'module/fileManager/fileManager.jsp');
INSERT INTO `module` VALUES ('0002', '', '', null, '\0', '0', '', 'ModuleType0001', '评委', '', '1', '评委');
INSERT INTO `module` VALUES ('00020002', '', '', null, '\0', '1', '', 'ModuleType0001', '评委评审', '0002', '1', ' s');
INSERT INTO `module` VALUES ('000200020001', '\0', '', null, '\0', '2', '', 'ModuleType0001', '评委评分', '00020002', '0', 'module/exprtScore/expertScoreManager.jsp');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeId` varchar(255) NOT NULL,
  `fileID` varchar(255) DEFAULT NULL,
  `noticeDetaile` varchar(255) DEFAULT NULL,
  `noticeTitle` varchar(255) DEFAULT NULL,
  `noticeType` varchar(255) DEFAULT NULL,
  `pageView` varchar(255) DEFAULT NULL,
  `publishId` varchar(255) DEFAULT NULL,
  `publishName` varchar(255) DEFAULT NULL,
  `publishTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `operatorID` varchar(255) NOT NULL,
  `ZGH` varchar(255) DEFAULT NULL,
  `academicID` varchar(255) DEFAULT NULL,
  `disabled` int(11) DEFAULT NULL,
  `disciplineID` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `systemFileID` varchar(255) DEFAULT NULL,
  `operatorKind` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operatorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('111', '20160415', null, '1', null, '系统管理员', 'E10ADC3949BA59ABBE56E057F20F883E', '20160410210347107', null);

-- ----------------------------
-- Table structure for permissionassign
-- ----------------------------
DROP TABLE IF EXISTS `permissionassign`;
CREATE TABLE `permissionassign` (
  `permissionAssignID` varchar(255) NOT NULL,
  `moduleID` varchar(255) DEFAULT NULL,
  `roleID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permissionAssignID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissionassign
-- ----------------------------
INSERT INTO `permissionassign` VALUES ('20151207153748816', '000100040001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748817', '00010002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748818', '000100040002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748819', '000100010005', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748820', '000100020001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748821', '00010003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748822', '000100010001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748823', '000100020002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748824', '000100040003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748825', '000100040005', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748826', '00010006', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748827', '000100050003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748828', '000100050001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748829', '000100050004', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748830', '000100020005', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748831', '000100020007', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748832', '000100030002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748833', '0001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748834', '000100010011', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748835', '00010004', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748836', '000100020008', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748837', '000100020006', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748838', '000100010014', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748839', '000100010010', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748840', '000100020003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748841', '000100040004', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748842', '000100020004', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748843', '000100010002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748844', '000100010003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748845', '00010005', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748846', '000100050002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748847', '000100060001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748848', '000100030001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748849', '000100030003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153748850', '00010001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153752371', '00070002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153752372', '00070001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153752373', '0007', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151207153752374', '00070003', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151228155530912', '00070004', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151228155530913', '0007', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151228155753845', '000100020009', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151228155753846', '00010002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151228155753848', '0001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151229171727000', '000100010015', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151229171727001', '00010001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20151229171727002', '0001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20160409105404973', '0006', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20160409105404974', '00060001', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20160413031718467', '0008', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20160413142734697', '0001', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734698', '00010001', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734699', '000100010001', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734700', '000100010002', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734701', '000100010003', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734702', '000100010005', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734703', '000100010010', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734704', '000100010011', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734705', '00010002', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734706', '000100020001', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734707', '000100020002', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734708', '00010004', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734709', '000100040001', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734710', '000100040002', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160413142734711', '000100040005', '20160413033231097');
INSERT INTO `permissionassign` VALUES ('20160506191905892', '00020002', '20160506154705281');
INSERT INTO `permissionassign` VALUES ('20160506191905894', '000200020001', '20160506154705281');
INSERT INTO `permissionassign` VALUES ('20160506191905895', '0002', '20160506154705281');
INSERT INTO `permissionassign` VALUES ('20161004212426129', '000100060002', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20161004212426140', '00010006', '20150515215311141');
INSERT INTO `permissionassign` VALUES ('20161004212426141', '0001', '20150515215311141');

-- ----------------------------
-- Table structure for problemmanager
-- ----------------------------
DROP TABLE IF EXISTS `problemmanager`;
CREATE TABLE `problemmanager` (
  `problemId` varchar(255) NOT NULL,
  `problemDetail` varchar(255) DEFAULT NULL,
  `programme` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`problemId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of problemmanager
-- ----------------------------

-- ----------------------------
-- Table structure for processresultdetail
-- ----------------------------
DROP TABLE IF EXISTS `processresultdetail`;
CREATE TABLE `processresultdetail` (
  `processResultDetailID` varchar(255) NOT NULL,
  `fileId` varchar(255) DEFAULT NULL,
  `fileReviewTime` varchar(255) DEFAULT NULL,
  `gameStepDetailID` varchar(255) DEFAULT NULL,
  `signUpId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`processResultDetailID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of processresultdetail
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectID` varchar(255) NOT NULL,
  `fileId` varchar(255) DEFAULT NULL,
  `teamId` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('20170321234825760', '20170321234823697', '20170321234810416', 'task0001');

-- ----------------------------
-- Table structure for raterappointmanager
-- ----------------------------
DROP TABLE IF EXISTS `raterappointmanager`;
CREATE TABLE `raterappointmanager` (
  `raterAppointId` varchar(255) NOT NULL,
  `judgesIds` varchar(255) DEFAULT NULL,
  `processResultDetailID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`raterAppointId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of raterappointmanager
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleID` varchar(255) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('20150515215311141', '系统管理员');

-- ----------------------------
-- Table structure for roleassign
-- ----------------------------
DROP TABLE IF EXISTS `roleassign`;
CREATE TABLE `roleassign` (
  `roleAssignID` varchar(255) NOT NULL,
  `operatorID` varchar(255) DEFAULT NULL,
  `roleID` varchar(255) DEFAULT NULL,
  `taskID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleAssignID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleassign
-- ----------------------------
INSERT INTO `roleassign` VALUES ('1', '1112', '20150515215311141', null);
INSERT INTO `roleassign` VALUES ('20151207153459919', '111', '20150515215311141', null);
INSERT INTO `roleassign` VALUES ('20160506154705281', '20160517204635845', null, null);
INSERT INTO `roleassign` VALUES ('20160506160448643', '20160506154549120', '20160506154705281', null);
INSERT INTO `roleassign` VALUES ('20160512171749998', '20160512123854112', '20160506154705281', null);
INSERT INTO `roleassign` VALUES ('20160517204733933', '20160517204725832', '20160506154705281', null);

-- ----------------------------
-- Table structure for standardversion
-- ----------------------------
DROP TABLE IF EXISTS `standardversion`;
CREATE TABLE `standardversion` (
  `standardVersionID` varchar(255) NOT NULL,
  `gameStep` varchar(255) DEFAULT NULL,
  `standardVersionName` varchar(255) DEFAULT NULL,
  `citeState` varchar(255) DEFAULT NULL,
  `createrId` varchar(255) DEFAULT NULL,
  `createrTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`standardVersionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standardversion
-- ----------------------------
INSERT INTO `standardversion` VALUES ('20170405222007888', null, '测试版本', '0', '111', '1491402007888');
INSERT INTO `standardversion` VALUES ('20170405222731311', null, '测试版本2', '0', '111', '1491402451311');

-- ----------------------------
-- Table structure for systemfile
-- ----------------------------
DROP TABLE IF EXISTS `systemfile`;
CREATE TABLE `systemfile` (
  `ID` varchar(255) NOT NULL,
  `alterTime` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `fileSize` bigint(20) DEFAULT NULL,
  `fileType` varchar(255) DEFAULT NULL,
  `newTime` datetime DEFAULT NULL,
  `savePath` varchar(255) DEFAULT NULL,
  `whoAlter` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemfile
-- ----------------------------
INSERT INTO `systemfile` VALUES ('20170321234823697', null, null, '王思凡.zip', '75842961', 'application/octet-stream', '2017-03-21 23:48:23', 'E:/uploadFile2017/03/21/20170321234822568', null);
INSERT INTO `systemfile` VALUES ('20170403215521173', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403215516143', null);
INSERT INTO `systemfile` VALUES ('20170403220312634', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403220312631', null);
INSERT INTO `systemfile` VALUES ('20170403220358501', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403220358456', null);
INSERT INTO `systemfile` VALUES ('20170403222518375', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403222518372', null);
INSERT INTO `systemfile` VALUES ('20170403222637763', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403222637761', null);
INSERT INTO `systemfile` VALUES ('20170403223104527', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403223104502', null);
INSERT INTO `systemfile` VALUES ('20170403223649420', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403223649418', null);
INSERT INTO `systemfile` VALUES ('20170403224759075', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/20170403224744109', null);
INSERT INTO `systemfile` VALUES ('20170403224916236', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'E:/uploadFile2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403225009824', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403225028873', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403225407732', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'E:/uploadFile2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403225615752', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403230159788', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403230906205', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, '/D:/eclipseWorkSpace/.metadata/.me_tcat7/webapps/CQUTGmsoftCup/WEB-INF/classes/2017/04/03/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403231518228', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, '/D:/eclipseWorkSpace/.metadata/.me_tcat7/webapps/CQUTGmsoftCup/2017/04/03/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403231844394', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403231917305', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232033654', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232051505', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232054571', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232513954', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232901541', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232904398', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232914696', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/03/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403232917771', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/03/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233016928', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233023842', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/03/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233026231', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/03/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233241070', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233503332', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403233757408', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403234306865', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403234426032', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403234820519', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/03/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403234856080', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403235024548', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403235145108', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170403235413565', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/03/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000048003', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000556417', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000610093', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000802113', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000806499', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000811757', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404000814693', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001422022', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001425453', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001553753', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001556263', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001600972', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001623038', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001659757', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001705182', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404001914272', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404002153081', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404002202575', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404002206346', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404002209744', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404002222719', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003525921', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003530076', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003538402', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003659566', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003741812', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003745155', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404003752249', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404004409962', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404004553122', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404004555875', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005249352', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005251444', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005254349', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005302990', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005557163', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005603381', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005713607', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005715532', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005718605', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005829828', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404005834385', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010103487', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010109081', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010113901', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010417509', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010512765', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010743694', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010747788', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010749751', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010752355', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404010758512', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404011656387', null, null, 'Koala.jpg', '780831', 'image/jpeg', null, 'uploadFile/2017/04/04/Koala.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404011902773', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404011904934', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404012002866', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404012143978', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404013505230', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, '/uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404013622687', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404013637912', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094321343', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094355412', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, '/uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094637920', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094754530', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094915410', null, null, 'Koala.jpg', '780831', 'image/jpeg', null, '/uploadFile/2017/04/04/Koala.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404094929489', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404095153642', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404095452822', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404095633152', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404095725708', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404095741379', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, '/uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100151346', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100207395', null, null, 'Lighthouse.jpg', '561276', 'image/jpeg', null, '/uploadFile/2017/04/04/Lighthouse.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100506061', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, '/uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100527925', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100710500', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404100748622', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, '/uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101030375', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, '/uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101217547', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101220887', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101223536', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101226531', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101233367', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101235597', null, null, 'Koala.jpg', '780831', 'image/jpeg', null, 'uploadFile/2017/04/04/Koala.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101305828', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101307639', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101309888', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101314671', null, null, 'Koala.jpg', '780831', 'image/jpeg', null, 'uploadFile/2017/04/04/Koala.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101317520', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101320735', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101331949', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101338857', null, null, 'Lighthouse.jpg', '561276', 'image/jpeg', null, 'uploadFile/2017/04/04/Lighthouse.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101558317', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101600756', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101602945', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101605886', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101612393', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404101615336', null, null, 'Chrysanthemum.jpg', '879394', 'image/jpeg', null, 'uploadFile/2017/04/04/Chrysanthemum.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404112954083', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404112956348', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404112957966', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404113114453', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404113116690', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404113411204', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404113757336', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404113908893', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404114155665', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404115229949', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404115232514', null, null, 'Desert.jpg', '845941', 'image/jpeg', null, 'uploadFile/2017/04/04/Desert.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404115532752', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404124122008', null, null, 'Jellyfish.jpg', '775702', 'image/jpeg', null, 'uploadFile/2017/04/04/Jellyfish.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404172012123', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404173223190', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404173742545', null, null, 'Hydrangeas.jpg', '595284', 'image/jpeg', null, 'uploadFile/2017/04/04/Hydrangeas.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404175634077', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404175734910', null, null, 'Penguins.jpg', '777835', 'image/jpeg', null, 'uploadFile/2017/04/04/Penguins.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404175914483', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);
INSERT INTO `systemfile` VALUES ('20170404180152273', null, null, 'Tulips.jpg', '620888', 'image/jpeg', null, 'uploadFile/2017/04/04/Tulips.jpg', null);

-- ----------------------------
-- Table structure for target
-- ----------------------------
DROP TABLE IF EXISTS `target`;
CREATE TABLE `target` (
  `targetCode` varchar(255) NOT NULL,
  `isLastTarget` int(11) DEFAULT NULL,
  `showOrder` int(11) DEFAULT NULL,
  `standardVersionID` varchar(255) DEFAULT NULL,
  `targetExplain` varchar(255) DEFAULT NULL,
  `targetLevel` int(11) DEFAULT NULL,
  `targetName` varchar(255) DEFAULT NULL,
  `targetParentCode` varchar(255) DEFAULT NULL,
  `targetScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`targetCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of target
-- ----------------------------

-- ----------------------------
-- Table structure for targetscore
-- ----------------------------
DROP TABLE IF EXISTS `targetscore`;
CREATE TABLE `targetscore` (
  `targetScoreID` varchar(255) NOT NULL,
  `expertTargetDistributeID` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `targetCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`targetScoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of targetscore
-- ----------------------------

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `teamID` varchar(255) NOT NULL,
  `captianId` varchar(255) DEFAULT NULL,
  `checkState` int(11) DEFAULT NULL,
  `gameStepID` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `teamName` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `gameStyle` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('20161004214428424', '11303080129', '1', '20161004213946697', null, '路飞', null, '路飞', '2016100786', '0', null);
INSERT INTO `team` VALUES ('20170321233113203', '111', '1', '20170321232941449', null, '路飞', null, '你是', '2017030028', '0', null);
INSERT INTO `team` VALUES ('20170321234810416', '11303080129', '1', '20170321234656996', null, 'lufei', null, 'lufei', '2017030035', '0', null);
INSERT INTO `team` VALUES ('20170322002341297', '1', '1', '20170321234656996', null, '111', null, '111', '2017030042', '0', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` varchar(255) NOT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `academy` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `roleID` varchar(255) DEFAULT NULL,
  `units` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456', '01', 'education0001', '714225540@qq.com', '0101', '1', '1BBD886460827015E5D605ED44252251', '18875055448', '0', null, null, null);
INSERT INTO `user` VALUES ('11303080129', '714225540', '01', 'education0001', '714225540@qq.com', '0101', '123', '25D55AD283AA400AF464C76D713C07AD', '18875055448', '0', null, null, null);
