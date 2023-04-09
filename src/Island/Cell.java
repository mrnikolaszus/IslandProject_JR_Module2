package Island;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {

    private CopyOnWriteArrayList<Objects> predators;
    private CopyOnWriteArrayList<Objects> herbivores;

    public String getName() {
        return name;
    }

    private String name;
    private int posX;
    private int posY;
    private int insectsCount;
    private int MAX_insectsCount =1000;
    private double plants;
    private static double MAX_plants = 300;



    public Cell(int posX, int posY) {
        this.predators = new CopyOnWriteArrayList<>();
        this.herbivores = new CopyOnWriteArrayList<>();
        this.name = + posX +"*"+  posY;
        this.posX = posX;
        this.posY = posY;
        this.plants = 0;  // лучше с нуля начинать
        this.insectsCount = 0; // лучше с нуля начинать
    }


    @Override
    public String toString() {
        return "\uD83D\uDDFA️" + name +
                "(\uD83D\uDC1B:" + insectsCount +
                ", \uD83C\uDF3F:" + plants + ")";
    }

    public void growing(){

        if(this.plants>50 && this.plants<100 && this.insectsCount<MAX_insectsCount){
            this.insectsCount +=1;
        } else if(this.plants>100 && this.plants<150 && this.insectsCount<MAX_insectsCount){
            this.insectsCount +=3;
        }  else  if(this.plants>150 && this.insectsCount<MAX_insectsCount){
            this.insectsCount +=6;
        } else if (this.plants<50 && this.insectsCount > 1){
            this.insectsCount -=1;
        }

        if(this.plants<MAX_plants){
            this.plants += 1;
        }
        if(this.plants>(MAX_plants*0.9) && this.insectsCount>(MAX_insectsCount*0.9)){
            int random = ThreadLocalRandom.current().nextInt(1, 10001);
            if (random > 9999) {
                System.out.println("NEW LIFE!");
//                this.newLife();
            }
        }
    }

    public void testHerb(){
        System.out.println("Cell : " + this.getName() + " Herbivores acting, in Thread: " + Thread.currentThread().getName());
    }

    public void testOmni(){
        System.out.println("Cell : " + this.getName() + " Omnivores acting, in Thread: " + Thread.currentThread().getName());
    }

    public void testPred(){
        System.out.println("Cell : " + this.getName() + " Predators acting, in Thread: " + Thread.currentThread().getName());
    }
    public void testLog(){
        System.out.println("Cell : " + this.getName() + " logging, in Thread: " + Thread.currentThread().getName());
    }

}

