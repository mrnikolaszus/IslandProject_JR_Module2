import Island.Island;
import Options.GameMethods;
import Options.GameOptions;
import Threads.*;

import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        GameMethods.initializeGame(5,5);
        try {
            GameMethods.startSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}