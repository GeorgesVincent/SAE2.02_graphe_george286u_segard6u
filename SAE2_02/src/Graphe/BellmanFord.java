package Graphe;

public class BellmanFord {//v5 de l'algo enfin fonctionnelle

    /**
     * Méthode qui résoud un graphe avec un point de depart donne
     * @param g Graphe que l'on veut resoudre
     * @param depart Nom du noeud de depart
     * @return objet Valeur
     */
    public Valeur resoudre(Graphe g, String depart) {
        Valeur v = new Valeur();
        boolean fin = false;
        for (String s : g.listeNoeuds()) {
            if (s.equals(depart)) {
                v.setValeur(s, 0);
            } else {
                v.setValeur(s, Double.MAX_VALUE);
            }
        }
        while (!fin) {
            fin = true;
            for (String s : g.listeNoeuds()) {
                    for (Arc a : g.suivants(s)) {
                        if (v.getValeur(s) + a.getCout() < v.getValeur(a.getDest())) {
                            v.setValeur(a.getDest(), v.getValeur(s) + a.getCout());
                            v.setParent(a.getDest(), s);
                            fin = false;
                        }
                    }
            }
        }
        return v;
    }
}