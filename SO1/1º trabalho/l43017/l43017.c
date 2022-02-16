#include <stdio.h>
#include "queue.h"
#include "list.h"
#include <stdlib.h>
#define QUANTUM 300000

struct process{
    
    int PID, chegada;
    bool entrou;
    struct queue *processRUN;
    struct queue *processBlocked;    
};

struct process *process_new(int PID, int chegada){

    struct process *p= malloc(sizeof(struct process));  
    p->PID=PID;
    p->chegada = chegada;
    p->entrou=false;
    p->processRUN = queue_new();
    p->processBlocked = queue_new();
    
    return p;
}

void print_instante(int instante, struct queue *READY, struct queue *RUN, struct list *BLOCKED, int totalProcessos){
    
    if(instante / 10 >= 1){ //Ver se o numero de algarismos do instante
        printf(" %d ", instante);
    }
    else if(instante / 100 >= 1){
        printf("%d ", instante);
    }
    else{
        printf("  %d ", instante);
    }
    printf("| READY  ");

    queue_print(READY);
    for(int z=0; z< (totalProcessos - queue_size(READY)) - 1; z++){ 
        printf("    ");
    }

    printf(" | RUN  ");
    if(queue_isEmpty(RUN)){
        printf("    ");
    }
    else{
        queue_print(RUN);
    }

    printf(" | BLOCKED  ");
    list_print(BLOCKED); 
}

int main(){

    char c;
    int n, chegada,processosActivos, totalProcessos = 0, tempoCPU = 0;
    struct process *processArr[50];
    
    struct queue *READY = queue_new();
    struct queue *RUN = queue_new();
    struct list *BLOCKED = list_new();
    
    for(int i=0; i<50; i++){
        processArr[i] = NULL;
    }

    for(int i=0; scanf("%d %d", &n, &chegada)!=EOF; i++, totalProcessos++){
        
        struct process *p = process_new(n, chegada);
        int x=0;

        do{ 
            if(scanf("%d", &n)==EOF){
                break;
            }

            scanf("%c", &c);

            if(x%2==0){
                enqueue(p->processRUN, n);                 
            }
            else{
                enqueue(p->processBlocked, n);
            }
            x++;               

        }while(c!='\n');
      
        processArr[i] = p;
    }

    processosActivos = totalProcessos;
    for(int instante=0; processosActivos>0; instante++){ //Instantes até todos os processos acabarem
             
        for(int x=0; x<totalProcessos; x++){ //Percorrer todos os processos, decrementar valores

            if(processArr[x] != NULL){ //Se o processo ainda não acabou

                struct process *p = processArr[x];

                if(p->entrou == true){ //Se o processo já entrou

                    if(queue_find(RUN, p->PID)){ //Se o processo está no RUN, decrementar na processRun queue 

                        queue_decrementarPrimeiro(p->processRUN);
                        tempoCPU++;      
                    }
                    else if(list_find(BLOCKED, p->PID)!= -1){//Se o processo está no BLOCKED, decrementar na processBlocked queue

                        queue_decrementarPrimeiro(p->processBlocked);                     
                    }
                }
                
            }
        }
/////////////////////////////////////////////////////////////////////ACTUALIZAR O BLOCKED/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        // int blockedLength =list_length(BLOCKED);
        for(int y=0; y<list_length(BLOCKED); y++){ //ACTUALIZAR O BLOCKED
                            
            int blockedId = list_nth(BLOCKED, y); //Percorrer por ordem, os elementos que estão no BLOCKED
        
            for(int x=0;blockedId!=-1 && x<totalProcessos; x++){ //percorrer os processos
                
                struct process *p = processArr[x];

                if(p!= NULL && p->PID == blockedId && queue_front(p->processBlocked)==0){ //Se o processo equivale ao id, e tem o processBlocked a 0, vai para o run ou para o ready

                    dequeue(p->processBlocked);    
                    list_remove(BLOCKED ,p->PID); 

                    if(queue_isEmpty(RUN)){//Se o RUN estiver vazio

                        enqueue(RUN, p->PID);
                    }
                    else{
                        enqueue(READY, p->PID);
                    }

                    y--;
                }
            }           
        }
////////////////////////////////////////////////////////VER SE O PROCESSO QUE ESTÁ NO RUN JÁ ACABOU////////////////////////////////////////////////////////////////////////////////////
        int runPid = queue_front(RUN); //pid = PID do processo que está no RUN
        for(int x=0; x<totalProcessos; x++){
            
            if(processArr[x]!=NULL){

                struct process *p = processArr[x]; 

                if(p->PID == runPid){ //Se o runPid == PI

                    if(queue_front(p->processRUN)==0){//Se o processo acabou o que tinha a fazer no CPU
                        
                        dequeue(RUN);
                        dequeue(p->processRUN);

                        if(!(queue_isEmpty(p->processBlocked))){ //Verificar se o processo ainda tem de esperar por I/O
                            
                            list_insert(BLOCKED, p->PID);                           
                        }
                        tempoCPU = 0;
                    }
                    else if(queue_front(p->processRUN)>0 && tempoCPU >= QUANTUM){//Se o processo ainda nao acabou, mas o tempo no CPU = quantum

                        enqueue(READY, dequeue(RUN));
                        tempoCPU = 0;
                    }
                }
            }
        }
////////////////////////////////////////////////SE NAO ESTA NENHUM PROCESSO NO RUN METER O PRIMEIRO DO READY PARA LÁ////////////////////////////////////////////////////////
        if(queue_isEmpty(RUN) && !(queue_isEmpty(READY))){ //Se o RUN estiver vazio e o READY nao vazio
            enqueue(RUN, dequeue(READY));
        }


////////////////////////////COM TODAS AS POSIÇOES ACTUALIZADAS, ENTRAM OS NOVOS PROCESSOS, E SAO REMOVIDOS OS QUE JÁ ACABARAM////////////////////////////////////////////////
       
        for(int x=0; x<totalProcessos; x++){ //Percorrer todos os processos

            if(processArr[x]!=NULL){

                struct process *p = processArr[x];

                if(p->entrou == false && p->chegada==instante){ //Se o processo ainda nao entrou, coloca-lo ou no RUN ou no READY
                
                    if(queue_isEmpty(RUN)){  //Se ainda nao estiver nenhum processo no RUN
                        
                        enqueue(RUN, p->PID);
                    }
                    else{                       //Colocar no READY
                       
                        enqueue(READY, p->PID);
                    }

                    p->entrou = true; //Mudar a flag, porque o processo já entrou
                }
                else if(queue_size(p->processBlocked)==0 && queue_size(p->processRUN)==0){ //Se o processo já acabou

                    processArr[x]=NULL;
                    free(p);
                    processosActivos--;
                }
            }
        }
        if(processosActivos>0){

            print_instante(instante, READY, RUN, BLOCKED, totalProcessos);
        }
        else{
            printf("Todos os Processos Terminaram.\n");
        }
               
    }
    return 0;
}