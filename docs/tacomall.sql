/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : tacomall

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 20/11/2023 16:22:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for _template
-- ----------------------------
DROP TABLE IF EXISTS `_template`;
CREATE TABLE `_template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of _template
-- ----------------------------

-- ----------------------------
-- Table structure for approve
-- ----------------------------
DROP TABLE IF EXISTS `approve`;
CREATE TABLE `approve`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) NULL DEFAULT NULL,
  `executor_id` int(11) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL,
  `node_id` int(11) NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '1->审核中;2->已通过；3->未通过；4->已取消',
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of approve
-- ----------------------------
INSERT INTO `approve` VALUES (11, 1, NULL, 1, 1, NULL, 'passed', 0, '2022-10-05 17:26:37', NULL, NULL);

-- ----------------------------
-- Table structure for approve_node
-- ----------------------------
DROP TABLE IF EXISTS `approve_node`;
CREATE TABLE `approve_node`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `opt` json NULL,
  `org_dept_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `org_staff_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of approve_node
-- ----------------------------
INSERT INTO `approve_node` VALUES (1, 1, 1, '内容审核', '{\"SQLs\": [\"UPDATE member SET type = \'dl\' WHERE id IN (SELECT member_id FROM apply_region #WHERE)\"], \"hook\": {\"add\": \"pending\", \"passed\": \"wait\", \"reject\": \"reject\"}, \"table\": \"apply_region\", \"detail\": [{\"key\": \"applyRegion.sn\", \"type\": \"text\", \"label\": \"关联订单\"}, {\"key\": \"applyRegion.member.nickname\", \"type\": \"text\", \"label\": \"申请人\"}, {\"key\": \"applyRegion.province\", \"type\": \"text\", \"label\": \"区域\"}, {\"key\": \"applyRegion.createTime\", \"type\": \"text\", \"label\": \"创建时间\"}, {\"key\": \"applyRegion.remark\", \"type\": \"text\", \"label\": \"备注\"}]}', NULL, NULL, 0, '2022-09-30 18:03:06', NULL, NULL);

-- ----------------------------
-- Table structure for approve_process
-- ----------------------------
DROP TABLE IF EXISTS `approve_process`;
CREATE TABLE `approve_process`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `approve_id` int(11) NULL DEFAULT NULL,
  `node_id` int(11) NULL DEFAULT NULL,
  `checker_id` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of approve_process
-- ----------------------------
INSERT INTO `approve_process` VALUES (1, 10, 1, 1, 'passed', NULL, 0, '2022-10-05 15:41:23', NULL, NULL);
INSERT INTO `approve_process` VALUES (3, 11, 1, 1, 'passed', NULL, 0, '2022-10-05 17:31:33', NULL, NULL);

-- ----------------------------
-- Table structure for approve_type
-- ----------------------------
DROP TABLE IF EXISTS `approve_type`;
CREATE TABLE `approve_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `opt` json NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of approve_type
-- ----------------------------
INSERT INTO `approve_type` VALUES (1, '作品审核', NULL, '{}', 0, '2022-09-30 18:01:17', NULL, NULL);

-- ----------------------------
-- Table structure for form_order
-- ----------------------------
DROP TABLE IF EXISTS `form_order`;
CREATE TABLE `form_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `logistic_id` int(11) NULL DEFAULT NULL,
  `form_pay_id` int(11) NULL DEFAULT NULL,
  `approve_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `info_data` json NULL,
  `amount_estimate` decimal(10, 2) NULL DEFAULT NULL,
  `amount_actual` decimal(10, 2) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_order
-- ----------------------------
INSERT INTO `form_order` VALUES (43, 'KD', 23, NULL, 28, NULL, 'OF-202310131829181558307', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"YTO\", \"takeTime\": null, \"objectType\": \"日用品\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.11, 0.11, 'refund', 0, '2023-10-13 18:29:18', NULL, NULL);
INSERT INTO `form_order` VALUES (44, 'KD', 23, 9, 29, NULL, 'OF-202310131842297542005', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"YTO\", \"takeTime\": null, \"objectType\": \"日用品\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.11, 0.11, 'refund', 0, '2023-10-13 18:42:30', '2023-10-20 16:47:05', NULL);
INSERT INTO `form_order` VALUES (45, 'KD', 23, NULL, 30, NULL, 'OF-202310141059096038877', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"STO-INT\", \"takeTime\": null, \"objectType\": \"电子产品\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-14 10:59:10', NULL, NULL);
INSERT INTO `form_order` VALUES (46, 'KD', 23, NULL, 31, NULL, 'OF-202310161517274557204', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"YTO\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-16 15:17:27', NULL, NULL);
INSERT INTO `form_order` VALUES (47, 'KD', 23, NULL, 32, NULL, 'OF-202310161517405016514', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"YTO\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-16 15:17:41', NULL, NULL);
INSERT INTO `form_order` VALUES (48, 'KD', 23, NULL, 33, NULL, 'OF-202310161519209564108', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"STO-INT\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-16 15:19:21', NULL, NULL);
INSERT INTO `form_order` VALUES (49, 'KD', 23, NULL, 34, NULL, 'OF-202310161519558692723', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"STO-INT\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-16 15:19:56', NULL, NULL);
INSERT INTO `form_order` VALUES (50, 'KD', 23, NULL, 35, NULL, 'OF-202310161521488264686', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"JD\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'initial', 0, '2023-10-16 15:21:49', NULL, NULL);
INSERT INTO `form_order` VALUES (51, 'KD', 23, 10, 36, NULL, 'OF-202310202001368996394', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"STO-INT\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'refund', 0, '2023-10-20 20:01:37', NULL, NULL);
INSERT INTO `form_order` VALUES (52, 'KD', 23, 11, 37, NULL, 'OF-202310202033298327205', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"YUND\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'refund', 0, '2023-10-20 20:33:30', '2023-10-20 20:34:00', NULL);
INSERT INTO `form_order` VALUES (53, 'KD', 23, 12, 38, NULL, 'OF-202310202043182875851', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"JT\", \"takeTime\": null, \"objectType\": \"日用品\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'refund', 0, '2023-10-20 20:43:18', '2023-10-20 20:43:46', NULL);
INSERT INTO `form_order` VALUES (54, 'KD', 23, 13, 39, NULL, 'OF-202310202046402126696', '{\"note\": \"\", \"ensure\": null, \"logistic\": \"STO-INT\", \"takeTime\": null, \"objectType\": \"文件\", \"senderCity\": \"韶关市\", \"objectWeight\": 1, \"receiverCity\": \"佛山市\", \"senderDetail\": \"学府里二期一栋\", \"senderMobile\": \"16607574271\", \"receiverDetail\": \"石湾镇街道彩虹北二街12号绿地璀璨家园一期4座\", \"receiverMobile\": \"18823483091\", \"senderDistrict\": \"仁化县\", \"senderProvince\": \"广东省\", \"senderRealName\": \"邓海标\", \"receiverDistrict\": \"禅城区\", \"receiverProvince\": \"广东省\", \"receiverRealName\": \"陈华相\"}', 0.10, 0.11, 'refund', 0, '2023-10-20 20:46:40', '2023-10-20 20:47:04', NULL);
INSERT INTO `form_order` VALUES (55, 'CHF', 23, NULL, 40, NULL, 'OF-202310301810184052908', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'initial', 0, '2023-10-30 18:10:18', NULL, NULL);
INSERT INTO `form_order` VALUES (56, 'CHF', 23, NULL, 41, NULL, 'OF-202310301842587031452', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-10-30 18:42:59', NULL, NULL);
INSERT INTO `form_order` VALUES (57, 'CHF', 23, NULL, 42, NULL, 'OF-202310301952463921711', '{\"note\": \"\", \"amount\": 189, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 189.00, 0.10, 'refund', 0, '2023-10-30 19:52:46', NULL, NULL);
INSERT INTO `form_order` VALUES (58, 'CHF', 23, NULL, 43, NULL, 'OF-202310301956528517849', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-10-30 19:56:53', '2023-11-02 17:40:59', NULL);
INSERT INTO `form_order` VALUES (59, 'CHF', 23, NULL, 44, NULL, 'OF-202311031815547424125', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 18:15:55', NULL, NULL);
INSERT INTO `form_order` VALUES (60, 'CHF', 23, NULL, 45, NULL, 'OF-202311031820167543987', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 18:20:17', NULL, NULL);
INSERT INTO `form_order` VALUES (61, 'CHF', 23, NULL, 46, NULL, 'OF-202311031823312158915', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 18:23:31', NULL, NULL);
INSERT INTO `form_order` VALUES (62, 'CHF', 23, NULL, 47, NULL, 'OF-202311031830190961698', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:30:19', NULL, NULL);
INSERT INTO `form_order` VALUES (63, 'CHF', 23, NULL, 48, NULL, 'OF-202311031834552041974', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:34:55', NULL, NULL);
INSERT INTO `form_order` VALUES (64, 'CHF', 23, NULL, 49, NULL, 'OF-202311031837445672251', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:37:45', NULL, NULL);
INSERT INTO `form_order` VALUES (65, 'CHF', 23, NULL, 50, NULL, 'OF-202311031840438347353', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:40:44', NULL, NULL);
INSERT INTO `form_order` VALUES (66, 'CHF', 23, NULL, 51, NULL, 'OF-202311031843235961378', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:43:24', NULL, NULL);
INSERT INTO `form_order` VALUES (67, 'CHF', 23, NULL, 52, NULL, 'OF-202311031847378302548', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'placed', 0, '2023-11-03 18:47:38', NULL, NULL);
INSERT INTO `form_order` VALUES (68, 'CHF', 23, NULL, 53, NULL, 'OF-202311031848341278870', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 18:48:34', NULL, NULL);
INSERT INTO `form_order` VALUES (69, 'CHF', 23, NULL, 54, NULL, 'OF-202311031907471266802', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 19:07:47', NULL, NULL);
INSERT INTO `form_order` VALUES (70, 'CHF', 23, NULL, 55, NULL, 'OF-202311031909391444428', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 19:09:39', NULL, NULL);
INSERT INTO `form_order` VALUES (71, 'CHF', 23, NULL, 56, NULL, 'OF-202311031914599732955', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 19:15:00', NULL, NULL);
INSERT INTO `form_order` VALUES (72, 'CHF', 23, NULL, 57, NULL, 'OF-202311031919060053713', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 19:19:06', NULL, NULL);
INSERT INTO `form_order` VALUES (73, 'CHF', 23, NULL, 58, NULL, 'OF-202311032038453534363', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 20:38:45', NULL, NULL);
INSERT INTO `form_order` VALUES (74, 'CHF', 23, NULL, 59, NULL, 'OF-202311032043008193486', '{\"note\": \"\", \"amount\": 94.5, \"ensure\": null, \"account\": \"16607574271\", \"logistic\": \"\", \"takeTime\": null, \"objectType\": \"\", \"senderCity\": \"\", \"objectWeight\": null, \"receiverCity\": \"\", \"senderDetail\": \"\", \"senderMobile\": \"\", \"receiverDetail\": \"\", \"receiverMobile\": \"\", \"senderDistrict\": \"\", \"senderProvince\": \"\", \"senderRealName\": \"\", \"receiverDistrict\": \"\", \"receiverProvince\": \"\", \"receiverRealName\": \"\"}', 94.50, 0.10, 'refund', 0, '2023-11-03 20:43:01', NULL, NULL);

-- ----------------------------
-- Table structure for form_order_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `form_order_product_sku`;
CREATE TABLE `form_order_product_sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `sku_id` int(11) NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `quantity` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_order_product_sku
-- ----------------------------

-- ----------------------------
-- Table structure for form_pay
-- ----------------------------
DROP TABLE IF EXISTS `form_pay`;
CREATE TABLE `form_pay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `refund_form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `fee` decimal(10, 2) NULL DEFAULT NULL,
  `info_data` json NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_pay
-- ----------------------------
INSERT INTO `form_pay` VALUES (28, 'WX', 'PF-202310131829182622911', NULL, 0.11, NULL, 'refund', 0, '2023-10-13 18:29:18', NULL, NULL);
INSERT INTO `form_pay` VALUES (29, 'WX', 'PF-202310131842298532298', NULL, 0.11, NULL, 'payed', 0, '2023-10-13 18:42:30', NULL, NULL);
INSERT INTO `form_pay` VALUES (30, 'WX', 'PF-202310141059097423919', NULL, 0.11, NULL, 'created', 0, '2023-10-14 10:59:10', NULL, NULL);
INSERT INTO `form_pay` VALUES (31, 'WX', 'PF-202310161517275823096', NULL, 0.11, NULL, 'created', 0, '2023-10-16 15:17:28', NULL, NULL);
INSERT INTO `form_pay` VALUES (32, 'WX', 'PF-202310161517406082787', NULL, 0.11, NULL, 'created', 0, '2023-10-16 15:17:41', NULL, NULL);
INSERT INTO `form_pay` VALUES (33, 'WX', 'PF-202310161519210816147', NULL, 0.11, NULL, 'created', 0, '2023-10-16 15:19:21', NULL, NULL);
INSERT INTO `form_pay` VALUES (34, 'WX', 'PF-202310161519559954711', NULL, 0.11, NULL, 'created', 0, '2023-10-16 15:19:56', NULL, NULL);
INSERT INTO `form_pay` VALUES (35, 'WX', 'PF-202310161521489498994', NULL, 0.11, NULL, 'created', 0, '2023-10-16 15:21:49', NULL, NULL);
INSERT INTO `form_pay` VALUES (36, 'WX', 'PF-202310202001370384608', NULL, 0.11, NULL, 'payed', 0, '2023-10-20 20:01:37', NULL, NULL);
INSERT INTO `form_pay` VALUES (37, 'WX', 'PF-202310202033299612870', NULL, 0.11, NULL, 'payed', 0, '2023-10-20 20:33:30', NULL, NULL);
INSERT INTO `form_pay` VALUES (38, 'WX', 'PF-202310202043183906782', NULL, 0.11, NULL, 'payed', 0, '2023-10-20 20:43:18', NULL, NULL);
INSERT INTO `form_pay` VALUES (39, 'WX', 'PF-202310202046403576658', NULL, 0.11, NULL, 'refund', 0, '2023-10-20 20:46:40', NULL, NULL);
INSERT INTO `form_pay` VALUES (40, 'WX', 'PF-202310301810184204573', NULL, 0.10, NULL, 'created', 0, '2023-10-30 18:10:18', NULL, NULL);
INSERT INTO `form_pay` VALUES (41, 'WX', 'PF-202310301842587183167', NULL, 0.10, NULL, 'refund', 0, '2023-10-30 18:42:59', NULL, NULL);
INSERT INTO `form_pay` VALUES (42, 'WX', 'PF-202310301952463988794', NULL, 0.10, NULL, 'refund', 0, '2023-10-30 19:52:46', NULL, NULL);
INSERT INTO `form_pay` VALUES (43, 'WX', 'PF-202310301956528553685', NULL, 0.10, NULL, 'payed', 0, '2023-10-30 19:56:53', NULL, NULL);
INSERT INTO `form_pay` VALUES (44, 'WX', 'PF-202311031815547581406', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 18:15:55', NULL, NULL);
INSERT INTO `form_pay` VALUES (45, 'WX', 'PF-202311031820167573056', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 18:20:17', NULL, NULL);
INSERT INTO `form_pay` VALUES (46, 'WX', 'PF-202311031823312185341', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 18:23:31', NULL, NULL);
INSERT INTO `form_pay` VALUES (47, 'WX', 'PF-202311031830191025256', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:30:19', NULL, NULL);
INSERT INTO `form_pay` VALUES (48, 'WX', 'PF-202311031834552096876', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:34:55', NULL, NULL);
INSERT INTO `form_pay` VALUES (49, 'WX', 'PF-202311031837445708157', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:37:45', NULL, NULL);
INSERT INTO `form_pay` VALUES (50, 'WX', 'PF-202311031840438372722', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:40:44', NULL, NULL);
INSERT INTO `form_pay` VALUES (51, 'WX', 'PF-202311031843235988668', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:43:24', NULL, NULL);
INSERT INTO `form_pay` VALUES (52, 'WX', 'PF-202311031847378345555', NULL, 0.10, NULL, 'payed', 0, '2023-11-03 18:47:38', NULL, NULL);
INSERT INTO `form_pay` VALUES (53, 'WX', 'PF-202311031848341302182', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 18:48:34', NULL, NULL);
INSERT INTO `form_pay` VALUES (54, 'WX', 'PF-202311031907471294912', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 19:07:47', NULL, NULL);
INSERT INTO `form_pay` VALUES (55, 'WX', 'PF-202311031909391687175', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 19:09:39', NULL, NULL);
INSERT INTO `form_pay` VALUES (56, 'WX', 'PF-202311031915000013639', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 19:15:00', NULL, NULL);
INSERT INTO `form_pay` VALUES (57, 'WX', 'PF-202311031919060087755', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 19:19:06', NULL, NULL);
INSERT INTO `form_pay` VALUES (58, 'WX', 'PF-202311032038453745113', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 20:38:45', NULL, NULL);
INSERT INTO `form_pay` VALUES (59, 'WX', 'PF-202311032043008237606', NULL, 0.10, NULL, 'refund', 0, '2023-11-03 20:43:01', NULL, NULL);

-- ----------------------------
-- Table structure for form_refund
-- ----------------------------
DROP TABLE IF EXISTS `form_refund`;
CREATE TABLE `form_refund`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `fee` decimal(10, 2) NULL DEFAULT NULL,
  `info_data` json NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_refund
-- ----------------------------
INSERT INTO `form_refund` VALUES (10, 'WX', 'RF-202310131830188117529', 0.11, NULL, 'completed', 0, '2023-10-13 18:30:19', NULL, NULL);
INSERT INTO `form_refund` VALUES (11, 'WX', 'RF-202310202047321196076', 0.11, NULL, 'completed', 0, '2023-10-20 20:47:32', NULL, NULL);
INSERT INTO `form_refund` VALUES (12, 'WX', 'RF-202310301843229045521', 0.10, NULL, 'completed', 0, '2023-10-30 18:43:23', NULL, NULL);
INSERT INTO `form_refund` VALUES (13, 'WX', 'RF-202310301953148474604', 0.10, NULL, 'completed', 0, '2023-10-30 19:53:15', NULL, NULL);
INSERT INTO `form_refund` VALUES (14, 'WX', 'RF-202311031816202723498', 0.10, NULL, 'completed', 0, '2023-11-03 18:16:20', NULL, NULL);
INSERT INTO `form_refund` VALUES (15, 'WX', 'RF-202311031820400776694', 0.10, NULL, 'completed', 0, '2023-11-03 18:20:40', NULL, NULL);
INSERT INTO `form_refund` VALUES (16, 'WX', 'RF-202311031823454938609', 0.10, NULL, 'completed', 0, '2023-11-03 18:23:45', NULL, NULL);
INSERT INTO `form_refund` VALUES (17, 'WX', 'RF-202311031848512966995', 0.10, NULL, 'completed', 0, '2023-11-03 18:48:51', NULL, NULL);
INSERT INTO `form_refund` VALUES (18, 'WX', 'RF-202311031908445076522', 0.10, NULL, 'completed', 0, '2023-11-03 19:08:45', NULL, NULL);
INSERT INTO `form_refund` VALUES (19, 'WX', 'RF-202311031910534476428', 0.10, NULL, 'completed', 0, '2023-11-03 19:10:53', NULL, NULL);
INSERT INTO `form_refund` VALUES (20, 'WX', 'RF-202311031915236046833', 0.10, NULL, 'completed', 0, '2023-11-03 19:15:24', NULL, NULL);
INSERT INTO `form_refund` VALUES (21, 'WX', 'RF-202311031919401797466', 0.10, NULL, 'completed', 0, '2023-11-03 19:19:40', NULL, NULL);
INSERT INTO `form_refund` VALUES (22, 'WX', 'RF-202311032039499388216', 0.10, NULL, 'completed', 0, '2023-11-03 20:39:50', NULL, NULL);
INSERT INTO `form_refund` VALUES (23, 'WX', 'RF-202311032043156347090', 0.10, NULL, 'completed', 0, '2023-11-03 20:43:16', NULL, NULL);

-- ----------------------------
-- Table structure for logistic
-- ----------------------------
DROP TABLE IF EXISTS `logistic`;
CREATE TABLE `logistic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `info_data` json NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logistic
-- ----------------------------
INSERT INTO `logistic` VALUES (9, 'YTO', '{\"price\": \"[{\\\"add\\\":1.1,\\\"end\\\":50,\\\"first\\\":5.34,\\\"start\\\":1}]\", \"orderNo\": \"YT231013184245231774\", \"printInfo\": {\"shortAddress\": \"610-H16-00-037\"}, \"upOrderId\": null, \"deliveryId\": \"YT2507755915650\", \"expandInfo\": null, \"pickUpCode\": null, \"calcFeeType\": \"profit\", \"limitWeight\": \"50.00\", \"preOrderFee\": \"5.34\", \"calcFeeWeight\": \"1\", \"exChannelName\": \"圆通【BL】P-限重50KG\", \"originalPrice\": \"[{\\\"add\\\":2,\\\"end\\\":0,\\\"first\\\":8,\\\"start\\\":1}]\", \"preDeliveryFee\": \"5.34\", \"childDeliveryIds\": null}', 'created', 0, '2023-10-13 18:42:46', NULL, NULL);
INSERT INTO `logistic` VALUES (10, 'STO-INT', '{\"price\": \"[{\\\"add\\\":1.35,\\\"end\\\":50,\\\"first\\\":5.30,\\\"start\\\":1}]\", \"orderNo\": \"ST231020200243979269\", \"printInfo\": {\"bulkpen\": \"610-F10 798\", \"pkgName\": \"中山中转包 1\"}, \"upOrderId\": null, \"deliveryId\": \"772022772104354\", \"expandInfo\": null, \"pickUpCode\": null, \"calcFeeType\": \"profit\", \"limitWeight\": \"50.00\", \"preOrderFee\": \"5.30\", \"calcFeeWeight\": \"1\", \"exChannelName\": \"申通【AYB】P-限重50KG\", \"originalPrice\": \"[{\\\"add\\\":2,\\\"end\\\":0,\\\"first\\\":10,\\\"start\\\":1}]\", \"preDeliveryFee\": \"5.30\", \"childDeliveryIds\": null}', 'created', 0, '2023-10-20 20:02:43', NULL, NULL);
INSERT INTO `logistic` VALUES (11, 'YUND', '{\"price\": \"[{\\\"add\\\":1.45,\\\"end\\\":50,\\\"first\\\":5.0,\\\"start\\\":1}]\", \"orderNo\": \"YD231020203347223672\", \"printInfo\": null, \"upOrderId\": null, \"deliveryId\": \"5302013323556\", \"expandInfo\": null, \"pickUpCode\": null, \"calcFeeType\": \"profit\", \"limitWeight\": \"50.00\", \"preOrderFee\": \"5.00\", \"calcFeeWeight\": \"1\", \"exChannelName\": \"韵达【AY】P-限重50KG\", \"originalPrice\": \"[{\\\"add\\\":2,\\\"end\\\":0,\\\"first\\\":8,\\\"start\\\":1}]\", \"preDeliveryFee\": \"5.00\", \"childDeliveryIds\": null}', 'created', 0, '2023-10-20 20:33:49', NULL, NULL);
INSERT INTO `logistic` VALUES (12, 'JT', '{\"price\": \"[{\\\"add\\\":1.88,\\\"end\\\":50,\\\"first\\\":9.0,\\\"start\\\":1}]\", \"orderNo\": \"JT231020204333598177\", \"printInfo\": {\"bulkpen\": \"122  U406-00  333\", \"pkgName\": \"广州里水转运中心\"}, \"upOrderId\": null, \"deliveryId\": \"JT0009688912190\", \"expandInfo\": null, \"pickUpCode\": null, \"calcFeeType\": \"profit\", \"limitWeight\": \"50.00\", \"preOrderFee\": \"9.00\", \"calcFeeWeight\": \"1\", \"exChannelName\": \"极兔【AYB】\", \"originalPrice\": \"[{\\\"add\\\":2,\\\"end\\\":0,\\\"first\\\":10,\\\"start\\\":1}]\", \"preDeliveryFee\": \"9.00\", \"childDeliveryIds\": null}', 'created', 0, '2023-10-20 20:43:33', NULL, NULL);
INSERT INTO `logistic` VALUES (13, 'STO-INT', '{\"price\": \"[{\\\"add\\\":1.35,\\\"end\\\":50,\\\"first\\\":5.30,\\\"start\\\":1}]\", \"orderNo\": \"ST231020204655602104\", \"printInfo\": {\"bulkpen\": \"610-F10 798\", \"pkgName\": \"中山中转包 1\"}, \"upOrderId\": null, \"deliveryId\": \"772022770051361\", \"expandInfo\": null, \"pickUpCode\": null, \"calcFeeType\": \"profit\", \"limitWeight\": \"50.00\", \"preOrderFee\": \"5.30\", \"calcFeeWeight\": \"1\", \"exChannelName\": \"申通【AYB】P-限重50KG\", \"originalPrice\": \"[{\\\"add\\\":2,\\\"end\\\":0,\\\"first\\\":10,\\\"start\\\":1}]\", \"preDeliveryFee\": \"5.30\", \"childDeliveryIds\": null}', 'created', 0, '2023-10-20 20:46:56', NULL, NULL);

-- ----------------------------
-- Table structure for logistic_tracking
-- ----------------------------
DROP TABLE IF EXISTS `logistic_tracking`;
CREATE TABLE `logistic_tracking`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logistic_id` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logistic_tracking
-- ----------------------------

-- ----------------------------
-- Table structure for logistic_type
-- ----------------------------
DROP TABLE IF EXISTS `logistic_type`;
CREATE TABLE `logistic_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `channel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logistic_type
-- ----------------------------
INSERT INTO `logistic_type` VALUES (1, '顺丰', 'SF', 'icon_sf.png', 'yida178', 'ok', 0, '2023-09-20 16:47:29', NULL, NULL);
INSERT INTO `logistic_type` VALUES (2, '圆通', 'YTO', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:00', NULL, NULL);
INSERT INTO `logistic_type` VALUES (3, '京东', 'JD', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (4, '申通', 'STO-INT', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (5, '德邦', 'DOP', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (6, '极兔', 'JT', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (7, '中通', 'ZTO', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (8, '韵达', 'YUND', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (9, '菜鸟', 'CNSD', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);
INSERT INTO `logistic_type` VALUES (10, '百世', 'BEST', NULL, 'yida178', 'ok', 0, '2023-09-20 16:48:24', NULL, NULL);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `invite_member_id` int(11) NULL DEFAULT NULL,
  `invite_upper_member_id` int(11) NULL DEFAULT NULL,
  `chain_ids` json NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `invite_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (22, 'PT', NULL, NULL, NULL, 'root', NULL, NULL, NULL, '', NULL, '//sudaexpress.oss-cn-shenzhen.aliyuncs.com/b69c4182-3090-4b44-a765-58e2894c9cfa', '', NULL, 0, '2023-07-28 17:09:54', '2023-08-01 23:21:21', NULL);
INSERT INTO `member` VALUES (23, 'PT', 22, NULL, NULL, 'Jerry Tang', NULL, NULL, NULL, NULL, NULL, '//sudaexpress.oss-cn-shenzhen.aliyuncs.com/5f483dd7-e784-4fff-964e-73efa0f9038d', 'ooUNz621Syr5wZONbmIJ_hxq59kg', NULL, 0, '2023-08-22 22:48:03', '2023-08-22 22:48:12', NULL);

-- ----------------------------
-- Table structure for member_address
-- ----------------------------
DROP TABLE IF EXISTS `member_address`;
CREATE TABLE `member_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_address
-- ----------------------------
INSERT INTO `member_address` VALUES (7, 23, '广东省', '韶关市', '仁化县', '学府里二期一栋', '邓海标', '16607574271', 0, NULL, NULL, NULL);
INSERT INTO `member_address` VALUES (8, 23, '广东省', '佛山市', '禅城区', '石湾镇街道彩虹北二街12号绿地璀璨家园一期4座', '陈华相', '18823483091', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for member_wallet
-- ----------------------------
DROP TABLE IF EXISTS `member_wallet`;
CREATE TABLE `member_wallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL,
  `balance` decimal(10, 2) NULL DEFAULT 0.00,
  `balance_pending` decimal(10, 2) NULL DEFAULT 0.00,
  `balance_withdraw` decimal(10, 2) NULL DEFAULT 0.00,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_wallet
-- ----------------------------
INSERT INTO `member_wallet` VALUES (1, 19, 0.00, 0.00, 0.00, 0, NULL, NULL, NULL);
INSERT INTO `member_wallet` VALUES (2, 20, 0.00, 0.00, 0.00, 0, NULL, NULL, NULL);
INSERT INTO `member_wallet` VALUES (3, 21, 0.00, 0.00, 0.00, 0, NULL, NULL, NULL);
INSERT INTO `member_wallet` VALUES (4, 22, 0.00, 0.00, 0.00, 0, NULL, NULL, NULL);
INSERT INTO `member_wallet` VALUES (5, 23, 0.00, 0.00, 0.00, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for org_access_rule
-- ----------------------------
DROP TABLE IF EXISTS `org_access_rule`;
CREATE TABLE `org_access_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NULL DEFAULT NULL,
  `chain_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `reg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_access_rule
-- ----------------------------
INSERT INTO `org_access_rule` VALUES (1, 0, NULL, '工作审批', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);
INSERT INTO `org_access_rule` VALUES (2, 1, NULL, '上刊审批', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);
INSERT INTO `org_access_rule` VALUES (3, 0, NULL, '上刊管理', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);
INSERT INTO `org_access_rule` VALUES (4, 3, NULL, '查看', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);
INSERT INTO `org_access_rule` VALUES (5, 3, NULL, '创建', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);
INSERT INTO `org_access_rule` VALUES (6, 0, NULL, '客户管理', NULL, NULL, 0, '2022-10-06 14:48:19', NULL, NULL);

-- ----------------------------
-- Table structure for org_dept
-- ----------------------------
DROP TABLE IF EXISTS `org_dept`;
CREATE TABLE `org_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leader_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `access_rule_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `tag_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_dept
-- ----------------------------
INSERT INTO `org_dept` VALUES (1, NULL, '数字化创新部', NULL, '1,2,3,4,5,6', NULL, 0, NULL, NULL, NULL);
INSERT INTO `org_dept` VALUES (2, NULL, '运营部', NULL, '1,2', NULL, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for org_job
-- ----------------------------
DROP TABLE IF EXISTS `org_job`;
CREATE TABLE `org_job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `ignore_access_rule_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `ignore_tag_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_job
-- ----------------------------

-- ----------------------------
-- Table structure for org_staff
-- ----------------------------
DROP TABLE IF EXISTS `org_staff`;
CREATE TABLE `org_staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NULL DEFAULT NULL,
  `job_id` int(11) NULL DEFAULT NULL,
  `is_admin` int(11) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_staff
-- ----------------------------
INSERT INTO `org_staff` VALUES (1, NULL, NULL, 1, '开发账号', '16607574271', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for org_staff_login_log
-- ----------------------------
DROP TABLE IF EXISTS `org_staff_login_log`;
CREATE TABLE `org_staff_login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_staff_login_log
-- ----------------------------
INSERT INTO `org_staff_login_log` VALUES (1, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:26:33', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (2, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:27:13', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (3, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:27:26', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (4, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:28:16', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (5, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:34:03', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (6, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:35:17', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (7, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:36:00', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (8, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:37:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (9, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:35', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (10, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:37', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (11, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:37', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (12, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:37', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (13, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:37', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (14, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:38', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (15, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:38', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (16, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:40:38', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (17, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:44', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (18, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:46', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (19, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (20, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (21, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (22, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:49', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (23, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:53', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (24, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:50:55', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (25, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:57:22', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (26, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:57:45', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (27, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-25 17:58:13', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (28, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:36:08', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (29, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:37:59', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (30, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:39:27', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (31, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:41:04', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (32, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:48:03', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (33, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:52:11', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (34, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 22:52:50', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (35, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 23:31:25', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (36, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-27 23:31:38', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (37, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-28 14:10:24', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (38, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-28 14:12:36', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (39, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-28 14:15:03', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (40, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-28 14:21:10', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (41, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-29 14:05:56', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (42, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-29 16:14:06', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (43, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-29 18:53:30', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (44, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-29 19:58:53', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (45, NULL, '0:0:0:0:0:0:0:1', 0, '2022-07-29 21:40:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (46, NULL, '0:0:0:0:0:0:0:1', 0, '2022-08-09 10:38:53', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (47, NULL, '0:0:0:0:0:0:0:1', 0, '2022-08-09 10:42:25', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (48, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-17 16:57:57', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (49, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-17 17:16:45', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (50, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-17 21:28:25', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (51, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-21 21:50:23', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (52, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-26 14:36:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (53, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-26 16:09:40', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (54, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-26 17:50:22', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (55, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-27 11:33:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (56, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-27 18:54:31', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (57, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-27 18:58:09', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (58, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-27 19:20:00', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (59, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-28 15:04:52', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (60, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-29 17:31:54', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (61, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-29 17:35:31', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (62, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 09:00:53', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (63, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 17:53:07', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (64, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 17:57:18', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (65, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 17:59:34', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (66, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:11:08', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (67, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:11:14', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (68, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:11:40', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (69, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:12:11', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (70, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:14:30', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (71, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:14:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (72, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:18:31', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (73, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:25:02', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (74, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:39:16', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (75, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 18:59:25', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (76, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 19:00:58', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (77, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 19:02:33', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (78, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 19:04:15', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (79, NULL, '0:0:0:0:0:0:0:1', 0, '2022-09-30 21:41:23', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (80, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 15:26:01', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (81, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 15:45:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (82, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 15:55:10', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (83, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 15:59:45', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (84, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:02:10', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (85, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:03:13', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (86, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:45:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (87, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:48:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (88, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:49:56', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (89, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:53:12', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (90, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-03 16:54:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (91, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 16:18:59', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (92, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 16:22:59', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (93, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 16:24:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (94, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 16:37:09', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (95, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 18:35:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (96, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 18:38:31', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (97, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 18:47:06', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (98, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-04 18:48:24', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (99, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 15:11:47', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (100, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 15:20:31', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (101, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 15:37:24', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (102, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 15:39:01', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (103, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:23:46', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (104, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:26:24', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (105, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:28:37', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (106, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:31:27', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (107, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:45:19', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (108, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-05 17:47:58', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (109, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-06 14:26:40', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (110, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-06 14:58:14', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (111, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-07 14:20:17', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (112, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-07 15:32:35', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (113, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-08 11:29:16', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (114, NULL, '0:0:0:0:0:0:0:1', 0, '2022-10-10 13:33:28', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (115, NULL, '0:0:0:0:0:0:0:1', 0, '2023-01-16 21:05:21', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (116, NULL, '0:0:0:0:0:0:0:1', 0, '2023-01-17 22:22:20', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (117, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-11 17:33:57', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (118, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-11 19:16:13', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (119, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-11 19:41:36', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (120, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-11 20:59:17', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (121, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-11 21:09:09', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (122, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-12 21:41:28', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (123, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-19 15:36:52', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (124, NULL, '0:0:0:0:0:0:0:1', 0, '2023-07-29 21:29:49', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (125, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-01 18:23:35', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (126, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-01 19:04:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (127, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-01 19:12:02', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (128, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-02 17:23:21', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (129, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-02 17:39:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (130, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-02 17:47:33', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (131, NULL, '0:0:0:0:0:0:0:1', 0, '2023-08-02 18:07:28', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (132, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-13 21:07:27', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (133, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 15:54:04', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (134, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 16:07:51', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (135, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 16:39:27', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (136, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 20:03:39', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (137, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 20:12:12', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (138, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 20:22:20', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (139, NULL, '0:0:0:0:0:0:0:1', 0, '2023-10-20 20:31:42', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (140, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-02 16:42:54', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (141, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-06 20:42:50', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (142, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-10 21:04:53', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (143, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-14 20:08:15', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (144, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-14 22:20:46', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (145, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-15 21:14:40', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (146, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-16 16:21:10', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (147, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-16 21:44:48', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (148, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-16 21:47:30', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (149, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-16 21:50:00', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (150, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-16 22:09:52', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (151, NULL, '0:0:0:0:0:0:0:1', 0, '2023-11-17 15:10:58', NULL, NULL);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `info_data` json NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '汉堡', NULL, '{\"amount\": \"120\", \"status\": \"posted\", \"thumbnail\": \"\", \"description\": \"好吃汉堡\"}', NULL, 0, '2023-11-16 21:48:06', NULL, NULL);

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `info_data` json NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (1, 1, '牛肉', NULL, 12, 0, '2023-11-16 21:48:06', NULL, NULL);
INSERT INTO `product_sku` VALUES (2, 1, '芝士', NULL, 11, 0, '2023-11-16 21:48:06', NULL, NULL);

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of region
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info_data` json NULL,
  `menu` json NULL,
  `api` json NULL,
  `entity` json NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES (2, '{\"name\": \"塔可商城\", \"briefs\": \"TACOMALL|塔可商城 线下多门店，会员分销，市场营销系统📦\", \"nameEN\": \"TACOMALL\", \"subTitle\": \"（CRM）\"}', '[{\"icon\": \"&#xe630;\", \"path\": \"/index\", \"title\": \"Dashbord\", \"isCoreIcon\": true}, {\"sub\": [{\"path\": \"/approve/list\", \"title\": \"审批列表\", \"accessRuleId\": 1}, {\"path\": \"/approve-type/list\", \"title\": \"审批类型\", \"accessRuleId\": 2}], \"icon\": \"&#xe6a1;\", \"path\": \"/approve\", \"title\": \"工作审批\", \"isCoreIcon\": true}, {\"sub\": [{\"path\": \"/product/list\", \"title\": \"产品列表\", \"accessRuleId\": 1}], \"icon\": \"&#xe78a;\", \"path\": \"/product\", \"title\": \"产品中心\"}, {\"sub\": [{\"path\": \"/form-order/list\", \"title\": \"订单列表\", \"accessRuleId\": 1}], \"icon\": \"&#xe78a;\", \"path\": \"/form-order\", \"title\": \"订单管理\"}, {\"sub\": [{\"path\": \"/member/list\", \"title\": \"客户列表\", \"accessRuleId\": 1}], \"icon\": \"&#xe600;\", \"path\": \"/member\", \"title\": \"会员管理\"}, {\"sub\": [{\"path\": \"/region/list\", \"title\": \"产品列表\", \"accessRuleId\": 1}], \"icon\": \"&#xe683;\", \"path\": \"/region\", \"title\": \"区域代理\"}, {\"sub\": [{\"path\": \"/org-dept/list\", \"title\": \"部门列表\", \"accessRuleId\": 9999}, {\"path\": \"/org-job/list\", \"title\": \"职位列表\", \"accessRuleId\": 9999}, {\"path\": \"/org-staff/list\", \"title\": \"职员列表\", \"accessRuleId\": 9999}], \"icon\": \"&#xe62b;\", \"path\": \"/org\", \"title\": \"组织架构\", \"isCoreIcon\": true}, {\"sub\": [{\"path\": \"/account/profile\", \"title\": \"用户资料\"}, {\"path\": \"/account/setting\", \"title\": \"账户设置\"}], \"icon\": \"&#xe649;\", \"path\": \"/account\", \"title\": \"账户中心\", \"isCoreIcon\": true}]', '{\"ma\": {\"modules\": {\"form\": {\"orderAdd\": {\"path\": \"formOrder/add\", \"method\": \"POST\"}, \"orderInfo\": {\"path\": \"formOrder/info\", \"method\": \"POST\"}, \"orderPage\": {\"path\": \"formOrder/page\", \"method\": \"POST\"}}, \"page\": {\"info\": {\"path\": \"page/info\", \"method\": \"POST\"}}, \"member\": {\"add\": {\"path\": \"member/add\", \"method\": \"POST\"}, \"info\": {\"path\": \"member/info\", \"method\": \"POST\"}, \"page\": {\"path\": \"member/page\", \"method\": \"POST\"}, \"update\": {\"path\": \"member/update\", \"method\": \"POST\"}, \"wxLogin\": {\"path\": \"member/wxLogin\", \"method\": \"POST\"}, \"addressAdd\": {\"path\": \"memberAddress/add\", \"method\": \"POST\"}, \"addressInfo\": {\"path\": \"memberAddress/info\", \"method\": \"POST\"}, \"addressPage\": {\"path\": \"memberAddress/page\", \"method\": \"POST\"}, \"addressDelete\": {\"path\": \"memberAddress/delete\", \"method\": \"POST\"}, \"addressUpdate\": {\"path\": \"memberAddress/update\", \"method\": \"POST\"}}}, \"hostname\": \"http://localhost:4002/\"}, \"web\": {\"modules\": {\"org\": {\"deptAdd\": {\"path\": \"org/deptAdd\", \"method\": \"POST\"}, \"jobPage\": {\"path\": \"org/jobPage\", \"method\": \"POST\"}, \"deptInfo\": {\"path\": \"org/deptInfo\", \"method\": \"POST\"}, \"deptPage\": {\"path\": \"org/deptPage\", \"method\": \"POST\"}, \"staffInfo\": {\"path\": \"org/staffInfo\", \"method\": \"POST\"}, \"staffPage\": {\"path\": \"org/staffPage\", \"method\": \"POST\"}, \"deptUpdate\": {\"path\": \"org/deptUpdate\", \"method\": \"POST\"}, \"staffLogin\": {\"path\": \"org/staffLogin\", \"method\": \"POST\"}, \"staffLogout\": {\"path\": \"org/staffLogout\", \"method\": \"POST\"}, \"accessRuleList\": {\"path\": \"org/accessRuleList\", \"method\": \"POST\"}, \"staffAccessRuleList\": {\"path\": \"org/staffAccessRuleList\", \"method\": \"POST\"}}, \"form\": {\"orderAdd\": {\"path\": \"form/orderAdd\", \"method\": \"POST\"}, \"orderInfo\": {\"path\": \"form/orderInfo\", \"method\": \"POST\"}, \"orderPage\": {\"path\": \"form/orderPage\", \"method\": \"POST\"}, \"orderUpdate\": {\"path\": \"form/orderUpdate\", \"method\": \"POST\"}}, \"page\": {\"info\": {\"path\": \"page/info\", \"method\": \"POST\"}}, \"member\": {\"page\": {\"path\": \"member/page\", \"method\": \"POST\"}}, \"approve\": {\"add\": {\"path\": \"approve/add\", \"method\": \"POST\"}, \"info\": {\"path\": \"approve/info\", \"method\": \"POST\"}, \"page\": {\"path\": \"approve/page\", \"method\": \"POST\"}, \"doAgree\": {\"path\": \"approve/doAgree\", \"method\": \"POST\"}, \"typeAdd\": {\"path\": \"approve/typeAdd\", \"method\": \"POST\"}, \"typeInfo\": {\"path\": \"approve/typeInfo\", \"method\": \"POST\"}, \"typePage\": {\"path\": \"approve/typePage\", \"method\": \"POST\"}, \"typeUpdate\": {\"path\": \"approve/typeUpdate\", \"method\": \"POST\"}}, \"product\": {\"add\": {\"path\": \"product/add\", \"method\": \"POST\"}, \"info\": {\"path\": \"product/info\", \"method\": \"POST\"}, \"page\": {\"path\": \"product/page\", \"method\": \"POST\"}}, \"logistic\": {\"add\": {\"path\": \"logistic/add\", \"method\": \"POST\"}}}, \"hostname\": \"//localhost:4001/\"}, \"open\": {\"modules\": {\"sys\": {\"client\": {\"path\": \"sys/client\", \"method\": \"POST\"}}, \"member\": {\"inviteCode\": {\"path\": \"member/inviteCode\", \"method\": \"POST\"}}}, \"hostname\": \"http://localhost:4000/\"}}', '{\"User\": {\"fields\": {\"mobile\": {\"type\": \"string\", \"default\": \"\"}}, \"columns\": [{\"index\": 1, \"value\": {\"key\": \"sex\", \"label\": \"性别\"}}, {\"index\": 2, \"value\": {\"key\": \"nation\", \"label\": \"国籍\"}}]}}', 0, '2022-10-04 18:28:43', NULL, NULL);

-- ----------------------------
-- Table structure for sys_app_version
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_version`;
CREATE TABLE `sys_app_version`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '1->admin；2->shop；3->ma',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `update_start_time` datetime NULL DEFAULT NULL,
  `update_end_time` datetime NULL DEFAULT NULL,
  `content_fixed` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `content_new` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `is_end` int(11) NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_app_version
-- ----------------------------
INSERT INTO `sys_app_version` VALUES (1, 1, '1.1.0', '2021-10-03 14:16:55', '2021-10-03 14:17:12', '<p>1、修复支付弹出失败问题</p><p>2、修复其他已知bug</p>', '<p>1、支持自定义订单下载预售库存</p>', 1, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_entity
-- ----------------------------
DROP TABLE IF EXISTS `sys_entity`;
CREATE TABLE `sys_entity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `config` json NULL,
  `form` json NULL,
  `detail` json NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_entity
-- ----------------------------
INSERT INTO `sys_entity` VALUES (1, 'Approve', '{\"enumValues\": [{\"key\": \"status\", \"list\": [{\"color\": \"#333\", \"label\": \"保存\", \"value\": \"save\"}, {\"color\": \"#2d8cf0\", \"label\": \"审核中\", \"value\": \"pendding\"}, {\"color\": \"#2d8cf0\", \"label\": \"待上刊\", \"value\": \"wait\"}]}]}', '{\"update\": {\"config\": {\"step\": [], \"type\": \"default\"}, \"fields\": [{\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"名称\"}, {\"key\": \"remark\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"备注\"}, {\"key\": \"approveNodeList\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": [], \"require\": true}, \"type\": \"array\", \"label\": \"审批节点\", \"config\": {\"template\": [{\"key\": \"id\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"id\"}, {\"key\": \"sort\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"排序\"}, {\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"节点\"}, {\"key\": \"orgDeptIds\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"部门\"}, {\"key\": \"orgStaffIds\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true}, \"type\": \"text\", \"label\": \"人员\"}]}}]}, \"doAgree\": {\"config\": {\"step\": [], \"type\": \"default\"}, \"fields\": [{\"key\": \"remark\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": false, \"errorTip\": \"请输入备注\"}, \"type\": \"textarea\", \"label\": \"备注\"}, {\"key\": \"attach\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": false}, \"type\": \"file\", \"label\": \"附件\"}]}}', NULL, 0, '2022-09-30 23:22:33', NULL, NULL);
INSERT INTO `sys_entity` VALUES (3, 'OrderForm', '{\"enumValues\": [{\"key\": \"status\", \"list\": [{\"color\": \"#2d8cf0\", \"label\": \"待下单\", \"value\": \"initial\"}, {\"color\": \"#1890ff\", \"label\": \"已下单\", \"value\": \"placed\"}, {\"color\": \"#ff4949\", \"label\": \"已退单\", \"value\": \"refund\"}]}]}', '{\"KDadd\": {\"config\": {\"type\": \"section\", \"section\": [{\"col\": 3, \"field\": [\"orderFormId\", \"status\", \"fileJson\", \"remark\"], \"title\": \"步骤一\"}, {\"col\": 1, \"field\": [\"publishMediaItemsList\"], \"title\": \"添加广告位\"}]}, \"fields\": [{\"key\": \"amountEstimate\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": false}, \"type\": \"text\", \"label\": \"预估费用\"}, {\"key\": \"logistic\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"物流\"}, {\"key\": \"objectType\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"物品类型\"}, {\"key\": \"objectWeight\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"物品重量\"}, {\"key\": \"takeTime\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": false}, \"type\": \"text\", \"label\": \"取件时间\"}, {\"key\": \"secure\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": false}, \"type\": \"text\", \"label\": \"保价\"}, {\"key\": \"note\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": false}, \"type\": \"text\", \"label\": \"留言\"}, {\"key\": \"senderProvince\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件省份\"}, {\"key\": \"senderCity\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件市区\"}, {\"key\": \"senderDistrict\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件县区\"}, {\"key\": \"senderDetail\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件详细地址\"}, {\"key\": \"senderRealName\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件人\"}, {\"key\": \"senderMobile\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"寄件手机号\"}, {\"key\": \"receiverProvince\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人省份\"}, {\"key\": \"receiverCity\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人市区\"}, {\"key\": \"receiverDistrict\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人县区\"}, {\"key\": \"receiverDetail\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人详细地址\"}, {\"key\": \"receiverRealName\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人\"}, {\"key\": \"receiverMobile\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"收件人手机号\"}]}, \"CHFadd\": {\"config\": {\"type\": \"section\", \"section\": [{\"col\": 3, \"field\": [\"orderFormId\", \"status\", \"fileJson\", \"remark\"], \"title\": \"步骤一\"}, {\"col\": 1, \"field\": [\"publishMediaItemsList\"], \"title\": \"添加广告位\"}]}, \"fields\": [{\"key\": \"account\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"号码\"}, {\"key\": \"amount\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true}, \"type\": \"text\", \"label\": \"金额\"}]}}', '{\"info\": {\"config\": {\"type\": \"section\", \"section\": [{\"col\": 3, \"field\": [\"status\", \"password\", \"loginTime\", \"isAutoLogin\", \"fileJson\", \"remark\"], \"title\": \"上刊详情\"}, {\"col\": 1, \"field\": [\"publishMediaItemsList\"], \"title\": \"广告位\"}]}, \"fields\": [{\"key\": \"status\", \"type\": \"text\", \"label\": \"状态\"}, {\"key\": \"password\", \"type\": \"text\", \"label\": \"密码\"}, {\"key\": \"loginTime\", \"path\": \"infoData.loginTime\", \"type\": \"text\", \"label\": \"登录时间\"}, {\"key\": \"isAutoLogin\", \"type\": \"ratio\", \"label\": \"是否记住密码\"}, {\"key\": \"fileJson\", \"type\": \"file\", \"label\": \"附件\"}, {\"key\": \"remark\", \"type\": \"textarea\", \"label\": \"备注\"}, {\"key\": \"publishMediaItemsList\", \"type\": \"array\", \"label\": \"登录记录\", \"config\": {\"template\": [{\"key\": \"ip\", \"type\": \"text\", \"label\": \"ip\"}, {\"key\": \"time\", \"type\": \"text\", \"label\": \"登录时间\"}, {\"key\": \"img\", \"type\": \"file\", \"label\": \"截图\"}]}}]}}', 0, '2023-08-02 18:26:16', NULL, NULL);
INSERT INTO `sys_entity` VALUES (4, 'OrgDept', NULL, '{\"add\": {\"config\": {\"step\": [], \"type\": \"default\"}, \"fields\": [{\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true, \"errorTip\": \"请输入账号\"}, \"type\": \"text\", \"label\": \"部门名称\"}, {\"key\": \"status\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": false}, \"type\": \"ratio\", \"label\": \"是否启用\", \"config\": {\"ratio\": [{\"label\": \"是\", \"value\": 1}, {\"label\": \"否\", \"value\": 0}]}}]}, \"update\": {\"config\": {\"step\": [], \"type\": \"default\"}, \"fields\": [{\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true, \"errorTip\": \"请输入账号\"}, \"type\": \"text\", \"label\": \"部门名称\"}, {\"key\": \"status\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": false}, \"type\": \"ratio\", \"label\": \"是否启用\", \"config\": {\"ratio\": [{\"label\": \"是\", \"value\": 1}, {\"label\": \"否\", \"value\": 0}]}}]}}', NULL, 0, '2023-11-02 15:23:29', NULL, NULL);
INSERT INTO `sys_entity` VALUES (5, 'Logistic', NULL, '{\"add\": {\"config\": {\"step\": [], \"type\": \"default\"}, \"fields\": [{\"key\": \"sn\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true, \"errorTip\": \"请输入物流单号\"}, \"type\": \"text\", \"label\": \"物流单号\"}]}}', NULL, 0, '2023-11-02 15:23:31', NULL, NULL);
INSERT INTO `sys_entity` VALUES (6, 'Product', NULL, '{\"add\": {\"config\": {\"step\": [{\"field\": [\"name\", \"amount\", \"description\", \"thumbnail\", \"status\"], \"title\": \"创建产品\"}, {\"field\": [\"productSKUList\"], \"title\": \"创建SKU\"}], \"type\": \"step\"}, \"fields\": [{\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true, \"errorTip\": \"请输入名称\"}, \"type\": \"text\", \"label\": \"名称\"}, {\"key\": \"amount\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true, \"errorTip\": \"请输入金额\"}, \"type\": \"text\", \"label\": \"金额\"}, {\"key\": \"description\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": true, \"errorTip\": \"请输入描述\"}, \"type\": \"text\", \"label\": \"描述\"}, {\"key\": \"thumbnail\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"isJson\": true, \"keyPath\": \"infoData\", \"require\": false}, \"tip\": \"仅支持上传PNG,PDF,EXCEL\", \"type\": \"file\", \"label\": \"缩略图\"}, {\"key\": \"status\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"keyPath\": \"infoData\", \"require\": false}, \"type\": \"ratio\", \"label\": \"是否上架\", \"config\": {\"ratio\": [{\"label\": \"是\", \"value\": \"posted\"}, {\"label\": \"否\", \"value\": \"created\"}]}}, {\"key\": \"productSKUList\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": [], \"require\": false}, \"type\": \"array\", \"label\": \"SKU\", \"config\": {\"template\": [{\"key\": \"name\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true, \"errorTip\": \"请输入名称\"}, \"type\": \"text\", \"label\": \"名称\"}, {\"key\": \"stock\", \"opt\": {\"skip\": false, \"type\": \"string|min:4|max:12\", \"value\": \"\", \"require\": true, \"errorTip\": \"请输入库存\"}, \"type\": \"text\", \"label\": \"库存\"}]}}]}}', NULL, 0, '2023-11-02 15:23:31', NULL, NULL);

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time` ASC) USING BTREE,
  INDEX `I_handle_code`(`handle_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group` ASC, `registry_key` ASC, `registry_value` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
