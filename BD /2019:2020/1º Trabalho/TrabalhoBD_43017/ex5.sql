--5(a)
select matricula
from modelo, taxi
where modelo.modelo = taxi.modelo and
marca like 'Mercedes';

--5(b)
select nome
from motorista, turno, taxi, modelo
where motorista.Nbi = turno.Nbi 
and turno.matricula = taxi.matricula
and taxi.modelo = modelo.modelo
and modelo.marca like 'Mercedes';

--5(c)
select distinct telefone
from cliente,pedido,servico,turno,telefone
where pedido.matricula = servico.matricula
and servico.matricula = turno.matricula
and turno.Nbi = telefone.Nbi
and cliente.nif like '600700800900';

--5(d)
select taxi.modelo
from motorista, turno, taxi
where motorista.nome like 'Anibal Silva'
and motorista.Nbi = turno.Nbi
and turno.matricula = taxi.matricula;

--5(e)
select nome
from motorista
except
select motorista.nome
from pedido,cliente,servico,turno,motorista
where cliente.nome like 'Jose Silva'
and cliente.Nif = pedido.Nif
and pedido.matricula = servico.matricula
and servico.matricula = turno.matricula
and turno.Nbi = motorista.Nbi;

--5(f)
select nome
from motorista
except
select motorista.nome
from motorista,turno,taxi,modelo
where modelo.marca like 'Mercedes'
and modelo.modelo = taxi.modelo
and taxi.matricula = turno.matricula
and turno.Nbi = motorista.Nbi;