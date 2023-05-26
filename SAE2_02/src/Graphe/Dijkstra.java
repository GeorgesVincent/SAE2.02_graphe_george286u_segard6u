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
     * @param g      Graphe que l'on veut resoudre
     * @param depart Nom du noeud de depart
     * @return objet Valeur
     */
    public Valeur resoudre(Graphe g, String depart) {
        //liste qui contiendra les noeud pas encore parcouru
        ArrayList<Noeud> q = new ArrayList<>();
        // objet Valeur possédant le chemin minimal qui sera retourné à la fin de la méthode
        Valeur v = new Valeur();
        //ajoute a la liste tous les noeud du graphe et les initialise dans l'objet Valeur qui sera retourné
        for (String n : g.listeNoeuds()) {
            v.setParent(n, null);
            q.add(new Noeud(n));
            if (n.equals(depart)) {
                v.setValeur(n, 0);
            } else {
                v.setValeur(n, Double.MAX_VALUE);
            }
        }
        //effectue des itération jusqu'à ce que tous les noeuds ai était parcourus
        while (!q.isEmpty()) {
            //initialisation du Noeud qui contiendra la Valeur minimal à null
            Noeud min = null;
            //recherche le Noeud pour lequel la valeur est celle du chemin minimal
            for (Noeud n : q) {
                if (min == null) {
                    //initialise le Noeud min au premier Noeud de la liste pour eviter les nullpointerException et pour que le dernier Noeud soit parcouru
                    min = n;
                    //remplace le Noeud Minimal actuelle par un autre Noeud pas encore parcouru pour lequel le chemin est plus petit
                } else if (v.getValeur(min.getNom()) > v.getValeur(n.getNom())) {
                    min = n;
                }
            }
            //supprission du Noeud minimal dans la liste des noeud parcouru pour ne pas le reparcourir
            q.remove(min);
            //initialise les valeur des Noeuds dont il est le parent à de nouvelle valeur si la valeur actuelle est la minimal
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
