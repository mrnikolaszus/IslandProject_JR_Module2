package Threads;

import Island.Cell;
import Options.GameOptions;

import java.util.concurrent.*;

public class OmnivoresCycle implements Runnable {
    private Cell cell;
    public OmnivoresCycle(Cell cell) {
        this.cell = cell;
    }

    int random = ThreadLocalRandom.current().nextInt(1, 50);
    @Override
    public void run() {
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cell.testOmni();
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
