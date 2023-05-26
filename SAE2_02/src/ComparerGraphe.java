import Graphe.BellmanFord;
import Graphe.Dijkstra;
import Graphe.GrapheListe;

import java.io.*;
import java.util.regex.*;

public class ComparerGraphe {
    public static void main(String[] args) throws IOException {
        File dir = new File("Graphes");
        Pattern p = Pattern.compile("Graphe\\d");
        File[] graphes = dir.listFiles(pathname -> p.matcher(pathname.toString()).find());
        PrintWriter pw = new PrintWriter(new FileWriter("ComparerGraphe.txt"));
        pw.write("Nom du Graphe.Graphe    \tTemps_BellmanFord\tTemps_Dijkstra    \tMoy d'Arcs/Graphe.Noeud \tRatio\n");
        double ratio_moy =0;
        long tempsB_moy = 0;
        long tempsD_moy=0;
        double taille_graphe_moy = 0;
        if (graphes != null) {
            for (File f : graphes) {
                GrapheListe g = new GrapheListe(f.getPath());
                BellmanFord b = new BellmanFord();
                Dijkstra d = new Dijkstra();
                long temps = System.nanoTime();
                b.resoudre(g, g.listeNoeuds().get(0));
                temps = System.nanoTime() - temps;
                pw.printf("%-17s\t%-17d\t",f.getName(),temps);
                double moy = (double) g.getNBArc() /g.listeNoeuds().size();
                double ratio = temps;
                taille_graphe_moy += moy;
                tempsB_moy += temps;
                temps = System.nanoTime();
                d.resoudre(g, g.listeNoeuds().get(0));
                temps = System.nanoTime() - temps;
                tempsD_moy += temps;
                ratio = (ratio-temps)/moy;
                ratio_moy+=ratio;
                pw.printf("%-17d\t%-17.1f\t%-17.4f%n",temps,moy,ratio);
            }
            pw.printf("%-17s\t%-17d\t%-17d\t%-17.1f\t%-17.4f","Moy_Final",tempsB_moy/graphes.length,tempsD_moy/graphes.length,taille_graphe_moy/graphes.length,ratio_moy/graphes.length);
        }
        pw.close();
    }
}
