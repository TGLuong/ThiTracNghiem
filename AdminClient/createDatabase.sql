create database thitracnghiem
go
use thitracnghiem
go


create table users(
	id_user varchar(8) constraint pk_users primary key,
	name_user nvarchar(30) not null,
	birthday date,
	id_class varchar(5) not null,
	password_user nvarchar(20) not null,
	id_department varchar(10)
)
select * from users



go
create table subjects(
	id_subject varchar(8) constraint pk_subjects primary key,
	name_subject nvarchar(30) not null
)


select * from admin_account
go
create table scores
(
	id_user varchar(8),
	id_subject varchar(8),
	socre float,
	constraint pk_scores primary key (id_user,id_subject)
)

go
create table questions
(
	id_subject varchar(8),
	id_question varchar(5),
	question ntext not null,
	answer_a ntext not null,
	answer_b ntext not null,
	answer_c ntext,
	answer_d ntext,
	answer char(1) not null,
	level_question tinyint
	constraint pk_questions primary key (id_subject,id_question)
)



go
create table admin_account
(
	admin_user_name varchar(20) constraint pk_admin primary key,
	admin_password varchar(20) not null,
	admin_name nvarchar(30),
	cookie nvarchar(40) default null
)

go
create table class
(
	id_class varchar(5) ,
	id_department varchar(10),
	classname nvarchar(30),
	constraint PK_classname primary key (id_class,id_department)
)


go
create table department
(
	id_department varchar(10) constraint PL_department primary key,
	department_name nvarchar(30)
)


go


create table test
(
	id_subject varchar(8),
	id_department varchar(10),
	number_question tinyint,
	easy_question tinyint,
	normal_question tinyint,
	hard_question tinyint,
	time_test int,
	do bit default 0,
	constraint PK_test primary key (id_subject,id_department)
)
select * from test
delete from test where id_subject='CSDL'
insert into test(id_subject,id_department,number_question,easy_question,normal_question,hard_question,time_test,do) values('CSDL','CNTT',40,15,15,10,60,0)
alter table scores add constraint fk_scores_users
foreign key (id_user) references users(id_user)

alter table scores add constraint fk_scores_subject
foreign key (id_subject) references subjects(id_subject)

alter table questions add constraint fk_questions_subject
foreign key (id_subject) references subjects(id_subject)

alter table users add constraint FK_user_class foreign key (id_class,id_department) references class (id_class,id_department)
alter table class add constraint FK_class_department foreign key (id_department) references department(id_department)
alter table test add constraint FK_test_subject foreign key (id_subject) references subjects(id_subject)
alter table test add constraint FK_test_department foreign key (id_department) references department(id_department)



create procedure importuser @id_user varchar(8),@name_user nvarchar(30),@birthday date,@id_class varchar(5),@password_user nvarchar(20),@id_department varchar(10)
as
begin
	insert into users(id_user,name_user,birthday,id_class,password_user,id_department) values
	(@id_user,@name_user,@birthday,@id_class,@password_user,@id_department)
end

create procedure importsubject @id_subject varchar(8),@name_subject nvarchar(30)
as
begin
	insert into subjects(id_subject,name_subject) values
	(@id_subject,@name_subject)
end

create procedure importdepartment @id_department varchar(10),@department_name nvarchar(30)
as
begin
	insert into department(id_department,department_name) values
	(@id_department,@department_name)
end

create procedure importquestion @id_subject varchar(8),@id_question varchar(5),@question ntext,@answer_a ntext,@answer_b ntext,@answer_c ntext,@answer_d ntext,@answer ntext,@level_question tinyint
as
begin
	insert into questions(id_subject,id_question,question,answer_a,answer_b,answer_c,answer_d,answer,level_question) values
	(@id_subject,@id_question,@question,@answer_a,@answer_b,@answer_c,@answer_d,@answer,@level_question)
end

create procedure importclass @id_subject varchar(8),@id_department varchar(10),@number_question tinyint,@easy_question tinyint,@normal_question tinyint,@hard_question tinyint
as
begin
	insert into test(id_subject,id_department,number_question,easy_question,normal_question,hard_question) values
	(@id_subject,@id_department,@number_question,@easy_question,@normal_question,@hard_question)
end
go


create view test_full_info
as
select ts.id_subject,ts.id_department,sb.name_subject,dp.department_name,ts.number_question,ts.easy_question,ts.normal_question,ts.hard_question,ts.time_test,ts.do from test ts,subjects sb,department dp
where ts.id_subject=sb.id_subject and ts.id_department=dp.id_department
go




