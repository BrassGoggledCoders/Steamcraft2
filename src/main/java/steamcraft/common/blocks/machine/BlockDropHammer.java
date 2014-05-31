package steamcraft.common.blocks.machine;

import cpw.mods.fml.common.FMLLog;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.BlockDropHammerFrame;
import steamcraft.common.tiles.TileDropHammer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDropHammer extends BlockContainerMod
{

	int x, y, z;
	TileDropHammer tile = new TileDropHammer();

	public BlockDropHammer(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2)
	{
		if(isValid(x, y, z, world))
		{
		return tile;
		}
		else
		return null;
	}
	public boolean isValid(int x, int y, int z, World world)
	{
		if(world.getBlock(x, y + 1, z) instanceof BlockDropHammerFrame)
		{
		tile.validate();
		return true;
		}
		else
		{
		tile.invalidate();
		return false;
		}
	}
	//Hackses, hackses
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int lol)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		FMLLog.info(String.valueOf(x), String.valueOf(x));
		return lol;
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if(world.getBlock(x, y + 1, z) instanceof BlockDropHammerFrame)
		{
		tile.validate();
		}
		else
		tile.invalidate();
	}
}
