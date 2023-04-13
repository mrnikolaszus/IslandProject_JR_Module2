package Animals.Herbivores;

import Animals.Herbivore;
import Island.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Mouse:
0.6kg max weight
0.06kg lose weight per day


 */
public class Mouse extends Herbivore {
    public static double MAX_WEIGHT  = 0.6D;
    public static double LOSE_WEIGHT_PER_DAY  = 0.06D;
    public static double FOOD_PLANTS_SIZE = 1D;
    public static int FOOD_INSECT_SIZE = 1;
    public static double MAX_RAISE_WEIGHT = 0.25D;
    public static int TRIES_TO_CATCH_FOOD = 10;
    public static int SPEED =1;
    public static double WEIGHT_AT_START = 0.2;
    public static int MAX_CELL_COUNT = 500;

    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }


    public Mouse(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("constructor + this.hunger: " + this.hunger );

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
        int mouses = this.getCell().mouseCount().size();
        if(mouses<MAX_CELL_COUNT) {
            if (mouses > 2 && mouses < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 975) {
//                System.out.println("mouse born: " + mouses);
                    newCommonMouse();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
            if (mouses > 100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
//                System.out.println("2 mouses born: " + mouses);
                    newCommonMouse();
                    newCommonMouse();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
                }
            }
        }

    }

    private void newCommonMouse() {
        this.getCell().getHerbivores().add(new Mouse(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }

    @Override
    public void eat() {
        int attemp = 1;
       while( attemp < TRIES_TO_CATCH_FOOD ) {
           if ( !checkPlants() && !checkInsects()){
//               System.out.println(this + " moves coz no food");
               this.move();
           }
//        System.out.println(this.getWeight() + " health before cycle");
           boolean insects = false;
           int random = ThreadLocalRandom.current().nextInt(1, 1000);
           if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkInsects() && random > 100) {
//           System.out.println("eating insects");
               insects = true;
               if (this.getCell().getInsectsCount() > FOOD_INSECT_SIZE) {
                   this.getCell().eatInsects(FOOD_INSECT_SIZE);
                   this.raiseWeight(MAX_RAISE_WEIGHT);
                   if (this.getWeight() > getMAX_weight()) {
                       this.setWeight(getMAX_weight());
                       break;
                   }
               }
           }

           if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkPlants() && !insects) {
//            System.out.println("eating plants");
               if (this.getCell().getPlants() > FOOD_PLANTS_SIZE) {
                   this.getCell().eatPlants(FOOD_PLANTS_SIZE);
                   this.raiseWeight(MAX_RAISE_WEIGHT);
                   if (this.getWeight() > getMAX_weight()) {
                       this.setWeight(getMAX_weight());
                   }
                   break;
               }

           } attemp++;
       }
//        System.out.println(this.getWeight() + " В конце цикла голод мышки");
    }

    @Override
    public void lifeCycle() {
        super.lifeCycle();
    }


    @Override
    public String toString() {
        return "Mouse{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}