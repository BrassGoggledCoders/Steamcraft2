
package steamcraft.common.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockCustomFence extends BlockFence
{
	public BlockCustomFence(String type, Material mat)
	{
		super(ModInfo.PREFIX + type, mat);
		this.setCreativeTab(Steamcraft.tabSC2);
	}
}
