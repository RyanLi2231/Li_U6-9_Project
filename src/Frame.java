import javax.swing.*;

public class Frame {
    private DisplayPanel panel;

    public Frame() {
        JFrame frame = new JFrame("Farming");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        panel = new DisplayPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}