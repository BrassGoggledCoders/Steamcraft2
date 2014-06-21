/*
 * 
 */
package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModAxe.
 */
public class ItemModAxe extends ItemModTool {
	
	/** The Constant blocksEffectiveAgainst. */
	public static final Block[] blocksEffectiveAgainst = new Block[] {
			Blocks.planks, Blocks.bookshelf, Blocks.planks, Blocks.chest,
			Blocks.stone_slab, Blocks.pumpkin, Blocks.lit_pumpkin };

	/**
	 * Instantiates a new item mod axe.
	 *
	 * @param toolMat the tool mat
	 */
	public ItemModAxe(ToolMaterial toolMat) {
		super(3.0F, toolMat, blocksEffectiveAgainst);
	}

	/* (non-Javadoc)
	 * @see steamcraft.common.items.equipment.ItemModTool#getDigSpeed(net.minecraft.item.ItemStack, net.minecraft.block.Block, int)
	 */
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata) {
		return block != null
				&& (block.getMaterial() == Material.wood
						|| block.getMaterial() == Material.plants || block
						.getMaterial() == Material.vine) ? this.efficiencyOnProperMaterial
				: super.getDigSpeed(stack, block, metadata);
	}
}
