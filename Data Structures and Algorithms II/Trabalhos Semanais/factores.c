#include <stdio.h>


int ePrimo(int x){
	
	for(int i=2; i<x;i++){
		if(x%i==0){
			return 0;
		}
	}
	return 1;
}

int Res(int x){

	int cont=0;

	if(x==0 || x==1){
		return 0;
	}

	if(ePrimo(x)==1){
		return 1;
	}
	/*
	while(x%2==0){
		x/=2;
		cont++;
	}

	for(int i=3; i<x; i+=2){
		if(x%i==0 && ePrimo(i)==1){
			x/=i;
			cont++;
			i-=2;
		}
	}*/
	for(int i=2; i<x; i++){
		if(x%i==0 && ePrimo(i)==1){
			x/=i;
			cont++;
			i--;
		}
	}

	return cont+1;
}
int main(){

	int nInputs, n;
	scanf("%d", &nInputs);

	for(int i=0; i<nInputs; i++){

		scanf("%d", &n);
		printf("%d: %d\n", n, Res(n));

	}

	

	return 0;
}