public class BellmanFord {//v5 de l'algo enfin fonctionnelle
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