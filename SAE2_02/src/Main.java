import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe1.txt");
        System.out.println(g.toGraphViz());
        System.out.println(g.toString());
    }
}
