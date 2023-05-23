import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    @Test
    void resoudre() throws IOException {
        GrapheListe g = new GrapheListe("Graphes/Graphe5.txt");
        Valeur v = new BellmanFord().resoudre(g,"1");
        Valeur test = new Valeur();
        test.setParent("1","3");
        test.setValeur("1",0);
        test.setValeur("2",6);
        test.setValeur("3",12);
        assertEquals(v.toString(),test.toString());
    }
}