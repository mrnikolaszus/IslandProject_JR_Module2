package Animals;

import Island.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal{
    public Herbivore(int posX, int posY, double weight, int speed) {
        super(posX, posY, weight, speed);
    }

    public void checkStatus(){ // check XY of animal and XY of Cell;
        if (this.getCell().getPosX() != this.getPosX() || this.getCell().getPosY() != this.getPosX()){
            this.setCell(Island.getCell(this.getPosX(), this.getPosY()));
        }
    }
    public boolean checkPredator(){
        return this.getCell().getPredators().isEmpty();
    }
    public abstract boolean checkPlants();
    public abstract boolean checkInsects();
    public void move(){

        Island.getCell(this.getPosX(), this.getPosY()).getHerbivores().remove(this);
        for (int i = 0; i < this.getSpeed(); i++) {
            int tempX = this.getPosX();
            int tempY = this.getPosY();
            int random = ThreadLocalRandom.current().nextInt(1, 5);
            if(random == 1 && tempY != this.getPosY()-1){
                stepDown();
                if(checkPredator() && checkPlants() && checkInsects()){
                    break;
                }
            } else { i++;}

            if(random == 2 && tempY != this.getPosY()+1){
                stepUP();
                if(checkPredator() && checkPlants() && checkInsects()){
                    break;
                }
            } else { i++;}
            if(random == 3 && tempX != this.getPosX()+1){
                stepRight();
                if(checkPredator() && checkPlants() && checkInsects()){
                    break;
                }
            } else { i++;}
            if(random == 4 && tempX != this.getPosX()-1){
                stepLeft();
                if(checkPredator() && checkPlants() && checkInsects()){
                    break;
                }
            } else { i++;}
        }
        Island.getCell(this.getPosX(), this.getPosY()).getHerbivores().add(this);
        this.setCell(Island.getCell(this.getPosX(), this.getPosY()));

    }
    public void reproduce(){
//
    }
    public void eat(){

    }
    public void death(){
        Island.getCell(this.getPosX(), this.getPosY()).getHerbivores().remove(this);

    }

    public void lifeCycle(){
//        System.out.println("начался цикл живтного : "+ this);
        if(this.getWeight() > 0) {
//            System.out.println(this.getWeight() + " до гет хангер");
            this.hungry(this.getHunger());
//            System.out.println(this.getWeight() + " после гет хангер");
//                     System.out.println(" проголадалась на: " + this.getHunger() + " вес: " +this.getWeight());
            this.checkStatus();
//           System.out.println("Проверяем на этой ячейке нет ли хищников:");
//            System.out.println("Проверяем на этой ячейке есть ли растения :");
            if (this.checkPredator() && this.checkPlants()) {
                if(this.getWeight() >= (getMAX_weight()*0.8)){
                    this.reproduce();}

                this.eat();
            } else {
//                System.out.println("Не нашла еду, бегаем:");
                this.move();
            }
        }
        else {

//            System.out.println(this + "животное умерло от голода");
            this.death();
        }
    }
}
