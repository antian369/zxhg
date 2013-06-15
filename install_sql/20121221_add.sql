
delete from st_table_paramet where table_name='sc' and col_name='cpbh' and col_value='012';
INSERT INTO st_table_paramet VALUES ('sc', 'cpbh', '012', '产品编号', '送乙二醇合成气');

delete from pl_menu where m_id in ('cl0012', 'cl0062');
insert into pl_menu values ('cl0012','送乙二醇合成气量录入','sc/cl/cl0012','送乙二醇合成气生产量录入','0','cl', '03050012');
insert into pl_menu values ('cl0062','送乙二醇合成气量计划','sc/cl/cl0062','送乙二醇合成气生产量计划录入','0','cl', '03050062');
commit;