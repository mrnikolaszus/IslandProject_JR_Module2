package Island;

import Options.GameOptions;

import java.util.concurrent.ConcurrentHashMap;

public class Island extends ConcurrentHashMap<String,Cell> {       //TODO узнать нужны ли методв синк если уже пакет канкарент
    private volatile static Island island;

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
         for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {

                result.append(island.get(i + "*" + j));
            }
            result.append("\n");
        }
         return result.toString();
    }
}
