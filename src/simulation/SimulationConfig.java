package simulation;

public class SimulationConfig {
    // Simulation configuration:
    // - Population size
    // - Initial number of infected
    // - Disease parameters
    // - Control measures parameters

    private int populationSize;
    private int initialInfected;
    private double infectionProbability;
    private int infectionRadius;
    private int incubationPeriod;
    private int diseaseDuration;
    private double recoveryProbability;
    private double mortalityRate;

    public SimulationConfig() {
        // Default values
        this.populationSize = 100;
        this.initialInfected = 5;
        this.infectionProbability = 0.3;
        this.infectionRadius = 2;
        this.incubationPeriod = 3;
        this.diseaseDuration = 14;
        this.recoveryProbability = 0.1;
        this.mortalityRate = 0.01;
    }
    
    public SimulationConfig(int populationSize, int initialInfected,
                          double infectionProbability, int infectionRadius,
                          int incubationPeriod, int diseaseDuration,
                          double recoveryProbability, double mortalityRate) {
        this.populationSize = populationSize;
        this.initialInfected = initialInfected;
        this.infectionProbability = infectionProbability;
        this.infectionRadius = infectionRadius;
        this.incubationPeriod = incubationPeriod;
        this.diseaseDuration = diseaseDuration;
        this.recoveryProbability = recoveryProbability;
        this.mortalityRate = mortalityRate;
    }

    // Getters
    public int getPopulationSize() { return populationSize; }
    public int getInitialInfected() { return initialInfected; }
    public double getInfectionProbability() { return infectionProbability; }
    public int getInfectionRadius() { return infectionRadius; }
    public int getIncubationPeriod() { return incubationPeriod; }
    public int getDiseaseDuration() { return diseaseDuration; }
    public double getRecoveryProbability() { return recoveryProbability; }
    public double getMortalityRate() { return mortalityRate; }
} 