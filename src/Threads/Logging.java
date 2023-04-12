package Threads;

import Island.Cell;
import Options.GameMethods;
import Options.GameOptions;

import java.util.concurrent.*;

public class Logging implements Runnable {
    private Cell cell;
    public Logging(Cell cell) {
        this.cell = cell;
    }

    int random = ThreadLocalRandom.current().nextInt(1, 50);
    @Override
    public void run() {
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cell.testLog();
       GameOptions.setIsCycleReady(true);
        try {
            GameOptions.getCyclicBarrier().await(3, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }


    }
}
