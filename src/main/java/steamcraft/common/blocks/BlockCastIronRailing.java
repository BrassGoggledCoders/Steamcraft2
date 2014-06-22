/*
 * 
 */
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCastIronRailing.
 */
public class BlockCastIronRailing extends BaseBlock
{

	/**
	 * Instantiates a new block cast iron railing.
	 * 
	 * @param p_i45394_1_
	 *            the p_i45394_1_
	 */
	public BlockCastIronRailing(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setBlockName("blockCastIronRailing");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType()
	{
		return 18;
	}

}
