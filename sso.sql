/*
Navicat MySQL Data Transfer

Source Server         : local_sso
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : sso

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-12-18 19:19:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', '91440300MA5EUCXT22', '金荣中国金融业有限公司', '2018-12-17 14:45:54');
INSERT INTO `t_company` VALUES ('2', '91440300321697996H', '深圳前海金道贵金属有限公司', null);

-- ----------------------------
-- Table structure for t_info_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_info_permission`;
CREATE TABLE `t_info_permission` (
  `info_perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权表名',
  `code` varchar(120) DEFAULT NULL COMMENT '授权表',
  `perm_column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权列',
  `perm_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权列描述',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`info_perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_info_permission
-- ----------------------------
INSERT INTO `t_info_permission` VALUES ('1', '报价表', 't_quoted', 'product_name,price,price_date', '产品名称,价格,价格日期', '2018-12-18 18:22:14');
INSERT INTO `t_info_permission` VALUES ('2', '信息共享', null, null, null, null);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL COMMENT '菜单',
  `icon` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL COMMENT '权限名',
  `code` varchar(120) DEFAULT NULL COMMENT '权限编码 规则：（模块名:操作方法）',
  `parent_id` int(11) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `product_id` int(11) NOT NULL,
  `name` varchar(120) DEFAULT NULL,
  `code` varchar(120) DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `product_type_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '成色不低于99.95％黄金', 'AUX.CNY', '经交易所认定的可提供标准金锭、金条企业生产的符合交易所金锭SGEB1-2002、金条SGEB2-2004质量标准的实物，及伦敦金银市场协会（LBMA）认定的合格供货商生产的标准实物。', '1', '2018-12-17 18:22:42');

-- ----------------------------
-- Table structure for t_product_type
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_type
-- ----------------------------
INSERT INTO `t_product_type` VALUES ('1', '贵金属', '2018-12-17 14:57:06');

-- ----------------------------
-- Table structure for t_quoted
-- ----------------------------
DROP TABLE IF EXISTS `t_quoted`;
CREATE TABLE `t_quoted` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(120) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `visit_start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '授权访问开始时间',
  `visit_end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '授权访问结束时间',
  `state` tinyint(4) DEFAULT NULL COMMENT '0-创建，1-已上链',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '0-有效 ，1-已删除',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_quoted
-- ----------------------------
INSERT INTO `t_quoted` VALUES ('1', '1', '1', '成色不低于99.95％黄金', '0.40', '2018-12-18 15:18:35', '2018-12-18 15:18:35', '2018-12-18 15:18:35', '0', '1', '2018-12-18 15:18:35');
INSERT INTO `t_quoted` VALUES ('2', '1', '1', '成色不低于99.95％黄金', '10.23', '2018-12-18 15:18:36', '2018-12-18 15:18:36', '2018-12-18 15:18:36', '0', '0', '2018-12-18 15:18:36');
INSERT INTO `t_quoted` VALUES ('3', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:37', '2018-12-18 15:18:37', '2018-12-18 15:18:37', '0', '0', '2018-12-18 15:18:37');
INSERT INTO `t_quoted` VALUES ('4', '1', '1', '成色不低于99.95％黄金', '0.50', '2018-12-18 15:18:38', '2018-12-18 15:18:38', '2018-12-18 15:18:38', '0', '0', '2018-12-18 15:18:38');
INSERT INTO `t_quoted` VALUES ('5', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:38', '2018-12-18 15:18:38', '2018-12-18 15:18:38', '0', '0', '2018-12-18 15:18:38');
INSERT INTO `t_quoted` VALUES ('6', '1', '1', '成色不低于99.95％黄金', '0.20', '2018-12-18 15:18:39', '2018-12-18 15:18:39', '2018-12-18 15:18:39', '0', '0', '2018-12-18 15:18:39');
INSERT INTO `t_quoted` VALUES ('7', '1', '1', '成色不低于99.95％黄金', '0.00', '2018-12-18 15:18:40', '2018-12-18 15:18:40', '2018-12-18 15:18:40', '0', '0', '2018-12-18 15:18:40');
INSERT INTO `t_quoted` VALUES ('8', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:41', '2018-12-18 15:18:41', '2018-12-18 15:18:41', '0', '0', '2018-12-18 15:18:41');
INSERT INTO `t_quoted` VALUES ('9', '1', '1', '成色不低于99.95％黄金', '1.10', '2018-12-18 15:18:42', '2018-12-18 15:18:42', '2018-12-18 15:18:42', '0', '0', '2018-12-18 15:18:42');
INSERT INTO `t_quoted` VALUES ('10', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:42', '2018-12-18 15:18:42', '2018-12-18 15:18:42', '0', '0', '2018-12-18 15:18:42');
INSERT INTO `t_quoted` VALUES ('12', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:44', '2018-12-18 15:18:44', '2018-12-18 15:18:44', '0', '0', '2018-12-18 15:18:44');
INSERT INTO `t_quoted` VALUES ('13', '1', '1', '成色不低于99.95％黄金', '0.00', '2018-12-18 15:18:45', '2018-12-18 15:18:45', '2018-12-18 15:18:45', '0', '1', '2018-12-18 15:18:45');
INSERT INTO `t_quoted` VALUES ('14', '1', '1', '成色不低于99.95％黄金', '0.00', '2018-12-18 15:18:46', '2018-12-18 15:18:46', '2018-12-18 15:18:46', '0', '1', '2018-12-18 15:18:46');
INSERT INTO `t_quoted` VALUES ('15', '1', '1', '成色不低于99.95％黄金', '0.30', '2018-12-18 15:18:47', '2018-12-18 15:18:47', '2018-12-18 15:18:47', '0', '0', '2018-12-18 15:18:47');
INSERT INTO `t_quoted` VALUES ('16', '1', '1', '成色不低于99.95％黄金', '0.50', '2018-12-18 15:18:48', '2018-12-18 15:18:48', '2018-12-18 15:18:48', '0', '0', '2018-12-18 15:18:48');
INSERT INTO `t_quoted` VALUES ('17', '1', '1', '成色不低于99.95％黄金', '0.00', '2018-12-18 15:18:50', '2018-12-18 15:18:50', '2018-12-18 15:18:50', '0', '0', '2018-12-18 15:18:50');

-- ----------------------------
-- Table structure for t_read_log
-- ----------------------------
DROP TABLE IF EXISTS `t_read_log`;
CREATE TABLE `t_read_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '阅读者ID',
  `product_type_id` int(11) DEFAULT NULL COMMENT '产品类型',
  `product_info_id` int(11) DEFAULT NULL COMMENT '产品消息ID',
  `from_company_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_read_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '发布者', '2018-12-18 17:18:02');
INSERT INTO `t_role` VALUES ('2', '阅读者', '2018-12-18 17:18:10');
INSERT INTO `t_role` VALUES ('3', '监管者', '2018-12-18 17:18:22');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Idx_roleId` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Idx_roleId` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_system_constant
-- ----------------------------
DROP TABLE IF EXISTS `t_system_constant`;
CREATE TABLE `t_system_constant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `value` varchar(64) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_constant
-- ----------------------------
INSERT INTO `t_system_constant` VALUES ('1', 'publish_role_id', '2', null, null);
INSERT INTO `t_system_constant` VALUES ('2', 'read_role_id', '3', null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `salt` varchar(40) NOT NULL COMMENT '盐值',
  `name` varchar(40) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `is_delete` tinyint(4) NOT NULL COMMENT '0-有效, 1-删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `Idx_email` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1056196129@qq.com', '1949c7f6e2127bb1ce52a14516b38f25a4e41731', 'ca42e099bf6c45b990a4a323cc82eb24', '张磊', '15989305930', null, '0', '2018-12-03 15:47:50');
INSERT INTO `t_user` VALUES ('2', 'jrzg', '38312cb5b3ee862eba514f7ebc97f82ca16df3f4', 'ffb737f746414aaebe28c8f54ed61047', '金荣', '13678945621', '1', '0', '2018-12-17 15:10:02');
INSERT INTO `t_user` VALUES ('3', 'jdgjs', 'f4bd672cf8c19fc4438597f19a046fa979431a47', '056e91b485934d728c139a444bab8691', '金道', '13578954635', '2', '0', '2018-12-17 15:10:56');

-- ----------------------------
-- Table structure for t_user_info_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info_permission`;
CREATE TABLE `t_user_info_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `from_company_id` int(11) DEFAULT NULL,
  `to_company_id` int(11) DEFAULT NULL,
  `info_perm_id` int(11) DEFAULT NULL,
  `perm_column` varchar(255) DEFAULT NULL,
  `perm_column_desc` varchar(255) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info_permission
-- ----------------------------
INSERT INTO `t_user_info_permission` VALUES ('1', '2', '1', '2', '1', 'product_name,price,price_date', '产品名称,价格,价格日期', '0', '2018-12-18 18:29:23');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Idx_userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('2', '2', '1');
INSERT INTO `t_user_role` VALUES ('3', '3', '2');
