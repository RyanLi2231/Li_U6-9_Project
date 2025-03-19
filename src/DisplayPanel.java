import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    private boolean menu = false;
    private String message;
    private JButton[] defaultButtons = new JButton[5];
    private JButton[] menuButtons = new JButton[5];
    private JButton submit;
    private BufferedImage[] menuItems = new BufferedImage[5];
    private BufferedImage farmland;
    private BufferedImage forest;
    private BufferedImage shop;
    private JTextField textField;

    public DisplayPanel() {
        menu();
        defaultButtons[0] = new JButton("Menu");
        defaultButtons[0].addActionListener(this);
        menuButtons[0] = new JButton("To Farm!");
        menuButtons[0].addActionListener(this);
        menuButtons[1] = new JButton("To Forest!");
        menuButtons[1].addActionListener(this);
        menuButtons[2] = new JButton("To Shop!");
        menuButtons[2].addActionListener(this);
        menuButtons[3] = new JButton("Check Calendar");
        menuButtons[3].addActionListener(this);
        textField = new JTextField(10);
        add(textField);
        add(defaultButtons[0]);
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
        defaultButtons[0].setLocation(1100, 50);
        if (menu) {
            placeMenuStuff(g);
        }
        g.setColor(Color.BLACK);
        textField.setLocation(50, 60);
    }

    private void placeMenuStuff(Graphics g) {
        g.drawImage(menuItems[0], 100, 50, null);
        g.drawImage(menuItems[1], 100, 300, null);
        g.drawImage(menuItems[2], 500, 50, null);
        g.drawImage(menuItems[3], 510, 300, null);
        add(menuButtons[0]);
        add(menuButtons[1]);
        add(menuButtons[2]);
        add(menuButtons[3]);
        menuButtons[0].setLocation(200, 250);
        menuButtons[1].setLocation(200, 500);
        menuButtons[2].setLocation(575, 300);
        menuButtons[3].setLocation(555, 500);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == defaultButtons[0]) {
                menu = true;
                message = "Open Menu";
                repaint();
            }
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