package steamcraft.common.blocks;

import steamcraft.common.config.ConfigBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import boilerplate.steamapi.IChiselable;

public class BlockEtherium extends BaseBlock implements IChiselable
{

	public BlockEtherium(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setBlockName("blockEtherium");
	}

	@Override
	public Block getChiseledVariant()
	{
		return ConfigBlocks.blockEngraved;
	}

	@Override
	public int getChiseledVariantMeta()
	{
		return 9;
	}

}
