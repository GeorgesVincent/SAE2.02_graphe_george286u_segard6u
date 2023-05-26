import Graphe.Dijkstra;
import Graphe.GrapheListe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Graphe.GrapheListe g = new Graphe.GrapheListe("Graphes/Graphe1.txt");
        //System.out.println(g.toGraphViz());
        //System.out.println(g.toString());
        //Graphe.BellmanFord b = new Graphe.BellmanFord();
        //Graphe.GrapheListe.fichierListeArc("Graphes/Matrice_adjacence.txt");
        //Graphe.Valeur v = b.resoudre(g,"1");
        //System.out.println(v.toString());
        //v.calculerChemin("1");
        GrapheListe g = new GrapheListe();
        g.genererGraphe("2", "6", 20);
        System.out.println(g.toGraphViz());
        Dijkstra d = new Dijkstra();
        System.out.println(d.resoudre(g, "2").toString());
    }
}
