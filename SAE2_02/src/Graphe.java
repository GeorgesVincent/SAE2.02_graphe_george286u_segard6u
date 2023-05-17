import java.util.ArrayList;

public interface Graphe {

    //retourne tous les nœuds du graphe (sous la forme de String - c’est a dire leur nom)
    public ArrayList<String> listeNoeuds();

    //retourne la liste des arcs partant du noeud n passe en parametre
    public ArrayList<Arc> suivants(String n);
}
