package GENETIC_TSP;

import java.util.ArrayList;
import java.util.List;

public class TestForCrossOver {

    public static void main(String[] args) {
        Population p = new Population();
        System.out.println(p);
        GenomeTSP parent1 = p.getPopulationTSP().get(0);
        GenomeTSP parent2 = p.getPopulationTSP().get(1);
        List<GenomeTSP> children = new ArrayList<>();
        children = parent1.crossoverTSP(parent2);
        System.out.println(children.get(0));
        System.out.println(children.get(1));
    }

    public List<GenomeTSP> crossoverTSP(GenomeTSP partner) {
        List<GENETIC_TSP.GeneTSP> child1 = new ArrayList<>();
        List<GENETIC_TSP.GeneTSP> child2 = new ArrayList<>();







        return null;
    }

}
