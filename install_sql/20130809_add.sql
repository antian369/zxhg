ALTER TABLE `zxhg`.`aq_yh_yhsg` ADD COLUMN `fxsj` datetime COMMENT '发现时间'  AFTER `fxrxm` ;

ALTER TABLE `zxhg`.`aq_yh_yhsg` DROP FOREIGN KEY `aq_yhsg_fk2` ;
ALTER TABLE `zxhg`.`aq_yh_yhsg` DROP COLUMN `sgzt` , DROP COLUMN `jiangjin` , DROP COLUMN `hzsj` , DROP COLUMN `hzrxm` , DROP COLUMN `hzr` , ADD COLUMN `yhlb` VARCHAR(1) NULL COMMENT '隐患类别'  AFTER `djsj` , ADD COLUMN `zgr` VARCHAR(50) NULL COMMENT '整改人'  AFTER `yhlb` , ADD COLUMN `qrr` VARCHAR(20) NULL COMMENT '确认人'  AFTER `zgr` , ADD COLUMN `qrrxm` VARCHAR(50) NULL COMMENT '确认人姓名'  AFTER `qrr` , ADD COLUMN `zgqk` VARCHAR(500) NULL  AFTER `qrrxm` , ADD COLUMN `yhjb` VARCHAR(1) NULL COMMENT '隐患级别'  AFTER `zgqk` , ADD COLUMN `rdr` VARCHAR(20) NULL COMMENT '认定人'  AFTER `yhjb` , ADD COLUMN `rdrxm` VARCHAR(50) NULL COMMENT '认定人姓名'  AFTER `rdr` , ADD COLUMN `rdbz` VARCHAR(500) NULL COMMENT '认定备注'  AFTER `rdrxm` , ADD COLUMN `zt` VARCHAR(1) NULL COMMENT '状态'  AFTER `rdbz`
, DROP INDEX `aq_yhsg_fk2` ;
ALTER TABLE `zxhg`.`aq_yh_yhsg` DROP COLUMN `fxrxm` , CHANGE COLUMN `fxr` `fxr` VARCHAR(50) NULL DEFAULT NULL COMMENT '发现人'  ;
ALTER TABLE `zxhg`.`aq_yh_yhsg` ADD COLUMN `zgsj` datetime COMMENT '整改时间'  AFTER `zgqk` ;
ALTER TABLE `zxhg`.`aq_yh_yhsg` ADD COLUMN `djrxm` varchar(50) COMMENT ''  AFTER `djr` ;

delete from st_table_paramet where table_name='aq_yh_yhsg' and col_name='yhfl';
delete from st_table_paramet where table_name='aq_yh_yhsg' and col_name='yhlb';
delete from st_table_paramet where table_name='aq_yh_yhsg' and col_name='yhjb';
insert into st_table_paramet value ('aq_yh_yhsg','yhlb','1','隐患分类','机电仪类');
insert into st_table_paramet value ('aq_yh_yhsg','yhlb','2','隐患分类','工艺操作类');
insert into st_table_paramet value ('aq_yh_yhsg','yhlb','3','隐患分类','综合安全类');

insert into st_table_paramet value ('aq_yh_yhsg','yhjb','1','隐患级别','A级');
insert into st_table_paramet value ('aq_yh_yhsg','yhjb','2','隐患级别','B级');
insert into st_table_paramet value ('aq_yh_yhsg','yhjb','3','隐患级别','C级');

delete from st_table_paramet where table_name='aq_yh_yhsg' and col_name='sgzg';

insert into st_table_paramet value ('aq_yh_yhsg','zt','1','隐患收购状态','未核实');
insert into st_table_paramet value ('aq_yh_yhsg','zt','2','隐患收购状态','作废');
insert into st_table_paramet value ('aq_yh_yhsg','zt','3','隐患收购状态','未认定');
insert into st_table_paramet value ('aq_yh_yhsg','zt','4','隐患收购状态','已认定');

UPDATE `zxhg`.`pl_menu` SET `m_name`='分厂内部核实', `m_title`='分厂内部覂', `m_order`='02040002' WHERE `m_id`='yhsg02';
UPDATE `zxhg`.`pl_menu` SET `m_name`='安环部认定', `m_title`='安环部认定', `m_order`='02040003' WHERE `m_id`='yhsg03';
UPDATE `zxhg`.`pl_menu` SET `m_name`='生产部认定', `m_title`='生产部认定', `m_order`='02040004' WHERE `m_id`='yhsg04';
INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`, `m_order`) VALUES ('yhsg05', '机动部认定', 'aq/yhsg/yhsg05', '机动部认定', '0', 'yhsg', '02040005');
UPDATE `zxhg`.`pl_menu` SET `m_order`='02040001' WHERE `m_id`='yhsg01';
INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`, `m_order`) VALUES ('yhsg06', '隐患收购查询', 'aq/yhsg/yhsg06', '隐患收购查询', '0', 'yhsg', '02040006');
INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`, `m_order`) VALUES ('yhsg07', '隐患收购删除', 'aq/yhsg/yhsg07', '隐患收购删除', '0', 'yhsg', '02040007');
INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`) VALUES ('yhsg08', '隐患收购清单', 'aq/yhsg/yhsg08', '隐患收购清单', '0', 'yhsg');


insert into st_service_bean value ('S12027','getDwYhsgYh','获取某单位的隐患收购','Y','yh');
insert into st_service_bean value ('P12025','cancelYhsgYh','作废隐患收购','Y','yh');
insert into st_service_bean value ('P12026','sureYhsgYh','隐患确认','Y','yh');
insert into st_service_bean value ('S12028','getSureYh','获取某单位的隐患收购','Y','yh');
insert into st_service_bean value ('P12027','delYhsg','隐患收购删除','Y','yh');
insert into st_service_bean value ('S12029','sgqdYhsgYh','隐患收购清单','Y','yh');