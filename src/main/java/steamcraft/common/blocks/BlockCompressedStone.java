package steamcraft.common.blocks;

import net.minecraft.block.material.Material;

public class BlockCompressedStone extends BaseBlock
{

	public BlockCompressedStone(Material mat)
	{
		super(mat);
		this.setBlockName("blockCompressedStone");
		setHardness(2.2F);
		setResistance(15.0F);
	}

}
