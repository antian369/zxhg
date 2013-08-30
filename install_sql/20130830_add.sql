
DROP TABLE IF EXISTS `pl_login_his`;
CREATE TABLE `pl_login_his` (
  `opt_id` varchar(20) NOT NULL COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `name` varchar(200) NOT NULL COMMENT '姓名',
  `login_ip` varchar(200) NOT NULL COMMENT 'ip',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '下线时间',
  `on_line` int(11) DEFAULT NULL COMMENT '在线时长(分钟)',
  `zt` varchar(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`opt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into st_table_paramet value ('pl_login_his','zt','0','登录状态','在线');
insert into st_table_paramet value ('pl_login_his','zt','1','登录状态','手动退出');
insert into st_table_paramet value ('pl_login_his','zt','2','登录状态','超时自动退出');
insert into st_table_paramet value ('pl_login_his','zt','3','登录状态','错误操作');

insert into st_service_bean value ('S11011','getOnLinePl','获取在线用户列表','Y','pl');
insert into st_service_bean value ('S11012','repLoginPl','用户登录统计','Y','pl');
insert into st_service_bean value ('S11013','getLoginHisPl','获取详细登录历史','Y','pl');


INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`, `m_order`)
VALUES ('pl0006', '在线用户', 'pl/pl0006', '在线用户列表', '0', 'pl', '99000006');
INSERT INTO `zxhg`.`pl_menu` (`m_id`, `m_name`, `m_url`, `m_title`, `m_type`, `m_super`, `m_order`)
VALUES ('pl0007', '登录统计', 'pl/pl0007', '登录统计', '0', 'pl', '99000007');