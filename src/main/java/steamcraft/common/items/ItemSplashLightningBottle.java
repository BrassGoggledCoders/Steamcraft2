
package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import steamcraft.common.entities.projectile.EntitySplashLightningBottle;

public class ItemSplashLightningBottle extends BaseItem
{
	public ItemSplashLightningBottle()
	{
		this.setMaxStackSize(3);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F);

		if (!world.isRemote)
		{
			world.spawnEntityInWorld(new EntitySplashLightningBottle(world, player));
		}

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		return stack;
	}
}
