public class Farm {
    private Plant[][] grid;

    public Farm() {
        grid = new Plant[16][16];
        for (Plant[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                int random = (int) (Math.random() * 5);
                if (random == 0) {
                    row[i] = new Rock();
                } else {
                    row[i] = new Plant("soil", -1, -1, -1);
                }
            }
        }
    }

    public Plant[][] getGrid() {
        return grid;
    }
}
