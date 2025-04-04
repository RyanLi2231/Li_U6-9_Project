public class Shrub extends Plant {
    String berry;

    public Shrub(String species, int lifespan, double sellPrice, int harvestCount, String berry) {
        super(species, lifespan, sellPrice, harvestCount);
        this.berry = berry;
    }

    public String getBerry() {
        return berry;
    }

    @Override
    public String printHarvest() {
        return "Harvested " + getHarvestCount() + " " + berry + " for " + harvest() + " coins";
    }
}
