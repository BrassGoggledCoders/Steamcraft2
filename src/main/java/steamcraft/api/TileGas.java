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
 * File created @ [May 7, 2014, 11:43:24 AM]
 */
package steamcraft.api;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class TileGas extends TileEntity
{
	private static FluidStack gas;
	public static final int VOLUME = FluidContainerRegistry.BUCKET_VOLUME * 4;
	private final Random random;
	public static int count;
	private final int dissipationHeight;
	private static int meta;
	private int viscosity;
	GasUtils utils;

	GasTuple[] pos = new GasTuple[] { new GasTuple(0, 1), new GasTuple(1, 0),
			new GasTuple(0, -1), new GasTuple(-1, 0) };

	public TileGas(final int disHeight)
	{
		random = new Random();
		TileGas.count = random.nextInt(10);
		dissipationHeight = disHeight;
		utils = new GasUtils(worldObj, xCoord, yCoord, zCoord);
		blockMetadata = meta;
	}

	public TileGas(final Fluid type, final int disHeight, final int viscosity)
	{
		TileGas.gas = new FluidStack(type, VOLUME);
		random = new Random(System.nanoTime());
		TileGas.count = random.nextInt(10);
		dissipationHeight = disHeight;
		utils = new GasUtils(worldObj, xCoord, yCoord, zCoord);
		blockMetadata = meta;
		this.viscosity = viscosity;
	}

	@Override
	public void updateEntity()
	{
		boolean go = false;

		if (!worldObj.isRemote)
		{
			GasUtils.updateForFireCheck();

			while (GasUtils.isOffsetAir(0, 1, 0) && (dissipationHeight != 0))
			{
				GasUtils.moveGas(0, 1, 0);
				go = true;
			}

			if (!(GasUtils.isOffsetAir(0, 1, 0)))
			{
				for (; (viscosity < 10) && !(go); viscosity++)
				{
					int x, z;
					int randInt = worldObj.rand.nextInt(TileGas.getGasAmount());
					x = pos[randInt].x();
					z = pos[randInt].z();

					if (GasUtils.isOffsetAir(x, 0, z)
							|| (GasUtils.canGasDestroyBlock(x, 0, z)))
					{
						TileGas.setGasAmount(TileGas.getGasAmount() / 2);
						GasUtils.splitAndMoveGas(x, 0, z);
						go = true;
					}

					randInt++;

					if (randInt == TileGas.getGasAmount())
					{
						randInt = 0;
					}
				}
			}
		}
	}

	/**
	 * Grabs the current gas amount.
	 * 
	 * @return - gas amount
	 */
	public static int getGasAmount()
	{
		return gas.amount;
	}

	/**
	 * Sets a new gas amount.
	 * 
	 * @param amount
	 *            - new amount
	 */
	public static void setGasAmount(final int amount)
	{
		gas.amount = amount;
	}

	/**
	 * Grabs the FluidStack associated with this gas.
	 * 
	 * @return - FluidStack
	 */
	public static FluidStack getGas()
	{
		return gas;
	}

	/**
	 * Grabs a static reference of the gas' metadata.
	 * 
	 * @return - gas metadata
	 */
	public static int getMeta()
	{
		return meta;
	}

	@Override
	public void writeToNBT(final NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		TileGas.gas.writeToNBT(tagCompound);
		tagCompound.setBoolean("Flammable", Gas.isFlammable);
		tagCompound.setBoolean("Explosive", Gas.isExplosive);
	}

	@Override
	public void readFromNBT(final NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		Gas.isFlammable = tagCompound.getBoolean("Flammable");
		Gas.isExplosive = tagCompound.getBoolean("Explosive");

		if (tagCompound.hasKey("Amount"))
		{
			TileGas.gas = FluidStack.loadFluidStackFromNBT(tagCompound);
		}
	}
}
