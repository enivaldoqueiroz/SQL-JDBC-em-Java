--Deletar dados

delete from userposjava where id = 3;
delete from telefoneuser tt where tt.usuariopessoa = 3;

select * from telefoneuser tt where tt.usuariopessoa = 3;
select * from userposjava;
--Inner JOIN

select nome, numero, email from telefoneuser as fone
inner join userposjava as userp
on fone.usuariopessoa = userp.id
where userp.id = 8;

select * from telefoneuser as fone
inner join userposjava as userp
on fone.usuariopessoa = userp.id;

select * from userposjava uu, telefoneuser tt
where uu.id = tt.usuariopessoa
and uu.id in (1,8);

--Realizando insert na tabela telefones
insert into userposjava (nome, email) 
values ('alex Fernando', 'alex@gmail.com');

insert into telefoneuser (numero, tipo, usuariopessoa)
values ('(85) 9999-8888', 'celular', 8);

insert into telefoneuser (numero, tipo, usuariopessoa)
values ('(85) 7777-7777', 'celular', 1);

select * from userposjava;
select * from telefoneuser;

select * from userposjava uu
where uu.id = 8;

alter table telefoneuser alter column id set default nextval('userposjava'::regclass);

create sequence user_telefone_seq
increment 1
minvalue 1
maxvalue 9999999999999999
start 1;
alter table user_telefone_seq
owner to postgres;

alter table telefoneuser add foreign key (usuariopessoa) references userposjava (id);

--Criando a tabela telefoneuser
create table telefoneuser
(
	id bigint not null,
	numero character varying (255) not null,
	tipo character varying (255) not null,
	usuariopessoa bigint not null,
	constraint telefone_id primary key (id)
);

alter table userposjava add unique (id);

create sequence usersequece
increment 1
minvalue 1
maxvalue 9999999999999999
start 6;

alter table userposjava alter column id set default nextval('usersequece'::regclass);

select * from userposjava;

create table userposjava (
	id bigint NOT NULL DEFAULT nextval('userposjava_seq'::regclass),
	nome character varying,
	email character varying,
	CONSTRAINT userposjava_id_key unique (id)
)