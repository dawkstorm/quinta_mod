package net.dawkmeow.quinta.item;

import net.dawkmeow.quinta.Quinta;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroup {

    public static final ItemGroup QUINTA_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Quinta.MOD_ID, "quinta_items"),
            FabricItemGroup.builder().
                    displayName(Text.translatable("itemGroup.quinta.quinta_items"))
                    .icon(() -> new ItemStack(ModItems.AIR_WAND)).entries(((displayContext, entries) -> {
                        entries.add(ModItems.AIR_WAND);
                        entries.add(ModItems.MANA_COLLECTOR);
                    }))
                    .build());

    public static void registerItemGroups(){
        Quinta.LOGGER.info("Registering item groups for " + Quinta.MOD_ID);
    }

}
