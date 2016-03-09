package xyz.brassgoggledcoders.steamcraft.common.blocks;

import net.minecraft.block.BlockChest;
import xyz.brassgoggledcoders.steamcraft.client.lib.RenderIDs;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;

public class BlockTrunk extends BlockChest
{

	public BlockTrunk()
	{
		super(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockTrunkRI;
	}

}
