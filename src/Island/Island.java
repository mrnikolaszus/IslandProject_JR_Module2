package Island;

import Options.GameOptions;

import java.util.concurrent.ConcurrentHashMap;

public class Island extends ConcurrentHashMap<String,Cell> {       //TODO узнать нужны ли методв синк если уже пакет канкарент
    private  static Island island;

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
        int count = 1;
         for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                if ( count ==3 ) { result.append("\n");}

                result.append(island.get(i + "*" + j));
                if ( count > 2 && count%2 ==0 ) { result.append("\n");}
                count ++;
            }
        }
         return result.toString();
    }
}
