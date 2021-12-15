
create table motorista(
	Nome varchar(20),
	NCartaCond char(5),
	DataNasc text,
	Nbi char(4) primary key
);


create table telefone(
	Nbi char(4),
	Telefone integer,
	foreign key (Nbi) references motorista
);


create table modelo(
	marca text,
	modelo text,
	Nlugares integer,
	Consumo decimal
);

create table taxi(
	modelo text,
	Ano integer,
	Kms integer,
	Matricula text
);

create table servico(
	DataInicio text,
	DataFim text,
	Kms integer,
	Valor decimal,
	Matricula text,
	CoordGPSInic decimal,
	CoordGPDfin decimal
);

create table turno(
	DataInicio text,
	DataFim text,
	KmInicio integer,
	KmFim integer,
	Matricula text,
	Nbi char(4)
);


create table cliente(
	Nome text,
	Morada text,
	CodigoPostal text,
	Nif text
);


create table pedido(
	Nif text,
	MoradaInicio text,
	CodigoPostalInicio text,
	DataPedido text,
	Matricula text,
	DataInicio text
);

insert into modelo values ('Renault', 'Renault Espace', '7', '7');
insert into taxi values ('Renault Espace', '2015', '123098', '22-AA-22');

insert into modelo values ('Mercedes', 'Mercedes CLK', '7', '9');
insert into taxi values ('Mercedes CLK', '2014', '234554', '21-AA-22');

insert into modelo values ('Honda', 'Honda Civic', '5', '5');
insert into taxi values ('Honda Civic', '2012', '89764', '20-AA-22');

insert into modelo values ('Mercedes', 'Mercedes-benz classe S', '5', '6.5');
insert into taxi values ('Mercedes-benz classe S', '2015', '79744', '19-AA-22');

insert into motorista values ('Manuel Duarte', 'L-123', '14/1/76', '1234');
insert into telefone values('1234', '266262626');
insert into telefone values ('1234', '939393939');

insert into motorista values ('Fernando Nobre', 'L-124', '14/1/77', '1235');
insert into telefone values ('1235', '266262627');
insert into telefone values ('1235', '939393940');

insert into motorista values ('Anibal Silva', 'L-125', '14/1/78', '1236');
insert into telefone values ('1236', '266262628');
insert into telefone values ('1236', '939393941');

insert into motorista values ('Francisco Lopes', 'L-126', '14/1/79', '1237');
insert into telefone values ('1237', '266262629');

insert into cliente values ('Jose Silva', 'Rua Antonio Silva 23', '7100-434 Evora', '600700800900');
insert into cliente values ('Francisco Passos', 'Rua Manuel Passos 12', '7000-131 Evora', '600700800901');
insert into cliente values ('Pedro Sousa', 'Rua Joaquim Sousa 21', '7500-313 Evora', '600700800902');

insert into pedido values ('600700800900', 'Rua Silva Pais 33', '7120-212 Evora', '2/1/2016 9:00', '19-AA-22', '2/1/2016 8:43');

insert into turno values ('2/1/2016 8:00', '2/1/2016 9:00', '79744', '79944', '19-AA-22', '1234');
insert into turno values ('2/1/2016 8:00', '2/1/2016 17:00', '89764', '89964', '20-AA-22', '1235');
insert into turno values ('3/1/2016 8:00', '3/1/2016 17:00', '234554', '234954', '21-AA-22', '1236');
insert into turno values ('3/1/2016', '3/1/2016', '123098', '123498', '22-AA-22', '1237');

insert into servico values ('2/1/2016 8:12', '2/1/2016 8:32', '12', '5.25', '19-AA-22', '0.75', '0.76');
insert into servico values ('2/1/2016 8:43', '2/1/2016 8:52', '7', '3.25', '19-AA-22', '0.78', '0.79');
insert into servico values ('2/1/2016 8:53', '2/1/2016 9:59', '98', '53.25', '19-AA-22', '0.79', '0.83');
insert into servico values ('2/1/2016 10:13', '2/1/2016 10:29', '18', '19.25', '19-AA-22', '0.85', '0.87');
insert into servico values ('2/1/2016 11:10', '2/1/2016 11:39', '23', '22.25', '19-AA-22', '0.89', '0.92');
insert into servico values ('2/1/2016 12:00', '2/1/2016 13:39', '21', '42.25', '19-AA-22', '0.93', '0.96');
insert into servico values ('2/1/2016 15:20', '2/1/2016 15:39', '9', '12.25', '19-AA-22', '0.97', '0.99');
