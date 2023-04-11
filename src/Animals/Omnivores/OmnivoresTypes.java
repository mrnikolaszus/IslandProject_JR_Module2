package Animals.Omnivores;

import java.util.HashMap;

public enum OmnivoresTypes {  //TODO animal info?
    BOAR(new HashMap<>());

    private HashMap<String, Double> getInfo() {
        return info;
    }
    public double chance(String s){
        return getInfo().get(s)!=null ? getInfo().get(s) : 0.0d;
    }
    private final HashMap<String, Double> info;
    OmnivoresTypes(HashMap<String, Double> hm) {
        this.info = hm;
    }
    public static void init(){
        BOAR.getInfo().put("wolf", 0D);
        BOAR.getInfo().put("snake", 0D);
        BOAR.getInfo().put("fox", 0D);
        BOAR.getInfo().put("bear", 0D);
        BOAR.getInfo().put("eagle", 0D);
        BOAR.getInfo().put("horse", 0D);
        BOAR.getInfo().put("deer", 0D);
        BOAR.getInfo().put("rabbit", 0D);
        BOAR.getInfo().put("mouse", 0.5D);
        BOAR.getInfo().put("goat", 0D);
        BOAR.getInfo().put("sheep", 0D);
        BOAR.getInfo().put("boar", 0D);
        BOAR.getInfo().put("buffalo", 0D);
        BOAR.getInfo().put("duck", 0D);
        BOAR.getInfo().put("insect", 0.9D);

    }
}
