public class Player {
    private int coins;
    private String[][] inventory;

    public Player() {
        coins = 0;
        inventory = new String[2][2];
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }


}
