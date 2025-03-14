public class Plant {
    private String species;
    private int lifespan;
    private double currentAge;
    private double growthRate;
    private double sellPrice;

    public Plant(String species, int lifespan, double growthRate, double sellPrice) {
        this.species = species;
        this.lifespan = lifespan;
        this.growthRate = growthRate;
        currentAge = 0;
        this.sellPrice = sellPrice;
    }
}
