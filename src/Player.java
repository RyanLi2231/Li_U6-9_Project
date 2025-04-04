public class Player {
    private int coins;
    private String[][] inventory = new String[3][2];

    public Player() {
        coins = 0;
        inventory = new String[][]{{"Shovel", 0 + ""}, {"Pickaxe", 0 + ""}, {"Disinfectant", 1 + ""}};
    }

    public int getCoins() {
        return coins;
    }

    public String[][] getInventory() {
        return inventory;
    }

    public void addCoins(int coins) {
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
