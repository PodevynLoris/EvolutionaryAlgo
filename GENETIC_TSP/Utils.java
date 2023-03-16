package GENETIC_TSP;

public class Utils {
    public static double euclideanDistance(GENETIC_TSP.GeneTSP a, GENETIC_TSP.GeneTSP b) {
        double dist;
        double deltaX = Math.pow(a.getX()-b.getX(),2);
        double deltaY = Math.pow(a.getY()-b.getY(),2);
        dist = Math.sqrt(deltaX+deltaY);
        return dist;
    }
}
