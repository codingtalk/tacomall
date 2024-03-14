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

 Date: 14/03/2024 16:57:05
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
-- Table structure for ma_carousel
-- ----------------------------
DROP TABLE IF EXISTS `ma_carousel`;
CREATE TABLE `ma_carousel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ma_carousel
-- ----------------------------
INSERT INTO `ma_carousel` VALUES (1, 'product', '美味汉堡', '//m15.360buyimg.com/mobilecms/s1062x420_jfs/t1/231060/5/3007/80337/6551bcd1Fad345a22/e35654a80c5b86fd.png!cr_1053x420_4_0', 0, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_staff_login_log
-- ----------------------------
INSERT INTO `org_staff_login_log` VALUES (152, NULL, '0:0:0:0:0:0:0:1', 0, '2024-02-28 15:32:56', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (153, NULL, '0:0:0:0:0:0:0:1', 0, '2024-02-29 15:42:17', NULL, NULL);
INSERT INTO `org_staff_login_log` VALUES (154, NULL, '0:0:0:0:0:0:0:1', 0, '2024-02-29 21:46:42', NULL, NULL);

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
  `classification_id` int(11) NULL DEFAULT NULL,
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
INSERT INTO `product` VALUES (1, NULL, '汉堡', NULL, '{\"amount\": \"120\", \"status\": \"posted\", \"thumbnail\": \"\", \"description\": \"好吃汉堡\"}', NULL, 0, '2023-11-16 21:48:06', NULL, NULL);

-- ----------------------------
-- Table structure for product_classification
-- ----------------------------
DROP TABLE IF EXISTS `product_classification`;
CREATE TABLE `product_classification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_delete` int(11) NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_classification
-- ----------------------------
INSERT INTO `product_classification` VALUES (1, 0, '热门', 0, NULL, NULL, NULL);
INSERT INTO `product_classification` VALUES (2, 0, '冬季保暖', 0, NULL, NULL, NULL);
INSERT INTO `product_classification` VALUES (3, 0, '潮流靴子', 0, NULL, NULL, NULL);
INSERT INTO `product_classification` VALUES (4, 0, '家电产品', 0, NULL, NULL, NULL);

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
