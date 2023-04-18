package Animals.Herbivores;

import Animals.Herbivore;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Horse:
400kg max weight
20kg lose weight per day


 */
public class Horse extends Herbivore {
    public static double MAX_WEIGHT  = 400D;
    public static double LOSE_WEIGHT_PER_DAY  = 20D;
    public static double FOOD_PLANTS_SIZE = 60D;
    public static int FOOD_INSECT_SIZE = -1;
    public static double MAX_RAISE_WEIGHT = 60D;
    public static int TRIES_TO_CATCH_FOOD = 10;
    public static int SPEED =4;
    public static double WEIGHT_AT_START = 120;
    public static int MAX_CELL_COUNT = 20;
    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }


    public Horse(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("Horse's constructor + this.hunger: " + this.hunger );
    }
    @Override
    public boolean checkPredator() {
        return super.checkPredator();
    }

    @Override
    public boolean checkInsects(){
        return this.getCell().getInsectsCount()>FOOD_INSECT_SIZE;
    }


    @Override
    public boolean checkPlants(){
        return this.getCell().getPlants()>FOOD_PLANTS_SIZE;
    }

    @Override
    public void reproduce() {
        int Horses = this.getCell().horseCount().size();
        if (Horses < MAX_CELL_COUNT) {
            if (Horses > 2 && Horses < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 975) {
                System.out.println("Horse born");
                    newCommonHorse();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
            if (Horses > 100 && Horses < 200) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
                System.out.println("Horse born");
                    newCommonHorse();
                    newCommonHorse();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
        }
    }
    private void newCommonHorse() {
        this.getCell().getHerbivores().add(new Horse(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }
    @Override
    public void eat() {

        int attemp = 1;
        while( attemp < TRIES_TO_CATCH_FOOD ) {
            if (!checkPlants() ) {
//                System.out.println(this + " moves coz no food");
                this.move();
            }
//        System.out.println(this.getWeight() + " health before cycle");
            if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkPlants()) {
//           System.out.println( this + "eating plants");
                if (this.getCell().getPlants() > FOOD_PLANTS_SIZE) {
                    this.getCell().eatPlants(FOOD_PLANTS_SIZE);
                    this.raiseWeight(MAX_RAISE_WEIGHT);  // 0.01
                    if (this.getWeight() > getMAX_weight()) {
                        this.setWeight(getMAX_weight());
                    }
                    break;
                }
            } attemp++;
        }
//        System.out.println(this.getWeight() + " health in the cycle");
    }
    @Override
    public void lifeCycle() {
        super.lifeCycle();
    }
    @Override
    public String toString() {
        return "Horse{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}