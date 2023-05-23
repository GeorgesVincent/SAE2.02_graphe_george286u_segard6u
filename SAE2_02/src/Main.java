import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe1.txt");
        System.out.println(g.toGraphViz());
        System.out.println(g.toString());
        BellmanFord b = new BellmanFord();
        System.out.println(b.resoudre(g,"1").toString());
    }
}
