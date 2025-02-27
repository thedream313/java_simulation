package models;

import java.util.Random;

public class Person {
    private static final Random random = new Random();
    
    private boolean infected;
    private boolean recovered;
    private int x;
    private int y;
    private static final int WORLD_SIZE = 100;
    private static final int MOVE_SPEED = 1;
    
    public Person(boolean infected) {
        this.infected = infected;
        this.recovered = false;
        this.x = random.nextInt(WORLD_SIZE);
        this.y = random.nextInt(WORLD_SIZE);
    }
    
    public void move() {
        // Random movement with boundary checking
        int newX = x + (random.nextInt(2 * MOVE_SPEED + 1) - MOVE_SPEED);
        int newY = y + (random.nextInt(2 * MOVE_SPEED + 1) - MOVE_SPEED);
        
        // Check and correct boundaries
        x = Math.min(Math.max(newX, 0), WORLD_SIZE - 1);
        y = Math.min(Math.max(newY, 0), WORLD_SIZE - 1);
    }
    
    public boolean isInfected() { return infected; }
    public boolean isRecovered() { return recovered; }
    public int getX() { return x; }
    public int getY() { return y; }
    
    public void infect() {
        if (!recovered) infected = true;
    }
    
    public void recover() {
        if (infected) {
            infected = false;
            recovered = true;
        }
    }
} 