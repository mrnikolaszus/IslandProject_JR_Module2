package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Mouse:
максимальный вес 0.05
за один цикл может набрать от 0.01 до 0.015 массы тела.
за один цикл теряет в весе 0.004


 */
public class Snake extends Predator {


    @Override
    public double getMAX_weight() {
        return 15;
    }

    public Snake (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.setHunger(0.15);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int snakes = this.getCell().mouseCount().size();
        if (snakes > 2 && snakes<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 990) {
//                System.out.println("snake born: " + mouses);
                this.getCell().getPredators().add(new Snake(this.getPosX(), this.getPosY(), 5, 3));

            }
        }
        if (snakes > 100 && snakes<200 ) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 snakes born: " + mouses);
                this.getCell().getPredators().add(new Snake(this.getPosX(), this.getPosY(), 5, 3));
                this.getCell().getPredators().add(new Snake(this.getPosX(), this.getPosY(), 5, 3));
//
            }
        }
    }


    @Override
    public void eat() {
        int attemp =1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
                while(attemp<30 && checkHerbivores()) {
//                    System.out.println("Snake try to eat mouse attemp = " + attemp);
                    if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                        this.move();
                        continue;}
//                        System.out.println("Snake found mouse on cell = " + attemp);
                            if(!checkHerbivores()){continue;}  //TODO understand why did it help not to cath EXCP
                        if (AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - 1))) {
                            AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - 1));
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
        return "Mouse{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}