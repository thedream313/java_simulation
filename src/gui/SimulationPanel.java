package gui;

import javax.swing.*;
import java.awt.*;
import models.Person;
import simulation.SimulationEngine;

public class SimulationPanel extends JPanel {
    private SimulationEngine engine;
    private static final int CELL_SIZE = 6;
    
    // Определяем цвета для темной темы
    private static final Color BACKGROUND_COLOR = new Color(18, 18, 18);
    private static final Color GRID_COLOR = new Color(45, 45, 45);
    private static final Color HEALTHY_COLOR = new Color(76, 175, 80);  // Зеленый
    private static final Color INFECTED_COLOR = new Color(244, 67, 54); // Красный
    private static final Color RECOVERED_COLOR = new Color(33, 150, 243); // Синий
    
    public SimulationPanel() {
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(600, 600));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(50, 50, 50))); // Разделитель
    }
    
    public void setEngine(SimulationEngine engine) {
        this.engine = engine;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (engine == null) return;
        
        // Рисуем сетку
        g.setColor(GRID_COLOR);
        for (int i = 0; i < getWidth(); i += CELL_SIZE) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i < getHeight(); i += CELL_SIZE) {
            g.drawLine(0, i, getWidth(), i);
        }
        
        // Используем Graphics2D для лучшего качества отрисовки
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Рисуем людей
        for (Person person : engine.getPopulation()) {
            int x = person.getX() * CELL_SIZE;
            int y = person.getY() * CELL_SIZE;
            
            if (person.isInfected()) {
                g2d.setColor(INFECTED_COLOR);
            } else if (person.isRecovered()) {
                g2d.setColor(RECOVERED_COLOR);
            } else {
                g2d.setColor(HEALTHY_COLOR);
            }
            
            g2d.fillOval(x - 2, y - 2, 5, 5);
        }
    }
} 