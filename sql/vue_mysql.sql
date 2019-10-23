/*基本的rbac表，及示例表*/

/*创建用户及授权*/
/*CREATE USER  IF NOT EXISTS 'vue'@localhost IDENTIFIED BY '123456';

GRANT ALL  ON *.*  TO 'vue'@localhost;

FLUSH PRIVILEGES;*/


/*创建数据库*/
DROP DATABASE IF EXISTS vue;

CREATE DATABASE IF NOT EXISTS vue character SET 'UTF8';


USE vue;

#禁用约束
SET FOREIGN_KEY_CHECKS=0;

create table sample(
  sample_id int auto_increment primary key,
  name varchar(200),
  create_time datetime,
  create_user varchar(200),
  modify_user varchar(200),
  modify_time datetime
);



insert into sample(sample_id,name,create_time,create_user)  values(1,'sample1',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(2,'sample2',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(3,'sample3',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(4,'sample4',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(5,'sample5',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(6,'sample6',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(7,'sample7',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(8,'sample8',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(9,'sample9',now(),'admin');
insert into sample(sample_id,name,create_time,create_user)  values(10,'sample10',now(),'admin');
commit;


SET FOREIGN_KEY_CHECKS=1;
commit;

