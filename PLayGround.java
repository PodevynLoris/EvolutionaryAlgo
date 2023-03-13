public class PLayGround {

    public static void main(String[] args) {
        Population p = new Population();
        int numGenerations = 100;
        long startTime = System.currentTimeMillis();
           // long timeLimit = 60000; // 1 minute time limit
        //System.out.println(p);
        System.out.println(p.getAverageFitness());

        for (int i = 0; i < numGenerations; i++) {
            p.update();
            //System.out.println(p);
            System.out.println(p.getAverageFitness());

              //  if (System.currentTimeMillis() - startTime >= timeLimit) {
               //     break; // terminate if time limit exceeded
              //  }
        }

            // continue with any post-processing or analysis on the final population p
    }

}
