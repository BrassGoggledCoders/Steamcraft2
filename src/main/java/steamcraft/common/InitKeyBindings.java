/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * @author warlordjones
 *
 */
public class InitKeyBindings
{
	public static String keyCategory = "Steamcraft 2";
	//public static KeyBinding vanity = new KeyBinding("Vanity GUI", Keyboard.KEY_V, keyCategory);

	public InitKeyBindings()
	{
		//ClientRegistry.registerKeyBinding(vanity);
	}
}
