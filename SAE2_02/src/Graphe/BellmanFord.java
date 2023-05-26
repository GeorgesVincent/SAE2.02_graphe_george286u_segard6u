package Graphe;

public class BellmanFord {//v5 de l'algo enfin fonctionnelle

    /**
     * Méthode qui résoud un graphe avec un point de depart donne
     *
     * @param g      Graphe que l'on veut resoudre
     * @param depart Nom du noeud de depart
     * @return objet Valeur
     */
    public Valeur resoudre(Graphe g, String depart) {
        //initialisation d'un Objet valeur qui sera retourné à la fin de la méthode
        Valeur v = new Valeur();
        //booleen qui permet de savoir si il y a eu une modification lors de l'itération en cours
        boolean fin = false;
        //initialisation des valeurs des sommets, avec 0 pour le départ et +inf pour les autres
        for (String s : g.listeNoeuds()) {
            if (s.equals(depart)) {
                v.setValeur(s, 0);
            } else {
                v.setValeur(s, Double.MAX_VALUE);
            }
        }
        //boucle qui se finit lorsque la dernière itération ne modifie aucune valeur
        while (!fin) {
            fin = true;
            // parcours de tous les noeuds existants dans le graphe
            for (String s : g.listeNoeuds()) {
                //parcours de tous les arcs du noeud actuelle
                for (Arc a : g.suivants(s)) {
                    //vérifie que le chemin actuelle est le minimal
                    if (v.getValeur(s) + a.getCout() < v.getValeur(a.getDest())) {
                        //affecte les valeurs et parents du chemin minimal actuel
                        v.setValeur(a.getDest(), v.getValeur(s) + a.getCout());
                        v.setParent(a.getDest(), s);
                        // passe à faux pour indiquer qu'une valeur a été modifié
                        fin = false;
                    }
                }
            }
        }
        return v;
    }
}