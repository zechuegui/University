#include <stdio.h>

int main(){

    for (int i=1;i<21;i++){

        if(i!=7){
            if(i/10==0){   
            printf("Insert into amigo values('m07','m0%d');\n",i);
        }
            else{
                printf("Insert into amigo values('m07','m%d');\n",i);
            }
        }
        
        

    }

    return 0;
}
