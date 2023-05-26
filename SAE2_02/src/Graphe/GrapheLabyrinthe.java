package Graphe;

import laby.Labyrinthe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLabyrinthe implements Graphe{
    @Override
    public List<String> listeNoeuds() {
        return null;
    }

    @Override
    public List<Arc> suivants(String n) {
        return null;
    }
    public GrapheLabyrinthe(Labyrinthe l){
        ArrayList<String> actions = new ArrayList<>();
        for (int y = 0; y < l.getLengthY(); y++) {
            for (int x = 0; x < l.getLength(); x++) {
                if (!l.murs[x][y]) {
                    ArrayList<int[]> chemins = new ArrayList<>();
                    StringBuilder s1 = null;
                    if (x > 0) {
                        actions.add(Labyrinthe.GAUCHE);
                    }
                    if (x < getLength() - 1) {
                        actions.add(Labyrinthe.GAUCHE);
                    }
                    if (y > 0) {
                        actions.add(HAUT);
                    }
                    if (y < getLengthY() - 1) {
                        actions.add(BAS);
                    }
                    for (String s : actions) {
                        chemins.add(getSuivant(x,y,s));
                    }
                    if(chemins.size()>0){
                        s1 = new StringBuilder();
                        s1.append(x).append(",").append(y);
                    }
                    for (int[] i : chemins){
                        if(!murs[i[0]][i[1]]) {
                            StringBuilder s2 = new StringBuilder();
                            s2.append(i[0]).append(",").append(i[1]);
                            g.ajouterArc(s1.toString(), s2.toString(), 1);
                            g.ajouterArc(s2.toString(), s1.toString(), 1);
                        }
                    }
                }
            }
        }
    }
}
