CREATE TABLE `task_datasource` (
  `task_id` varchar(20) NOT NULL,
  `task_name` varchar(20) NOT NULL,
  `isruning` varchar(1) NOT NULL,
  `UUID` varchar(64) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `cron` varchar(20) NOT NULL,
  `classpath` varchar(100) NOT NULL,
  `ex_method` varchar(20) NOT NULL,
  `current_thead` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `mysql`.`task_datasource`(`task_id`, `task_name`, `isruning`, `UUID`, `status`, `cron`, `classpath`, `ex_method`, `current_thead`) VALUES ('mytaskone', 'mytaskOne', 'N', '111111', '0', '0/6 * * * * ?', 'com.sunpf.taskdatesourcetest.task.MytaskOne', 'dotask', 'N');
INSERT INTO `mysql`.`task_datasource`(`task_id`, `task_name`, `isruning`, `UUID`, `status`, `cron`, `classpath`, `ex_method`, `current_thead`) VALUES ('mytasktwo', 'mytaskTwo', 'N', '111111', '0', '0/6 * * * * ?', 'com.sunpf.taskdatesourcetest.task.MytaskTwo', 'dotask', 'N');
INSERT INTO `mysql`.`task_datasource`(`task_id`, `task_name`, `isruning`, `UUID`, `status`, `cron`, `classpath`, `ex_method`, `current_thead`) VALUES ('mytaskthree', 'mytaskThree', 'N', '111111', '0', '0/6 * * * * ?', 'com.sunpf.taskdatesourcetest.task.MytaskThree', 'dotask', 'N');