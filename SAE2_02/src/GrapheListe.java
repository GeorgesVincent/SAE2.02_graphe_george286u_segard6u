import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GrapheListe implements Graphe {
    private List<String> ensNom;
    private List<Noeud> ensNoeuds;

    public GrapheListe() {
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
    }

    public GrapheListe(String fichier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichier));
        String[] s;
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
        while (br.ready()) {
            s = br.readLine().split("\t");
            this.ajouterArc(s[0], s[1], Double.parseDouble(s[2]));
        }
        br.close();
    }

    public void ajouterArc(String depart, String destination, double cout) {
        boolean trouve = false;
        boolean trouve2 = false;
        int i = -1;
        Noeud dep = new Noeud(depart);
        Noeud dest = new Noeud(destination);
        for (Noeud n : ensNoeuds) {
            if (!trouve || !trouve2) {
                if (!trouve) {
                    trouve = dep.equals(n);
                    i++;
                }
                if (!trouve2) {
                    trouve2 = dest.equals(n);
                }
            } else {
                break;
            }
        }

        if (trouve) {
            ensNoeuds.get(i).ajouterArc(destination, cout);
        } else {
            dep.ajouterArc(destination, cout);
            if (ensNoeuds.size() > 0) {
                insTrier(dep);
            } else {
                ensNoeuds.add(dep);
                ensNom.add(dep.getNom());
            }
        }
        if (!trouve2 && !dep.equals(dest)) {
            insTrier(dest);
        }
    }

    public void insTrier(Noeud n) {
        boolean trouver = false;
        int comp;
        int comp2;
        for (int i = 0; i < ensNom.size(); i++) {
            comp = 0;
            comp2 = 0;
            int nl = n.getNom().length();
            int noml = ensNom.get(i).length();
            if (nl > noml) {
                for (int x = nl - 1; x >= 0; x--) {
                    comp2 += (int) (n.getNom().charAt(nl - x - 1) * Math.pow(128, x));
                    if (x >= nl - noml) {
                        comp += (int) (ensNom.get(i).charAt(nl - x - 1)* Math.pow(128, x));
                    }
                }
            } else {
                for (int x = noml - 1; x >= 0; x--) {
                    comp += (int) (ensNom.get(i).charAt(noml - x - 1) * Math.pow(128, x));
                    if (x >= noml - nl) {
                        comp2 += (int) (n.getNom().charAt(noml - x - 1) * Math.pow(128, x));
                    }
                }
            }
            comp -= comp2;
            if (comp == 0) {
                trouver = true;
                break;
            }
            if (comp > 0) {
                ensNoeuds.add(ensNoeuds.get(ensNoeuds.size() - 1));
                ensNom.add(ensNom.get(ensNom.size() - 1));
                for (int y = ensNom.size() - 2; y > i; y--) {
                    ensNoeuds.set(y, ensNoeuds.get(y - 1));
                    ensNom.set(y, ensNom.get(y - 1));
                }
                ensNoeuds.set(i, n);
                ensNom.set(i, n.getNom());
                trouver = true;
                break;
            }
        }
        if (!trouver) {
            ensNoeuds.add(n);
            ensNom.add(n.getNom());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (ensNom.size() > 0) {
            for (Noeud n : ensNoeuds) {
                s.append(n.getNom()).append(" -> ");
                for (Arc a : n.getAdj()) {
                    s.append(a.getDest()).append("(").append(a.getCout()).append(") ");
                }
                s.append("\n");
            }
            return s.toString();
        } else {
            return null;
        }
    }

    public String toGraphViz() {
        StringBuilder s = new StringBuilder();
        s.append("digraph G {\n");
        if (ensNom.size() > 0) {
            for (Noeud n : ensNoeuds) {
                for (Arc a : n.getAdj()) {
                    s.append(n.getNom()).append(" -> ");
                    s.append(a.getDest()).append(" [label = ").append((int) a.getCout()).append(" ]\n");
                }
            }
            s.append("}");
            return s.toString();
        } else {
            return null;
        }
    }

    @Override
    public List<String> listeNoeuds() {
        return ensNom;
    }

    @Override
    public List<Arc> suivants(String n) {
        List<Arc> arcs = new ArrayList<>();
        int i;
        for (i = 0; i < ensNoeuds.size(); i++) {
            if (n.equals(ensNoeuds.get(i).getNom())) {
                break;
            }
        }
        if (i < ensNoeuds.size()) {
            arcs.addAll(ensNoeuds.get(i).getAdj());
        }
        return arcs;
    }

    public static void fichierListeArc(String fichier) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(fichier));
        FileWriter fw = new FileWriter("resGraphe");
        String[] s;
        String[] arcs;
        s = bf.readLine().split("\t");
        while (bf.ready()) {
            arcs = bf.readLine().split("\t");
            for (int i = 1; i < s.length; i++) {
                if (arcs[i].charAt(0) != '0') {
                    fw.write(arcs[0] + " " + s[i] + " " + arcs[i] + "\n");
                }
            }
        }
        fw.close();
        bf.close();
    }

    public List<Noeud> getEnsNoeuds() {
        return ensNoeuds;
    }
}
