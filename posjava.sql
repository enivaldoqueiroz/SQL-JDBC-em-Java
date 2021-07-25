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