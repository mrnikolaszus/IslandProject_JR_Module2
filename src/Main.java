import Animals.Herbivores.Mouse;
import Animals.Predators.PredatorsTypes;
import Island.Island;
import Options.GameMethods;
import Options.GameOptions;
import Threads.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        GameMethods.initializeGame(14,14);
        GameMethods.startSimulation();
//        Mouse mouse = new Mouse(3, 3, 2D, 3);
//        System.out.println(mouse.getClass());


//        GameMethods.initializeGame(14,14);
//        PredatorsTypes.init();
//        System.out.println(PredatorsTypes.WOLF.chance("goat"));
//        System.out.println(Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.02, 1)));
//        System.out.println(Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.02, 1)));
//        System.out.println(Island.getCell(2, 1).getHerbivores().size());
//
//
//        System.out.println(Island.getCell(2, 1).getHerbivores().get(1));
//
//        List<String> a = Island.getCell(2, 1).getHerbivores().stream()
//                .map(herbivore -> herbivore.getClass().getName())
//                .toList()
//                .stream()
//                .filter(s -> s.contains("Mouse"))
//                .toList();
//
//        System.out.println(a);

    }

}