package simulation;

import models.*;
import java.util.ArrayList;

public class SimulationEngine {
    // Main simulation class:
    // - Time management
    // - State updates
    // - Rule application
    // - Statistics collection

    private ArrayList<Person> population;
    private SimulationConfig config;
    private int currentDay;
    private int deathCount;

    public SimulationEngine(SimulationConfig config) {
        this.config = config;
        this.currentDay = 0;
        this.deathCount = 0;
        initializePopulation();
    }

    private void initializePopulation() {
        population = new ArrayList<>();
        for (int i = 0; i < config.getPopulationSize(); i++) {
            population.add(new Person(i < config.getInitialInfected(), config));
        }
    }

    public void update() {
        currentDay++;
        
        // Update state of each person
        for (Person person : population) {
            person.move();
            person.update();  // Process disease progression
        }

        // Check for infections
        for (Person person1 : population) {
            if (person1.isInfected()) {
                Disease disease = person1.getDisease();
                for (Person person2 : population) {
                    if (!person2.isInfected() && !person2.isRecovered() && !person2.isDead() &&
                        isClose(person1, person2) &&
                        Math.random() < disease.getInfectionProbability()) {
                        person2.infect();
                    }
                }
            }
        }
        
        // Update death count
        deathCount = (int) population.stream().filter(Person::isDead).count();
    }

    private boolean isClose(Person p1, Person p2) {
        Disease disease = p1.getDisease();
        int distance = disease.getInfectionRadius();
        return Math.abs(p1.getX() - p2.getX()) <= distance &&
               Math.abs(p1.getY() - p2.getY()) <= distance;
    }

    public ArrayList<Person> getPopulation() {
        return population;
    }

    public int getCurrentDay() {
        return currentDay;
    }
    
    public int getDeathCount() {
        return deathCount;
    }
} 