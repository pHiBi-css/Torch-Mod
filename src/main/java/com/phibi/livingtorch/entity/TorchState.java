package com.phibi.livingtorch.entity;

public enum TorchState {
    EXTINGUISHED(0, 0),      // Éteinte - immobile, pas de lumière
    WEAK(1, 5),               // Faible - peu de mouvement, peu de lumière
    NORMAL(2, 10),            // Normal - mouvement normal, lumière normale
    EXCITED(3, 15);           // Excitée - très active, lumière brillante

    private final int level;
    private final int lightLevel;

    TorchState(int level, int lightLevel) {
        this.level = level;
        this.lightLevel = lightLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public static TorchState fromHungerLevel(int hunger) {
        if (hunger <= 0) return EXTINGUISHED;
        if (hunger <= 30) return WEAK;
        if (hunger <= 80) return NORMAL;
        return EXCITED;
    }
}