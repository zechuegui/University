#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "colorSquares.h"

int main (void){

  FILE *f;
  int a[500], tabuleiro[20][20], sz, x, y, totalmoves;
  x=0;
  y=0;
  int i=0;
  char t1[10000], nomef[100];
  int pont =0;

  printf("nome do ficheiro? ");
  scanf("%s", nomef);
  f = fopen(nomef, "r");

  fscanf(f, "%d", &a[0]);
  sz = a[0];

  for (int i=0; i < (sz*sz); i++){

    fscanf(f, "%1d", &a[i]);  
  }
  i=0;
  for (int y=0; y<sz; y++){
    for (int x=0; x<sz; x++){

        
      tabuleiro [x][y]=a[i];    
      i++;      
    }
  }

  fscanf(f, "%d", &totalmoves);
  
  int b=0;
  for(int k=1; k<totalmoves; k++){
    b+=2;
  }
  
  x=(totalmoves*3)+b;
  i=0;
  for(int k=0; k<x; k++){

    fscanf(f, "%1s", &t1[i]);
    i++;
  }

  for(int i=0; t1[i]!='\0'; i++){

    int x = t1[i] - '0';
    int y = t1[i+1] - '0';
    y = (sz-1)-y;

    if (tabuleiro[x][y]!=0){
      pont = pont + jogada (tabuleiro, sz, x, y);
      
    }

    i+=2;
  }
  printf("\n");

  printf ("Resultado final %d \n", pont);
  

  fclose(f);





return 0;
}
