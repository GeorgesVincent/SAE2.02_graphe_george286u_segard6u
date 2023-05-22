import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A","B",12);
        g.ajouterArc("A","D",87);
        g.ajouterArc("B","E",11);
        g.ajouterArc("E","D",43);
        g.ajouterArc("D","B",23);
        g.ajouterArc("D","C",10);
        g.ajouterArc("C","A",19);
<<<<<<< HEAD
        System.out.println(g.toGraphViz());
=======
        System.out.println(g.toString());
>>>>>>> f422a5bef8c32079b3a972ee71a05fa4e8f097e6
    }
}
