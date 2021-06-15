create database db_cpm;


use hms;
drop table project_submission;
drop table project;
drop table classroom_students;
drop table teacher;
drop table student;
drop table classroom;
drop table user;


create table user(
    username varchar(10) primary key,
    password varchar(64)
);

create table teacher(
    id int auto_increment ,
    username varchar(10) ,
    name varchar(40),
    foreign key(username) references user(username),
    primary key (id, username)
);

create table student(
    id int auto_increment,
    username varchar(10) ,
    name varchar(40),
    foreign key(username) references user(username), 
    primary key (id, username)
);

create table classroom(
    id int auto_increment primary key,
    name varchar(40),
    teacher_id int
);

create table classroom_students(
    class_ref int,
    student_ref int,
    primary key(class_ref, student_ref),
    foreign key(class_ref) references classroom(id),
    foreign key(student_ref) references student(id)
);

create table project(
    id int auto_increment primary key,
    name varchar(40),
    teacher_ref int,
    classroom_ref int,
    foreign key(classroom_ref) references classroom(id),
    foreign key(teacher_ref) references teacher(id)
);

create table project_submission(
    id int auto_increment ,
    student_ref int,
    project_ref int,
    report_pdf varchar(80),
    approved boolean,
    foreign key(student_ref) references student(id),
    foreign key(project_ref) references project(id),
    primary key(id, student_ref, project_ref)
);


-- create table teacher(
--     id varchar(40) primary key,
--     name varchar(20),
--     password varchar(64)    
-- );

-- create table student(
--     id int auto_increment primary key,
--     name varchar(20)
-- );

-- create table project(
--     id int auto_increment primary key,
--     name varchar(40),
--     teacher_id varchar(40),
--     subject varchar(20),
--     foreign key(teacher_id) references teacher(id)
-- );

-- create table subject(
--     id int auto_increment primary key,
--     name varchar(20)
-- );

-- create table project_submission(
--     project_id int,
--     student_id int,
--     report_pdf varchar(80),
--     approved boolean,
--     foreign key(student_id) references student(id),
--     foreign key(project_id) references project(id)
-- );


select user.username, (select count(*) from teacher where username='abcd') as teacher, (select count(*) from student where username='abcd') as student from user
where;
-- Getting Project details

select project.name, subject.name as subject, teacher.name as teacher from project inner join teacher on project.teacher_id=teacher.id inner join subject on subject.id=project.subject;

select project.name as project_name, student.name as student_name, report_pdf from project_submission inner join student on student.id=student_id inner join project on project.id=project_id;