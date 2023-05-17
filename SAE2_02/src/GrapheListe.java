import java.util.List;

public class GrapheListe implements Graphe {
    private List<String> ensNom;
    private List<Noeud> ensNoeuds;

    public void ajouterArc(String depart, String destination, double cout) {
        boolean trouve = false;
        boolean trouve2 = false;
        int i = 0;

        Noeud dep = new Noeud(depart);
        Noeud dest = new Noeud(depart);
        for (Noeud n : ensNoeuds) {
            if (!trouve && !trouve2) {
                trouve = dep.equals(n);
                trouve2 = dest.equals(n);
                if (trouve && trouve2) {
                    break;
                }
                if (!trouve) {
                    i++;
                }
            } else if (trouve) {
                trouve2 = dest.equals(n);
                if (trouve2) {
                    break;
                }
            } else {
                trouve = dep.equals(n);
                if (trouve) {
                    break;
                }
                i++;
            }
        }

        if (trouve) {
            ensNoeuds.get(i).ajouterArc(destination, cout);
        } else {
            dep.ajouterArc(destination, cout);
            ensNoeuds.add(dep);
            ensNom.add(depart);
        }
        if (!trouve2) {
            ensNoeuds.add(dest);
            ensNom.add(destination);
        }
    }

    @Override
    public List<String> listeNoeuds() {
        return null;
    }

    @Override
    public List<Arc> suivants(String n) {
        return null;
    }
}
