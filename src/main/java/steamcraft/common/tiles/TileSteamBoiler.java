/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 * 
 * File created @ [Feb 1, 2014, 12:54:18 PM]
 */
package steamcraft.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.common.blocks.machine.BlockSteamBoiler;

public class TileSteamBoiler extends TileEntityMachine implements IFluidHandler
{
	/*
	 * 20 steam/tick is basically 4 MJ/t if we use RailCraft ratios I also
	 * calculated that for a piece of coal that burns 400 ticks in this, we get
	 * 1600 MJ/t and if we transform that in EU using the MFR conversion ratio,
	 * we get 4000 EU/t, which is the same as a piece of coal in an IC2
	 * generator
	 * 
	 * Anyways, I think this is a good number Maybe we'll have to tweak the
	 * water a bit
	 */
	private final int steamPerTick = 20; // how much steam it produces per tick
	private final int waterPerTick = 30; // how much water it uses per tick
	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	public FluidTank waterTank;
	public FluidTank steamTank;

	public TileSteamBoiler()
	{
		super();
		inventory = new ItemStack[3];
		waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
		steamTank = new FluidTank(new FluidStack(
				FluidRegistry.getFluid("steam"), 0), 10000);
	}

	@Override
	public void readFromNBT(final NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		currentItemBurnTime = par1NBTTagCompound.getShort("ItemTime");
		steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"),
				par1NBTTagCompound.getShort("steamLevel")));
		waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"),
				par1NBTTagCompound.getShort("waterLevel")));
	}

	@Override
	public void writeToNBT(final NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
		par1NBTTagCompound.setShort("ItemTime", (short) currentItemBurnTime);
		par1NBTTagCompound.setShort("steamLevel",
				(short) steamTank.getFluidAmount());
		par1NBTTagCompound.setShort("waterLevel",
				(short) waterTank.getFluidAmount());
	}

	public int getBurnTimeRemainingScaled(final int par1)
	{
		if (currentItemBurnTime == 0)
		{
			currentItemBurnTime = 200;
		}

		return furnaceBurnTime * par1 / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		final boolean var1 = furnaceBurnTime > 0;
		boolean var2 = false;

		if (!worldObj.isRemote)
		{
			if (inventory[1] != null)
			{
				final FluidStack liquid = FluidContainerRegistry
						.getFluidForFilledItem(inventory[1]);

				if (liquid != null
						&& waterTank.fill(
								new FluidStack(FluidRegistry.getFluid("water"),
										liquid.amount), false) == liquid.amount)
				{
					if (liquid.getFluid() == FluidRegistry.WATER)
					{
						waterTank.fill(
								new FluidStack(FluidRegistry.getFluid("water"),
										liquid.amount), true);

						if (inventory[1].stackSize > 1)
						{
							inventory[1].stackSize--;
						}
						else
						{
							inventory[1] = inventory[1].getItem()
									.getContainerItem(inventory[1]);
						}
					}
				}
			}
			// TODO: Readd this functionality
			/*
			 * if(inventory[2] != null && inventory[2].getItem() ==
			 * ConfigItems.itemCanisterGas)
			 * if(!ItemCanister.isFull(inventory[2]))
			 * this.steamTank.drain(ItemCanister.setSteam(inventory[2],
			 * this.steamTank.getFluidAmount()), true);
			 */
			if (getItemBurnTime() > 0
					&& furnaceBurnTime == 0
					&& waterTank.getFluidAmount() >= waterPerTick
					&& steamTank.fill(
							new FluidStack(FluidRegistry.getFluid("steam"),
									steamPerTick), false) > 0)
			{
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime() / 4;

				if (inventory[0].stackSize == 1)
				{
					inventory[0] = inventory[0].getItem().getContainerItem(
							inventory[0]);
				}
				else
				{
					--inventory[0].stackSize;
				}
			}

			if (furnaceBurnTime > 0
					&& waterTank.getFluidAmount() >= waterPerTick
					&& steamTank.getFluidAmount() <= 10000)
			{
				steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"),
						steamPerTick), true);
				furnaceBurnTime--;
				waterTank.drain(waterPerTick, true);
			}
			else
			{
				furnaceBurnTime = 0;
			}

			if (var1 != furnaceBurnTime > 0)
			{
				var2 = true;
				BlockSteamBoiler.updateFurnaceBlockState(furnaceBurnTime > 0,
						worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (var2)
		{
			markDirty();
		}
	}

	private int getItemBurnTime()
	{
		if (inventory[0] == null)
		{
			return 0;
		}

		return TileEntityFurnace.getItemBurnTime(inventory[0]);
	}

	public int getScaledWaterLevel(final int i)
	{
		return waterTank.getFluid() != null ? (int) (((float) waterTank
				.getFluid().amount / (float) (waterTank.getCapacity())) * i)
				: 0;
	}

	public int getScaledSteamLevel(final int i)
	{
		return steamTank.getFluid() != null ? (int) (((float) steamTank
				.getFluid().amount / (float) (steamTank.getCapacity())) * i)
				: 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(final int par1)
	{
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(final int par1, final ItemStack itemstack,
			final int par3)
	{
		if (par1 == 1 && FluidContainerRegistry.isContainer(itemstack))
		{
			return true;
		}
		if (par1 == 0 && TileEntityFurnace.getItemBurnTime(itemstack) > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(final int par1,
			final ItemStack par2ItemStack, final int par3)
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(final int i, final ItemStack itemstack)
	{
		if (i == 0 || i == 1)
		{
			return true;
		}
		return false;
	}

	@Override
	public int fill(final ForgeDirection from, final FluidStack resource,
			final boolean doFill)
	{
		if (resource.getFluid() == FluidRegistry.WATER)
		{
			return waterTank.fill(resource, doFill);
		}

		return 0;
	}

	@Override
	public FluidStack drain(final ForgeDirection from,
			final FluidStack resource, final boolean doDrain)
	{
		if (resource == null || !resource.isFluidEqual(steamTank.getFluid()))
		{
			return null;
		}
		return steamTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(final ForgeDirection from, final int maxDrain,
			final boolean doDrain)
	{
		return steamTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(final ForgeDirection from, final Fluid fluid)
	{
		if (fluid == FluidRegistry.WATER)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(final ForgeDirection from, final Fluid fluid)
	{
		if (fluid == FluidRegistry.getFluid("steam"))
		{
			return true;
		}
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(final ForgeDirection from)
	{
		return new FluidTankInfo[] { steamTank.getInfo(), waterTank.getInfo() };
	}
}
