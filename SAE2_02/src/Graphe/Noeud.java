package Graphe;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
    private String nom;
    private List<Arc> adj;

    public Noeud(String name) {
        nom = name;
        adj = new ArrayList<>();
    }

    /**
     * methode pour comparer des noeud
     * @param o noeud acvec lequel on veut comparer
     * @return true si == sinon false
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Noeud) {
            return ((Noeud) o).getNom().equals(this.nom);
        } else {
            return false;
        }
    }

    /**
     * methode qui ajoute un arc a un noeud
     *
     * @param destination noeud de destination
     * @param cout        cout de l'arc
     */
    public void ajouterArc(String destination, double cout) {
        adj.add(new Arc(new Noeud(destination), cout));
    }

    public String getNom() {
        return nom;
    }

    public List<Arc> getAdj() {
        return adj;
    }
}
