#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <netdb.h>
#include <sys/select.h>

#define PORT 5555
#define BUFSIZE 512+1

int main(){

	struct sockaddr_in serv_addr;
	char buffer_de_saida[BUFSIZE], buffer_de_entrada[BUFSIZE];

	serv_addr.sin_family = AF_INET; 
    serv_addr.sin_port = htons(PORT);

	int sock_x = socket(AF_INET, SOCK_STREAM, 0);
	
	connect(sock_x, (struct sockaddr*)&serv_addr, sizeof(serv_addr));

    while(1){

        gets(buffer_de_entrada);
        send(sock_x, buffer_de_entrada, strlen(buffer_de_entrada),0);
        
        int num_bits = recv(sock_x, buffer_de_entrada, 500, 0);
        buffer_de_entrada[num_bits] = 0;
        puts(buffer_de_entrada);

    }
}