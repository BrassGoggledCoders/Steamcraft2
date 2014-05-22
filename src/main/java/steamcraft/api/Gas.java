/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [May 7, 2014, 11:31:42 AM]
 */
package steamcraft.api;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class Gas extends BlockContainer implements IFluidBlock
{
	public Fluid gas;
	public static boolean isFlammable;
	public static boolean isExplosive;
	public int dissipationHeight;
	public int viscosity;

	protected IIcon[] icon = new IIcon[16];

	public Gas()
	{
		super(new MaterialGas(false));
		this.setCreativeTab((CreativeTabs)null);
		Gas.isFlammable = false;
		Gas.isExplosive = false;
		this.dissipationHeight = 10;
		this.viscosity = 5;
	}

	public Gas(boolean canBurn, int lightLevel, boolean isFlammable, boolean isExplosive, int disHeight, int viscosity)
	{
		super(new MaterialGas(canBurn));
		this.setLightLevel(lightLevel);
		this.setCreativeTab((CreativeTabs)null);
		Gas.isFlammable = isFlammable;
		Gas.isExplosive = isExplosive;
		this.dissipationHeight = disHeight;
		this.viscosity = viscosity;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileGas(this.getFluid(), this.dissipationHeight, this.viscosity);
	}

	@Override
	public Fluid getFluid()
	{
		return this.gas;
	}

	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			TileGas teGas = (TileGas)te;
			FluidStack fluid = TileGas.getGas();

			if (doDrain)
			{
				world.setBlockToAir(x, y, z);
				world.removeTileEntity(x, y, z);
			}

			return fluid;
		}

		return null;
	}

	@Override
	public boolean canDrain(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
			return true;

		return false;
	}
	
	@Override
	public float getFilledPercentage(World world, int x, int y, int z)
	{
		return 0;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1; // 0 for solids, 1 for alpha
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		if (Gas.isExplosive)
			Gas.createExplosion(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if ((block == Blocks.torch) || (block == Blocks.fire) || (block == Blocks.lava))
		{
			if (Gas.isExplosive)
				Gas.createExplosion(world, x, y, z);
			else if (Gas.isFlammable)
				Gas.setFire(world, x, y, z);
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);

		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			TileGas tg = (TileGas)te;

			int volume = TileGas.getGasAmount();
			int metadata = (volume * 15) / TileGas.VOLUME;
			world.setBlockMetadataWithNotify(x, y, z, metadata, 4);
		}
	}

	public static void createExplosion(World world, int x, int y, int z)
	{
		// TODO: send to packet handlers
		world.newExplosion((Entity)null, x, y, z, (world.rand.nextFloat() / 0.5F) + 0.9F, isFlammable, true);
	}

	public static void setFire(World world, int x, int y, int z)
	{
		// TODO: send to packet handlers
		int tempX = x - 1;
		int tempY = y - 1;
		int tempZ = y - 2;

		while ((world.getBlock(tempX, tempY, tempZ) instanceof Gas) || (world.getBlock(tempX, tempY, tempZ).isAir(world, tempX, tempY, tempZ)) || (world.getBlock(tempX, tempY, tempZ).isReplaceable(world, tempX, tempY, tempZ)))
		{
			tempX--;
			tempY--;
			tempZ--;
		}
		
		tempX++;
		tempY++;
		tempZ++;
		
		world.setBlockToAir(x, y, z);
		world.removeTileEntity(x, y, z);
		world.setBlock(tempX, tempY, tempZ, Blocks.fire);
	}
}
