ALTER TABLE `aq_sg_info`
MODIFY COLUMN `zywhp`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要危化品(改为事故概要)' AFTER `jjss`;
ALTER TABLE `aq_sg_info`
MODIFY COLUMN `sgdw`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事故单位' AFTER `sg_id`;

insert into pl_menu value ('sg0004', '通报事故', 'aq/sg/sg0004', '通报事故', '0', 'sg', '02030004');
insert into pl_menu value ('sg0005', '事故删除', 'aq/sg/sg0005', '事故删除', '0', 'sg', '02030005');

insert into st_service_bean value ('P12019','delSg','删除事故','Y','sg');
insert into st_service_bean value ('S12020','getTbSg','已通报事故','Y','sg');

