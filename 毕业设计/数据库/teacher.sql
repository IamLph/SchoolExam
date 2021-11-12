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

 Date: 12/11/2021 16:45:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tt_id` int NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `teacherId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师账号',
  `teacherName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `teacherPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师密码',
  `teacherHeadImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师头像',
  PRIMARY KEY (`tt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '119563', '张三', 'b7ff829f6415df03e72bed021f01132e', NULL);
INSERT INTO `teacher` VALUES (2, '18101', '张大伟', '256dc8215ffa5bad57876de77e02f9af', NULL);
INSERT INTO `teacher` VALUES (3, '18102', '张益达', '123456', NULL);
INSERT INTO `teacher` VALUES (4, '18103', '张大炮', '123456', NULL);
INSERT INTO `teacher` VALUES (5, '18104', '斯内克', '123456', NULL);
INSERT INTO `teacher` VALUES (7, '3532513751', '张大伟', '256dc8215ffa5bad57876de77e02f9af', NULL);
INSERT INTO `teacher` VALUES (9, '8813133929', '张伟', '256dc8215ffa5bad57876de77e02f9af', NULL);
INSERT INTO `teacher` VALUES (11, '766354', '张撒娇的', '3636f85d8ff6de44fa1d9d832c647555', NULL);
INSERT INTO `teacher` VALUES (12, '771595', '123', '3636f85d8ff6de44fa1d9d832c647555', NULL);
INSERT INTO `teacher` VALUES (15, '755941', '张三', '256dc8215ffa5bad57876de77e02f9af', NULL);

SET FOREIGN_KEY_CHECKS = 1;
