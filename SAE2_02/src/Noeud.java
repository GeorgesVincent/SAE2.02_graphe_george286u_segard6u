import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Noeud {
    private String nom;
    private List<ARC> adj;
    public Noeud(String name){
        nom = name;
        adj= new List<ARC>();
    }

    @Override
    public boolean equals(Object o){
        return ((Noeud)o).nom.equals(this.nom);
    }

    public void ajouterArc(String destination, double cout){
        adj.add(new ARC(new Noeud(destination),cout));
    }

    public String getNom() {
        return nom;
    }

    public List<ARC> getAdj() {
        return adj;
    }
}
