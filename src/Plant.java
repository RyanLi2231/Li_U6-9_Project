import java.awt.*;

public class Plant {
    private String species;
    private int lifespan;
    private int harvestCount;
    private double currentAge;
    private double growthRate;
    private double sellPrice;
    private boolean infected;
    private boolean soil;
    private boolean watered;
    private boolean wilted;
    private boolean harvestable;

    public Plant(String species, int lifespan, double sellPrice, int harvestCount) {
        this.species = species;
        this.lifespan = lifespan;
        this.growthRate = 1;
        this.harvestCount = harvestCount;
        currentAge = 0;
        this.sellPrice = sellPrice;
        this.infected = true;
        if (species.equals("soil")) {
            soil = true;
        }
    }

    public boolean isInfected() {
        return infected;
    }

    public String getSpecies() {
        return species;
    }

    public int getLifespan() {
        return lifespan;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public int getHarvestCount() {
        return harvestCount;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }



    public String printStats(Graphics g) {
        if (soil) {
            return "soil";
        }
        if (species.equals("rock")) {
            return "rock";
        }
        int x = 1000;
        int y = 200;
        g.setColor(Color.darkGray);
        g.fillRoundRect(x - 10, y - 20, 170, 160, 10, 10);
        g.setColor(Color.white);
        g.drawString("Species: " + species, x, y);
        g.drawString("Lifespan: " + lifespan + " days", x, y + 20);
        g.drawString("Age: " + currentAge + " days", x, y + 40);
        g.drawString("Infected: " + infected, x, y + 60);
        g.drawString("Watered: " + watered, x, y + 80);
        g.setColor(Color.black);
        return "";
    }
    public int harvest() {
        if (harvestable) {
            harvestable = false;
            return harvestCount;
        } else {
            return -1;
        }
    }
}
