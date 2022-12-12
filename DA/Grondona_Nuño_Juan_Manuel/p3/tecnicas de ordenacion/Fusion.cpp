#include <iostream>
#include <algorithm>
#include <cstring>
#include "../cronometro.h"
#define N 3

void copia(int* des, int* ori, int n){
    for (int i = 0; i < n; i++){
        des[i] = ori[i];
    }
}

void merge(int* vec, int tam, int izquierda,int medio,int derecha){
    int h,i,j,k;
    int b[tam];
    h=izquierda;
    i=izquierda;
    j=medio+1;
    
    while((h<=medio)&&(j<=derecha)){
        if(vec[h]<=vec[j]){
            b[i]=vec[h];
            h++;
        }
        else{
            b[i]=vec[j];
            j++;
        }
        i++;
    }
    if(h>medio){
        for(k=j;k<=derecha;k++){
            b[i]=vec[k];
            i++;
        }
    }
    else{
        for(k=h;k<=medio;k++){
            b[i]=vec[k];
            i++;
        }
    }
    for(k=izquierda;k<=derecha;k++){
        vec[k]=b[k];
    }
}

void merge_sort(int* vec, int tam, int izquierda, int derecha){
    int medio;
    if(izquierda<derecha){
        medio=(izquierda+derecha)/2;
        merge_sort(vec, tam, izquierda, medio);
        merge_sort(vec, tam, medio+1, derecha);
        merge(vec, tam, izquierda, medio, derecha);
    }
}


int main(){
    //Cracion variables
    int vec[N];
    int cpy[N];
    int ord[N];
    double tot = 0;

    //Inicializacion
    for(int i = 0; i < N; i++){
        cpy[i] = ord[i] = vec[i] = i;
    }

    bool com = true;
    do{
        copia(ord, cpy, N);
        merge_sort(ord, N, 0, N-1);
        if(memcmp(vec, ord, N-1)){ com = false; }
    }while(std::next_permutation(cpy, cpy + N) && com);

    if(com == true){ std::cout << "exito" << std::endl; }
    else { std::cout << "error" << std::endl; }
}
