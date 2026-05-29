package com.phibi.livingtorch.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;

public class TorchStaffItem extends Item {
    private static final int MAX_DURABILITY = 500;

    public TorchStaffItem(Settings settings) {
        super(settings.maxDamage(MAX_DURABILITY));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int damage = itemStack.getDamage();

        if (!world.isClient && damage < MAX_DURABILITY) {
            if (user.isSneaking()) {
                // Shift+Right Click = Crée torche temporaire
                createTemporaryTorch(world, user, itemStack);
            } else {
                // Right Click = Lance boule de feu
                launchFireball(world, user, itemStack);
            }
        }

        return ActionResult.SUCCESS;
    }

    private void launchFireball(World world, PlayerEntity user, ItemStack itemStack) {
        Vec3d eyePos = user.getEyePos();
        Vec3d lookDir = user.getRotationVec(1.0f).normalize();

        SnowballEntity snowball = new SnowballEntity(world, user);
        snowball.setPosition(eyePos.x + lookDir.x * 2, eyePos.y + lookDir.y * 2, eyePos.z + lookDir.z * 2);
        snowball.setVelocity(lookDir.x * 1.5, lookDir.y * 1.5, lookDir.z * 1.5);
        world.spawnEntity(snowball);

        for (int i = 0; i < 10; i++) {
            world.addParticle(
                net.minecraft.particle.ParticleTypes.FLAME,
                eyePos.x + (Math.random() - 0.5) * 0.5,
                eyePos.y + (Math.random() - 0.5) * 0.5,
                eyePos.z + (Math.random() - 0.5) * 0.5,
                lookDir.x * 0.5,
                lookDir.y * 0.5,
                lookDir.z * 0.5
            );
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(),
            SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5f, 1.0f);

        itemStack.damage(5, user, (e) -> e.sendEquipmentBreakStatus(net.minecraft.entity.EquipmentSlot.MAINHAND));
    }

    private void createTemporaryTorch(World world, PlayerEntity user, ItemStack itemStack) {
        user.sendMessage(net.minecraft.text.Text.literal("Torche temporaire (future feature)"), false);
        itemStack.damage(15, user, (e) -> e.sendEquipmentBreakStatus(net.minecraft.entity.EquipmentSlot.MAINHAND));
    }
}