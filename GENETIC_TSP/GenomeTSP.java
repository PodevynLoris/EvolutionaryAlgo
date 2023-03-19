package GENETIC_TSP;

import java.util.*;

public class GenomeTSP {

    private  List<GENETIC_TSP.GeneTSP> genome;

    public static int N = 20;

    private double fitnessTSP;

    private GeneTSP [] ordered ;

    public GenomeTSP() {
        this.genome = new ArrayList<>(N);
        this.fitnessTSP = 0.0;
        // fillOrdered();
    }

    public GenomeTSP(List<GENETIC_TSP.GeneTSP> genome) {
        this.genome = genome;
        fillOrdered();
        fitnessTSP();
    }

    public List<GENETIC_TSP.GeneTSP> getGenome() {
        return genome;
    }

    public double getFitnessTSP() {
        return fitnessTSP;
    }

    static GenomeTSP createGenome() {
        Random random = new Random();
        List<GENETIC_TSP.GeneTSP> list = new ArrayList<>(N);
        for(int i = 0; i < N; i++) {
            int ranX = random.nextInt(GENETIC_TSP.GeneTSP.DIMENSIONS);
            int ranY = random.nextInt(GENETIC_TSP.GeneTSP.DIMENSIONS);
            list.add(new GENETIC_TSP.GeneTSP(ranX,ranY));
        }
        GenomeTSP genome = new GenomeTSP(list);
        return genome;
    }

    static GenomeTSP createGenomeCircle() {
        Random random = new Random();
        int centerX = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int centerY = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int radius = GENETIC_TSP.GeneTSP.DIMENSIONS/4;
        List<GENETIC_TSP.GeneTSP> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            int ranX = (int) (centerX + radius * Math.cos(angle));
            int ranY = (int) (centerY + radius * Math.sin(angle));

            //  int ranX = (int) (centerX + radius * Math.cos(angle))*10 + 400;
            //  int ranY = (int) (centerY + radius * Math.sin(angle))*10 + 100;
            GeneTSP geneTSP = new GeneTSP(ranX,ranY) ;

            while (checkDuplicate(geneTSP, list))
                geneTSP = createRandomGene() ;



            geneTSP.setLabel(i);
            list.add(geneTSP);
        }
        GenomeTSP genome = new GenomeTSP(list);
        return genome;
    }

    public void fitnessTSP() {
        double fitness = 0.0;
        for(int i=0; i<this.genome.size()-1;i++) {
            fitness += GENETIC_TSP.Utils.euclideanDistance(this.genome.get(i),this.genome.get(i+1));
        }
        fitness += GENETIC_TSP.Utils.euclideanDistance(this.genome.get(this.getGenome().size()-1),this.getGenome().get(0));
        this.fitnessTSP = 1/fitness;
    }

    public void mutateTSP() {
        Random random = new Random();
        int index1 = random.nextInt(N);
        int index2 = random.nextInt(N);
        while(index1 == index2) {
            index2 = random.nextInt(N);
        }
        GENETIC_TSP.GeneTSP gene1 = this.genome.get(index1);
        GENETIC_TSP.GeneTSP gene2 = this.genome.get(index2);
        this.genome.set(index1,gene2);
        this.genome.set(index2,gene1);
    }

    public int getGeneIndex(List<GENETIC_TSP.GeneTSP> list, GENETIC_TSP.GeneTSP gene) {
        for(int i=0; i<list.size();i++) {
            if(list.get(i).equals(gene)) {
                return i;
            }
        }
        return -1;
    }

   /*public List<GenomeTSP> crossoverTSP(GenomeTSP partner)
    {
        List<GENETIC_TSP.GeneTSP> child1 = new ArrayList<>(N);
        List<GENETIC_TSP.GeneTSP> child2 = new ArrayList<>(N);
        Random random = new Random();
        for(int i=0; i<N;i++) {
            child1.add(this.genome.get(i));
            child2.add(partner.getGenome().get(i));
        }
        System.out.println("CHILD 1"+child1);
        System.out.println("CHILD 2"+child2);
        System.out.println(" ");
        int crossOverPoint = random.nextInt(N);
        for(int i=0; i<crossOverPoint;i++) {
            GENETIC_TSP.GeneTSP gene = partner.getGenome().get(i);
            int index = getGeneIndex(child1, gene);
            child1.set(index,child1.get(i));
            child1.set(i,gene);

            gene = this.genome.get(i);
            index = getGeneIndex(child2, gene);
            child2.set(index,child2.get(i));
            child2.set(i,gene);
        }
        List<GenomeTSP> offspring = new ArrayList<>();
        offspring.add(new GenomeTSP(child1));
        offspring.add(new GenomeTSP(child2));
        return offspring;
    }*/

    public List<GenomeTSP> crossoverTSP(GenomeTSP partner){


        List<GENETIC_TSP.GeneTSP> child1 = new ArrayList<>(N);
        List<GENETIC_TSP.GeneTSP> child2 = new ArrayList<>(N);

        Random random = new Random();

        for(int i=0; i<N;i++) {
            child1.add(this.genome.get(i));
            child2.add(partner.getGenome().get(i));
        }



        int r1 = random.nextInt(N);
        int r2 = random.nextInt(N) ;

        while(r1 == r2){
            r1 = random.nextInt(N);
        }

        if(r1>r2){
            int temp = r1  ;
            r1 = r2 ;
            r2 = temp ;
        }

        int sizeOff = r2-r1 ;
        List<GENETIC_TSP.GeneTSP> off1 = new ArrayList<>(sizeOff);
        List<GENETIC_TSP.GeneTSP> off2 = new ArrayList<>(sizeOff);

        for (int i = r1; i < r2 ; i++) {
            off1.add(child1.get(i));
            off2.add(child2.get(i));
        }


        int j = 0 ;
        for (int i = r1; i < r2 ; i++) {
            //System.out.println(i);
            child1.set(i, off2.get(j));
            child2.set(i, off1.get(j));
            j++ ;
        }


        for (int i = 0; i < N; i++) {
            for (int k = i+1; k < N ; k++) {
                if(child1.get(i)!=null) {
                    if ((child1.get(i).getLabel() == child1.get(k).getLabel())) {
                        // System.out.print("yes");
                        child1.set(i, null);
                    }
                }
                if(child2.get(i)!=null) {
                    if ((child2.get(i).getLabel() == child2.get(k).getLabel())) {
                        child2.set(i, null);
                    }
                }
            }
        }


        boolean [] labelChild1 = new boolean[N] ;
        boolean [] labelChild2 = new boolean[N] ;


        for (int i = 0; i < N; i++) {
            if(child1.get(i)!=null) {
                int lab = child1.get(i).getLabel();
                labelChild1[lab] = true;
            }

            if(child2.get(i)!=null) {
                int  lab = child2.get(i).getLabel();
                labelChild2[lab] = true;
            }
        }


        for (int i = 0; i < N; i++) {
            if(child1.get(i)==null){
                for (int k = 0; k < N; k++) {
                    if(!labelChild1[k]){
                        child1.set(i, ordered[k]) ;
                        labelChild1[k]=true ;
                        break;
                    }

                }
            }


            if(child2.get(i)==null){
                for (int k = 0; k < N; k++) {
                    if(!labelChild2[k]){
                        child2.set(i, ordered[k]);
                        labelChild2[k]=true ;
                        break;
                    }
                }
            }
        }


        List<GenomeTSP> offspring = new ArrayList<>();

        GenomeTSP a = new GenomeTSP(child1) ;
        GenomeTSP b = new GenomeTSP(child2) ;

        offspring.add(a);
        offspring.add(b);

        return offspring;

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(final GENETIC_TSP.GeneTSP gene : this.genome) {
            builder.append(gene.toString()).append((" "));
        }
        builder.append("Fitness score: ").append(this.fitnessTSP);
        return builder.toString();
    }

    public int getN(){
        return  N ;
    }

    public void fillOrdered(){
        ordered = new GeneTSP[N] ;
        for (int i = 0; i < N; i++) {
            //  ordered.add(partner.getGenome().get(i).getLabel(), partner.getGenome().get(i));
            ordered[genome.get(i).getLabel()] = genome.get(i) ;
        }
    }

    private static boolean checkDuplicate(GeneTSP gene, List<GeneTSP> list) {

        boolean check = false ;

        for (int i = 0; i < list.size(); i++) {
            if (gene.getX() == list.get(i).getX() && gene.getY() == list.get(i).getY()) {
                check = true;
                break;
            }
        }

        return check ;


    }

    private static GeneTSP createRandomGene(){

        Random random = new Random();
        int centerX = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int centerY = GENETIC_TSP.GeneTSP.DIMENSIONS/2;
        int radius = GENETIC_TSP.GeneTSP.DIMENSIONS/4;
        double angle = random.nextDouble() * 2 * Math.PI;
        int ranX = (int) (centerX + radius * Math.cos(angle));
        int ranY = (int) (centerY + radius * Math.sin(angle));

        return new GeneTSP(ranX,ranY);

    }


}
