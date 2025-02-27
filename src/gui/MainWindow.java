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
    private static final Color MENU_BAR_COLOR = new Color(30, 30, 30);
    private static final Color MENU_TEXT_COLOR = new Color(220, 220, 220);
    
    public MainWindow() {
        // Window setup
        setTitle("Epidemic Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set dark theme for the entire window
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Initialize components
        SimulationConfig config = new SimulationConfig();
        engine = new SimulationEngine(config);
        
        // Create panels
        simulationPanel = new SimulationPanel();
        simulationPanel.setEngine(engine);
        
        controlPanel = new ControlPanel();
        
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
        
        // Setup menu
        setupMenu();
        
        // Final window setup
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
    
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(MENU_BAR_COLOR);
        
        // File Menu
        JMenu fileMenu = createStyledMenu("File");
        JMenuItem newItem = createStyledMenuItem("New Simulation");
        JMenuItem exitItem = createStyledMenuItem("Exit");
        
        newItem.addActionListener(e -> resetSimulation());
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // Control Menu
        JMenu controlMenu = createStyledMenu("Control");
        JMenuItem startItem = createStyledMenuItem("Start");
        JMenuItem pauseItem = createStyledMenuItem("Pause");
        JMenuItem settingsItem = createStyledMenuItem("Settings");
        
        startItem.addActionListener(e -> simulationTimer.start());
        pauseItem.addActionListener(e -> simulationTimer.stop());
        settingsItem.addActionListener(e -> showSettings());
        
        controlMenu.add(startItem);
        controlMenu.add(pauseItem);
        controlMenu.addSeparator();
        controlMenu.add(settingsItem);
        
        menuBar.add(fileMenu);
        menuBar.add(controlMenu);
        setJMenuBar(menuBar);
    }
    
    private JMenu createStyledMenu(String text) {
        JMenu menu = new JMenu(text);
        menu.setForeground(MENU_TEXT_COLOR);
        return menu;
    }
    
    private JMenuItem createStyledMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(MENU_BAR_COLOR);
        item.setForeground(MENU_TEXT_COLOR);
        return item;
    }
    
    private void resetSimulation() {
        simulationTimer.stop();
        SimulationConfig config = new SimulationConfig();
        engine = new SimulationEngine(config);
        simulationPanel.setEngine(engine);
        statisticsPanel.setEngine(engine);
        simulationPanel.repaint();
        statisticsPanel.reset();
    }
    
    private void showSettings() {
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", MENU_TEXT_COLOR);
        
        JOptionPane.showMessageDialog(this, 
            "Settings will be available in the next version",
            "Settings",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void startSimulation() {
        simulationTimer.start();
    }
    
    public void stopSimulation() {
        simulationTimer.stop();
    }
} 