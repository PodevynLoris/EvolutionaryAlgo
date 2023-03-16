package GENETIC_TSP;

import java.util.*;

public class GenomeTSP {

    private List<GENETIC_TSP.GeneTSP> genome;

    public static int N = 20;

    private double fitness;

    public GenomeTSP() {
        this.genome = new ArrayList<>(N);
        this.fitness = 0.0;
    }

    public GenomeTSP(List<GENETIC_TSP.GeneTSP> genome) {
        this.genome = genome;
        fitnessTSP();
    }

    public List<GENETIC_TSP.GeneTSP> getGenome() {
        return genome;
    }

    public double getFitness() {
        return fitness;
    }

    static GenomeTSP createGenome() {
        Random random = new Random();
        List<GENETIC_TSP.GeneTSP> list = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            int ranX = random.nextInt(GENETIC_TSP.GeneTSP.DIMENSIONS);
            int ranY = random.nextInt(GENETIC_TSP.GeneTSP.DIMENSIONS);
            list.add(new GENETIC_TSP.GeneTSP(ranX,ranY));
        }
        GenomeTSP genome = new GenomeTSP(list);
        return genome;
    }

    static GenomeTSP createGenomeCircle() {
        Random random = new Random();
        int centerX = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int centerY = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int radius = GENETIC_TSP.GeneTSP.DIMENSIONS/4;
        List<GENETIC_TSP.GeneTSP> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            int ranX = (int) (centerX + radius * Math.cos(angle));
            int ranY = (int) (centerY + radius * Math.sin(angle));
            list.add(new GENETIC_TSP.GeneTSP(ranX,ranY));
        }
        GenomeTSP genome = new GenomeTSP(list);
        return genome;
    }

    public void fitnessTSP() {
        double fitness = 0.0;
        for(int i=0; i<this.genome.size()-1;i++) {
            fitness += GENETIC_TSP.Utils.euclideanDistance(this.genome.get(i),this.genome.get(i+1));
        }
        fitness += GENETIC_TSP.Utils.euclideanDistance(this.genome.get(this.getGenome().size()-1),this.getGenome().get(0));
        this.fitness = 1/fitness;
    }

    public void mutateTSP() {
        Random random = new Random();
        int index1 = random.nextInt(N);
        int index2 = random.nextInt(N);
        while(index1 == index2) {
            index2 = random.nextInt(N);
        }
        GENETIC_TSP.GeneTSP gene1 = this.genome.get(index1);
        GENETIC_TSP.GeneTSP gene2 = this.genome.get(index2);
        this.genome.set(index1,gene2);
        this.genome.set(index2,gene1);
    }

    public int getGeneIndex(List<GENETIC_TSP.GeneTSP> list, GENETIC_TSP.GeneTSP gene) {
        for(int i=0; i<list.size();i++) {
            if(list.get(i).equals(gene)) {
                return i;
            }
        }
        return -1;
    }

    public List<GenomeTSP> crossoverTSP(GenomeTSP partner)
    {
        List<GENETIC_TSP.GeneTSP> child1 = new ArrayList<>(N);
        List<GENETIC_TSP.GeneTSP> child2 = new ArrayList<>(N);
        Random random = new Random();
        for(int i=0; i<N;i++) {
            child1.add(this.genome.get(i));
            child2.add(partner.getGenome().get(i));
        }
        int crossOverPoint = random.nextInt(N);
        for(int i=0; i<crossOverPoint;i++) {
            GENETIC_TSP.GeneTSP gene = partner.getGenome().get(i);
            int index = getGeneIndex(child1, gene);
            child1.set(index,child1.get(i));
            child1.set(i,gene);

            gene = this.genome.get(i);
            index = getGeneIndex(child2, gene);
            child2.set(index,child2.get(i));
            child2.set(i,gene);
        }
        List<GenomeTSP> offspring = new ArrayList<>();
        offspring.add(new GenomeTSP(child1));
        offspring.add(new GenomeTSP(child2));
        return offspring;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(final GENETIC_TSP.GeneTSP gene : this.genome) {
            builder.append(gene.toString()).append((" "));
        }
        builder.append("Fitness score: ").append(this.fitness);
        return builder.toString();
    }
}
