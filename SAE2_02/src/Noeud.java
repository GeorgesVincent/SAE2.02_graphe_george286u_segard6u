import java.util.*;

public class Noeud {
    private String nom;
    private List<Arc> adj;
    public Noeud(String name){
        nom = name;
        adj= new ArrayList<Arc>();
    }

    @Override
    public boolean equals(Object o){
        return ((Noeud)o).nom.equals(this.nom);
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
