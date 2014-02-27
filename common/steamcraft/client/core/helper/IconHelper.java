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
 * File created @ [Jan 30, 2014, 5:16:43 PM]
 */
package common.steamcraft.client.core.helper;

import common.steamcraft.client.lib2.ClientResources;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

/**
 * @author Vazkii from Thaumic Tinkerer
 * (https://github.com/Vazkii/ThaumicTinkerer/IconHelper)
 *
 */
public class IconHelper {
	private static Icon emptyTexture;

	public static Icon forName(IconRegister icon, String name) {
		return icon.registerIcon(ClientResources.PREFIX_MOD + name);
	}

	public static Icon forNameRaw(IconRegister icon, String name) {
		return icon.registerIcon(name);
	}

	public static Icon forBlock(IconRegister icon, Block block) {
		return forNameRaw(icon, block.getUnlocalizedName().replaceAll("tile.", ""));
	}

	public static Icon forItem(IconRegister icon, Item item) {
		return forName(icon, item.getUnlocalizedName().replaceAll("item.", ""));
	}
}
