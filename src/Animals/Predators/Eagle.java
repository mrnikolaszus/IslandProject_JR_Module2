package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Eagle:
6kg max weight
0.3kg lose weight per day


 */
public class Eagle extends Predator {
    public static double MAX_WEIGHT  = 6D;
    public static double LOSE_WEIGHT_PER_DAY  = 0.3D;
    public static double MAX_RAISE_WEIGHT = 1.5D;
    public static int TRIES_TO_CATCH_FOOD = 50;
    public static int SPEED =3;
    public static double WEIGHT_AT_START = 2;
    public static int MAX_CELL_COUNT = 20;


    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }

    public Eagle (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.EAGLE;
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int Eagles = this.getCell().eagleCount().size();
        if (Eagles < MAX_CELL_COUNT) {
            if (Eagles > 2 && Eagles < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
                    newCommonEagle();

                }
            }
            if (Eagles > 10) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 800) {
                    newCommonEagle();
                    newCommonEagle();
//
                }
            }
        }
    }

    private void newCommonEagle() {
        this.getCell().getPredators().add(new Eagle(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }


    @Override
    public synchronized void eat() {
        int attemp =1;
        double daylyPrey = 0D;
        int checkSize = 1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
        while(attemp<TRIES_TO_CATCH_FOOD) {
//                    System.out.println("Eagle try to eat herbivore attemp = " + attemp);
            if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                this.move();
                attemp++;
                continue;}
//                        System.out.println("Eagle found mouse on cell = " + attemp);
            if((this.getCell().getHerbivores().size() - (checkSize)) < 1 ){
                checkSize=1;
                this.move();
                attemp++;
                continue;}
            if ((this.getCell().getHerbivores().size() - (checkSize)) > 0  && AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize)) {
//                            System.out.println(" Eagle method logic");
                daylyPrey += this.getCell().getHerbivores().get(this.getCell().getHerbivores().size()-checkSize).getWeight();
                AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize);
                if(daylyPrey > MAX_RAISE_WEIGHT){ //MAX_RAISE_WEIGHT
                    break;
                }
                attemp++;
                continue;

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
        return "Eagle{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}