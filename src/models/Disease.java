package models;

import simulation.SimulationConfig;

public class Disease {
    // Disease parameters from simulation logic
    private double infectionProbability;  // Base infection probability on contact
    private int infectionRadius;          // Distance for infection spread
    private int incubationPeriod;        // Time before symptoms appear
    private int diseaseDuration;         // Total duration of disease
    private double recoveryProbability;   // Chance to recover per day
    private double mortalityRate;         // Chance of death per day
    
    // Disease stages tracking
    private int daysInfected;            // Days since infection
    private boolean isInIncubation;      // Currently in incubation period
    private double currentInfectivity;    // Current infection strength
    
    public Disease(SimulationConfig config) {
        this.daysInfected = 0;
        this.infectionProbability = config.getInfectionProbability();
        this.infectionRadius = config.getInfectionRadius();
        this.incubationPeriod = config.getIncubationPeriod();
        this.diseaseDuration = config.getDiseaseDuration();
        this.recoveryProbability = config.getRecoveryProbability();
        this.mortalityRate = config.getMortalityRate();
        
        this.isInIncubation = true;
        this.currentInfectivity = 0.5;  // Start with medium infectivity
    }
    
    public void update() {
        daysInfected++;
        
        // Update disease stage
        if (daysInfected > incubationPeriod) {
            isInIncubation = false;
        }
        
        // Update infectivity based on stage
        if (isInIncubation) {
            // Gradually increase during incubation
            currentInfectivity = Math.min(1.0, 
                0.5 + (daysInfected / (double)incubationPeriod) * 0.5);
        } else {
            // Gradually decrease after peak
            currentInfectivity = Math.max(0.2, 
                1.0 - ((daysInfected - incubationPeriod) / (double)diseaseDuration) * 0.8);
        }
    }
    
    // Getters
    public double getInfectionProbability() {
        // During incubation period, infection probability is lower
        if (daysInfected < incubationPeriod) {
            return infectionProbability * 0.5;
        }
        return infectionProbability;
    }
    
    public int getInfectionRadius() {
        return infectionRadius;
    }
    
    public boolean isInIncubationPeriod() {
        return isInIncubation;
    }
    
    public double getRecoveryProbability() {
        return recoveryProbability;
    }
    
    public double getMortalityRate() {
        return mortalityRate;
    }
    
    public int getDaysInfected() {
        return daysInfected;
    }
    
    public boolean shouldRecover() {
        if (daysInfected >= diseaseDuration) {
            return Math.random() < recoveryProbability;
        }
        return false;
    }
    
    public boolean shouldDie() {
        if (daysInfected >= diseaseDuration) {
            return Math.random() < mortalityRate;
        }
        return false;
    }
    
    public void reset() {
        daysInfected = 0;
        isInIncubation = true;
        currentInfectivity = 0.5;
    }
    
    public int getIncubationPeriod() {
        return incubationPeriod;
    }
    
    public int getDiseaseDuration() {
        return diseaseDuration;
    }
} 