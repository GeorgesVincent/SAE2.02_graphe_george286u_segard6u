import java.util.ArrayList;
import java.util.List;

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

    public Valeur resoudre(Graphe g, String depart){
        ArrayList<Noeud> q = new ArrayList<>();
        Valeur v = new Valeur();
        double dis;
        double min = Double.MAX_VALUE;
        double d;
        Noeud u = null;
        for (Noeud n : ((GrapheListe)g).getEnsNoeuds()){
            v.setParent(n.getNom(),null);
            v.setValeur(n.getNom(),Double.MAX_VALUE);
            q.add(n);
        }

        v.setValeur(depart,0);
        while (!q.isEmpty()){
            //recherche minimum
            for (Noeud no : q){
                dis = v.getValeur(no.getNom());
                if (dis < min){

                    //min = dis;
                    u = no;
                }
            }
            q.remove(u);
            for (Noeud n : q){
                for (Arc ar : n.getAdj()){
                    if (ar.getDest().equals(u.getNom())){
                        d = (v.getValeur(u.getNom()) + ar.getCout());
                        if (d < v.getValeur(n.getNom())){
                            v.setParent(n.getNom(),u.getNom());
                            v.setValeur(n.getNom(),d);
                        }
                    }
                }
            }
        }
        return v;
    }
}
