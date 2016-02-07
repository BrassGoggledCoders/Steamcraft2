
package steamcraft.common.items;

import java.util.Arrays;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.LibInfo;

/**
 * @author warlordjones
 *
 */
public class ItemWithCraftingDurability extends BaseItem
{
	boolean hasEffect = false;

	public ItemWithCraftingDurability()
	{
		super(Steamcraft.instance);
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses());
		this.setNoRepair();
	}

	@Override
	public ItemStack getContainerItem(ItemStack ist)
	{
		ist.setItemDamage(ist.getItemDamage() + 1);
		if (ist.getItemDamage() > ist.getMaxDamage())
			ist = null;

		return ist;
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
	{
		return false;
	}

	public Item setHasEffect()
	{
		this.hasEffect = true;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack p_77636_1_)
	{
		return this.hasEffect;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (player.getCommandSenderName() != null)
			{
				if ((stack.getItem() == InitItems.itemHammer) && Arrays.asList(LibInfo.names).contains(player.getUniqueID().toString()))
				{
					stack.func_150996_a(InitItems.itemBugHammer);
				}
			}
		}
	}
}
