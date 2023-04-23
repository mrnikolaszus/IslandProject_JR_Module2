package Threads;

import Island.Cell;
import Options.GameOptions;

import java.util.concurrent.*;

public class OmnivoresCycle implements Runnable {
    private Cell cell;
    public OmnivoresCycle(Cell cell) {
        this.cell = cell;
    }


    @Override
    public void run() {

        cell.testOmni();
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
