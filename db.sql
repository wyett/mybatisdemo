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