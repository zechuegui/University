#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int main (void);

int marcar (int tabuleiro [99][99], int sz, int x, int y){

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

void gravidade (int tabuleiro[99][99], int sz){

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

void coluna (int tabuleiro[99][99], int sz){

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

int jogada(int tabuleiro[99][99], int sz, int x, int y){

    int pont=0;
    pont =  pontuacao (marcar (tabuleiro, sz, x, y));
    gravidade (tabuleiro, sz);
    coluna (tabuleiro, sz);
    return pont;
}
void mostrarAuto (int tabuleiro [99][99], int sz, FILE *fjogo){
  
    for (int y=0; y<sz; y++){
        for (int x=0; x<sz; x++){
        
            if (tabuleiro[x][y]==0){
                fprintf(fjogo, "-");
            }
            else{
                fprintf(fjogo, "%d", tabuleiro[x][y]);
            }
         }
    fprintf(fjogo, "\n");
  }  
}

int main (){

  FILE *fjogo;
  int c[10000];
  fjogo = fopen("a.txt", "w");
  int i =0;

  int sz, x, y, totalmoves, errozero, resultado;
  resultado =0;
  srand(time(0));
  sz = (rand()%(10)) +1;
  fprintf(fjogo, "%d\n", sz);
  int tabuleiro[99][99];
  void mostrarAuto (int tabuleiro [99][99], int sz, FILE *fjogo);
  for (y=0; y<sz; y++){
    for (x=0; x<sz; x++){
      tabuleiro[x][y] = (rand()%(4)) +1;
    }
  }
  mostrarAuto (tabuleiro, sz, fjogo);

  totalmoves = 0;
  while (tabuleiro[0][sz-1] != 0){
    errozero = 1;

    while (errozero == 1){
      x = (rand()%(sz));
      y = (rand()%(sz));
      y = (sz-1) - y;
      
      if (tabuleiro[x][y]==0 || x >= sz || y >= sz){
        errozero = 1;
      }else{
        errozero = 0;
        c[i]=x;
        c[i+1]=((sz-1)-y);
        i = i + 2;
      }
    }

    resultado += jogada (tabuleiro, sz, x, y);
    totalmoves = totalmoves + 1;
  }
  fprintf(fjogo, "%d", totalmoves);
  fprintf (fjogo, "\n");
 
  for (i=0; i <(totalmoves*2); i+= 2){
    if (i!=0){
      fprintf(fjogo, "%c", ',');
      fprintf (fjogo, "%c", ' ');
    }
    fprintf (fjogo, "%d", c[i]);
    fprintf (fjogo, "%c", ' ');
    fprintf(fjogo, "%d", c[i+1]);
  }

  fclose(fjogo);

  printf ("Pontuacao Final = %d \n", resultado);


   return 0;
}
