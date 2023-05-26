import Graphe.BellmanFord;
import Graphe.Dijkstra;
import Graphe.GrapheListe;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ComparerGrapheGenerer {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("ComparerGrapheGenerer.txt"));
        pw.write("Nom du Graphe.Graphe    \tTemps_BellmanFord\tTemps_Dijkstra    \tMoy d'Arcs/Graphe.Noeud \tRatio\n");
        double ratio_moy = 0;
        long tempsB_moy = 0;
        long tempsD_moy = 0;
        double taille_graphe_moy = 0;
        for (int taille = 3; taille <= 1003; taille++) {
            GrapheListe g = new GrapheListe();
            g.genererGraphe("0", String.valueOf(taille - 1), taille);
            BellmanFord b = new BellmanFord();
            Dijkstra d = new Dijkstra();
            long temps = System.nanoTime();
            b.resoudre(g, g.listeNoeuds().get(0));
            temps = System.nanoTime() - temps;
            pw.printf("%-17s\t%-17d\t", "Graphe" + (taille - 2), temps);
            double moy = (double) g.getNBArc() / g.listeNoeuds().size();
            double ratio = temps;
            taille_graphe_moy += moy;
            tempsB_moy += temps;
            temps = System.nanoTime();
            d.resoudre(g, g.listeNoeuds().get(0));
            temps = System.nanoTime() - temps;
            tempsD_moy += temps;
            ratio = (ratio - temps) / moy;
            ratio_moy += ratio;
            pw.printf("%-17d\t%-17.1f\t%-17.4f%n", temps, moy, ratio);
        }
        pw.printf("%-17s\t%-17d\t%-17d\t%-17.1f\t%-17.4f", "Moy_Final", tempsB_moy / 1000, tempsD_moy / 1000, taille_graphe_moy / 1000, ratio_moy / 1000);
        pw.close();
    }
}
