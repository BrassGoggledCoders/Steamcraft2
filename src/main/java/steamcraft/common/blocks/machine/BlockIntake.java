/*
 *
 */
package steamcraft.common.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileIntake;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockIntake.
 */
public class BlockIntake extends BlockContainerMod
{

	/**
	 * Instantiates a new block intake.
	 *
	 * @param p_i45394_1_
	 *            the p_i45394_1_
	 */
	public BlockIntake(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setBlockName("blockIntake");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft
	 * .world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileIntake();
	}

}
