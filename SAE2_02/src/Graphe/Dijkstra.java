package Graphe;

import java.util.ArrayList;

public class Dijkstra {
    //    Entrees :
//    G un graphe oriente avec une ponderation (poids) positive des arcs
//    A un sommet (depart) de G
//
//    Debut
//    Q <- {} // utilisation d’une liste de noeuds a traiter
//    Pour chaque sommet v de G faire
//      v.distance <- Infini
//      v.parent <- Indefini
//      Q <- Q \ U {v} // ajouter le sommet v a la liste Q
//    Fin Pour
//
//    A.distance <- 0
//    Tant que Q est un ensemble non vide faire
//      u <- un sommet de Q telle que u.distance est minimale
//      Q <- Q \ {u} // enlever le sommet u de la liste Q
//      Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
//          D <- u.distance + poids(u,v)
//          Si D < v.distance
//              Alors v.distance <- D
//                  v.parent <- u
//          Fin Si
//      Fin Pour
//    Fin Tant que
//    Fin

    /**
     * Méthode qui résoud un graphe avec un point de depart donne
     * @param g Graphe que l'on veut resoudre
     * @param depart Nom du noeud de depart
     * @return objet Valeur
     */
    public Valeur resoudre(Graphe g, String depart) {
        ArrayList<Noeud> q = new ArrayList<>();
        Valeur v = new Valeur();
        for (Noeud n : g.getEnsNoeuds()) {
            v.setParent(n.getNom(), null);
            q.add(n);
            if (n.getNom().equals(depart)) {
                v.setValeur(n.getNom(), 0);
            } else {
                v.setValeur(n.getNom(), Double.MAX_VALUE);
            }
        }
        while (!q.isEmpty()) {
            Noeud min = null;
            for (Noeud n : q) {
                if (min == null) {
                    min = n;
                } else if (v.getValeur(min.getNom()) > v.getValeur(n.getNom())) {
                    min = n;
                }
            }
            q.remove(min);
            for (Arc a : g.suivants(min.getNom())) {
                if (v.getValeur(min.getNom()) + a.getCout() < v.getValeur(a.getDest())) {
                    v.setValeur(a.getDest(), v.getValeur(min.getNom()) + a.getCout());
                    v.setParent(a.getDest(), min.getNom());
                }
            }
        }
        return v;
    }
}
