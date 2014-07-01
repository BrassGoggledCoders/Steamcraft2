package steamcraft.common.config;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ConfigKeyBindings
{
	public static String keyCategory = "Steamcraft 2";
	public static KeyBinding vanity = new KeyBinding("Vanity GUI", Keyboard.KEY_V, keyCategory);

	public ConfigKeyBindings()
	{
		ClientRegistry.registerKeyBinding(vanity);
	}
}
