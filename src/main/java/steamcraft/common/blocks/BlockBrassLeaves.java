package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import steamcraft.common.Steamcraft;

public class BlockBrassLeaves extends Block
{

	public BlockBrassLeaves(Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Steamcraft.tabSC2);
		setBlockName("blockBrassLeaves");
		setBlockUnbreakable();
	}
}
