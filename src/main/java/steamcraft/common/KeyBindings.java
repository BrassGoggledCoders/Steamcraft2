/*
 * 
 */
package steamcraft.common;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyBindings.
 */
public class KeyBindings
{

	/** The key open vanity gui. */
	static KeyBinding keyOpenVanityGUI;

	/**
	 * Register keys.
	 */
	public static void registerKeys()
	{
		keyOpenVanityGUI = new KeyBinding("Open/Close Vanity GUI", Keyboard.KEY_V, "key.categories.sc2");

		ClientRegistry.registerKeyBinding(keyOpenVanityGUI);
	}
}
