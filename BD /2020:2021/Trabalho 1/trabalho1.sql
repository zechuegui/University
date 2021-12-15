drop table if exists membro cascade;
create table membro(
Nome varchar(25),
IdMemb varchar(7) primary key,
Pais varchar(2),
Cidade varchar(20),
DataNasc char(8) --AAAAMMDD
);

insert into membro values('Pedro','m01','PT','Porto','19580418');
insert into membro values('Mike','m02','US','Albuquerque','19421009');
insert into membro values('Sebastiao','m03','PT','Viseu','20001010');
insert into membro values('Alfa','m04','PT','Evora','19930517');
insert into membro values('Isa','m05','PT','Viseu','19990217');
insert into membro values('Leonor','m06','BR','Rio de Janeiro','19870423');
insert into membro values('Annabelle','m07','EG','Edfu','19810824');
insert into membro values('Jonas','m08','DE','Winden','20030608');
insert into membro values('Goreti','m09','IT','Milan','19391224');
insert into membro values('Abdul','m10','TR','Diyarbakir','19870114');
insert into membro values('Chandra','m11','IN','Calcutá','19891217');
insert into membro values('Dimitri','m12','RU','Moscow','19680420');
insert into membro values('Anaximandro','m13','TR','Miletu','19880930');
insert into membro values('Democrito','m14','GR','Abdera','19700716');
insert into membro values('Claude','m15','FR','Paris','19691114');
insert into membro values('Vincent','m16','NL','Amsterdam','19530330');
insert into membro values('Ustad','m17','IR','Xiraz','19620522');
insert into membro values('Bob','m18','JM','Nine Mile','19810511');
insert into membro values('Brites','m19','PT','Faro','19500814');
insert into membro values('Rui','m20','KP','Pyongyang','20200123');
insert into membro values('Flopes','oleitor','PT','Evora','20200122');

drop table if exists amigo cascade;
create table amigo(
IdMemb1 varchar(7),
IdMemb2 varchar(7),
primary key (IdMemb1, IdMemb2),
foreign key (IdMemb1) references membro on delete restrict,
foreign key (IdMemb2) references membro on delete restrict
);

Insert into amigo values('m01','m02');
Insert into amigo values('m01','m03');
Insert into amigo values('m01','m04');
Insert into amigo values('m01','m05');
Insert into amigo values('m01','m06');
Insert into amigo values('m01','m07');
Insert into amigo values('m01','m08');
Insert into amigo values('m01','m09');
Insert into amigo values('m01','m10');
Insert into amigo values('m01','m11');
Insert into amigo values('m01','m12');
Insert into amigo values('m01','m13');
Insert into amigo values('m01','m14');
Insert into amigo values('m01','m15');
Insert into amigo values('m01','m16');
Insert into amigo values('m01','m17');
Insert into amigo values('m01','m18');
Insert into amigo values('m01','m19');
Insert into amigo values('m01','m20');
insert into amigo values('m01','oleitor');

Insert into amigo values('m07','oleitor'); --20
Insert into amigo values('m07','m02');
Insert into amigo values('m07','m03');
Insert into amigo values('m07','m04');
Insert into amigo values('m07','m06');
Insert into amigo values('m07','m08');
Insert into amigo values('m07','m09');
Insert into amigo values('m07','m10');
Insert into amigo values('m07','m11');
Insert into amigo values('m07','m12');
Insert into amigo values('m07','m13');
Insert into amigo values('m07','m14');
Insert into amigo values('m07','m15');
Insert into amigo values('m07','m16');
Insert into amigo values('m07','m17');
Insert into amigo values('m07','m18');
Insert into amigo values('m07','m19');
Insert into amigo values('m07','m20');




insert into amigo values('m02','m03');
insert into amigo values('m02','m04');

insert into amigo values('m03','m04');

insert into amigo values('m05','m06');
insert into amigo values('m05','m07');

insert into amigo values('oleitor','m20');



drop table if exists gosta cascade;
create table gosta(
IdMemb varchar(7),
ISBN char(3),
primary key(IdMemb, ISBN),
foreign key (IdMemb) references membro on delete restrict
);

insert into gosta values('m01','l06');
insert into gosta values('m02','l06');
insert into gosta values('m03','l06');
insert into gosta values('m04','l06');
insert into gosta values('m05','l06');
insert into gosta values('m06','l06');
insert into gosta values('m07','l06');
insert into gosta values('m08','l06');
insert into gosta values('m09','l06');
insert into gosta values('m10','l06');
insert into gosta values('m11','l06');
insert into gosta values('m12','l06');
insert into gosta values('m13','l06');
insert into gosta values('m14','l06');
insert into gosta values('m15','l06');
insert into gosta values('m16','l06');
insert into gosta values('m17','l06');
insert into gosta values('m18','l06');
insert into gosta values('m19','l06');
insert into gosta values('m20','l06');
insert into gosta values('oleitor','l06');

insert into gosta values('m01','l03');
insert into gosta values('m01','l09');
insert into gosta values('m02','l01');
insert into gosta values('m03','l04');
insert into gosta values('m04','l03');
insert into gosta values('m04','l10');
insert into gosta values('m04','l05');
insert into gosta values('m05','l08');
insert into gosta values('m13','l02');
insert into gosta values('m18','l07');
insert into gosta values('m10','l05');





drop table if exists livro cascade;
create table livro(
ISBN char(3) primary key,
Titulo varchar(100)
);

insert into livro values('l01','Base de Dados para idiotas'); --1G
insert into livro values('l02','Historia do nada - um livro sobre natacao');--1G
insert into livro values('l03','Dicionário por imagens do Fanstástico'); --2G
insert into livro values('l04','Diário de um banana'); --1G
insert into livro values('l05','Gerónimo Stilton'); --1G
insert into livro values('l06','Eu, Rui'); --3 autores --20G
insert into livro values('l07','Sistemas Digitais'); --2 autores --1G
insert into livro values('l08','O livro do Zen'); --1G
insert into livro values('l09','Anybody can be cool... but awesome takes practice'); --1G
insert into livro values('l10','How to rob banks without violence');--1G

drop table if exists genero cascade;
create table genero( 
ISBN char(3),
Genero varchar(20), 
primary key(ISBN,Genero),
foreign key(ISBN) references livro on delete restrict
);

insert into genero values('l01','Policial');
insert into genero values('l01','Educacional');

insert into genero values('l02','Policial');
insert into genero values('l02','Desporto');

insert into genero values('l03','Policial');
insert into genero values('l03','Fantasia');

insert into genero values('l04','Policial');
insert into genero values('l04','Animacao');

insert into genero values('l05','Policial');
insert into genero values('l05','Aventura');

insert into genero values('l06','Policial');
insert into genero values('l06','Drama');
insert into genero values('l06','Romance');
insert into genero values('l06','Biografia');
insert into genero values('l06','Suspanse');
insert into genero values('l06','Desporto');
insert into genero values('l06','Educacional');

insert into genero values('l07','Policial');
insert into genero values('l07','Educacional');

insert into genero values('l08','Policial');
insert into genero values('l08','Autoajuda');
insert into genero values('l08','Educacional');

insert into genero values('l09','Policial');
insert into genero values('l09','Comedia');
insert into genero values('l09','Educacional');

insert into genero values('l10','Policial');
insert into genero values('l10','Suspanse');
insert into genero values('l10','Drama');
insert into genero values('l10','Educacional');

drop table if exists autor cascade;
create table autor(
CodA char(3) primary key,
Nome varchar(30),
Pais varchar(20)
);

insert into autor values('a01','Rui','KP'); --2
insert into autor values('a02','Isa','PT'); --2
insert into autor values('a03','Robin','CA'); --2
insert into autor values('a04','Gbemisola','NG'); --2
insert into autor values('a05','Pablo','CO'); --Ja tem 2
insert into autor values('a06','Agatha Christie','GB'); --2
insert into autor values('a07','Francisco Jose Viegas','PT');--2



drop table if exists autoria cascade;
create table autoria(
ISBN char(3),
CodA char(3),
primary key (ISBN, CodA),
foreign key (ISBN) references livro on delete restrict,
foreign key (CodA) references autor on delete restrict
);

insert into autoria values('l01','a04');

insert into autoria values('l02','a01');
insert into autoria values('l02','a07');

insert into autoria values('l03','a06');
insert into autoria values('l03','a07');

insert into autoria values('l04','a01');

insert into autoria values('l05','a05');

insert into autoria values ('l06','a02');
insert into autoria values ('l06','a05');
insert into autoria values('l06','a03');

insert into autoria values('l07','a03');
insert into autoria values('l07','a04');

insert into autoria values('l08','a02');

insert into autoria values('l09','a06');
insert into autoria values('l09','a04');

insert into autoria values('l10','a05');

--a)
select distinct Nome
from autor natural inner join autoria natural inner join genero
where Genero like 'Drama';

--b)
select distinct membro.Nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Agatha Christie';

--c)
select distinct membro.nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where membro.pais = autor.pais;

--d)
select Nome
from membro
except
select distinct membro.Nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Agatha Christie';

--e)
select Nome
from membro
except
(select Nome
from membro, amigo
where idMemb=idMemb1 and amigo.idMemb2 like 'oleitor'
UNION
select Nome
from membro, amigo
where idMemb=idMemb2 and amigo.idMemb1 like 'oleitor'
UNION
select Nome
from membro
where idmemb like 'oleitor');


--f) dar uma vista de olhos
select Nome from 
(select Nome, DataNasc
from membro, amigo
where idMemb=idMemb1 and amigo.idMemb2 like 'oleitor' 
UNION
select Nome, DataNasc
from membro, amigo
where idMemb=idMemb2 and amigo.idMemb1 like 'oleitor') as r
where(select DataNasc
from membro
where idMemb like 'oleitor')<r.Datanasc;

--g) ou gosta do 3 ou gosta do (2 e do 9)
select membro.Nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Francisco Jose Viegas'
INTERSECT
select membro.nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Agatha Christie';

--h)
select membro.Nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Francisco Jose Viegas'
UNION
select membro.nome
from (membro natural inner join gosta) inner join (autoria natural inner join autor) using (ISBN)
where autor.nome like 'Agatha Christie';

--i)
select count(*) from
(select Nome
from membro, amigo
where idMemb=idMemb1 and amigo.idMemb2 like 'oleitor'
UNION
select Nome
from membro, amigo
where idMemb=idMemb2 and amigo.idMemb1 like 'oleitor') as r;

--j)
with x as(
select Nome, count(idMemb2) as a     --Nº amigos da esq
from membro, amigo
where idMemb=idMemb1 group by Nome),

y as (
select Nome, count(idMemb1) as b	--Nº amigos da dir
from membro, amigo
where idMemb=idMemb2 group by Nome),

z as(
select x.Nome, x.a			--Nºmax amigos da esq
from x
where x.a = (select max(x.a) from x)),

w as(
select y.Nome, y.b					--Nºmax amigos da dir
from y
where y.b = (select max(y.b)from y)),

k as(select Nome ,(x.a+y.b) as soma  --Soma dos amigos esq + dir
from y inner join x using (nome))

select * from(
select z.Nome
from z,w,k
where z.a>=w.b and z.a>=k.soma
UNION
select w.nome
from z,w,k
where w.b>=z.a and w.b>=k.soma
UNION
select k.nome
from z,w,k
where k.soma>=z.a and k.soma>=w.b) as r;

--k)
with y as(
	select Nome, idMemb2
from membro, amigo
where idMemb=idMemb1),

x as (
select Nome, idMemb1
from membro, amigo
where idMemb=idMemb2),

z as (
select IdMemb, count(ISBN) as maisLivros
from gosta group by IdMemb),

aux as (select z.IdMemb
from z
where z.maislivros = (select max(z.maislivros) from z))

select distinct y.nome
from y, aux
where y.idMemb2=aux.idmemb
UNION
select distinct x.nome
from x, aux
where x.idMemb1=aux.idmemb;

--l)
select Titulo, count(genero)
from livro natural inner join genero
group by Titulo;

--m)
select * from(
(select Titulo, count(genero) as ngeneros
from livro natural inner join genero 
 group by Titulo) as a natural inner join
(select Titulo, count(IdMemb) as nmembros
from livro natural inner join gosta
group by Titulo) as b);

--n)
select * from(
(select Nome, count(ISBN) as nlivros
 from autor natural inner join autoria
 group by Nome) as a natural inner join
 (select Nome, count(genero) as ngeneros
 from autor natural inner join autoria natural inner join genero
 group by Nome) as b natural inner join
 (select Nome, count(IdMemb) as ngostos
 from autor natural inner join autoria natural inner join gosta 
 group by Nome) as c);

--o)
with tudo as (with x as(
select Nome, count(idMemb2) as a     --Nº amigos da esq
from membro, amigo
where idMemb=idMemb1 group by Nome),

y as (
select Nome, count(idMemb1) as a	--Nº amigos da dir
from membro, amigo
where idMemb=idMemb2 group by Nome),

k as(select Nome ,(x.a+y.a) as a  --Soma dos amigos esq + dir
from y join x using (nome)),

aux1 as(select Nome
from x
except
select nome
from k),

aux2 as (select Nome
from y
except
select nome
from k)
			  
select * from(
select Nome,a
from x natural inner join aux1
UNION
select Nome,a
from y natural inner join aux2
UNION select Nome,a
from k) as r

)
select Nome, tudo.a as nAmigos, count(ISBN) as nGostos
from tudo natural inner join membro natural inner join gosta group by (Nome, tudo.a);

--p)
with nMemb as(
select count(IdMemb)-1 as c --Numero Total Membros (menos ele próprio)
from membro),

tudo as (with x as(
select Nome, count(idMemb2) as a     --Nº amigos da esq
from membro, amigo
where idMemb=idMemb1 group by Nome),

y as (
select Nome, count(idMemb1) as b	--Nº amigos da dir
from membro, amigo
where idMemb=idMemb2 group by Nome),

z as(
select x.Nome, x.a			--Nºmax amigos da esq
from x
where x.a = (select max(x.a) from x)),

w as(
select y.Nome, y.b					--Nºmax amigos da dir
from y
where y.b = (select max(y.b)from y)),

k as(select Nome ,(x.a+y.b) as soma  --Soma dos amigos esq + dir
from y inner join x using (nome))

select * from(
select z.Nome, z.a as ola
from z,w,k
where z.a>=w.b and z.a>=k.soma
UNION
select w.nome, w.b as ola
from z,w,k
where w.b>=z.a and w.b>=k.soma
UNION
select k.nome,k.soma as ola
from z,w,k
where k.soma>=w.b and k.soma>=z.a) as r)

select Nome
from tudo, Nmemb
where tudo.ola=Nmemb.c;

--q)

with x as (
select idMemb
from membro, amigo
where idMemb=idMemb1 and amigo.idMemb2 like 'oleitor'
UNION
select idMemb
from membro, amigo
where idMemb=idMemb2 and amigo.idMemb1 like 'oleitor')

select distinct Titulo
from gosta natural inner join livro natural inner join x;