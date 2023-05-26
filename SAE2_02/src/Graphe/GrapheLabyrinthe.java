package Graphe;

import laby.Labyrinthe;

import java.util.ArrayList;
import java.util.List;

/**
 * class servent d'apdateur pour la classe Labyrinthe
 */
public class GrapheLabyrinthe implements Graphe {

    /**
     * attribut permettant de relié le Labyrinthe à Graphe tout en permettant l'utilisation des méthodes de g ce qui évite de devoir modifier du code à droite et à gauche
     */
    private GrapheListe g;

    /**
     * méthode qui renvoie les noms des noueds présents dans le graphe
     * @return la liste de Noeud de l'attibut g
     */
    @Override
    public List<String> listeNoeuds() throws NullPointerException {
        return g.listeNoeuds();
    }

    /**
     * méthode qui renvoie les chemins vers les enfants du noeud désiré
     * @param n nom du Noeud pour lequel on souhaite connaître les enfants
     * @return une Liste de chemins menant aux enfant du Noeud
     * @throws NullPointerException
     */
    @Override
    public List<Arc> suivants(String n) throws NullPointerException {
        return g.suivants(n);
    }

    public GrapheLabyrinthe(Labyrinthe l) {
        //initialisation de l'attribut g
        GrapheListe g = new GrapheListe();
        //paucours du Labyrinthe
        for (int y = 0; y < l.getLengthY(); y++) {
            for (int x = 0; x < l.getLength(); x++) {
                //vérifie que la position actuelle n'est pas un mur
                if (!l.murs[x][y]) {
                    //Liste qui est destiné à contenir les actions réalisables à la position actuelle du parcours dans le labyrinthe
                    ArrayList<String> actions = new ArrayList<>();
                    //Liste destiné à contenir les position où l'on peut se déplacer depuis la position actuelle du parcours dans le labyrinthe
                    ArrayList<int[]> chemins = new ArrayList<>();
                    //initialisation d'un StringBuilder à null qui contiendra le nom du Noeud à la position actuelle du parcours dans le labyrinthe
                    StringBuilder s1 = null;
                    //4 conditions pour ajouter les actions si elles sont à l'intérieur du Labyrinthe
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
                    // ajout des coordonnées où il est possible de se déplacer
                    for (String s : actions) {
                        chemins.add(Labyrinthe.getSuivant(x, y, s));
                    }
                    //initialise le nom du Noeud à la position actuelle du parcours dans le labyrinthe si il peut avoir des enfants
                    if (chemins.size() > 0) {
                        s1 = new StringBuilder();
                        s1.append(x).append(",").append(y);
                    }
                    //créations des arcs dans le graphes pour chaques déplacements possible
                    for (int[] i : chemins) {
                        if (!l.murs[i[0]][i[1]]) {
                            StringBuilder s2 = new StringBuilder();
                            s2.append(i[0]).append(",").append(i[1]);
                            g.ajouterArc(s1.toString(), s2.toString(), 1);
                            g.ajouterArc(s2.toString(), s1.toString(), 1);
                        }
                    }
                }
            }
        }
        this.g = g;
    }
}
