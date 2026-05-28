package com.phibi.livingtorch.util;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public class BiomeHelper {
    public enum BiomeType {
        HOT,      // Désert, Nether
        COLD,     // Neige, Taïga
        NORMAL,   // Forêt, Plaines
        HUMID,    // Jungle, Marais
        NETHER
    }

    public static BiomeType getBiomeType(Biome biome) {
        Category category = biome.getCategory();
        
        if (biome.getTemperature() >= 2.0f) {
            return BiomeType.HOT;
        } else if (biome.getTemperature() <= 0.1f) {
            return BiomeType.COLD;
        } else if (category == Category.JUNGLE || category == Category.SWAMP) {
            return BiomeType.HUMID;
        } else if (category == Category.NETHER) {
            return BiomeType.NETHER;
        }
        
        return BiomeType.NORMAL;
    }
}