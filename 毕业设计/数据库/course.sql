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

 Date: 12/11/2021 16:45:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `classId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `classImgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名字',
  `classTeacherName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程教师ID',
  `site` int NULL DEFAULT 0 COMMENT '课程位置',
  PRIMARY KEY (`classId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1001', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学体育', '理想', 0);
INSERT INTO `course` VALUES ('1002', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学英语', '李卓', 0);
INSERT INTO `course` VALUES ('1003', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学数学', '李卓', 0);
INSERT INTO `course` VALUES ('1004', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学美术', '李卓', 0);
INSERT INTO `course` VALUES ('1005', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学团课', '李卓', 0);
INSERT INTO `course` VALUES ('1006', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学科学', '李卓', 0);
INSERT INTO `course` VALUES ('1007', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学艺术', '李卓', 0);
INSERT INTO `course` VALUES ('1008', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学政治', '李卓', 0);
INSERT INTO `course` VALUES ('1009', 'https://img1.baidu.com/it/u=1894041144,114306704&fm=26&fmt=auto', '大学Java', '李卓', 0);
INSERT INTO `course` VALUES ('33892', 'http://img.baiding.top/1634968259965img.jpg', '航海学', '杰克', 0);

SET FOREIGN_KEY_CHECKS = 1;
