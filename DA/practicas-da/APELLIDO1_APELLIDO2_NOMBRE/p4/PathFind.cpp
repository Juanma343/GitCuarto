// ###### Config options ################

#define PRINT_PATHS 1

// #######################################

#define BUILDING_DEF_STRATEGY_LIB 1
               
#include "../simulador/Asedio.h"
#include "../simulador/Defense.h"

#ifdef PRINT_PATHS
#include "ppm.h"
#endif

using namespace Asedio;

Vector3 cellCenterToPosition(int i, int j, float cellWidth, float cellHeight){ 
    return Vector3((j * cellWidth) + cellWidth * 0.5f, (i * cellHeight) + cellHeight * 0.5f, 0); 
}

bool pertenece (std::list<AStarNode*> lista, AStarNode* nodo){
    for(auto i = lista.begin(); i != lista.end(); ++i){
        if((*i) == nodo){
            return true;
        }
    }
    return false;
}

void DEF_LIB_EXPORTED calculateAdditionalCost(float** additionalCost
                   , int cellsWidth, int cellsHeight, float mapWidth, float mapHeight
                   , List<Object*> obstacles, List<Defense*> defenses) {

    float cellWidth = mapWidth / cellsWidth;
    float cellHeight = mapHeight / cellsHeight;

    for(int i = 0 ; i < cellsHeight ; ++i) {
        for(int j = 0 ; j < cellsWidth ; ++j) {
            Vector3 cellPosition = cellCenterToPosition(i, j, cellWidth, cellHeight);
            float cost = 0;
            if( (i+j) % 2 == 0 ) {
                cost = cellWidth * 100;
            }
            
            additionalCost[i][j] = cost;
        }
    }
}

void DEF_LIB_EXPORTED calculatePath(AStarNode* originNode, AStarNode* targetNode
                   , int cellsWidth, int cellsHeight, float mapWidth, float mapHeight
                   , float** additionalCost, std::list<Vector3> &path) {

    AStarNode* current = originNode;
    std::list<AStarNode*> abiertos, cerrados;
    bool encontrado = false;

    current->G = _sdistance(current->position, targetNode->position);
    current->H = additionalCost[(int)(current->position.y / cellsHeight)][(int)(current->position.x / cellsHeight)];
    current->F = current->G + current->H;

    abiertos.push_back(current);

    std::list<AStarNode*> aux;

    while(!encontrado && abiertos.empty()){
        current = abiertos.front();
        abiertos.pop_front();
        cerrados.push_back(current);

        if(current == targetNode){
            encontrado = true;
        }else{
            for(auto i = current->adjacents.begin(); i != current->adjacents.end(); ++i){
                int g = current->G + _sdistance((*i)->position, targetNode->position);
                int h = additionalCost[(int)((*i)->position.y / cellsHeight)][(int)((*i)->position.x / cellsHeight)];
                int f = g + h;

                int c = current->G + _sdistance(current->position, (*i)->position);

                if((pertenece(cerrados, (*i)) || pertenece(abiertos, (*i))) && (c < (*i)->G)){
                    cerrados.remove((*i));
                    abiertos.remove((*i));
                }
                if (!pertenece(cerrados, (*i)) && !pertenece(abiertos, (*i))){
                    (*i)->G = g;
                    (*i)->H = h;
                    (*i)->F = f;
                    (*i)->parent = current;
                    aux.push_back((*i));
                }
            }
            aux.sort([](AStarNode* a, AStarNode* b){return a->F < b->F;});
            for(auto i = aux.begin(); i != aux.end(); ++i){
                abiertos.push_back((*i));
            }

        }
    }
    
    

}
