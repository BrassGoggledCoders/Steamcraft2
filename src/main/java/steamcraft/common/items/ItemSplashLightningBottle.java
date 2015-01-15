package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.entities.projectile.EntitySplashLightningBottle;

public class ItemSplashLightningBottle extends BaseItem
{
	public ItemSplashLightningBottle()
	{
		this.setMaxStackSize(16);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, int useCount)
	{
		if(useCount > getMaxItemUseDuration(p_77659_1_) / 2)
		{
			if(!p_77659_3_.capabilities.isCreativeMode)
			{
				--p_77659_1_.stackSize;
			}

			p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F);

			if(!p_77659_2_.isRemote)
			{
				p_77659_2_.spawnEntityInWorld(new EntitySplashLightningBottle(p_77659_2_, p_77659_3_, useCount));
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
	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
	{
		return p_77654_1_;
	}
}
