/*
 *
 */
package steamcraft.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModPickaxe.
 */
public class ItemModPickaxe extends ItemModTool
{

	/** The Constant blocksEffectiveAgainst. */
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.stone_slab, Blocks.stone, Blocks.sandstone,
			Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
			Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_block, Blocks.lapis_ore, Blocks.redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail };

	/**
	 * Instantiates a new item mod pickaxe.
	 * 
	 * @param toolMat
	 *            the tool mat
	 */
	public ItemModPickaxe(ToolMaterial toolMat)
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
	public boolean canHarvestBlock(Block par1Block, ItemStack itemstack)
	{
		return par1Block == Blocks.obsidian ? toolMaterial.getHarvestLevel() == 3
				: ((par1Block != Blocks.diamond_block) && (par1Block != Blocks.diamond_ore) ? ((par1Block != Blocks.emerald_ore)
						&& (par1Block != Blocks.emerald_block) ? ((par1Block != Blocks.gold_block) && (par1Block != Blocks.gold_ore) ? ((par1Block != Blocks.iron_block)
						&& (par1Block != Blocks.iron_ore) ? ((par1Block != Blocks.lapis_block) && (par1Block != Blocks.lapis_ore) ? (par1Block != Blocks.redstone_ore ? (par1Block
						.getMaterial() == Material.rock ? true : (par1Block.getMaterial() == Material.iron ? true
						: par1Block.getMaterial() == Material.anvil)) : toolMaterial.getHarvestLevel() >= 2)
						: toolMaterial.getHarvestLevel() >= 1)
						: toolMaterial.getHarvestLevel() >= 1)
						: toolMaterial.getHarvestLevel() >= 2)
						: toolMaterial.getHarvestLevel() >= 2)
						: toolMaterial.getHarvestLevel() >= 2);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (isSteampowered())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if (tag.getBoolean("hasCanister"))
				return (4.0F);
			else
				return 0.1F;
		}
		if ((block != null)
				&& ((block.getMaterial() == Material.iron) || (block.getMaterial() == Material.anvil) || (block.getMaterial() == Material.rock)))
			super.getDigSpeed(stack, block, metadata);

		return efficiencyOnProperMaterial;
	}
}