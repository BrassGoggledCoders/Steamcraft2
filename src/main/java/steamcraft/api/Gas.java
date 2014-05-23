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
		setCreativeTab((CreativeTabs) null);
		Gas.isFlammable = false;
		Gas.isExplosive = false;
		dissipationHeight = 10;
		viscosity = 5;
	}

	public Gas(final boolean canBurn, final int lightLevel,
			final boolean isFlammable, final boolean isExplosive,
			final int disHeight, final int viscosity)
	{
		super(new MaterialGas(canBurn));
		setLightLevel(lightLevel);
		setCreativeTab((CreativeTabs) null);
		Gas.isFlammable = isFlammable;
		Gas.isExplosive = isExplosive;
		dissipationHeight = disHeight;
		this.viscosity = viscosity;
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int metadata)
	{
		return new TileGas(getFluid(), dissipationHeight, viscosity);
	}

	@Override
	public Fluid getFluid()
	{
		return gas;
	}

	@Override
	public FluidStack drain(final World world, final int x, final int y,
			final int z, final boolean doDrain)
	{
		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			final FluidStack fluid = TileGas.getGas();

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
	public boolean canDrain(final World world, final int x, final int y,
			final int z)
	{
		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			return true;
		}

		return false;
	}

	@Override
	public float getFilledPercentage(final World world, final int x,
			final int y, final int z)
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
	public int quantityDropped(final Random random)
	{
		return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z)
	{
		return null;
	}

	@Override
	public void onBlockDestroyedByExplosion(final World world, final int x,
			final int y, final int z, final Explosion explosion)
	{
		if (Gas.isExplosive)
		{
			Gas.createExplosion(world, x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final Block block)
	{
		if ((block == Blocks.torch) || (block == Blocks.fire)
				|| (block == Blocks.lava))
		{
			if (Gas.isExplosive)
			{
				Gas.createExplosion(world, x, y, z);
			}
			else if (Gas.isFlammable)
			{
				Gas.setFire(world, x, y, z);
			}
		}
	}

	@Override
	public void onBlockAdded(final World world, final int x, final int y,
			final int z)
	{
		super.onBlockAdded(world, x, y, z);

		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			final int volume = TileGas.getGasAmount();
			final int metadata = (volume * 15) / TileGas.VOLUME;
			world.setBlockMetadataWithNotify(x, y, z, metadata, 4);
		}
	}

	public static void createExplosion(final World world, final int x,
			final int y, final int z)
	{
		// TODO: send to packet handlers
		world.newExplosion((Entity) null, x, y, z,
				(world.rand.nextFloat() / 0.5F) + 0.9F, isFlammable, true);
	}

	public static void setFire(final World world, final int x, final int y,
			final int z)
	{
		// TODO: send to packet handlers
		int tempX = x - 1;
		int tempY = y - 1;
		int tempZ = y - 2;

		while ((world.getBlock(tempX, tempY, tempZ) instanceof Gas)
				|| (world.getBlock(tempX, tempY, tempZ).isAir(world, tempX,
						tempY, tempZ))
				|| (world.getBlock(tempX, tempY, tempZ).isReplaceable(world,
						tempX, tempY, tempZ)))
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
