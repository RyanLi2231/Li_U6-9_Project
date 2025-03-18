import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    private boolean menu = true;
    private String message;
    private JButton[] menuButtons = new JButton[5];
    private JButton submit;
    private BufferedImage[] menuItems = new BufferedImage[5];
    private BufferedImage farmland;
    private BufferedImage forest;
    private BufferedImage shop;
    private JTextField textField;

    public DisplayPanel() {
        menuButtons[0] = new JButton("To Farm!");
        menuButtons[0].addActionListener(this);
        add(menuButtons[0]);
        menu();
        textField = new JTextField(10);
        add(textField);

        menuButtons[1] = new JButton("To Forest!");
        menuButtons[1].addActionListener(this);
        add(menuButtons[1]);
    }

    public void addMessage(String message) {
        this.message = message;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString(message, 50, 30);
        if (menu) {
            g.drawImage(menuItems[0], 100, 50, null);
            g.drawImage(menuItems[1], 100, 300, null);
            g.drawImage(menuItems[2], 500, 50, null);
            g.drawImage(menuItems[3], 500, 300, null);
            menuButtons[0].setLocation(100, 100);
        }

        g.setColor(Color.BLACK);
        textField.setLocation(50, 60);
        submit.setLocation(50, 130);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == menuButtons[0]) {
                message = "CLICKED!";
                repaint();
            }

            if (casted == submit) {
                // obtain string from text field
                String enteredText = textField.getText();

                // update message to the entered text
                message = enteredText;

                // refresh the screen so that the updated message gets displayed
                repaint();
            }
        }
    }

    private void menu() {
        if (menu) {
            try {
                menuItems[0] = ImageIO.read(new File("src\\farmland.png"));
                menuItems[1] = ImageIO.read(new File("src\\forest.jpg"));
                menuItems[2] = ImageIO.read(new File("src\\shop.png"));
                menuItems[3] = ImageIO.read(new File("src\\calendar.png"));
                menuItems[4] = ImageIO.read(new File("src\\quit.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}