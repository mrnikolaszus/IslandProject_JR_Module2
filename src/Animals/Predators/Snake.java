package Animals.Predators;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Predator;
import Island.Island;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
Snake:
15kg max weight
0.1kg lose weight per day


 */
public class Snake extends Predator {



    @Override
    public double getMAX_weight() {
        return 15;
    }

    public Snake (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.SNAKE;
        this.setHunger(0.1);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int snakes = this.getCell().snakeCount().size();
        if (snakes > 2 && snakes<100 ) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 990) {
//                System.out.println("snake born: " + mouses);
                newCommonSnake();

            }
        }
        if (snakes > 100 && snakes<200 ) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
            int random = ThreadLocalRandom.current().nextInt(1, 1000);
            if (random > 900) {
//                System.out.println("2 snakes born: " + mouses);
                newCommonSnake();
                newCommonSnake();
//
            }
        }
    }

    private void newCommonSnake() {
        this.getCell().getPredators().add(new Snake(this.getPosX(), this.getPosY(), 5, 3));
    }


    @Override
    public void eat() {
        int attemp =1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
                while(attemp<15) {
//                    System.out.println("Snake try to eat herbivore attemp = " + attemp);
                    if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                        this.move();
                        continue;}
//                        System.out.println("Snake found mouse on cell = " + attemp);
                            if(!checkHerbivores()){continue;}
                        if (AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - 1))) {
//                            System.out.println(" snake method logic");
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
        return "Snake{" +
                ", posX=" + this.getPosX() +
                ", posY=" + this.getPosY() +
                ", weight=" + this.getWeight() +
                ", speed=" + this.getSpeed() +
                '}';
    }
}