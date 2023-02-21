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
            float media = 0;
            float max = 0;
            for (auto it = obstacles.begin(); it != obstacles.end(); ++it) {
                if (_sdistance((*it)->position, cellPosition) < (*it)->radio) {
                    cost -=10 * _sdistance((*it)->position, (*obstacles.begin())->position);
                }
                media += _sdistance((*it)->position, cellPosition);
                if (_sdistance((*it)->position, cellPosition) > max) {
                    max = _sdistance((*it)->position, cellPosition);
                }
            }
            cost += max - (media / obstacles.size());
            
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

    current->G = 0;
    current->H = additionalCost[(int)(current->position.y / cellsHeight)][(int)(current->position.x / cellsHeight)];
    current->F = current->G + current->H;
    current->parent = nullptr;

    abiertos.push_back(current);

    while(!encontrado && !abiertos.empty()){
        current = abiertos.front();
        abiertos.pop_front();
        cerrados.push_back(current);

        if(current == targetNode){
            encontrado = true;
        }else{
            for(auto i = current->adjacents.begin(); i != current->adjacents.end(); ++i){
                int g = current->G + _sdistance((*i)->position, (*i)->position);
                int h = additionalCost[(int)((*i)->position.y / cellsHeight)][(int)((*i)->position.x / cellsHeight)];
                int f = g + h;

                if((pertenece(cerrados, (*i)) || pertenece(abiertos, (*i))) && (g < (*i)->G)){
                    if(pertenece(cerrados, (*i))){
                        cerrados.remove((*i));
                    }
                    if(pertenece(abiertos, (*i))){
                        abiertos.remove((*i));
                    }
                }
                if (!pertenece(cerrados, (*i)) && !pertenece(abiertos, (*i))){
                    (*i)->G = g;
                    (*i)->H = h;
                    (*i)->F = f;
                    (*i)->parent = current;
                    abiertos.push_back((*i));
                }
            }
            abiertos.sort([](AStarNode* a, AStarNode* b){return a->F < b->F;});
        
        }

    }

    AStarNode* auxNode = targetNode;
    int cont = 0;
    while(auxNode->parent != nullptr){
        path.push_front(auxNode->position);
        auxNode = auxNode->parent;
    }

}
