
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import steamcraft.common.tiles.TileIntake;

/**
 * @author decebaldecebal
 *
 */
public class BlockIntake extends BaseContainerBlock
{
	public BlockIntake()
	{
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileIntake();
	}
}
