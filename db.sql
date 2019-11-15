create database user_info;


create table user(
id bigint unsigned not null,
username varchar(32) not null default '',
birthday date not null default '1980-01-01',
address varchar(64) not null default '',
primary key(id)
)engine=innodb;

alter table user add column gender char(12) not null default '';

insert into user(id, username, birthday, gender, address) values('1', 'John', '1990-01-01', '男', '北京');
insert into user(id, username, birthday, gender, address) values('2', 'Wyett', '1986-01-01', '男', '北京');
insert into user(id, username, birthday, gender, address) values('3', 'Michille', '1990-02-01', '女', '上海');
insert into user(id, username, birthday, gender, address) values('4', 'Mike', '1993-01-01', '男', '北京');
insert into user(id, username, birthday, gender, address) values('5', 'Lily', '1982-01-01', '女', '广州');


set names utf8;

create table account(
 id bigint unsigned not null,
 uid bigint default null,
 money double default null,
primary key(id)
) engine = innodb;

insert into account(id, uid, money) values (1,2,30000),(2,3,100000),(3,2,1000000),(4,2,100000);

select * from account;
select * from user;

------------------------------

create table role(
id int not null,
role_name varchar(30) default null,
role_desc varchar(60) default null,
primary key(id)
)engine=innodb default charset=utf8;


insert into role(id,role_name,role_desc) values(1,'院长','管理整个学院'),(2,'总裁','管理整个公司'),(3,'校长','管理整个学校');

create table user_role(
uid bigint not null,
rid int not null,
primary key(uid,rid)
)engine = innodb default charset=utf8;


insert into user_role(uid, rid) values(1,1),(2,2),(3,2),(4,2);


select * from user_role;


