package net.dawkmeow.quinta.util;

import net.dawkmeow.quinta.Quinta;
import net.minecraft.nbt.NbtCompound;

public class ManaData {
    public static final float maxMana = 100;

    public static float addMana(IEntityDataSaver player, float amount){
        NbtCompound nbt = player.getPersistentData();
        float mana = nbt.getFloat("mana");
        if(mana + amount >= maxMana){
            mana = maxMana;
        }
        else{
            mana += amount;
        }

        nbt.putFloat("mana", mana);
        return mana;
    }

    public static float removeMana(IEntityDataSaver player, float amount){
        NbtCompound nbt = player.getPersistentData();
        float mana = nbt.getFloat("mana");
        if(mana - amount < 0){
            mana = 0;
        }
        else{
            mana -= amount;
        }

        nbt.putFloat("mana", mana);
        return mana;
    }

    public static float getMana(IEntityDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        float mana = nbt.getFloat("mana");
        return mana;
    }
}
