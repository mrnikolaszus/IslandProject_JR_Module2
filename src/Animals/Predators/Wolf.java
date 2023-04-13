package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Wolf:
50kg max weight
3kg lose weight per day


 */
public class Wolf extends Predator {
    public static double MAX_WEIGHT  = 50D;
    public static double LOSE_WEIGHT_PER_DAY  = 3D;
    public static double MAX_RAISE_WEIGHT = 8D;
    public static int TRIES_TO_CATCH_FOOD = 30;
    public static int SPEED =3;
    public static double WEIGHT_AT_START = 17;
    public static int MAX_CELL_COUNT = 30;


    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }

    public Wolf (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.WOLF;
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int Wolfs = this.getCell().wolfCount().size();
        if (Wolfs < MAX_CELL_COUNT) {
            if (Wolfs > 2 && Wolfs < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
                    newCommonWolf();

                }
            }
            if (Wolfs > 10) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 800) {
                    newCommonWolf();
                    newCommonWolf();
//
                }
            }
        }
    }

    private void newCommonWolf() {
        this.getCell().getPredators().add(new Wolf(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }


    @Override
    public void eat() {
        int attemp =1;
        double daylyPrey = 0D;
        int checkSize = 1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
        while(attemp<TRIES_TO_CATCH_FOOD) {
//                    System.out.println("Wolf try to eat herbivore attemp = " + attemp);
            if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                this.move();
                attemp++;
                continue;}
//                        System.out.println("Wolf found mouse on cell = " + attemp);
            if((this.getCell().getHerbivores().size() - (checkSize)) <= 2 ){
                checkSize=1;
                this.move();
                attemp++;
                continue;}
            if ((this.getCell().getHerbivores().size() - (checkSize)) > 0 && AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize)) {
//                            System.out.println(" Wolf method logic");
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
        return "Wolf{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}