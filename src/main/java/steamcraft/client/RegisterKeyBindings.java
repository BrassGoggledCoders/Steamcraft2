/**
 * This class was created by <MrArcane111> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [5 Apr 2014, 20:47:06]
 */
package steamcraft.client;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterKeyBindings.
 *
 * @author warlordjones
 *
 *         5 Apr 201420:47:06
 */
public class RegisterKeyBindings
{

	/**  Key index for easy handling. */
	public static final int CUSTOM_INV = 0;

	/**  Key descriptions. */
	private static final String[] desc = { "[SC2] Vanity Inventory" };

	/**  Default key values. */
	@SuppressWarnings("unused")
	private static final int[] keyValues = { Keyboard.KEY_V };

	/** This stores all of our key bindings and is always updated with the in-game settings. */
	public static final KeyBinding[] keys = new KeyBinding[desc.length];

	/**
	 *  This initializes and registers all the key bindings.
	 */
	public static void init()
	{
		final boolean[] repeat = new boolean[desc.length];
		// just use a for loop to run through all the values
		for (int i = 0; i < desc.length; ++i)
		{
			// keys[i] = new KeyBinding(desc[i], keyValues[i]);
			repeat[i] = false;
		}

		// KeyBindingRegistry.registerKeyBinding(new KeyHandlerVanity(keys,
		// repeat));
	}
}
