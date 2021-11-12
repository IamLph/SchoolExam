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

 Date: 12/11/2021 16:45:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `noticeId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试ID,通知ID',
  `courseId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程Id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试类型',
  `startTime` datetime(0) NULL DEFAULT NULL COMMENT '详细开始时间',
  `endTime` datetime(0) NULL DEFAULT NULL COMMENT '详细结束时间',
  `teacherId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监考老师，评卷老师',
  `isPublish` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否发布，0还未发布，1已经发布，2已经结束,3已经批改完成',
  PRIMARY KEY (`noticeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1000002', '1002', '软件测试', '2021-12-30 10:00:00', '2021-12-30 11:50:00', '18102', '0');
INSERT INTO `notice` VALUES ('1000003', '1003', '物理考试', '2021-12-31 08:00:00', '2021-12-31 09:50:00', '18103', '0');
INSERT INTO `notice` VALUES ('1000004', '1004', '地理考试', '2021-12-28 14:00:00', '2021-12-29 15:50:00', '18104', '0');
INSERT INTO `notice` VALUES ('17826629', '1005', '开题报告', '2021-09-29 12:09:56', '2021-10-30 00:00:00', '18102', '2');
INSERT INTO `notice` VALUES ('36348189', '1001', '我去', '2021-10-27 10:16:31', '2021-10-29 00:00:00', '119563', '2');
INSERT INTO `notice` VALUES ('38921683', '1007', '开题报告', '2021-09-29 12:02:14', '2022-10-12 00:00:00', '18101', '1');
INSERT INTO `notice` VALUES ('66682435', '1001', '体测来一下', '2021-10-22 00:00:00', '2021-10-24 00:00:00', '18103', '3');
INSERT INTO `notice` VALUES ('83583573', '1001', '测试一下', '2021-10-22 12:31:19', '2021-10-23 00:00:00', '18101', '2');

SET FOREIGN_KEY_CHECKS = 1;
