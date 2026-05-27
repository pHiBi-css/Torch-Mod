package com.phibi.livingtorch.util;

public class NutritionManager {
    private int hunger = 50;
    private static final int MAX_HUNGER = 100;
    private static final int MIN_HUNGER = 0;
    
    // Aliments acceptés
    private static final int COAL_VALUE = 25;
    private static final int CHARCOAL_VALUE = 25;
    private static final int LAVA_BUCKET_VALUE = 50;
    private static final int WOOD_VALUE = 10;

    public void feed(String itemName) {
        int value = 0;
        switch (itemName.toLowerCase()) {
            case "coal":
            case "charcoal":
                value = COAL_VALUE;
                break;
            case "lava_bucket":
                value = LAVA_BUCKET_VALUE;
                break;
            case "oak_wood":
            case "birch_wood":
            case "spruce_wood":
                value = WOOD_VALUE;
                break;
        }
        hunger = Math.min(hunger + value, MAX_HUNGER);
    }

    public void consume(int amount) {
        hunger = Math.max(hunger - amount, MIN_HUNGER);
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int value) {
        hunger = Math.max(MIN_HUNGER, Math.min(value, MAX_HUNGER));
    }

    public boolean canSurvive() {
        return hunger > 0;
    }
}