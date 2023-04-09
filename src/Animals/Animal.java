package Animals;

import Island.*;
import Options.GameOptions;

public abstract class Animal {
    Cell cell;
    int posX;
    int posY;
    double weight;

    static double MAX_weight;
    int speed;
    double hunger;

    public Animal(int posX, int posY, double weight, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.weight = weight;
        this.speed = speed;
    }

    public abstract void checkStatus();                 // check XY of animal and XY of Cell;
    public abstract void move();

    public void stepUP(){
        if(posY != GameOptions.getSizeY()){
            this.posY += 1;}
    }
    public void stepDown(){
        if(posY != 1){
            this.posY -= 1;}
    }
    public void stepLeft(){
        if(posX != 1){
            this.posX -= 1;}
    }
    public void stepRight(){
        if(posX != GameOptions.getSizeX()){
            this.posX += 1;}
    }
    public abstract void reproduce();
    public abstract void eat();
    public abstract void death();
    public abstract void lifeCycle();


    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
