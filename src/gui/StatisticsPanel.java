package gui;

import javax.swing.*;
import java.awt.*;
import models.Person;
import simulation.SimulationEngine;

public class StatisticsPanel extends JPanel {
    // Statistics Panel:
    // - Graphs
    // - Numerical indicators
    // - Data export
    
    private SimulationEngine engine;
    private JLabel dayLabel;
    private JLabel healthyLabel;
    private JLabel infectedLabel;
    private JLabel recoveredLabel;
    
    // Define colors for dark theme
    private static final Color BACKGROUND_COLOR = new Color(24, 24, 24);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color BORDER_COLOR = new Color(50, 50, 50);
    
    public StatisticsPanel() {
        setPreferredSize(new Dimension(200, 600));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 0, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Create title
        JLabel titleLabel = new JLabel("Statistics");
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Initialize labels with new style
        dayLabel = createStyledLabel("Day: 0");
        healthyLabel = createStyledLabel("Healthy: 0");
        infectedLabel = createStyledLabel("Infected: 0");
        recoveredLabel = createStyledLabel("Recovered: 0");
        
        // Add components
        add(titleLabel);
        add(Box.createVerticalStrut(20));
        add(dayLabel);
        add(Box.createVerticalStrut(10));
        add(createSeparator());
        add(Box.createVerticalStrut(10));
        add(healthyLabel);
        add(Box.createVerticalStrut(5));
        add(infectedLabel);
        add(Box.createVerticalStrut(5));
        add(recoveredLabel);
    }
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(BORDER_COLOR);
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        return separator;
    }
    
    public void setEngine(SimulationEngine engine) {
        this.engine = engine;
    }
    
    public void updateStats() {
        if (engine == null) return;
        
        int healthy = 0;
        int infected = 0;
        int recovered = 0;
        
        for (Person person : engine.getPopulation()) {
            if (person.isInfected()) infected++;
            else if (person.isRecovered()) recovered++;
            else healthy++;
        }
        
        dayLabel.setText("Day: " + engine.getCurrentDay());
        healthyLabel.setText("Healthy: " + healthy);
        infectedLabel.setText("Infected: " + infected);
        recoveredLabel.setText("Recovered: " + recovered);
    }
    
    public void reset() {
        dayLabel.setText("Day: 0");
        healthyLabel.setText("Healthy: 0");
        infectedLabel.setText("Infected: 0");
        recoveredLabel.setText("Recovered: 0");
    }
} 