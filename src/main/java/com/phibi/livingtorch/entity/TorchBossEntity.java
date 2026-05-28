package com.phibi.livingtorch.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.minecraft.world.boss.BossBar;
import net.minecraft.world.boss.ServerBossBar;
import net.minecraft.text.Text;

public class TorchBossEntity extends HostileEntity {
    private final ServerBossBar bossBar;

    public TorchBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = new ServerBossBar(
            Text.literal("Torch Boss 🔥"),
            BossBar.Color.YELLOW,
            BossBar.Style.NOTCHED_10
        );
        this.bossBar.setDarkenSky(true);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    @Override
    protected void setAttributes() {
        super.setAttributes();
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(32.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0);
        this.setHealth(100.0f);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isClient) {
            // Mettre à jour la barre de boss
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            // Attaquer les entités proches
            if (this.random.nextInt(40) == 0) {
                attackNearbyEntities();
            }

            // Mettre le feu aléatoirement
            if (this.random.nextInt(100) < 30) {
                this.setFireTicks(60);
            }
        }
    }

    private void attackNearbyEntities() {
        this.world.getOtherEntities(this, this.getBoundingBox().expand(4)).forEach(entity -> {
            if (entity instanceof LivingTorchEntity && this.random.nextInt(100) < 50) {
                entity.damage(this.world.getDamageSources().magic(this), 3.0f);
            } else if (entity instanceof HostileEntity && this.random.nextInt(100) < 30) {
                entity.damage(this.world.getDamageSources().magic(this), 3.0f);
            }
        });
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean result = super.damage(source, amount);
        if (result && source.getAttacker() instanceof PlayerEntity) {
            // Mettre le feu à l'attaquant
            if (this.random.nextInt(100) < 40) {
                source.getAttacker().setOnFireFor(3);
            }
        }
        return result;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        // Drop du charbon custom x4
        for (int i = 0; i < 4; i++) {
            this.dropItem(ModItems.CUSTOM_CHARCOAL, 1);
        }
        // Drop du charbon classique x2
        this.dropItem(Items.COAL, 2);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        this.bossBar.clearPlayers();
    }
}