--테이블 확인
select  person_id,
        name,
        hp,
        company
from person;
----------------------------------------------------------
--# 계정 / 비번 / 권한 만들기
/*
--1)system계정 접속
--2)계정 생성
create user phonedb identified by phonedb;
--3)권한 부여
grant resource, connect to phonedb;
*/
----------------------------------------------------------
--#01. 테이블 삭제
drop table person;

--#02. 시퀀스 삭제 
drop sequence seq_person_id;

--#03. person 테이블 생성
create  table person(
        person_id number(5),
        name VARCHAR2(30) not null,
        hp VARCHAR2(20),
        company VARCHAR2(20),
        primary key (person_id)
);

--#04. 시퀀스 생성
create sequence seq_person_id
increment by 1
start with 1
nocache;

commit;
---------------------------------------------------
--#데이터 입력
--#1
insert into person
values(seq_person_id.nextval, '이효리','010-1111-1111','02-1111-1111');
--#2
insert into person
values(seq_person_id.nextval, '정우성','010-2222-2222','02-2222-2222');
--#3
insert into person
values(seq_person_id.nextval, '유재석','010-3333-3333','02-3333-3333');
--#4
insert into person
values(seq_person_id.nextval, '이정재','010-4444-4444','02-4444-4444');
--#5
insert into person
values(seq_person_id.nextval, '서장훈','010-5555-5555','02-5555-5555');

commit;
------------------------------------------------------

--#05. insert, select, update, delete
--#insert문
insert into person
values(seq_person_id.nextval, 'name','hp','company');
--#select문
select  person_id,
        name,
        hp,
        company
from person;
--#update문
update person
set name = '이정재',
    hp = '010-9999-9999',
    company = '02-9999-9999'
where person_id = 4;
--#delete문
delete from person
where person_id = 5;
--#search 검색
select person_id,
        name,
        hp,
        company
from person
where name like '%유%'
or hp like '%유%'
or company like '%유%';

