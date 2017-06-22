CREATE TABLE `t_test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usr_nm` varchar(50) DEFAULT NULL,
  `pass_wd` varchar(128) DEFAULT NULL,
  `tm_smp` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)