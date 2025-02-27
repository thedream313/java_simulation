# Epidemic Simulation

An interactive real-time visualization of epidemic spread simulation.

## Features

- Real-time disease spread visualization
- Dark mode interface for comfortable viewing
- Customizable simulation parameters
- Statistics and analytics
- Population movement with boundary constraints
- Different states of infection (healthy, infected, recovered)

## Technical Details

### Core Components

1. **Population Management**
   - Individual movement simulation
   - State tracking (healthy, infected, recovered)
   - Boundary collision detection
   - Population density visualization

2. **Disease Mechanics**
   - Infection probability calculation
   - Recovery chance system
   - Contact-based spread
   - Disease progression stages

3. **Visualization**
   - Real-time population movement
   - Color-coded states:
     * Green: Healthy
     * Red: Infected
     * Blue: Recovered
   - Grid-based world representation
   - Statistics panel with current numbers

## Technologies Used

- Java (Core language)
- Swing (GUI Framework)
- Object-Oriented Design
- Event-Driven Architecture

## Project Structure

```
src/
├── models/
│   ├── Person.java         # Individual entity model
│   ├── Population.java     # Population management
│   └── Disease.java        # Disease parameters
│
├── simulation/
│   ├── SimulationEngine.java    # Core simulation logic
│   ├── SimulationConfig.java    # Configuration
│   └── Statistics.java          # Data collection
│
└── gui/
    ├── MainWindow.java          # Main application window
    ├── SimulationPanel.java     # Visualization component
    ├── ControlPanel.java        # Control interface
    └── StatisticsPanel.java     # Statistics display
```

## Getting Started

### Prerequisites
- Java JDK 8 or higher
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/epidemic-simulation.git
```

2. Navigate to the project directory:
```bash
cd epidemic-simulation
```

3. Compile the project:
```bash
javac src/**/*.java
```

4. Run the simulation:
```bash
java -cp src Main
```

## Planned Features

### Events System
- Natural events (seasonal changes, mutations)
- Social events (mass gatherings, quarantine)
- Medical events (vaccine development, hospital capacity)
- Behavioral events (mask mandates, social distancing)

### Enhanced Disease Parameters
- Incubation period
- Varying infection rates
- Multiple disease strains
- Immunity duration

### Additional Features
- Statistical graphs and charts
- Save/Load simulation states
- Configuration presets
- Advanced control measures

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Inspired by real-world epidemic modeling
- Based on SIR (Susceptible, Infected, Recovered) model
- Developed for educational purposes 