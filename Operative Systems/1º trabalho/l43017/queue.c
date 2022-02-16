#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include "list.h"

struct queue{
    struct list *ll;
};

struct queue *queue_new(){
    struct queue *q = malloc(sizeof(struct queue));
    q->ll = list_new();
    return q;
}

void enqueue(struct queue *q, int value){
    list_insert(q->ll, value);
}

int queue_front(struct queue *q){
    return list_nth(q->ll, 0);
}

int dequeue(struct queue *q){
    if(!list_empty(q->ll)){

        int element = list_nth(q->ll, 0);
        list_remove(q->ll, queue_front(q));
        return element;
    }
    return -1;
}

void queue_print(struct queue *q){
    list_linePrint(q->ll);
}

int queue_size(struct queue *q){
    return list_length(q->ll);
}

void queue_decrementarPrimeiro(struct queue *q){
    list_mudarValue(q->ll, 0, queue_front(q)-1);
}

bool queue_find(struct queue *q, int element){
    return list_find(q->ll, element) != -1;
}

bool queue_isEmpty(struct queue *q){
    return list_length(q->ll)==0;
}
// int main(){

//     struct queue *q = queue_new();

//     enqueue(q, 10);
//     //queue_print(q);
//     printf(queue_isEmpty(q) ? "VAZIA\n" : "N VAZIA\n");
//     dequeue(q);
//     printf(queue_isEmpty(q) ? "VAZIA\n" : "N VAZIA\n");
    
//    /* printf(queue_find(q, 10) ? "TRUE\n" : "FALSE\n");
//     queue_decrementarPrimeiro(q);
//     queue_print(q);
//     printf(queue_find(q, 10) ? "TRUE\n" : "FALSE\n");*/
    
//     return 0;
// }




// typedef struct queue queue;

// typedef struct queue{   

//     int *arr; 
//     int primeiro,ultimo, size;
   
// }queue;

// queue *queue_new(){

//     struct queue *queue = malloc(sizeof(queue));
//     queue->arr = (int*)malloc(10*sizeof(int));
//     queue->size=0;
//     queue->primeiro=0;
//     queue->ultimo=0;
    

//     return queue;     
// }

// bool isEmpty(queue *q){
//     return q->size==0;
// }

// void enqueue(queue *q, int element){
    
//     if(q->ultimo+1 < 100){

//         q->arr[q->ultimo]= element;
//         q->ultimo++;
//         q->size++;
//     }
// }

// int dequeue(queue *q){
//     if(!isEmpty(q)){
 
//         int element = q->arr[q->primeiro];
//         q->primeiro++;
//         q->size--;
//         return element;
//     }
//     return -1;
// }

// int queue_front(queue *q){
//     if(!isEmpty(q)){
//         return q->arr[q->primeiro];
//     }
//     return -1;
// }

// void queue_print(queue *q){
//     if(!isEmpty(q)){    

//         for(int i=q->primeiro; i < q->ultimo - 1; i++){
//             printf("%d ", q->arr[i]);
//         }        
//         printf("%d\n", q->arr[q->ultimo-1]);
//     }
// }
