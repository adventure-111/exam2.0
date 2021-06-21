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

select * from course_t;


