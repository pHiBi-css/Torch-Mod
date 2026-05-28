package com.phibi.livingtorch.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.minecraft.world.boss.BossBar;
import net.minecraft.world.boss.ServerBossBar;
import net.minecraft.text.Text;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

public class InfernoTorchBossEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int attackCooldown = 0;

    public InfernoTorchBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = new ServerBossBar(
            Text.literal("Inferno Torch Boss 🔥👹"),
            BossBar.Color.RED,
            BossBar.Style.NOTCHED_20
        );
        this.bossBar.setDarkenSky(true);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(800.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(64.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_ARMOR).setBaseValue(5.0);
        this.setHealth(800.0f);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isClient) {
            // Mettre à jour la barre de boss
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            // Toujours en feu
            this.setFireTicks(Integer.MAX_VALUE);

            // Attaquer les entités proches régulièrement
            if (attackCooldown <= 0) {
                attackNearbyEntities();
                attackCooldown = 40; // 2 secondes entre les attaques
            } else {
                attackCooldown--;
            }

            // Occasionnellement créer du feu autour
            if (this.random.nextInt(20) == 0) {
                createFireAround();
            }
        }
    }

    private void attackNearbyEntities() {
        this.world.getOtherEntities(this, this.getBoundingBox().expand(8)).forEach(entity -> {
            if (entity instanceof LivingEntity && entity != this && !(entity instanceof InfernoTorchBossEntity)) {
                LivingEntity living = (LivingEntity) entity;
                
                // Dégâts de feu: 5 cœurs sans armure ou plus
                float damage = 10.0f; // 5 cœurs
                if (living instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) living;
                    float armor = (float) player.getArmor();
                    if (armor > 0) {
                        damage = 10.0f + (armor * 0.5f); // Plus d'armure = plus de dégâts
                    }
                }
                
                living.damage(this.world.getDamageSources().magic(this), damage);
                living.setOnFireFor(10); // 10 ticks de feu
                
                // Knockback
                double dx = entity.getX() - this.getX();
                double dz = entity.getZ() - this.getZ();
                double dist = Math.sqrt(dx * dx + dz * dz);
                if (dist > 0) {
                    entity.setVelocity(
                        (dx / dist) * 0.5,
                        0.3,
                        (dz / dist) * 0.5
                    );
                }
            }
        });
    }

    private void createFireAround() {
        for (int i = 0; i < 4; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2;
            double distance = 3.0 + this.random.nextDouble() * 2.0;
            int x = (int) (this.getX() + Math.cos(angle) * distance);
            int z = (int) (this.getZ() + Math.sin(angle) * distance);
            int y = (int) this.getY();
            
            if (this.world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getMaterial().isReplaceable()) {
                this.world.setBlockState(new net.minecraft.util.math.BlockPos(x, y, z), net.minecraft.block.Blocks.FIRE.getDefaultState());
            }
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
        this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0f, 0.8f);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean result = super.damage(source, amount);
        if (result && source.getAttacker() instanceof LivingEntity) {
            // Mettre le feu à l'attaquant
            source.getAttacker().setOnFireFor(5);
        }
        return result;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        // Drops épiques
        for (int i = 0; i < 8; i++) {
            this.dropItem(ModItems.CUSTOM_CHARCOAL, 1);
        }
        for (int i = 0; i < 4; i++) {
            this.dropItem(Items.COAL, 1);
        }
        this.dropItem(Items.DIAMOND, 2);
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