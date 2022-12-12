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

void quicksort(int* vec,int pri,int ult)
{
    int med,i,j;
    double pivote;
    med=(pri+ult)/2;
    pivote=vec[med];
    i=pri;
    j=ult;
    do{   
        while(vec[i]<pivote) { i++; }
        while(vec[j]>pivote) { j--; }
        if(i<=j){
            int aux = vec[i];
            vec[i]=vec[j];
            vec[j]=aux;
            i++;
            j--;
        }
    } while(i<=j);
    if(pri<j)
        quicksort(vec,pri,j); /*mismo proceso con sublista izquierda*/
    if(i<ult)
        quicksort(vec,i,ult); /*mismo proceso con sublista derecha*/
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
        if(memcmp(vec, ord, N-1)){ com = false; }
    }while(std::next_permutation(cpy, cpy + N) && com);

    if(com == true){ std::cout << "exito" << std::endl; }
    else { std::cout << "error" << std::endl; }
}