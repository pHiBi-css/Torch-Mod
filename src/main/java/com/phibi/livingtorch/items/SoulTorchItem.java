package com.phibi.livingtorch.items;

import net.minecraft.item.Item;
import net.minecraft.item.TorchItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightingProvider;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LightningEntity;
import com.phibi.livingtorch.entity.LivingTorchEntity;
import com.phibi.livingtorch.entity.TorchBossEntity;
import com.phibi.livingtorch.LivingTorchMod;

public class SoulTorchItem extends TorchItem {
    public SoulTorchItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResultOnUse useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos().up();
        int random = world.random.nextInt(10);

        if (!world.isClient) {
            if (random == 0) {
                // 10% : Torch Boss + 3 éclairs
                TorchBossEntity boss = new TorchBossEntity(LivingTorchMod.TORCH_BOSS, world);
                boss.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                world.spawnEntity(boss);

                // 3 éclairs massifs
                for (int i = 0; i < 3; i++) {
                    world.addLightning(new LightningEntity(
                        world,
                        pos.getX() + 0.5 + (world.random.nextDouble() - 0.5) * 2,
                        pos.getY() + 2,
                        pos.getZ() + 0.5 + (world.random.nextDouble() - 0.5) * 2,
                        false
                    ));
                }
                return ActionResultOnUse.SUCCESS;
            } else if (random == 1 || random == 2) {
                // 10% : Torche vivante + éclair
                LivingTorchEntity torch = new LivingTorchEntity(LivingTorchMod.LIVING_TORCH, world);
                torch.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                world.spawnEntity(torch);

                world.addLightning(new LightningEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 1,
                    pos.getZ() + 0.5,
                    false
                ));
                return ActionResultOnUse.SUCCESS;
            }
        }

        return super.useOnBlock(context);
    }
}