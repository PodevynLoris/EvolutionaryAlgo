package GENETIC_TSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evolve {

    private static final int PRc =80;

    private static final int PRm = 5;

    private Population population;

    public void startEvolution(long delay) {

        population = new Population();
        System.out.println(population);
        // population.toString() ;
        FramePoints p = new FramePoints(population) ;

        // FitnessChartSwing chart = new FitnessChartSwing(); // Corrected the class name
        Timer timer = new Timer((int) delay, new ActionListener() {
            private int generation = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                double fitness = 0.0;
                population.evaluatePopulation();
                population.generateNewPopulation(PRc, PRm);

                fitness = population.getBestGenomeOfCurrentPopulation().getFitness();
                double bestSoFarFitness = population.getBestGenome().getFitness();

                p.getLinedem().repaint();


                //chart.updateChart(fitness, bestSoFarFitness, generation++);
                p.updateChart(fitness,bestSoFarFitness,generation++);
                //  System.out.println(population);
                System.out.println("--------------------------------NEW ITERATION :"+generation);
                System.out.println(population.getBestGenome());
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public static void main(String[] args) {
        Evolve evolve = new Evolve();
        evolve.startEvolution(5);
    }
}
