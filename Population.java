import java.util.*;

public class Population {

    public static final int M = 20; // M = Population length
    public  List<GenomeTSP> population;


    public Population() {
        this.population = initialiseTSPPOP();
    }
    // TODO BE CAREFULL BECAUSE TWO CONSTRUCTORS, ILL TRY TO USE ONLY THE FIRST IN THE CODE
    public Population(List<GenomeTSP> population) {
        this.population = population;
    }

    public List<GenomeTSP> getPopulation() {
        return population;
    }

    private List<GenomeTSP> initialiseTSPPOP() {
        final List<GenomeTSP> list = new ArrayList<>();
        GenomeTSP genome = GenomeTSP.createGenomeCircle();
        list.add(genome);
        for (int i = 1; i < M; i++) {
            List<GeneTSP> shuffledGenes = new ArrayList<>(genome.getGenome());
            Collections.shuffle(shuffledGenes);
            GenomeTSP newGenome = new GenomeTSP(shuffledGenes);
            list.add(newGenome);
        }
        return list;
    }

    void update() {
        Population popMat = new Population(this.selection());
        this.clearPopulation();
        this.reFillPop(popMat,10,10);
    }


    /**
     * @return Wheel selected copy of the population. The POP-MAT
     */
    List<GenomeTSP> selection() {

        List<GenomeTSP> selected = new ArrayList<>(M);
        List<GenomeTSP> sorted = this.order();
        int quarterSize = M/4;
        List<GenomeTSP> first = sorted.subList(0, quarterSize);
        List<GenomeTSP> second = sorted.subList(quarterSize, 2 * quarterSize);
        List<GenomeTSP> third = sorted.subList(2 * quarterSize, 3 * quarterSize);
        List<GenomeTSP> fourth = sorted.subList(3 * quarterSize, sorted.size());

       // System.out.println("Sorted : " + sorted);
       // System.out.println("First : "+first);
       // System.out.println("Second : "+second);
       // System.out.println("Third : "+third);
       // System.out.println("Fourth : "+fourth);
        Random random  = new Random();
        for (int i = 0; i < M; i++) {
            int roulette = random.nextInt(401); // generate random integer between 0 and 400
            int index = random.nextInt(quarterSize); // generate random index within the sublist
            if (roulette >= 200 && roulette < 400) {
                selected.add(first.get(index));
            } else if (roulette >= 100 && roulette < 200) {
                selected.add(second.get(index));
            } else if (roulette >= 25 && roulette < 100) {
                selected.add(third.get(index));
            } else {
                selected.add(fourth.get(index));
            }
        }
        return  selected;
    }




    List<GenomeTSP> applyCrossOverMutation(int PRc, int PRm) {
        Random random = new Random();
        // For the moment I will just cross-Over 1 with 2, 3 with 4 ... M-1 with M
        //TODO MORNING TEST THIS FORLOOP TO SEE IF CORRECT BOUND
        List<GenomeTSP> crossed = new ArrayList<>(M);
        for(int i = 0; i<M-1; i+=2) {
            int ranC = random.nextInt(100);
            if(ranC<PRc) {
                System.out.println(this.population.get(i));
                System.out.println(this.population.get(i+1));
                GenomeTSP[] offSprings = this.population.get(i).crossOverTSP(this.population.get(i+1));
                crossed.add(offSprings[0]);
                crossed.add(offSprings[1]);
                //System.out.println("Crossover");;
            }
            else {
                crossed.add(this.population.get(i));
                crossed.add(this.population.get(i+1));
            }
        }

        for(GenomeTSP genome : crossed) {

            int ranM = random.nextInt(100);
            if(ranM<PRm) {
                genome.mutateTSP();
                System.out.println("Mutation");

            }
        }
        this.clearPopulation(); // TODO NOT SURE IF I HAVE TO ADD THIS LINE OR NOT
        return crossed;
    }


    void reFillPop(Population popMat, int PRc, int PRm) {
        this.population.addAll(popMat.applyCrossOverMutation(PRc,PRm));
    }





    // Utils
    public void clearPopulation() {
        this.population.clear();
    }


    void sortPop() {
        this.population.sort(Comparator.comparingDouble(GenomeTSP::fitnessTSP));
    }

    public List<GenomeTSP> order() {
        List<GenomeTSP> sortedPopulation = new ArrayList<>(this.population);
        sortedPopulation.sort(Comparator.comparingDouble(GenomeTSP::fitnessTSP));
        return sortedPopulation;
    }

    double getAverageFitness() {
        double avg = 0.0;
        for(GenomeTSP genome : this.population) {
            avg+= genome.fitnessTSP();
        }
        avg = avg / this.population.size();
        return avg;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
            System.out.println("Population of size : " + this.getPopulation().size());
            for (final GenomeTSP genome : this.population) {
                builder.append(genome.toString()).append(("\n"));
        }
        return builder.toString();
    }








}
