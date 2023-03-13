import java.util.*;
import java.util.stream.IntStream;


public class GenomeTSP {

    private final List<GeneTSP> genome;
    public static  int N = 20; // N = Genome length

    public GenomeTSP(List<GeneTSP> genomeTSP) {
        this.genome = genomeTSP;
    }

    public List<GeneTSP> getGenome() {
        return genome;
    }

    static GenomeTSP createGenome() {
        Random random = new Random();
        List<GeneTSP> list = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            int ranX = random.nextInt(GeneTSP.DIMENSIONS);
            int ranY = random.nextInt(GeneTSP.DIMENSIONS);
            list.add(new GeneTSP(ranX,ranY));
        }
        return new GenomeTSP(list);
    }


    static GenomeTSP createGenomeCircle() {
        Random random = new Random();
        int centerX = GeneTSP.DIMENSIONS/2;
        int centerY = GeneTSP.DIMENSIONS/2;
        int radius = GeneTSP.DIMENSIONS/4;
        List<GeneTSP> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            int ranX = (int) (centerX + radius * Math.cos(angle));
            int ranY = (int) (centerY + radius * Math.sin(angle));
            list.add(new GeneTSP(ranX,ranY));
        }
        return new GenomeTSP(list);
    }

    public double fitnessTSP() {
        double fitness = 0.0;
        for(int i=0; i<this.genome.size()-1;i++) {
            fitness += Utils.euclideanDistance(this.genome.get(i),this.genome.get(i+1));
        }
        fitness += Utils.euclideanDistance(this.genome.get(this.getGenome().size()-1),this.getGenome().get(0));
        return fitness;
    }

    public void mutateTSP() {
        Random random = new Random();
        int ran = random.nextInt(N);
        int ran2 = random.nextInt(N);

        while(ran == ran2) {
            ran = random.nextInt(N);
            ran2 = random.nextInt(N);
        }
        GeneTSP temp = new GeneTSP(this.genome.get(ran).getX(),this.genome.get(ran).getY());
        this.genome.get(ran).setX(this.genome.get(ran2).getX());
        this.genome.get(ran).setY(this.genome.get(ran2).getY());
        this.genome.get(ran2).setX(temp.getX());
        this.genome.get(ran2).setY(temp.getY());
    }




    


    public GenomeTSP[] crossOverTSP(final GenomeTSP partner)
    {
        List<GeneTSP> child1 = new ArrayList<>(N);
        List<GeneTSP> child2 = new ArrayList<>(N);
        Random random = new Random();
        int ran = random.nextInt(N-1)+1;
       // System.out.println("Cut after element "+(ran+1));
        for(int i=0; i<=ran;i++) {
            child1.add(this.genome.get(i));
            child2.add(partner.getGenome().get(i));
        }
        for(int i=0; i<N;i++) {
            if(!child1.contains(partner.getGenome().get(i)))
            {
                child1.add(partner.getGenome().get(i));
            }
            if(!child2.contains(this.genome.get(i)))
            {
                child2.add(this.genome.get(i));
            }
        }

        GenomeTSP[] offSprings = new GenomeTSP[2];
        offSprings[0] = new GenomeTSP(child1);
        offSprings[1] = new GenomeTSP(child2);
        return offSprings;
    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(final GeneTSP gene : this.genome) {
            builder.append(gene.toString()).append((" "));
        }
        builder.append(" Fitness score : ").append(fitnessTSP());
        return builder.toString();
    }




}
