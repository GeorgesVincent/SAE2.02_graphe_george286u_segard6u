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
     *
     * @param fichier Nom du fichier
     * @throws IOException
     */
    public GrapheListe(String fichier) throws IOException {
        //initialisation d'un Reader pour lire un fichier
        BufferedReader br = new BufferedReader(new FileReader(fichier));
        //tableau de chaîne destiné à contenir les différents éléments du fichiers
        String[] s;
        //initialisations des attributs
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
        //lis le fichier jusque la fin
        while (br.ready()) {
            s = br.readLine().split("\t");
            //ajoute les arcs au graphe
            this.ajouterArc(s[0], s[1], Double.parseDouble(s[2]));
            //comptes le nombres d'arcs totals contenu dans le graphe
            NBArc++;
        }
        br.close();
    }

    /**
     * Genere un graphe avec un noeud de depart, un noeud d'ariver et un nombre
     *
     * @param depart  nom du noeud de depart
     * @param arriver nom du noeud d'arriver
     * @param nb      nombre de noeuds
     */
    public void genererGraphe(String depart, String arriver, int nb) {
        //supprime les précédentes données
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();
        //initialisation d'une initialisation d'une liste de noeuds
        ArrayList<Noeud> graphe = new ArrayList<>();
        //ajout du Noeud de départ
        graphe.add(new Noeud(depart));
        {
            //booleens permettant d'éviter des conflits de Nom de Noeud car 2 Nom on les même noms et que la méthode equals compare les noms et d'avoir le bon nombre de Noeuds
            boolean deptrouver = false;
            boolean arrtrouver = false;
            //ajout du nombre de Noeud nécessaire pour atteindre la taille de graphe voulu
            for (int i = 0; i < nb; i++) {
                if (!deptrouver && String.valueOf(i).equals(depart)) {
                    deptrouver = true;
                } else if (!arrtrouver && String.valueOf(i).equals(arriver)) {
                    arrtrouver = true;
                } else {
                    graphe.add(new Noeud(String.valueOf(i)));
                }
                //permet d'ajuster le nombre de Noeud selon les noms des Noeuds choisi
                if ((!deptrouver && !arrtrouver) && i > nb - 3) {
                    i += 2;
                } else if ((!deptrouver || !arrtrouver) && i > nb - 2) {
                    i += 1;
                }
            }
        }
        //ajout du Noeud de fin
        graphe.add(new Noeud(arriver));
        //parcours tous les Noeud créer sauf la fin
        for (int i = 0; i < graphe.size() - 1; i++) {
            //création d'une liste de Noeuds qui n'ont pas encore pour parents le Noeud actuelle
            ArrayList<Noeud> noeudnonlie = new ArrayList<>(graphe);
            //supprime le Noeud actuelle de la liste des Noeud sans parents
            noeudnonlie.remove(i);
            int nbarc = (int) (Math.random() * noeudnonlie.size());
            //ajoute un nombre aléatoire d'enfants actuelles compris entre 1 et la taille -1 de la liste de Noeud sans Parent
            for (int x = 0; x <= nbarc; x++) {
                //choisi un Noeud aléatoire en tant que fils du Noeud actuelle
                Noeud rdm = noeudnonlie.get((int) (Math.random() * noeudnonlie.size()));
                //ajoute un arc dans le graphe
                this.ajouterArc(graphe.get(i).getNom(), rdm.getNom(), Math.random() * 100);
                //enlève les Noeuds qui ont désormais un parent
                noeudnonlie.remove(rdm);
            }
        }
    }

    /**
     * methode qui ajoute un Arc a un graphe
     *
     * @param depart      nom du noeud de depart
     * @param destination nom du noeud d'arriver
     * @param cout        cout de l'arc
     */
    public void ajouterArc(String depart, String destination, double cout) {
        //boolean permettant de savoir si le Noeuds existent déjà
        boolean trouve = false;
        boolean trouve2 = false;
        //emplacement du Noeud de départ dans la liste
        int i = -1;
        // initialisation du Noeud de départ et d'arriver
        Noeud dep = new Noeud(depart);
        Noeud dest = new Noeud(destination);
        //recherche le Noeuds de départ et d'arriver dans les Noeuds déjà existants
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
        //permet de savoir qu'elle action effectué
        if (trouve) {
            ensNoeuds.get(i).ajouterArc(destination, cout);
            NBArc++;
        } else {
            dep.ajouterArc(destination, cout);
            NBArc++;
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
     * methode qui insére un Noeud tout en le maintenant trier
     *
     * @param n noeud que l'on souhaite insérer
     */
    public void insTrier(Noeud n) {
        //booleen permmetant de savoir si le Noeud existe
        boolean trouver = false;
        //parcours des Noeuds du graphe
        for (int i = 0; i < ensNoeuds.size(); i++) {
            int comp = ensNoeuds.get(i).getNom().compareTo(n.getNom());
            if (comp == 0) {
                trouver = true;
                break;
            }
            //
            if (comp > 0) {
                //déplacement des éléments de 1
                ensNoeuds.add(ensNoeuds.get(ensNoeuds.size() - 1));
                ensNom.add(ensNom.get(ensNom.size() - 1));
                for (int y = ensNoeuds.size() - 2; y > i; y--) {
                    ensNoeuds.set(y, ensNoeuds.get(y - 1));
                    ensNom.set(y, ensNom.get(y - 1));
                }
                //insertion du Noeud
                ensNoeuds.set(i, n);
                ensNom.set(i, n.getNom());
                trouver = true;
                break;
            }
        }
        //si le Noeud n'existe pas à la fin de la recherche il est insérer à la fin de la liste
        if (!trouver) {
            ensNoeuds.add(n);
            ensNom.add(n.getNom());
        }
    }

    /**
     * methode toString
     *
     * @return chaine qui decrit le graphe
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        //parcours tous les Noeuds pour créer une chaîne sous la forme désiré
        for (Noeud n : ensNoeuds) {
            s.append(n.getNom()).append(" -> ");
            //parcours des fils du Noeud actuelle
            for (Arc a : n.getAdj()) {
                s.append(a.getDest()).append("(").append(a.getCout()).append(") ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * methode qui traduit un graphe pour graphViz
     *
     * @return chaine qui decrit le graphe pour graphViz
     */
    public String toGraphViz() {
        StringBuilder s = new StringBuilder();
        s.append("digraph G {\n");
        //parcours tous les Noeuds pour créer une chaîne sous la forme désiré
            for (Noeud n : ensNoeuds) {
                //parcours des fils du Noeud actuelle
                for (Arc a : n.getAdj()) {
                    s.append(n.getNom()).append(" -> ");
                    s.append(a.getDest()).append(" [label = ").append((int) a.getCout()).append(" ]\n");
                }
            }
            s.append("}");
            return s.toString();
    }

    /**
     * getter de ensNom
     *
     * @return ensNom
     */
    @Override
    public List<String> listeNoeuds() {
        return ensNom;
    }

    /**
     * méthode qui renvoie les enfants du noeud ayant pour nom le paramètre
     * @param n nom du Noeud dont l'on désire connaître les enfants
     * @return
     */
    @Override
    public List<Arc> suivants(String n) {
        //liste destiné à contenir les arcs d'un Noeud choisi
        List<Arc> arcs = new ArrayList<>();
        //recherche du Noeud choisis
        for (Noeud pos : ensNoeuds) {
            if (n.equals(pos.getNom())) {
                //ajoute tout les arcs du Noeud choisi dans la liste
                arcs.addAll(pos.getAdj());
                break;
            }
        }
        return arcs;
    }

    /**
     * fonction(méthode de classe) qui liste les arc d'un graphe sous forme de matrice dans un fichier
     *
     * @param fichier lien du fichier
     * @throws IOException
     */
    public static void fichierListeArc(String fichier) throws IOException {
        //ouverture d'un flux pour lire le fichier choisis
        BufferedReader bf = new BufferedReader(new FileReader(fichier));
        //ouverture d'un flux pour écrire dans un fichier
        FileWriter fw = new FileWriter(fichier.replaceAll(".txt", "res.txt"));
        //tableau de chaîne destiné à contenir les noms des Noeuds
        String[] s;
        //tableau de chaîne destiné à contenir les différente données lié aux arcs
        String[] arcs;
        s = bf.readLine().split("\t");
        //lecture du fichier entière
        while (bf.ready()) {
            arcs = bf.readLine().split("\t");
            for (int i = 1; i < s.length; i++) {
                if (arcs[i].charAt(0) != '0') {
                    //convertion du contenu du fichier choisi sous le format souhaiter
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

}
