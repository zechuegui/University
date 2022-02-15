% estado_inicial(e([[l,l,l],[l,l,l],[l,l,l]], x)).
estado_inicial(e([[l,l,l],[l,l,l],[l,l,l]], x)).

terminal(L):-terminal(L,_).
terminal(L, X):- coluna(L, X);linha(L, X);diagonal(L, X).
terminal(e([L1,L2,L3], _), 0) :- \+ (member(l, L1);member(l, L2);member(l,L3)).

coluna(e( [ [X|_],[X|_],[X|_] ], _), X):- X=x; X=o.
coluna(e( [ [_,X,_],[_,X,_],[_,X,_] ], _), X):- X=x; X=o.
coluna(e( [ [_,_,X],[_,_,X],[_,_,X] ], _), X):- X=x; X=o.

linha(e( [ [X,X,X]|_ ], _), X):- X=x; X=o.
linha(e( [ _,[X,X,X],_ ], _), X):- X=x; X=o.
linha(e( [ _,_,[X,X,X] ], _), X):- X=x; X=o.

diagonal(e( [ [X,_,_],[_,X,_],[_,_,X] ], _), X):- X=x; X=o.
diagonal(e( [ [_,_,X],[_,X,_],[X,_,_] ], _), X):- X=x; X=o.

valor(E,V,_):- terminal(E, X), ((X = x) -> V = 1 ;((X = 0)->V = 0; V = -1)).

op1(e([L1,L2,L3], X), joga(X,1,N), e([NL1,L2,L3], O)):- troca(X,O), acrescenta(L1, X, NL1, N).
op1(e([L1,L2,L3], X), joga(X,2,N), e([L1,NL2,L3], O)):- troca(X,O), acrescenta(L2, X, NL2, N).
op1(e([L1,L2,L3], X), joga(X,3,N), e([L1,L2,NL3], O)):- troca(X,O), acrescenta(L3, X, NL3, N).

troca(x,o).
troca(o,x).

acrescenta([l,Y,Z], V, [V,Y,Z], 1).
acrescenta([X,l,Z], V, [X,V,Z], 2).
acrescenta([X,Y,l], V, [X,Y,V], 3).