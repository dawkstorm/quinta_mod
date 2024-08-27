package net.dawkmeow.quinta.event;

import net.dawkmeow.quinta.networking.packets.AttackKeyPacket;
import net.dawkmeow.quinta.networking.packets.Manipulate1KeyPacket;
import net.dawkmeow.quinta.networking.packets.Manipulate2KeyPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String MANA_CATEGORY = "key.category.quinta.mana";
    public static final String ATTACK_WAND_KEY = "key.quinta.attack_wand_key";
    public static final String MANIPULATE_1 = "key.quinta.manipulate_one";
    public static final String MANIPULATE_2 = "key.quinta.manipulate_two";

    public static KeyBinding attackKey;
    public static KeyBinding manipulate1;
    public static KeyBinding manipulate2;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(attackKey.wasPressed()){
                ClientPlayNetworking.send(new AttackKeyPacket("124214"));
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(manipulate1.isPressed()){
                ClientPlayNetworking.send(new Manipulate1KeyPacket("124214"));
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(manipulate2.isPressed()){
                ClientPlayNetworking.send(new Manipulate2KeyPacket("124214"));
            }
        });
    }

    public static void register(){
        attackKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                ATTACK_WAND_KEY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MANA_CATEGORY
        ));
        manipulate1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                MANIPULATE_1, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, MANA_CATEGORY
        ));
        manipulate2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                MANIPULATE_2, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MANA_CATEGORY
        ));
    registerKeyInputs();
    }
}
