package Graphe;

import java.util.*;

/**
 * Classe fournie, permet de stocker des valeurs associées au noeud et des parents
 * - un noeud est represente par un String (son nom)
 * - on accede avec des get (getValeur et getParent)
 * - on modifie avec des set (setValeur et setParent)
 */
public class Valeur {

    /**
     * attributs pour stocker les informations (type Table = Dictionnaire)
     * dans le programme de 2 annee.
     */
    Map<String, Double> valeur;
    Map<String, String> parent;

    /**
     * constructeur vide (initialise la possibilité de stocker des valeurs)
     */
    public Valeur() {
        this.valeur = new TreeMap<>();
        this.parent = new TreeMap<>();
    }

    /**
     * permet d'associer une valeur a un nom de noeud (ici L(X))
     *
     * @param nom    le nom du noeud
     * @param valeur la valeur associée
     */
    public void setValeur(String nom, double valeur) {
        // modifie valeur
        this.valeur.put(nom, valeur);
    }

    /**
     * * permet d'associer un parent a un nom de noeud (ici parent(X))
     *
     * @param nom    nom du noeud
     * @param parent nom du noeud parent associe
     */
    public void setParent(String nom, String parent) {
        this.parent.put(nom, parent);
    }

    /**
     * accede au parent stocke associe au noeud nom passe en parametre
     *
     * @param nom nom du noeud
     * @return le nom du noeud parent
     */
    public String getParent(String nom) {
        return this.parent.get(nom);
    }


    /**
     * accede a la valeur associee au noeud nom passe en parametre
     *
     * @param nom nom du noeud
     * @return la valeur stockee
     */
    public double getValeur(String nom) {
        return this.valeur.get(nom);
    }

    /**
     * retourne une chaine qui affiche le contenu
     * - par noeud stocke
     * - a chaque noeud, affiche la valeur puis le noeud parent
     *
     * @return descriptif du noeud
     */
    public String toString() {
        String res = "";
        // pour chaque noeud
        for (String s : this.valeur.keySet()) {
            // ajoute la valeur et le noeud parent
            Double valeurNoeud = valeur.get(s);
            String noeudParent = parent.get(s);
            res += s + " ->  V:" + valeurNoeud + " p:" + noeudParent + "\n";
        }
        return res;

    }

    /**
     * methode qui retourne une chaine qui représente le chemin minimum
     * @param destination noeud de destination
     * @return liste de string qui represente le nom des noeud
     */
    public List<String> calculerChemin(String destination) {
        String s = destination;
        //list de chaine  destiné à contenir le chemin
        List<String> ch = new ArrayList<>();
        //liste des enfants existants
        Set<String> keys = parent.keySet();
        //booleen qui permet de savoir si le chemin est terminer
        boolean fin = true;
        int i = 0;
        //parcours de tout les parents
        for (String key : keys) {
            //verifie qu'il y a un parent pour l'enfant actuelle
            if (parent.get(key) != null) {
                //ajoute la destination a la liste si elle existe dans les enfants
                //si elle existent pas la suite du programme ne s'éxécute pas et retourne la liste encore vide
                if (parent.get(key).equals(destination)) {
                    ch.add(destination);
                    fin = false;
                    break;
                }
            }
        }
        //tant que le chemin minimal n'a pas était ajouter dans la liste
        while (!fin) {
            for (String key : keys) {
                i++;
                if (parent.get(key) != null) {
                    if (parent.get(key).equals(s)) {
                        ch.add(key);
                        s = key;
                        i = 0;
                    }
                }
                if (i > keys.size()) {
                    fin = true;
                }
            }
        }
        // inverse l'ordre pour avoir la destination a la fin et le départ au début
        Collections.reverse(ch);
        return ch;
    }
}

