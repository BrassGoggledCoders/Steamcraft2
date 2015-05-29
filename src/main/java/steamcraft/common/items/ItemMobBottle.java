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
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import steamcraft.common.entities.projectile.EntityMobBottle;

public class ItemMobBottle extends BaseItem
{
	public ItemMobBottle()
	{
		this.setMaxStackSize(1);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int useCount)
	{
		if(!stack.hasTagCompound())
			return;
		if(useCount > (this.getMaxItemUseDuration(stack) / 2))
		{
			if(!player.capabilities.isCreativeMode)
			{
				--stack.stackSize;
			}

			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F);

			if(!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityMobBottle(world, player, stack.getTagCompound().getTag("storedCreature")));
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
	{
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		return stack;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World p_77654_2_, EntityPlayer p_77654_3_)
	{
		return stack;
	}
}
