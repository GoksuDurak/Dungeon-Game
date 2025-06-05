public enum BiomeTypes {
    SAVANNA(Biomes.HOT),
    DESERT(Biomes.HOT),
    VOLCANIC(Biomes.HOT),
    ICE_SPIKES(Biomes.COLD),
    TUNDRA(Biomes.COLD),
    POLAR(Biomes.COLD),
    PLAINS(Biomes.WARM),
    FOREST(Biomes.WARM),
    SWAMP(Biomes.WARM);
    private final Biomes biome;

    BiomeTypes(Biomes biome) {
        this.biome = biome;
    }

    public Biomes getBiome() {
        return biome;
    }
}
