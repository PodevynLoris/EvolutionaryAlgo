package GENETIC_TSP;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class FitnessChartSwing extends JFrame {

    private XYSeries bestFitnessSeries;
    private XYSeries bestSoFarSeries;

    public FitnessChartSwing() {
        setTitle("TSP Genetic Algorithm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bestFitnessSeries = new XYSeries("Best Fitness");
        bestSoFarSeries = new XYSeries("Best So Far");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(bestFitnessSeries);
        dataset.addSeries(bestSoFarSeries);

        NumberAxis xAxis = new NumberAxis("Generation");
        NumberAxis yAxis = new NumberAxis("Fitness");

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

        JFreeChart chart = new JFreeChart("Fitness Over Time", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartPanel chartPanel = new ChartPanel(chart);

        setContentPane(chartPanel);
        setVisible(true);
    }

    public void updateChart(double bestFitness, double bestSoFarFitness, int generation) {
        bestFitnessSeries.add(generation, bestFitness);
        bestSoFarSeries.add(generation, bestSoFarFitness);

        // Notify the chart that the dataset has been updated
        bestFitnessSeries.fireSeriesChanged();
        bestSoFarSeries.fireSeriesChanged();
    }
}
