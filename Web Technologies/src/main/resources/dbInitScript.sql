DROP TABLE IF EXISTS tempos;
DROP TABLE IF EXISTS inscricoes;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS utilizadores;

Create table utilizadores(
  nomeUtilizador varchar(50) not null, 
  password varchar (255) not null, 
  email varchar (50) not null primary key, 
  role varchar (12) not null,
  enabled TINYINT NOT NULL DEFAULT 1,
);

insert into utilizadores values ('admin', '$2a$10$dl8TemMlPH7Z/mpBurCX8O4lu0FoWbXnhsHTYXVsmgXyzagn..8rK', 'admin@email.com', 'ROLE_STAFF', 1);
insert into utilizadores values ('jose', '$2a$10$bKWhb9hIUD3xxxtzfhvodugWIK3Gbw4vRySYOnBqy2O4gtqZ78jUK', 'jose@email.com', 'ROLE_ATLETA', 1);
insert into utilizadores values ('isa', '$2a$10$bKWhb9hIxD3xxxtzfhvodugWIK3Gbw4vRySYOnBqy2O4gtqZ78jUK', 'isa@email.com', 'ROLE_ATLETA', 1);

Create table evento(
  id int not null primary key,
  nomeEvento varchar(50) not null, 
  descricao varchar(500) not null,
  preco int,
  data date not null,
);

insert into evento values (0,'Corrida por Lisboa', 'Corrida anual pela baixa da cidade pombalina', 24, '2021-11-05');
insert into evento values (1,'Corrida Mata do Buçaco', 'Famosa corrida pela Mata do Buçaco', 10, '2022-02-09');
insert into evento values (2,'Color Run', 'Saudades da sua juventude? Pode reviver a magia da infância na nova corrida das cores.', 17, '2022-04-02');
insert into evento values (3,'RunInRio', 'Corrida sobre a Ponte 25 de Abril com o patrocínio RockInRio', 25, '2022-06-22');
insert into evento values (4,'Corrida de Évora', '25ª corrida anual à volta das muralhas de Évora', 10, '2022-08-17');





Create table inscricoes (
  email varchar(50) not null,
  genero char(1) not null,
  escalao varchar(6) not null,
  id int not null, 
  pago smallint not null default 0, 
  dorsal smallint not null default 0,
  primary key (email, id), 
  foreign key (email) references utilizadores, 
  foreign key (id) references evento
);

insert into inscricoes values ('jose@email.com', 'm', 'jun' ,0, 1, 1);
insert into inscricoes values ('isa@email.com', 'f', 'jun' ,0, 1, 2);

Create table tempos (
  dorsal smallint not null, 
  id int not null, 
  ponto varchar (7) not null, 
  tempo time not null, 
  primary key (dorsal, id, ponto), 
  foreign key (id) references evento
);

insert into tempos values(1,0,'start','00:12:31');
insert into tempos values(1,0,'P1','00:22:11');
insert into tempos values(1,0,'P2','00:37:15');
insert into tempos values(1,0,'P3','00:57:23');
insert into tempos values(1,0,'finish','01:15:31');

insert into tempos values(2,0,'start','00:00:31');
insert into tempos values(2,0,'P1','00:15:31');
insert into tempos values(2,0,'P2','00:28:40');
insert into tempos values(2,0,'P3','00:44:53');
insert into tempos values(2,0,'finish','00:59:41');

-- mb_amount: '10.00', status: 'ok', mb_entity: '21067', mb_reference: '900001364'
Create table pagamento(
    email varchar(50) not null, 
    id int not null, 
    mb_amount float not null,
    mb_entity int not null,
    mb_reference int not null,
    primary key (email, id),
    foreign key (email) references utilizadores, 
    foreign key (id) references evento
)


