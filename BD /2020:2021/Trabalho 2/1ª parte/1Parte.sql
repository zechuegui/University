drop table if exists membro cascade;
create table membro(
	Idmemb varchar (20) primary key,
	Nome varchar (20),
	Pais varchar (2),
	cidade varchar (15),
	DataNasc char(8) --AAAAMMDD
);

insert into membro values ('m01','Frodo','ME','Shire','19680803');
insert into membro values ('m02','Manuel Silva','PT','Évora','19990619');
insert into membro values ('m03','Gus','CL','El transito','19580720');
insert into membro values ('m04','Javier Peña','US','Texas','19840703');
insert into membro values ('m05','Beth Harmon','US','Cincinnati','19971022');
insert into membro values ('m06','Katharina','DE','Winden','19700722');
insert into membro values ('m07','Sully','GB','London','19930427');
insert into membro values ('m08','Morty','US','Seatle','19991202');
insert into membro values ('m09','Fernando','PT','Lisboa','19630309');
insert into membro values ('m10','Abid','US','Riverside','19890310');

drop table if exists amigo cascade;
create table amigo(
	Idmemb1 varchar(20),
	Idmemb2 varchar(20),
	primary key (IdMemb1, IdMemb2),
	foreign key (IdMemb1) references membro on delete restrict,
	foreign key (IdMemb2) references membro on delete restrict
);

insert into amigo values ('m01','m02');
insert into amigo values ('m01','m03');
insert into amigo values ('m02','m03');
insert into amigo values ('m02','m04');
insert into amigo values ('m03','m04');
insert into amigo values ('m03','m05');
insert into amigo values ('m04','m05');
insert into amigo values ('m04','m06');
insert into amigo values ('m05','m06');
insert into amigo values ('m05','m07');
insert into amigo values ('m06','m07');
insert into amigo values ('m06','m08');
insert into amigo values ('m07','m08');
insert into amigo values ('m07','m09');
insert into amigo values ('m08','m09');
insert into amigo values ('m08','m10');
insert into amigo values ('m09','m10');
insert into amigo values ('m09','m01');
insert into amigo values ('m10','m01');
insert into amigo values ('m10','m02');



drop table if exists receita cascade;
create table receita(
	IdReceita varchar(3) primary key,
	Descrição varchar (100),
	NomeR varchar (50),
	Custo varchar (3),
	Tempo integer --minutos
);

insert into receita values ('r01','Receita à base de pato e batata','Pato à brás','$$','45'); --pp
insert into receita values ('r02','Receita deliciosa com vegetais frescos','Couscous com vegetais','$','20'); --pp
insert into receita values ('r03','Receita muito rápida, saborosa e fácil de fazer','Tosta Mista','$','7'); --pp
insert into receita values ('r04','Hamburguer de lentilhas, com uma base de guacamole e maionese, verduras e vegetais','Hamburguer de lentilhas','$$','15'); --pp
insert into receita values ('r05','Empadão de peru biológico, com chouriço vegano','Empadão de Peru','$$','90'); --pp
insert into receita values ('r06','Arroz cremoso com cogumelos','Risotto de cogumelos','$','15'); --pp
insert into receita values ('r07','Deliciosa sopa de tomate com um topping de manjericão','Sopa de tomate','$','60'); --entrada
insert into receita values ('r08','Pão rústico com paté de azeitona','Pão com paté de azeitona','$','10'); -- entrada
insert into receita values ('r09','Massa folhada com recheio de maçã acompanhado por gelado vegano de baunilha','Folhado de maçã com gelado','$','50'); --sobremesa
insert into receita values ('r10','Rolo de Tofu com vegetais e especiarias','Rolo de Tofu','$$$','120'); -- pp
insert into receita values ('r11','Mousse de chocolate','Mousse de chocolate','$','20'); --sobremesa
insert into receita values ('r12','Mousse de chocolate branco','Mousse de chocolate','$','25'); --sobremesa
insert into receita values ('r13','Doce regional com ovo e amendoa','Doce de amendoa','$','40'); -- sobremesa
insert into receita values ('r14','Croquete crocante com pato e mostarda','Croquete','$','35'); -- entrada
insert into receita values ('r15','Mini pizzas com pimento e azeitona','Mini pizzas','$$','60'); -- entrada
insert into receita values ('r16','Pizza gigante com toppings tropicais','Pizza','$$$','55'); --pp
insert into receita values ('r17','Cogumelos salteados com alho e azeite','Cogumelos Salteados','$','10'); -- entrada
insert into receita values ('r18','Bolo frio suave com sabores tropicais','Cheesecake de maracujá','$$','80'); --sobremesa
insert into receita values ('r19','Cogumelo recheado com queijo tomate e pimento','Cogumelo recheado','$','40'); --entrada
insert into receita values ('r20','Lasanha de espinafres com queijo gratinado','Lasanha espinafres','$','60'); --pp
insert into receita values ('r21','Nuggets crocantes de peru e maionese','Nuggets','$$','55'); --pp
insert into receita values ('r22','Bitoque com ovo estrelado','Bitoque','$$','30'); --pp

drop table if exists gosto cascade;
create table gosto(
	IdMemb varchar (20),
	IdReceita varchar (3),
	valor smallint, -- 1,2,3
	primary key (IdMemb, IdReceita),
	foreign key (IdReceita) references receita on delete restrict,
	foreign key (IdMemb) references membro on delete restrict
);

insert into gosto values('m01','r03',3);
insert into gosto values('m01','r04',1);
insert into gosto values('m02','r05',2);
insert into gosto values('m02','r06',3);
insert into gosto values('m03','r07',1);
insert into gosto values('m03','r08',1);
insert into gosto values('m04','r09',2);
insert into gosto values('m04','r10',3);
insert into gosto values('m05','r11',1);
insert into gosto values('m05','r12',3);
insert into gosto values('m06','r13',3);
insert into gosto values('m06','r14',3);
insert into gosto values('m07','r15',2);
insert into gosto values('m07','r16',2);
insert into gosto values('m08','r17',3);
insert into gosto values('m08','r18',2);
insert into gosto values('m09','r19',2);
insert into gosto values('m09','r20',1);
insert into gosto values('m10','r01',1);
insert into gosto values('m10','r02',2);
insert into gosto values('m09','r01',2);
insert into gosto values('m07','r01',3);
insert into gosto values('m01','r14',1);
insert into gosto values('m03','r22',2);
insert into gosto values('m01','r19',3);
insert into gosto values('m01','r17',3);
insert into gosto values('m10','r12',2);
insert into gosto values('m02','r12',2);
insert into gosto values('m06','r12',3);
insert into gosto values('m03','r12',3);

insert into gosto values('m01','r02',2);
insert into gosto values('m03','r02',3);
insert into gosto values('m04','r02',3);



drop table if exists recMemb cascade;
create table recMemb(
	IdReceita varchar (3) primary key,
	IdMemb varchar (20),
	foreign key (IdReceita) references receita on delete restrict,
	foreign key (IdMemb) references membro on delete restrict
);

insert into recMemb values ('r01','m01');
insert into recMemb values ('r02','m01');
insert into recMemb values ('r03','m02');
insert into recMemb values ('r04','m02');
insert into recMemb values ('r05','m03');
insert into recMemb values ('r06','m03');
insert into recMemb values ('r07','m04');
insert into recMemb values ('r08','m04');
insert into recMemb values ('r09','m05');
insert into recMemb values ('r10','m05');
insert into recMemb values ('r11','m06');
insert into recMemb values ('r12','m06');
insert into recMemb values ('r13','m07');
insert into recMemb values ('r14','m07');
insert into recMemb values ('r15','m08');
insert into recMemb values ('r16','m08');
insert into recMemb values ('r17','m09');
insert into recMemb values ('r18','m09');
insert into recMemb values ('r19','m10');
insert into recMemb values ('r20','m10');
insert into recMemb values ('r21','m07');
insert into recMemb values ('r22','m03');

drop table if exists sobremesa cascade;
create table sobremesa(
	IdReceita varchar (3) primary key,
	facilidade varchar (7), -- facil, medio, dificil
	foreign key (IdReceita) references receita on delete restrict
);

insert into sobremesa values('r17','medio');
insert into sobremesa values('r13','facil');
insert into sobremesa values('r12','facil');
insert into sobremesa values('r11','facil');
insert into sobremesa values('r09','dificil');

drop table if exists prato_principal cascade;
create table prato_principal(
	IdReceita varchar (3) primary key,
	Porcoes smallint, -- 2, 4 ou 6
	foreign key (IdReceita) references receita on delete restrict
);

insert into prato_principal values('r01','2');
insert into prato_principal values('r02','2');
insert into prato_principal values('r03','1');
insert into prato_principal values('r04','4');
insert into prato_principal values('r05','6');
insert into prato_principal values('r06','2');
insert into prato_principal values('r10','4');
insert into prato_principal values('r16','10');
insert into prato_principal values('r20','2');
insert into prato_principal values('r21','2');
insert into prato_principal values('r22','1');

drop table if exists entrada cascade;
create table entrada(
	IdReceita varchar (3) primary key,
	foreign key (IdReceita) references receita on delete restrict
);

insert into entrada values ('r07');
insert into entrada values ('r08');
insert into entrada values ('r14');
insert into entrada values ('r15');
insert into entrada values ('r17');
insert into entrada values ('r19');

-- Será que preciso de fotografia, porque não apenas fotRec
drop table if exists fotografia cascade;
create table fotografia(
	Url varchar (200) primary key
);

insert into fotografia values ('www.angulo1Musse.pt');
insert into fotografia values ('www.angulo2Musse.pt');
insert into fotografia values ('www.angulo3Musse.pt');
insert into fotografia values ('www.angulo4Musse.pt');
insert into fotografia values ('www.fotoRolo.pt');
insert into fotografia values ('www.cheesecake1.pt');
insert into fotografia values ('www.cheesecake2.pt');
insert into fotografia values ('www.cheesecake3.pt');
insert into fotografia values ('www.cheesecake4.pt');
insert into fotografia values ('www.folhadocgelado.pt');

drop table if exists fotRec cascade;
create table fotRec(
	Url varchar (200) primary key,
	IdReceita varchar (3),
	foreign key (Url) references fotografia on delete restrict
);

insert into fotRec values ('www.angulo1Musse.pt','r11');
insert into fotRec values ('www.angulo2Musse.pt','r11');
insert into fotRec values ('www.angulo3Musse.pt','r11');
insert into fotRec values ('www.angulo4Musse.pt','r11');
insert into fotRec values ('www.fotoRolo.pt','r10');
insert into fotRec values ('www.cheesecake1.pt','r18');
insert into fotRec values ('www.cheesecake2.pt','r18');
insert into fotRec values ('www.cheesecake3.pt','r18');
insert into fotRec values ('www.cheesecake4.pt','r18');
insert into fotRec values ('www.folhadocgelado.pt','r09');

drop table if exists ingrediente cascade;
create table ingrediente(
	NomeI varchar (20) primary key
);

insert into ingrediente values ('pato');
insert into ingrediente values ('peru');
insert into ingrediente values ('ovo');
insert into ingrediente values ('amendoa');
insert into ingrediente values ('brocolo');
insert into ingrediente values ('pimento');
insert into ingrediente values ('batata');
insert into ingrediente values ('tomate');
insert into ingrediente values ('agua');
insert into ingrediente values ('tofu');
insert into ingrediente values ('queijo');
insert into ingrediente values ('espinafre');
insert into ingrediente values ('cogumelos');
insert into ingrediente values ('alho');
insert into ingrediente values ('azeite');
insert into ingrediente values ('cebola');
insert into ingrediente values ('maracuja');
insert into ingrediente values ('ananas');
insert into ingrediente values ('arroz');
insert into ingrediente values ('azeitona');
insert into ingrediente values ('chouriço vegano');
insert into ingrediente values ('chocolate');
insert into ingrediente values ('maça');
insert into ingrediente values ('baunilha');
insert into ingrediente values ('leite');
insert into ingrediente values ('couscous');
insert into ingrediente values ('manjericão');
insert into ingrediente values ('pao');
insert into ingrediente values ('maionese');
insert into ingrediente values ('abacate');
insert into ingrediente values ('alface');
insert into ingrediente values ('lentilha');
insert into ingrediente values ('massa');
insert into ingrediente values ('mostarda');

drop table if exists ingRec cascade;
create table ingRec(
	IdReceita varchar (3),
	NomeI varchar (20),
	Quantidade smallint,
	Unidade varchar (20), -- grama, chavena, colher de soupa, unidade
	primary key (IdReceita, NomeI),
	foreign key (IdReceita) references receita on delete restrict,
	foreign key (NomeI) references ingrediente on delete restrict
);

insert into ingRec values ('r01','pato', '600','grama');
insert into ingRec values ('r01','batata','200','grama');
insert into ingRec values ('r01','ovo','6','unidade');
insert into ingRec values ('r01','azeite','6','colher de soupa');

insert into ingRec values ('r02','couscous','120','grama');
insert into ingRec values ('r02','brocolo','100','grama');
insert into ingRec values ('r02','pimento','100','grama');
insert into ingRec values ('r02','cogumelos','90','grama');
insert into ingRec values ('r02','amendoa','10','grama');

insert into ingRec values ('r03','pao','2','fatia');
insert into ingRec values ('r03','queijo','1','fatia');
insert into ingRec values ('r03','peru','50','grama');

insert into ingRec values ('r04','lentilha','8','colher de sopa');
insert into ingRec values ('r04','pao','2','fatia');
insert into ingRec values ('r04','abacate','20','grama');
insert into ingRec values ('r04','maionese','2','colher de sopa');
insert into ingRec values ('r04','tomate','50','grama');
insert into ingRec values ('r04','alface','50','grama');

insert into ingRec values ('r05','batata','500','grama');
insert into ingRec values ('r05','peru','300','grama');
insert into ingRec values ('r05','chouriço vegano','200','grama');

insert into ingRec values ('r06','arroz','200','grama');
insert into ingRec values ('r06','cogumelos','100','grama');

insert into ingRec values ('r07','tomate','7','unidade');
insert into ingRec values ('r07','ovo','6','unidade');
insert into ingRec values ('r07','alho','3','unidade');
insert into ingRec values ('r07','azeite','3','colher de sopa');

insert into ingRec values ('r08','azeitona','10','unidade');
insert into ingRec values ('r08','alho','2','unidade');
insert into ingRec values ('r08','maionese','4','colher de sopa');
insert into ingRec values ('r08','pao','1','fatia');

insert into ingRec values ('r09','massa','200','grama');
insert into ingRec values ('r09','maça','3','unidade');
insert into ingRec values ('r09','baunilha','25','grama');
insert into ingRec values ('r09','leite','100','grama');

insert into ingRec values ('r10','tofu','300','grama');
insert into ingRec values ('r10','batata','500','grama');
insert into ingRec values ('r10','brocolo','100','grama');

insert into ingRec values ('r11','chocolate','200','grama');
insert into ingRec values ('r11','ovo','6','unidade');

insert into ingRec values ('r12','chocolate','200','grama');
insert into ingRec values ('r12','ovo','6','unidade');
insert into ingRec values ('r12','leite','100','grama');

insert into ingRec values ('r13','ovo','5','unidade');
insert into ingRec values ('r13','amendoa','50','grama');

insert into ingRec values ('r14','pato','100','grama');
insert into ingRec values ('r14','mostarda','20','grama');

insert into ingRec values ('r15','massa','300','grama');
insert into ingRec values ('r15','azeitona','10','unidade');
insert into ingRec values ('r15','pimento','200','grama');

insert into ingRec values ('r16','massa','1000','grama');
insert into ingRec values ('r16','ananas','3000','grama');
insert into ingRec values ('r16','maracuja','200','grama');
insert into ingRec values ('r16','pimento','200','grama');

insert into ingRec values ('r17','cogumelos','100','grama');
insert into ingRec values ('r17','azeite','5','colher de sopa');
insert into ingRec values ('r17','alho','2','unidade');

insert into ingRec values ('r18','queijo','100','grama');
insert into ingRec values ('r18','maracuja','3','unidade');

insert into ingRec values ('r19','cogumelos','4','unidade');
insert into ingRec values ('r19','queijo','100','grama');
insert into ingRec values ('r19','tomate','50','grama');
insert into ingRec values ('r19','pimento','50','grama');

insert into ingRec values ('r20','massa','10','fatia');
insert into ingRec values ('r20','espinafre','300','grama');
insert into ingRec values ('r20','queijo','200','grama');

insert into ingRec values ('r21','peru','400','grama');
insert into ingRec values ('r21','maionese','50','grama');
insert into ingRec values ('r21','pao','25','grama');

insert into ingRec values ('r22','pato','1000','grama');
insert into ingRec values ('r22','ovo','2','unidade');
insert into ingRec values ('r22','alho','2','unidade');

--(a) Quais as Receitas com Pato?
select NomeR
from receita natural inner join ingRec natural inner join ingrediente
where NomeI like 'pato';

--(b) Quais as Receitas que n ̃ao tˆem Pato?
select IdReceita, NomeR
from receita
except
select IdReceita, NomeR
from receita natural inner join ingRec natural inner join ingrediente
where NomeI like 'pato'
Order by IdReceita;

--(c) Quais os membros que tˆem receitas com Pato?
select count(IdMemb)
from membro natural inner join recMemb natural inner join receita natural inner join ingRec
where NomeI like 'pato';

--(d) Quais os amigos dos membros que tˆem receitas com Pato?
with autoresPato as (select IdMemb as x
from membro natural inner join recMemb natural inner join receita natural inner join ingRec
where NomeI like 'pato')

select Nome,IdMemb
from membro,amigo,autoresPato
where Idmemb=IdMemb1 and IdMemb2=x
Union
select Nome,Idmemb
from membro,amigo,autoresPato
where IdMemb=IdMemb2 and IdMemb1=x
Order by IdMemb;

--(e) Quais os membros que dao mais de 1 estrela a Receitas com Pato?
select distinct Nome,IdMemb
from membro natural inner join gosto natural inner join receita natural inner join ingRec
where valor > 1 and NomeI like 'pato';

--(f) Quais os membros que têm receitas com Ovos e Amêndoa?
select distinct Nome, IdMemb
from membro natural inner join recMemb natural inner join receita natural inner join ingRec
where NomeI like 'ovo'
intersect
select distinct Nome, IdMemb
from membro natural inner join recMemb natural inner join receita natural inner join ingRec
where NomeI like 'amendoa';

--(g) Quais os membros que têm receitas com Pato ou Peru ́?
select distinct Nome, IdMemb
from membro natural inner join recMemb natural inner join receita natural inner join ingRec
where NomeI like 'pato' or NomeI like 'peru';

--(h) Quais s ̃ao as sobremesas que tem mais fotografias?
with a as (select NomeR, IdReceita, count(Url) as x
from receita natural inner join fotRec group by (NomeR,IdReceita))

select NomeR, IdReceita
from a
where x = (select max(x) from a);

--(i) Quais sao os membros que dao 3 estrelas a todas as entradas com cogumelos?

--todas as entradas com cogumelos
with aux as (select IdReceita
from receita natural inner join ingRec natural inner join entrada
where NomeI like 'cogumelos' group by IdReceita),

--Todos os membros que têm valor 3 em entradas com cogumelos e quantas vezes cada nome aparece
aux2 as (select Nome, count(Nome) as cNome
from membro natural inner join gosto natural inner join aux
where valor = 3 and IdReceita = aux.IdReceita group by Nome),

-- O número total de entradas com cogumelos
nEntradas as (select count(aux.IdReceita) as cEntradas from aux)

-- Se o nome do membro aparece tantas vezes quanto o numero total de entradas com cogumelos, significa que gosta de todas as entradas com cogumelos
select Nome
from aux2,nEntradas
where cEntradas = cNome;

--(j) Qual  ́e o membro que tem mais gostos com 3 estrelas dos seus amigos nas suas receitas?


--(k) Para cada Sobremesa rapida que usa chocolate indique o numero de gostos com 3 estrelas e o custo.
-- Sobremesa rápida demora menos de 30 mins
with sobRapChoco as ( select NomeR, IdReceita, tempo, custo
from sobremesa natural inner join receita natural inner join ingRec
where NomeI like 'chocolate' and tempo<30)

select NomeR, IdReceita, count(valor), custo
from gosto natural inner join sobRapChoco
where valor = 3 group by NomeR,IdReceita,custo;


--(l) Quantas sobremesas têm o nome mousse de chocolate e têm mais de cinco membros que deram 2 ou mais estrelas?

with r as (select IdReceita, count(Nome) as x
from membro natural inner join gosto natural inner join receita
where NomeR like 'Mousse de chocolate' and valor >1 group by IdReceita)

select count(r.IdReceita)
from r
where x>=5;


--(m) Sabendo que uma receita pode ser recomendada a um membro se todos os seus amigos a avaliaram com duas ou mais estrelas. Indique quais sao as receitas recomendadas ao membro Manuel Silva.

with IdManuel as (select IdMemb from membro where nome like 'Manuel Silva'),

amigosManuel as (select membro.IdMemb
					 from membro,amigo,IdManuel
					 where membro.IdMemb = amigo.IdMemb1 and amigo.IdMemb2 = IdManuel.IdMemb
					 Union
					 select membro.IdMemb
					 from membro,amigo,IdManuel
					 where membro.IdMemb = amigo.IdMemb2 and amigo.IdMemb1 = IdManuel.IdMemb),

nReceitas as (select idReceita, count(IdReceita) as x
from amigosManuel natural inner join gosto
where valor >= 2 group by IdReceita),

nAmigos as (select count(idMemb) as y from amigosManuel)

select NomeR, receita.IdReceita
from nAmigos,nReceitas, receita
where x=y and nReceitas.IdReceita = receita.IdReceita




