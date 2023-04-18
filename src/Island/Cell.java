package Island;

import Animals.AnimalMethods;
import Animals.Herbivore;
import Animals.Herbivores.*;
import Animals.Omnivore;
import Animals.Predator;
import Animals.Predators.Eagle;
import Animals.Predators.Snake;
import Options.GameOptions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {

    private volatile CopyOnWriteArrayList<Predator> predators;
    private volatile CopyOnWriteArrayList<Omnivore> omnivores;
    private volatile CopyOnWriteArrayList<Herbivore> herbivores;

    public String getName() {
        return name;
    }

    private String name;
    private int posX;
    private int posY;

    private volatile int insectsCount;

    private static final int MAX_insectsCount = 1000;
    private volatile double plants;
    private static final double MAX_plants = 2000;


    public Cell(int posX, int posY) {
        this.predators = new CopyOnWriteArrayList<>();
        this.omnivores = new CopyOnWriteArrayList<>();
        this.herbivores = new CopyOnWriteArrayList<>();
        this.name = +posX + "*" + posY;
        this.posX = posX;
        this.posY = posY;
        this.plants = 2000;  // лучше с нуля начинать
        this.insectsCount = 1000; // лучше с нуля начинать

    }


    @Override
    public String toString() {
        return String.format("|%-8s %-80s|", "\uD83D\uDDFA️" + name,
                "\uD83D\uDC1B:" + insectsCount +
                        " \uD83C\uDF3F:" + plants +
                        " \uD83D\uDC00:" + this.mouseCount().size() +
                        " \uD83D\uDC0D:" + this.snakeCount().size() +
                        " \uD83D\uDC11:" + this.sheepCount().size() +
                        " \uD83E\uDD86:" + this.duckCount().size() +
                        " \uD83E\uDD85:" + this.eagleCount().size() +
                        " \uD83D\uDC07:" + this.rabbitCount().size() +
                        " \uD83D\uDC10:" + this.goatCount().size() +
                        " \uD83E\uDD8C:" + this.goatCount().size() +
                        " \uD83D\uDC0E:" + this.horseCount().size() +
                        " \uD83D\uDC03:" + this.buffaloCount().size() +
                        " \uD83E\uDD8A:" + this.foxCount().size() +
                        " \uD83D\uDC3A:" + this.wolfCount().size() +
                        " \uD83D\uDC3B:" + this.bearCount().size()

        );

    }

    public void growing() {

        if (this.plants > 50 && this.plants < 100 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 1;
        } else if (this.plants > 100 && this.plants < 150 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 2;
        } else if (this.plants > 150 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 3;
        } else if (this.plants < 50 && this.insectsCount > 1) {
            this.insectsCount -= 2;
        }

        if(this.insectsCount > MAX_insectsCount){
            this.insectsCount = MAX_insectsCount;
        }

        if (this.plants < MAX_plants) {
            this.plants += 4;
            if(this.plants > MAX_plants){
                this.plants = MAX_plants;
            }
        }

        if(this.plants > (MAX_plants*0.8)){
        int random = ThreadLocalRandom.current().nextInt(1, 10001);
            if (  random > 9000 && random < 9500  ) {
                AnimalMethods.newMysteriousMouse(this);}
            if (  random > 9500 && random < 9700  ) {
            AnimalMethods.newMysteriousRabbit(this);}
            if (  random > 9700 && random < 9750  ) {
                AnimalMethods.newMysteriousRabbit(this);
                AnimalMethods.newMysteriousRabbit(this);}

            if (  random > 9750 && random < 9800  ) {
            AnimalMethods.newMysteriousSheep(this);}
            if (  random > 9800 && random < 9850  ) {
            AnimalMethods.newMysteriousGoat(this);}
            if (  random > 9850 && random < 9900  ) {
            AnimalMethods.newMysteriousDeer(this);}
            if (  random > 9900 && random < 9950  ) {
            AnimalMethods.newMysteriousHorse(this);}
            if (  random > 9950 && random < 10000  ) {
            AnimalMethods.newMysteriousBuffalo(this);}
        }

        if(this.insectsCount > (MAX_insectsCount*0.8)) {
            int random = ThreadLocalRandom.current().nextInt(1, 10001);
            if (random > 8000 && random < 9450) {
                AnimalMethods.newMysteriousMouse(this);
            }
            if (random > 9450 && random < 9700) {
                AnimalMethods.newMysteriousMouse(this);
                AnimalMethods.newMysteriousMouse(this);
            }
            if (random > 9700 && random < 9900) {
                AnimalMethods.newMysteriousDuck(this);
            }
        }

         if(this.insectsCount < (MAX_insectsCount*0.5) || this.plants < (MAX_plants*0.5) ) {
             int random = ThreadLocalRandom.current().nextInt(1, 10001);
             if (random > 9400 && random < 9550){
             AnimalMethods.newMysteriousSnake(this);}
             if (random > 9550 && random < 9700){
             AnimalMethods.newMysteriousEagle(this);}
             if (random > 9700 && random < 9775){
             AnimalMethods.newMysteriousFox(this);}
             if (random > 9775 && random < 9850){
             AnimalMethods.newMysteriousWolf(this);}
             if (random > 9850 && random < 9900){
             AnimalMethods.newMysteriousBear(this);}

         }
    }

    public void testHerb() {
//        System.out.print("2CYCL-" + Thread.currentThread().getName()+ "; ");
    }

    public void testOmni() {
//        System.out.print("3CYCL-" + Thread.currentThread().getName()+ "; ");
    }

    public void testPred() {
//        System.out.print("4CYCL-" + Thread.currentThread().getName()+ "; ");
    }

    public void testLog() {
//        System.out.print("5CYCL" + Thread.currentThread().getName()+ "; ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (posX != cell.posX) return false;
        if (posY != cell.posY) return false;
        return Objects.equals(name, cell.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + posX;
        result = 31 * result + posY;
        return result;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public synchronized CopyOnWriteArrayList<Predator> getPredators() {
        return predators;
    }

    public int getInsectsCount() {
        return insectsCount;
    }

    public double getPlants() {
        return plants;
    }

    public void eatPlants(double a) {
        this.plants = plants - a;
    }

    public void eatInsects(int a) {
        this.insectsCount = insectsCount - a;
    }

    public CopyOnWriteArrayList<Omnivore> getOmnivores() {
        return omnivores;
    }

    public synchronized CopyOnWriteArrayList<Herbivore> getHerbivores() {
        return herbivores;
    }

    public List<String> mouseCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Mouse"))
                .toList();
    }

    public static void totalMouseCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).mouseCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC00 on Island : " + result);

    }
    public static int totalMouseCountInt() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).mouseCount().size();

            }
        }
        return result;

    }

    public List<String> duckCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Duck"))
                .toList();
    }

    public static void totalDuckCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).duckCount().size();

            }
        }
        System.out.println("Total \uD83E\uDD86 on Island : " + result);

    }
    public static int totalDuckCountInt() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).duckCount().size();

            }
        }
        return result;

    }
    public List<String> sheepCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Sheep"))
                .toList();
    }

    public static void totalSheepCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).sheepCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC11 on Island : " + result);

    }
    public static int totalSheepCountInt() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).sheepCount().size();
            }
        }
        return result;
    }
    public List<String> rabbitCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Rabbit"))
                .toList();
    }

    public static void totalRabbitCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).rabbitCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC07 on Island : " + result);

    }
    public List<String> goatCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Goat"))
                .toList();
    }

    public static void totalGoatCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).goatCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC10 on Island : " + result);

    }
    public List<String> deerCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Deer"))
                .toList();
    }

    public static void totalDeerCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).deerCount().size();

            }
        }
        System.out.println("Total \uD83E\uDD8C on Island : " + result);

    }
    public List<String> horseCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Horse"))
                .toList();
    }

    public static void totalHorseCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).horseCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC0E on Island : " + result);

    }
    public List<String> buffaloCount() {
        return this.getHerbivores().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Buffalo"))
                .toList();
    }

    public static void totalBuffaloCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).buffaloCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC03 on Island : " + result);

    }


    public List<String> snakeCount() {
        return this.getPredators().stream()
                .map(predator -> predator.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Snake"))
                .toList();
    }

    public static void totalSnakesCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).snakeCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC0D on Island : " + result);

    }

    public List<String> eagleCount() {
        return this.getPredators().stream()
                .map(predator -> predator.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Eagle"))
                .toList();
    }

    public static void totalEagleCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).eagleCount().size();

            }
        }
        System.out.println("Total \uD83E\uDD85 on Island : " + result);

    }
    public List<String> foxCount() {
        return this.getPredators().stream()
                .map(predator -> predator.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Fox"))
                .toList();
    }

    public static void totalFoxCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).foxCount().size();

            }
        }
        System.out.println("Total \uD83E\uDD8A on Island : " + result);

    }
    public List<String> wolfCount() {
        return this.getPredators().stream()
                .map(predator -> predator.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Wolf"))
                .toList();
    }

    public static void totalWolfCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).wolfCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC3A on Island : " + result);

    }
    public List<String> bearCount() {
        return this.getPredators().stream()
                .map(predator -> predator.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Bear"))
                .toList();
    }

    public static void totalBearCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).bearCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC3B on Island : " + result);

    }

}

//        return Island.getIsland().values().stream()
//                .mapToInt(value -> value.getHerbivores().stream().map(herbivore -> herbivore.getClass().getName())
//                        .toList()
//                        .stream()
//                        .filter(s -> s.contains("HERNA"))
//                        .toList().size())
////                .count();
//
//    };
//}

