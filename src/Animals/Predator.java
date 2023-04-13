package Animals;

import Animals.Predators.PredatorsTypes;
import Island.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal{

    public PredatorsTypes type;

    public Predator(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
    }

    public void checkStatus(){ // check XY of animal and XY of Cell;
        if (this.getCell().getPosX() != this.getPosX() || this.getCell().getPosY() != this.getPosX()){
            this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
        }
    }

    public boolean checkHerbivores(){
        return !this.getCell().getHerbivores().isEmpty();
    }

    public void move(){
        Island.getCell(this.getPosX(), this.getPosY()).getPredators().remove(this);
        for (int i = 0; i < this.getSpeed(); i++) {
            int tempX = this.getPosX();
            int tempY = this.getPosY();
            int random = ThreadLocalRandom.current().nextInt(1, 5);
            if(random == 1 && tempY != this.getPosY()-1){
                stepDown();
                if(checkHerbivores()){
                    break;
                }
            } else { i++;}

            if(random == 2 && tempY != this.getPosY()+1){
                stepUP();
                if(checkHerbivores()){
                    break;
                }
            } else { i++;}
            if(random == 3 && tempX != this.getPosX()+1){
                stepRight();
                if(checkHerbivores()){
                    break;
                }
            } else { i++;}
            if(random == 4 && tempX != this.getPosX()-1){
                stepLeft();
                if(checkHerbivores()){
                    break;
                }
            } else { i++;}
        }
        Island.getCell(this.getPosX(), this.getPosY()).getPredators().add(this);
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));

    }
    public void reproduce(){
//
    }
    public void eat(){

    }
    public void death(){
        Island.getCell(this.getPosX(), this.getPosY()).getPredators().remove(this);

    }

    public void lifeCycle(){
//        System.out.println("начался цикл живтного : "+ this);
        if(this.getWeight() > 0) {
            this.hungry(this.getHunger());
//                      System.out.println("с утра животное "  + this.getClass()+ " проголадалась на: " + this.getHunger() + " вес: " +this.getWeight());
            this.checkStatus();
            if(!checkHerbivores()){
//                System.out.println("Не нашла еду, бегаем:");
                this.move();
            }
                if(this.getWeight() >= (getMAX_weight()*0.8)){
//                    System.out.println(getMAX_weight() + " МАКС ВЕС В ПРЕДАТОР ЛАЙФЦИКЛ");
                    this.reproduce();}
                this.eat();
//            System.out.println(checkHerbivores() + "check hebivores");

        }
        else {

//           System.out.println(this + "PREDATOR DIES OF HUNGER");
            this.death();
        }
    }
}
