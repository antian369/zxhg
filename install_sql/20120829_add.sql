delete from pl_menu where m_id = 'sc';
insert into pl_menu values ('sc', '生产管理', '', '生产管理', '1','');

delete from pl_menu where m_id in ('ddrb');
insert into pl_menu values ('ddrb', '调度日报信息', '', '调度日报信息', '1','sc');
insert into pl_menu values ('ddrb01', '调度日报审核', 'sc/ddrb/ddrb01', '调度日报审核', '0','ddrb');
insert into pl_menu values ('ddrb02', '调度日报查询', 'sc/ddrb/ddrb02', '调度日报查询', '0','ddrb');

delete from pl_menu where m_id in ('gyjc', 'gyjc01', 'gyjc02');
insert into pl_menu values ('gyjc', '工艺检查信息', '', '工艺检查信息', '1','sc');
insert into pl_menu values ('gyjc01', '工艺检查登记', 'sc/gyjc/gyjc01', '工艺检查登记', '0','gyjc');
insert into pl_menu values ('gyjc02', '工艺检查查询', 'sc/gyjc/gyjc02', '工艺检查查询', '0','gyjc');

delete from pl_menu where m_id in ('gyrz', 'gyrz01', 'gyrz02');
insert into pl_menu values ('gyrz', '工艺日志信息', '', '工艺日志信息', '1','sc');
insert into pl_menu values ('gyrz01', '工艺日志登记', 'sc/gyrz/gyrz01', '工艺日志登记', '0','gyrz');
insert into pl_menu values ('gyrz02', '工艺日志查询', 'sc/gyrz/gyrz02', '工艺日志查询', '0','gyrz');

delete from pl_menu where m_id in ('dh', 'dh0001', 'dh0002', 'dh0003', 'dh0004', 'dh0005', 'dh0006', 'dh0007');
insert into pl_menu values ('dh', '产品单耗信息', '', '产品单耗信息', '1','sc');
insert into pl_menu values ('dh0001', '原煤录入', 'sc/dh/dh0001', '原煤单耗录入', '0','dh');
insert into pl_menu values ('dh0002', '燃煤录入', 'sc/dh/dh0002', '燃煤单耗录入', '0','dh');
insert into pl_menu values ('dh0003', '原水录入', 'sc/dh/dh0003', '原水单耗录入', '0','dh');
insert into pl_menu values ('dh0004', '外电录入', 'sc/dh/dh0004', '外电单耗录入', '0','dh');
insert into pl_menu values ('dh0005', '气耗录入', 'sc/dh/dh0005', '气耗录入', '0','dh');
insert into pl_menu values ('dh0006', '月报表', 'sc/dh/dh0006', '单耗月报表', '0','dh');
insert into pl_menu values ('dh0007', '曲线图', 'sc/dh/dh0007', '单耗曲线图', '0','dh');


delete from pl_menu where m_id in ('cl', 'cl0001', 'cl0002', 'cl0003', 'cl0004', 'cl0005', 'cl0006', 'cl0007', 'cl0008', 'cl0009', 'cl0010');
insert into pl_menu values ('cl', '产量信息', '', '产品产量信息', '1','sc');
insert into pl_menu values ('cl0001', '粗醇录入', 'sc/cl/cl0001', '粗醇产量录入', '0','cl');
insert into pl_menu values ('cl0002', '精醇录入', 'sc/cl/cl0002', '精醇产量录入', '0','cl');
insert into pl_menu values ('cl0003', '液氧录入', 'sc/cl/cl0003', '液氧产量录入', '0','cl');
insert into pl_menu values ('cl0004', '液氮录入', 'sc/cl/cl0004', '液氮产量录入', '0','cl');
insert into pl_menu values ('cl0005', '蒸汽录入', 'sc/cl/cl0005', '蒸汽产量录入', '0','cl');
insert into pl_menu values ('cl0006', '金山化工用蒸汽', 'sc/cl/cl0006', '金山化工用蒸汽产量录入', '0','cl');
insert into pl_menu values ('cl0007', '硫磺录入', 'sc/cl/cl0007', '硫磺产量录入', '0','cl');
insert into pl_menu values ('cl0008', '硫酸铵录入', 'sc/cl/cl0008', '硫酸铵产量录入', '0','cl');
insert into pl_menu values ('cl0009', '自发电录入', 'sc/cl/cl0009', '自发电产量录入', '0','cl');
insert into pl_menu values ('cl0010', '有效煤气录入', 'sc/cl/cl0010', '有效煤气产量录入', '0','cl');


delete from pl_menu where m_id in ('rxh', 'rxh001', 'rxh002', 'rxh003', 'rxh004', 'rxh005', 'rxh006', 'rxh007');
insert into pl_menu values ('rxh', '日消耗', '', '日消耗信息管理', '1','sc');
insert into pl_menu values ('rxh001', '甲醇耗有效气', 'sc/rxh/rxh001', '吨甲醇耗有效气录入', '0','rxh');
insert into pl_menu values ('rxh002', '有效气耗原煤', 'sc/rxh/rxh002', '千方有效气耗原煤录入', '0','rxh');
insert into pl_menu values ('rxh003', '蒸汽耗燃料煤', 'sc/rxh/rxh003', '吨蒸汽耗燃料煤录入', '0','rxh');
insert into pl_menu values ('rxh004', '原料煤', 'sc/rxh/rxh004', '原料煤消耗录入', '0','rxh');
insert into pl_menu values ('rxh005', '燃料煤', 'sc/rxh/rxh005', '燃料煤消耗录入', '0','rxh');
insert into pl_menu values ('rxh006', '用电量', 'sc/rxh/rxh006', '用电量消耗录入', '0','rxh');
insert into pl_menu values ('rxh007', '原水量', 'sc/rxh/rxh007', '原水量消耗录入', '0','rxh');

delete from pl_menu where m_id in ('xs', 'xs0001', 'xs0002', 'xs0003', 'xs0004');
insert into pl_menu values ('xs', '内部销售信息', '', '内部销售信息管理', '1','sc');
insert into pl_menu values ('xs0001', '粗甲醇', 'sc/xs/xs0001', '粗甲醇销售录入', '0','xs');
insert into pl_menu values ('xs0002', '精甲醇', 'sc/xs/xs0002', '精甲醇销售录入', '0','xs');
insert into pl_menu values ('xs0003', '液氧', 'sc/xs/xs0003', '液氧销售录入', '0','xs');
insert into pl_menu values ('xs0004', '液氮', 'sc/xs/xs0004', '液氮销售录入', '0','xs');

delete from pl_menu where m_id in ('sczs', 'sczs01');
insert into pl_menu values ('sczs', '生产情况综述', '', '生产情况综述', '1','sc');
insert into pl_menu values ('sczs01', '综述录入', 'sc/sczs/sczs01', '生产情况综述录入', '0','sczs');


delete from pl_menu where m_id = 'sb';
insert into pl_menu values ('sb', '设备管理', '', '设备管理', '1','');

delete from pl_menu where m_id in ('zlgl', 'zlgl01','zlgl02', 'zlgl03', 'zlgl04', 'zlgl05','zlgl06', 'zlgl07', 'zlgl08');
insert into pl_menu values ('zlgl', '设备资料管理', '', '设备资料管理', '1','sb');
insert into pl_menu values ('zlgl01', '工艺流程查询', 'sb/zlgl/zlgl01', '工艺流程查询', '0','zlgl');
insert into pl_menu values ('zlgl02', '工艺流程管理', 'sb/zlgl/zlgl02', '工艺流程管理', '0','zlgl');
insert into pl_menu values ('zlgl03', '工艺设备查询', 'sb/zlgl/zlgl03', '工艺设备查询', '0','zlgl');
insert into pl_menu values ('zlgl04', '工艺设备管理', 'sb/zlgl/zlgl04', '工艺设备管理', '0','zlgl');
insert into pl_menu values ('zlgl05', '设备备品查询', 'sb/zlgl/zlgl05', '设备备品查询', '0','zlgl');
insert into pl_menu values ('zlgl06', '设备备品管理', 'sb/zlgl/zlgl06', '设备备品管理', '0','zlgl');
insert into pl_menu values ('zlgl07', '设备资料查询', 'sb/zlgl/zlgl07', '设备资料查询', '0','zlgl');
insert into pl_menu values ('zlgl08', '新增设备资料', 'sb/zlgl/zlgl08', '新增设备资料', '0','zlgl');

delete from pl_menu where m_id like 'sbjx%';
insert into pl_menu values ('sbjx', '设备检修与故障', '', '设备检修与故障', '1','sb');
insert into pl_menu values ('sbjx01', '缺陷故障录入', 'sb/sbjx/sbjx01', '缺陷故障录入', '0','sbjx');
insert into pl_menu values ('sbjx02', '事故记录录入', 'sb/sbjx/sbjx02', '事故记录录入', '0','sbjx');
insert into pl_menu values ('sbjx03', '检修检查录入', 'sb/sbjx/sbjx03', '检修检查录入', '0','sbjx');
insert into pl_menu values ('sbjx04', '检修经济效果分析录入', 'sb/sbjx/sbjx04', '检修经济效果分析录入', '0','sbjx');
insert into pl_menu values ('sbjx05', '技改技措录入', 'sb/sbjx/sbjx05', '技改技措录入', '0','sbjx');
insert into pl_menu values ('sbjx06', '缺陷故障查询', 'sb/sbjx/sbjx06', '缺陷故障查询', '0','sbjx');
insert into pl_menu values ('sbjx07', '事故记录查询', 'sb/sbjx/sbjx07', '事故记录查询', '0','sbjx');
insert into pl_menu values ('sbjx08', '检修检查查询', 'sb/sbjx/sbjx08', '检修检查查询', '0','sbjx');
insert into pl_menu values ('sbjx09', '检修经济效果分析查询', 'sb/sbjx/sbjx09', '检修经济效果分析查询', '0','sbjx');
insert into pl_menu values ('sbjx10', '技改技措查询', 'sb/sbjx/sbjx10', '技改技措查询', '0','sbjx');


delete from pl_menu where m_id in ('sbzt', 'sbzt01','sbzt02', 'sbzt03');
insert into pl_menu values ('sbzt', '设备状态管理', '', '设备状态管理', '1','sb');
insert into pl_menu values ('sbzt01', '设备总目录', 'sb/sbzt/sbzt01', '设备总目录', '0','sbzt');
insert into pl_menu values ('sbzt02', '设备状态修改', 'sb/sbzt/sbzt02', '设备状态修改', '0','sbzt');
insert into pl_menu values ('sbzt03', '设备检修提醒', 'sb/sbzt/sbzt03', '设备检修提醒', '0','sbzt');

delete from pl_menu where m_id in ('djrz', 'djrz01','djrz02', 'djrz03', 'djrz04');
insert into pl_menu values ('djrz', '点检日志管理', '', '点检日志管理', '1','sb');
insert into pl_menu values ('djrz01', '点检日志记录', 'sb/djrz/djrz01', '点检日志记录', '0','djrz');
insert into pl_menu values ('djrz02', '点检日志上报', 'sb/djrz/djrz02', '点检日志上报', '0','djrz');
insert into pl_menu values ('djrz03', '点检班级设置', 'sb/djrz/djrz03', '点检班级设置', '0','djrz');

delete from pl_menu where m_id in ('yxsj', 'yxsj01','yxsj02');
insert into pl_menu values ('yxsj', '主要装置运行时间', '', '主要装置运行时间', '1','sb');
insert into pl_menu values ('yxsj01', '运行时间录入', 'sb/yxsj/yxsj01', '运行时间录入', '0','yxsj');
insert into pl_menu values ('yxsj02', '运行时间统计', 'sb/yxsj/yxsj02', '运行时间统计', '0','yxsj');

delete from pl_menu where m_id in ('bq', 'bq0001', 'bq0002', 'bq0003', 'bq0004');
insert into pl_menu values ('bq', '收发便签', '', '收发便签', '1','');
insert into pl_menu values ('bq0001', '发布公告', 'bq/bq0001', '发布公告', '0','bq');
insert into pl_menu values ('bq0002', '发送私信', 'bq/bq0002', '发送私信', '0','bq');
insert into pl_menu values ('bq0003', '查看公告', 'bq/bq0003', '查看公告', '0','bq');
insert into pl_menu values ('bq0004', '查看私信', 'bq/bq0004', '查看私信', '0','bq');
