package Options;

import Island.*;
import Threads.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameMethods {

    public static void initializeGame(int x, int y){
        GameOptions.setSizeX(x);
        GameOptions.setSizeY(y);
        GameOptions.setCyclicBarrier();
        newIsland();
    }

    private static void  newIsland(){

        Island.newIsland(GameOptions.getSizeX(),GameOptions.getSizeY());}

    public static void startSimulation() throws InterruptedException {
        GameOptions.setOnLine(true);
        while (GameOptions.isOnLine()){
            ExecutorService service = Executors.newFixedThreadPool(GameOptions.getSizeX()*GameOptions.getSizeY());
            System.out.println("Good Morinig");
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




            service.shutdown();   //TODO Разбораться как не вырубать и не создавать заново Пул
            service.awaitTermination(3, TimeUnit.SECONDS);
            System.out.println("Good night");
            System.out.println(Island.getIsland());
            System.out.println("ZzZzZzZ");
        }
    }
}
