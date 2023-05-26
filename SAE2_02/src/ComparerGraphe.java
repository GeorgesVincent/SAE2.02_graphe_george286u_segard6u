import Graphe.BellmanFord;
import Graphe.Dijkstra;
import Graphe.GrapheListe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class ComparerGraphe {
    public static void main(String[] args) throws IOException {
        File dir = new File("Graphes");
        //permet de définir un format de string
        Pattern p = Pattern.compile("Graphe\\d");
        //retourne le tableau de fichier contenu dans le dossier dir qui sont de la forme Graphei.txt, donc ceux fournis par arche
        //i étant un nombre quelconque
        File[] graphes = dir.listFiles(pathname -> p.matcher(pathname.toString()).find());
        //ouvre un flux permettant d'écrire dans un fichier
        PrintWriter pw = new PrintWriter(new FileWriter("ComparerGraphe.txt"));
        if (graphes != null) {
            //écris le nom des colonnes en première ligne du fichiers
            pw.write("Nom du Graphe.Graphe    \tTemps_BellmanFord\tTemps_Dijkstra    \tMoy d'Arcs/Graphe.Noeud \tRatio\n");
            double ratio_moy = 0;
            long tempsB_moy = 0;
            long tempsD_moy = 0;
            double taille_graphe_moy = 0;
            for (File f : graphes) {
                GrapheListe g = new GrapheListe(f.getPath());
                BellmanFord b = new BellmanFord();
                Dijkstra d = new Dijkstra();
                long temps = System.nanoTime();
                b.resoudre(g, g.listeNoeuds().get(0));
                temps = System.nanoTime() - temps;
                pw.printf("%-17s\t%-17d\t", f.getName(), temps);
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
            pw.printf("%-17s\t%-17d\t%-17d\t%-17.1f\t%-17.4f", "Moy_Final", tempsB_moy / graphes.length, tempsD_moy / graphes.length, taille_graphe_moy / graphes.length, ratio_moy / graphes.length);
        }
        pw.close();
    }
}
