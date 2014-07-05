package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.blocks.machines.BlockContainerMod;
import steamcraft.common.tiles.TileCopperWire;

public class BlockCopperWire extends BlockContainerMod
{

	public BlockCopperWire(Material p_i45394_1_)
	{
		super(p_i45394_1_);

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileCopperWire();
	}
}
