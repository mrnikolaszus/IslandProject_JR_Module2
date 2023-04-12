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
    @Override
    public double getMAX_weight() {
        return 0.6;
    }


    public Mouse(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(0.06);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("constructor + this.hunger: " + this.hunger );

    }

    @Override
    public boolean checkPredator() {
        return super.checkPredator();
    }

    @Override
    public boolean checkInsects(){
        return this.getCell().getInsectsCount()>0;
    }


    @Override
    public boolean checkPlants(){
        return this.getCell().getPlants()>0;
    }

    @Override
    public void reproduce() {
        int mouses = this.getCell().mouseCount().size();
        if (mouses > 2 && mouses<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 975) {
//                System.out.println("mouse born: " + mouses);
                newCommonMouse();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
        if (mouses > 100 && mouses<200 ) {
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

    private void newCommonMouse() {
        this.getCell().getHerbivores().add(new Mouse(this.getPosX(), this.getPosY(), 0.2, 1));
    }

    @Override
    public void eat() {
        int attemp = 1;
       while( attemp < 10 ) {
           if ( !checkPlants() && !checkInsects()){
               System.out.println(this + " moves coz no food");
               this.move();
           }
//        System.out.println(this.getWeight() + " health before cycle");
           boolean insects = false;
           int random = ThreadLocalRandom.current().nextInt(1, 1000);
           if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkInsects() && random > 100) {
//           System.out.println("eating insects");
               insects = true;
               if (this.getCell().getInsectsCount() > 1) {
                   this.getCell().eatInsects(1);
                   this.raiseWeight(0.25); // 0.01
                   if (this.getWeight() > getMAX_weight()) {
                       this.setWeight(getMAX_weight());
                       break;
                   }
               }
           }

           if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkPlants() && !insects) {
//            System.out.println("eating plants");
               if (this.getCell().getPlants() > 3) {
                   this.getCell().eatPlants(3);
                   this.raiseWeight(0.25);  // 0.01
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