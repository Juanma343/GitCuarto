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

void merge(int* vec, int tam, int izquierda, int medio, int derecha){
    int h = izquierda ,i = izquierda ,j = medio + 1;
    int res[tam];
    
    while((h <= medio) && (j <= derecha)){
        if(vec[h] >= vec[j]){
            res[i] = vec[h];
            h++;
        }
        else{
            res[i] = vec[j];
            j++;
        }
        i++;
    }
    if(h > medio){
        for(int k = j; k <= derecha; k++){
            res[i] = vec[k];
            i++;
        }
    }
    else{
        for(int k = h; k<=medio; k++){
            res[i] = vec[k];
            i++;
        }
    }
    for(int k = izquierda; k<=derecha; k++){
        vec[k] = res[k];
    }
}

void merge_sort(int* vec, int tam, int izquierda, int derecha){
    if(izquierda>derecha){
        int medio=(izquierda+derecha)/2;
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
        cpy[i] = ord[i] = vec[i] = N - 1 - i;
        // std::cout << vec[i];
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
