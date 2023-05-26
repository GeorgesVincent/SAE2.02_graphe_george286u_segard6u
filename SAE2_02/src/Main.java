import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //GrapheListe g = new GrapheListe("Graphes/Graphe1.txt");
        //System.out.println(g.toGraphViz());
        //System.out.println(g.toString());
        //BellmanFord b = new BellmanFord();
        //GrapheListe.fichierListeArc("Graphes/Matrice_adjacence.txt");
        //Valeur v = b.resoudre(g,"1");
        //System.out.println(v.toString());
        //v.calculerChemin("1");
        GrapheListe g = new GrapheListe();
        g.genererGraphe("2","6",3);
        System.out.println(g.toString());
        System.out.println(g.toGraphViz());
        Dijkstra d = new Dijkstra();
        System.out.println(d.resoudre(g,"2").toString());
    }
}
