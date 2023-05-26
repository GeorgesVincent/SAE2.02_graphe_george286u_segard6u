package Graphe;

import java.util.*;

public class Noeud {
    private String nom;
    private List<Arc> adj;
    public Noeud(String name){
        nom = name;
        adj= new ArrayList<>();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Noeud){
            return ((Noeud) o).getNom().equals(this.nom);
        }
        else {
            return false;
        }
    }

    public void ajouterArc(String destination, double cout){
        adj.add(new Arc(new Noeud(destination),cout));
    }

    public String getNom() {
        return nom;
    }

    public List<Arc> getAdj() {
        return adj;
    }
}
