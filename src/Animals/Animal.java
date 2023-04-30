package Animals;

import Island.*;
import Options.GameOptions;

public abstract class Animal {
    private volatile Cell cell;
    private volatile int posX;
    private volatile int posY;

    private  final int speed;


    private volatile double hunger;
    private volatile double weight;


    public Animal(int posX, int posY, double weight, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.weight = weight;
        this.speed = speed;

    }

    public abstract void checkStatus();                 // check XY of animal and XY of Cell;
    public abstract void reproduce();
    public abstract void eat();
    public abstract void death();
    public abstract void lifeCycle();
    public abstract void move();
    public synchronized void stepUP(){
        if(posY != GameOptions.getSizeY()){
            this.posY += 1;}
    }
    public synchronized  void stepDown(){
        if(posY != 1){
            this.posY -= 1;}
    }
    public synchronized  void stepLeft(){
        if(posX != 1){
            this.posX -= 1;}
    }
    public synchronized  void stepRight(){
        if(posX != GameOptions.getSizeX()){
            this.posX += 1;}
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public double getHunger() {
        return hunger;
    }

    public void hungry(double hunger) {   //every morning animal feel hungry
        this.weight -= hunger;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void raiseWeight(Double food) {
        this.weight +=food;
    }

    public abstract double  getMAX_weight();


    public synchronized Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    };
}
