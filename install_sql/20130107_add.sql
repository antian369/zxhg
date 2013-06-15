ALTER TABLE `sc_rxs`
ADD COLUMN `ya`  double NOT NULL DEFAULT 0 COMMENT '液氩' AFTER `yd`,
ADD COLUMN `lh`  double NOT NULL DEFAULT 0 COMMENT '硫磺' AFTER `ya`,
ADD COLUMN `lsa`  double NOT NULL DEFAULT 0 COMMENT '硫酸铵' AFTER `lh`;

DROP TABLE IF EXISTS `sc_rdh`;
CREATE TABLE `sc_rdh` (
  `scrq` date NOT NULL COMMENT '生产日期',
  `dhlx` varchar(1) NOT NULL COMMENT '单耗类型，sc.dhlx',
  `hym` double DEFAULT NULL COMMENT '精甲醇(t)耗原煤',
  `hrm` double DEFAULT NULL COMMENT '精甲醇(t)耗燃煤',
  `hs` double DEFAULT NULL COMMENT '精甲醇(t)耗水',
  `hwd` double DEFAULT NULL COMMENT '精甲醇(t)耗外电',
  `hq` double DEFAULT NULL COMMENT '吨甲醇耗气',
  `qhym` double DEFAULT NULL COMMENT '千方有效气耗原煤',
  `zqhm` double DEFAULT NULL COMMENT '吨蒸汽耗燃料煤',
  PRIMARY KEY (`scrq`,`dhlx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into st_table_paramet value ('sc','cpbh','013','产品编号','送永金蒸汽');
insert into st_table_paramet value ('sc','dhlx','b','单耗类型','白班单耗');
insert into st_table_paramet value ('sc','dhlx','y','单耗类型','夜班单耗');
insert into st_table_paramet value ('sc','dhlx','z','单耗类型','中班单耗');
insert into st_table_paramet value ('sc','dhlx','d','单耗类型','全天单耗');
insert into st_table_paramet value ('sc','dhlx','m','单耗类型','当月单耗');

insert into pl_menu value ('cl0013', '送永金蒸汽量录入', 'sc/cl/cl0013', '送永金蒸汽量录入', '0', 'cl', '03050013');
insert into pl_menu value ('cl0063', '送永金蒸汽量计划', 'sc/cl/cl0063', '送永金蒸汽量计划录入', '0', 'cl', '03050063');

delete from pl_role_limit where m_id in ('dh0001','rxh001','rxh002','rxh003','rxh101','rxh102','rxh103');
delete from pl_user_limit where m_id in ('dh0001','rxh001','rxh002','rxh003','rxh101','rxh102','rxh103');
delete from pl_menu where m_id in ('dh0001','rxh001','rxh002','rxh003','rxh101','rxh102','rxh103');