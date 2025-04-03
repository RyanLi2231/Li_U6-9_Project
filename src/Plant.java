import java.awt.*;

public class Plant {
    private String species;
    private int lifespan;
    private double currentAge;
    private double growthRate;
    private double sellPrice;
    private boolean infected;
    private boolean soil;

    public Plant(String species, int lifespan, double growthRate, double sellPrice) {
        this.species = species;
        this.lifespan = lifespan;
        this.growthRate = growthRate;
        currentAge = 0;
        this.sellPrice = sellPrice;
        this.infected = false;
        if (species.equals("soil")) {
            soil = true;
        }
    }

    public boolean printStats(Graphics g) {
        if (soil) {
            return false;
        }
        int x = 1000;
        int y = 100;
        g.drawString("Species: " + species, x, y);
        g.drawString("Lifespan: " + lifespan + " days", x, y + 20);
        g.drawString("Age: " + currentAge + " days", x, y + 40);
        g.drawString("Infected: " + infected, x, y + 60);
        return true;
    }
}
