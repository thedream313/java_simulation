package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;
    
    // Define colors for dark theme
    private static final Color BACKGROUND_COLOR = new Color(24, 24, 24);
    private static final Color BUTTON_BACKGROUND = new Color(32, 32, 32);
    private static final Color BUTTON_HOVER = new Color(40, 40, 40);
    private static final Color BUTTON_BORDER = new Color(60, 60, 60);
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    
    public ControlPanel() {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create buttons
        startButton = createStyledButton("Start");
        pauseButton = createStyledButton("Pause");
        resetButton = createStyledButton("Reset");
        
        // Add buttons to panel with spacing
        add(startButton);
        add(Box.createHorizontalStrut(10));
        add(pauseButton);
        add(Box.createHorizontalStrut(10));
        add(resetButton);
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_BACKGROUND);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER, 1));
        button.setPreferredSize(new Dimension(100, 30));
        
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
    
    public void setStartAction(Runnable action) {
        startButton.addActionListener(e -> action.run());
    }
    
    public void setPauseAction(Runnable action) {
        pauseButton.addActionListener(e -> action.run());
    }
    
    public void setResetAction(Runnable action) {
        resetButton.addActionListener(e -> action.run());
    }
} 