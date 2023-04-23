package Animals.Herbivores;

import Animals.Herbivore;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Sheep:
70kg max weight
5kg lose weight per day


 */
public class Sheep extends Herbivore {
    public static double MAX_WEIGHT  = 70D;
    public static double LOSE_WEIGHT_PER_DAY  = 5D;
    public static double FOOD_PLANTS_SIZE = 15D;
    public static int FOOD_INSECT_SIZE = -1;
    public static double MAX_RAISE_WEIGHT = 15D;
    public static int TRIES_TO_CATCH_FOOD = 10;
    public static int SPEED =3;
    public static double WEIGHT_AT_START = 23;
    public static int MAX_CELL_COUNT = 140;

    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }


    public Sheep(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("sheep's constructor + this.hunger: " + this.hunger );

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
        int sheeps = this.getCell().sheepCount().size();
        if (sheeps < MAX_CELL_COUNT) {
            if (sheeps > 2 && sheeps < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 700) {
//                System.out.println("mouse born: " + mouses);
                    newCommonSheep();
                    Island.incBornHerbAnimals();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
            if (sheeps > 100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 800) {
//                System.out.println("2 mouses born: " + mouses);
                    newCommonSheep();
                    Island.incBornHerbAnimals();
                    newCommonSheep();
                    Island.incBornHerbAnimals();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
        }
    }

    private void newCommonSheep() {
        this.getCell().getHerbivores().add(new Sheep(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }


    @Override
    public void eat() {
        int attemp =1;

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
        return "Sheep{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}