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

 Date: 12/11/2021 16:45:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `userId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登陆账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `headImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  `nation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `nationPlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院校',
  `grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `branch` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门分支',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = REDUNDANT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'LPH66', '181530324', '256dc8215ffa5bad57876de77e02f9af', 'http://img.baiding.top/1635061610608headImg.jpg', 1, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (2, '纳兰嫣三', '181530333', '15415413', 'https://lin-xin.gitee.io/images/post/wms.png', 1, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (3, '李白', '123123126', '151131422', 'https://lin-xin.gitee.io/images/post/wms.png', 0, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (4, '李太白', '123123124', '151131423', 'https://lin-xin.gitee.io/images/post/wms.png', 1, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (5, '李太白', '123123123', '151131425', 'https://lin-xin.gitee.io/images/post/wms.png', 0, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (6, '李小白', '123123125', '151131422', 'https://lin-xin.gitee.io/images/post/wms.png', 1, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (7, '王子西安', '1232131231', '123123', 'https://lin-xin.gitee.io/images/post/wms.png', 0, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (8, '王晓梦', '1541254111', '123123', 'https://lin-xin.gitee.io/images/post/wms.png', 1, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (9, '王兄啊', '1645124541', '123123', 'https://lin-xin.gitee.io/images/post/wms.png', 0, '汉族', '河南平顶山市', '110120119', '计算机学院', '18软工三班', '党支部');
INSERT INTO `user` VALUES (13, '白莲居士', '181530666', '256dc8215ffa5bad57876de77e02f9af', NULL, 0, '汉族', '地球村', '12312312', '啊是本地', '17软二', '无');
INSERT INTO `user` VALUES (15, '刘鹏辉', '1815303266', '256dc8215ffa5bad57876de77e02f9af', NULL, 1, '', '', '', '', '', '');
INSERT INTO `user` VALUES (16, 'Lph', '1815302191', '256dc8215ffa5bad57876de77e02f9af', 'http://img.baiding.top/1635387021825headImg.jpg', 0, NULL, NULL, NULL, NULL, 'RS', NULL);
INSERT INTO `user` VALUES (17, '刘先生', '1815301111', '256dc8215ffa5bad57876de77e02f9af', 'http://img.baiding.top/1635398166577headImg.jpg', 1, '汉族', '河南', '18445411444', '软三', '3班', '学生会');
INSERT INTO `user` VALUES (18, '梁小姐', '1815302222', '256dc8215ffa5bad57876de77e02f9af', 'http://img.baiding.top/1635398742965headImg.jpg', 0, '蒙古族', '珠玛蒂埃哦', '15230521515', '计算机学院', '软三', NULL);

SET FOREIGN_KEY_CHECKS = 1;
