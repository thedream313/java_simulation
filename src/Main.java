import gui.MainWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Set native look and feel for the current operating system
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set native look and feel: " + e);
            // Fallback to default look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("Failed to set cross-platform look and feel: " + ex);
            }
        }

        // Launch application in Swing event dispatch thread
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            } catch (Exception e) {
                System.err.println("Failed to start application: " + e);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
} 