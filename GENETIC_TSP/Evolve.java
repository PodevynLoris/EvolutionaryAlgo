package GENETIC_TSP;

import java.util.TimerTask;
import java.util.Timer;

public class Evolve {

    private static final int PRc = 80;
    private static final int PRm = 5;

    private Population population;

    public void startEvolution(long delay) {

        population = new Population();

        FitnessChartSwing chart = new FitnessChartSwing(); // Corrected the class name
        TimerTask updateTask = new TimerTask() {
            private int generation = 0;
            @Override
            public void run() {
                double fitness = 0.0;
                population.evaluatePopulation();
                population.generateNewPopulation(PRc, PRm);

                fitness = population.getBestGenomeOfCurrentPopulation().getFitness();
                double bestSoFarFitness = population.getBestGenome().getFitness();

                chart.updateChart(fitness, bestSoFarFitness, generation++);
            }
        };
        Timer timer = new Timer();
        timer.schedule(updateTask, 0, delay);
    }

    public static void main(String[] args) {
        Evolve evolve = new Evolve();
        evolve.startEvolution(10);
    }
}
