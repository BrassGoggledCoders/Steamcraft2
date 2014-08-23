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
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Surseance
 * 
 */
public class ItemModPickaxe extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.cobblestone, Blocks.stone_slab, Blocks.stone, Blocks.sandstone,
			Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore,
			Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_block, Blocks.lapis_ore, Blocks.redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail };

	public ItemModPickaxe(ToolMaterial toolMat)
	{
		super(toolMat.getDamageVsEntity() + 1.0F, toolMat, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block par1Block, ItemStack itemstack)
	{
		return par1Block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3
				: par1Block != Blocks.diamond_block && par1Block != Blocks.diamond_ore ? par1Block != Blocks.emerald_ore
						&& par1Block != Blocks.emerald_block ? par1Block != Blocks.gold_block && par1Block != Blocks.gold_ore ? par1Block != Blocks.iron_block
						&& par1Block != Blocks.iron_ore ? par1Block != Blocks.lapis_block && par1Block != Blocks.lapis_ore ? par1Block != Blocks.redstone_ore ? par1Block
						.getMaterial() == Material.rock ? true : par1Block.getMaterial() == Material.iron ? true
						: par1Block.getMaterial() == Material.anvil
						: this.toolMaterial.getHarvestLevel() >= 2
						: this.toolMaterial.getHarvestLevel() >= 1
						: this.toolMaterial.getHarvestLevel() >= 1
						: this.toolMaterial.getHarvestLevel() >= 2
						: this.toolMaterial.getHarvestLevel() >= 2
						: this.toolMaterial.getHarvestLevel() >= 2;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if(this.isSteampowered())
		{
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.getBoolean("hasCanister"))
				return 4.0F;
			else
				return 0.1F;
		}
		if(block != null
				&& (block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil || block.getMaterial() == Material.rock))
			super.getDigSpeed(stack, block, metadata);

		return this.efficiencyOnProperMaterial;
	}
}