
import Animals.Predators.PredatorsTypes;

import Options.GameMethods;


public class Main {
    public static void main(String[] args) {
        GameMethods.initializeGame(20,100);
        PredatorsTypes.init();
        GameMethods.startSimulation();






        /* TESTING */

        //        Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.2, 1));
//        Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.2, 1));
//        Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.2, 1));
//        Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 0.2, 1));
//        Island.getCell(2, 1).getPredators().add(new Snake(2, 1, 5, 1));
//        System.out.println(Island.getCell(2,1).getPredators().get(0));
//        Island.getCell(2,1).getPredators().indexOf(Island.getCell(2,1).getPredators().get(0));
//        Mouse mouse = new Mouse(3, 3, 2D, 3);
//        System.out.println(mouse.getClass());


//        GameMethods.initializeGame(14,14);


//        System.out.println(PredatorsTypes.WOLF.chance("goat"));
//        AnimalMethods.newMysteriousMouse(Island.getCell(2, 1));
//        System.out.println(Island.getCell(2, 1).getHerbivores().add(new Mouse(2, 1, 20, 3)));
//       System.out.println(Island.getCell(2, 1).getHerbivores().size());
//        System.out.println(Island.getCell(2, 1).getHerbivores().get(0));
//
//        List<String> a = Island.getCell(2, 1).getHerbivores().stream()
//                .map(herbivore -> herbivore.getClass().getName())
//                .toList()
//                .stream()
//                .filter(s -> s.contains("Sheep"))
//                .toList();
//
//        System.out.println(a);
//        System.out.println(a.size());

    }

}