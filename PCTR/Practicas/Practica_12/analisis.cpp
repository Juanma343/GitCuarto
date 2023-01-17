#include <iostream>
#include <thread>
#include <mutex>
#include <math.h>
#include <ctime>
#include <chrono>

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
    int nPuntos = 11111111, nTareas = 1;
    // std::chrono::time_point<std::chrono::system_clock> start, end;
    srand(123456789);

    // std::chrono::duration<double> tSecuencial;
    // std::chrono::duration<double> elapsed_seconds;


    for (nTareas = 1; nTareas < 16; nTareas++){
        int rango = nPuntos/nTareas;
        std::thread hilos[nTareas];
        int* tot;
        tot = 0;

        // start = std::chrono::system_clock::now();
        for (int i = 0; i < nTareas; i++){
            hilos[i] = std::thread(montecarlo, tot, rango);
        }
        for (int i = 0; i < nTareas; i++){
            hilos[i].join();
        }
        // end = std::chrono::system_clock::now();
        // if (nTareas = 1){
        //     tSecuencial = end-start;
        // }
        // else{
        //     elapsed_seconds = end-start;
        // }

        // std::cout << nTareas << "\t" << elapsed_seconds.count()/tSecuencial.count() << std::endl;
    }

}