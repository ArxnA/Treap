// random number generator bedoone estefade az library ke time ejraye barname ra yek dovom mikonad

import java.util.Random;

public class RandomGenerator {
        private static final int a=1664525;
        private static final int m=(int) Math.pow(2,31);
        private static final int c=1013904223;

        private static int seed= (int)System.currentTimeMillis();

        public static int generateRandom(){
                seed=(a*seed+c)%m;
                return Math.abs(seed%100000);
        }


        public static int randomGenerator(){
                Random random=new Random();
                return random.nextInt(100000);
        }
}
