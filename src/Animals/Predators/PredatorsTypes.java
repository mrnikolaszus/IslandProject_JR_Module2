package Animals.Predators;

import java.util.HashMap;

public enum PredatorsTypes { //TODO animal info?

    SNAKE(new HashMap<>()),
    EAGLE(new HashMap<>()),
    FOX(new HashMap<>()),
    WOLF(new HashMap<>()),
    BEAR(new HashMap<>());

    private HashMap<String, Double> getInfo() {
        return info;
    }
    public double chance(String s){
        return getInfo().get(s)!=null ? getInfo().get(s) : 0.0d;
    }
    private final HashMap<String, Double> info;
    PredatorsTypes(HashMap<String, Double> hm) {
        this.info = hm;
    }
    public static void init(){
        SNAKE.getInfo().put("Wolf", 0D);
        SNAKE.getInfo().put("Snake", 0D);
        SNAKE.getInfo().put("Fox", 0.15D);
        SNAKE.getInfo().put("Bear", 0D);
        SNAKE.getInfo().put("Eagle", 0D);
        SNAKE.getInfo().put("Horse", 0D);
        SNAKE.getInfo().put("Deer", 0D);
        SNAKE.getInfo().put("Rabbit", 0.2D);
        SNAKE.getInfo().put("Mouse", 0.4D);
        SNAKE.getInfo().put("Goat", 0D);
        SNAKE.getInfo().put("Sheep", 0D);
        SNAKE.getInfo().put("Boar", 0D);
        SNAKE.getInfo().put("Buffalo", 0D);
        SNAKE.getInfo().put("Duck", 0.1D);
        SNAKE.getInfo().put("Insect", 0.5D);

        EAGLE.getInfo().put("Wolf", 0D);
        EAGLE.getInfo().put("Snake", 0.3D); // в задании 0
        EAGLE.getInfo().put("Fox", 0.1D);
        EAGLE.getInfo().put("Bear", 0D);
        EAGLE.getInfo().put("Eagle", 0D);
        EAGLE.getInfo().put("Horse", 0D);
        EAGLE.getInfo().put("Deer", 0D);
        EAGLE.getInfo().put("Rabbit", 0.9D);
        EAGLE.getInfo().put("Mouse", 0.9D);
        EAGLE.getInfo().put("Goat", 0D);
        EAGLE.getInfo().put("Sheep", 0D);
        EAGLE.getInfo().put("Boar", 0D);
        EAGLE.getInfo().put("Buffalo", 0D);
        EAGLE.getInfo().put("Duck", 0.8D);
        EAGLE.getInfo().put("Insect", 0.5D);

        FOX.getInfo().put("Wolf", 0D);
        FOX.getInfo().put("Snake", 0D);
        FOX.getInfo().put("Fox", 0D);
        FOX.getInfo().put("Bear", 0D);
        FOX.getInfo().put("Eagle", 0D);
        FOX.getInfo().put("Horse", 0D);
        FOX.getInfo().put("Deer", 0D);
        FOX.getInfo().put("Rabbit", 0.7D);
        FOX.getInfo().put("Mouse", 0.9D);
        FOX.getInfo().put("Goat", 0D);
        FOX.getInfo().put("Sheep", 0D);
        FOX.getInfo().put("Boar", 0D);
        FOX.getInfo().put("Buffalo", 0D);
        FOX.getInfo().put("Duck", 0.6D);
        FOX.getInfo().put("Insect", 0.4D);

        WOLF.getInfo().put("Wolf", 0D);
        WOLF.getInfo().put("Snake", 0D);
        WOLF.getInfo().put("Fox", 0D);
        WOLF.getInfo().put("Bear", 0D);
        WOLF.getInfo().put("Eagle", 0D);
        WOLF.getInfo().put("Horse", 0.1D);
        WOLF.getInfo().put("Deer", 0.15D);
        WOLF.getInfo().put("Rabbit", 0.6D);
        WOLF.getInfo().put("Mouse", 0.8D);
        WOLF.getInfo().put("Goat", 0.6D);
        WOLF.getInfo().put("Sheep", 0.7D);
        WOLF.getInfo().put("Boar", 0.15D);
        WOLF.getInfo().put("Buffalo", 0.1D);
        WOLF.getInfo().put("Duck", 0.4D);
        WOLF.getInfo().put("Insect", 0.0D);

        BEAR.getInfo().put("Wolf", 0D);
        BEAR.getInfo().put("Snake", 0.8D);
        BEAR.getInfo().put("Fox", 0D);
        BEAR.getInfo().put("Bear", 0D);
        BEAR.getInfo().put("Eagle", 0D);
        BEAR.getInfo().put("Horse", 0.4D);
        BEAR.getInfo().put("Deer", 0.8D);
        BEAR.getInfo().put("Rabbit", 0.8D);
        BEAR.getInfo().put("Mouse", 0.9D);
        BEAR.getInfo().put("Goat", 0.7D);
        BEAR.getInfo().put("Sheep", 0.7D);
        BEAR.getInfo().put("Boar", 0.5D);
        BEAR.getInfo().put("Buffalo", 0.2D);
        BEAR.getInfo().put("Duck", 0.1D);
        BEAR.getInfo().put("Insect", 0.4D);

    }
}
