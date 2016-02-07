
package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.entities.projectile.EntityFieldManipulator;

public class ItemFieldManipulator extends BaseItem
{
	public ItemFieldManipulator()
	{
		super(Steamcraft.instance);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((itemRand.nextFloat() * 0.4F) + 0.8F));

		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityFieldManipulator(world, player));
		}

		return stack;
	}
}
