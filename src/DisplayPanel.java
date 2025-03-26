import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayPanel extends JPanel implements ActionListener {
    private Logic logic = new Logic();
    private Calender calender = new Calender();
    private Farm farm = new Farm();
    private BufferedImage[][] farmLand = new BufferedImage[16][16];
    private boolean displayFarm;
    private boolean menu;
    private boolean finishMessage;
    private String[] messages = new String[]{"Today you have decided to start your farm!",
            "Your task is to make as much money as you can from farming.",
            "There will be no end and you will come across random events.",
            "Good luck!"};
    private String message = messages[0];
    private JButton[] defaultButtons = new JButton[5];
    private JButton[] menuButtons = new JButton[5];
    private BufferedImage[] menuItems = new BufferedImage[5];
    private JTextField textField;
    private Rectangle messageBox;
    private int[] messageBoxLocation;


    public DisplayPanel() {
        farm();
        messageBox = new Rectangle(500, 100);
        messageBoxLocation = new int[]{50, 20};
        textField = new JTextField(10);
        add(textField);
        defaultButtons[0] = new JButton("Menu");
        defaultButtons[0].addActionListener(this);
        add(defaultButtons[0]);
        defaultButtons[1] = new JButton("Next...");
        defaultButtons[1].addActionListener(this);
        add(defaultButtons[1]);
        defaultButtons[2] = new JButton("Next Day");
        defaultButtons[2].addActionListener(this);
        add(defaultButtons[2]);
        defaultButtons[3] = new JButton("Enter");
        defaultButtons[3].addActionListener(this);
        add(defaultButtons[3]);
        menuButtons[0] = new JButton("To Farm!");
        menuButtons[0].addActionListener(this);
        menuButtons[1] = new JButton("To Forest!");
        menuButtons[1].addActionListener(this);
        menuButtons[2] = new JButton("To Shop!");
        menuButtons[2].addActionListener(this);
        menuButtons[3] = new JButton("Open Inventory");
        menuButtons[3].addActionListener(this);
        menu();
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
        g.setColor(Color.BLACK);
        g.drawString(message, messageBoxLocation[0] + 10, messageBoxLocation[1] + 20);
        g.drawString(calender.printDay(), 800, 20);
        defaultButtons[0].setLocation(1100, 50);
        defaultButtons[1].setLocation(messageBoxLocation[0] + 420, messageBoxLocation[1] + 70);
        defaultButtons[2].setLocation(1080, 80);
        defaultButtons[3].setLocation(700, 20);
        if (menu) {
            placeMenuStuff(g);
        }
        if (displayFarm) {
            placeFarm(g);
        }
        g.setColor(Color.BLACK);
        textField.setLocation(570, 30);
    }

    private void placeMenuStuff(Graphics g) {
        g.drawImage(menuItems[0], 200, 200, null);
        g.drawImage(menuItems[1], 200, 475, null);
        g.drawImage(menuItems[2], 600, 150, null);
        g.drawImage(menuItems[3], 630, 500, null);
        menuButtons[0].setLocation(300, 400);
        menuButtons[1].setLocation(300, 700);
        menuButtons[2].setLocation(675, 400);
        menuButtons[3].setLocation(655, 700);
    }

    private void placeFarm(Graphics g) {
        for (int i = 0; i < farmLand[0].length; i++) {
            for (int j = 0; j < farmLand.length; j++) {
                g.drawImage(farmLand[i][j], i * 50 + 200, j * 50 + 200, 50, 50, null);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == defaultButtons[0]) {
                finishMessage = true;
                menu = !menu;
                menuButtonVisibility(menu);
                repaint();
            }
            if (casted == defaultButtons[1]) {
                if (messages[messages.length - 1].equals(message) || finishMessage) {
                    message = "";
                    finishMessage = true;
                } else if (!finishMessage) {
                    for (int i = 0; i < messages.length - 1; i++) {
                        if (this.message.equals(messages[i])) {
                            message = messages[i + 1];
                            break;
                        }
                    }
                }
                repaint();
            }
            if (casted == defaultButtons[2]) {
                calender.adjustDay();
                repaint();
            }
            if (casted == menuButtons[0]) {
                enterFarm();
                repaint();
            }
            if (casted == menuButtons[1]) {
                message = "You have entered the forest!";
                repaint();
            }
            if (casted == menuButtons[2]) {
                message = "Welcome to the shop!";
                repaint();
            }
            if (casted == menuButtons[3]) {
                message = "Opening inventory!";
                repaint();
            }
        }
    }

    private void enterFarm() {
        message = "You have arrived at your farm!";
        displayFarm = true;
    }

    private void menu() {
        try {
            menuItems[0] = ImageIO.read(new File("src\\farmland.png"));
            menuItems[1] = ImageIO.read(new File("src\\forest.jpg"));
            menuItems[2] = ImageIO.read(new File("src\\shop.png"));
            menuItems[3] = ImageIO.read(new File("src\\inventory.png"));
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

    private void farm() {
        try {
            for (BufferedImage[] row : farmLand) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = ImageIO.read(new File("src\\soil.jpg"));
                }
            }

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