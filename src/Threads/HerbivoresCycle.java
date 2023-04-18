package Threads;

import Animals.Herbivore;
import Island.*;
import Options.GameOptions;

import java.util.concurrent.*;

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
            e.printStackTrace();
        }
        cell.getHerbivores().forEach(Herbivore::lifeCycle);
        try {
            GameOptions.getCyclicBarrier().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    }

