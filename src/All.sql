/*
 Navicat Premium Data Transfer

 Source Server         : First
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : vueblog

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/08/2021 20:01:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_blog
-- ----------------------------
DROP TABLE IF EXISTS `m_blog`;
CREATE TABLE `m_blog`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` bigint(20) NOT NULL,
                           `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                           `created` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
                           `status` tinyint(4) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_blog
-- ----------------------------
INSERT INTO `m_blog` VALUES (28, 2, '静观默察，藏器待时。', '欢迎来到我的学习博客，也欢迎各位和我一块学习，交流，分享每天在学习或者生活中得到的知识和感悟。', '2021年1月3日 \n         欢迎来到我的学习博客，也欢迎各位和我一块学习，交流，分享每天在学习或者生活中得到的知识和感悟。\n\n', '2021-08-31 15:54:35', 0);

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `status` int(5) NOT NULL,
                           `created` datetime(0) NULL DEFAULT NULL,
                           `last_login` datetime(0) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `UK_USERNAME`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES (2, 'WenJianC', 'http://www.explorerjy.com/wp-content/uploads/2021/01/cropped-%E5%A3%81%E7%BA%B8-1.jpg', NULL, '96e79218965eb72c92a549dd5a330112', 9, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
