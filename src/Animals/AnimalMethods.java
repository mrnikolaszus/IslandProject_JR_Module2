package Animals;

import Animals.Herbivores.HerbivoresTypes;
import Animals.Omnivores.OmnivoresTypes;
import Animals.Predators.PredatorsTypes;

import java.util.concurrent.ThreadLocalRandom;

public class AnimalMethods {
    public HerbivoresTypes checkTarget(Predator p, Herbivore h){


       if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Mouse")) {
           return HerbivoresTypes.MOUSE;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Duck")) {
           return HerbivoresTypes.DUCK;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Goat")) {
           return HerbivoresTypes.GOAT;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Rabbit")) {
           return HerbivoresTypes.RABBIT;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Sheep")) {
           return HerbivoresTypes.SHEEP;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Buffalo")) {
           return HerbivoresTypes.BUFFALO;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Deer")) {
            return HerbivoresTypes.DEER;
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getClass().getName().contains("Horse")) {
            return HerbivoresTypes.HORSE;
        }
       else return null;
    }

    public static boolean tryToCatch(Predator p, Herbivore h){
        int random = ThreadLocalRandom.current().nextInt(1, 1000);
        //        System.out.println(p.getWeight());
//        System.out.println(p.getMAX_weight());
//        System.out.println(p.checkHerbivores());
//        System.out.println(random);
        return p.getWeight() > 0 && p.getWeight() < p.getMAX_weight() && p.checkHerbivores() && random > 600;
    }

    public static void consumeHerbivore(Predator p, Herbivore h){

//                            System.out.println("Snake caught mouse = " );
            p.raiseWeight(p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-1).getWeight()); // 0.01
            p.getCell().getHerbivores().remove(p.getCell().getHerbivores().size()-1);
//                            System.out.println("Snake eat mouse");
            if (p.getWeight() > p.getMAX_weight()) {
                p.setWeight(p.getMAX_weight());
            }

    }
}
