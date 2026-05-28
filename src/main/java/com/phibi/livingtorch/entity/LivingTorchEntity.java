package com.phibi.livingtorch.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import com.phibi.livingtorch.util.NutritionManager;
import com.phibi.livingtorch.util.BiomeHelper;
import com.phibi.livingtorch.util.BiomeHelper.BiomeType;

public class LivingTorchEntity extends MobEntity {
    private final NutritionManager nutritionManager = new NutritionManager();
    private TorchState currentState = TorchState.NORMAL;
    private int waterContactTicks = 0;
    private int rainTicks = 0;
    private static final int WATER_FLEE_DISTANCE = 20;

    public LivingTorchEntity(EntityType<? extends LivingTorchEntity> entityType, World world) {
        super(entityType, world);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(6.0);
        this.setHealth(6.0f);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isClient) {
            // Consommation naturelle
            nutritionManager.consume(1);

            // Mettre à jour l'état
            updateState();

            // Vérifier la pluie et l'eau
            handleWaterAndRain();

            // Adaptation au biome
            adaptToBiome();

            // Actions basées sur l'état
            performStateActions();

            // Aller vers le joueur le plus proche
            followNearestPlayer();
        }
    }

    private void updateState() {
        currentState = TorchState.fromHungerLevel(nutritionManager.getHunger());
        
        // Mettre à jour le level de lumière
        if (currentState == TorchState.EXTINGUISHED) {
            this.setLuminance(0);
        } else {
            this.setLuminance(currentState.getLightLevel());
        }
    }

    private void handleWaterAndRain() {
        if (this.isWet() || this.world.isRaining()) {
            // Fuir l'eau et la pluie
            fleeWater();
            nutritionManager.consume(2);
            rainTicks++;
        }

        if (rainTicks > 0) rainTicks--;
    }

    private void fleeWater() {
        if (this.isWet()) {
            waterContactTicks++;
            if (waterContactTicks > 20) {
                // Sauter et essayer de s'échapper
                this.setVelocity(this.getVelocity().x, 0.5, this.getVelocity().z);
                waterContactTicks = 0;
            }
        } else {
            waterContactTicks = 0;
        }
    }

    private void adaptToBiome() {
        BiomeType biomeType = BiomeHelper.getBiomeType(this.world.getBiome(this.getBlockPos()));
        
        switch (biomeType) {
            case COLD:
                // Consomme plus de nourriture au froid
                nutritionManager.consume(1);
                break;
            case HOT:
            case NETHER:
                // Consomme moins ou rien au chaud
                break;
            case HUMID:
                // L'humidité affaiblit la torche
                nutritionManager.consume(2);
                break;
            default:
                break;
        }
    }

    private void performStateActions() {
        if (currentState == TorchState.EXTINGUISHED) {
            // Immobile, pas de lumière
            this.setVelocity(0, 0, 0);
        } else if (currentState == TorchState.WEAK) {
            // Peu de mouvement
            if (this.random.nextInt(3) == 0) {
                this.moveControl.tick();
            }
        } else if (currentState == TorchState.EXCITED) {
            // Très active
            if (this.random.nextInt(30) == 0) {
                attackNearbyEntities();
            }
            if (this.random.nextInt(50) == 0) {
                setFireNearby();
            }
        }
    }

    private void followNearestPlayer() {
        PlayerEntity player = this.world.getClosestPlayer(this, 32);
        if (player != null && currentState != TorchState.EXTINGUISHED) {
            this.getNavigation().startMovingTo(player, 1.0 + currentState.getLevel() * 0.2);
        }
    }

    private void attackNearbyEntities() {
        // Attaquer les entités vivantes proches avec très peu de dégâts
        this.world.getOtherEntities(this, this.getBoundingBox().expand(5)).forEach(entity -> {
            if (entity instanceof MobEntity && this.random.nextInt(100) < 30) {
                ((MobEntity) entity).damage(this.world.getDamageSources().magic(this), 0.5f);
            }
        });
    }

    private void setFireNearby() {
        this.world.getOtherEntities(this, this.getBoundingBox().expand(3)).forEach(entity -> {
            if (this.random.nextInt(100) < 20 && !entity.isFireImmune()) {
                entity.setOnFireFor(2);
            }
        });
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        // Drop charbon de bois x2
        this.dropItem(ModItems.CHARCOAL_WOOD, 2);
        // Drop stick x1
        this.dropItem(Items.STICK, 1);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Hunger", nutritionManager.getHunger());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        nutritionManager.setHunger(nbt.getInt("Hunger"));
    }

    public TorchState getCurrentState() {
        return currentState;
    }

    public NutritionManager getNutritionManager() {
        return nutritionManager;
    }
}