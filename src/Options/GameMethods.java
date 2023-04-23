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


            do {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("still logging");
            } while (GameOptions.isIsCycleReady());

            weekCounter++;

            if (weekCounter > 7) {
                totalWeeks++;
                Logging2 log2 = new Logging2((int)totalWeeks);
                log2.start();
                try {
                    log2.join(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                weekCounter = 0;

                if (totalWeeks % 4 == 0) {
                    Logging log = new Logging();
                    log.start();
                    try {
                        log.join(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        service.shutdown();

        try {
            service.awaitTermination(30, TimeUnit.SECONDS);  //TODO?
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void newIsland() {

        Island.newIsland(GameOptions.getSizeX(), GameOptions.getSizeY());
    }





}

