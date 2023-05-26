package Graphe;

import laby.Labyrinthe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLabyrinthe implements Graphe{

    private GrapheListe g;

    @Override
    public List<String> listeNoeuds() {
        return g.listeNoeuds();
    }
    @Override
    public List<Noeud> getEnsNoeuds(){
        return g.getEnsNoeuds();
    }

    @Override
    public List<Arc> suivants(String n) {
        return g.suivants(n);
    }
    public GrapheLabyrinthe(Labyrinthe l){
        GrapheListe g = new GrapheListe();
        ArrayList<String> actions = new ArrayList<>();
        for (int y = 0; y < l.getLengthY(); y++) {
            for (int x = 0; x < l.getLength(); x++) {
                if (!l.murs[x][y]) {
                    ArrayList<int[]> chemins = new ArrayList<>();
                    StringBuilder s1 = null;
                    if (x > 0) {
                        actions.add(Labyrinthe.GAUCHE);
                    }
                    if (x < l.getLength() - 1) {
                        actions.add(Labyrinthe.DROITE);
                    }
                    if (y > 0) {
                        actions.add(Labyrinthe.HAUT);
                    }
                    if (y < l.getLengthY() - 1) {
                        actions.add(Labyrinthe.BAS);
                    }
                    for (String s : actions) {
                        chemins.add(Labyrinthe.getSuivant(x,y,s));
                    }
                    if(chemins.size()>0){
                        s1 = new StringBuilder();
                        s1.append(x).append(",").append(y);
                    }
                    for (int[] i : chemins){
                        if(!l.murs[i[0]][i[1]]) {
                            StringBuilder s2 = new StringBuilder();
                            s2.append(i[0]).append(",").append(i[1]);
                            g.ajouterArc(s1.toString(), s2.toString(), 1);
                            g.ajouterArc(s2.toString(), s1.toString(), 1);
                        }
                    }
                }
            }
        }
        this.g=g;
    }
}
