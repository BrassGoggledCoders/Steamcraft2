package common.steamcraft.common.item;

import common.steamcraft.common.lib2.CreativeTabsMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTeacup extends ItemFood
{
	private final int healAmount;

	public ItemTeacup(int id, int amount, float saturation, boolean flag)
	{
		super(id, amount, saturation, flag);
		this.healAmount = amount;
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
	}

	public ItemTeacup(int id, int amount, boolean flag)
	{
		this(id, amount, 0.6F, flag);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.getHealth() < 20 && stack.getItem() == ModItems.fullTeacup)
		{
			if(player.getHealth() < 20 - healAmount)
			{
				player.setHealth(healAmount); //+= healAmount;
			} else
			{
				player.setHealth(20);
			}

			stack = new ItemStack(ModItems.emptyTeacup, 1);
		}

		return stack;
	}

	@Override
	public int getHealAmount()
	{
		return healAmount;
	}
}