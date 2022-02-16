#include <stdio.h>
#include <string.h>

void res(char arr[], int sz){

	int s[sz];
	int x=0;

	for(int i=0; i<sz; i++){

		if(arr[i]!='+' && arr[i]!='-' && arr[i]!='*' && arr[i]!='/' && arr[i]!='~'){
			s[x]=(int)(arr[i] - '0');
			x++;
		}else if(arr[i]=='+'){
			s[x-2]=s[x-2] + s[x-1];
			x--;
		}else if(arr[i]=='-'){
			s[x-2]=s[x-2] - s[x-1];
			x--;
		}else if(arr[i]=='*'){
			s[x-2]=s[x-2] * s[x-1];
			x--;
		}else if(arr[i]=='/'){
			if(s[x-1]!=0){
				s[x-2]=s[x-2] / s[x-1];
				x--;
			}else{
				printf("divisao por 0\n");
				return;
			}
			
		}else if(arr[i]=='~'){
			s[x-1]= -s[x-1];
		}

	}
	printf("%d\n", s[0]);
	return;
}

int main(){

	char arr[10000];
	while(scanf("%s", arr)!=EOF){
		res(arr, strlen(arr));
	}
	return 0;
}