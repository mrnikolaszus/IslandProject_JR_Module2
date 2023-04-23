package Threads;

import Island.Cell;
import Island.Island;
import Options.GameMethods;
import Options.GameOptions;

import java.util.concurrent.*;

public class Logging extends Thread {

    @Override
    public void run() {

            System.out.println("One Month Report:");
            System.out.println("Total Predators Migrated on Island: " + Island.getMigratedPred());
            System.out.println("Total Herbivores Migrated on Island: " + Island.getMigratedHerb());
            System.out.println("Total Predators Born: " + Island.getBornAnimals());
            System.out.println("Total Herbivores Born: " + Island.getBornHerbAnimals());
            System.out.println("Total Eaten Herbivores by Predators: " + Island.getHerbEaten());
            System.out.println("Total Predators Died of starvation: " + Island.getDeadAnimals());
            System.out.println("Total Herbivores Died of starvation: " + Island.getDeadHerbAnimals());

            Cell.totalMouseCount();
            Cell.totalSnakesCount();
            Cell.totalSheepCount();
            Cell.totalDuckCount();
            Cell.totalEagleCount();
            Cell.totalRabbitCount();
            Cell.totalGoatCount();
            Cell.totalDeerCount();
            Cell.totalHorseCount();
            Cell.totalBuffaloCount();
            Cell.totalFoxCount();
            Cell.totalWolfCount();
            Cell.totalBearCount();




    }
}
