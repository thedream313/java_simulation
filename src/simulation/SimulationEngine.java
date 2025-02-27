package simulation;

import models.*;
import java.util.ArrayList;

public class SimulationEngine {
    // Основной класс симуляции:
    // - Управление временем
    // - Обновление состояний
    // - Применение правил
    // - Сбор статистики

    private ArrayList<Person> population;
    private SimulationConfig config;
    private int currentDay;

    public SimulationEngine(SimulationConfig config) {
        this.config = config;
        this.currentDay = 0;
        initializePopulation();
    }

    private void initializePopulation() {
        population = new ArrayList<>();
        for (int i = 0; i < config.getPopulationSize(); i++) {
            population.add(new Person(i < config.getInitialInfected()));
        }
    }

    public void update() {
        currentDay++;
        // Обновление состояния каждого человека
        for (Person person : population) {
            person.move();
        }

        // Проверка заражений
        for (Person person1 : population) {
            if (person1.isInfected()) {
                for (Person person2 : population) {
                    if (!person2.isInfected() && !person2.isRecovered() &&
                        isClose(person1, person2) &&
                        Math.random() < config.getInfectionRate()) {
                        person2.infect();
                    }
                }
            }
        }
    }

    private boolean isClose(Person p1, Person p2) {
        int distance = 2; // Радиус заражения
        return Math.abs(p1.getX() - p2.getX()) <= distance &&
               Math.abs(p1.getY() - p2.getY()) <= distance;
    }

    public ArrayList<Person> getPopulation() {
        return population;
    }

    public int getCurrentDay() {
        return currentDay;
    }
} 