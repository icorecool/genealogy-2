/*
Navicat MySQL Data Transfer

Source Server         : 我的阿里云
Source Server Version : 80013
Source Host           : 47.98.37.85:3306
Source Database       : genealogy

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-03-04 10:08:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0-女、1-男',
  `zi` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '字',
  `bei` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '辈',
  `fatherId` int(11) DEFAULT NULL COMMENT '父亲ID',
  `generation` int(2) DEFAULT NULL COMMENT '第几代',
  `ranking` int(2) DEFAULT NULL COMMENT '排行，长、次、三、四、五、六',
  `is_living` int(1) DEFAULT NULL COMMENT '是否在世：0-在世、1-去世',
  `birth_time` date DEFAULT NULL COMMENT '出生日期',
  `death_time` date DEFAULT NULL COMMENT '死亡日期',
  `degree` int(1) DEFAULT NULL COMMENT '学位：1、小学；2、初中；3、高中；4、中专；5、大专；6、本科；7、硕士；8、博士',
  `university` varchar(55) COLLATE utf8_bin DEFAULT NULL COMMENT '毕业学校',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '工作',
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `husbandId` int(11) DEFAULT NULL COMMENT '丈夫ID，如果不为空，则为妻子',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像URL',
  `delete_status` int(1) DEFAULT NULL COMMENT '状态：0-正常、1-删除',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of family
-- ----------------------------

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件路径',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名称',
  `create_userid` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_showname` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人显示名字',
  `delete_status` int(1) DEFAULT NULL COMMENT '状态：0-正常；1-删除',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of files
-- ----------------------------

-- ----------------------------
-- Table structure for platform_user
-- ----------------------------
DROP TABLE IF EXISTS `platform_user`;
CREATE TABLE `platform_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_show_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户显示名称',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码. 密码一律用不可逆加密形式',
  `user_status` int(2) NOT NULL COMMENT '用户状态，0-正常，1-禁用',
  `user_type` int(2) NOT NULL COMMENT '用户类型，0-管理员，1-普通运营人员',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_user
-- ----------------------------
INSERT INTO `platform_user` VALUES ('1', '张超', 'zhangchao', '1e71800aea64ea37b6e7b54a42f1aa70', '0', '0', '2019-03-04 01:47:23', '2019-03-04 09:41:39', '2019-03-04 01:47:23', null);
