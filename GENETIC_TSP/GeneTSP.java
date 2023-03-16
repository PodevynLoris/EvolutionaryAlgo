package GENETIC_TSP;

public class GeneTSP {

    private int x;

    private int y;

    static final int DIMENSIONS = 40;

    public GeneTSP(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneTSP tspGene = (GeneTSP) o;
        return x == tspGene.x && y == tspGene.y;
    }
}
