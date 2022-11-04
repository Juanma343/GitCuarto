// ###### Config options ################

//#define PRINT_DEFENSE_STRATEGY 1    // generate map images

// #######################################

#define BUILDING_DEF_STRATEGY_LIB 1

#include "../simulador/Asedio.h"
#include "../simulador/Defense.h"
#include <cmath>
#include <algorithm>

#ifdef PRINT_DEFENSE_STRATEGY
#include "ppm.h"
#endif

#ifdef CUSTOM_RAND_GENERATOR
RAND_TYPE SimpleRandomGenerator::a;
#endif

using namespace Asedio;

//funcion encargada de calcular la distancia ente dos objetos dando de estos su posicon y su radio
float distObjeRad(Vector3 posicion1, float radio1, Vector3 posicion2, float radio2) {

    float dist = _distance(posicion1, posicion2) - radio1 + radio2;
    if (dist < 0) { // Caso en que los obstaculos chocan
        return 0; 
    }
    else{
        return dist;
    }
}

/* float distObje(Vector3 posicion1, Vector3 posicion2) {

    float dist = _distance(posicion1, posicion2);
    if (dist < 0) { // Caso en que los obstaculos chocan
        return 0; 
    }
    else{
        return dist;
    }
} */



void debase() {}

void detorretas() {}

int cellValueBase(int row, int col, bool** freeCells, int nCellsWidth, int nCellsHeight
	, float mapWidth, float mapHeight, List<Object*> obstacles, List<Defense*> defenses) {
    
    int RadioPiedras = 40;

    int valor, inter = std::min(ceil(nCellsWidth / 3), ceil(nCellsHeight / 3));
    int distCenWid = fabs(row - ceil(nCellsWidth / 2));
    int distCenHei = fabs(col - ceil(nCellsHeight / 2));
    valor = std::min(inter - distCenHei, inter - distCenWid);

    if (valor > 0){
        double inicio = 0, fin = 999999999, distancia;
        int maxva;
        for (auto it = obstacles.begin(); it != obstacles.end(); it++) {
            distancia = _distance((*it)->position, Vector3(row / nCellsWidth * mapWidth, col / nCellsHeight * mapHeight, 0));
            if (distancia <= RadioPiedras) {
                // if (distancia < inicio){
                //     inicio = distancia;
                // }
                // else if (distancia > fin) {
                //     fin = distancia;
                // }
                maxva += 5;
            }
        }
        valor += maxva - maxva * (fin - inicio) / fin;
    
    }
    return valor; // implemente aqui la funci�n que asigna valores a las celdas
}

int cellValueTorretas(int row, int col, bool** freeCells, int nCellsWidth, int nCellsHeight
	, float mapWidth, float mapHeight, List<Object*> obstacles, List<Defense*> defenses) {
    
    int valor, rad = 25; // radio de la base inicial en el que no deveria de haber nada
    int maxVal = std::min(ceil(nCellsWidth / 3), ceil(nCellsHeight / 3));
    double dif = 25 - _distance(defenses.front()->position, Vector3(row / nCellsWidth * mapWidth, col / nCellsHeight * mapHeight, 0));
    if (dif <= 25) {
        return 0;
    }
    else {
        return maxVal - floor(dif / (nCellsHeight / mapHeight));
    }

    return 0; // implemente aqui la funci�n que asigna valores a las celdas
}

bool factible(Object defensa, List<Object*> obstacles, List<Defense*> defenses) {
    for (auto it = obstacles.begin(); it != obstacles.end(); it++){
        if (distObjeRad(defensa.position, defensa.radio, (*it)->position, (*it)->radio) <= 0) {
            return false;
        }
    }
    return true;
    
}

//las posiciones de defensas pueden ser nulas??

bool otrasPos(Object defensa, List<Defense*> defenses) {
    for (auto it = defenses.begin(); it != defenses.end(); it++){
        if (distObjeRad(defensa.position, defensa.radio, (*it)->position, (*it)->radio) <= defensa.radio) {
            return false;
        }
    }
    return true;
}

void DEF_LIB_EXPORTED placeDefenses(bool** freeCells, int nCellsWidth, int nCellsHeight, float mapWidth, float mapHeight
              , std::list<Object*> obstacles, std::list<Defense*> defenses) {

    /* float cellWidth = mapWidth / nCellsWRadioPiedrasidth;
    float cellHeight = mapHeight / nCellsHeight; 

    int maxAttemps = 1000;
    List<Defense*>::iterator currentDefense = defenses.begin();
    while(currentDefense != defenses.end() && maxAttemps > 0) {

        (*currentDefense)->position.x = ((int)(_RAND2(nCellsWidth))) * cellWidth + cellWidth * 0.5f;
        (*currentDefense)->position.y = ((int)(_RAND2(nCellsHeight))) * cellHeight + cellHeight * 0.5f;
        (*currentDefense)->position.z = 0; 
        ++currentDefense;
    } */



#ifdef PRINT_DEFENSE_STRATEGY

    float** cellValues = new float* [nCellsHeight]; 
    for(int i = 0; i < nCellsHeight; ++i) {
       cellValues[i] = new float[nCellsWidth];
       for(int j = 0; j < nCellsWidth; ++j) {
           cellValues[i][j] = ((int)(cellValue(i, j))) % 256;
       }
    }
    dPrintMap("strategy.ppm", nCellsHeight, nCellsWidth, cellHeight, cellWidth, freeCells
                         , cellValues, std::list<Defense*>(), true);

    for(int i = 0; i < nCellsHeight ; ++i)
        delete [] cellValues[i];
	delete [] cellValues;
	cellValues = NULL;

#endif
}
