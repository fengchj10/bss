CREATE DATABASE /*!32312 IF NOT EXISTS*/`bss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE bss;

DROP TABLE IF EXISTS tb_user;
-- 用户表
CREATE TABLE tb_user(
  id BIGINT AUTO_INCREMENT COMMENT '编号',
  username VARCHAR(100) COMMENT '用户名',
  password VARCHAR(100) COMMENT '密码',
  CONSTRAINT pk_sys_user PRIMARY KEY(id)
) CHARSET=utf8 ENGINE=InnoDB;

INSERT INTO tb_user VALUES(1, 'admin', 'c200174d77e5b30a38f457d07b9d0ec1');


DROP TABLE IF EXISTS `t_bss_data`;
CREATE TABLE `t_bss_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(64) DEFAULT NULL COMMENT '业务号码',
  `cust_id` varchar(64) DEFAULT NULL COMMENT '客户标识',
  `cust_name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `local_net_id` varchar(64) DEFAULT NULL COMMENT '归属地：区号',
  `accept_date` datetime DEFAULT NULL COMMENT '预约受理时间',
  `prod_id` varchar(64) NOT NULL COMMENT '产品编码',
  `price_plan_id` varchar(64) NOT NULL COMMENT '资费编码',
  `act_type` varchar(64) DEFAULT NULL COMMENT '业务操作（开通/退订）A增加  R删除  X不变',
  `eff_date` datetime DEFAULT NULL COMMENT '生效时间',
  `exp_date` datetime DEFAULT NULL COMMENT '失效时间',
  `institution_code` varchar(64) DEFAULT NULL COMMENT '接收机构编码，0000：外勤管家、0001：初定外平台、0002：音乐公司 、0003：产创云（COP平台）、0004：MDM平台',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
