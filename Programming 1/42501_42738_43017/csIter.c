cs#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "colorSquares.h"

void mostrar (int tabuleiro [20][20], int sz){
  
    for (int y=0; y<sz; y++){
        for (int x=0; x<sz; x++){
        
            if (tabuleiro[x][y]==0){
                printf(" - ");
            }
            else{
                printf(" %d ", tabuleiro[x][y]);
            }
         }
    printf("\n");
  }  
}

int main (void){
  
    int sz, x, y, tabuleiro[20][20];
    srand(time(0));
    int errosize = 1;
    int pont = 0;

    printf ("\n");
    printf("COLOR SQUARES \n \n");
    
    printf ("Regras: O tabuleiro de jogo e preenchido com numeros diferentes e o jogador pode remover grupos com numeros iguais, um grupo consiste num conjunto de quadrados com lados partilhados. \n");
    printf ("No final de cada jogada o tabuleiro e atualizado com as seguintes regras: \n");
    printf ("   gravidade: Os quadrados acima da  area vazia caem devido a gravidade. \n");
    printf ("   coluna: Quando toda a coluna esta vazia, esta colapsa movendo os blocos da direita para a esquerda de modo a fechar a separacao.\n \n");
    
    while (errosize==1){

        printf ("Insira o tamanho do tabuleiro (Um numero entre 1 e 20) - ");
        scanf("%d", &sz);                                                 
        if(sz>0 && sz<21){
            errosize=0;
        }        
        else{

            printf("\n");
        }
    }
        
    void mostrar (int tabuleiro [20][20], int sz);
  
    for (y=0; y<sz; y++){
        for (x=0; x<sz; x++){
      
            tabuleiro[x][y] = (rand()%(4)) +1;      
        }   
    }

    printf ("\n");
    printf ("Canto inferior esquerdo é (0,0) \n \n");
 
    while (tabuleiro[0][sz-1] != 0){

        int errozero = 1;
        mostrar (tabuleiro, sz);
        
        while (errozero == 1){

            printf ("\n");
            printf("Insira um valor para (x,y) (ex: 1,0) -  ");
            scanf ("%d,%d",&x,&y);
            y = (sz-1) - y;
        
            if (tabuleiro[x][y]==0 || x >= sz || y >= sz){
                printf ("\n");
                printf("Escolha um numero nao '-' e valores de x e y entre 0 e %d \n", sz-1);
            }
            else{
                errozero = 0;
            }
        }

        int marcar(int tabuleiro[20][20], int sz, int x, int y);
        void gravidade (int tabuleiro[20][20], int sz);
        void coluna (int tabuleiro[20][20], int sz);
        int pontuacao(int numquadrados);
        int jogada(int tabuleiro[20][20], int sz, int x, int y);
    
        pont = pont + jogada (tabuleiro, sz, x, y);
        printf("\n");   
    }
    mostrar(tabuleiro, sz);
    printf("\n");
    printf("Pontuacao Final: %d \n", pont);

    return 0;
}
