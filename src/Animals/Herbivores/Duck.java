package Animals.Herbivores;

import Animals.Herbivore;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Duck:
1kg max weight
0.08kg lose weight per day


 */
public class Duck extends Herbivore {
    @Override
    public double getMAX_weight() {
        return 1;
    }


    public Duck(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(0.08);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("constructor + this.hunger: " + this.hunger );

    }

    @Override
    public boolean checkPredator() {
        return super.checkPredator();
    }

    @Override
    public boolean checkInsects(){
        return this.getCell().getInsectsCount()>3;
    }


    @Override
    public boolean checkPlants(){
        return this.getCell().getPlants()>3;
    }

    @Override
    public void reproduce() {
        int Ducks = this.getCell().duckCount().size();
        if (Ducks > 2 && Ducks<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 975) {
//                System.out.println("Duck born: " + Ducks);
                newCommonDuck();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
        if (Ducks > 100 && Ducks<200 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 Ducks born: " + Ducks);
                newCommonDuck();
                newCommonDuck();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
    }

    private void newCommonDuck() {
        this.getCell().getHerbivores().add(new Duck(this.getPosX(), this.getPosY(), 0.35, 3));
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
                if (this.getCell().getInsectsCount() > 3) {
                    this.getCell().eatInsects(3);
                    this.raiseWeight(0.3); // 0.01
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
                    this.raiseWeight(0.3);  // 0.01
                    if (this.getWeight() > getMAX_weight()) {
                        this.setWeight(getMAX_weight());
                    }
                    break;
                }

            } attemp++;
        }
//        System.out.println(this.getWeight() + " End of cycle");
    }

    @Override
    public void lifeCycle() {
        super.lifeCycle();
    }


    @Override
    public String toString() {
        return "Duck{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}