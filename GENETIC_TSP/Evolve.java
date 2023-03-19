package GENETIC_TSP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evolve {

    public static int choice = 0;
    private static final int PRc =80;
    private static final int PRm = 5;


    private Population population;

    public void startEvolution(long delay) {

        if(choice==1) {
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
                    population.evaluatePopulationTSP();
                    population.generateNewPopulationTSP(PRc, PRm);

                    fitness = population.getBestGenomeOfCurrentPopulationTSP().getFitnessTSP();
                    double bestSoFarFitness = population.getBestGenomeTSP().getFitnessTSP();

                    p.getLinedem().repaint();


                    //chart.updateChart(fitness, bestSoFarFitness, generation++);
                    p.updateChart(fitness,bestSoFarFitness,generation++);
                    //  System.out.println(population);
                    System.out.println("--------------------------------NEW ITERATION :"+generation);
                    System.out.println(population.getBestGenomeTSP());
                }
            });
            timer.setRepeats(true);
            timer.start();

        }
        else {

            population = new Population();
            System.out.println(population);
            // population.toString() ;
            FitnessChartSwing f = new FitnessChartSwing();

            // FitnessChartSwing chart = new FitnessChartSwing(); // Corrected the class name
            Timer timer = new Timer((int) delay, new ActionListener() {
                private int generation = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    double fitness = 0.0;
                    population.evaluatePopulationKP();
                    population.generateNewPopulationKP(PRc, PRm);

                    fitness = population.getBestGenomeOfCurrentPopulationKP().getFitnessKP();
                    double bestSoFarFitness = population.getBestGenomeKP().getFitnessKP();




                    //chart.updateChart(fitness, bestSoFarFitness, generation++);

                    //  System.out.println(population);
                    f.updateChart(fitness,bestSoFarFitness,generation++);
                    System.out.println("--------------------------------NEW ITERATION :"+generation);
                    System.out.println(population.getBestGenomeKP());
                }
            });
            timer.setRepeats(true);
            timer.start();

        }

    }

    public static void main(String[] args) {
        Evolve evolve = new Evolve();
        evolve.startEvolution(100);
    }
}
