// ###### Config options ################

//#define PRINT_DEFENSE_STRATEGY 1    // generate map images

// #######################################

#define BUILDING_DEF_STRATEGY_LIB 1

#include "../simulador/Asedio.h"
#include "../simulador/Defense.h"
#include <cmath>
#include <algorithm>
#include <list>
#include <iostream>

#ifdef PRINT_DEFENSE_STRATEGY
#include "ppm.h"
#endif

#ifdef CUSTOM_RAND_GENERATOR
RAND_TYPE SimpleRandomGenerator::a;
#endif

using namespace Asedio;

//funcion encargada de calcular la distancia ente dos objetos dando de estos su posicon y su radio
float distObjeRad(Vector3 posicion1, float radio1, Vector3 posicion2, float radio2) {

    float dist = _distance(posicion1, posicion2) - (radio1 + radio2);
    if (dist < 0) { // Caso en que los obstaculos chocan
        return 0; 
    }
    else{
        return dist;
    }
}

struct celda
    {
        int x_;
        int y_;
        int valor;
        Vector3 posicion;

        celda(int x, int y, int val, double cellW, double cellH): x_(x), y_(y), valor(val){
            posicion = Vector3(x * cellW + cellW * 0.5f, y * cellH + cellH * 0.5f, 0);
        }
    };


int cellValueBase(int row, int col, int nCellsWidth, int nCellsHeight
	, float mapWidth, float mapHeight, List<Object*> obstacles, List<Defense*> defenses) {
    
    int RadioPiedras = 13;
    double densidad = 0;

    int valor, inter = std::min(ceil(nCellsWidth / 3), ceil(nCellsHeight / 3));
    int distCenWid = fabs(row - ceil(nCellsWidth / 2));
    int distCenHei = fabs(col - ceil(nCellsHeight / 2));
    valor = std::min(inter - distCenHei, inter - distCenWid);

    if (valor > 0 ){
        double distancia, media = 0;
        int maxva, aux = 0;
        for (auto it = obstacles.begin(); it != obstacles.end(); it++) {
            distancia = _distance((*it)->position, Vector3((double)row / nCellsWidth * mapWidth, (double)col / nCellsHeight * mapHeight, 0));
            if (distancia <= RadioPiedras) {
                media += distancia;
                aux++;
            }
        }
        valor += 5 * media / aux;
    
    }
    return valor;
}

bool otrasPos(Vector3 pos, List<Defense*> defenses) {
    for (auto it = defenses.begin(); it != defenses.end(); it++){
        if (_distance(pos, (*it)->position) <= (*it)->radio) {
            return true;
        }
    }
    return false;
}

int cellValueTorretas(int row, int col, bool** freeCells, int nCellsWidth, int nCellsHeight
	, float mapWidth, float mapHeight, List<Object*> obstacles, List<Defense*> defenses) {
    
    int valor, rad = 15; // radio de la base inicial en el que no deveria de haber nada
    int maxVal = std::min(ceil(nCellsWidth / 3), ceil(nCellsHeight / 3));
    int distCenWid = fabs(row - defenses.front()->position.x / (mapWidth / nCellsWidth) );
    int distCenHei = fabs(col - defenses.front()->position.y / (mapHeight / nCellsHeight) );
    valor = std::min(maxVal - distCenHei, maxVal - distCenWid);
    return valor;
}

bool factible(Object* defensa, celda *cell, float mapWidth, float mapHeight, List<Object*> obstacles, List<Defense*> defenses) {
    bool res = true;
    if ((defensa->radio >= cell->posicion.x || cell->posicion.x >= mapWidth - defensa->radio) || (defensa->radio >= cell->posicion.y || cell->posicion.y >= mapHeight - defensa->radio) ) {
        res = false;
    }
    for (auto it = obstacles.begin(); it != obstacles.end() && res; it++){
        if (distObjeRad(cell->posicion, defensa->radio, (*it)->position, (*it)->radio) <= 5) {
            res = false;
        }
    }
    for (auto it = defenses.begin(); it != defenses.end() && res; it++){
        if (distObjeRad(cell->posicion, defensa->radio, (*it)->position, (*it)->radio) <= 0) {
            
            res = false;
        }
        
    }
    return res;   
}

void DEF_LIB_EXPORTED placeDefenses(bool** freeCells, int nCellsWidth, int nCellsHeight, float mapWidth, float mapHeight
              , std::list<Object*> obstacles, std::list<Defense*> defenses) {

    float cellWidth = mapWidth / nCellsWidth;
    float cellHeight = mapHeight / nCellsHeight; 
    
    std::list<celda*> valoresbase;
    for(int i = 0; i < nCellsHeight; ++i) {
       for(int j = 0; j < nCellsWidth; ++j) {
           valoresbase.push_back(new celda(i, j, cellValueBase(i, j, nCellsWidth, nCellsHeight, mapWidth, mapHeight, obstacles, defenses), cellWidth, cellHeight));
       }
    }
    valoresbase.sort([](celda* a, celda* b) -> bool{return a->valor > b->valor;});

    std::list<Defense*> posicionadas;
    auto defAct = defenses.begin();
    auto cellAct = valoresbase.begin();
    bool fin = true;
    while (cellAct != valoresbase.end() && fin){
        if (factible(*defAct, (*cellAct), mapWidth, mapHeight, obstacles, defenses)){
            (*defAct)->position = (*cellAct)->posicion;
            fin = false;
            defAct++;
        }
        else{
            cellAct++;
        }
    }

    std::list<celda*> valorestorre;
    for(int i = 0; i < nCellsHeight; ++i) {
       for(int j = 0; j < nCellsWidth; ++j) {
           valorestorre.push_back(new celda(i, j, cellValueTorretas(i, j, freeCells, nCellsWidth, nCellsHeight, mapWidth, mapHeight, obstacles, defenses), cellWidth, cellHeight));
       }
    }
    valorestorre.sort([](celda* a, celda* b) -> bool{return a->valor > b->valor;});

    auto cellAct1 = valorestorre.begin();
    bool fin1 = true;
    while (defAct != defenses.end()){
        while (cellAct1 != valorestorre.end() && fin1){
            if (factible(*defAct, (*cellAct1), mapWidth, mapHeight, obstacles, defenses)){
                (*defAct)->position = (*cellAct1)->posicion;
                fin1 = false;
                valorestorre.erase(cellAct1);
                cellAct1 = valorestorre.begin();
            }
            else{
                cellAct1++;
            }
        }
        fin1 = true;
        defAct++;
    }
}


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

