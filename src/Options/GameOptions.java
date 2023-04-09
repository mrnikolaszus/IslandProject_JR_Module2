package Options;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class GameOptions {
    static private boolean onLine;

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

    public static void setOnLine(boolean onLine) {
        GameOptions.onLine = onLine;
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
}
