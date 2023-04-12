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



    @Override
    public double getMAX_weight() {
        return 0.6;
    }

    public Eagle (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.EAGLE;
        this.setHunger(0.3);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int Eagles = this.getCell().eagleCount().size();
        if (Eagles > 2 && Eagles<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 990) {
//                System.out.println("Eagle born: " + mouses);
                newCommonEagle();

            }
        }
        if (Eagles > 100 && Eagles<200 ) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 Eagles born: " + mouses);
                newCommonEagle();
                newCommonEagle();
//
            }
        }
    }

    private void newCommonEagle() {
        this.getCell().getPredators().add(new Eagle(this.getPosX(), this.getPosY(), 5, 3));
    }


    @Override
    public void eat() {
        int attemp =1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
        while(attemp<10) {
//                    System.out.println("Eagle try to eat herbivore attemp = " + attemp);
            if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                this.move();
                continue;}
//                        System.out.println("Eagle found mouse on cell = " + attemp);
            if(!checkHerbivores()){continue;}
            if (AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - 1))) {
//                            System.out.println(" Eagle method logic");
                AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - 1));
                if(this.getWeight() > (this.getMAX_weight()*0.75D)){
                    break;
                }
            }

            attemp++;
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