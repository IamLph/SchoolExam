/*
 Navicat Premium Data Transfer

 Source Server         : PDSU
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : exam_test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/11/2021 16:45:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exampaper
-- ----------------------------
DROP TABLE IF EXISTS `exampaper`;
CREATE TABLE `exampaper`  (
  `noticeId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试编号',
  `questionId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试题编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exampaper
-- ----------------------------
INSERT INTO `exampaper` VALUES ('1000001', '11001');
INSERT INTO `exampaper` VALUES ('1000001', '11002');
INSERT INTO `exampaper` VALUES ('1000001', '11003');
INSERT INTO `exampaper` VALUES ('1000001', '11004');
INSERT INTO `exampaper` VALUES ('1000001', '11005');
INSERT INTO `exampaper` VALUES ('1000001', '11006');
INSERT INTO `exampaper` VALUES ('1000001', '11007');
INSERT INTO `exampaper` VALUES ('1000001', '11008');
INSERT INTO `exampaper` VALUES ('1000001', '11009');
INSERT INTO `exampaper` VALUES ('78548515', '11008');
INSERT INTO `exampaper` VALUES ('45986841', '11003');
INSERT INTO `exampaper` VALUES ('45986841', '11008');
INSERT INTO `exampaper` VALUES ('32196267', '11003');
INSERT INTO `exampaper` VALUES ('32196267', '11002');
INSERT INTO `exampaper` VALUES ('32196267', '11004');
INSERT INTO `exampaper` VALUES ('32196267', '11001');
INSERT INTO `exampaper` VALUES ('38921683', '11003');
INSERT INTO `exampaper` VALUES ('38921683', '11008');
INSERT INTO `exampaper` VALUES ('17826629', '11003');
INSERT INTO `exampaper` VALUES ('17826629', '11008');
INSERT INTO `exampaper` VALUES ('17826629', '11002');
INSERT INTO `exampaper` VALUES ('17826629', '11004');
INSERT INTO `exampaper` VALUES ('83583573', '11001');
INSERT INTO `exampaper` VALUES ('83583573', '11007');
INSERT INTO `exampaper` VALUES ('83583573', '11002');
INSERT INTO `exampaper` VALUES ('83583573', '11004');
INSERT INTO `exampaper` VALUES ('83583573', '11003');
INSERT INTO `exampaper` VALUES ('83583573', '11008');
INSERT INTO `exampaper` VALUES ('66682435', '11002');
INSERT INTO `exampaper` VALUES ('66682435', '11004');
INSERT INTO `exampaper` VALUES ('66682435', '11003');
INSERT INTO `exampaper` VALUES ('66682435', '11001');
INSERT INTO `exampaper` VALUES ('66682435', '11007');
INSERT INTO `exampaper` VALUES ('36348189', '11004');
INSERT INTO `exampaper` VALUES ('36348189', '11002');
INSERT INTO `exampaper` VALUES ('36348189', '11001');
INSERT INTO `exampaper` VALUES ('36348189', '11003');

SET FOREIGN_KEY_CHECKS = 1;
