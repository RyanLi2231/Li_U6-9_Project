public class Tree extends Plant {
    String fruit;

    public Tree(String species, int lifespan, double sellPrice, int harvestCount, String fruit) {
        super(species, lifespan, sellPrice, harvestCount);
        this.fruit = fruit;
    }

    public String getFruit() {
        return fruit;
    }

    @Override
    public String printHarvest() {
        return "Harvested " + getHarvestCount() + " " + fruit + " for " + harvest() + " coins";
    }
}
