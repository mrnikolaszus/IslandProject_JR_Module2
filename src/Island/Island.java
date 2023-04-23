package Island;

import Options.GameOptions;

import java.util.concurrent.ConcurrentHashMap;

public class Island extends ConcurrentHashMap<String,Cell> {
    private  static Island island;
    private volatile static int deadAnimals;
    private volatile static int bornAnimals;
    private volatile static int deadHerbAnimals;
    private volatile static int bornHerbAnimals;
    private volatile static int HerbEaten;
    private volatile static int migratedHerb;
    private volatile static int migratedPred;

    public synchronized static int getHerbEaten() {
        return Island.HerbEaten;
    }

    public synchronized static void incHerbEaten() {
        Island.HerbEaten++;
    }
    public synchronized static int getMigratedHerb() {
        return Island.migratedHerb;
    }

    public synchronized static void incMigratedHerb() {
        Island.migratedHerb++;
    }

    public synchronized static int getMigratedPred() {
        return Island.migratedPred;
    }

    public synchronized static void incMigratedPred() {
        Island.migratedPred++;
    }



    private Island(){};

    public static void newIsland(int x, int y) {
        if (island != null){
            System.out.println("Island Already EXISTS");
        } else {
            island = new Island();
            for (int i = 1; i <= x; i++) {
                for (int j = 1; j <= y; j++) {
                    island.put(i + "*" + j, new Cell(i, j));
                    System.out.println("new Cell: " + i + " " + j + " created");
                }
            }
            System.out.println(island);
        }
    }
    public static synchronized Cell getCell(int x, int y){
        return island.get(x + "*" + y);
    }

    public static Island getIsland() {
        return island;
    }

    public static int getDeadAnimals() {
        return Island.deadAnimals;
    }

    public synchronized static void incDeadAnimals() {
        Island.deadAnimals++;
    }
    public static int getDeadHerbAnimals() {
        return Island.deadHerbAnimals;
    }

    public synchronized static void incDeadHerbAnimals() {
        Island.deadHerbAnimals++;
    }

    public static int getBornAnimals() {
        return Island.bornAnimals;
    }

    public synchronized static void incBornAnimals() {
        Island.bornAnimals++;
    }

    public static int getBornHerbAnimals() {
        return Island.bornHerbAnimals;
    }

    public synchronized static void incBornHerbAnimals() {
        Island.bornHerbAnimals++;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 1;
         for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {

                result.append(island.get(i + "*" + j));
                 result.append("\n");
                count ++;
            }
        }
         return result.toString();
    }
}
