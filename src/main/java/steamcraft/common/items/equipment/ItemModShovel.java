/*
 * 
 */
package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

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
		super(1.0F, toolMat, blocksEffectiveAgainst);
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
}