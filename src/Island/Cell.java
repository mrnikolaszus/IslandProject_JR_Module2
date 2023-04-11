package Island;

import Animals.Herbivore;
import Animals.Herbivores.Mouse;
import Animals.Omnivore;
import Animals.Predator;
import Animals.Predators.Snake;
import Options.GameOptions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {

    private CopyOnWriteArrayList<Predator> predators;
    private CopyOnWriteArrayList<Omnivore> omnivores;
    private CopyOnWriteArrayList<Herbivore> herbivores;

    public String getName() {
        return name;
    }

    private String name;
    private int posX;
    private int posY;

    private int insectsCount;

    private static final int MAX_insectsCount = 1000;
    private double plants;
    private static final double MAX_plants = 300;


    public Cell(int posX, int posY) {
        this.predators = new CopyOnWriteArrayList<>();
        this.omnivores = new CopyOnWriteArrayList<>();
        this.herbivores = new CopyOnWriteArrayList<>();
        this.name = +posX + "*" + posY;
        this.posX = posX;
        this.posY = posY;
        this.plants = 300;  // лучше с нуля начинать
        this.insectsCount = 1000; // лучше с нуля начинать

    }


    @Override
    public String toString() {
        return String.format("|%-8s %-30s|", "\uD83D\uDDFA️" + name,
                "\uD83D\uDC1B:" + insectsCount +
                        " \uD83C\uDF3F:" + plants +
                        " \uD83D\uDC00:" + this.mouseCount().size() +
                        " \uD83D\uDC0D:" + this.snakeCount().size());

    }

    public void growing() {

        if (this.plants > 50 && this.plants < 100 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 1;
        } else if (this.plants > 100 && this.plants < 150 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 3;
        } else if (this.plants > 150 && this.insectsCount < MAX_insectsCount) {
            this.insectsCount += 6;
        } else if (this.plants < 50 && this.insectsCount > 1) {
            this.insectsCount -= 1;
        }

        if (this.plants < MAX_plants) {
            this.plants += 1;
        }
        if (this.plants > (MAX_plants * 0.9) && this.insectsCount > (MAX_insectsCount * 0.9)) {
            int random = ThreadLocalRandom.current().nextInt(1, 10001);
            if (random > 9900) {
//                System.out.println("NEW LIFE!");
                this.newMouse();
            }
        }
        if (this.mouseCount().size() > 10) {
            int random = ThreadLocalRandom.current().nextInt(1, 10001);
            if (random > 9900) {
//                System.out.println("NEW LIFE!");
                this.newSnake();
            }
        }

    }

    public void newMouse() {
        this.herbivores.add(new Mouse(this.posX, this.posY, 0.2, 1));
    }

    public void newSnake() {
        this.predators.add(new Snake(this.posX, this.posY, 5, 3));
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
//        System.out.print("WAIT THIS" + Thread.currentThread().getName()+ "; ");
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

    public CopyOnWriteArrayList<Predator> getPredators() {
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

    public CopyOnWriteArrayList<Herbivore> getHerbivores() {
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

    public List<String> snakeCount() {
        return this.getPredators().stream()
                .map(herbivore -> herbivore.getClass().getName())
                .toList()
                .stream()
                .filter(s -> s.contains("Snake"))
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


    public static void totalSnakesCount() {
        int result =0;
        for (int i = 1; i <= GameOptions.getSizeX(); i++) {
            for (int j = 1; j <= GameOptions.getSizeY(); j++) {
                result += Island.getCell(i, j).snakeCount().size();

            }
        }
        System.out.println("Total \uD83D\uDC0D on Island : " + result);

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

