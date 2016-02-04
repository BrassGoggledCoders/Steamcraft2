
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import steamcraft.common.tiles.energy.TileStasisField;

/**
 * @author decebaldecebal
 *
 */
public class BlockStasisField extends BaseContainerBlock
{
	public BlockStasisField()
	{
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileStasisField();
	}
}
