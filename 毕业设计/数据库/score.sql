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

 Date: 12/11/2021 16:45:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号随机',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `teacherId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '改卷教师编号',
  `noticeId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试编号',
  `score` int NULL DEFAULT NULL COMMENT '分数',
  `live` int NULL DEFAULT NULL COMMENT '离开次数',
  `finalscore` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终得分',
  `publish` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '成绩发布0未发布1发布',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, '181530324', '18101', '1000001', 22, 3, '33', '0');
INSERT INTO `score` VALUES (2, '181530324', '18103', '66682435', 20, 0, '13', '1');
INSERT INTO `score` VALUES (6, '181530333', '18101', '1000001', 22, 1, '15', '0');
INSERT INTO `score` VALUES (7, '181530324', '18102', '17826629', 33, 0, NULL, '0');
INSERT INTO `score` VALUES (8, '181530324', '119563', '36348189', 50, 0, NULL, '0');
INSERT INTO `score` VALUES (9, '1815302222', '18102', '17826629', 44, 0, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
