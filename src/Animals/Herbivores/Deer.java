package Animals.Herbivores;

import Animals.Herbivore;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Mouse:
300kg max weight
30kg lose weight per day


 */
public class Deer extends Herbivore {
    @Override
    public double getMAX_weight() {
        return 300;
    }


    public Deer(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(30);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
//        System.out.println("Deer's constructor + this.hunger: " + this.hunger );
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
        return this.getCell().getPlants()>51;
    }

    @Override
    public void reproduce() {
        int Deers = this.getCell().deerCount().size();
        if (Deers > 2 && Deers<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 975) {
//                System.out.println("mouse born: " + mouses);
                newCommonDeer();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
        if (Deers > 100 && Deers<200 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 mouses born: " + mouses);
                newCommonDeer();
                newCommonDeer();
//                    System.out.println(this.cell.Herbivore.get(this.cell.Herbivore.size() - 1));
            }
        }
    }
    private void newCommonDeer() {
        this.getCell().getHerbivores().add(new Deer(this.getPosX(), this.getPosY(), 80, 4));
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
                if (this.getCell().getPlants() > 50) {
                    this.getCell().eatPlants(50);
                    this.raiseWeight(50D);  // 0.01
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
        return "Deer{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}