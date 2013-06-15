delete from st_service_bean where service_code = 'S13016';

insert into st_service_bean value ('S13016', 'getJcYclSc', '获取甲醇月产量图表数据', 'Y', 'sc');

delete from st_err_msg where err_code = '210036';
insert into st_err_msg value ('210036', '日期格式不正确');
update pl_menu set m_name = '统计图表' where m_id='dh';
update pl_menu set m_name = '甲醇月产量' where m_id='dh0006';
update pl_menu set m_name = '单耗曲线图' where m_id='dh0007';
commit;