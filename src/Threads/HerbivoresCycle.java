package Threads;

import Animals.Herbivore;
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

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cell.getHerbivores().forEach(Herbivore::lifeCycle);
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    }

