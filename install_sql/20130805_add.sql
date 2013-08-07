
delete from aq_yh_yhjc;
delete from aq_yh_yhsg;
delete from aq_yh_zgjl;
delete from aq_yh_zsxb;


-- ----------------------------
-- Table structure for `aq_yh_info`
-- ----------------------------
DROP TABLE IF EXISTS `aq_yh_info`;
CREATE TABLE `aq_yh_info` (
  `yh_id` varchar(20) NOT NULL DEFAULT '' COMMENT '隐患编号',
  `yhmc` varchar(100) NOT NULL COMMENT '隐患名称',
  `yhlb` varchar(2) DEFAULT NULL COMMENT '隐患类别',
  `yhms` varchar(500) DEFAULT NULL COMMENT '隐患描述',
  `yhjb` varchar(1) DEFAULT NULL COMMENT '隐患级别',
  `yhdw` varchar(20) NOT NULL COMMENT '隐患单位',
  `yhdd` varchar(200) NOT NULL COMMENT '隐患地点',
  `jcsj` datetime DEFAULT NULL COMMENT '检查时间',
  `lrr` varchar(20) NOT NULL COMMENT '录入人',
  `lrsj` datetime NOT NULL COMMENT '录入时间',
  `zt` varchar(1) NOT NULL COMMENT '隐患状态',
  `ly` varchar(1) NOT NULL COMMENT '检查类型（来源）',
  PRIMARY KEY (`yh_id`),
  KEY `aq_yh_info_fk1` (`yhdw`),
  KEY `aq_yh_info_fk2` (`lrr`),
  CONSTRAINT `aq_yh_info_fk1` FOREIGN KEY (`yhdw`) REFERENCES `pl_dep_info` (`dep_id`),
  CONSTRAINT `aq_yh_info_fk2` FOREIGN KEY (`lrr`) REFERENCES `pl_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `aq_yh_zg`
-- ----------------------------
DROP TABLE IF EXISTS `aq_yh_zg`;
CREATE TABLE `aq_yh_zg` (
  `zg_id` varchar(20) NOT NULL,
  `yh_id` varchar(20) NOT NULL,
  `kssj` datetime NOT NULL COMMENT '开始时间',
  `pzsx` datetime NOT NULL COMMENT '批准时间',
  `lazy_zt` varchar(1) NOT NULL COMMENT '延时状态',
  `yszt` varchar(1) NOT NULL COMMENT '验收状态',
  `ysr` varchar(20) DEFAULT NULL COMMENT '验收人',
  `ysrxm` varchar(200) DEFAULT NULL,
  `yssj` datetime DEFAULT NULL,
  `ysbz` varchar(500) DEFAULT NULL COMMENT '验收备注',
  `zgbz` varchar(500) DEFAULT NULL COMMENT '整改备注',
  PRIMARY KEY (`zg_id`),
  KEY `aq_yh_zg_fk` (`yh_id`),
  CONSTRAINT `aq_yh_zg_fk` FOREIGN KEY (`yh_id`) REFERENCES `aq_yh_info` (`yh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE
VIEW `aq_yh_zg_v` AS
select `a`.`yh_id` AS `yh_id`,`a`.`yhmc` AS `yhmc`,`a`.`yhlb` AS `yhlb`,`a`.`yhms` AS `yhms`,`a`.`yhjb` AS `yhjb`,`a`.`yhdw` AS `yhdw`,`a`.`yhdd` AS `yhdd`,`a`.`jcsj` AS `jcsj`,`a`.`lrr` AS `lrr`,`a`.`lrsj` AS `lrsj`,`a`.`zt` AS `zt`,`a`.`ly` AS `ly`,`b`.`zg_id` AS `zg_id`,`b`.`kssj` AS `kssj`,`b`.`pzsx` AS `pzsx`,`b`.`lazy_zt` AS `lazy_zt`,`b`.`yszt` AS `yszt`,`b`.`ysr` AS `ysr`,`b`.`ysrxm` AS `ysrxm`,`b`.`yssj` AS `yssj`,`b`.`ysbz` AS `ysbz`,`b`.`zgbz` AS `zgbz` from (`aq_yh_info` `a` join `aq_yh_zg` `b`) where (`a`.`yh_id` = `b`.`yh_id`) ;

delete from st_table_paramet where table_name = 'aq_yh_info' and col_name='ly';

insert into st_table_paramet value ('aq_yh_info','ly','1','隐患类型','上级检查');
insert into st_table_paramet value ('aq_yh_info','ly','2','隐患类型','日常检查');
insert into st_table_paramet value ('aq_yh_info','ly','3','隐患类型','隐患排查');
insert into st_table_paramet value ('aq_yh_info','ly','4','隐患类型','专项检查');
insert into st_table_paramet value ('aq_yh_info','ly','5','隐患类型','综合检查');


insert into st_table_paramet value ('aq_yh_zg','lazy_zt','1','延时状态','未超时');
insert into st_table_paramet value ('aq_yh_zg','lazy_zt','2','延时状态','申请延时');
insert into st_table_paramet value ('aq_yh_zg','lazy_zt','3','延时状态','批准延时');
insert into st_table_paramet value ('aq_yh_zg','lazy_zt','4','延时状态','超时');

insert into st_table_paramet value ('aq_yh_zg','yszt','1','验收状态','正在整改');
insert into st_table_paramet value ('aq_yh_zg','yszt','2','验收状态','申请验收');
insert into st_table_paramet value ('aq_yh_zg','yszt','3','验收状态','验收不通过');
insert into st_table_paramet value ('aq_yh_zg','yszt','4','验收状态','验收通过');

delete from st_table_paramet  where table_name='aq_yh_info' and col_name='zt';
insert into st_table_paramet value ('aq_yh_info','zt','1','隐患状态','正在整改');
insert into st_table_paramet value ('aq_yh_info','zt','2','隐患状态','申请验收');
insert into st_table_paramet value ('aq_yh_info','zt','3','隐患状态','申请延时');
insert into st_table_paramet value ('aq_yh_info','zt','4','隐患状态','逾期未整改');
insert into st_table_paramet value ('aq_yh_info','zt','5','隐患状态','验收通过');



insert into pl_menu value ('yhzg', '隐患整改管理', '', '隐患整改管理', '1', 'aq', '02020000');
insert into pl_menu value ('yhzg01', '隐患登记', 'aq/yhzg/yhzg01', '隐患登记', '0', 'yhzg', '02020001');
insert into pl_menu value ('yhzg02', '整改验收申请', 'aq/yhzg/yhzg02', '整改验收申请', '0', 'yhzg', '02020002');
insert into pl_menu value ('yhzg03', '整改延时申请', 'aq/yhzg/yhzg03', '整改延时申请', '0', 'yhzg', '02020003');
insert into pl_menu value ('yhzg04', '整改验收审批', 'aq/yhzg/yhzg04', '整改验收审批', '0', 'yhzg', '02020004');
insert into pl_menu value ('yhzg05', '整改延时审批', 'aq/yhzg/yhzg05', '整改延时审批', '0', 'yhzg', '02020005');
insert into pl_menu value ('yhzg06', '整改通报', 'aq/yhzg/yhzg06', '整改通报', '0', 'yhzg', '02020006');
insert into pl_menu value ('yhzg07', '隐患整改查询', 'aq/yhzg/yhzg07', '隐患整改查询', '0', 'yhzg', '02020007');
insert into pl_menu value ('yhzg08', '隐患删除', 'aq/yhzg/yhzg08', '隐患删除', '0', 'yhzg', '02020008');
insert into pl_menu value ('yhzg09', '隐患整改历史', 'aq/yhzg/yhzg09', '隐患整改历史', '0', 'yhzg', '02020009');

insert into st_service_bean value ('S12021','searchYhdwYh','获取某单位的隐患信息','Y','yh');
insert into st_service_bean value ('S12022','getZgYh','获取隐患的整改记录','Y','yh');
insert into st_service_bean value ('S12023','searchZtYh','获取某状态的隐患信息','Y','yh');
insert into st_service_bean value ('P12020','applyYsYh','申请验收','Y','yh');
insert into st_service_bean value ('P12021','applyLazyYh','申请延时','Y','yh');
insert into st_service_bean value ('P12022','checkYsYh','审核验收申请','Y','yh');
insert into st_service_bean value ('P12023','lazyYh','审核延时申请','Y','yh');
insert into st_service_bean value ('S12024','pageYh','隐患分页查询','Y','yh');
insert into st_service_bean value ('S12025','pageTbYh','整改通报分页查询','Y','yh');
insert into st_service_bean value ('S12026','pageLsYh','隐患整改历史','Y','yh');
insert into st_service_bean value ('P12024','delYh','删除隐患','Y','yh');