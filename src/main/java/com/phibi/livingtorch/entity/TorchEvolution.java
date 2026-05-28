package com.phibi.livingtorch.entity;

public enum TorchEvolution {
    NORMAL("normal", 0x000000, 0),
    FROST("frost", 0x00CCFF, 1),           // 🔵 Bleu
    NATURE("nature", 0x00FF33, 2),         // 🟢 Vert
    LIGHTNING("lightning", 0xFFFF00, 3),   // ⚡ Jaune
    WATER("water", 0x0066FF, 4),           // 🌊 Bleu foncé
    TWILIGHT("twilight", 0xCC00FF, 5);     // 💜 Violet

    private final String name;
    private final int color;
    private final int id;

    TorchEvolution(String name, int color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public static TorchEvolution fromId(int id) {
        for (TorchEvolution evolution : values()) {
            if (evolution.id == id) {
                return evolution;
            }
        }
        return NORMAL;
    }
}