import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Utils {


        public static double euclideanDistance(GeneTSP a, GeneTSP b) {
            double dist;
            double deltaX = Math.pow(a.getX()-b.getX(),2);
            double deltaY = Math.pow(a.getY()-b.getY(),2);
            dist = Math.sqrt(deltaX+deltaY);
            return dist;
        }


}
