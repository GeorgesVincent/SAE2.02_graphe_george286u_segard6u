import Graphe.Dijkstra;
import Graphe.Graphe;
import Graphe.GrapheLabyrinthe;
import Graphe.Valeur;
import laby.Labyrinthe;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby.txt");
        Graphe g = new GrapheLabyrinthe(l);
        Valeur v = new Dijkstra().resoudre(g, "2,0");
        System.out.println(v.toString());
    }
}
