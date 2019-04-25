/*
Navicat MySQL Data Transfer

Source Server         : 我的阿里云
Source Server Version : 50643
Source Host           : 47.98.37.85:3306
Source Database       : genealogy

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-04-25 09:40:24
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for personnel
-- ----------------------------
DROP TABLE IF EXISTS `personnel`;
CREATE TABLE `personnel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `zi` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '字',
  `bei` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '辈',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0-女、1-男',
  `parentId` int(11) DEFAULT NULL COMMENT '父亲或母亲ID',
  `coupleId` int(11) DEFAULT NULL COMMENT '当前配偶ID',
  `generation` int(2) DEFAULT NULL COMMENT '世代-自动计算',
  `ranking` int(2) DEFAULT NULL COMMENT '排行：1、2、3、4、5、6、7',
  `is_living` int(1) DEFAULT NULL COMMENT '是否在世：0-在世、1-去世',
  `birth_date` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '出生日期',
  `death_date` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '去世日期',
  `degree` int(1) DEFAULT NULL COMMENT '学位：1、小学；2、初中；3、高中；4、中专；5、大专；6、本科；7、硕士；8、博士',
  `university` varchar(55) COLLATE utf8_bin DEFAULT NULL COMMENT '毕业学校',
  `work` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '工作',
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像URL',
  `delete_status` int(1) DEFAULT NULL COMMENT '状态：0-正常、1-删除',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of personnel
-- ----------------------------
INSERT INTO `personnel` VALUES ('1', '张文汉', '', '卓庵', '', '1', '0', null, '1', null, '1', '', '', null, '', '', '', '', '始祖', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('2', '李氏', '', '', '', '0', null, '1', '1', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('3', '张权', '', '', '', '1', '1', null, '2', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('4', '赵氏', '', '', '', '0', null, '3', '2', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('5', '张考', '', '', '', '1', '1', null, '2', '2', '1', '', '', null, '', '', '', '', '无子，续侄振友承嗣', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('6', '陈氏', '', '', '', '0', null, '5', '2', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('7', '张柏', '', '凌寒', '', '1', '1', null, '2', '3', '1', '', '', null, '', '', '', '', '子三 长富友 次振友(出继) 三圣友(出继)', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('8', '仉氏', '', '', '', '0', null, '7', '2', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('9', '张松', '', '', '', '1', '1', null, '2', '4', '1', '', '', null, '', '', '', '', '无子，续侄圣友承嗣', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('10', '吴氏', '', '', '', '0', null, '9', '2', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('11', '张期朋', '', '', '', '1', '3', null, '3', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('12', '章氏', '', '', '', '0', null, '11', '3', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('13', '张期友', '', '', '', '1', '3', null, '3', '2', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('14', '孙氏', '', '', '', '0', null, '13', '3', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('15', '张振友', '', '建安', '', '1', '5', null, '3', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('16', '程氏', '', '', '', '0', null, '15', '3', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('17', '张富友', '', '仁辅', '', '1', '7', null, '3', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('18', '王氏', '', '', '', '0', null, '17', '3', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('19', '张圣友', '', '', '', '1', '9', null, '3', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('20', '薛氏', '', '', '', '0', null, '19', '3', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('21', '张端', '', '', '', '1', '11', null, '4', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('22', '侯氏', '', '', '', '0', null, '21', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('23', '张重', '', '', '', '1', '13', null, '4', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('24', '章氏', '', '', '', '0', null, '23', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('25', '张德', '', '', '', '1', '15', null, '4', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('26', '杨氏', '', '', '', '0', null, '25', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('27', '张丙', '', '', '', '1', '15', null, '4', '2', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('28', '孙氏', '', '', '', '0', null, '27', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('29', '张纯', '', '心服', '', '1', '15', null, '4', '3', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('30', '侯氏', '', '', '', '0', null, '29', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('31', '张普', '', '惠远', '', '1', '17', null, '4', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('32', '岳氏', '', '', '', '0', null, '31', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('33', '张正', '', '', '', '1', '17', null, '4', '2', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('34', '薛氏', '', '', '', '0', null, '33', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('35', '张本', '', '', '', '1', '19', null, '4', '1', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('36', '仉氏', '', '', '', '0', null, '35', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('37', '张俊', '', '秀生', '', '1', '19', null, '4', '2', '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');
INSERT INTO `personnel` VALUES ('38', '卢氏', '', '', '', '0', null, '37', '4', null, '1', '', '', null, '', '', '', '', '', null, '0', '2019-04-17', '2019-04-17');

-- ----------------------------
-- Table structure for platform_user
-- ----------------------------
DROP TABLE IF EXISTS `platform_user`;
CREATE TABLE `platform_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_show_name` varchar(50) NOT NULL COMMENT '用户显示名称',
  `login_name` varchar(50) NOT NULL COMMENT '登录用户名',
  `password` varchar(50) NOT NULL COMMENT '登录密码. 密码一律用不可逆加密形式',
  `user_status` int(2) NOT NULL COMMENT '用户状态，0-正常，1-禁用',
  `user_type` int(2) NOT NULL COMMENT '用户类型，0-管理员，1-浏览用户',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_user
-- ----------------------------
INSERT INTO `platform_user` VALUES ('1', '管理员', 'admin', '34e26a2c509d37d6d6bec0e51572ab4d', '0', '0', '2019-04-22 03:27:17', '2019-04-17 09:56:12', '2019-04-22 03:27:17', null);
INSERT INTO `platform_user` VALUES ('2', '浏览用户', 'view', '6838f02691e22b3e6bc338e61f647d52', '0', '1', '2019-04-23 05:25:55', '2019-04-17 09:56:12', '2019-04-23 05:25:55', '');
