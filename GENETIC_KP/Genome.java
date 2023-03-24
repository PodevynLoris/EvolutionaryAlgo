package GENETIC_KP;



import GENETIC_TSP.Evolve;
import GENETIC_TSP.GenomeTSP;
import GENETIC_TSP.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static GENETIC_TSP.Evolve.CAPACITY;


public class Genome {
    private final List<GENETIC_KP.Gene> genome;
    public static int N = Stuff.values().length;
    private double fitnessKP;

    public Genome(List<GENETIC_KP.Gene> genome) {
        this.genome = Collections.unmodifiableList(genome);
        this.fitnessKP  = 0.0;
    }

    public Genome() {
        this.genome = new ArrayList<>(N);
        fitnessKP();
    }

    public List<GENETIC_KP.Gene> getGenome() {
        return genome;
    }

    public double getFitnessKP() {
        return fitnessKP;
    }

    public int getCAPACITY() {
        return N;
    }

    public static Genome createGenome() {
        Random random = new Random();
        List<Stuff> stuffList = new ArrayList<>();
        Collections.addAll(stuffList, Stuff.values());

        final List<GENETIC_KP.Gene> genes = new ArrayList<>();

        for (Stuff stuff : stuffList) {
            int ran = random.nextInt(2);
            genes.add(new GENETIC_KP.Gene(stuff, ran));
        }
        return new Genome(genes);
    }


    public void fitnessKP(){
        int weight = 0;
        int usefulness = 0;
        for(GENETIC_KP.Gene gene : this.genome) {
            if(gene.getInside()==1) {
                usefulness += gene.getStuff().getUsefulness();
                weight += gene.getStuff().getWeight();
                if(weight> CAPACITY) {
                    usefulness  = 0;
                }
            }
        }
        this.fitnessKP = usefulness;
    }


    public void mutateKP() {
        Random random = new Random();
        int ran = random.nextInt(this.genome.size());
        this.genome.get(ran).setInside(Utils.flip(this.genome.get(ran).getInside()));
    }



    public List<Genome> SPCrossOverKP(final Genome partner) {

        if(this.genome.size() != partner.getGenome().size()) {
            throw new RuntimeException("The parent genomes are not the same size");
        }
        Random random = new Random();
        int[] parent1Binary = getBinaryArray();
        int[] parent2Binary = partner.getBinaryArray();
        int indexCut = random.nextInt(parent1Binary.length + 1);

        int[][] childrenBinary = swapArrays(parent1Binary, parent2Binary, indexCut);

        Genome firstChild = Genome.createGenome();
        Genome secondChild = Genome.createGenome();
        for(int i = 0; i < firstChild.getGenome().size(); i++) {
            firstChild.getGenome().get(i).setInside(childrenBinary[0][i]);
            secondChild.getGenome().get(i).setInside(childrenBinary[1][i]);
        }

        List<Genome> children = new ArrayList<>();
        children.add(firstChild);
        children.add(secondChild);

        return children;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(final GENETIC_KP.Gene gene : this.genome) {
            builder.append(gene.toString()).append((" "));
        }
        builder.append(" Fitness score : ").append(this.fitnessKP);
        return builder.toString();
    }













    //UTILS
    public int[] getBinaryArray() {
        int[] binaryArray = new int[this.genome.size()];
        for(int i =0; i<this.genome.size(); i++) {
            binaryArray[i] = this.genome.get(i).getInside();
        }
        return binaryArray;
    }
    public int[][] swapArrays(int[]a,int[]b,int cut) {
        int m = 2;
        int n = a.length;
        int[][] children = new int[m][n];
        for(int i=0; i<a.length; i++) {
            if(i<=cut) {
                children[0][i] = a[i];
                children[1][i] = b[i];
            }
            else {
                children[0][i] = b[i];
                children[1][i] = a[i];
            }
        }
        return children;
        //int[] child1Binary = new int[parent1Binary.length];
        //int[] child2Binary = new int[parent1Binary.length];
        /* for(int i=0; i<parent1Binary.length; i++) {
            if(i<=indexCut) {
                child1Binary[i] = parent1Binary[i];
                child2Binary[i] = parent2Binary[i];
            }
            else {
                child1Binary[i] = parent2Binary[i];
                child2Binary[i] = parent1Binary[i];
            }
        }*/
    }

}