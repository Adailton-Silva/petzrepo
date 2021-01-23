create table cliente (
 id bigint not null auto_increment,
 nome varchar(100) null,
 endereco varchar(80) null,
 bairro varchar(50) null,
 cidade varchar(50) null,
 cpf varchar(11) null,
 telefone varchar(14) null,
 primary key (id));
 
 create table pet (
 id bigint not null,
 nome_pet varchar(50) null,
 idade int null,
 id_cliente bigint not null,
 primary key (id));

alter table pet add constraint fk_pet_cliente
foreign key (id_cliente) references cliente (id);
 
 
