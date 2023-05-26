package Graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    private List<String> ensNom;
    private List<Noeud> ensNoeuds;

    private int NBArc;

    /**
     * Constructeur vide
     */
    public GrapheListe() {
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
    }

    /**
     * Constructeur avec parametre
     * @param fichier Nom du fichier
     * @throws IOException
     */
    public GrapheListe(String fichier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichier));
        String[] s;
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
        while (br.ready()) {
            s = br.readLine().split("\t");
            this.ajouterArc(s[0], s[1], Double.parseDouble(s[2]));
            NBArc ++;
        }
        br.close();
    }

    /**
     * Genere un graphe avec un noeud de depart, un noeud d'ariver et un nombre
     * @param depart nom du noeud de depart
     * @param arriver nom du noeud d'arriver
     * @param nb  nombre
     */
    public void genererGraphe(String depart,String arriver,int nb){
        ArrayList<Noeud> graphe = new ArrayList<>();
        graphe.add(new Noeud(depart));
        {
            boolean deptrouver = false;
            boolean arrtrouver = false;
            for (int i = 0; i < nb; i++) {
                if (String.valueOf(i).equals(depart)) {
                    deptrouver = true;
                } else if (String.valueOf(i).equals(arriver)) {
                    arrtrouver = true;
                } else {
                    graphe.add(new Noeud(String.valueOf(i)));
                }
                if ((!deptrouver || !arrtrouver)&&i>nb-2) {
                    i += 2;
                }
            }
        }
        graphe.add(new Noeud(arriver));
        for(int i =0;i<graphe.size()-1;i++){
            ArrayList<Noeud> noeudnonlie = new ArrayList<>(graphe);
            noeudnonlie.remove(i);
            int nbarc =(int) (Math.random()* noeudnonlie.size());
            for (int x =0;x<=nbarc;x++){
                Noeud rdm = noeudnonlie.get((int)(Math.random()*noeudnonlie.size()));
                this.ajouterArc(graphe.get(i).getNom(),rdm.getNom(),Math.random()*100);
                noeudnonlie.remove(rdm);
            }
        }
    }

    /**
     * methode qui ajoute un Arc a un graphe
     * @param depart nom du noeud de depart
     * @param destination nom du noeud d'arriver
     * @param cout cout de l'arc
     */
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
            NBArc ++;
        } else {
            dep.ajouterArc(destination, cout);
            NBArc ++;
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

    /**
     * methode qui trie un graphe avec un noeud
     * @param n noeud que l'on veut trier dans le graphe
     */
    public void insTrier(Noeud n) {
        boolean trouver = false;
        for (int i = 0; i < ensNoeuds.size(); i++) {
            int comp = ensNoeuds.get(i).getNom().compareTo(n.getNom());
            if(comp ==0){
                trouver = true;
                break;
            }
            if (comp > 0) {
                ensNoeuds.add(ensNoeuds.get(ensNoeuds.size() - 1));
                ensNom.add(ensNom.get(ensNom.size() - 1));
                for (int y = ensNoeuds.size() - 2; y > i; y--) {
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

    /**
     * methode toString
     * @return chaine qui decrit le graphe
     */
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

    /**
     * methode qui traduit un graphe pour graphViz
     * @return chaine qui decrit le graphe pour graphViz
     */
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

    /**
     * getter de ensNom
     * @return ensNom
     */
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

    /**
     * methode qui liste les arc d'un graphe dans un fichier
     * @param fichier nom du fichier
     * @throws IOException
     */
    public static void fichierListeArc(String fichier) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(fichier));
        FileWriter fw = new FileWriter(fichier.replaceAll(".txt","res.txt"));
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

    public int getNBArc() {
        return NBArc;
    }

    public List<Noeud> getEnsNoeuds() {
        return ensNoeuds;
    }
}
