#include <stdio.h>

long pesquisaL(long sz,long s[], long x){
	for(long i=0; i<sz; i++){
		if(s[i]==x){
			return i;
		}
	}
	return -1;
}

long res(long sz, long s[sz],long x){

		long sum=0, k=0;
		long inicio =0, fim=sz;


		for(long inicio2=0; inicio2<sz; inicio2++){
			sum=0;
			for(long i=inicio2; i<sz;i++){
				sum+=s[i];
				//printf("sum=%ld, numero=%ld\n", sum, s[i]);

				if(sum==x){
					if(fim-inicio >i-inicio2){
						k=1;
						inicio=inicio2+1;
						fim=i+1;
					}

				}
			}
		}
	if(k==1){
		if(fim-inicio==1){
			printf("s[%ld] + s[%ld] = %ld\n", inicio,fim, x);
			return 1;
		}else{//Mais do que dois elementos
			printf("s[%ld] + ... + s[%ld] = %ld\n", inicio,fim, x);
			return 1;
		}
	}
	else{
		printf("nenhuma subsequencia soma %ld\n", x);
	}
	return 0;
}

int main(){
	long sz,x,y;
	scanf("%ld", &sz);
	long s[sz]; 
	
	for(int i=0; i<sz; i++){
		scanf("%ld", &s[i]);
	}
	scanf("%ld", &x);
	y=pesquisaL(sz,s,x);
	if(y!=-1){
		printf("s[%ld] = %ld\n", y+1, x);
	}
	else if(res(sz,s,x)==0){
		
	}
	
	return 0;
}