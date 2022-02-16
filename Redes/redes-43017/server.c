#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>
#include <stdbool.h>

#define PORT 5555
#define BUFSIZE 512+1

struct sockaddr_in serv_addr;
struct addrinfo *result;
struct cliente *clientes[20];
char *canais[4] = {"default", "cnn" ,"oss"}; 

struct cliente{
    int sock; // o socket associado a este cliente
    bool operador; // iniciado a false
    char chat_pref[10]; // iniciado como default
    char current_chat[10];//
    char username [10]; // 9 char's + char nulo
    char password [10]; // 9 char's + char nulo
    bool online;
};

struct cliente *new_cliente(int sock)
{
	struct cliente *cliente = malloc(sizeof(struct cliente));
    cliente->sock=sock;
	cliente->operador = false;
    strcpy(cliente->chat_pref,"default");
    cliente->online = true;
    strcpy(cliente->current_chat,"default");

	return cliente;
}

// Dado um input de um cliente coloca no char array final a mensagem sem o código de 5 caracteres
void codificarMensagem(char *inicial, char *final, int size_in){
    for (int x=5; x<=size_in; x++){

        final[x-5] = inicial[x];
    }
}

int main(){


    int server_fd, new_socket; 
    struct sockaddr_in address;
    
    int j=0;
    int opt = 1;      // for setsockopt() SO_REUSEADDR, below
    int addrlen = sizeof(address); 
    char buffer[BUFSIZE];

    fd_set all_fds;   // master file descriptor list
    fd_set sel_fds;   // temp file descriptor list for select()
    int maxfd;        // maximum file descriptor number
    
    server_fd = socket(AF_INET, SOCK_STREAM , 0);

    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt));

    address.sin_family = AF_INET; 
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT ); 
    
    bind(server_fd, (struct sockaddr *)&address, sizeof(address));

    listen(server_fd, 3);
   
    FD_ZERO(&all_fds);            
    FD_SET(server_fd, &all_fds); 

    maxfd = server_fd;          
    
    // INICIALIZAR O ARRAY DE CLIENTES A NULl
    for(int k=0;k<20;k++){
        clientes[k]=NULL;
    }

    while(1) {  // Server loop
        int i;
        
        sel_fds = all_fds; 
        
        
        if ( select(maxfd+1, &sel_fds, NULL, NULL, NULL) == -1 ) {
            perror("select failed");
            exit(EXIT_FAILURE);
        }


        for(i=0; i<=maxfd; i++) {
            if (FD_ISSET(i, &sel_fds)) { 


                if (i == server_fd) {
                
                    if ((new_socket = accept(server_fd, (struct sockaddr *)&address,  
                                            (socklen_t*)&addrlen))<0) { 
                        perror("accept failed"); 
                        exit(EXIT_FAILURE); 
                    }

                    printf("Client connected.\n");
                    
                    // Adicionar o novo cliente à lista de clientes
                    if(clientes[j]==NULL){
                        clientes[j]=new_cliente(new_socket);
                        j++;
                    }

                  
                    FD_SET(new_socket, &all_fds);

                   
                    maxfd = new_socket > maxfd ? new_socket : maxfd;          
                }
                else {
                    
                    bzero(buffer, BUFSIZE);
                    
                    // No buffer temos a mensagem enviada pelo cliente
                    int bytes = recv(i, buffer, BUFSIZE-1, 0);
                    printf("Alguem enviou uma mensagem\n");
    
                    if ( bytes == 0 ) { 
                        FD_CLR(i, &all_fds);
                        
                    }
                    else {
                        
                        // Descobrir quem mandou a mensagem
                        struct cliente *sender;
                        //Percorrer o array de clientes
                        for (int x=0; clientes[x]!='\0'; x++){

                            // Se a socket do cliente tiver o mesmo número
                            if(clientes[x]->sock== i){
                                // Significa que foi este que enviou a mensagem
                                sender = clientes[x];
                            }
                        }

                        // Codificar a mensagem
                        if (strncmp(buffer, "NICK ", 5)==0){ // O cliente quer criar ou alterar o nick
                            
                            bool nome_valido = true;
                            char new_nick [strlen(buffer)-5];

                            if (strlen(buffer)<=14){
  
                                codificarMensagem(buffer,new_nick,strlen(buffer));

                                for (int x=0; new_nick[x]!='\0'; x++){

                                    int c = new_nick[x];

                                    if (!((c >= 48 && c <= 57) || (c >= 65 && c <= 122))){

             
                                        nome_valido = false;
                                        break;
                                    }
                                }
                            }
                            else{
                                nome_valido=false;
                            }

                            if (!nome_valido){ // Se o nick introduzido não é válido

                                send(sender->sock, "RPLY 003 - Erro: Nome pedido não válido.\n" , strlen("RPLY 003 - Erro: Nome pedido não válido.\n"),0);
                            }
                            else{ // Se é válido
                                
                                for (int x=0; clientes[x]!='\0';x++){ // Percorrer todos os clientes

                                    if (strcmp(clientes[x]->username,new_nick)==0){ // Se algum cliente já tem esse nome

                                        nome_valido = false; // O nome escolhido já esta a ser utilizado
                                        break;
                                    }
                                }    

                                if (nome_valido){

                                    // AVISAR TODOS OS CLIENTES NO MESMO SERVIDOR DA MUDANÇA DE NOME
                                    for (int x=0; clientes[x]!='\0';x++){
                                        if (strcmp(clientes[x]->current_chat,sender->current_chat)==0 && clientes[x]->sock != i){ // Se o cliente está no mesmo chat do sender
                                            

                                            if (strcmp(sender->username,"")==0){ // Se o sender ainda nao tinha nome
                                                
                                                char msg_aux[BUFSIZE] = "server :> novo utilizador ";
                                                strcat(msg_aux,new_nick);
                                                strcat(msg_aux,"\n");

                                                send(clientes[x]->sock, msg_aux, strlen(msg_aux),0);
                                            }
                                            else{
                                                char msg_aux[BUFSIZE] = "server :> nome antigo ";
                                                strcat(msg_aux,sender->username);
                                                strcat(msg_aux," mudou o seu nome para ");
                                                strcat(msg_aux,new_nick);
                                                strcat(msg_aux,"\n");

                                                send(clientes[x]->sock, msg_aux, strlen(msg_aux),0);
                                            }
                                        }
                                    }

                                    strcpy(sender->username,new_nick);


                                    // ATUALIZAR NO ARRAY DE CLIENTES
                                    for(int x=0; clientes[x]!='\0';x++){
                                        if(clientes[x]->sock==i){

                                            clientes[x]=sender;
                                            break;
                                        }
                                    }

                                    send(sender->sock, "RPLY 001 - Nome atribuído com sucesso\n" , strlen("RPLY 001 - Nome atribuído com sucesso\n"),0);
                                }
                                else{

                                    send(sender->sock, "RPLY 004 - Erro: nome já em uso.\n" , strlen("RPLY 004 - Erro: nome já em uso.\n"),0);
                                }       
                            }        
                        }
                        else if (strncmp(buffer, "MSSG ", 5)==0){
                            
                            if (strlen(buffer)<512){
                                                            // Se a pessoa já se autenticou
                                if (strncmp(sender->username,"",1)!=0 ){
                                    
                                    char aux [strlen(buffer)-5];
                                    char msg[BUFSIZE]="";
                                    strcat(msg, sender->username);
                                    strcat(msg, ":> ");
                                    codificarMensagem(buffer,aux, strlen(buffer));
                                    strcat(msg,aux); 
                                    strcat(msg,"\n");

                                    if (strlen(aux)>0){

                                        send(sender->sock, "RPLY 101 - mensagem enviada com sucesso.\n", strlen("RPLY 101 - mensagem enviada com sucesso.\n"),0);

                                        for (int x=0; clientes[x]!='\0'; x++){

                                            if(clientes[x]->sock!= i && strcmp(clientes[x]->current_chat,sender->current_chat)==0){
                            
                                                send(clientes[x]->sock, msg, strlen(msg),0);
                                            }
                                        }
                                    }
                                    else{
                                        send(sender->sock, "RPLY 102 - Erro. Não há texto na mensagem.\n", strlen("RPLY 102 - Erro. Não há texto na mensagem.\n"),0);
                                    }
                                }
                                else{
                                    send(i, "RPLY 002 - Erro: Falta introdução do nome.\n" , strlen("RPLY 002 - Erro: Falta introdução do nome.\n"),0);
                                }
                            }
                            else{
                                send(i, "RPLY 103 - Erro. Mensagem demasiado longa.\n" , strlen("RPLY 103 - Erro. Mensagem demasiado longa.\n"),0);
                            }
                        }
                        else if(strncmp(buffer, "JOIN ",5)==0){
                            
                            // Descobrir quem mandou a mensagem
                            struct cliente *sender;
                            for (int x=0; clientes[x]!='\0'; x++){

                                if(clientes[x]->sock== i){
                                   
                                    sender = clientes[x];
                                }
                            }

                            if (strncmp(sender->username,"",1)!=0){

                                char nomeCanal [strlen(buffer)-5];
                                codificarMensagem(buffer,nomeCanal,strlen(buffer));
                                bool canal_existe = false;

                                for (int x=0; canais[x]!='\0'; x++){
                                    if(strcmp(canais[x],nomeCanal)==0){
                                        canal_existe=true;
                                        break;
                                    }
                                }

                                if(canal_existe){

                                    char msg_saida [50] = "server :> ";
                                    strcat(msg_saida, sender->username);
                                    strcat(msg_saida, " deixou este canal\n");

                                    char msg_entrada [50] = "server :> ";
                                    strcat(msg_entrada, sender->username);
                                    strcat(msg_entrada, " entrou neste canal\n");

                                    if(strcmp(sender->current_chat,nomeCanal)!=0){ 
                                        
                                        for (int x=0; clientes[x]!='\0';x++){
                                            if(strcmp(sender->current_chat,clientes[x]->current_chat)==0 && clientes[x]->sock != i ){
                                                
                                                

                                                send(clientes[x]->sock, msg_saida , strlen(msg_saida),0);
                                            }
                                            else if(strcmp(nomeCanal,clientes[x]->current_chat)==0){
                                                send(clientes[x]->sock, msg_entrada , strlen(msg_entrada),0);
                                            }
                                        }

                                        strcpy(sender->current_chat,nomeCanal);

                                        // ATUALIZAR NO ARRAY DE CLIENTES
                                        for(int x=0; clientes[x]!='\0';x++){
                                            if(clientes[x]->sock==i){
               
                                                clientes[x]=sender;
                                                break;
                                            }
                                        }

                                        send(i, "RPLY 301 - Mudança de canal com sucesso.\n" , strlen("RPLY 301 - Mudança de canal com sucesso.\n"),0);
                                        
                                    }
                                    else{// Significa que o cliente já está naquele canal
                                        send(i, "RPLY 304 – Erro. já está nesse canal.\n" , strlen("RPLY 304 – Erro. já está nesse canal.\n"),0);
                                    }
                                }
                                else{                               
                                    send(i, "RPLY 302 – Erro. canal não existente.\n" , strlen("RPLY 302 – Erro. canal não existente.\n"),0);
                                }

                            }
                            else{
                                send(i, "RPLY 303 - Erro. Não pode mudar para o canal.\n" , strlen("RPLY 303 - Erro. Não pode mudar para o canal.\n"),0);
                            }
                        }
                        else if(strncmp(buffer, "LIST", 4)==0){

                            // Descobrir quem mandou a mensagem
                            struct cliente *sender;
                            for (int x=0; clientes[x]!='\0'; x++){

                                if(clientes[x]->sock== i){

                                    sender = clientes[x];
                                }
                            }
                            if (strncmp(sender->username,"",1)!=0){

                                char msg[BUFSIZE] = "RPLY 401 ";
                                for (int i=0; canais[i]!='\0';i++){
                                    strcat(msg,"<");
                                    strcat(msg,canais[i]);
                                    strcat(msg,">;");
                                }
                                msg[strlen(msg)-1]='\0';

                                send(i, msg, strlen(msg),0);
                            }
                            else{
                                send(i, "RPLY 002 - Erro: Falta introdução do nome.\n" , strlen("RPLY 002 - Erro: Falta introdução do nome.\n"),0);
                            }
                                

                        }
                        else if(strncmp(buffer, "WHOS", 4)==0){
                            
                            // Descobrir quem mandou a mensagem
                            struct cliente *sender;
                            for (int x=0; clientes[x]!='\0'; x++){

                                if(clientes[x]->sock== i){
                                    sender = clientes[x];
                                }
                            }
                            
                            if (strncmp(sender->username,"",1)!=0){

                                char msg[300] = "RPLY 501 ";


                                for (int x=0; clientes[x]!='\0';x++){
                                    if(strcmp(sender->current_chat,clientes[x]->current_chat)==0){
                                        
                                        if(strcmp(clientes[x]->username,"")!=0){
                                            strcat(msg,"<");
                                            strcat(msg,clientes[x]->username);
                                            strcat(msg,">;");
                                        }

                                    }
                                }
                                msg[strlen(msg)-1]='\0';
                                send(i, msg, strlen(msg),0);
                            }
                            else{

                                send(i, "RPLY 002 - Erro: Falta introdução do nome.\n" , strlen("RPLY 002 - Erro: Falta introdução do nome.\n"),0);
                            }

                        }
                        else{
                            send(i, "Codigo invalido.\n" , strlen("Codigo invalido.\n"),0);
                        }                                                
                    }
                }
            }
        }    
    }
}