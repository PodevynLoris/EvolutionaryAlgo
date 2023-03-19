package GENETIC_TSP;
import java.util.*;

public class Population {
    public static final int M = 1000;

    public List<GENETIC_TSP.GenomeTSP> population;

    GENETIC_TSP.GenomeTSP bestGenome = new GENETIC_TSP.GenomeTSP();

    public Population() {
        this.population = initialiseTSPPOP();
    }

    public List<GENETIC_TSP.GenomeTSP> getPopulation() {
        return population;
    }

    public GENETIC_TSP.GenomeTSP getBestGenome() {
        return bestGenome;
    }

    private List<GENETIC_TSP.GenomeTSP> initialiseTSPPOP() {
        List<GENETIC_TSP.GenomeTSP> list = new ArrayList<>();
        GENETIC_TSP.GenomeTSP genome = GENETIC_TSP.GenomeTSP.createGenomeCircle();
        list.add(genome);
        for (int i = 1; i < M; i++) {
            List<GENETIC_TSP.GeneTSP> shuffledGenes = new ArrayList<>(genome.getGenome());
            Collections.shuffle(shuffledGenes);
            GENETIC_TSP.GenomeTSP newGenome = new GENETIC_TSP.GenomeTSP(shuffledGenes);
            list.add(newGenome);
        }
        return list;
    }

    GENETIC_TSP.GenomeTSP selectionTSP() {
        GENETIC_TSP.GenomeTSP selected = null;
        double sum = 0.0;
        for (GENETIC_TSP.GenomeTSP genome : this.population) {
            sum += genome.getFitness();
        }
        double ran = Math.random() * sum;
        double partialSum = 0.0;
        for (GENETIC_TSP.GenomeTSP genome : this.population) {
            partialSum += genome.getFitness();
            if (partialSum >= ran) {
                selected = genome;
                break;
            }
        }
        return selected;
    }

    List<GENETIC_TSP.GenomeTSP> applyCrossOverAndMutation(int PRc, int PRm, GENETIC_TSP.GenomeTSP parent1, GENETIC_TSP.GenomeTSP parent2) {
        Random random = new Random();
        List<GENETIC_TSP.GenomeTSP> children = new ArrayList<>();
        if (random.nextInt(100) < PRc) {
            children = parent1.crossoverTSP(parent2);
        } else {
            children.add(parent1);
            children.add(parent2);
        }

        for (GENETIC_TSP.GenomeTSP child : children) {
            if (random.nextInt(100) < PRm) {
                child.mutateTSP();
            }
        }
        return children;
    }

    public void generateNewPopulation(int PRc, int PRm) {
        List<GENETIC_TSP.GenomeTSP> newPopulation = new ArrayList<>();
        for (int i = 0; i < M; i+=2) {
            GENETIC_TSP.GenomeTSP parent1 = selectionTSP();
            GENETIC_TSP.GenomeTSP parent2 = selectionTSP();
            List<GENETIC_TSP.GenomeTSP> children = applyCrossOverAndMutation(PRc, PRm, parent1, parent2);
            newPopulation.addAll(children);
        }
        this.population = newPopulation;
    }

    public void evaluatePopulation() {
        double bestFitness = 0.0;
        GENETIC_TSP.GenomeTSP fitnessGenome = null;
        for (GENETIC_TSP.GenomeTSP genome : this.population) {
            genome.fitnessTSP();
            if (genome.getFitness() > bestFitness) {
                bestFitness = genome.getFitness();
                fitnessGenome = genome;
            }
        }
        if (fitnessGenome.getFitness() > this.bestGenome.getFitness()) {
            this.bestGenome = fitnessGenome;
        }
    }

    public double getAverageFitness() {
        double avg = 0.0;
        for(GENETIC_TSP.GenomeTSP genome : this.population) {
            avg += genome.getFitness();
        }
        avg = avg / this.population.size();
        return avg;
    }


    public GENETIC_TSP.GenomeTSP getBestGenomeOfCurrentPopulation() {
        double bestFitness = 0.0;
        GENETIC_TSP.GenomeTSP bestGenomeOfCurrentPopulation = null;

        for (GENETIC_TSP.GenomeTSP genome : this.population) {
            if (genome.getFitness() > bestFitness) {
                bestFitness = genome.getFitness();
                bestGenomeOfCurrentPopulation = genome;
            }
        }

        return bestGenomeOfCurrentPopulation;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        System.out.println("Population of size : " + this.getPopulation().size());
        for (final GENETIC_TSP.GenomeTSP genome : this.population) {
            builder.append(genome.toString()).append(("\n"));
        }
        return builder.toString();
    }
}
