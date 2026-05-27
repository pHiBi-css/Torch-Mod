package com.phibi.livingtorch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.phibi.livingtorch.entity.LivingTorchEntity;
import com.phibi.livingtorch.entity.TorchBossEntity;

public class LivingTorchMod implements ModInitializer {
    public static final String MOD_ID = "livingtorch";
    
    public static EntityType<LivingTorchEntity> LIVING_TORCH;
    public static EntityType<TorchBossEntity> TORCH_BOSS;

    @Override
    public void onInitialize() {
        LIVING_TORCH = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "living_torch"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, LivingTorchEntity::new)
                .dimensions(EntityDimensions.fixed(0.5f, 0.9f))
                .trackRangeBlocks(16)
                .build()
        );

        TORCH_BOSS = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "torch_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.HOSTILE, TorchBossEntity::new)
                .dimensions(EntityDimensions.fixed(1.5f, 2.7f))
                .trackRangeBlocks(32)
                .build()
        );
    }
}