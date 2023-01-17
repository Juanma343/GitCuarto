#include <iostream>
#include <thread>
#include <mutex>
#include <math.h>

std::mutex m;

double aleatorio(){
    double num = rand() % 1000001;
    num /= 1000000;
    return num;
}

void montecarlo (int* vec, int nPuntos) {
    int dentro = 0;
    for (int i = 0; i < nPuntos; i++){
        if (aleatorio() < pow(aleatorio(), 2)){
            m.lock();
            (*vec)++;
            m.unlock();
        }
    }
}

int main (){
    int nPuntos, nTareas;
    srand(123456789);

    std::cout << "Numeros de puntos a lanzar para el metodomontecarlo: ";

    std::cin >> nPuntos;

    std::cout << std::endl << "Numeros de tareas paralelas: ";

    std::cin >> nTareas; std::cout << std::endl;

    int rango = nPuntos/nTareas;
    // int rango = 0;
    int* tot;
    *tot = 0;
    std::thread hilos[nTareas];

    for (int i = 0; i < nTareas - 1; i++){
        hilos[i] = std::thread(montecarlo, tot, rango);
    }
    hilos[nTareas - 1] = std::thread(montecarlo, tot, rango);
    for (int i = 0; i < nTareas; i++){
        hilos[i].join();
    }

    std::cout << "La intgral de x^2 es " << (double)*tot/nPuntos << std::endl;
    
    // std::cout << nTareas << "\t" << (double)*tot / nPuntos << std::endl;

}