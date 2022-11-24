// ###### Config options ################


// #######################################

#define BUILDING_DEF_STRATEGY_LIB 1
               
#include "../simulador/Asedio.h"
#include "../simulador/Defense.h"
#include <cstring>

using namespace Asedio;

void valorar (std::list<Defense*> Defensas, int* p, int* id, float* v){
    
    int Vdaño       = 3;
    int Vrango      = 6;
    int Vdispersion = 6;
    int Vsalud      = 1;

    float* aux = new float[Defensas.size()];
    int i = 1;
    for (auto it1 = ++Defensas.begin(); it1 != Defensas.end(); it1++){
        double dañoporCoste = (*it1)->attacksPerSecond * (*it1)->damage / (*it1)->cost;
        double rangoporCoste = (*it1)->range / (*it1)->cost;
        double dispersionporCoste = (*it1)->dispersion / (*it1)->cost;
        double saludporCoste = (*it1)->health / (*it1)->cost;

        aux[i++] = Vdaño * dañoporCoste + Vrango * rangoporCoste + Vdispersion * dispersionporCoste + Vsalud * saludporCoste;
    }

    //ordenar lista

    int k = i, max = 0, imax = 0;
    std::list<Defense*> lis2;
    auto it = Defensas.begin();
    for(int k = 1; k <= i; k++){
        it = ++Defensas.begin();
        max = 0;
        imax = 0;
        for (int j = 1; j < i; j++){
            
            if (aux[j] > max){
                max = aux[j];
                imax = j;
            }
        }
        v[k] = aux[imax];
        aux[imax] = 0;
        while(imax > 1){
            imax--; it++;
        }
        p[k] = (*it)->cost;
        id[k] = (*it)->id;
    }

}

void DEF_LIB_EXPORTED selectDefenses(std::list<Defense*> defenses, unsigned int ases, std::list<int> &selectedIDs
            , float mapWidth, float mapHeight, std::list<Object*> obstacles) {

    int cost = ases+1;
    float valor[defenses.size()];
    int coste[defenses.size()];
    int id[defenses.size()];

    //meter primer torreta

    selectedIDs.push_back((*defenses.begin())->id);
    cost -= (*defenses.begin())->cost;

    //dar valor

    valorar(defenses, coste, id, valor);
    
    //matriz dinamica
    float** tabla = new float* [defenses.size()]; 
    for(int i = 1; i < defenses.size(); ++i) {
       tabla[i] = new float[ases+1];
    }

    // std::cout << coste[3] << "--" << id[3] << "--" << valor[3] << std::endl;

    //algoritmo mochila
    for (int j = 1; j <= cost; j++){
        if (j < coste[1]){
            tabla[1][j] = 0;
        }
        else{
            tabla[1][j] = valor[1];
        }
    }
    for (int i = 2; i < defenses.size(); i++){
        for (int j = 1; j <= cost; j++){
            if (j < coste[i]){
                tabla[i][j] = tabla[i-1][j];
            }
            else{
                tabla[i][j] = std::max(tabla[i-1][j], tabla[i-1][j-coste[i]] + valor[i]);
            }
            // std::cout << tabla[i][j] << "--" << std::ends;
        }
        // std::cout << std::endl;

    }
            // std::cout << tabla[20][cost] << "--" << std::endl;

    
    //recuperar
    


    int i = defenses.size()-1;
    while(i > 1){
        // tabla[i][cost];
    // std::cout << tabla[i][cost] << "--" << tabla[i-1][cost] << std::endl;
        if ((tabla[i][cost] != tabla[i-1][cost])){

            cost -= coste[i];
            selectedIDs.push_back(id[i]);
        }
        i--;
    }

    //cerrar matriz
    for(int i = 1; i < defenses.size() ; ++i)
        delete [] tabla[i];
	delete [] tabla;
	tabla = NULL;
}
