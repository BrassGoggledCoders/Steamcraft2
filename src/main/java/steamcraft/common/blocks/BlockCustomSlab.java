
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockCustomSlab extends BlockSlab
{
	String type;
	Block block;

	public BlockCustomSlab(String type, Block block, Material mat)
	{
		super(false, mat);
		this.type = type;
		this.block = block;
		this.setCreativeTab(Steamcraft.tabSC2);
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return ModInfo.PREFIX + this.type;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(side, meta);
	}
}
