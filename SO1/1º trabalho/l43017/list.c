#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct node{
    int value;
    struct node *next_node;
};

struct list{
    
    struct node *header;
};

struct node *new_node(int value){

    struct node *node = malloc(sizeof(struct node)); 
    node->value = value;
    node->next_node = NULL;
    return node;

};

struct list *list_new(){
    
    struct list *ll= malloc(sizeof(struct list));
    ll->header = NULL;
    return ll;
};

void list_insert(struct list *ll, int value){
  
    struct node *node = ll->header;
    if(node==NULL){
        
        struct node *node0 = new_node(value);
        ll->header = node0;
        return;
    }
    while(node->next_node != NULL){
      
        node = node->next_node;
    }

    struct node *node0 = new_node(value);
    node->next_node = node0;
}

void list_print(struct list *ll){

    struct node *node = ll->header;
    if(node!=NULL){
        while(node->next_node != NULL){
            printf("%d ", node->value);
            node = node->next_node;
        }
        printf("%d\n", node->value);
    }
    else{
        printf("\n");
    }    
}

bool list_empty(struct list *ll){
    if(ll->header==NULL){
        return true;
    }
    return false;
}

int list_find(struct list *ll, int value){
    struct node *node = ll->header;
    int cont = 0;

    while(node!=NULL){
        if(node->value == value){
            return cont;
        }
        cont++;
        node = node->next_node;
    }
    return -1;    
}

void list_remove(struct list *ll, int value){
    struct node *node = ll->header;

    if(node!=NULL && node->value==value){ //Se o nó a remover for o header

        ll->header = node->next_node;
        free(node);
    }
    while(node->next_node!=NULL){

        if(node->next_node->value == value){

            struct node *nodeToRemove = node->next_node;    
            node->next_node = nodeToRemove->next_node;
            free(nodeToRemove);
            break;
        }
        node = node->next_node;
    }
}

int list_length(struct list *ll){
    struct node *node = ll->header;
    int cont = 0;
    while(node!=NULL){
        cont++;
        node = node->next_node;
    }
    return cont;
}

int list_nth(struct list *ll, int n){
    struct node *node = ll->header;
    int cont = 0;

    if(!list_empty(ll)){
        while(cont != n){
            
            node = node->next_node;
            cont ++;
            if(node->next_node==NULL){
                break;
            }

        }
        if(cont == n){
            return node->value;
        }
    }
    return -1;
}

void list_mudarValue(struct list *ll, int n, int value){
    struct node *node = ll->header;
    int cont =0;

    if(list_length(ll) >= n){

        while(cont != n){

            node = node->next_node;
            cont++;
        }
        node->value = value;
    }
        

}
void list_linePrint(struct list *ll){
    struct node *node = ll->header;
    if(node!=NULL){
        while(node->next_node != NULL){
            printf("%d ", node->value);
            node = node->next_node;
        }
        printf("%d ", node->value);
    }  
}
// int main(){

//     struct list *ll = list_new();
    

//     int posicao = list_find(ll, 10);

//     list_mudarValue(ll, posicao, list_nth(ll,posicao)-1);


//     return 0;
// }