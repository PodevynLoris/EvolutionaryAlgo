package GENETIC_TSP;

import java.util.HashMap;
import java.util.Map;

public class KPSimulation {


    // ATTENTION RUN CA SEULEMENT SI CHOICE == 0 , pcq 0 c'est pour le KP
    private static final int NUM_TRIALS = 100;
    private Map<String, Integer> results = new HashMap<>();

    public KPSimulation() {
    }

    public void runMultipleTrials(int minPrC, int maxPrC, int minPrM, int maxPrM, int step) {
        for (int prc = minPrC; prc <= maxPrC; prc += step) {
            for (int prm = minPrM; prm <= maxPrM; prm += step) {
                int totalGenerations = 0;
                for (int trial = 0; trial < NUM_TRIALS; trial++) {
                    totalGenerations += runSingleTrial(prc, prm);
                }
                int averageGenerations = totalGenerations / NUM_TRIALS;
                String key = "PrC: " + prc + ", PrM: " + prm;
                results.put(key, averageGenerations);
                System.out.println(key + " - Average generations: " + averageGenerations);
            }
        }
    }


    public int runSingleTrial(int prc, int prm) {
        Population population = new Population();
        int generation = 0;
        double bestFitness = 0.0;
        int targetFitness = 1025;

        while (bestFitness < targetFitness) {
            population.evaluatePopulationKP();
            population.generateNewPopulationKP(prc, prm);
            bestFitness = population.getBestGenomeKP().getFitnessKP();
            generation++;
        }

        return generation;
    }

    public static void main(String[] args) {
        KPSimulation simulation = new KPSimulation();
        // Run experiments with varying parameters
        simulation.runMultipleTrials(5, 80, 5, 40, 5);
    }
}
