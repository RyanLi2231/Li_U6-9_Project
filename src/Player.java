public class Player {
    private double coins;
    private String[][] inventory;

    public Player() {
        coins = 10;
        inventory = new String[][]{{"Shovel", 0 + ""}, {"Pickaxe", 1 + ""}, {"Disinfectant", 0 + ""}};
    }

    public double getCoins() {
        return coins;
    }

    public String[][] getInventory() {
        return inventory;
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
}
