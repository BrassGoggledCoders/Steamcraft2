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
package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;

public class ItemDimensionalPocket extends BaseItem
{
	public ItemDimensionalPocket()
	{
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			player.openGui(Steamcraft.instance, GuiIDs.POCKET, world, 0, 0, 0);
		}
		return stack;
	}
}
