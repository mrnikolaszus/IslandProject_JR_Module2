package Threads;

import Animals.Herbivore;
import Animals.Predator;
import Island.Cell;
import Options.GameOptions;

import java.util.concurrent.*;

public class PredatorsCycle implements Runnable {
    private Cell cell;
    public PredatorsCycle(Cell cell) {
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
        cell.getPredators().forEach(Predator::lifeCycle);
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
