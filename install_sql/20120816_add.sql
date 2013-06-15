ALTER TABLE `aq_yh_yhsg`
ADD COLUMN `sgzt`  varchar(1) NULL COMMENT '收购单状态' AFTER `jiangjin`;
ALTER TABLE `aq_yh_zgjl`
MODIFY COLUMN `zgrxm`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整改人姓名' AFTER `zgr`,
MODIFY COLUMN `zgcs`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '整改措施' AFTER `zgrxm`,
MODIFY COLUMN `kssj`  datetime NULL COMMENT '开始时间' AFTER `zgcs`,
MODIFY COLUMN `sqsx`  datetime NULL COMMENT '整改申请时限' AFTER `kssj`;



delete from st_table_paramet where table_name='aq_yh_yhsg' and col_name='sgzt';
insert into st_table_paramet value ('aq_yh_yhsg','sgzt','1','收购状态','未收购');
insert into st_table_paramet value ('aq_yh_yhsg','sgzt','2','收购状态','已收购');
insert into st_table_paramet value ('aq_yh_yhsg','sgzt','0','收购状态','不是隐患');

update pl_menu set m_name='整改进度汇报' where m_id='yhsg03';
update pl_menu set m_name='收购单查询' where m_id='yhsg04';
update pl_menu set m_name='安全检查登记' where m_id='yhjc01';
update pl_menu set m_name='分厂验收' where m_id='yhjc02';
update pl_menu set m_name='延时申请' where m_id='yhjc03';
