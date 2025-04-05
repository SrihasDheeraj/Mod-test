package com.helpmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class HelpKeybindMod implements ClientModInitializer {
    private static KeyBinding helpKeyBind;

    @Override
    public void onInitializeClient() {
        helpKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.helpmod.help",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PERIOD,
                "category.helpmod.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (helpKeyBind.wasPressed()) {
                if (client.player != null) {
                    client.player.networkHandler.sendChatMessage("/help");
                }
            }
        });
    }
}
