# 2021.6.14 余春生

# 执行前请确保class_t表和classroom_t表与插入数据没有冲突
insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 5, 45);

insert into classroom_t(site, capacity, type)
values ('H2101', 60, 2);
insert into classroom_t(site, capacity, type)
values ('H2102', 60, 2);
insert into classroom_t(site, capacity, type)
values ('H2103', 60, 2);
insert into classroom_t(site, capacity, type)
values ('H2104', 60, 2);

insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 1, 45);
insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 2, 40);
insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 3, 45);
insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 4, 40);
insert into class_t(mno, semester, cnt, num)
values ('0702', 19, 5, 45);

insert into course_t(cno, cname, type) VALUES ('CS005A', '操作系统原理', 2);

alter table exam_t drop foreign key fk_exam_class_cid;
alter table exam_t drop column cid;

alter table examinee_t drop foreign key fk_examinee_exam_eno;
alter table exam_t modify eno int ;
alter table examinee_t add constraint fk_examinee_exam_eno foreign key (eno) references exam_t(eno);

# 2021-6-16

drop table patrol_t;

create table inspector_t(
    teacher1 varchar(15) not null ,
    teacher2 varchar(15) not null ,
    eno int ,
    site varchar(5),
    constraint fk_inspector_teacher_tno1 foreign key (teacher1) references teacher_t(tno),
    constraint fk_inspector_teacher_tno2 foreign key (teacher2) references teacher_t(tno),
    constraint fk_inspector_exam_eno foreign key (eno) references exam_t(eno),
    constraint fk_inspector_classroom_site foreign key (site) references classroom_t(site)
);

alter table exam_t drop foreign key fk_exam_classroom_site;
alter table exam_t drop column site;
alter table exam_t drop foreign key fk_exam_teacher_tno1;
alter table exam_t drop foreign key fk_exam_teacher_tno2;
alter table exam_t drop column teacher1;
alter table exam_t drop column teacher2;

select * from inspector_t;
select * from teacher_t;
select * from user_t;
select * from classroom_t;
select * from student_t;
delete from student_t where cid=2;

select * from exam_t;
delete from exam_t where cno='CS005A';

alter table exam_t add column type int;




