import gui.MainWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Устанавливаем нативный внешний вид для текущей операционной системы
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Не удалось установить нативный внешний вид: " + e);
        }

        // Запускаем приложение в потоке обработки событий Swing
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
            mainWindow.startSimulation(); // Автоматически запускаем симуляцию
        });
    }
} 