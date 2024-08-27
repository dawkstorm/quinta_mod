package net.dawkmeow.quinta.entity;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.entity.custom.AirScooterEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<AirScooterEntity> AIR_SCOOTER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Quinta.MOD_ID,"air_scooter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, AirScooterEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f))
                    .build());

    /*public static final EntityType<EndCrystalEntity> END_CRYSTAL = register(
            "end_crystal",
            EntityType.Builder.<EndCrystalEntity>create(EndCrystalEntity::new, SpawnGroup.MISC)
                    .makeFireImmune()
                    .dimensions(2.0F, 2.0F)
                    .maxTrackingRange(16)
                    .trackingTickInterval(Integer.MAX_VALUE)
    );*/

    public static void register(){
        Quinta.LOGGER.info("Registering entities...");
    }

}
