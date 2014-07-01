package steamcraft.common.blocks.machines;

import steamcraft.common.tiles.TileCopperTank;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCopperTank extends BlockContainerMod
{
	public BlockCopperTank(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCopperTank();
	}

}
