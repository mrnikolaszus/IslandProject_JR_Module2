package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Bear:
500kg max weight
30kg lose weight per day


 */
public class Bear extends Predator {
    public static double MAX_WEIGHT  = 500D;
    public static double LOSE_WEIGHT_PER_DAY  = 25D;
    public static double MAX_RAISE_WEIGHT = 90D;
    public static int TRIES_TO_CATCH_FOOD = 30;
    public static int SPEED =2;
    public static double WEIGHT_AT_START = 200;
    public static int MAX_CELL_COUNT = 5;


    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }

    public Bear (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.BEAR;
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int Bears = this.getCell().bearCount().size();
        if (Bears < MAX_CELL_COUNT) {
            if (Bears > 2 && Bears < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
                    newCommonBear();

                }
            }
            if (Bears > 10) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 800) {
                    newCommonBear();
                    newCommonBear();
//
                }
            }
        }
    }

    private void newCommonBear() {
        this.getCell().getPredators().add(new Bear(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }


    @Override
    public void eat() {
        int attemp =1;
        double daylyPrey = 0D;
        int checkSize = 1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
        while(attemp<TRIES_TO_CATCH_FOOD) {
//                    System.out.println("Bear try to eat herbivore attemp = " + attemp);
            if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                this.move();
                attemp++;
                continue;}
//                        System.out.println("Bear found mouse on cell = " + attemp);
            if((this.getCell().getHerbivores().size() - (checkSize)) <= 2 ){
                checkSize=1;
                this.move();
                attemp++;
                continue;}
            if ((this.getCell().getHerbivores().size() - (checkSize)) > 0 && AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize)) {
//                            System.out.println(" Bear method logic");
                daylyPrey += this.getCell().getHerbivores().get(this.getCell().getHerbivores().size()-checkSize).getWeight();
                AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize);
                if(daylyPrey > MAX_RAISE_WEIGHT){ //MAX_RAISE_WEIGHT
                    break;
                }
                attemp++;
                continue;

            }
            if ((this.getCell().getHerbivores().size() - (checkSize +1)) <= 1 ){
                checkSize=1;
                this.move();
                attemp++;

            } else { checkSize++;
            }
        }
//        System.out.println(this.getWeight() + " в конце цикла голод змеи");
    }

    @Override
    public void lifeCycle() {
        super.lifeCycle();
    }


    @Override
    public String toString() {
        return "Bear{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}