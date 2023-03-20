package GENETIC_TSP;


import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSPSimulation {
    private static final int NUM_TRIALS = 10;
    private Map<String, Integer> results = new HashMap<>();

    public TSPSimulation() {
    }

    public void runMultipleTrials(int[] crossoverValues, int[] mutationValues, int[] populationSizes) {
        for (int prc : crossoverValues) {
            for (int prm : mutationValues) {
                for (int popSize : populationSizes) {
                    int totalGenerations = 0;
                    for (int trial = 0; trial < NUM_TRIALS; trial++) {
                        totalGenerations += runSingleTrial(prc, prm, popSize);
                    }
                    int averageGenerations = totalGenerations / NUM_TRIALS;
                    String key = "PrC: " + prc + ", PrM: " + prm + ", PopSize: " + popSize;
                    results.put(key, averageGenerations);
                    System.out.println(key + " - Average generations: " + averageGenerations);
                }
            }
        }
    }

    public int runSingleTrial(int prc, int prm, int popSize) {
        Evolve evolve = new Evolve(null, 1);

        evolve.setChoice(1);
        Population population = new Population(popSize,true);
        int generation = 0;
        double bestFitness = 0.0;
        double targetFitness = 0.016238657381117947;

        while (bestFitness < targetFitness) {
            population.evaluatePopulationTSP();
            population.generateNewPopulationTSP(prc, prm);
            bestFitness = population.getBestGenomeTSP().getFitnessTSP();
            generation++;
        }

        return generation;
    }

    private void saveResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                writer.write(entry.getKey() + " - Average generations: " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing results to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TSPSimulation simulation = new TSPSimulation();
        // Run experiments with varying parameters
        int[] crossoverValues = {50, 60, 70, 80, 90, 100};
        int[] mutationValues = {5, 10, 15, 20};
        int[] populationSizes = {50, 100, 1000};
        simulation.runMultipleTrials(crossoverValues, mutationValues, populationSizes);
        simulation.saveResultsToFile("tsp_simulation_results.txt");
    }
}
