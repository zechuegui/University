#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int marcar (int tabuleiro [20][20], int sz, int x, int y){

    int n_quadrados = 1;
    int valor;
    valor = tabuleiro[x][y];
    tabuleiro[x][y]=0;
    
    if (tabuleiro[x][y+1]==valor && y+1 < sz){
        n_quadrados += marcar(tabuleiro, sz, x, y+1);
    }
    if (tabuleiro[x][y-1]==valor && y-1 >= 0){
        n_quadrados += marcar(tabuleiro, sz, x, y-1);
    }
    if (tabuleiro[x+1][y]==valor && x+1 < sz){
        n_quadrados += marcar(tabuleiro, sz, x+1, y);
    }
    if (tabuleiro[x-1][y]==valor && x-1 >= 0){
        n_quadrados += marcar(tabuleiro, sz, x-1, y);
    }
    return n_quadrados;
}

int pontuacao(int numquadrados){
  
    int pontos;
    pontos = (numquadrados * (numquadrados + 1)) / 2;
    return pontos;
}

void gravidade (int tabuleiro[20][20], int sz){

    for (int x=0; x < sz; x++){
        for (int y=sz-1; y > 0; y--){
            if (tabuleiro[x][y]== 0){
                for (int a=1; a<sz; a++){
                    if (tabuleiro[x][y-a]!=0 && y-a >= 0){
                        tabuleiro[x][y]=tabuleiro[x][y-a];
                        tabuleiro[x][y-a]= 0;
                        a=sz;
                    }
                }
            }
        }
    }
}

void coluna (int tabuleiro[20][20], int sz){

    for (int i=0; i<sz; i++){

        for (int x = 0; x < sz; x++){
            int cont = 0;
            for (int y=0; y<sz; y++){
                if (tabuleiro[x][y] != 0){
                    y = sz;
                }
                else{
                    cont++;
                }
            }
            if (cont == sz){
                for (int y=0; y<sz; y++){
                    tabuleiro [x][y] = tabuleiro [x+1][y];
                    tabuleiro [x+1][y]=0;
                }
            }
        }   
    }
}


int jogada(int tabuleiro[20][20], int sz, int x, int y){

    int pont;
    pont =  pontuacao (marcar (tabuleiro, sz, x, y));
    gravidade (tabuleiro, sz);
    coluna (tabuleiro, sz);
    return pont;
}