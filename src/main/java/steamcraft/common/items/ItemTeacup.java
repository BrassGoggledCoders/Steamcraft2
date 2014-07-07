package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTeacup extends BaseItem
{
	public ItemTeacup()
	{
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(3);
		this.setNoRepair();
		this.setFull3D();
	}
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItemDamage() > 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 50, 100));
			stack.damageItem(1, player);
		}
		return stack;
	}
}
