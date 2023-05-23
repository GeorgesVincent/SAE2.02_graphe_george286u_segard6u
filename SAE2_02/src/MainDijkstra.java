import java.io.IOException;

public class MainDijkstra {
    public static void main(String[] args) throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe_exemple1.txt");
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g,"A");
        System.out.println(v.toString());
        System.out.println(v.calculerChemin("C"));
    }
}
