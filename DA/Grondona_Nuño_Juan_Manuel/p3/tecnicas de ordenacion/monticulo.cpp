#include <iostream>
#include <algorithm>
#include <cstring>
#include "../cronometro.h"
#define N 10

void copia(int* des, int* ori, int n){
    for (int i = 0; i < n; i++){
        des[i] = ori[i];
    }
}

void monticulo(int* pri, int* fin){
    std::make_heap(pri, fin);
    std::sort_heap(pri, fin);
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
        std::sort_heap(ord, ord + N);
        if(memcmp(vec, ord, N-1)){ com = false; }
    }while(std::next_permutation(cpy, cpy + N) && com);

    if(com == true){ std::cout << "exito" << std::endl; }
    else { std::cout << "error" << std::endl; }
}