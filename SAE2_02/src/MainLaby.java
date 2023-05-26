import Graphe.GrapheListe;
import laby.Labyrinthe;
import Graphe.GrapheListe;
import Graphe.Dijkstra;
import Graphe.Valeur;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby.txt");
        GrapheListe g = l.genererGraphe();
        Valeur v = new Dijkstra().resoudre(g,"2,0");
        System.out.println(v.toString());
    }
}
