import java.awt.*;

public class Player {
    private double coins;
    private String[][] inventory;
    private String[][] shop;

    public Player() {
        coins = 10;
        inventory = new String[][]{{"Shovel", 0 + ""}, {"Pickaxe", 1 + ""}, {"Disinfectant", 0 + ""}};
        shop = new String[][]{{"Shovel", 10 + ""}, {"Pickaxe", 12 + ""}, {"Disinfectant", 7 + ""}};
    }

    public double getCoins() {
        return coins;
    }

    public void addCoins(double coins) {
        this.coins += coins;
    }

    public boolean hasItem(String target) {
        for (String[] item : inventory) {
            if (item[0].toLowerCase().equals(target) && Integer.parseInt(item[1]) > 0) {
                item[1] = Integer.parseInt(item[1]) - 1 + "";
                return true;
            }
        }
        return false;
    }

    public void addItem(String target) {
        for (String[] item : inventory) {
            if (item[0].toLowerCase().equals(target)) {
                item[1] = Integer.parseInt(item[1]) + 1 + "";
            }
        }
    }

    public void printInventory(Graphics g) {
        g.setColor(Color.darkGray);
        int x = 900;
        int y = 770;
        g.fillRoundRect(x - 10, y - 20, 200, 100, 10, 10);
        g.setColor(Color.white);
        g.drawString("Shovel: " + inventory[0][1], x, y);
        g.drawString("Pickaxe: " + inventory[1][1], x, y + 20);
        g.drawString("Disinfectant: " + inventory[2][1], x, y + 40);
        g.setColor(Color.black);
    }

    public void printShop(Graphics g) {
        g.setColor(Color.darkGray);
        int x = 900;
        int y = 650;
        g.fillRoundRect(x - 10, y - 20, 200, 100, 10, 10);
        g.setColor(Color.white);
        g.drawString("Shovel for " + shop[0][1] + " coins", x, y);
        g.drawString("Pickaxe for " + shop[1][1] + " coins", x, y + 20);
        g.drawString("Disinfectant for " + shop[2][1] + " coins", x, y + 40);
        g.setColor(Color.black);
    }

    public int shopHasItem(String target) {
        for (String[] item : shop) {
            if (item[0].toLowerCase().equals(target)) {
                return Integer.parseInt(item[1]);
            }
        }
        return -1;
    }
}
