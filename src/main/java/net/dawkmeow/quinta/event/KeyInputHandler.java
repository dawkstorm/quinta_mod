package net.dawkmeow.quinta.event;

import net.dawkmeow.quinta.networking.packets.TestPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String MANA_CATEGORY = "key.category.quinta.mana";
    public static final String COLLECT_MANA = "key.quinta.collect_mana";

    public static KeyBinding collectingManaKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(collectingManaKey.wasPressed()){
                //client.player.sendMessage(Text.translatable("nyaa :3"));
                ClientPlayNetworking.send(new TestPayload("124214"));
            }
        });
    }

    public static void register(){
        collectingManaKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           COLLECT_MANA, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MANA_CATEGORY
        ));
    registerKeyInputs();
    }
}
