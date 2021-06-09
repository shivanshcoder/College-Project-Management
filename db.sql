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
    report_pdf varchar(20),
    foreign key(student_id) references student(id),
    foreign key(project_id) references project(id)
);
 
create table customer(id varchar(30), number varchar(30), name varchar(30), gender varchar(30), country varchar(30), room_number varchar(30), status varchar(30), deposit varchar(30));


create table room(room_number varchar(20), availability varchar(20), clean_status varchar(20), price varchar(20), bed_type varchar(30));


create table employee(name varchar(30), age varchar(10), gender varchar(30), job varchar(30), salary varchar(30), phone varchar(30), aadhar varchar(30), email varchar(40));


create table driver(name varchar(30), age varchar(10), gender varchar(20), company varchar(30), brand varchar(30), available varchar(10), location varchar(50));


create table department(department varchar(30), budget varchar(30));