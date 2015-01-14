package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
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
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	{
		if(!p_77659_3_.capabilities.isCreativeMode)
		{
			--p_77659_1_.stackSize;
		}

		p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F);

		if(!p_77659_2_.isRemote)
		{
			p_77659_2_.spawnEntityInWorld(new EntitySplashLightningBottle(p_77659_2_, p_77659_3_));
		}

		return p_77659_1_;
	}
}
