/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.tiles.EmptyTiles;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 *
 */
public class BlockCastIronLamp extends BlockContainer
{
	protected boolean powered;

	public BlockCastIronLamp(boolean powered)
	{
		super(Material.circuits);
		this.powered = powered;
		this.setHardness(1.5F);
		this.setResistance(7.5F);
		this.setTickRandomly(true);
		this.disableStats();
		float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		if(!powered)
			this.setCreativeTab(Steamcraft.tabSC2);

		if(powered)
			this.setLightLevel(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new EmptyTiles.TileCastIronLamp();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCastIronLampRI;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int metadata, float hitX, float hitY,
			float hitZ, int side)
	{
		if((side == 1) || (side == 3))
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if(side == 2)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		if(side == 4)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if(side == 5)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		return metadata;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			if(this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockCastIronLamp, world.getBlockMetadata(x, y, z), 2);
			}
			else if(!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockCastIronLampOn, world.getBlockMetadata(x, y, z), 2);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if(!world.isRemote)
		{
			if(this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockCastIronLamp, world.getBlockMetadata(x, y, z), 2);
			}
			else if(!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockCastIronLampOn, world.getBlockMetadata(x, y, z), 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int j, int z, Random random)
	{
		if(this.powered)
		{
			int l = world.getBlockMetadata(x, j, z);
			double d = x + 0.5F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			double d1 = j + 1.0F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			double d2 = z + 0.5F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			double d3 = 0.2199999988079071D;
			double d4 = 0.27000001072883606D;

			if(l == 1)
				world.spawnParticle("reddust", d - d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			else if(l == 2)
				world.spawnParticle("reddust", d + d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			else if(l == 3)
				world.spawnParticle("reddust", d, d1 + d3, d2 - d4, -1.0D, 0.7D, 1.0D);
			else
				world.spawnParticle("reddust", d, d1, d2, -1.0D, 0.7D, 1.0D);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return InitBlocks.blockMetal.getIcon(0, 7);
	}
}
