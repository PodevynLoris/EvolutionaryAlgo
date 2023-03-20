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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class KSframe extends JFrame {

    private int show = 1 ;
    private int  check = 0 ;
    private XYSeriesCollection  dataset;
    private JFreeChart chart;

    private XYSeries bestFitnessSeries;
    private XYSeries bestSoFarSeries;

    private JPanel settingPanel ;

    private  MainFrame frame ;


    private JButton start ;

    private JSlider sliderMutation1 ;

    private JSlider sliderCrossover1 ;

    private JSlider sliderMaxWeight1 ;

    private JLabel mutationLabel1 = new JLabel("Mutation Rate :") ;
    private JLabel crossoverLabel1 = new JLabel("Crossover Rate :");
    private JLabel maxWeightLabel1 = new JLabel("Max Weight :");
    private int valM1 = 5 ;

    private int valC1 ;


    public KSframe(MainFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.8);
        int height = (int) (screenSize.getHeight() * 0.8);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setTitle("KnapStack Problem");
        this.frame = frame ;

        createChartPanel();
        createSettingPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void createStartButton(){
        start = new JButton("Start") ;
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Evolve evolution1 = new Evolve(frame) ;
                evolution1.setChoice(0);
                evolution1.startEvolution(5);

            }
        });
        settingPanel.add(start) ;

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

        add(BorderLayout.CENTER,chartPanel) ;
    }
    private void createSettingPanel(){

        settingPanel = new JPanel() ;
        settingPanel.setBackground(new Color(224, 178, 129));
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.PAGE_AXIS));
        settingPanel.setVisible(true);
        parameter1();
        createStartButton();


        add(BorderLayout.WEST,settingPanel) ;
    }

    private void parameter1(){
        sliderMutation1 = new JSlider(0,10,5) ;
        setSlider(sliderMutation1);
        sliderMutation1.addChangeListener(e -> {
            valM1 = sliderMutation1.getValue();
        });
        sliderCrossover1 = new JSlider(0,10,5) ;
        setSlider(sliderCrossover1);
        sliderCrossover1.addChangeListener(e -> {
            valC1 = sliderCrossover1.getValue() ;
        });

        sliderMaxWeight1 = new JSlider(0,200,5) ;
        setSlider2(sliderMaxWeight1);
        // sliderMaxWeight1.setPaintLabels(false);


        mutationLabel1.setForeground(Color.RED);
        crossoverLabel1.setForeground(Color.RED);
        maxWeightLabel1.setForeground(Color.RED);


        settingPanel.add(mutationLabel1) ;
        settingPanel.add(sliderMutation1) ;
        settingPanel.add(crossoverLabel1) ;
        settingPanel.add(sliderCrossover1) ;
        settingPanel.add(maxWeightLabel1) ;
        settingPanel.add(sliderMaxWeight1) ;
    }

    private void setSlider(JSlider sliderr){
        sliderr.setMajorTickSpacing(10);
        sliderr.setMinorTickSpacing(1);
        sliderr.setPaintTicks(true);
        sliderr.setPaintLabels(true);
        sliderr.setOrientation(JSlider.HORIZONTAL);
        sliderr.setPreferredSize(new Dimension(50, 50));

    }

    private void setSlider2(JSlider slider){
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(10));

// Disable minor ticks
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(0, new JLabel("0"));
        labelTable.put(100, new JLabel("100"));
        slider.setLabelTable(labelTable);
    }



    public void updateChart(double bestFitness, double bestSoFarFitness, int generation) {
        bestFitnessSeries.add(generation, bestFitness);
        bestSoFarSeries.add(generation, bestSoFarFitness);

        // Notify the chart that the dataset has been updated
        bestFitnessSeries.fireSeriesChanged();
        bestSoFarSeries.fireSeriesChanged();
    }
}

