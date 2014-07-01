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
 * File created @ [Jun 23, 2014, 10:51:48 PM]
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
import steamcraft.common.blocks.machines.BlockSteamBoiler;
import steamcraft.common.items.ItemCanister;

/**
 * @author Decebaldecebal
 * 
 */
public class TileSteamBoiler extends BaseTileWithInventory implements IFluidHandler
{
	public static final int steamPerTick = 20;
	private static final int waterPerTick = 5; //3x3 RC boiler is 5 water/tick and 1mb for 160mb steam?

	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;

	public FluidTank waterTank;
	public FluidTank steamTank;

	public TileSteamBoiler()
	{
		super((byte) 3);

		waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
		steamTank = new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 10000);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		furnaceBurnTime = tag.getShort("BurnTime");
		currentItemBurnTime = tag.getShort("ItemTime");
		steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), tag.getShort("steamLevel")));
		waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), tag.getShort("waterLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("BurnTime", (short) furnaceBurnTime);
		tag.setShort("ItemTime", (short) currentItemBurnTime);
		tag.setShort("steamLevel", (short) steamTank.getFluidAmount());
		tag.setShort("waterLevel", (short) waterTank.getFluidAmount());
	}

	public int getBurnTimeRemainingScaled(int par1)
	{
		if (currentItemBurnTime == 0)
			currentItemBurnTime = 200;

		return (furnaceBurnTime * par1) / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		boolean var1 = furnaceBurnTime > 0;
		boolean var2 = false;

		if (!worldObj.isRemote)
		{
			if (inventory[1] != null)
			{
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(inventory[1]);

				if ((liquid != null) && (waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), false) == liquid.amount))
					if (liquid.getFluid() == FluidRegistry.WATER)
					{
						waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), true);

						if (inventory[1].stackSize > 1)
							inventory[1].stackSize--;
						else
							inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
					}
			}
			if ((inventory[2] != null) && (inventory[2].getItem() instanceof ItemCanister))
			{
				ItemCanister canister = (ItemCanister) inventory[2].getItem();
				if ((steamTank.getFluidAmount() > steamPerTick) && (canister.getFluidAmount(inventory[2]) != ItemCanister.MAX_STEAM))
				{
					canister.fill(inventory[2], new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
					steamTank.drain(steamPerTick, true);
				}
			}

			if ((getItemBurnTime() > 0) && (furnaceBurnTime == 0) && (waterTank.getFluidAmount() >= waterPerTick)
					&& (steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), false) > 0))
			{
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime() / 4;

				if (inventory[0].stackSize == 1)
					inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
				else
					--inventory[0].stackSize;
			}

			if ((furnaceBurnTime > 0) && (waterTank.getFluidAmount() >= waterPerTick) && (steamTank.getFluidAmount() <= 10000))
			{
				steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
				furnaceBurnTime--;
				waterTank.drain(waterPerTick, true);
			}
			else
				furnaceBurnTime = 0;

			if (var1 != (furnaceBurnTime > 0))
			{
				var2 = true;
				BlockSteamBoiler.updateBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (var2)
			markDirty();
	}

	private int getItemBurnTime()
	{
		if (inventory[0] == null)
			return 0;

		return TileEntityFurnace.getItemBurnTime(inventory[0]);
	}

	public int getScaledWaterLevel(int i)
	{
		return waterTank.getFluid() != null ? (int) (((float) waterTank.getFluid().amount / (float) waterTank.getCapacity()) * i) : 0;
	}

	public int getScaledSteamLevel(int i)
	{
		return steamTank.getFluid() != null ? (int) (((float) steamTank.getFluid().amount / (float) steamTank.getCapacity()) * i) : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if ((par1 == 1) && FluidContainerRegistry.isContainer(itemstack))
			return true;
		if ((par1 == 0) && (TileEntityFurnace.getItemBurnTime(itemstack) > 0))
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if ((i == 0) || (i == 1))
			return true;
		return false;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (resource.getFluid() == FluidRegistry.WATER)
			return waterTank.fill(resource, doFill);

		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(steamTank.getFluid()))
			return null;
		return steamTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return steamTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.WATER)
			return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.getFluid("steam"))
			return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { steamTank.getInfo(), waterTank.getInfo() };
	}
}
