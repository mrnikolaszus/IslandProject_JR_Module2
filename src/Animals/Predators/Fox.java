package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Fox:
8kg max weight
0.4kg lose weight per day


 */
public class Fox extends Predator {
    public static double MAX_WEIGHT  = 8D;
    public static double LOSE_WEIGHT_PER_DAY  = 0.4D;
    public static double MAX_RAISE_WEIGHT = 2.5D;
    public static int TRIES_TO_CATCH_FOOD = 35;
    public static int SPEED =2;
    public static double WEIGHT_AT_START = 3;
    public static int MAX_CELL_COUNT = 30;


    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }

    public Fox (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.FOX;
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int Foxs = this.getCell().foxCount().size();
        if (Foxs < MAX_CELL_COUNT) {
            if (Foxs >= 2 && Foxs < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 200) {
                    newCommonFox();
                    Island.incBornAnimals();

                }
            }
            if (Foxs > 10) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 500) {
                    newCommonFox();
                    Island.incBornAnimals();
                    newCommonFox();
                    Island.incBornAnimals();
//
                }
            }
        }
    }

    private void newCommonFox() {
        this.getCell().getPredators().add(new Fox(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }


    @Override
    public synchronized void eat() {
        int attemp =1;
        double daylyPrey = 0D;
        int checkSize = 1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
        while(attemp<TRIES_TO_CATCH_FOOD) {
//                    System.out.println("Fox try to eat herbivore attemp = " + attemp);
            if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                this.move();
                attemp++;
                continue;}
//                        System.out.println("Fox found mouse on cell = " + attemp);
            if((this.getCell().getHerbivores().size() - (checkSize)) < 1 ){
                checkSize=1;
                this.move();
                attemp++;
                continue;}
            if ((this.getCell().getHerbivores().size() - (checkSize)) > 1) {
            if ( AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize)) {
//                            System.out.println(" Fox method logic");
                daylyPrey += this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize).getWeight();
                AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize);
                if (daylyPrey > MAX_RAISE_WEIGHT) { //MAX_RAISE_WEIGHT
                    break;
                }
                attemp++;
                continue;
            }
            }
            if ((this.getCell().getHerbivores().size() - (checkSize +1)) < 1 ){
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
        return "Fox{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}