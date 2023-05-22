import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrapheListeTest {

    @Test
    void ajouterArc_1Noeud() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A","B",12);
       assertEquals(g.listeNoeuds().size(),2,"le nombre de noeud doit être 2");
       assertEquals(g.listeNoeuds().get(0),"A");
        assertEquals(g.listeNoeuds().get(1),"B");
    }
    @Test
    void ajouterArc_2Noeud() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A","B",12);
        g.ajouterArc("B","E",11);
        assertEquals(g.listeNoeuds().size(),3,"le nombre de noeud doit être 2");
        assertEquals(g.listeNoeuds().get(0),"A");
        assertEquals(g.listeNoeuds().get(1),"B");
        assertEquals(g.listeNoeuds().get(2),"E");
    }
    @Test
    void ajouterArc_2NoeudIdentiques() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A","B",12);
        g.ajouterArc("A","B",12);
        assertEquals(g.listeNoeuds().size(),2,"le nombre de noeud doit être 2");
        assertEquals(g.listeNoeuds().get(0),"A");
        assertEquals(g.listeNoeuds().get(1),"B");
    }

    @Test
    void suivants() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A","B",12);
        g.ajouterArc("A","D",87);
        g.ajouterArc("B","E",11);
        g.ajouterArc("E","D",43);
        g.ajouterArc("D","B",23);
        g.ajouterArc("D","C",10);
        g.ajouterArc("C","A",19);
        assertEquals(g.suivants("E").get(0).getDest(),"D");
    }
}