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

 Date: 12/11/2021 16:45:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uservideo
-- ----------------------------
DROP TABLE IF EXISTS `uservideo`;
CREATE TABLE `uservideo`  (
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `videoId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '视频id',
  `initTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频开始时间',
  `remainTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剩余观看时间',
  `videoIsOk` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否看完'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uservideo
-- ----------------------------
INSERT INTO `uservideo` VALUES ('181530324', '100001', '221', '8', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100002', NULL, '10', 'true');
INSERT INTO `uservideo` VALUES ('181530324', '100003', NULL, '10', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100004', NULL, '10', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100005', NULL, '11', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100006', NULL, '12', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100007', NULL, '12', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100009', NULL, '13', 'false');
INSERT INTO `uservideo` VALUES ('181530324', '100010', NULL, '14', 'true');

SET FOREIGN_KEY_CHECKS = 1;
