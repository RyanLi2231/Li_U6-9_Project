import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements ActionListener {
    private boolean menu;
    private String message;
    private JButton[] defaultButtons = new JButton[5];
    private JButton[] menuButtons = new JButton[5];
    private JButton submit;
    private BufferedImage[] menuItems = new BufferedImage[5];
    private BufferedImage farmland;
    private BufferedImage forest;
    private BufferedImage shop;
    private JTextField textField;
    private Rectangle messageBox;
    private int[] messageBoxLocation;


    public DisplayPanel() {
        messageBox = new Rectangle(400, 200);
        messageBoxLocation = new int[]{50, 50};
        defaultButtons[0] = new JButton("Menu");
        defaultButtons[0].addActionListener(this);
        defaultButtons[1] = new JButton("Next...");
        defaultButtons[1].addActionListener(this);
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
        menu();
    }

    public void addMessage(String message) {
        this.message = message;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        messageBox.setLocation(messageBoxLocation[0], messageBoxLocation[1]);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.draw(messageBox);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString(message, messageBoxLocation[0] + 10, messageBoxLocation[1] + 20);
        defaultButtons[0].setLocation(1100, 50);
        if (menu) {
            placeMenuStuff(g);
        }
        g.setColor(Color.BLACK);
        textField.setLocation(50, 60);
        textField.setVisible(false);
    }

    private void placeMenuStuff(Graphics g) {
        g.drawImage(menuItems[0], 200, 200, null);
        g.drawImage(menuItems[1], 200, 475, null);
        g.drawImage(menuItems[2], 600, 150, null);
        g.drawImage(menuItems[3], 610, 500, null);

        menuButtons[0].setLocation(300, 400);
        menuButtons[1].setLocation(300, 700);
        menuButtons[2].setLocation(675, 400);
        menuButtons[3].setLocation(655, 700);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == defaultButtons[0]) {
                if (menu) {
                    menu = false;
                } else {
                    menu = true;
                }
                menuButtonVisibility(menu);
                repaint();
            }
            if (casted == menuButtons[0]) {
                message = "Farm!";
                repaint();
            }
            if (casted == menuButtons[1]) {
                message = "Forest!";
                repaint();
            }
            if (casted == menuButtons[2]) {
                message = "Shop!";
                repaint();
            }
            if (casted == menuButtons[3]) {
                message = "Calendar!";
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
        try {
            menuItems[0] = ImageIO.read(new File("src\\farmland.png"));
            menuItems[1] = ImageIO.read(new File("src\\forest.jpg"));
            menuItems[2] = ImageIO.read(new File("src\\shop.png"));
            menuItems[3] = ImageIO.read(new File("src\\calendar.png"));
            menuItems[4] = ImageIO.read(new File("src\\quit.png"));
            add(menuButtons[0]);
            add(menuButtons[1]);
            add(menuButtons[2]);
            add(menuButtons[3]);
            menuButtonVisibility(menu);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuButtonVisibility(boolean visible) {
        for (JButton menuButton : menuButtons) {
            if (menuButton != null) {
                menuButton.setVisible(visible);
            } else {
                break;
            }
        }
    }
}