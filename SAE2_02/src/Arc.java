public class Arc {
    private String dest;
    private double cout;

    public Arc(Noeud n, int cout) {
        this.dest = n.getNom();
        if (cout>=0){
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
