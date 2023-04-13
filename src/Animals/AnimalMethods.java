package Animals;

import Animals.Herbivores.*;
import Animals.Omnivores.OmnivoresTypes;
import Animals.Predators.*;
import Island.Cell;
import Options.GameMethods;

import java.util.concurrent.ThreadLocalRandom;

public class AnimalMethods {
    public synchronized static String checkTarget(Predator p, Herbivore h, int a){
        if(a < 1){ return " ";}
        if((p.getCell().getHerbivores().size() - a ) <= 2 ){
            return  " ";}
       if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Mouse")) {
           return "Mouse";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Duck")) {
           return "Duck";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Goat")) {
           return "Goat";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Rabbit")) {
           return "Rabbit";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Sheep")) {
           return "Sheep";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Buffalo")) {
           return "Buffalo";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Deer")) {
            return "Deer";
        }else if( p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getClass().getName().contains("Horse")) {
            return "Horse";
        }
       else return " ";
    }

    public synchronized static boolean tryToCatch(Predator p, Herbivore h, int a){
        if(a < 1){ return false;}
        if((p.getCell().getHerbivores().size() - a ) <= 0 ){
            return  false;}
       double chance = p.type.chance(checkTarget(p,h, a));
//        System.out.println(p.type);
        int random = ThreadLocalRandom.current().nextInt(1, 1000);
//        System.out.println(chance*1000 + " <--- should be less then ---->" + random );
        if(p.getWeight() < 0 || p.getWeight() > p.getMAX_weight() || !p.checkHerbivores()){
//            System.out.println((p.getWeight() > 0 )+ " "  +(p.getWeight() < p.getMAX_weight()) + " "  +p.checkHerbivores() );
//            System.out.println(" FAIL: p.getWeight:" + p.getWeight()  +  "or no herbs: " +p.checkHerbivores() );
            return false;
        }
//        System.out.println( random+" < " + (chance*1000) + " if true goNext");
        return random < (chance*1000);
    }

    public synchronized static void consumeHerbivore(Predator p, Herbivore h, int a){
        if(a < 1){ return;}
        if((p.getCell().getHerbivores().size() - a ) <= 2 ){
            return;}

//                          System.out.println("predator got herbivore = " );
        System.out.println(p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a) + " " + a + " weight: " + p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getWeight());
            p.raiseWeight(p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a).getWeight()); // 0.01
        System.out.println(p.getCell().getHerbivores().get(p.getCell().getHerbivores().size()-a) + " " + a + "remove");
            p.getCell().getHerbivores().remove(p.getCell().getHerbivores().size()-a);
//                          System.out.println("predator" + p.getClass().getName() + " eat herbivore");
            if (p.getWeight() > p.getMAX_weight()) {
                p.setWeight(p.getMAX_weight());
            }

    }

    public static void newMysteriousMouse(Cell c){
        c.getHerbivores().add(new Mouse(c.getPosX(), c.getPosY(), Mouse.WEIGHT_AT_START, Mouse.SPEED));
    }
    public static void newMysteriousRabbit(Cell c) {
        c.getHerbivores().add(new Rabbit(c.getPosX(), c.getPosY(), Rabbit.WEIGHT_AT_START, Rabbit.SPEED));
    }
    public static void newMysteriousSheep(Cell c) {
        c.getHerbivores().add(new Sheep(c.getPosX(), c.getPosY(), Sheep.WEIGHT_AT_START, Sheep.SPEED));
    }
    public static void newMysteriousDuck(Cell c) {
        c.getHerbivores().add(new Duck(c.getPosX(), c.getPosY(), Duck.WEIGHT_AT_START, Duck.SPEED));
    }

    public static void newMysteriousSnake(Cell c) {
        c.getPredators().add(new Snake(c.getPosX(), c.getPosY(), Snake.WEIGHT_AT_START, Snake.SPEED));
    }
    public static void newMysteriousEagle(Cell c) {
        c.getPredators().add(new Eagle(c.getPosX(), c.getPosY(), Eagle.WEIGHT_AT_START, Eagle.SPEED));
    }
    public static void newMysteriousFox(Cell c) {
        c.getPredators().add(new Fox(c.getPosX(), c.getPosY(), Fox.WEIGHT_AT_START, Fox.SPEED));
    }
    public static void newMysteriousWolf(Cell c) {
        c.getPredators().add(new Wolf(c.getPosX(), c.getPosY(), Wolf.WEIGHT_AT_START, Wolf.SPEED));
    }
    public static void newMysteriousBear(Cell c) {
        c.getPredators().add(new Bear(c.getPosX(), c.getPosY(), Bear.WEIGHT_AT_START, Bear.SPEED));
    }
    public static void newMysteriousGoat(Cell c) {
        c.getHerbivores().add(new Goat(c.getPosX(), c.getPosY(), Goat.WEIGHT_AT_START, Goat.SPEED));
    }
    public static void newMysteriousDeer(Cell c) {
        c.getHerbivores().add(new Deer(c.getPosX(), c.getPosY(), Deer.WEIGHT_AT_START, Deer.SPEED));
    }
    public static void newMysteriousHorse(Cell c) {
        c.getHerbivores().add(new Horse(c.getPosX(), c.getPosY(), Horse.WEIGHT_AT_START, Horse.SPEED));
    }
    public static void newMysteriousBuffalo(Cell c) {
        c.getHerbivores().add(new Buffalo(c.getPosX(), c.getPosY(), Buffalo.WEIGHT_AT_START, Buffalo.SPEED));
    }

}
