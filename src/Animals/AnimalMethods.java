package Animals;

import Animals.Herbivores.*;
import Animals.Omnivores.OmnivoresTypes;
import Animals.Predators.Eagle;
import Animals.Predators.PredatorsTypes;
import Animals.Predators.Snake;
import Island.Cell;
import Options.GameMethods;

import java.util.concurrent.ThreadLocalRandom;

public class AnimalMethods {
    public static String checkTarget(Predator p, Herbivore h){

       if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Mouse")) {
           return "Mouse";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Duck")) {
           return "Duck";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Goat")) {
           return "Goat";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Rabbit")) {
           return "Rabbit";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Sheep")) {
           return "Sheep";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Buffalo")) {
           return "Buffalo";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Deer")) {
            return "Deer";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Horse")) {
            return "Horse";
        }
       else return " ";
    }

    public static boolean tryToCatch(Predator p, Herbivore h){
       double chance = p.type.chance(checkTarget(p,h));
//        System.out.println(p.type);
//        System.out.println(chance*1000);
        int random = ThreadLocalRandom.current().nextInt(1, 1000);
        if(p.getWeight() < 0 || p.getWeight() > p.getMAX_weight() || !p.checkHerbivores()){
//            System.out.println((p.getWeight() > 0 )+ " "  +(p.getWeight() < p.getMAX_weight()) + " "  +p.checkHerbivores() );
//            System.out.println(" exit in checking");
            return false;
        }
//        System.out.println( random+" < " + (chance*1000) + " if true goNext");
        return random < (chance*1000);
    }

    public static void consumeHerbivore(Predator p, Herbivore h){

//                           System.out.println("predator got herbivore = " );
            p.raiseWeight(p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getWeight()); // 0.01
            p.getCell().getHerbivores().remove(p.getCell().getHerbivores().size()-1);
//                           System.out.println("predator" + p.getClass().getName() + " eat herbivore");
            if (p.getWeight() > p.getMAX_weight()) {
                p.setWeight(p.getMAX_weight());
            }

    }

    public static void newMysteriousMouse(Cell c){
        c.getHerbivores().add(new Mouse(c.getPosX(), c.getPosY(), 0.2, 1));
    }
    public static void newMysteriousRabbit(Cell c) {
        c.getHerbivores().add(new Rabbit(c.getPosX(), c.getPosY(), 0.8, 2));
    }
    public static void newMysteriousSheep(Cell c) {
        c.getHerbivores().add(new Sheep(c.getPosX(), c.getPosY(), 23, 3));
    }
    public static void newMysteriousDuck(Cell c) {
        c.getHerbivores().add(new Duck(c.getPosX(), c.getPosY(), 0.35, 3));
    }

    public static void newMysteriousSnake(Cell c) {
        c.getPredators().add(new Snake(c.getPosX(), c.getPosY(), 5, 3));
    }
    public static void newMysteriousEagle(Cell c) {
        c.getPredators().add(new Eagle(c.getPosX(), c.getPosY(), 5, 3));
    }
    public static void newMysteriousGoat(Cell c) {
        c.getHerbivores().add(new Goat(c.getPosX(), c.getPosY(), 23, 3));
    }
    private void newMysteriousDeer(Cell c) {
        c.getHerbivores().add(new Deer(c.getPosX(), c.getPosY(), 80, 4));
    }

}
