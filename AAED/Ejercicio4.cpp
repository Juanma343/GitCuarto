#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>

void main(){

    int tam = 10000000;
    double vec[tam];

    srand(time(NULL));

    for (int i = 0; i < tam; i++){
        vec[i] = (rand() % 101) / (double)100;
    }

    int sol;

    for (int i = 0; i < tam; i++){
        sol += 4 * sqrt(1 - pow(vec[i], 2));
    }

    std::cout << sol/tam << std::endl;

}