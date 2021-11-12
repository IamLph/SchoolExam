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

 Date: 12/11/2021 16:45:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userquestion
-- ----------------------------
DROP TABLE IF EXISTS `userquestion`;
CREATE TABLE `userquestion`  (
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `examId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试题id',
  `userFavor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户是否收藏',
  `isWrong` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是错题'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userquestion
-- ----------------------------
INSERT INTO `userquestion` VALUES ('181530324', '11002', 'false', 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11001', 'false', 'false');
INSERT INTO `userquestion` VALUES ('181530324', '11003', 'true', 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11005', 'true', 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11007', 'false', 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11008', 'false', 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11004', NULL, 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11009', NULL, 'true');
INSERT INTO `userquestion` VALUES ('181530324', '11006', NULL, 'true');
INSERT INTO `userquestion` VALUES ('181530324', '9997387', NULL, 'true');
INSERT INTO `userquestion` VALUES ('1815302222', '11002', NULL, 'true');
INSERT INTO `userquestion` VALUES ('1815302222', '11003', NULL, 'true');
INSERT INTO `userquestion` VALUES ('1815302222', '11004', NULL, 'true');
INSERT INTO `userquestion` VALUES ('1815302222', '11008', NULL, 'true');

SET FOREIGN_KEY_CHECKS = 1;
