# 2021.6.14 余春生

alter table examinee_t drop foreign key fk_examinee_exam_eno;
alter table exam_t modify eno int ;
alter table examinee_t add constraint fk_examinee_exam_eno foreign key (eno) references exam_t(eno);

# 2021-6-16

alter table exam_t drop foreign key fk_exam_class_cid;
alter table exam_t drop column cid;
alter table exam_t drop foreign key fk_exam_classroom_site;
alter table exam_t drop column site;
alter table exam_t drop foreign key fk_exam_teacher_tno1;
alter table exam_t drop foreign key fk_exam_teacher_tno2;
alter table exam_t drop column teacher1;
alter table exam_t drop column teacher2;

drop table patrol_t;

create table inspector_t(
    teacher1 varchar(15) not null ,
    teacher2 varchar(15) not null ,
    eno int ,
    site varchar(5) not null ,
    constraint fk_inspector_teacher_tno1 foreign key (teacher1) references teacher_t(tno),
    constraint fk_inspector_teacher_tno2 foreign key (teacher2) references teacher_t(tno),
    constraint fk_inspector_exam_eno foreign key (eno) references exam_t(eno),
    constraint fk_inspector_classroom_site foreign key (site) references classroom_t(site)
);

drop table absence;

create table absence_t (
    tno varchar(15) not null ,
    eno int not null ,
    reason varchar(200) ,
    state int,
    comment varchar(200) ,
    constraint fk_absence_teacher_tno foreign key (tno) references teacher_t(tno) ,
    constraint fk_absence_exam_eno foreign key (eno) references exam_t(eno)
);

alter table exam_t add patrol1 varchar(15);
alter table exam_t add patrol2 varchar(15);

alter table examinee_t modify column seat int;

select * from examinee_t;
