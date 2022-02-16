#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#define NAFDS 20
#define NESTADOS 100
#define NSIMB 26
#define ESTADOSACEITES 100
#define ESTADOSINICIAl 20
#define PALAVRA 128

struct Auto{

	char c[PALAVRA + 1];
	short afd;
	short matriz[NAFDS][NESTADOS][NSIMB]; 
	short estadosAceites[NAFDS][ESTADOSACEITES]; 
	short inicio[ESTADOSINICIAl];			
	
};

void res(struct Auto a){

	short i=0, current, x=0; // percorrer o array c;
	bool found = false;
		
	current = a.inicio[a.afd];

	while(a.c[i]!='$'){

		int simbToInd = ((int) a.c[i]) - 97;
		
		current = a.matriz[a.afd][current][simbToInd];
		i++;
	}
	
	for(int j=0; a.estadosAceites[a.afd][j]!= -1; j++){
		if(a.estadosAceites[a.afd][j]==current){
			found = true;
			break;
		}
	}

	printf("%c", '"');
	while(x<i){
		printf("%c", a.c[x]);
		x++;
	}
	printf("%c ", '"');

	if(found){
		printf("aceite\n");
	}else{
		printf("rejeitada\n");
	}
}

int main(){
	short nAFDS, nEstados, nSimb, nEstadosAceit, inicial;
	struct Auto a;

	scanf("%hu", &nAFDS); // 1 Linha

	for(short i=0; i<nAFDS; i++){
		scanf("%hu %hu %hu", &nEstados, &nSimb, &inicial); //2 Linha
		a.inicio[i]=inicial;

		scanf("%hu", &nEstadosAceit); 

		if(nEstadosAceit==0){
			a.estadosAceites[i][0]= -1;

		}else{
			for(short j=0; j<nEstadosAceit; j++){       // 3 Linha
				scanf("%hd", &a.estadosAceites[i][j]);

				if(j+1==nEstadosAceit){
					a.estadosAceites[i][j+1]= -1;
				}
			}
		}

		for(short j=0; j<nEstados; j++){
			for(short x=0; x<nSimb; x++){          // 4 até prox autómato
				scanf("%hd", &a.matriz[i][j][x]);
			}
		}
	}	

	while(scanf("%hd %s", &a.afd, a.c)!= EOF){
		res(a);
	}
	
	return 0;
}