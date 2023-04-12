package Animals.Herbivores;

import Animals.Herbivore;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Goat:
70kg max weight
7kg lose weight per day


 */
public class Goat extends Herbivore {
    @Override
    public double getMAX_weight() {
        return 60;
    }


    public Goat(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(7);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("Goat's constructor + this.hunger: " + this.hunger );

    }

    @Override
    public boolean checkPredator() {
        return super.checkPredator();
    }

    @Override
    public boolean checkInsects(){
        return this.getCell().getInsectsCount()>-1;
    }


    @Override
    public boolean checkPlants(){
        return this.getCell().getPlants()>11;
    }

    @Override
    public void reproduce() {
        int Goats = this.getCell().goatCount().size();
        if (Goats > 2 && Goats<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 975) {
//                System.out.println("goat born: " + mouses);
                newCommonGoat();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
        if (Goats > 100 && Goats<200 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 goats born: " + mouses);
                newCommonGoat();
                newCommonGoat();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
    }
    private void newCommonGoat() {
        this.getCell().getHerbivores().add(new Goat(this.getPosX(), this.getPosY(), 23, 3));
    }

    @Override
    public void eat() {

        int attemp = 1;
        while( attemp < 10 ) {
            if (!checkPlants() ) {
                System.out.println(this + " moves coz no food");
                this.move();
            }
//        System.out.println(this.getWeight() + " health before cycle");


            if (this.getWeight() > 0 && this.getWeight() < getMAX_weight() && checkPlants()) {
//           System.out.println( this + "eating plants");
                if (this.getCell().getPlants() > 10) {
                    this.getCell().eatPlants(10);
                    this.raiseWeight(10D);  // 0.01
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
        return "Goat{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}