import java.io.IOException;
import java.util.List;

public class MainDijkstra {
    public static void main(String[] args) throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe1.txt");
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g,"1");
        System.out.println(v.toString());
        List<String> ls = v.calculerChemin("1");
        for (String s : ls){
            System.out.println(s);
        }
    }
}
