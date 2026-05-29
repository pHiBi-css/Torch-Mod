package com.phibi.livingtorch.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.text.Text;

public class TorchEssenceItem extends Item {
    public TorchEssenceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            user.sendMessage(
                Text.literal("§6Utilisez l'Essence sur une Torche Vivante pour l'évoluer!"),
                false
            );
        }

        return ActionResult.SUCCESS;
    }
}