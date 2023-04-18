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
0.7kg lose weight per day


 */
public class Snake extends Predator {
    public static double MAX_WEIGHT  = 15D;
    public static double LOSE_WEIGHT_PER_DAY  = 0.7D;
    public static double MAX_RAISE_WEIGHT = 1.5D;
    public static int TRIES_TO_CATCH_FOOD = 50;
    public static int SPEED =3;
    public static double WEIGHT_AT_START = 5;
    public static int MAX_CELL_COUNT = 30;

    @Override
    public double getMAX_weight() {
        return MAX_WEIGHT;
    }

    public Snake (int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
        this.type = PredatorsTypes.SNAKE;
        this.setHunger(LOSE_WEIGHT_PER_DAY);;
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
    }

    @Override
    public void reproduce() {
        int snakes = this.getCell().snakeCount().size();
        if (snakes < MAX_CELL_COUNT) {
            if (snakes > 2 && snakes < 100) {
//                System.out.println("на этой ячейки всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 900) {
                    newCommonSnake();
                }
            }
            if (snakes > 15 ) {
//                System.out.println("на этой ячейке всего животных такого типа: " + thisAnimalCount);
                int random = ThreadLocalRandom.current().nextInt(1, 1000);
                if (random > 800) {
                    newCommonSnake();
                    newCommonSnake();
                }
            }
        }
    }

    private void newCommonSnake() {
        this.getCell().getPredators().add(new Snake(this.getPosX(), this.getPosY(), WEIGHT_AT_START, SPEED));
    }

    @Override
    public synchronized void eat() {
        int attemp =1;
        double daylyPrey = 0D;
        int checkSize = 1;
//        System.out.println(this.getWeight() + " до цикла голод змеи");
                while(attemp<TRIES_TO_CATCH_FOOD) {
//                    System.out.println("Snake try to eat herbivore attemp = " + attemp);
                    if(!checkHerbivores()) {
//                        System.out.println("травоядных на этой ячейке: " + this.getCell().getHerbivores().size());
//                        System.out.println("выходим из цикла ?");
                        this.move();
                        attemp++;
                        continue;}
//                        System.out.println("Snake found mouse on cell = " + attemp);
                            if((this.getCell().getHerbivores().size() - (checkSize)) < 1 ){
                                checkSize=1;
                                this.move();
                                attemp++;
                                continue;}
                            if ((this.getCell().getHerbivores().size() - (checkSize)) > 0) {
                                if (AnimalMethods.tryToCatch(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize)) {
//                            System.out.println("Snake catches herb");
                                    daylyPrey += this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize).getWeight();
                                    AnimalMethods.consumeHerbivore(this, this.getCell().getHerbivores().get(this.getCell().getHerbivores().size() - checkSize), checkSize);
                                    if (daylyPrey > MAX_RAISE_WEIGHT) { //MAX_RAISE_WEIGHT
//                                System.out.println("Snake FULL");
                                        break;
                                    }
                                    attemp++;
                                    continue;

                                }
                            }
//                    System.out.println("Snake didnt catch herb");
                        if ((this.getCell().getHerbivores().size() - (checkSize +1)) < 1 ){
//                            System.out.println("Snake sees no more herb and moves");
                            checkSize=1;
                            this.move();
                            attemp++;

                        } else { checkSize++;
//                            System.out.println("Snake sees new target on same cell" + checkSize);
                        }
                }
//        System.out.println(this.getWeight() + " end of Cycle");
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