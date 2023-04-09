package Threads;

import Island.*;
import Options.GameOptions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class HerbivoresCycle implements Runnable {
    private Cell cell;
    public HerbivoresCycle(Cell cell) {
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
        cell.testHerb();
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    }

