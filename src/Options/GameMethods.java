package Options;

import Animals.Predators.PredatorsTypes;
import Island.*;
import Threads.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameMethods {

    public static void initializeGame(int x, int y) {
        GameOptions.setSizeX(x);
        GameOptions.setSizeY(y);
        GameOptions.setCyclicBarrier();
        newIsland();
        PredatorsTypes.init();
    }

    public static void startSimulation() {
        GameOptions.setOnLine(true);
        int weekCounter = 0;
        long totalWeeks = 1L;
        ExecutorService service = Executors.newFixedThreadPool(GameOptions.getSizeX() * GameOptions.getSizeY());
        while (GameOptions.isOnLine()) {
            Island.getIsland().forEach(
                    (key, value)
                            -> {
                        service.execute(new NatureCycle(value));

                    });

            Island.getIsland().forEach(
                    (key, value)
                            -> {
                        service.execute(new HerbivoresCycle(value));

                    });

            Island.getIsland().forEach(
                    (key, value)
                            -> {
                        service.execute(new OmnivoresCycle(value));

                    });

            Island.getIsland().forEach(
                    (key, value)
                            -> {
                        service.execute(new PredatorsCycle(value));

                    });
            Island.getIsland().forEach(
                    (key, value)
                            -> {
                        service.execute(new Logging(value));

                    });




            do {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("still logging");
            } while (GameOptions.isIsCycleReady());         //TODO awefull join method???

            weekCounter++;

            if (weekCounter > 7) {
                totalWeeks++;
                GameMethods.oneWeekLog((int)totalWeeks);
                weekCounter = 0;

                if (totalWeeks % 4 == 0) {
                    GameMethods.oneMonthLog();
                }
            }
        }
        service.shutdown();   //TODO Разбораться как не вырубать и не создавать заново Пул

        try {
            service.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void newIsland() {

        Island.newIsland(GameOptions.getSizeX(), GameOptions.getSizeY());
    }


    public static void oneWeekLog(int totalWeeks){
        System.out.println("___________________________________");
        System.out.println("one week has passed");
//        System.out.println(Island.getIsland());
        System.out.println("New Week Started");
        System.out.println(totalWeeks + " weeks passed");
    }
    public static void oneMonthLog(){
        System.out.println("One Month Report:");
        Cell.totalMouseCount();
        Cell.totalSnakesCount();
        Cell.totalSheepCount();
        Cell.totalDuckCount();
        Cell.totalEagleCount();
        Cell.totalRabbitCount();
        Cell.totalGoatCount();
        Cell.totalDeerCount();
        Cell.totalHorseCount();
        Cell.totalBuffaloCount();
        Cell.totalFoxCount();
        Cell.totalWolfCount();
        Cell.totalBearCount();
    }

}

