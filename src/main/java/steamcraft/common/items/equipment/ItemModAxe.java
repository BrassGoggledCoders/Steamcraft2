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
 * The Class ItemModAxe.
 */
public class ItemModAxe extends ItemModTool
{

	/** The Constant blocksEffectiveAgainst. */
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.planks, Blocks.bookshelf, Blocks.planks, Blocks.chest,
			Blocks.stone_slab, Blocks.pumpkin, Blocks.lit_pumpkin };

	/**
	 * Instantiates a new item mod axe.
	 *
	 * @param toolMat
	 *            the tool mat
	 */
	public ItemModAxe(ToolMaterial toolMat)
	{
		super(3.0F, toolMat, blocksEffectiveAgainst);
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
			if(tag.getBoolean("hasCanister"))
			return (4.0F);
			else return 0.1F;
		}
		if (block != null && (block.getMaterial() == Material.wood || block.getMaterial() == Material.plants || block.getMaterial() == Material.vine))
		{
			super.getDigSpeed(stack, block, metadata);
		}

		return this.efficiencyOnProperMaterial;
	}

}
