package net.dawkmeow.quinta.item;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.item.custom.ManaCollector;
import net.dawkmeow.quinta.item.custom.AirWand;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item WIND_STAFF = registerItem("air_wand",
            new AirWand(new Item.Settings().attributeModifiers(AirWand.createAttributeModifiers()).maxCount(1)));

    public static final Item MANA_COLLECTOR = registerItem("mana_collector",
            new ManaCollector(new Item.Settings().attributeModifiers(ManaCollector.createAttributeModifiers()).maxDamage(250).maxCount(1)));

    private static Item registerItem(String itemName, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Quinta.MOD_ID, itemName), item);
    }

    public static void registerModItems(){
        Quinta.LOGGER.info("Registering mod items for " + Quinta.MOD_ID);
    }
}
