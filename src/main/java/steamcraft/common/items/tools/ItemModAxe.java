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
public class ItemModAxe extends ItemModTool
{
	public static final Block[] blocksEffectiveAgainst = new Block[] { Blocks.planks, Blocks.bookshelf, Blocks.planks, Blocks.chest,
			Blocks.stone_slab, Blocks.pumpkin, Blocks.lit_pumpkin };

	public ItemModAxe(ToolMaterial toolMat)
	{
		super(toolMat.getDamageVsEntity() + 2.0F, toolMat, blocksEffectiveAgainst);
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
		if((block != null)
				&& ((block.getMaterial() == Material.wood) || (block.getMaterial() == Material.plants) || (block.getMaterial() == Material.vine)))
			super.getDigSpeed(stack, block, metadata);

		return this.efficiencyOnProperMaterial;
	}
}
