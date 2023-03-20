package GENETIC_TSP;
import GENETIC_KP.Gene;
import GENETIC_KP.Genome;

import java.util.*;

public class Population {
    public int M;

    public List<GENETIC_TSP.GenomeTSP> populationTSP;
    public List<Genome> populationKP;
    public int choice = Evolve.choice;

    GENETIC_TSP.GenomeTSP bestGenomeTSP = new GENETIC_TSP.GenomeTSP();
    Genome bestGenomeKP = new Genome();
    //TODO THERE MIGHT BE A PROBLEM WHEN USING THIS genome because its a new constructor I had to create, with NO parameter, we use it in evaluatePopulationKP
    public Population() {
        this(5); // Default population size
    }
    public Population(int populationSize) {
        this.M = populationSize;
        if (choice == 1) {
            this.populationTSP = initialiseTSPPOP();
            this.populationKP = null;
        } else {
            this.populationKP = initialiseKPPOP();
            this.populationTSP = null;
        }
    }

    public List<GENETIC_TSP.GenomeTSP> getPopulationTSP() {
        return populationTSP;
    }

    public List<Genome> getPopulationKP() {
        return populationKP;
    }

    public GENETIC_TSP.GenomeTSP getBestGenomeTSP() {
        return bestGenomeTSP;
    }

    public Genome getBestGenomeKP() {
        return bestGenomeKP;
    }

    public List<Genome> initialiseKPPOP() {
        final List<Genome> list = new ArrayList<>();
        for(int i=0; i<M;i++) {
            final Genome genome = Genome.createGenome();
            list.add(genome);
        }
        return list;
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
        for (GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
            sum += genome.getFitnessTSP();
        }
        double ran = Math.random() * sum;
        double partialSum = 0.0;
        for (GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
            partialSum += genome.getFitnessTSP();
            if (partialSum >= ran) {
                selected = genome;
                break;
            }
        }
        return selected;
    }

    Genome selectionKP() {
        Genome selected = null;
        double sum = 0.0;
        for (Genome genome : this.populationKP) {
            sum += genome.getFitnessKP();
        }
        double ran = Math.random() * sum;
        double partialSum = 0.0;
        for (Genome genome : this.populationKP) {
            partialSum += genome.getFitnessKP();
            if (partialSum >= ran) {
                selected = genome;
                break;
            }
        }
        return selected;
    }

    List<GENETIC_TSP.GenomeTSP> applyCrossOverAndMutationTSP(int PRc, int PRm, GENETIC_TSP.GenomeTSP parent1, GENETIC_TSP.GenomeTSP parent2) {
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

    List<Genome> applyCrossOverAndMutationKP(int PRc, int PRm, Genome parent1, Genome parent2) {
        Random random = new Random();
        List<Genome> children = new ArrayList<>();
        if (random.nextInt(100) < PRc) {
            children = parent1.SPCrossOverKP(parent2);
        } else {
            children.add(parent1);
            children.add(parent2);
        }

        for (Genome child : children) {
            if (random.nextInt(100) < PRm) {
                child.mutateKP();
            }
        }
        return children;
    }

    public void generateNewPopulationTSP(int PRc, int PRm) {
        List<GENETIC_TSP.GenomeTSP> newPopulation = new ArrayList<>();
        for (int i = 0; i < M; i+=2) {
            GENETIC_TSP.GenomeTSP parent1 = selectionTSP();
            GENETIC_TSP.GenomeTSP parent2 = selectionTSP();
            List<GENETIC_TSP.GenomeTSP> children = applyCrossOverAndMutationTSP(PRc, PRm, parent1, parent2);
            newPopulation.addAll(children);
        }
        this.populationTSP = newPopulation;
    }

    public void generateNewPopulationKP(int PRc, int PRm) {
        List<Genome> newPopulation = new ArrayList<>();
        for (int i = 0; i < M; i+=2) {
            Genome parent1 = selectionKP();
            Genome parent2 = selectionKP();
            List<Genome> children = applyCrossOverAndMutationKP(PRc, PRm, parent1, parent2);
            newPopulation.addAll(children);
        }
        this.populationKP = newPopulation;
    }

    public void evaluatePopulationTSP() {
        double bestFitness = 0.0;
        GENETIC_TSP.GenomeTSP fitnessGenome = null;
        for (GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
            genome.fitnessTSP();
            if (genome.getFitnessTSP() > bestFitness) {
                bestFitness = genome.getFitnessTSP();
                fitnessGenome = genome;
            }
        }
        if (fitnessGenome.getFitnessTSP() > this.bestGenomeTSP.getFitnessTSP()) {
            this.bestGenomeTSP = fitnessGenome;
        }
    }

    public void evaluatePopulationKP() {
        double bestFitness = 0.0;
        Genome fitnessGenome = null;
        for (Genome genome : this.populationKP) {
            genome.fitnessKP();
            if (genome.getFitnessKP() > bestFitness) {
                bestFitness = genome.getFitnessKP();
                fitnessGenome = genome;
            }
        }
        if (fitnessGenome.getFitnessKP() > this.bestGenomeKP.getFitnessKP()) {
            this.bestGenomeKP = fitnessGenome;
        }
    }

    /* public double getAverageFitness() {
        double avg = 0.0;
        for(GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
            avg += genome.getFitnessTSP();
        }
        avg = avg / this.populationTSP.size();
        return avg;
    }
    * */

    public GENETIC_TSP.GenomeTSP getBestGenomeOfCurrentPopulationTSP() {
        double bestFitness = 0.0;
        GENETIC_TSP.GenomeTSP bestGenomeOfCurrentPopulation = null;

        for (GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
            if (genome.getFitnessTSP() > bestFitness) {
                bestFitness = genome.getFitnessTSP();
                bestGenomeOfCurrentPopulation = genome;
            }
        }

        return bestGenomeOfCurrentPopulation;
    }

    public Genome getBestGenomeOfCurrentPopulationKP() {
        double bestFitness = 0.0;
        Genome bestGenomeOfCurrentPopulation = null;
        for (Genome genome : this.populationKP) {
            if (genome.getFitnessKP() > bestFitness) {
                bestFitness = genome.getFitnessKP();
                bestGenomeOfCurrentPopulation = genome;
            }
        }
        return bestGenomeOfCurrentPopulation;
    }

    @Override
    public String toString() {
        if(choice==1) {
            final StringBuilder builder = new StringBuilder();
            System.out.println("Population of size : " + this.getPopulationTSP().size());
            for (final GENETIC_TSP.GenomeTSP genome : this.populationTSP) {
                builder.append(genome.toString()).append(("\n"));
            }
            return builder.toString();

        }
        else {
            final StringBuilder builder = new StringBuilder();
            System.out.println("Population of size : " + this.getPopulationKP().size());
            for (final Genome genome : this.populationKP) {
                builder.append(genome.toString()).append(("\n"));
            }
            return builder.toString();
        }

    }
}
