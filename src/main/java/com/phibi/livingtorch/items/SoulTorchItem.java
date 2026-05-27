package com.phibi.livingtorch.items;

import net.minecraft.item.TorchItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultOnUse;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.LightningEntity;
import com.phibi.livingtorch.entity.LivingTorchEntity;
import com.phibi.livingtorch.entity.TorchBossEntity;
import com.phibi.livingtorch.entity.InfernoTorchBossEntity;
import com.phibi.livingtorch.LivingTorchMod;

public class SoulTorchItem extends TorchItem {
    public SoulTorchItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResultOnUse useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos().up();
        int random = world.random.nextInt(100);

        if (!world.isClient) {
            if (random == 0) {
                // 1% : Inferno Torch Boss + tempête d'éclairs
                InfernoTorchBossEntity boss = new InfernoTorchBossEntity(LivingTorchMod.INFERNO_TORCH_BOSS, world);
                boss.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                world.spawnEntity(boss);

                // Tempête d'éclairs (5 éclairs)
                for (int i = 0; i < 5; i++) {
                    world.addLightning(new LightningEntity(
                        world,
                        pos.getX() + 0.5 + (world.random.nextDouble() - 0.5) * 3,
                        pos.getY() + 2,
                        pos.getZ() + 0.5 + (world.random.nextDouble() - 0.5) * 3,
                        false
                    ));
                }
                return ActionResultOnUse.SUCCESS;
            } else if (random <= 10) {
                // 10% : Torch Boss + 3 éclairs
                TorchBossEntity boss = new TorchBossEntity(LivingTorchMod.TORCH_BOSS, world);
                boss.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                world.spawnEntity(boss);

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
            } else if (random <= 20) {
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