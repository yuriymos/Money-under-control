import javax.swing.*;

/**
 * The Main method class
 * @autor yuriymos
 * @version 1.0
 */
public class Money {
    /**
     * The main method in the program (anyone knows that? :)
     */
    public static void main (String [] args) {
        UserInterface frame = new UserInterface();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                frame.getClass();
            }
        };
        SwingUtilities.invokeLater(run);
    }
}
