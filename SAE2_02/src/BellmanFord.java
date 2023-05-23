public class BellmanFord {
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
            String antecedant = depart;
            while(antecedant!=null) {
                for (Arc a : g.suivants(antecedant)) {
                    if (v.getValeur(antecedant) + a.getCout() < v.getValeur(a.getDest())) {
                        v.setValeur(a.getDest(), v.getValeur(antecedant) + a.getCout());
                        v.setParent(antecedant,a.getDest());
                        fin = false;
                    }
                }
                antecedant = v.getParent(antecedant);
            }
        }
        return v;
    }
}
