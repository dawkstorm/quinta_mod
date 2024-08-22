package net.dawkmeow.quinta.item;

import net.dawkmeow.quinta.Quinta;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item AIR_STAFF = registerItem("wind_staff", new Item(new Item.Settings()));

    private static Item registerItem(String itemName, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Quinta.MOD_ID, itemName), item);
    }

    public static void registerModItems(){
        Quinta.LOGGER.info("Registering mod items for " + Quinta.MOD_ID);
    }
}
