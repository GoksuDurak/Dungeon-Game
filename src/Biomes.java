public enum Biomes {
    HOT,
    WARM,
    COLD,
    BIOME_COUNT;
    private static Biomes[][] biomes;

    static {
        biomes = new Biomes[BIOME_COUNT.ordinal()][6];
        biomes[HOT.ordinal()]  = new Biomes[]{Biomes.HOT, Biomes.HOT, Biomes.HOT,Biomes.WARM, Biomes.WARM, Biomes.WARM};
        biomes[COLD.ordinal()] = new Biomes[]{Biomes.WARM, Biomes.WARM, Biomes.WARM,Biomes.COLD, Biomes.COLD, Biomes.COLD};
        biomes[WARM.ordinal()] = new Biomes[]{Biomes.HOT, Biomes.HOT, Biomes.WARM, Biomes.WARM, Biomes.COLD, Biomes.COLD};
    }

    public BiomeTypes next() {
        int randomNum = (int) (Math.random() * 6);
        if (biomes[this.ordinal()][randomNum] == Biomes.COLD) {
            randomNum = (int) (Math.random() * 3);
            if (randomNum == 0) return BiomeTypes.ICE_SPIKES;
            else if (randomNum == 1) return BiomeTypes.TUNDRA;
            else if (randomNum == 2) return BiomeTypes.POLAR;
        } else if (biomes[this.ordinal()][randomNum] == Biomes.HOT) {
            randomNum = (int) (Math.random() * 3);
            if (randomNum == 0) return BiomeTypes.DESERT;
            else if (randomNum == 1) return BiomeTypes.VOLCANIC;
            else if (randomNum == 2) return BiomeTypes.SAVANNA;
        } else if (biomes[this.ordinal()][randomNum] == Biomes.WARM) {
            randomNum = (int) (Math.random() * 3);
            if (randomNum == 0) return BiomeTypes.FOREST;
            else if (randomNum == 1) return BiomeTypes.PLAINS;
            else if (randomNum == 2) return BiomeTypes.SWAMP;
        }
        return null;
    }
}
