import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class DisplayPanel extends JPanel implements ActionListener {
    private Logic logic = new Logic();
    private Calender calender = new Calender();
    private Farm farm = new Farm();
    private BufferedImage[] farmObjects = new BufferedImage[5];
    private boolean displayFarm;
    private boolean menu;
    private boolean finishMessage;
    private boolean stats;
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
    private int[] currentStats = new int[2];
    private Player player = new Player();


    public DisplayPanel() {
        farmStuff();
        messageBox = new Rectangle(500, 100);
        messageBoxLocation = new int[]{50, 20};
        textField = new JTextField(10);
        add(textField);
        textField.setVisible(false);
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
        defaultButtons[3].setVisible(false);
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
        g.drawString(calender.printDay(), 800, 20);
        defaultButtons[0].setLocation(1100, 50);
        defaultButtons[1].setLocation(messageBoxLocation[0] + 420, messageBoxLocation[1] + 70);
        defaultButtons[2].setLocation(1080, 80);
        defaultButtons[3].setLocation(700, 20);
        if (displayFarm) {
            placeFarm(g);
        }
        if (menu) {
            placeMenuStuff(g);
        }
        g.setColor(Color.BLACK);
        textField.setLocation(570, 30);
        g.drawString(message, messageBoxLocation[0] + 10, messageBoxLocation[1] + 20);
    }

    private void placeMenuStuff(Graphics g) {
        g.drawRoundRect(120, 120, 800, 700, 60, 60);
        g.setColor(Color.darkGray);
        g.fillRoundRect(120, 120, 800, 700, 60, 60);
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
        g.drawRect(200, 150, 40 * 16 + 14, 40 * 16 + 14);
        g.fillRect(200, 150, 40 * 16 + 14, 40 * 16 + 14);
        for (int i = 0; i < farm.getGrid()[0].length; i++) {
            for (int j = 0; j < farm.getGrid().length; j++) {
                Plant plant = farm.getGrid()[i][j];
                g.drawImage(farmObjects[0], i * 41 + 200, j * 41 + 150, 40, 40, null);
                if (i == 0) {
                    g.drawString(j + 1 + "", 180, j * 41 + 175);
                }
                if (j == 0) {
                    g.drawString(i + 1 + "", i * 41 + 215, 140);
                }
                if (plant instanceof Tree) {
                    g.drawImage(farmObjects[1], i * 41 + 200, j * 41 + 150, 40, 40, null);
                } else if (plant instanceof Shrub) {
                    g.drawImage(farmObjects[2], i * 41 + 200, j * 41 + 150, 40, 40, null);
                } else if (plant instanceof Root) {
                    g.drawImage(farmObjects[3], i * 41 + 200, j * 41 + 150, 40, 40, null);
                } else if (plant instanceof Rock) {
                    g.drawImage(farmObjects[4], i * 41 + 200, j * 41 + 150, 40, 40, null);
                }
            }
        }
        textField.setVisible(true);
        defaultButtons[3].setVisible(true);
        if (stats) {
            if (!farm.getGrid()[currentStats[0]][currentStats[1]].printStats(g)) {
                message = "No plant there";
            } else {
                if (!Arrays.equals(messages, new String[]{"Type 'disinfect' to disinfect", "Type 'water' to water"})) {
                    messages = new String[]{"Type 'disinfect' to disinfect", "Type 'water' to water"};
                    message = "Type 'disinfect' to disinfect";
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) { // Menu Button
            JButton casted = (JButton) e.getSource();
            if (casted == defaultButtons[0]) {
                finishMessage = true;
                menu = !menu;
                menuButtonVisibility(menu);
                repaint();
            }
            if (casted == defaultButtons[1]) { // Enter Button
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
            if (casted == defaultButtons[3]) {
                String enteredText = textField.getText().toLowerCase();
                if (stats) {
                    if (enteredText.equals("disinfect")) {
                        if (player.hasItem("disinfectant")) {
                            message = "Has disinfectant!";
                        } else {
                            message = "No disinfectant in inventory, buy some in shop";
                        }
                    } else if (enteredText.equals("water")) {

                    }
                    finishMessage = true;
                    repaint();
                    return;
                }
                try {
                    String[] text = enteredText.split(" ");
                    if (text.length > 1 && Integer.parseInt(text[0]) <= 16 && Integer.parseInt(text[0]) > 0 && Integer.parseInt(text[1]) <= 16 && Integer.parseInt(text[1]) > 0) {
                        currentStats[1] = Integer.parseInt(text[0]) - 1;
                        currentStats[0] = Integer.parseInt(text[1]) - 1;
                        stats = true;
                    } else {
                        finishMessage = false;
                        messages = new String[]{"Invalid format", "Enter the row and column you want to access", "Ex: 5 8"};
                        message = "Invalid format";
                    }
                } catch (NumberFormatException ex) {
                    finishMessage = false;
                    messages = new String[]{"Invalid format", "Enter the row and column you want to access", "Ex: 5 8"};
                    message = "Invalid format";
                }
                repaint();
            }
            if (casted == menuButtons[0]) { // Farm
                menu = false;
                menuButtonVisibility(false);
                enterFarm();
                repaint();
            }
            if (casted == menuButtons[1]) {
                message = "WIP";
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
        if (!displayFarm) {
            finishMessage = false;
            messages = new String[]{"You have arrived at your farm!", "Enter the row and column you want to access", "Ex: 5 8"};
            message = "You have arrived at your farm!";
        }
        displayFarm = !displayFarm;
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

    private void farmStuff() {
        try {
            farmObjects[0] = ImageIO.read(new File("src\\soil.jpg"));  // Soil
            farmObjects[1] = ImageIO.read(new File("src\\tree.png")); // Tree
            farmObjects[2] = ImageIO.read(new File("src\\bush.png")); // Bush
            farmObjects[3] = ImageIO.read(new File("src\\root.png")); // Root
            farmObjects[4] = ImageIO.read(new File("src\\rock.png"));
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