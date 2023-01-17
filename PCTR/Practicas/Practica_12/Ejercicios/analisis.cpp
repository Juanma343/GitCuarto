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
            dentro++;
        }
    }
    m.lock();
    (vec) += dentro;
    m.unlock();
}

int main (){
    
    int nPuntos = 11111111, nTareas = 0;
    std::chrono::time_point<std::chrono::system_clock> start, end;
    std::chrono::duration<double> tSecuencial;
    std::chrono::duration<double> elapsed_seconds;
    srand(123456789);

    
    for (nTareas = 1; nTareas < 16; nTareas++){ 
        int rango = nPuntos/nTareas;
        int* tot = new int;
        *(tot) = 0;	
        std::thread hilos[nTareas];
            // std::cout << "hola" << std::endl;

        start = std::chrono::system_clock::now();
        for (int i = 0; i < nTareas; i++){
            hilos[i] = std::thread(montecarlo, tot, rango);
        }
        
        // hilos[nTareas - 1] = std::thread(montecarlo, tot, rango, nTareas - 1);
        for (int i = 0; i < nTareas; i++){
            hilos[i].join();
        }
        end = std::chrono::system_clock::now();
        
        if (nTareas == 1){
            tSecuencial = end-start;
        }
        else{
            elapsed_seconds = end-start;
            std::cout << nTareas << "\t" << tSecuencial.count()/elapsed_seconds.count() << std::endl;
        }
        
    }

}