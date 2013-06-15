ALTER TABLE `pl_dep_info`
CHANGE COLUMN `dep_super` `dep_type`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门类型' AFTER `dep_fzr`;

ALTER TABLE `pl_user`
ADD COLUMN `zw1`  varchar(2) NULL COMMENT '职务一' AFTER `create_user`,
ADD COLUMN `zw2`  varchar(2) NULL COMMENT '职务二' AFTER `zw1`,
ADD COLUMN `zw3`  varchar(2) NULL COMMENT '职务三' AFTER `zw2`;
ALTER TABLE `pl_user`
ADD COLUMN `tel`  varchar(15) NULL COMMENT '手机号' AFTER `zw3`;

ALTER
ALGORITHM=UNDEFINED
DEFINER=`root`@`localhost`
SQL SECURITY DEFINER
VIEW `pl_user_v` AS
select `a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`role_id` AS `role_id`,`a`.`dep_id` AS `dep_id`,`a`.`name` AS `name`,`a`.`rank` AS `rank`,`a`.`create_time` AS `create_time`,`a`.`create_user` AS `create_user`,`a`.`last_login_time` AS `last_login_time`,`a`.`last_login_ip` AS `last_login_ip`,`a`.`zt` AS `zt`,`a`.`bz` AS `bz`,`a`.`zw1` AS `zw1`,`a`.`zw2` AS `zw2`,`a`.`zw3` AS `zw3`,`a`.`tel` AS `tel`, `b`.`role_name` AS `role_name`,`c`.`dep_name` AS `dep_name` , `c`.`dep_type` AS `dep_type` from ((`pl_user` `a` join `pl_role` `b`) join `pl_dep_info` `c`) where ((`a`.`role_id` = `b`.`role_id`) and (`a`.`dep_id` = `c`.`dep_id`)) ;

update pl_dep_info set dep_type = '0';

delete from pl_menu where m_id in ('aq', 'sw', 'sw0001', 'sw0002', 'sw0003', 'sg', 'sg0001', 'sg0002', 'sg0003');
insert into pl_menu value ('aq', '安全管理', '', '安全管理', '1', '');
insert into pl_menu value ('sw', '三违管理', '', '三违管理', '1', 'aq');
insert into pl_menu value ('sw0001', '三违登记', 'aq/sw/sw0001', '三违登记', '0', 'sw');
insert into pl_menu value ('sw0002', '三违核实', 'aq/sw/sw0002', '三违核实', '0', 'sw');
insert into pl_menu value ('sw0003', '三违确认', 'aq/sw/sw0003', '三违确认', '0', 'sw');
insert into pl_menu value ('sg', '事故管理', '', '事故管理', '1', 'aq');
insert into pl_menu value ('sg0001', '事故录入', 'aq/sg/sg0001', '事故简报录入', '0', 'sg');
insert into pl_menu value ('sg0002', '事故通报', 'aq/sg/sg0002', '事故通报', '0', 'sg');
insert into pl_menu value ('sg0003', '事故查询', 'aq/sg/sg0003', '事故查询', '0', 'sg');

delete from pl_menu where m_id in ('yhsg','yhsg01','yhsg02','yhsg03', 'yhsg04');
insert into pl_menu value ('yhsg', '隐患收购管理', '', '隐患收购管理', '1', 'aq');
insert into pl_menu value ('yhsg01', '隐患收购登记', 'aq/yhsg/yhsg01', '隐患收购登记', '0', 'yhsg');
insert into pl_menu value ('yhsg02', '隐患核实', 'aq/yhsg/yhsg02', '隐患核实', '0', 'yhsg');
insert into pl_menu value ('yhsg03', '隐患整改', 'aq/yhsg/yhsg03', '隐患整改', '0', 'yhsg');
insert into pl_menu value ('yhsg04', '隐患查询', 'aq/yhsg/yhsg04', '隐患查询', '0', 'yhsg');

delete from pl_menu where m_id in ('zsxb','zsxb01','zsxb02','zsxb03');
insert into pl_menu value ('zsxb', '自述旬报管理', '', '自述旬报管理', '1', 'aq');
insert into pl_menu value ('zsxb01', '自述旬报登记', 'aq/zsxb/zsxb01', '自述旬报登记', '0', 'zsxb');
insert into pl_menu value ('zsxb02', '隐患整改', 'aq/zsxb/zsxb02', '隐患整改', '0', 'zsxb');
insert into pl_menu value ('zsxb03', '自述旬报查询', 'aq/zsxb/zsxb03', '自述旬报查询', '0', 'zsxb');

delete from pl_menu where m_id in ('yhjc','yhjc01','yhjc02','yhjc03');
insert into pl_menu value ('yhjc', '安全检查', '', '安全检查管理', '1', 'aq');
insert into pl_menu value ('yhjc01', '隐患登记', 'aq/yhjc/yhjc01', '隐患登记', '0', 'yhjc');
insert into pl_menu value ('yhjc02', '隐患整改', 'aq/yhjc/yhjc02', '隐患整改', '0', 'yhjc');
insert into pl_menu value ('yhjc03', '隐患查询', 'aq/yhjc/yhjc03', '隐患查询', '0', 'yhjc');

delete from pl_menu where m_id in ('yhgl','yhgl01','yhgl02','yhgl03', 'yhgl04','yhgl05','yhgl06');
insert into pl_menu value ('yhgl', '隐患管理', '', '隐患管理', '1', 'aq');
insert into pl_menu value ('yhgl01', '整改通报', 'aq/yhgl/yhgl01', '整改通报', '0', 'yhgl');
insert into pl_menu value ('yhgl02', '分厂复查', 'aq/yhgl/yhgl02', '隐患复查', '0', 'yhgl');
insert into pl_menu value ('yhgl03', '公司复查', 'aq/yhgl/yhgl03', '隐患复查', '0', 'yhgl');
insert into pl_menu value ('yhgl04', '整改延期审批', 'aq/yhgl/yhgl04', '整改延期审批', '0', 'yhgl');
insert into pl_menu value ('yhgl05', '隐患查询', 'aq/yhgl/yhgl05', '隐患查询', '0', 'yhgl');
insert into pl_menu value ('yhgl06', '整改审批', 'aq/yhgl/yhgl06', '隐患查询', '0', 'yhgl');


delete from st_table_paramet where table_name='system' and col_name='module' and col_value = 'aq';
delete from st_table_paramet where table_name='system' and col_name='module' and col_value = 'sw';
delete from st_table_paramet where table_name='pl_dep_info' and col_name='dep_type' and col_value = '0';
delete from st_table_paramet where table_name='pl_dep_info' and col_name='dep_type' and col_value = '1';
delete from st_table_paramet where table_name='pl_dep_info' and col_name='dep_type' and col_value = '2';
delete from st_table_paramet where table_name='pl_user' and col_name='zt' and col_value = '0';
delete from st_table_paramet where table_name='pl_user' and col_name='zt' and col_value = '1';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='swfl' and col_value = '01';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='swfl' and col_value = '02';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='swfl' and col_value = '03';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='zt' and col_value = '01';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='zt' and col_value = '02';
delete from st_table_paramet where table_name='aq_sw_info' and col_name='zt' and col_value = '03';
delete from st_table_paramet where table_name='aq_sg_info';
insert into st_table_paramet value ('system','module','aq','系统模块','安全管理');
insert into st_table_paramet value ('system','module','sw','系统模块','三违管理');
insert into st_table_paramet value ('pl_dep_info','dep_type','0','部门类型','管理部门');
insert into st_table_paramet value ('pl_dep_info','dep_type','1','部门类型','分厂');
insert into st_table_paramet value ('pl_dep_info','dep_type','2','部门类型','其它公司');
insert into st_table_paramet value ('pl_user','zt','0','用户状态','已删除');
insert into st_table_paramet value ('pl_user','zt','1','用户状态','正常');
insert into st_table_paramet value ('aq_sw_info','swfl','01','三违分类','轻微三违');
insert into st_table_paramet value ('aq_sw_info','swfl','02','三违分类','一般三违');
insert into st_table_paramet value ('aq_sw_info','swfl','03','三违分类','严重三违');
insert into st_table_paramet value ('aq_sw_info','zt','01','三违状态','未核实');
insert into st_table_paramet value ('aq_sw_info','zt','02','三违状态','未确认');
insert into st_table_paramet value ('aq_sw_info','zt','03','三违状态','已确认');

insert into st_table_paramet value ('aq_sg_info','zt','1','事故状态','未通报');
insert into st_table_paramet value ('aq_sg_info','zt','2','事故状态','已通报');

insert into st_table_paramet value ('aq_sg_info','sglb','01','事故类别','伤害事故');
insert into st_table_paramet value ('aq_sg_info','sglb','02','事故类别','生产操作事故');
insert into st_table_paramet value ('aq_sg_info','sglb','03','事故类别','设备事故');
insert into st_table_paramet value ('aq_sg_info','sglb','04','事故类别','交通运输事故');
insert into st_table_paramet value ('aq_sg_info','sglb','05','事故类别','火灾事故');
insert into st_table_paramet value ('aq_sg_info','sglb','06','事故类别','爆炸事故');
insert into st_table_paramet value ('aq_sg_info','sglb','07','事故类别','污染事故');
insert into st_table_paramet value ('aq_sg_info','sglb','08','事故类别','质量事故');
insert into st_table_paramet value ('aq_sg_info','sglb','09','事故类别','自然事故');
insert into st_table_paramet value ('aq_sg_info','sglb','10','事故类别','未遂事故');

insert into st_table_paramet value ('aq_sg_info','sgjb','01','事故级别','重伤以上事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','02','事故级别','重伤事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','03','事故级别','轻亡事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','04','事故级别','轻微伤害事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','05','事故级别','一级非伤亡事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','06','事故级别','二级非伤亡事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','07','事故级别','三级非伤亡事故');
insert into st_table_paramet value ('aq_sg_info','sgjb','08','事故级别','一般故障');
insert into st_table_paramet value ('aq_sg_info','sgjb','09','事故级别','微小故障');

insert into st_table_paramet value ('aq_sg_info','sgxz','01','事故性质','责任事故');
insert into st_table_paramet value ('aq_sg_info','sgxz','02','事故性质','非责任事故');

delete from st_table_paramet where table_name='pl_user' and col_name='zw';
insert into st_table_paramet value ('pl_user','zw','01','职务','总经理');
insert into st_table_paramet value ('pl_user','zw','04','职务','党委书记');
insert into st_table_paramet value ('pl_user','zw','07','职务','纪委书记');
insert into st_table_paramet value ('pl_user','zw','10','职务','副总经理');
insert into st_table_paramet value ('pl_user','zw','13','职务','财务总监');
insert into st_table_paramet value ('pl_user','zw','16','职务','总经理助理');
insert into st_table_paramet value ('pl_user','zw','19','职务','总工程师');
insert into st_table_paramet value ('pl_user','zw','22','职务','副总工程师');
insert into st_table_paramet value ('pl_user','zw','25','职务','部长');
insert into st_table_paramet value ('pl_user','zw','28','职务','副部长');
insert into st_table_paramet value ('pl_user','zw','31','职务','部门主管');
insert into st_table_paramet value ('pl_user','zw','34','职务','分厂厂长');
insert into st_table_paramet value ('pl_user','zw','37','职务','分厂副厂长');
insert into st_table_paramet value ('pl_user','zw','40','职务','车间主任');
insert into st_table_paramet value ('pl_user','zw','43','职务','车间副主任');
insert into st_table_paramet value ('pl_user','zw','46','职务','主任');
insert into st_table_paramet value ('pl_user','zw','49','职务','副主任');
insert into st_table_paramet value ('pl_user','zw','52','职务','分厂支部书记');
insert into st_table_paramet value ('pl_user','zw','55','职务','主任工程师');
insert into st_table_paramet value ('pl_user','zw','58','职务','副主任工程师');
insert into st_table_paramet value ('pl_user','zw','61','职务','安全员');
insert into st_table_paramet value ('pl_user','zw','64','职务','技术员');
insert into st_table_paramet value ('pl_user','zw','67','职务','核算员');
insert into st_table_paramet value ('pl_user','zw','70','职务','一般职员');
insert into st_table_paramet value ('pl_user','zw','73','职务','一般员工');

delete from st_table_paramet where table_name='aq_yh_info' and col_name='yhlb';
insert into st_table_paramet value ('aq_yh_info','yhlb','01','隐患类别','设备');
insert into st_table_paramet value ('aq_yh_info','yhlb','02','隐患类别','工艺');
insert into st_table_paramet value ('aq_yh_info','yhlb','03','隐患类别','安全管理');
insert into st_table_paramet value ('aq_yh_info','yhlb','04','隐患类别','电气');
insert into st_table_paramet value ('aq_yh_info','yhlb','05','隐患类别','仪表');
insert into st_table_paramet value ('aq_yh_info','yhlb','06','隐患类别','工程');

delete from st_table_paramet where table_name='aq_yh_info' and col_name='yhjb';
insert into st_table_paramet value ('aq_yh_info','yhjb','1','隐患级别','一般');
insert into st_table_paramet value ('aq_yh_info','yhjb','2','隐患级别','重大');

delete from st_table_paramet where table_name='aq_yh_info' and col_name='ly';
insert into st_table_paramet value ('aq_yh_info','ly','1','隐患来源','隐患收购');
insert into st_table_paramet value ('aq_yh_info','ly','2','隐患来源','自述旬报');
insert into st_table_paramet value ('aq_yh_info','ly','3','隐患来源','安全检查');

delete from st_table_paramet where table_name='aq_yh_info' and col_name='zt';
insert into st_table_paramet value ('aq_yh_info','zt','1','隐患状态','正在整改');
insert into st_table_paramet value ('aq_yh_info','zt','2','隐患状态','逾期未整改');
insert into st_table_paramet value ('aq_yh_info','zt','3','隐患状态','整改完成');
insert into st_table_paramet value ('aq_yh_info','zt','4','隐患状态','分厂已验收');
insert into st_table_paramet value ('aq_yh_info','zt','0','隐患状态','复查通过');

delete from st_table_paramet where table_name='aq_yh_yhjc' and col_name='jclx';
insert into st_table_paramet value ('aq_yh_yhjc','jclx','1','检查类型','日常检查');
insert into st_table_paramet value ('aq_yh_yhjc','jclx','2','检查类型','综合检查');
insert into st_table_paramet value ('aq_yh_yhjc','jclx','3','检查类型','隐患排查');
insert into st_table_paramet value ('aq_yh_yhjc','jclx','4','检查类型','专项检查');
insert into st_table_paramet value ('aq_yh_yhjc','jclx','5','检查类型','季度检查');

delete from st_table_paramet where table_name='aq_yh_zgjl' and col_name='zgjg';
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','0','整改状态','正在整改');
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','1','整改状态','分厂验收未通过');
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','2','整改状态','公司验收未通过');
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','3','整改状态','申请延时');
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','4','整改状态','复查通过');
insert into st_table_paramet value ('aq_yh_zgjl','zgjg','5','整改状态','逾期未整改');
commit;

delete from st_err_msg where err_code = '200009';
insert into st_err_msg value ('200009', '用户名重复！');
delete from st_err_msg where err_code = '200010';
insert into st_err_msg value ('200010', '只有录入人才能修改！');
delete from st_err_msg where err_code = '200010';
insert into st_err_msg value ('200010', '只有录入人才能修改！');
delete from st_err_msg where err_code = '200011';
insert into st_err_msg value ('200011', '必须由 !#! 核实！');
delete from st_err_msg where err_code = '200012';
insert into st_err_msg value ('200012', '必须由 !#! 确认！');
delete from st_err_msg where err_code = '200013';
insert into st_err_msg value ('200013', '登记的三违还未核实！');
delete from st_err_msg where err_code = '200014';
insert into st_err_msg value ('200014', '只能修改未通报的事故！');
delete from st_err_msg where err_code = '200015';
insert into st_err_msg value ('200015', '事故不存在！');
delete from st_err_msg where err_code = '200016';
insert into st_err_msg value ('200016', '!#! 必须是数字！');
delete from st_err_msg where err_code = '200017';
insert into st_err_msg value ('200017', '必须由 !#! 通报！');
commit;

delete from st_service_bean where service_code = 'P11011';
insert into st_service_bean value ('P11011', 'updateUsePl', '修改用户信息', 'Y', 'pl');
delete from st_service_bean where service_code = 'S12001';
insert into st_service_bean value ('S12001', 'getPageSw', '获取一页三违信息', 'Y', 'sw');
delete from st_service_bean where service_code = 'P12001';
insert into st_service_bean value ('P12001', 'inputSw', '三违录入', 'Y', 'sw');
delete from st_service_bean where service_code = 'P12002';
insert into st_service_bean value ('P12002', 'updateSw', '修改三违', 'Y', 'sw');
delete from st_service_bean where service_code = 'P12003';
insert into st_service_bean value ('P12003', 'hsSw', '核实三违', 'Y', 'sw');
delete from st_service_bean where service_code = 'P12004';
insert into st_service_bean value ('P12004', 'qrSw', '确认三违', 'Y', 'sw');
delete from st_service_bean where service_code = 'P12005';
insert into st_service_bean value ('P12005', 'saveSg', '录入事故', 'Y', 'sg');
delete from st_service_bean where service_code = 'P12006';
insert into st_service_bean value ('P12006', 'publishSg', '通报事故', 'Y', 'sg');
delete from st_service_bean where service_code = 'P12007';
insert into st_service_bean value ('P12007', 'updateSg', '修改事故', 'Y', 'sg');
delete from st_service_bean where service_code = 'S12002';
insert into st_service_bean value ('S12002', 'searchSg', '查询事故', 'Y', 'sg');
delete from st_service_bean where service_code = 'S12003';
insert into st_service_bean value ('S12003', 'searchNoPubSg', '查询未通报的事故', 'Y', 'sg');
commit;


DROP TABLE IF EXISTS `aq_sw_info`;
CREATE TABLE `aq_sw_info` (
  `sw_id` varchar(20) NOT NULL COMMENT '三违编号',
  `swsj` datetime NOT NULL COMMENT '三违时间',
  `swdd` varchar(200) NOT NULL COMMENT '三违地点',
  `swry` varchar(200) NOT NULL COMMENT '三违人员',
  `swxx` varchar(500) NOT NULL COMMENT '三违现象',
  `swfl` varchar(2) NOT NULL COMMENT '三违分类',
  `ssdw` varchar(20) NOT NULL COMMENT '三违单位',
  `swbz` varchar(200) DEFAULT NULL COMMENT '三违备注',
  `fxr` varchar(50) DEFAULT NULL COMMENT '发现人',
  `fxrxm` varchar(50) NOT NULL COMMENT '发现人姓名',
  `fxrbm` varchar(20) DEFAULT NULL COMMENT '发现人所在部门',
  `fxsj` datetime NOT NULL COMMENT '发现时间',
  `hsr` varchar(20) DEFAULT NULL COMMENT '核实人',
  `hsrxm` varchar(20) DEFAULT NULL COMMENT '核实人姓名',
  `hsrbm` varchar(20) DEFAULT NULL COMMENT '核实人所在部门',
  `hssj` datetime DEFAULT NULL COMMENT '核实时间',
  `hsbz` varchar(200) DEFAULT NULL COMMENT '核实备注',
  `qrr` varchar(20) DEFAULT NULL COMMENT '确认人',
  `qrrxm` varchar(20) DEFAULT NULL COMMENT '确认人姓名',
  `qrrbm` varchar(20) DEFAULT NULL COMMENT '确实人所在部门',
  `qrsj` datetime DEFAULT NULL COMMENT '确认时间',
  `qrbz` varchar(200) DEFAULT NULL COMMENT '确认备注',
  PRIMARY KEY (`sw_id`),
  KEY `aq_sw_info_fk1` (`ssdw`),
  KEY `aq_sw_info_fk2` (`hsr`),
  KEY `aq_sw_info_fk3` (`hsrbm`),
  KEY `aq_sw_info_fk4` (`qrr`),
  KEY `aq_sw_info_fk5` (`qrrbm`),
  CONSTRAINT `aq_sw_info_fk1` FOREIGN KEY (`ssdw`) REFERENCES `pl_dep_info` (`dep_id`),
  CONSTRAINT `aq_sw_info_fk2` FOREIGN KEY (`hsr`) REFERENCES `pl_user` (`username`),
  CONSTRAINT `aq_sw_info_fk3` FOREIGN KEY (`hsrbm`) REFERENCES `pl_dep_info` (`dep_id`),
  CONSTRAINT `aq_sw_info_fk4` FOREIGN KEY (`qrr`) REFERENCES `pl_user` (`username`),
  CONSTRAINT `aq_sw_info_fk5` FOREIGN KEY (`qrrbm`) REFERENCES `pl_dep_info` (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pl_oper_log`;
CREATE TABLE `pl_oper_log` (
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `oper_name` varchar(50) NOT NULL COMMENT '操作名',
  `oper_time` datetime NOT NULL COMMENT '操作时间',
  `before_data` varchar(1000) DEFAULT NULL COMMENT '操作前的数据',
  `after_data` varchar(1000) DEFAULT NULL COMMENT '操作后的数据',
  `module` varchar(6) DEFAULT NULL COMMENT '模块名,参考system.module',
  `m_id` varchar(20) DEFAULT NULL COMMENT '菜单名',
  PRIMARY KEY (`username`,`oper_name`,`oper_time`),
  KEY `pl_oper_log_m_fk` (`m_id`),
  CONSTRAINT `pl_oper_log_m_fk` FOREIGN KEY (`m_id`) REFERENCES `pl_menu` (`m_id`),
  CONSTRAINT `pl_oper_log_u_fk` FOREIGN KEY (`username`) REFERENCES `pl_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pl_oper_log_history`;
CREATE TABLE `pl_oper_log_history` (
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `oper_name` varchar(50) DEFAULT NULL COMMENT '操作名',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `before_data` varchar(1000) DEFAULT NULL COMMENT '操作前的数据',
  `after_data` varchar(1000) DEFAULT NULL COMMENT '操作后的数据',
  `module` varchar(6) DEFAULT NULL COMMENT '模块名,参考system.module',
  `m_id` varchar(20) DEFAULT NULL COMMENT '菜单名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
