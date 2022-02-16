#include <stdio.h>
#include <math.h>


int ePrimo(unsigned int x){
	
	for(int i=2; i<sqrt(x);i++){
		if(x%i==0){
			return 0;
		}
	}
	return 1;
}

void Res(unsigned int x){
	
	if(x==0 || x==1){
		return;
	}
	

	
	for(int i=2; i<=sqrt(x); i++){
		if(x%i==0 && ePrimo(i)==1){
			x/=i;
			printf(" %u", i);
			i--;
		}
	}
	if(ePrimo(x)==1){
		printf(" %u", x);
	}
}
int main(){

	unsigned int nInputs, n;
	scanf("%d", &nInputs);

	for(int i=0; i<nInputs; i++){

		scanf("%u", &n);
		printf("%u:", n);
		Res(n);
		printf("\n");

	}

	

	return 0;
}