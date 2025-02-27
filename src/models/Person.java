package models;

import java.util.Random;
import simulation.SimulationConfig;

public class Person {
    private static final Random random = new Random();
    
    private boolean infected;
    private boolean recovered;
    private boolean dead;
    private Disease disease;
    private int x;
    private int y;
    private static final int WORLD_SIZE = 100;
    private static final int MOVE_SPEED = 1;
    private SimulationConfig config;
    
    public Person(boolean infected, SimulationConfig config) {
        this.config = config;
        this.x = random.nextInt(WORLD_SIZE);
        this.y = random.nextInt(WORLD_SIZE);
        this.recovered = false;
        this.dead = false;
        
        if (infected) {
            infect();
        }
    }
    
    public void move() {
        if (!dead) {
            // Random movement
            x += random.nextInt(3) - 1;
            y += random.nextInt(3) - 1;
            
            // Keep within bounds
            x = Math.max(0, Math.min(x, WORLD_SIZE - 1));
            y = Math.max(0, Math.min(y, WORLD_SIZE - 1));
        }
    }
    
    public void update() {
        if (disease != null && !dead) {
            disease.update();
            
            // Check for recovery or death
            if (disease.shouldRecover()) {
                recovered = true;
                disease = null;
            } else if (disease.shouldDie()) {
                dead = true;
                disease = null;
            }
        }
    }
    
    public boolean isInfected() {
        return disease != null;
    }
    
    public boolean isRecovered() {
        return recovered;
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public Disease getDisease() {
        return disease;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void infect() {
        if (!isInfected() && !isRecovered() && !isDead()) {
            disease = new Disease(config);
        }
    }
} 