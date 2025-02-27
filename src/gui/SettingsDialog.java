package gui;

import javax.swing.*;
import java.awt.*;
import models.Disease;
import simulation.SimulationConfig;

public class SettingsDialog extends JDialog {
    private JSpinner populationSize;
    private JSpinner initialInfected;
    private JSpinner infectionProbability;
    private JSpinner infectionRadius;
    private JSpinner incubationPeriod;
    private JSpinner diseaseDuration;
    private JSpinner recoveryProbability;
    private JSpinner mortalityRate;
    
    private boolean confirmed = false;
    
    // Define colors for dark theme
    private static final Color BACKGROUND_COLOR = new Color(24, 24, 24);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color FIELD_BACKGROUND = new Color(45, 45, 45);
    private static final Color BUTTON_BACKGROUND = new Color(32, 32, 32);
    private static final Color BUTTON_HOVER = new Color(40, 40, 40);
    private static final Color BUTTON_BORDER = new Color(60, 60, 60);
    
    public SettingsDialog(JFrame parent) {
        super(parent, "Simulation Settings", true);
        setLayout(new BorderLayout());
        
        // Main panel with dark theme
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Population settings
        addSettingField(mainPanel, gbc, 0, "Population Size:", 
            createSpinner(100, 10, 1000, 10));
        addSettingField(mainPanel, gbc, 1, "Initial Infected:", 
            createSpinner(5, 1, 100, 1));
            
        // Disease parameters
        addSeparator(mainPanel, gbc, 2, "Disease Parameters");
        
        addSettingField(mainPanel, gbc, 3, "Infection Probability:", 
            createSpinner(0.3, 0.0, 1.0, 0.05));
        addSettingField(mainPanel, gbc, 4, "Infection Radius:", 
            createSpinner(2, 1, 10, 1));
        addSettingField(mainPanel, gbc, 5, "Incubation Period (days):", 
            createSpinner(3, 1, 14, 1));
        addSettingField(mainPanel, gbc, 6, "Disease Duration (days):", 
            createSpinner(14, 7, 30, 1));
        addSettingField(mainPanel, gbc, 7, "Recovery Probability:", 
            createSpinner(0.1, 0.0, 1.0, 0.05));
        addSettingField(mainPanel, gbc, 8, "Mortality Rate:", 
            createSpinner(0.01, 0.0, 1.0, 0.01));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        
        JButton startButton = createStyledButton("Start Simulation");
        JButton cancelButton = createStyledButton("Cancel");
        
        startButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);
        
        // Add panels to dialog
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Store spinner references
        populationSize = (JSpinner) getSettingComponent(mainPanel, 0);
        initialInfected = (JSpinner) getSettingComponent(mainPanel, 1);
        infectionProbability = (JSpinner) getSettingComponent(mainPanel, 3);
        infectionRadius = (JSpinner) getSettingComponent(mainPanel, 4);
        incubationPeriod = (JSpinner) getSettingComponent(mainPanel, 5);
        diseaseDuration = (JSpinner) getSettingComponent(mainPanel, 6);
        recoveryProbability = (JSpinner) getSettingComponent(mainPanel, 7);
        mortalityRate = (JSpinner) getSettingComponent(mainPanel, 8);
        
        styleSpinners();
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void addSettingField(JPanel panel, GridBagConstraints gbc, 
                               int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.4;
        
        JLabel lblField = new JLabel(label);
        lblField.setForeground(TEXT_COLOR);
        panel.add(lblField, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        panel.add(field, gbc);
    }
    
    private void addSeparator(JPanel panel, GridBagConstraints gbc, 
                            int row, String title) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        panel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
    }
    
    private JSpinner createSpinner(double value, double min, double max, double step) {
        if (step == 1.0) {
            // For integer values
            return new JSpinner(new SpinnerNumberModel(
                (int)value, (int)min, (int)max, (int)step));
        } else {
            // For double values
            return new JSpinner(new SpinnerNumberModel(
                value, min, max, step));
        }
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_BACKGROUND);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER, 1));
        button.setPreferredSize(new Dimension(120, 30));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_BACKGROUND);
            }
        });
        
        return button;
    }
    
    private Component getSettingComponent(JPanel panel, int row) {
        GridBagLayout layout = (GridBagLayout) panel.getLayout();
        Component[] components = panel.getComponents();
        
        for (Component comp : components) {
            GridBagConstraints gbc = layout.getConstraints(comp);
            if (gbc.gridy == row && gbc.gridx == 1) {
                return comp;
            }
        }
        return null;
    }
    
    private void styleSpinners() {
        JSpinner[] spinners = {
            populationSize, initialInfected, infectionProbability,
            infectionRadius, incubationPeriod, diseaseDuration,
            recoveryProbability, mortalityRate
        };
        
        for (JSpinner spinner : spinners) {
            spinner.setBackground(FIELD_BACKGROUND);
            spinner.getEditor().setBackground(FIELD_BACKGROUND);
            ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField()
                .setBackground(FIELD_BACKGROUND);
            ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField()
                .setForeground(TEXT_COLOR);
            ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField()
                .setCaretColor(TEXT_COLOR);
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public SimulationConfig getSimulationConfig() {
        // Get integer values
        int popSize = ((Number)populationSize.getValue()).intValue();
        int initInfected = ((Number)initialInfected.getValue()).intValue();
        int infRadius = ((Number)infectionRadius.getValue()).intValue();
        int incubPeriod = ((Number)incubationPeriod.getValue()).intValue();
        int diseaseDur = ((Number)diseaseDuration.getValue()).intValue();
        
        // Get double values
        double infProb = ((Number)infectionProbability.getValue()).doubleValue();
        double recProb = ((Number)recoveryProbability.getValue()).doubleValue();
        double mortRate = ((Number)mortalityRate.getValue()).doubleValue();
        
        return new SimulationConfig(
            popSize,
            initInfected,
            infProb,
            infRadius,
            incubPeriod,
            diseaseDur,
            recProb,
            mortRate
        );
    }
} 