/*
 *
 */
package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModShovel.
 */
public class ItemModShovel extends ItemModTool
{

	/** The Constant blocksEffectiveAgainst. */
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow,
			Blocks.snow_layer, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium };

	/**
	 * Instantiates a new item mod shovel.
	 * 
	 * @param toolMat
	 *            the tool mat
	 */
	public ItemModShovel(ToolMaterial toolMat)
	{
		super(toolMat.getDamageVsEntity() + 1.0F, toolMat, blocksEffectiveAgainst);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#canHarvestBlock(net.minecraft.block.Block,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.snow ? true : block == Blocks.snow_layer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.items.equipment.ItemModTool#getDigSpeed(net.minecraft
	 * .item.ItemStack, net.minecraft.block.Block, int)
	 */
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (this.isSteampowered())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.getBoolean("hasCanister"))
				return (4.0F);
			else
				return 0.1F;
		}
		if ((block != null)
				&& ((block.getMaterial() == Material.iron) || (block.getMaterial() == Material.anvil) || (block.getMaterial() == Material.rock)))
		{
			super.getDigSpeed(stack, block, metadata);
		}

		return this.efficiencyOnProperMaterial;
	}
}