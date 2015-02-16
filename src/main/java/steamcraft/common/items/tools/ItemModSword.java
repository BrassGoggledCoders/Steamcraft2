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
package steamcraft.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance
 * 
 */
public class ItemModSword extends ItemModTool
{
	public ItemModSword(ToolMaterial mat)
	{
		super(mat.getDamageVsEntity() + 4.0F, mat);
		this.maxStackSize = 1;
		this.setMaxDamage(mat.getMaxUses());
		this.setHarvestLevel("sword", mat.getHarvestLevel());
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if(block == Blocks.web)
			return 15.0F;
		return super.getDigSpeed(stack, block, metadata);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if(block.getBlockHardness(world, x, y, z) != 0.0D)
			stack.damageItem(2, living);

		return true;
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

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.web;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}
}