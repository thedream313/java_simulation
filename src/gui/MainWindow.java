package gui;

import javax.swing.*;
import java.awt.*;
import simulation.SimulationEngine;
import simulation.SimulationConfig;

public class MainWindow extends JFrame {
    private SimulationEngine engine;
    private SimulationPanel simulationPanel;
    private ControlPanel controlPanel;
    private StatisticsPanel statisticsPanel;
    private Timer simulationTimer;
    
    // Define colors for dark theme
    private static final Color BACKGROUND_COLOR = new Color(18, 18, 18);
    
    public MainWindow() {
        // Window setup
        setTitle("Epidemic Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set dark theme for the entire window
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Show settings dialog
        SettingsDialog dialog = new SettingsDialog(this);
        dialog.setVisible(true);
        
        if (!dialog.isConfirmed()) {
            System.exit(0);
        }
        
        // Initialize components with user settings
        SimulationConfig config = dialog.getSimulationConfig();
        engine = new SimulationEngine(config);
        
        // Create panels
        simulationPanel = new SimulationPanel();
        simulationPanel.setEngine(engine);
        
        controlPanel = new ControlPanel();
        setupControlPanel();
        
        statisticsPanel = new StatisticsPanel();
        statisticsPanel.setEngine(engine);
        
        // Place components
        add(simulationPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statisticsPanel, BorderLayout.EAST);
        
        // Setup simulation timer
        simulationTimer = new Timer(100, e -> {
            engine.update();
            simulationPanel.repaint();
            statisticsPanel.updateStats();
        });
        
        // Final window setup
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
    
    private void setupControlPanel() {
        controlPanel.setStartAction(() -> simulationTimer.start());
        controlPanel.setPauseAction(() -> simulationTimer.stop());
        controlPanel.setResetAction(this::resetSimulation);
    }
    
    private void resetSimulation() {
        simulationTimer.stop();
        
        // Show settings dialog for new simulation
        SettingsDialog dialog = new SettingsDialog(this);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            SimulationConfig config = dialog.getSimulationConfig();
            engine = new SimulationEngine(config);
            simulationPanel.setEngine(engine);
            statisticsPanel.setEngine(engine);
            simulationPanel.repaint();
            statisticsPanel.reset();
        }
    }
} 