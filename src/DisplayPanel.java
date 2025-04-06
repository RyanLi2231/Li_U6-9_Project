import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DisplayPanel extends JPanel implements ActionListener {
    private Calendar calendar = new Calendar();
    private Farm farm = new Farm();
    private BufferedImage[] farmObjects = new BufferedImage[5];
    private boolean displayFarm;
    private boolean menu;
    private boolean finishMessage;
    private boolean stats;
    private boolean plantPlant;
    private boolean shop;
    private boolean inventory;
    private String[] messages = new String[]{"Today you have decided to start your farm!",
            "Your task is to make as much money as you can from farming.",
            "Each plant costs 4 coins to plant",
            "Plants can be infected and uses disinfectant to cure",
            "Water your plants everyday",
            "Good luck!"};
    private String message = messages[0];
    private JButton[] defaultButtons = new JButton[4];
    private JButton[] menuButtons = new JButton[4];
    private BufferedImage[] menuItems = new BufferedImage[4];
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
        g.drawString(calendar.printDay(), 800, 20);
        defaultButtons[0].setLocation(1100, 50);
        defaultButtons[1].setLocation(messageBoxLocation[0] + 420, messageBoxLocation[1] + 70);
        defaultButtons[2].setLocation(1080, 80);
        defaultButtons[3].setLocation(700, 20);
        if (displayFarm) {
            placeFarm(g);
        }
        if (inventory) {
            player.printInventory(g);
        }
        if (shop) {
            player.printShop(g);
        }
        if (menu) {
            placeMenuStuff(g);
        }
        g.setColor(Color.BLACK);
        textField.setLocation(570, 30);
        g.drawString(message, messageBoxLocation[0] + 10, messageBoxLocation[1] + 20);
        g.drawString("Coins: " + player.getCoins(), 800, 50);
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
        if (stats) {
            if (farm.getGrid()[currentStats[0]][currentStats[1]].printStats(g).equals("soil")) {
                farm.printPlants(g);
                message = "Type the plant you want to plant";
                plantPlant = true;
            } else if (farm.getGrid()[currentStats[0]][currentStats[1]].printStats(g).equals("rock")) {
                if (player.hasItem("pickaxe")) {
                    farm.getGrid()[currentStats[0]][currentStats[1]] = new Plant("soil", -1, -1, -1);
                    message = "Mined, used pickaxe";
                } else {
                    message = "Need a pickaxe";
                }
                finishMessage = true;
            }else {
                if (!Arrays.equals(messages, new String[]{"Type harvest to harvest", "Type 'disinfect' to disinfect", "Type 'water' to water"})) {
                    messages = new String[]{"Type harvest to harvest", "Type 'disinfect' to disinfect", "Type 'water' to water"};
                    message = "Type harvest to harvest";
                }
            }
        }
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
                } else if (plant.getSpecies().equals("rock")) {
                    g.drawImage(farmObjects[4], i * 41 + 200, j * 41 + 150, 40, 40, null);
                }
            }
        }
        textField.setVisible(true);
        defaultButtons[3].setVisible(true);
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
            if (casted == defaultButtons[1]) { // Next Button
                if (messages[messages.length - 1].equals(message) || finishMessage) {
                    message = "";
                    finishMessage = false;
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
                calendar.adjustDay();
                farm.nextDay();
                repaint();
            }
            if (casted == defaultButtons[3]) { // Enter Button
                String enteredText = textField.getText().toLowerCase();
                if (shop) {
                    int price = player.shopHasItem(enteredText);
                    if (player.getCoins() >= price && price != -1) {
                        player.addItem(enteredText);
                        player.addCoins(price * -1);
                        message = "Item bought!";
                    } else {
                        message = "Not enough coins / Invalid input!";
                    }
                    finishMessage = true;
                    shop = false;
                    repaint();
                    return;
                }
                shop = false;
                if (plantPlant) {
                    if (player.getCoins() >= 4 && farm.processAnswer(enteredText, currentStats[0], currentStats[1])) {
                        player.addCoins(-4);
                        message = "Plant planted!";
                    } else {
                        message = "Not enough coins / Invalid input!";
                    }
                    finishMessage = true;
                    stats = false;
                    plantPlant = false;
                    repaint();
                    return;
                }
                if (stats) {
                    Plant plant = farm.getGrid()[currentStats[0]][currentStats[1]];
                    if (enteredText.equals("disinfect")) {
                        if (plant.isInfected() && player.hasItem("disinfectant")) {
                            message = "Disinfected plant!";
                            plant.setInfected(false);
                        } else if (!plant.isInfected()){
                            message = "Plant is not infected";
                        } else {
                            message = "No disinfectant in inventory, buy some in shop";
                        }
                        finishMessage = true;
                        repaint();
                        return;
                    } else if (enteredText.equals("water")) {
                        message = "Watered Plant!";
                        plant.setWatered(true);
                        finishMessage = true;
                        repaint();
                        return;
                    } else if (enteredText.equals("harvest")) {
                        if (plant.isHarvestable()) {
                            message = plant.printHarvest();
                            System.out.println("ran");
                            player.addCoins(plant.harvest());
                            plant.setHarvestable(false);
                        }
                        repaint();
                        return;
                    }
                }
                try {
                    String[] text = enteredText.split(" ");
                    if (text.length > 1 && Integer.parseInt(text[0]) <= 16 && Integer.parseInt(text[0]) > 0 && Integer.parseInt(text[1]) <= 16 && Integer.parseInt(text[1]) > 0) {
                        currentStats[1] = Integer.parseInt(text[0]) - 1;
                        currentStats[0] = Integer.parseInt(text[1]) - 1;
                        stats = true;
                    } else {
                        stats = false;
                        finishMessage = false;
                        messages = new String[]{"Invalid format", "Enter the row and column you want to access", "Ex: 5 8"};
                        message = "Invalid format";
                    }
                } catch (NumberFormatException ex) {
                    stats = false;
                    finishMessage = false;
                    messages = new String[]{"Invalid format", "Enter the row and column you want to access", "Ex: 5 8"};
                    message = "Invalid format";
                }
                repaint();
            }
            if (casted == menuButtons[0]) { // Farm
                shop = false;
                menu = false;
                menuButtonVisibility(false);
                stats = false;
                message = "";
                enterFarm();
                repaint();
            }
            if (casted == menuButtons[1]) {
                message = "WIP";
                repaint();
            }
            if (casted == menuButtons[2]) {
                message = "Type the item you want to buy";
                shop = !shop;
                menu = false;
                menuButtonVisibility(false);
                textField.setVisible(true);
                defaultButtons[3].setVisible(true);
                repaint();
            }
            if (casted == menuButtons[3]) {
                message = "Opening inventory!";
                inventory = !inventory;
                menu = false;
                menuButtonVisibility(false);
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