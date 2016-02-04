
package steamcraft.common.items.tools.steam;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class ItemSteamSword extends ItemSteamTool
{
	public ItemSteamSword(ToolMaterial mat)
	{
		super(4.0F);
		this.setHarvestLevel("sword", mat.getHarvestLevel());
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}
}