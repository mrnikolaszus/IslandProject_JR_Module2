package Options;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameOptions {
    public static void setOnLine(boolean onLine) {
        GameOptions.onLine = onLine;
    }

    public static Lock lock = new ReentrantLock();

    static private boolean onLine = true;

    static private boolean isCycleReady;

    static private final int cycle = 50;
    static private int sizeX;

    static private int sizeY;

    static volatile private CyclicBarrier cyclicBarrier;

    public static void setCyclicBarrier() {
        cyclicBarrier = new CyclicBarrier(sizeX*sizeY);
    }

    public static void setSizeX(int sizeX) {
        GameOptions.sizeX = sizeX;
    }

    public static void setSizeY(int sizeY) {
        GameOptions.sizeY = sizeY;
    }

    public synchronized static CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public static boolean isOnLine() {
        return onLine;
    }


    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }
    public static int getCycle() {
        return cycle;
    }

    public static boolean isIsCycleReady() {
        return isCycleReady;
    }

    public static void setIsCycleReady(boolean isCycleReady) {
        GameOptions.isCycleReady = isCycleReady;
    }
}
