
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
