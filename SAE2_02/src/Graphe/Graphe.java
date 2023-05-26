package Graphe;

import java.util.List;

public interface Graphe {

    //retourne tous les nœuds du graphe (sous la forme de String - c’est a dire leur nom)
    List<String> listeNoeuds();

    //retourne la liste des arcs partant du noeud n passe en parametre
    List<Arc> suivants(String n);
}
