package GENETIC_TSP;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class FramePoints extends JFrame {
    Population pop ;
    LineDemo ln ;

    private XYSeriesCollection  dataset;
    private JFreeChart chart;

    private XYSeries bestFitnessSeries;
    private XYSeries bestSoFarSeries;
    public FramePoints(Population population){
        this.pop = population ;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.8);
        int height = (int) (screenSize.getHeight() * 0.8);
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Visualization best path");

        this.add(BorderLayout.CENTER, ln = new LineDemo(pop)) ;
        createChartPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public LineDemo getLinedem(){
        return ln ;
    }

    private void createChartPanel(){

        bestFitnessSeries = new XYSeries("Best Fitness");
        bestSoFarSeries = new XYSeries("Best So Far");

        dataset = new XYSeriesCollection();
        dataset.addSeries(bestFitnessSeries);
        dataset.addSeries(bestSoFarSeries);

        NumberAxis xAxis = new NumberAxis("Generation");
        NumberAxis yAxis = new NumberAxis("Fitness");

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

        chart = new JFreeChart("Fitness Over Time", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartPanel chartPanel = new ChartPanel(chart);

        add(BorderLayout.NORTH,chartPanel) ;

    }

    public void updateChart(double bestFitness, double bestSoFarFitness, int generation) {
        bestFitnessSeries.add(generation, bestFitness);
        bestSoFarSeries.add(generation, bestSoFarFitness);

        // Notify the chart that the dataset has been updated
        bestFitnessSeries.fireSeriesChanged();
        bestSoFarSeries.fireSeriesChanged();
    }
}
