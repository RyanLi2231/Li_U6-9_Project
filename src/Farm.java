import java.awt.*;

public class Farm {
    private Plant[][] grid;
    private Plant[] plants = new Plant[]{new Tree("Apple Tree", 120, .5, 6, "Apples"),
    new Shrub("Blueberry Bush", 60, .2, 16, "Blueberries"),
    new Root("Potato", 40, 1, 2)};

    public Farm() {
        grid = new Plant[16][16];
        for (Plant[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                int random = (int) (Math.random() * 5);
                if (random == 0) {
                    row[i] = new Plant("rock", -1, -1, -1);
                } else {
                    row[i] = new Plant("soil", -1, -1, -1);
                }
            }
        }
    }

    public Plant[][] getGrid() {
        return grid;
    }

    public boolean processAnswer(String target, int row, int column) {
        for (Plant plant : plants) {
            if (plant.getSpecies().toLowerCase().equals(target.toLowerCase())) {
                if (plant instanceof Tree) {
                    grid[row][column] = new Tree(plant.getSpecies(), plant.getLifespan(), plant.getSellPrice(), plant.getHarvestCount(), ((Tree) plant).getFruit());
                } else if (plant instanceof Root) {
                    grid[row][column] = new Root(plant.getSpecies(), plant.getLifespan(), plant.getSellPrice(), plant.getHarvestCount());
                } else if (plant instanceof Shrub) {
                    grid[row][column] = new Shrub(plant.getSpecies(), plant.getLifespan(), plant.getSellPrice(), plant.getHarvestCount(), ((Shrub) plant).getBerry());
                }
                return true;
            }
        }
        return false;
    }

    public void nextDay() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Plant plant = grid[i][j];
                plant.calculateHarvestTime();
                if (!plant.getSpecies().equals("soil") && !plant.getSpecies().equals("rock")) {
                    if (plant.getCurrentAge() >= plant.getLifespan()) {
                        grid[i][j] = new Plant("soil", -1, -1, -1);
                    }
                    if (plant.isWatered()) {
                        plant.addCurrentAge();
                        plant.setWilted(false);
                        plant.setWatered(false);
                    } else if (plant.isWilted()) {
                        grid[i][j] = new Plant("soil", -1, -1, -1);
                    } else {
                        plant.setWilted(true);
                    }
                }
            }
        }
    }
    public void printPlants(Graphics g) {
        int x = 900;
        int y = 300;
        g.fillRoundRect(x - 10, y - 20, 250, 300, 10, 10);
        g.setColor(Color.white);
        g.drawString("1. Apple Tree", x, y);
        g.drawString("- Lifespan: 120 days", x, y + 20);
        g.drawString("- .5 per apple", x, y + 40);
        g.drawString("- 6 apples per harvest", x, y + 60);
        g.drawString("2. Blueberry Bush", x, y + 80);
        g.drawString("- Lifespan: 60 days", x, y + 100);
        g.drawString("- .2 per blueberry", x, y + 120);
        g.drawString("- 12 blueberries per harvest", x, y + 140);
        g.drawString("Potato", x, y + 160);
        g.drawString("- Lifespan: 40 days", x, y + 180);
        g.drawString("- 1 per potatoes days", x, y + 200);
        g.drawString("2 potatoes per harvest", x, y + 220);
        g.setColor(Color.black);
    }
}
