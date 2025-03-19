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

}
