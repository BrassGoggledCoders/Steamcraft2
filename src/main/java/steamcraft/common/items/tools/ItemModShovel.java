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
import net.minecraft.item.ItemStack;

/**
 * @author Surseance
 * 
 */
public class ItemModShovel extends ItemModTool
{

	public ItemModShovel(ToolMaterial mat)
	{
		super(mat.getDamageVsEntity() + 1.0F, mat);
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return block.getMaterial() == Material.snow || super.canHarvestBlock(block, stack);
	}
}