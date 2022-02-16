#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "list.h"
#define MILHAO 1000000

int main(){

    struct list *listArr[MILHAO];
    char firstChar;
    int i,j;

    for(int i=0; i<MILHAO; i++){
        listArr[i] = NULL;
    }

    while(scanf("%c ", &firstChar) != EOF){
        
        if(firstChar != 'q'){

            scanf("%d %d", &i, &j);

            if(firstChar=='p'){

                if(listArr[i] == NULL){      //Se ainda nao existir nenhum par com o i sendo o primeiro elemento
                    
                    listArr[i] = list_new();
                }
                if(list_find(listArr[i], j) == -1 && list_length(listArr[i])<20){ // i existe, verificar se existe o j
                    
                    list_insert(listArr[i], j); // adicionar ao i o j
                }
            }
            else if(firstChar == 'x'){

                if(listArr[i] != NULL){         //Se o i existir
                    list_remove(listArr[i], j); //Remover o j

                    if(list_length(listArr[i])==0){ //Se o par (i,j) removido for o unico
                        listArr[i] = NULL;
                    }
                }
            }
        }
        else if(firstChar == 'q'){

            scanf("%d", &i);

            if(listArr[i]!= NULL){

                printf("%d %d ", i, list_length(listArr[i]));
                list_print(listArr[i]);
            }
            else{
                printf("%d 0\n", i);
            }
           
        }
    }

    return 0;
}