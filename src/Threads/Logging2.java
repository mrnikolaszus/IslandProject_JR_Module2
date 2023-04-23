package Threads;

import Island.Cell;
import Island.Island;

public class Logging2 extends Thread {
        int totalWeeks;
        public Logging2(int a) {
            this.totalWeeks = a;
        }

        @Override
    public void run() {


                    System.out.println("___________________________________");
                    System.out.println("one week has passed");
//        System.out.println(Island.getIsland());
                    System.out.println("New Week Started");
                    System.out.println(totalWeeks + " weeks passed");



    }
}
