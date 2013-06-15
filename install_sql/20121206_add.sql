
delete from pl_role_limit where m_id in ('dh0002', 'dh0003','dh0004','dh0005','dh0006','dh0007');
delete from pl_user_limit where m_id in ('dh0002', 'dh0003','dh0004','dh0005','dh0006','dh0007');
delete from pl_menu where m_id in ('dh0002', 'dh0003','dh0004','dh0005');


delete from pl_role_limit where m_id in ('xs0002', 'xs0003','xs0004');
delete from pl_user_limit where m_id in ('xs0002', 'xs0003','xs0004');
delete from pl_menu where m_id in ('xs0002', 'xs0003','xs0004');

delete from pl_role_limit where m_id like 'sc%';
delete from pl_role_limit where m_id like 'ddrb%';
delete from pl_role_limit where m_id like 'dh%';
delete from pl_role_limit where m_id like 'cl%';
delete from pl_role_limit where m_id like 'rxh%';
delete from pl_role_limit where m_id like 'sczs%';
delete from pl_role_limit where m_id like 'xs%';
delete from pl_role_limit where m_id like 'bq%';
delete from pl_role_limit where m_id like 'gyjc%';
delete from pl_role_limit where m_id like 'gyrz%';
delete from pl_role_limit where m_id like 'sb%';
delete from pl_role_limit where m_id like 'yxsj%';
delete from pl_role_limit where m_id like 'sbzt%';
delete from pl_role_limit where m_id like 'sbjx%';
delete from pl_role_limit where m_id like 'zlgl%';
delete from pl_role_limit where m_id like 'djrz%';

delete from pl_user_limit where m_id like 'sc%';
delete from pl_user_limit where m_id like 'ddrb%';
delete from pl_user_limit where m_id like 'dh%';
delete from pl_user_limit where m_id like 'cl%';
delete from pl_user_limit where m_id like 'rxh%';
delete from pl_user_limit where m_id like 'sczs%';
delete from pl_user_limit where m_id like 'xs%';
delete from pl_user_limit where m_id like 'bq%';
delete from pl_user_limit where m_id like 'gyjc%';
delete from pl_user_limit where m_id like 'gyrz%';
delete from pl_user_limit where m_id like 'sb%';
delete from pl_user_limit where m_id like 'yxsj%';
delete from pl_user_limit where m_id like 'sbzt%';
delete from pl_user_limit where m_id like 'sbjx%';
delete from pl_user_limit where m_id like 'zlgl%';
delete from pl_user_limit where m_id like 'djrz%';

DROP VIEW IF EXISTS `pl_limit_v`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pl_limit_v` AS select `a`.`username` AS `username`,`b`.`m_id` AS `m_id`,`b`.`m_name` AS `m_name`,`b`.`m_url` AS `m_url`,`b`.`m_title` AS `m_title`,`b`.`m_type` AS `m_type`,`b`.`m_super` AS `m_super`,`b`.`m_order` AS `m_order` from (`pl_user_limit` `a` join `pl_menu` `b`) where (`a`.`m_id` = `b`.`m_id`) union select `a`.`username` AS `username`,`c`.`m_id` AS `m_id`,`c`.`m_name` AS `m_name`,`c`.`m_url` AS `m_url`,`c`.`m_title` AS `m_title`,`c`.`m_type` AS `m_type`,`c`.`m_super` AS `m_super`,`c`.`m_order` AS `m_order` from ((`pl_user` `a` join `pl_role_limit` `b`) join `pl_menu` `c`) where ((`b`.`m_id` = `c`.`m_id`) and (`a`.`role_id` = `b`.`role_id`)) order by `username`,`m_order` desc;

/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : zxhg

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2012-12-18 08:48:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sc_cl_jh`
-- ----------------------------
DROP TABLE IF EXISTS `sc_cl_jh`;
CREATE TABLE `sc_cl_jh` (
  `jhyf` varchar(7) NOT NULL COMMENT '计划月份(yyyy-mm)',
  `cpbh` varchar(3) NOT NULL COMMENT '产品编号',
  `yzb` double NOT NULL COMMENT '月指标',
  `rzb` double NOT NULL COMMENT '日指标(月指标除天数)',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`jhyf`,`cpbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_ddkb`
-- ----------------------------
DROP TABLE IF EXISTS `sc_ddkb`;
CREATE TABLE `sc_ddkb` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `rbnr` longtext NOT NULL COMMENT '生产日报内容',
  `bcr` varchar(20) DEFAULT NULL COMMENT '保存人',
  `bcrxm` varchar(20) DEFAULT NULL,
  `bcsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rcl`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rcl`;
CREATE TABLE `sc_rcl` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `cpbh` varchar(3) NOT NULL COMMENT '产品编号',
  `bc` varchar(1) NOT NULL COMMENT '班次',
  `cl` double NOT NULL COMMENT '产量',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`,`cpbh`,`bc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rcqkc`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rcqkc`;
CREATE TABLE `sc_rcqkc` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `cpbh` varchar(3) NOT NULL COMMENT '产品编号',
  `kc` double DEFAULT NULL COMMENT '库存',
  `cqe` double DEFAULT NULL COMMENT '超欠额',
  `lrr` varchar(20) DEFAULT NULL COMMENT '库存录入人',
  `lrrxm` varchar(20) DEFAULT NULL COMMENT '库存录入人',
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`,`cpbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rdh`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rdh`;
CREATE TABLE `sc_rdh` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `hym` double NOT NULL COMMENT '精甲醇(t)耗原煤',
  `hrm` double NOT NULL COMMENT '精甲醇(t)耗燃煤',
  `hs` double NOT NULL COMMENT '精甲醇(t)耗水',
  `hwd` double NOT NULL COMMENT '精甲醇(t)耗外电',
  `hq` double NOT NULL COMMENT '吨甲醇耗气',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rwg`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rwg`;
CREATE TABLE `sc_rwg` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `jjc` double NOT NULL COMMENT '日进精甲醇(t)',
  `cjc` double NOT NULL COMMENT '日进粗甲醇(t)',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_rwg
-- ----------------------------

-- ----------------------------
-- Table structure for `sc_rwscl`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rwscl`;
CREATE TABLE `sc_rwscl` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `bc` varchar(1) NOT NULL COMMENT '班次',
  `cll` double NOT NULL COMMENT '污水处理(t)',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`,`bc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_rwscl
-- ----------------------------

-- ----------------------------
-- Table structure for `sc_rxh`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rxh`;
CREATE TABLE `sc_rxh` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `xhpbh` varchar(3) NOT NULL COMMENT '消耗品编号',
  `bc` varchar(1) NOT NULL COMMENT '班次',
  `xhl` double NOT NULL COMMENT '消耗品使用量',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`,`xhpbh`,`bc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rxhpkc`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rxhpkc`;
CREATE TABLE `sc_rxhpkc` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `xhpbh` varchar(3) NOT NULL COMMENT '消耗品编号',
  `kc` double DEFAULT NULL COMMENT '库存',
  `cqe` double DEFAULT NULL COMMENT '超欠额',
  `lrr` varchar(20) DEFAULT NULL COMMENT '库存录入人',
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`,`xhpbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rxs`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rxs`;
CREATE TABLE `sc_rxs` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `cjc` double NOT NULL COMMENT '粗甲醇',
  `jjc` double NOT NULL COMMENT '精甲醇',
  `yy` double NOT NULL COMMENT '液氧',
  `yd` double NOT NULL COMMENT '液氮',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(255) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_rzs`
-- ----------------------------
DROP TABLE IF EXISTS `sc_rzs`;
CREATE TABLE `sc_rzs` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `rdzs` varchar(2000) NOT NULL COMMENT '热电综述',
  `qhzs` varchar(2000) NOT NULL COMMENT '气化综述',
  `jczs` varchar(2000) NOT NULL COMMENT '甲醇综述',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`scrq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sc_xhp_jh`
-- ----------------------------
DROP TABLE IF EXISTS `sc_xhp_jh`;
CREATE TABLE `sc_xhp_jh` (
  `jhyf` varchar(7) NOT NULL COMMENT '计划月份(yyyy-mm)',
  `xhpbh` varchar(3) NOT NULL COMMENT '消耗品编号',
  `yzb` double NOT NULL COMMENT '月指标',
  `rzb` double NOT NULL COMMENT '日指标',
  `lrr` varchar(20) DEFAULT NULL,
  `lrrxm` varchar(20) DEFAULT NULL,
  `lrsj` datetime DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`jhyf`,`xhpbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `st_err_msg`;
CREATE TABLE `st_err_msg` (
  `err_code` varchar(6) NOT NULL COMMENT '错误代码，取值200000~299999',
  `err_msg` varchar(200) NOT NULL COMMENT '错误描述',
  PRIMARY KEY (`err_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_err_msg
-- ----------------------------
INSERT INTO st_err_msg VALUES ('200001', '验证码错误');
INSERT INTO st_err_msg VALUES ('200002', '加密算法异常');
INSERT INTO st_err_msg VALUES ('200003', '没有查到相关数据');
INSERT INTO st_err_msg VALUES ('200004', '用户不存在');
INSERT INTO st_err_msg VALUES ('200005', '没有查到相关信息，请检查是否输入正确。');
INSERT INTO st_err_msg VALUES ('200006', '原密码错误');
INSERT INTO st_err_msg VALUES ('200007', '权限不足');
INSERT INTO st_err_msg VALUES ('200008', '用户名或密码错误');
INSERT INTO st_err_msg VALUES ('200009', '用户名重复！');
INSERT INTO st_err_msg VALUES ('200010', '只有录入人才能修改！');
INSERT INTO st_err_msg VALUES ('200011', '必须由 !#! 核实！');
INSERT INTO st_err_msg VALUES ('200012', '必须由 !#! 确认！');
INSERT INTO st_err_msg VALUES ('200013', '登记的三违还未核实！');
INSERT INTO st_err_msg VALUES ('200016', '!#! 必须是数字！');
INSERT INTO st_err_msg VALUES ('200017', '必须由 !#! 通报！');
INSERT INTO st_err_msg VALUES ('200018', '只能复查本厂的隐患');
INSERT INTO st_err_msg VALUES ('200019', '!#!不存在');
INSERT INTO st_err_msg VALUES ('200020', '只能复查正在整改的隐患');
INSERT INTO st_err_msg VALUES ('200021', '只能对正在整改的隐患申请延时');
INSERT INTO st_err_msg VALUES ('200022', '必须由安环部复查');
INSERT INTO st_err_msg VALUES ('200023', '请先登录');
INSERT INTO st_err_msg VALUES ('200024', '不能对该记录做修改');
INSERT INTO st_err_msg VALUES ('200025', '必须由安环部!#!');
INSERT INTO st_err_msg VALUES ('200026', '!#!不能为空');
INSERT INTO st_err_msg VALUES ('200027', '批准时间必须大于当前时间');
INSERT INTO st_err_msg VALUES ('200028', '只能复查分厂已验收的隐患');
INSERT INTO st_err_msg VALUES ('200030', '错误的请求地址！');
INSERT INTO st_err_msg VALUES ('200031', '只能核实自己分厂或部门的隐患！');
INSERT INTO st_err_msg VALUES ('200032', '!#!不能大于当前时间');
INSERT INTO st_err_msg VALUES ('210005', '!#!不符！');
INSERT INTO st_err_msg VALUES ('210008', '找不到属性文件');
INSERT INTO st_err_msg VALUES ('210032', '保存文件失败');
INSERT INTO st_err_msg VALUES ('210033', '!#!格式错误');
INSERT INTO st_err_msg VALUES ('210034', '没有更新的调度快报了！');
INSERT INTO st_err_msg VALUES ('210035', '调度日报的时间不能大于今天！');
INSERT INTO st_err_msg VALUES ('220006', '保存密码时出现异常，请重试！');
INSERT INTO st_err_msg VALUES ('220014', '无效的链接地址！');
INSERT INTO st_err_msg VALUES ('220016', '要查询的!#!不存在');
INSERT INTO st_err_msg VALUES ('220017', '图表生成错误');
INSERT INTO st_err_msg VALUES ('999972', '文件上传失败：!#!');
INSERT INTO st_err_msg VALUES ('999973', '不能创建session!');
INSERT INTO st_err_msg VALUES ('999974', '查询不到SQL语句：!#!');
INSERT INTO st_err_msg VALUES ('999975', '验证码生成异常');
INSERT INTO st_err_msg VALUES ('999976', '给定的验证参数格式不符合要求！');
INSERT INTO st_err_msg VALUES ('999977', '进行Base64转/解码出现异常');
INSERT INTO st_err_msg VALUES ('999978', '解析/封装 XML文档出现异常');
INSERT INTO st_err_msg VALUES ('999979', '调用前置WebService出现未知异常');
INSERT INTO st_err_msg VALUES ('999980', '调用前置WebService失败');
INSERT INTO st_err_msg VALUES ('999981', '手机号解密异常');
INSERT INTO st_err_msg VALUES ('999982', '请求报文解析异常');
INSERT INTO st_err_msg VALUES ('999983', 'RMI通讯异常');
INSERT INTO st_err_msg VALUES ('999984', '( !#! )不向!#!渠道提供服务');
INSERT INTO st_err_msg VALUES ('999985', '目标渠道错误');
INSERT INTO st_err_msg VALUES ('999986', '源渠道错误');
INSERT INTO st_err_msg VALUES ('999987', '请求报文为空');
INSERT INTO st_err_msg VALUES ('999988', '报文头为空');
INSERT INTO st_err_msg VALUES ('999989', '报文已过期');
INSERT INTO st_err_msg VALUES ('999990', '检验位错误');
INSERT INTO st_err_msg VALUES ('999991', '报文发送时间为空');
INSERT INTO st_err_msg VALUES ('999992', '服务码为空');
INSERT INTO st_err_msg VALUES ('999993', '源渠道为空');
INSERT INTO st_err_msg VALUES ('999994', '目标渠道为空');
INSERT INTO st_err_msg VALUES ('999995', '生成流水号异常');
INSERT INTO st_err_msg VALUES ('999996', '服务码( !#! )不存在');
INSERT INTO st_err_msg VALUES ('999997', '未定义的异常代码');
INSERT INTO st_err_msg VALUES ('999998', '数据库异常');
INSERT INTO st_err_msg VALUES ('999999', '未知系统异常');

-- ----------------------------
-- Table structure for `st_service_bean`
-- ----------------------------
DROP TABLE IF EXISTS `st_service_bean`;
CREATE TABLE `st_service_bean` (
  `service_code` varchar(6) NOT NULL COMMENT '服务码',
  `bean_name` varchar(100) NOT NULL COMMENT 'spring的bean ID 首字母小字',
  `service_desc` varchar(100) DEFAULT NULL COMMENT '''说明',
  `is_login` varchar(1) NOT NULL DEFAULT 'N' COMMENT '要求的登录状态',
  `module` varchar(5) NOT NULL COMMENT '所属模块',
  PRIMARY KEY (`service_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_service_bean
-- ----------------------------
INSERT INTO st_service_bean VALUES ('P10001', 'translationServiceImpl', '控制器中的事务管理', 'N', 'frame');
INSERT INTO st_service_bean VALUES ('P10002', 'fileUploadServiceImpl', '文件上传服务', 'Y', 'frame');
INSERT INTO st_service_bean VALUES ('P11000', 'loginPl', '用户登录', 'N', 'pl');
INSERT INTO st_service_bean VALUES ('P11001', 'updatePswPl', '修改密码', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11002', 'addDepPl', '新增部门', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11003', 'updateDepPl', '修改部门信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11004', 'addRolePl', '新增一条角色信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11005', 'updateRolePl', '修改角色信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11006', 'lockUserPl', '冻结用户', 'y', 'pl');
INSERT INTO st_service_bean VALUES ('P11007', 'resetUserPswPl', '重置用户密码', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11008', 'createUserPl', '创建用户', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11009', 'saveRoleLimitPl', '保存角色权限', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11010', 'saveUseLimitPl', '保存用户特有权限', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P11011', 'updateUsePl', '修改用户信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('P12001', 'inputSw', '三违录入', 'Y', 'sw');
INSERT INTO st_service_bean VALUES ('P12002', 'updateSw', '修改三违', 'Y', 'sw');
INSERT INTO st_service_bean VALUES ('P12003', 'hsSw', '核实三违', 'Y', 'sw');
INSERT INTO st_service_bean VALUES ('P12004', 'qrSw', '确认三违', 'Y', 'sw');
INSERT INTO st_service_bean VALUES ('P12005', 'saveSg', '录入事故', 'Y', 'sg');
INSERT INTO st_service_bean VALUES ('P12006', 'publishSg', '通报事故', 'Y', 'sg');
INSERT INTO st_service_bean VALUES ('P12007', 'updateSg', '修改事故', 'Y', 'sg');
INSERT INTO st_service_bean VALUES ('P12008', 'inputZsxbYh', '自述旬报录入', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12009', 'appDelayYh', '申请延时', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12010', 'factoryAccYh', '分厂验收', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12011', 'compAccYh', '公司验收', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12012', 'appZgjlYh', '整改审批', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12013', 'expiredZgYh', '定时修改隐患状态', 'N', 'yh');
INSERT INTO st_service_bean VALUES ('P12014', 'inputYhsgyh', '隐患收购登记单', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12015', 'verifyYhsgYh', '隐患收购核实', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12016', 'inputYhjcYh', '隐患检查登记', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P12017', 'factoryAccYhjc', '分厂验收安全检查', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('P13001', 'inputClSc', '产量录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13002', 'inputPlanSc', '产量计划录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13003', 'inputDhSc', '单耗录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13004', 'inputWgSc', '日外售录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13005', 'inputWsclSc', '污水处理录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13006', 'inputXsSc', '日销售录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13007', 'inputZsSc', '生产综述录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13008', 'inputXhSc', '日消耗录入', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13009', 'inputXhPlanSc', '录入消耗品计划', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13010', 'nextRbSc', '生成下一个调度日报', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13011', 'createRbSc', '创建生产调度日报', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('P13012', 'inputDdkbSc', '保存调度日报快照', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S10001', 'cutPageServiceImpl', '分页查询服务', 'N', 'frame');
INSERT INTO st_service_bean VALUES ('S10004', 'checkNumServiceImpl', '检验验证码', 'N', 'frame');
INSERT INTO st_service_bean VALUES ('S10005', 'kindUploadServiceImpl', 'kindeditor组件上传文件', 'N', 'frame');
INSERT INTO st_service_bean VALUES ('S11000', 'logoutPl', '用户退出', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11001', 'getUserAllLimitPl', '获取用户权限', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11002', 'getAllDepPl', '获取所有部门信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11003', 'getDepPl', '获取单一的部门信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11004', 'getAllRolePl', '获取所有角色信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11005', 'getRolePl', '获取一条角色信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11006', 'searchUserPl', '获取所有用户', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11007', 'getUserPl', '获取单个用户信息', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11008', 'getRoleLimit', '获取角色权限', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11009', 'getUserLimitPl', '获取用户特有权限', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S11010', 'getUserTreePl', '获取用户部门树', 'Y', 'pl');
INSERT INTO st_service_bean VALUES ('S12001', 'getPageSw', '获取一页三违信息', 'Y', 'sw');
INSERT INTO st_service_bean VALUES ('S12002', 'searchSg', '查询事故', 'Y', 'sg');
INSERT INTO st_service_bean VALUES ('S12003', 'searchNoPubSg', '查询未通报的事故', 'Y', 'sg');
INSERT INTO st_service_bean VALUES ('S12004', 'pageZsxbYh', '查询自述旬报', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12005', 'getZsxbYh', '获取一条自述旬报信息', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12006', 'searchZgjlYh', '查询整改记录', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12007', 'searchZgjlPzYh', '查询需要审批整改记录', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12008', 'searchYhsgYh', '查询隐患收购单', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12009', 'searchSgyhYh', '查询收购的隐患', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12010', 'pageYhsgYh', '隐患收购分页查询', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12011', 'getYhsgYh', '获取隐患收购', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12012', 'pageYhjcYh', '隐患查检查询', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12013', 'getYhjcYh', '获取一条安全查询', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12014', 'pageZgtbYh', '查询整改通报', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S12015', 'indexPageYh', '登陆后欢迎页面的安全管理信息', 'Y', 'yh');
INSERT INTO st_service_bean VALUES ('S13001', 'pageGyjcSc', '工艺检查查询', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13002', 'pageGyrzSc', '工艺日志查询', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13003', 'getDepYmdhSc', '获取本单位的原煤单耗', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13004', 'reportDhMonthSc', '单耗月报表', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13005', 'getDhJfreeSc', '获取单耗图表', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13006', 'getXsSc', '获取产品销售信息', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13007', 'getDdrbSc', '获取调试日报', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13008', 'getClSc', '获取产量', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13009', 'getPlanSc', '获取月指标', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13010', 'getDhSc', '获取单耗信息', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13011', 'getWgSc', '获取日购', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13012', 'getZsSc', '获取生产综述', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13013', 'getXhSc', '获取日消耗', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13014', 'getDdkbSc', '查询调度快报', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S13015', 'getXhPlanSc', '获取消耗品月指标', 'Y', 'sc');
INSERT INTO st_service_bean VALUES ('S14001', 'pageGylcSb', '查询工艺流程', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S14002', 'pageGysbSb', '查询工艺设备', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S14003', 'pageSbbpSb', '设备备品查询', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S14004', 'pageSbzlSb', '查询设备资料', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S14005', 'pageDjbjSb', '点检班级查询', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S14006', 'getReportYxsjSb', '主要装置运行时间统计报表', 'Y', 'sb');
INSERT INTO st_service_bean VALUES ('S15001', 'pagePubMsgBq', '查询公告', 'Y', 'bq');
INSERT INTO st_service_bean VALUES ('S15002', 'pagePriMsgBq', '查询私信', 'Y', 'bq');

-- ----------------------------
-- Table structure for `st_table_paramet`
-- ----------------------------
DROP TABLE IF EXISTS `st_table_paramet`;
CREATE TABLE `st_table_paramet` (
  `table_name` varchar(100) NOT NULL DEFAULT '' COMMENT '表名',
  `col_name` varchar(100) NOT NULL DEFAULT '' COMMENT '列名',
  `col_value` varchar(50) NOT NULL DEFAULT '' COMMENT '列值',
  `col_desc` varchar(100) NOT NULL COMMENT '列的描述',
  `value_desc` varchar(199) NOT NULL COMMENT '值的描述',
  PRIMARY KEY (`table_name`,`col_name`,`col_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_table_paramet
-- ----------------------------
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '01', '事故级别', '重伤以上事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '02', '事故级别', '重伤事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '03', '事故级别', '轻亡事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '04', '事故级别', '轻微伤害事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '05', '事故级别', '一级非伤亡事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '06', '事故级别', '二级非伤亡事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '07', '事故级别', '三级非伤亡事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '08', '事故级别', '一般故障');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgjb', '09', '事故级别', '微小故障');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '01', '事故类别', '伤害事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '02', '事故类别', '生产操作事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '03', '事故类别', '设备事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '04', '事故类别', '交通运输事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '05', '事故类别', '火灾事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '06', '事故类别', '爆炸事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '07', '事故类别', '污染事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '08', '事故类别', '质量事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '09', '事故类别', '自然事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sglb', '10', '事故类别', '未遂事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgxz', '01', '事故性质', '责任事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'sgxz', '02', '事故性质', '非责任事故');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'zt', '1', '事故状态', '未通报');
INSERT INTO st_table_paramet VALUES ('aq_sg_info', 'zt', '2', '事故状态', '已通报');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'swfl', '01', '三违分类', '轻微三违');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'swfl', '02', '三违分类', '一般三违');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'swfl', '03', '三违分类', '严重三违');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'zt', '01', '三违状态', '未核实');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'zt', '02', '三违状态', '未确认');
INSERT INTO st_table_paramet VALUES ('aq_sw_info', 'zt', '03', '三违状态', '已确认');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'ly', '1', '隐患来源', '隐患收购');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'ly', '2', '隐患来源', '自述旬报');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'ly', '3', '隐患来源', '安全检查');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhjb', '1', '隐患级别', '一般');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhjb', '2', '隐患级别', '重大');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '01', '隐患类别', '设备');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '02', '隐患类别', '工艺');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '03', '隐患类别', '安全管理');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '04', '隐患类别', '电气');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '05', '隐患类别', '仪表');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'yhlb', '06', '隐患类别', '工程');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'zt', '0', '隐患状态', '复查通过');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'zt', '1', '隐患状态', '正在整改');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'zt', '2', '隐患状态', '逾期未整改');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'zt', '3', '隐患状态', '整改完成');
INSERT INTO st_table_paramet VALUES ('aq_yh_info', 'zt', '4', '隐患状态', '分厂已验收');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhjc', 'jclx', '1', '检查类型', '日常检查');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhjc', 'jclx', '2', '检查类型', '综合检查');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhjc', 'jclx', '3', '检查类型', '隐患排查');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhjc', 'jclx', '4', '检查类型', '专项检查');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhjc', 'jclx', '5', '检查类型', '季度检查');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhsg', 'sgzt', '0', '收购状态', '不是隐患');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhsg', 'sgzt', '1', '收购状态', '未收购');
INSERT INTO st_table_paramet VALUES ('aq_yh_yhsg', 'sgzt', '2', '收购状态', '已收购');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '0', '整改状态', '正在整改');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '1', '整改状态', '分厂验收未通过');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '2', '整改状态', '公司验收未通过');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '3', '整改状态', '申请延时');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '4', '整改状态', '复查通过');
INSERT INTO st_table_paramet VALUES ('aq_yh_zgjl', 'zgjg', '5', '整改状态', '逾期未整改');
INSERT INTO st_table_paramet VALUES ('pl_dep_info', 'dep_type', '0', '部门类型', '管理部门');
INSERT INTO st_table_paramet VALUES ('pl_dep_info', 'dep_type', '1', '部门类型', '分厂');
INSERT INTO st_table_paramet VALUES ('pl_dep_info', 'dep_type', '2', '部门类型', '其它公司');
INSERT INTO st_table_paramet VALUES ('pl_menu', 'm_type', '0', '菜单类型', '链接');
INSERT INTO st_table_paramet VALUES ('pl_menu', 'm_type', '1', '菜单类型', '目录');
INSERT INTO st_table_paramet VALUES ('pl_role_limit', 'limit', '1', '权限级别', '使用');
INSERT INTO st_table_paramet VALUES ('pl_role_limit', 'limit', '9', '权限级别', '管理');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zt', '0', '用户状态', '已删除');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zt', '1', '用户状态', '正常');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '01', '职务', '总经理');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '04', '职务', '党委书记');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '07', '职务', '纪委书记');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '10', '职务', '副总经理');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '13', '职务', '财务总监');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '16', '职务', '总经理助理');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '19', '职务', '总工程师');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '22', '职务', '副总工程师');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '25', '职务', '部长');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '28', '职务', '副部长');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '31', '职务', '部门主管');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '34', '职务', '分厂厂长');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '37', '职务', '分厂副厂长');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '40', '职务', '车间主任');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '43', '职务', '车间副主任');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '46', '职务', '主任');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '49', '职务', '副主任');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '52', '职务', '分厂支部书记');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '55', '职务', '主任工程师');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '58', '职务', '副主任工程师');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '61', '职务', '安全员');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '64', '职务', '技术员');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '67', '职务', '核算员');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '70', '职务', '一般职员');
INSERT INTO st_table_paramet VALUES ('pl_user', 'zw', '73', '职务', '一般员工');
INSERT INTO st_table_paramet VALUES ('sc', 'bc', 'b', '班次', '白班');
INSERT INTO st_table_paramet VALUES ('sc', 'bc', 'y', '班次', '夜班');
INSERT INTO st_table_paramet VALUES ('sc', 'bc', 'z', '班次', '中班');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '001', '产品编号', '粗醇（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '002', '产品编号', '精醇（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '003', '产品编号', '液氧（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '004', '产品编号', '液氮（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '005', '产品编号', '蒸汽（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '006', '产品编号', '金山化工用蒸汽（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '007', '产品编号', '硫磺（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '008', '产品编号', '硫酸铵（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '009', '产品编号', '自发电（万度 ）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '010', '产品编号', '有效煤气（千标方）');
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '011', '产品编号', '液氩（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '101', '消耗品编号', '吨甲醇耗有效气（千标方）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '102', '消耗品编号', '千方有效气耗原煤（kg）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '103', '消耗品编号', '吨蒸汽耗燃料煤（kg）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '104', '消耗品编号', '原料煤（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '105', '消耗品编号', '燃料煤（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '106', '消耗品编号', '用电量（万度）');
INSERT INTO st_table_paramet VALUES ('sc', 'xhpbh', '107', '消耗品编号', '原水量（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xspbh', '201', '销售品编号', '粗甲醇（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xspbh', '202', '销售品编号', '精甲醇（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xspbh', '203', '销售品编号', '液氧（t）');
INSERT INTO st_table_paramet VALUES ('sc', 'xspbh', '204', '销售品编号', '液氮（t）');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'aq', '系统模块', '安全管理');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'pl', '系统模块', '平台功能');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'sg', '系统模块', '事故管理');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'sw', '系统模块', '三违管理');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'yhgl', '系统模块', '隐患管理');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'yhsg', '系统模块', '隐患收购');
INSERT INTO st_table_paramet VALUES ('system', 'module', 'zsxb', '系统模块', '自述旬报');


DROP TABLE IF EXISTS `pl_menu`;
CREATE TABLE `pl_menu` (
  `m_id` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单id',
  `m_name` varchar(50) NOT NULL COMMENT '菜单名',
  `m_url` varchar(50) DEFAULT NULL COMMENT '菜单url',
  `m_title` varchar(200) DEFAULT NULL COMMENT '菜单描述',
  `m_type` varchar(1) NOT NULL DEFAULT '0' COMMENT '1为目录，0为菜单',
  `m_super` varchar(20) DEFAULT NULL COMMENT '上级目录',
  `m_order` varchar(8) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pl_menu
-- ----------------------------
INSERT INTO pl_menu VALUES ('aq', '安全管理', null, '安全管理', '1', null, '02000000');
INSERT INTO pl_menu VALUES ('bq', '收发便签', '', '收发便签', '1', '', '01000000');
INSERT INTO pl_menu VALUES ('bq0001', '发布公告', 'bq/bq0001', '发布公告', '0', 'bq', '01000004');
INSERT INTO pl_menu VALUES ('bq0002', '发送私信', 'bq/bq0002', '发送私信', '0', 'bq', '01000003');
INSERT INTO pl_menu VALUES ('bq0003', '查看公告', 'bq/bq0003', '查看公告', '0', 'bq', '01000001');
INSERT INTO pl_menu VALUES ('bq0004', '查看私信', 'bq/bq0004', '查看私信', '0', 'bq', '01000002');
INSERT INTO pl_menu VALUES ('cl', '产量信息', '', '产品产量信息', '1', 'sc', '03050000');
INSERT INTO pl_menu VALUES ('cl0001', '粗醇录入', 'sc/cl/cl0001', '粗醇产量录入', '0', 'cl', '03050001');
INSERT INTO pl_menu VALUES ('cl0002', '精醇录入', 'sc/cl/cl0002', '精醇产量录入', '0', 'cl', '03050002');
INSERT INTO pl_menu VALUES ('cl0003', '液氧录入', 'sc/cl/cl0003', '液氧产量录入', '0', 'cl', '03050003');
INSERT INTO pl_menu VALUES ('cl0004', '液氮录入', 'sc/cl/cl0004', '液氮产量录入', '0', 'cl', '03050004');
INSERT INTO pl_menu VALUES ('cl0005', '蒸汽录入', 'sc/cl/cl0005', '蒸汽产量录入', '0', 'cl', '03050005');
INSERT INTO pl_menu VALUES ('cl0006', '金山化工用蒸汽', 'sc/cl/cl0006', '金山化工用蒸汽产量录入', '0', 'cl', '03050006');
INSERT INTO pl_menu VALUES ('cl0007', '硫磺录入', 'sc/cl/cl0007', '硫磺产量录入', '0', 'cl', '03050007');
INSERT INTO pl_menu VALUES ('cl0008', '硫酸铵录入', 'sc/cl/cl0008', '硫酸铵产量录入', '0', 'cl', '03050008');
INSERT INTO pl_menu VALUES ('cl0009', '自发电录入', 'sc/cl/cl0009', '自发电产量录入', '0', 'cl', '03050009');
INSERT INTO pl_menu VALUES ('cl0010', '有效煤气录入', 'sc/cl/cl0010', '有效煤气产量录入', '0', 'cl', '03050010');
INSERT INTO pl_menu VALUES ('cl0011', '液氩录入', 'sc/cl/cl0011', '液氩产量录入', '0', 'cl', '03050011');
INSERT INTO pl_menu VALUES ('cl0051', '粗醇生产计划', 'sc/cl/cl0051', '粗醇产量生产计划录入', '0', 'cl', '03050051');
INSERT INTO pl_menu VALUES ('cl0052', '精醇生产计划', 'sc/cl/cl0052', '精醇产量生产计划录入', '0', 'cl', '03050052');
INSERT INTO pl_menu VALUES ('cl0053', '液氧生产计划', 'sc/cl/cl0053', '液氧产量生产计划录入', '0', 'cl', '03050053');
INSERT INTO pl_menu VALUES ('cl0054', '液氮生产计划', 'sc/cl/cl0054', '液氮产量生产计划录入', '0', 'cl', '03050054');
INSERT INTO pl_menu VALUES ('cl0055', '蒸汽生产计划', 'sc/cl/cl0055', '蒸汽产量生产计划录入', '0', 'cl', '03050055');
INSERT INTO pl_menu VALUES ('cl0056', '金山化工用蒸汽', 'sc/cl/cl0056', '金山化工用蒸汽产量生产计划录入', '0', 'cl', '03050056');
INSERT INTO pl_menu VALUES ('cl0057', '硫磺生产计划', 'sc/cl/cl0057', '硫磺产量生产计划录入', '0', 'cl', '03050057');
INSERT INTO pl_menu VALUES ('cl0058', '硫酸铵生产计划', 'sc/cl/cl0058', '硫酸铵产量生产计划录入', '0', 'cl', '03050058');
INSERT INTO pl_menu VALUES ('cl0059', '自发电生产计划', 'sc/cl/cl0059', '自发电产量生产计划录入', '0', 'cl', '03050059');
INSERT INTO pl_menu VALUES ('cl0060', '有效煤气生产计划', 'sc/cl/cl0060', '有效煤气产量生产计划录入', '0', 'cl', '03050060');
INSERT INTO pl_menu VALUES ('cl0061', '液氩生产计划', 'sc/cl/cl0061', '液氩产量生产计划录入', '0', 'cl', '03050061');
INSERT INTO pl_menu VALUES ('ddrb', '调度日报信息', '', '调度日报信息', '1', 'sc', '03010000');
INSERT INTO pl_menu VALUES ('ddrb01', '调度日报审核', 'sc/ddrb/ddrb01', '调度日报审核', '0', 'ddrb', '03010002');
INSERT INTO pl_menu VALUES ('ddrb02', '调度日报查询', 'sc/ddrb/ddrb02', '调度日报查询', '0', 'ddrb', '03010001');
INSERT INTO pl_menu VALUES ('dh', '产品单耗信息', '', '产品单耗信息', '1', 'sc', '03040000');
INSERT INTO pl_menu VALUES ('dh0001', '单耗录入', 'sc/dh/dh0001', '单耗录入', '0', 'dh', '03040003');
INSERT INTO pl_menu VALUES ('dh0006', '月报表', 'sc/dh/dh0006', '单耗月报表', '0', 'dh', '03040001');
INSERT INTO pl_menu VALUES ('dh0007', '曲线图', 'sc/dh/dh0007', '单耗曲线图', '0', 'dh', '03040002');
INSERT INTO pl_menu VALUES ('djrz', '点检日志管理', '', '点检日志管理', '1', 'sb', '04050000');
INSERT INTO pl_menu VALUES ('djrz01', '点检日志记录', 'sb/djrz/djrz01', '点检日志记录', '0', 'djrz', '04050001');
INSERT INTO pl_menu VALUES ('djrz02', '点检日志上报', 'sb/djrz/djrz02', '点检日志上报', '0', 'djrz', '04050002');
INSERT INTO pl_menu VALUES ('djrz03', '点检班级设置', 'sb/djrz/djrz03', '点检班级设置', '0', 'djrz', '04050003');
INSERT INTO pl_menu VALUES ('gyjc', '工艺检查信息', '', '工艺检查信息', '1', 'sc', '03090000');
INSERT INTO pl_menu VALUES ('gyjc01', '工艺检查登记', 'sc/gyjc/gyjc01', '工艺检查登记', '0', 'gyjc', '03090002');
INSERT INTO pl_menu VALUES ('gyjc02', '工艺检查查询', 'sc/gyjc/gyjc02', '工艺检查查询', '0', 'gyjc', '03090001');
INSERT INTO pl_menu VALUES ('gyrz', '工艺日志信息', '', '工艺日志信息', '1', 'sc', '03100000');
INSERT INTO pl_menu VALUES ('gyrz01', '工艺日志登记', 'sc/gyrz/gyrz01', '工艺日志登记', '0', 'gyrz', '03100002');
INSERT INTO pl_menu VALUES ('gyrz02', '工艺日志查询', 'sc/gyrz/gyrz02', '工艺日志查询', '0', 'gyrz', '03100001');
INSERT INTO pl_menu VALUES ('pl', '平台管理', null, '平台管理', '1', null, '99000000');
INSERT INTO pl_menu VALUES ('pl0001', '部门管理', 'pl/pl0001', '增加、修改、删除部门', '0', 'pl', '99000003');
INSERT INTO pl_menu VALUES ('pl0002', '角色管理', 'pl/pl0002', '增加、修改、删除用户角色', '0', 'pl', '99000002');
INSERT INTO pl_menu VALUES ('pl0003', '用户管理', 'pl/pl0003', '增加、冻结用户', '0', 'pl', '99000001');
INSERT INTO pl_menu VALUES ('pl0004', '角色权限管理', 'pl/pl0004', '为角色授权或删除授权', '0', 'pl', '99000004');
INSERT INTO pl_menu VALUES ('pl0005', '用户特有权限管理', 'pl/pl0005', '为用户分配或收回特有权限', '0', 'pl', '99000005');
INSERT INTO pl_menu VALUES ('rxh', '日消耗', '', '日消耗信息管理', '1', 'sc', '03060000');
INSERT INTO pl_menu VALUES ('rxh001', '甲醇耗有效气', 'sc/rxh/rxh001', '甲醇耗有效气录入', '0', 'rxh', '03060001');
INSERT INTO pl_menu VALUES ('rxh002', '有效气耗原煤', 'sc/rxh/rxh002', '千方有效气耗原煤录入', '0', 'rxh', '03060002');
INSERT INTO pl_menu VALUES ('rxh003', '蒸汽耗燃料煤', 'sc/rxh/rxh003', '吨蒸汽耗燃料煤录入', '0', 'rxh', '03060003');
INSERT INTO pl_menu VALUES ('rxh004', '原料煤', 'sc/rxh/rxh004', '原料煤消耗录入', '0', 'rxh', '03060004');
INSERT INTO pl_menu VALUES ('rxh005', '燃料煤', 'sc/rxh/rxh005', '燃料煤消耗录入', '0', 'rxh', '03060005');
INSERT INTO pl_menu VALUES ('rxh006', '用电量', 'sc/rxh/rxh006', '用电量消耗录入', '0', 'rxh', '03060006');
INSERT INTO pl_menu VALUES ('rxh007', '原水量', 'sc/rxh/rxh007', '原水量消耗录入', '0', 'rxh', '03060007');
INSERT INTO pl_menu VALUES ('rxh101', '甲醇耗气计划', 'sc/rxh/rxh101', '甲醇耗有效气计划录入', '0', 'rxh', '03060101');
INSERT INTO pl_menu VALUES ('rxh102', '气耗原煤计划', 'sc/rxh/rxh102', '有效气耗原煤计划录入', '0', 'rxh', '03060102');
INSERT INTO pl_menu VALUES ('rxh103', '蒸汽耗煤计划', 'sc/rxh/rxh103', '蒸汽耗燃料煤计划录入', '0', 'rxh', '03060103');
INSERT INTO pl_menu VALUES ('rxh104', '原料煤消耗计划', 'sc/rxh/rxh104', '原料煤消耗计划录入', '0', 'rxh', '03060104');
INSERT INTO pl_menu VALUES ('rxh105', '燃料煤消耗计划', 'sc/rxh/rxh105', '燃料煤消耗计划录入', '0', 'rxh', '03060105');
INSERT INTO pl_menu VALUES ('rxh106', '用电量计划', 'sc/rxh/rxh106', '用电量计划录入', '0', 'rxh', '03060106');
INSERT INTO pl_menu VALUES ('rxh107', '原水消耗计划', 'sc/rxh/rxh107', '原水消耗计划录入', '0', 'rxh', '03060107');
INSERT INTO pl_menu VALUES ('sb', '设备管理', '', '设备管理', '1', '', '04000000');
INSERT INTO pl_menu VALUES ('sbjx', '设备检修与故障', '', '设备检修与故障', '1', 'sb', '04030000');
INSERT INTO pl_menu VALUES ('sbjx01', '缺陷故障录入', 'sb/sbjx/sbjx01', '缺陷故障录入', '0', 'sbjx', '04030006');
INSERT INTO pl_menu VALUES ('sbjx02', '事故记录录入', 'sb/sbjx/sbjx02', '事故记录录入', '0', 'sbjx', '04030007');
INSERT INTO pl_menu VALUES ('sbjx03', '检修检查录入', 'sb/sbjx/sbjx03', '检修检查录入', '0', 'sbjx', '04030008');
INSERT INTO pl_menu VALUES ('sbjx04', '检修经济效果分析录入', 'sb/sbjx/sbjx04', '检修经济效果分析录入', '0', 'sbjx', '04030009');
INSERT INTO pl_menu VALUES ('sbjx05', '技改技措录入', 'sb/sbjx/sbjx05', '技改技措录入', '0', 'sbjx', '04030010');
INSERT INTO pl_menu VALUES ('sbjx06', '缺陷故障查询', 'sb/sbjx/sbjx06', '缺陷故障查询', '0', 'sbjx', '04030001');
INSERT INTO pl_menu VALUES ('sbjx07', '事故记录查询', 'sb/sbjx/sbjx07', '事故记录查询', '0', 'sbjx', '04030002');
INSERT INTO pl_menu VALUES ('sbjx08', '检修检查查询', 'sb/sbjx/sbjx08', '检修检查查询', '0', 'sbjx', '04030003');
INSERT INTO pl_menu VALUES ('sbjx09', '检修经济效果分析查询', 'sb/sbjx/sbjx09', '检修经济效果分析查询', '0', 'sbjx', '04030004');
INSERT INTO pl_menu VALUES ('sbjx10', '技改技措查询', 'sb/sbjx/sbjx10', '技改技措查询', '0', 'sbjx', '04030005');
INSERT INTO pl_menu VALUES ('sbzt', '设备状态管理', '', '设备状态管理', '1', 'sb', '04020000');
INSERT INTO pl_menu VALUES ('sbzt01', '设备总目录', 'sb/sbzt/sbzt01', '设备总目录', '0', 'sbzt', '04020002');
INSERT INTO pl_menu VALUES ('sbzt02', '设备状态修改', 'sb/sbzt/sbzt02', '设备状态修改', '0', 'sbzt', '04020003');
INSERT INTO pl_menu VALUES ('sbzt03', '设备检修提醒', 'sb/sbzt/sbzt03', '设备检修提醒', '0', 'sbzt', '04020001');
INSERT INTO pl_menu VALUES ('sc', '生产管理', '', '生产管理', '1', '', '03000000');
INSERT INTO pl_menu VALUES ('sczs', '生产情况综述', '', '生产情况综述', '1', 'sc', '03070000');
INSERT INTO pl_menu VALUES ('sczs01', '综述录入', 'sc/sczs/sczs01', '生产情况综述录入', '0', 'sczs', '03070001');
INSERT INTO pl_menu VALUES ('sg', '事故管理', '', '事故管理', '1', 'aq', '02030000');
INSERT INTO pl_menu VALUES ('sg0001', '事故登记', 'aq/sg/sg0001', '事故简报录入', '0', 'sg', '02030002');
INSERT INTO pl_menu VALUES ('sg0002', '事故通报', 'aq/sg/sg0002', '事故通报', '0', 'sg', '02030003');
INSERT INTO pl_menu VALUES ('sg0003', '事故查询', 'aq/sg/sg0003', '事故查询', '0', 'sg', '02030001');
INSERT INTO pl_menu VALUES ('sw', '三违管理', null, '三违管理', '1', 'aq', '02010000');
INSERT INTO pl_menu VALUES ('sw0001', '三违登记', 'aq/sw/sw0001', '三违登记', '0', 'sw', '02010001');
INSERT INTO pl_menu VALUES ('sw0002', '三违核实', 'aq/sw/sw0002', '三违核实', '0', 'sw', '02010002');
INSERT INTO pl_menu VALUES ('sw0003', '三违确认', 'aq/sw/sw0003', '三违确认', '0', 'sw', '02010003');
INSERT INTO pl_menu VALUES ('xs', '内部销售信息', '', '内部销售信息管理', '1', 'sc', '03080000');
INSERT INTO pl_menu VALUES ('xs0001', '销售信息录入', 'sc/xs/xs0001', '销售信息录入', '0', 'xs', '03080001');
INSERT INTO pl_menu VALUES ('yhgl', '隐患管理', '', '隐患管理', '1', 'aq', '02020000');
INSERT INTO pl_menu VALUES ('yhgl01', '整改通报查询', 'aq/yhgl/yhgl01', '整改通报查询', '0', 'yhgl', '02020001');
INSERT INTO pl_menu VALUES ('yhgl02', '自述旬报查询', 'aq/yhgl/yhgl02', '自述旬报查询', '0', 'yhgl', '02020002');
INSERT INTO pl_menu VALUES ('yhgl03', '隐患收购查询', 'aq/yhgl/yhgl03', '隐患收购查询', '0', 'yhgl', '02020003');
INSERT INTO pl_menu VALUES ('yhgl04', '安全检查查询', 'aq/yhgl/yhgl04', '安全检查查询', '0', 'yhgl', '02020004');
INSERT INTO pl_menu VALUES ('yhgl05', '整改时间审批', 'aq/yhgl/yhgl05', '整改时间审批', '0', 'yhgl', '02020005');
INSERT INTO pl_menu VALUES ('yhgl06', '公司验收', 'aq/yhgl/yhgl06', '公司验收', '0', 'yhgl', '02020006');
INSERT INTO pl_menu VALUES ('yhjc', '安全检查', '', '安全检查管理', '1', 'aq', '02060000');
INSERT INTO pl_menu VALUES ('yhjc01', '安全检查登记', 'aq/yhjc/yhjc01', '隐患登记', '0', 'yhjc', '02060001');
INSERT INTO pl_menu VALUES ('yhjc02', '分厂验收', 'aq/yhjc/yhjc02', '隐患整改', '0', 'yhjc', '02060002');
INSERT INTO pl_menu VALUES ('yhjc03', '延时申请', 'aq/yhjc/yhjc03', '隐患查询', '0', 'yhjc', '02060003');
INSERT INTO pl_menu VALUES ('yhsg', '隐患收购', '', '隐患收购', '1', 'aq', '02040000');
INSERT INTO pl_menu VALUES ('yhsg01', '隐患收购登记', 'aq/yhsg/yhsg01', '隐患收购登记', '0', 'yhsg', '02040002');
INSERT INTO pl_menu VALUES ('yhsg02', '隐患核实', 'aq/yhsg/yhsg02', '隐患核实', '0', 'yhsg', '02040003');
INSERT INTO pl_menu VALUES ('yhsg03', '整改进度汇报', 'aq/yhsg/yhsg03', '隐患整改', '0', 'yhsg', '02040004');
INSERT INTO pl_menu VALUES ('yhsg04', '收购单查询', 'aq/yhsg/yhsg04', '隐患查询', '0', 'yhsg', '02040001');
INSERT INTO pl_menu VALUES ('yxsj', '主要装置运行时间', '', '主要装置运行时间', '1', 'sb', '04010000');
INSERT INTO pl_menu VALUES ('yxsj01', '运行时间录入', 'sb/yxsj/yxsj01', '运行时间录入', '0', 'yxsj', '04010002');
INSERT INTO pl_menu VALUES ('yxsj02', '运行时间统计', 'sb/yxsj/yxsj02', '运行时间统计', '0', 'yxsj', '04010001');
INSERT INTO pl_menu VALUES ('zlgl', '设备资料管理', '', '设备资料管理', '1', 'sb', '04040000');
INSERT INTO pl_menu VALUES ('zlgl01', '工艺流程查询', 'sb/zlgl/zlgl01', '工艺流程查询', '0', 'zlgl', '04040001');
INSERT INTO pl_menu VALUES ('zlgl02', '工艺流程管理', 'sb/zlgl/zlgl02', '工艺流程管理', '0', 'zlgl', '04040005');
INSERT INTO pl_menu VALUES ('zlgl03', '工艺设备查询', 'sb/zlgl/zlgl03', '工艺设备查询', '0', 'zlgl', '04040002');
INSERT INTO pl_menu VALUES ('zlgl04', '工艺设备管理', 'sb/zlgl/zlgl04', '工艺设备管理', '0', 'zlgl', '04040006');
INSERT INTO pl_menu VALUES ('zlgl05', '设备备品查询', 'sb/zlgl/zlgl05', '设备备品查询', '0', 'zlgl', '04040003');
INSERT INTO pl_menu VALUES ('zlgl06', '设备备品管理', 'sb/zlgl/zlgl06', '设备备品管理', '0', 'zlgl', '04040007');
INSERT INTO pl_menu VALUES ('zlgl07', '设备资料查询', 'sb/zlgl/zlgl07', '设备资料查询', '0', 'zlgl', '04040004');
INSERT INTO pl_menu VALUES ('zlgl08', '新增设备资料', 'sb/zlgl/zlgl08', '新增设备资料', '0', 'zlgl', '04040008');
INSERT INTO pl_menu VALUES ('zsxb', '自述旬报', '', '自述旬报', '1', 'aq', '02050000');
INSERT INTO pl_menu VALUES ('zsxb01', '自述旬报登记', 'aq/zsxb/zsxb01', '自述旬报登记', '0', 'zsxb', '02050001');
INSERT INTO pl_menu VALUES ('zsxb02', '分厂验收', 'aq/zsxb/zsxb02', '分厂验收', '0', 'zsxb', '02050002');
INSERT INTO pl_menu VALUES ('zsxb03', '延时申请', 'aq/zsxb/zsxb03', '延时申请', '0', 'zsxb', '02050003');
