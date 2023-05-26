package Graphe;

public class Arc {
    private String dest;
    private double cout;

    /**
     * Constructeur qui construit un chemin entre 2 points
     * @param n    Noeud destination
     * @param cout cout de l'arc
     */
    public Arc(Noeud n, double cout) {
        this.dest = n.getNom();
        if (cout >= 0) {
            this.cout = cout;
        }
    }

    public String getDest() {
        return dest;
    }

    public double getCout() {
        return cout;
    }
}
