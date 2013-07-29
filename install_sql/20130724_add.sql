delete from pl_menu where m_id in ('sw0004','sw0005','sw0006','sw0007');
insert into pl_menu value ('sw0004', '三违修改', 'aq/sw/sw0004', '三违修改', '0', 'sw', '02010004');
insert into pl_menu value ('sw0005', '三违通报', 'aq/sw/sw0005', '三违通报', '0', 'sw', '02010005');
insert into pl_menu value ('sw0006', '三违查询', 'aq/sw/sw0006', '三违查询', '0', 'sw', '02010006');
insert into pl_menu value ('sw0007', '三违删除', 'aq/sw/sw0007', '三违删除', '0', 'sw', '02010007');

insert into st_table_paramet value ('aq_sw_info','hslx','1','核实类型','内部核实');
insert into st_table_paramet value ('aq_sw_info','hslx','2','核实类型','公司核实');

insert into st_service_bean value ('S12016','getHsSw','获取需要核实的三违','Y','sw');
insert into st_service_bean value ('S12017','getQrSw','获取待确认的三违','Y','sw');

insert into st_service_bean value ('S12018','getXgSw','获取可修改的三违数据','Y','sw');
insert into st_service_bean value ('S12019','getTbSw','获取通报的三违','Y','sw');
insert into st_service_bean value ('P12018','delSw','删除三违','Y','sw');

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
  `hslx` varchar(1) NOT NULL COMMENT '核实类型',
  `fxr` varchar(50) DEFAULT NULL COMMENT '发现人',
  `fxrxm` varchar(50) NOT NULL COMMENT '发现人姓名',
  `fxrbm` varchar(20) DEFAULT NULL COMMENT '发现人所在部门',
  `fxsj` datetime DEFAULT NULL COMMENT '发现时间',
  `lrr` varchar(20) NOT NULL COMMENT '录入人',
  `lrrbm` varchar(20) NOT NULL COMMENT '录入人部门',
  `lrsj` datetime NOT NULL COMMENT '录入时间',
  `hsr` varchar(20) DEFAULT NULL COMMENT '核实人',
  `hsrxm` varchar(20) DEFAULT NULL COMMENT '核实人姓名',
  `hsrbm` varchar(20) DEFAULT NULL COMMENT '核实人所在部门',
  `hssj` datetime DEFAULT NULL COMMENT '核实时间',
  `hsbz` varchar(200) DEFAULT NULL COMMENT '核实备注',
  `cfyj` varchar(200) DEFAULT NULL COMMENT '处罚依据',
  `cfcs` varchar(50) DEFAULT NULL COMMENT '处罚措施',
  `cfje` int(11) DEFAULT NULL COMMENT '处罚金额',
  `qrr` varchar(20) DEFAULT NULL COMMENT '确认人',
  `qrrxm` varchar(20) DEFAULT NULL COMMENT '确认人姓名',
  `qrrbm` varchar(20) DEFAULT NULL COMMENT '确实人所在部门',
  `qrsj` datetime DEFAULT NULL COMMENT '确认时间',
  `qrbz` varchar(200) DEFAULT NULL COMMENT '确认备注',
  `zt` varchar(2) NOT NULL COMMENT '状态',
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
