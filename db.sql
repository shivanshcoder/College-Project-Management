create database hms;


use hms;

drop table student;
drop table teacher;
drop table project_submission;
drop table project;

create table teacher(
    id varchar(40) primary key,
    name varchar(20),
    password varchar(64)
);
0
create table student(
    id int auto_increment primary key,
    name varchar(20)
);

create table project(
    id int auto_increment primary key,
    name varchar(40),
    teacher_id varchar(40),
    subject varchar(20),
    foreign key(teacher_id) references teacher(id)
);

create table subject(
    id int auto_increment primary key,
    name varchar(20)
);

create table project_submission(
    project_id int,
    student_id int,
    report_pdf varchar(80),
    foreign key(student_id) references student(id),
    foreign key(project_id) references project(id)
);
 