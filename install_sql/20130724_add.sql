delete from pl_menu where m_id in ('sw0004','sw0005','sw0006','sw0007');
insert into pl_menu value ('sw0004', '三违修改', 'aq/sw/sw0004', '三违修改', '0', 'sw', '02010004');
insert into pl_menu value ('sw0005', '三违通报', 'aq/sw/sw0005', '三违通报', '0', 'sw', '02010005');
insert into pl_menu value ('sw0006', '三违查询', 'aq/sw/sw0006', '三违查询', '0', 'sw', '02010006');
insert into pl_menu value ('sw0007', '三违删除', 'aq/sw/sw0007', '三违删除', '0', 'sw', '02010007');

insert into st_table_paramet value ('aq_sw_info','hslx','1','核实类型','内部核实');
insert into st_table_paramet value ('aq_sw_info','hslx','2','核实类型','公司核实');

insert into st_service_bean value ('S12016','getHsSw','获取需要核实的三违','Y','sw');
insert into st_service_bean value ('S12017','getQrSw','获取待确认的三违','Y','sw');
