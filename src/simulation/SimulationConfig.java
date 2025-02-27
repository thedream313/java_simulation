package simulation;

public class SimulationConfig {
    // Simulation configuration:
    // - Population size
    // - Initial number of infected
    // - Disease parameters
    // - Control measures parameters

    private int populationSize;
    private int initialInfected;
    private double infectionRate;
    private double recoveryRate;
    private int worldSize;

    public SimulationConfig() {
        // Default values
        this.populationSize = 100;
        this.initialInfected = 5;
        this.infectionRate = 0.3;
        this.recoveryRate = 0.1;
        this.worldSize = 100;
    }

    // Getters
    public int getPopulationSize() { return populationSize; }
    public int getInitialInfected() { return initialInfected; }
    public double getInfectionRate() { return infectionRate; }
    public double getRecoveryRate() { return recoveryRate; }
    public int getWorldSize() { return worldSize; }
} 