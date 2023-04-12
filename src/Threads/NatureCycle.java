package Threads;

import Island.Cell;
import Options.GameOptions;

import java.util.concurrent.*;

public class NatureCycle implements Runnable {
    private Cell cell;
    public NatureCycle(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        GameOptions.setIsCycleReady(false);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cell.growing();
        try {
            GameOptions.getCyclicBarrier().await(3, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}

