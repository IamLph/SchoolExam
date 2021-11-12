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

 Date: 12/11/2021 16:45:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for videoinfo
-- ----------------------------
DROP TABLE IF EXISTS `videoinfo`;
CREATE TABLE `videoinfo`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '视频ID',
  `classId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程ID',
  `videoUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `videoName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名字',
  `videoInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频信息',
  `videoUnit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频单元',
  `publishTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频发布时间',
  `videoImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频图片',
  `initTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '初始化时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videoinfo
-- ----------------------------
INSERT INTO `videoinfo` VALUES ('100001', '1001', 'http://img.baiding.top/9c885252c88f445d97559a25090ef1a7.mp4', '第一课时', '和嘿嘿嘿', '1.101', '2021/10/23', 'http://img.baiding.top/avatar.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100002', '1001', 'http://img.baiding.top/1634988219763mp5.mp4', '第二课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.2', '2021/10/23', 'http://img.baiding.top/1634988219763img.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100003', '1002', 'http://cdn.baiding.top/45145679', '第三课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.3', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100004', '1003', 'http://cdn.baiding.top/45145679', '第四课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.4', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100005', '1004', 'http://cdn.baiding.top/45145679', '第五课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.5', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100006', '1005', 'http://cdn.baiding.top/45145679', '第六课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.6', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100007', '1006', 'http://cdn.baiding.top/45145679', '第七课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.7', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100008', '1007', 'http://cdn.baiding.top/45145679', '第八课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.8', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100009', '1008', 'http://cdn.baiding.top/45145679', '第九课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.9', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('100010', '1008', 'http://cdn.baiding.top/45145679', '第十课时', '这是一个好好看的视频,超级好看，学习新思想，争做新青年，好好学习，天天向上', '1.10', '2020/12/9', '../../static/image/icon/default_dangClassImg_D.jpg', '0');
INSERT INTO `videoinfo` VALUES ('512115', '1001', 'http://img.baiding.top/1634989504295mp5.mp4', '第四课时', '暗示多级', '1.4', '2021/10/23', 'http://img.baiding.top/1634989504295img.jpg', '0');

SET FOREIGN_KEY_CHECKS = 1;
