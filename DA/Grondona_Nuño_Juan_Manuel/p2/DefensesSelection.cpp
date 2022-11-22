// ###### Config options ################


// #######################################

#define BUILDING_DEF_STRATEGY_LIB 1
               
#include "../simulador/Asedio.h"
#include "../simulador/Defense.h"

using namespace Asedio;

void DEF_LIB_EXPORTED selectDefenses(std::list<Defense*> defenses, unsigned int ases, std::list<int> &selectedIDs
            , float mapWidth, float mapHeight, std::list<Object*> obstacles) {

    unsigned int cost = 0;

    for (auto it1 = defenses.begin(); it1 != defenses.end(); it1++){
        std::cout << "Id = " << (*it1)->id << ", Tipo = " << (*it1)->type << " , Ataque = " << (*it1)->damage << " , ataques por segundo = " << (*it1)->attacksPerSecond << ", Rango = " << (*it1)->range << ", Salud = " << (*it1)->health << ", Rango = " << (*it1)->range << ", dispersion = " << (*it1)->dispersion << ", Coste = " << (*it1)->cost << std::endl;
    }

    std::list<Defense*>::iterator it = defenses.begin();

    while(it != defenses.end()) {
        if(cost + (*it)->cost <= ases) {
            selectedIDs.push_back((*it)->id);
            cost += (*it)->cost;
        }
        ++it;
    }
}
