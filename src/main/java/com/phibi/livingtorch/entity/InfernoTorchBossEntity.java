package com.phibi.livingtorch.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.boss.ServerBossBar;

public class InfernoTorchBossEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int fireAttackCooldown = 0;
    private int phaseTransitionCooldown = 0;

    public InfernoTorchBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = new ServerBossBar(
            Text.literal("Inferno Torch Boss 🔥"),
            BossBar.Color.RED,
            BossBar.Style.NOTCHED_20
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
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(800.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(64.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(8.0);
        this.getAttributes().getTracked(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.5);
        this.setHealth(800.0f);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isClient) {
            // Mettre à jour la barre de boss
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            // Cooldowns
            if (fireAttackCooldown > 0) fireAttackCooldown--;
            if (phaseTransitionCooldown > 0) phaseTransitionCooldown--;

            // Être en feu permanent
            this.setOnFireFor(100);

            // Attaques de feu
            if (fireAttackCooldown <= 0 && this.random.nextInt(30) == 0) {
                performFireAttack();
                fireAttackCooldown = 60;
            }

            // Attaque de charge
            if (this.random.nextInt(100) == 0) {
                chargeAttack();
            }

            // Explosion de feu
            if (this.random.nextInt(150) == 0) {
                fireExplosion();
            }

            // Attaquer les entités proches
            attackNearbyEntities();
        }
    }

    private void performFireAttack() {
        // Infliger des dégâts de feu à tous les joueurs proches
        this.world.getPlayers(null, this.getBoundingBox().expand(20)).forEach(player -> {
            if (player != null && player.getHealth() > 0) {
                // Dégâts de feu: 5 cœurs sans armure ou plus
                float damage = 10.0f; // 5 cœurs
                float armorReduction = (float) player.getArmor() * 0.1f;
                float finalDamage = Math.max(damage - armorReduction, 5.0f);
                
                player.damage(this.world.getDamageSources().magic(this), finalDamage);
                player.setOnFireFor(8);
                
                // Slow effect
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
            }
        });
    }

    private void chargeAttack() {
        PlayerEntity player = this.world.getClosestPlayer(this, 40);
        if (player != null) {
            // Se charger vers le joueur
            double dx = player.getX() - this.getX();
            double dz = player.getZ() - this.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);
            
            if (distance > 0) {
                this.setVelocity(
                    (dx / distance) * 0.8,
                    0.3,
                    (dz / distance) * 0.8
                );
            }
        }
    }

    private void fireExplosion() {
        // Explosion circulaire de feu
        this.world.getOtherEntities(this, this.getBoundingBox().expand(15)).forEach(entity -> {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (player.getHealth() > 0) {
                    player.damage(this.world.getDamageSources().magic(this), 7.5f); // 3.75 cœurs
                    player.setOnFireFor(6);
                    
                    // Knockback
                    double dx = player.getX() - this.getX();
                    double dz = player.getZ() - this.getZ();
                    double distance = Math.sqrt(dx * dx + dz * dz);
                    
                    if (distance > 0) {
                        player.setVelocity(
                            player.getVelocity().x + (dx / distance) * 1.5,
                            player.getVelocity().y + 0.5,
                            player.getVelocity().z + (dz / distance) * 1.5
                        );
                    }
                }
            }
        });
    }

    private void attackNearbyEntities() {
        this.world.getOtherEntities(this, this.getBoundingBox().expand(6)).forEach(entity -> {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (this.random.nextInt(100) < 40) {
                    player.damage(this.world.getDamageSources().magic(this), 5.0f);
                    player.setOnFireFor(5);
                }
            }
        });
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean result = super.damage(source, amount);
        
        if (result && source.getAttacker() != null) {
            source.getAttacker().setOnFireFor(4);
            source.getAttacker().damage(this.world.getDamageSources().magic(this), 2.5f);
        }
        
        return result;
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
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        // Drops épiques
        // 8x Charbon custom (ultra-puissant)
        for (int i = 0; i < 8; i++) {
            this.dropItem(ModItems.CUSTOM_CHARCOAL, 1);
        }
        // 4x Charbon de bois
        this.dropItem(ModItems.CHARCOAL_WOOD, 4);
        // 2x Émeraude (bonus rare)
        this.dropItem(Items.EMERALD, 2);
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