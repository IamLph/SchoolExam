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

 Date: 12/11/2021 16:45:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `examId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试题编号',
  `courseId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 1单选 2多选 3判断 ',
  `optionList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `userAnswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户答案',
  `userFavor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'false' COMMENT '用户是否收藏',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '11001', '1001', '水是液体？', '3', '[{\"prefix\":\"A\",\"content\":\"对\"},{\"prefix\":\"B\",\"content\":\"错\"}]', 'A', NULL, 'false', '难到是固体不成？');
INSERT INTO `question` VALUES (2, '11002', '1001', '酸菜鱼的味道？', '2', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"咸味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"辣味\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'A,B,D', NULL, 'false', '你怎么想都行，要的就是这个味，答案只能选A,B,D');
INSERT INTO `question` VALUES (3, '11003', '1001', '螺狮粉的味道？', '1', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"臭味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"香的\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'A', NULL, 'false', '你怎么想都行，要的就是这个味，答案只能选A');
INSERT INTO `question` VALUES (4, '11004', '1001', '螺狮粉的味道？好吃吗？', '2', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"臭味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"香的\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'A,D', NULL, 'false', '你怎么想都行，要的就是这个味，答案只能选A,D');
INSERT INTO `question` VALUES (5, '11005', '1002', '酸菜鱼的味道？', '1', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"咸味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"辣味\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'B', NULL, 'false', '选B');
INSERT INTO `question` VALUES (6, '11006', '1005', '酸菜鱼的味道？', '3', '[{\"prefix\":\"A\",\"content\":\"咸味\"},{\"prefix\":\"B\",\"content\":\"辣味\"},{\"prefix\":\"C\",\"content\":\"甜味\"},{\"prefix\":\"D\",\"content\":\"酸味\"}]', 'B', NULL, 'false', '选A');
INSERT INTO `question` VALUES (7, '11007', '1001', '酸菜鱼的味道？', '3', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"咸味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"辣味\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'B', NULL, 'false', '选B');
INSERT INTO `question` VALUES (8, '11008', '1001', '酸菜鱼的味道？', '1', '[{\r\n			\"prefix\": \"A\",\r\n			\"content\": \"咸味\"\r\n		}, {\r\n			\"prefix\": \"B\",\r\n			\"content\": \"辣味\"\r\n		}, {\r\n			\"prefix\": \"C\",\r\n			\"content\": \"甜味\"\r\n		}, {\r\n			\"prefix\": \"D\",\r\n			\"content\": \"酸味\"\r\n		}]', 'A', NULL, 'false', '选A');
INSERT INTO `question` VALUES (10, '6535858', '1002', '西瓜好吃吗？', '3', '[{\"prefix\":\"A\",\"content\":\"好吃\"},{\"prefix\":\"B\",\"content\":\"不好吃\"}]', 'A', NULL, 'false', '我爱西瓜');
INSERT INTO `question` VALUES (11, '8232215', '1002', '西瓜好吃吗？', '1', '[{\"prefix\":\"A\",\"content\":\"好吃\"},{\"prefix\":\"B\",\"content\":\"不好吃\"},{\"prefix\":\"C\",\"content\":\"特好吃\"},{\"prefix\":\"D\",\"content\":\"特别好吃\"}]', 'D', NULL, 'false', '我爱西瓜');
INSERT INTO `question` VALUES (12, '9997387', '1002', '西瓜好吃吗？', '2', '[{\"prefix\":\"A\",\"content\":\"好吃\"},{\"prefix\":\"B\",\"content\":\"不好吃\"},{\"prefix\":\"C\",\"content\":\"特好吃\"},{\"prefix\":\"D\",\"content\":\"特别好吃\"}]', 'A', NULL, 'false', '我爱西瓜');
INSERT INTO `question` VALUES (13, '9442222', '1002', '西瓜好吃吗？', '1', '[{\"prefix\":\"A\",\"content\":\"好吃\"},{\"prefix\":\"B\",\"content\":\"不好吃\"},{\"prefix\":\"C\",\"content\":\"特好吃\"},{\"prefix\":\"D\",\"content\":\"特别好吃\"}]', 'D', NULL, 'false', '我爱西瓜');
INSERT INTO `question` VALUES (14, '8642486', '1004', '达芬奇是唱歌的吗？', '3', '[{\"prefix\":\"A\",\"content\":\"是\"},{\"prefix\":\"B\",\"content\":\"不是\"}]', 'A', NULL, 'false', '花花的');

SET FOREIGN_KEY_CHECKS = 1;
