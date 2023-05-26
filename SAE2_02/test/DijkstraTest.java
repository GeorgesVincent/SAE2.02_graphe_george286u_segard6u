import Graphe.Dijkstra;
import Graphe.GrapheListe;
import Graphe.Valeur;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void resoudre() throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe_exemple1.txt");
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g,"A");
        Valeur test = new Valeur();
        test.setValeur("A",0);
        test.setValeur("B",12);
        test.setValeur("C",76);
        test.setValeur("D",66);
        test.setValeur("E",23);
        test.setParent("A",null);
        test.setParent("B","A");
        test.setParent("C","D");
        test.setParent("D","E");
        test.setParent("E","B");
        assertEquals(test.toString(),v.toString());
    }
}