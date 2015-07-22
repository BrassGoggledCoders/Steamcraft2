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
package steamcraft.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

// TODO: Auto-generated Javadoc
/**
 * The Interface IModule.
 */
public interface IModule
{
	// Localized Name
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();

	// Unlocalized Name
	/**
	 * Gets the module id.
	 *
	 * @return the module id
	 */
	public String getModuleId();

	/**
	 * Apply module effect.
	 *
	 * @param world
	 *            the world
	 * @param player
	 *            the player
	 * @param stack
	 *            the stack
	 */
	public void applyModuleEffect(World world, EntityPlayer player, ItemStack stack);

	public void onModuleAdded(World world);

	public void onModuleRemoved(World world);
}
