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
public class ItemModShovel extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow,
			Blocks.snow_layer, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium };

	public ItemModShovel(ToolMaterial toolMat)
	{
		super(toolMat.getDamageVsEntity() + 1.0F, toolMat, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack)
	{
		return block == Blocks.snow ? true : block == Blocks.snow_layer;
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