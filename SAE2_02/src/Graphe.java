import java.util.List;

public interface Graphe {

    //retourne tous les nœuds du graphe (sous la forme de String - c’est a dire leur nom)
    public List<String> listeNoeuds();

    //retourne la liste des arcs partant du noeud n passe en parametre
    public List<Arc> suivants(String n);
}
