package steamcraft.common.blocks;

import net.minecraft.block.material.Material;

public class BlockCastIronRailing extends BaseBlock
{
	public BlockCastIronRailing(Material p_i45394_1_) {
		super(p_i45394_1_);
		setBlockName("blockCastIronRailing");
	}
	@Override
	public int getRenderType()
	{
		return 18;
	}

}
