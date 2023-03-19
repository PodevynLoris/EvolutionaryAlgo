package GENETIC_TSP;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class LineDemo extends JPanel {



    GenomeTSP genome ;

    GenomeTSP genomeCurrent ;
    Population population ;

    public LineDemo(Population pop){
        this.population = pop ;
        setBackground(new Color(197, 202, 222));

    }

    private void draw(Graphics g) {
        UpdateGenome();
        UpdateGenomeCurrent();

        Graphics2D g2d = (Graphics2D) g;
        int size = genome.getGenome().size() ;

        for (int i = 0; i < genome.getGenome().size()-1; i++) {
            g2d.setColor(Color.BLUE);
            g2d.draw(new Line2D.Double(genome.getGenome().get(i).getX()*12, genome.getGenome().get(i).getY()*12-105, genome.getGenome().get(i+1).getX()*12, genome.getGenome().get(i+1).getY()*12-105));
            g2d.setColor(Color.BLACK);
            g2d.fill(new Ellipse2D.Double(genome.getGenome().get(i).getX()*12-3, genome.getGenome().get(i).getY()*12-105-3, 6,6));


            g2d.setColor(Color.RED);
            g2d.draw(new Line2D.Double(genomeCurrent.getGenome().get(i).getX()*12+700, genomeCurrent.getGenome().get(i).getY()*12-105, genomeCurrent.getGenome().get(i+1).getX()*12+700, genomeCurrent.getGenome().get(i+1).getY()*12-105));
            g2d.setColor(Color.BLACK);
            g2d.fill(new Ellipse2D.Double(genomeCurrent.getGenome().get(i).getX()*12+700-3, genomeCurrent.getGenome().get(i).getY()*12-105-3, 6,6));
        }

        g2d.fill(new Ellipse2D.Double(genome.getGenome().get(size-1).getX()*12-3, genome.getGenome().get(size-1).getY()*12-105-3, 6,6));
        g2d.setColor(Color.BLUE);
        g2d.draw(new Line2D.Double(genome.getGenome().get(size-1).getX()*12, genome.getGenome().get(size-1).getY()*12-105, genome.getGenome().get(0).getX()*12, genome.getGenome().get(0).getY()*12-105));


        g2d.setColor(Color.BLACK);
        g2d.fill(new Ellipse2D.Double(genomeCurrent.getGenome().get(size-1).getX()*12+700-3, genomeCurrent.getGenome().get(size-1).getY()*12-105-3, 6,6));
        g2d.setColor(Color.RED);
        g2d.draw(new Line2D.Double(genomeCurrent.getGenome().get(size-1).getX()*12+700, genomeCurrent.getGenome().get(size-1).getY()*12-105, genomeCurrent.getGenome().get(0).getX()*12+700, genomeCurrent.getGenome().get(0).getY()*12-105));

    }

    public void UpdateGenome(){
        genome = population.getBestGenomeTSP();
    }

    public void UpdateGenomeCurrent(){
        genomeCurrent = population.getBestGenomeOfCurrentPopulationTSP();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}