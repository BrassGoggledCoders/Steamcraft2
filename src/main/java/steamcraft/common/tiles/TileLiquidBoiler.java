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
package steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

import boilerplate.api.IOpenableGUI;
import steamcraft.client.gui.GuiLiquidBoiler;
import steamcraft.common.blocks.machines.BlockBaseBoiler;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.tiles.container.ContainerLiquidBoiler;

/**
 * @author Decebaldecebal
 *
 */
public class TileLiquidBoiler extends TileBaseBoiler implements IOpenableGUI
{
	public static final int steamPerTick = 20;
	protected static final int waterPerTick = 5; // 3x3 RC boiler is 5
													// water/tick

	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;

	public FluidTank fuelTank;

	public TileLiquidBoiler()
	{
		super();
		this.fuelTank = new FluidTank(5000);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.fuelTank.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.fuelTank.writeToNBT(tag);
	}

	@Override
	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
			this.currentItemBurnTime = 200;

		return (this.furnaceBurnTime * par1) / this.currentItemBurnTime;
	}

	@Override
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		boolean var1 = this.furnaceBurnTime > 0;
		boolean var2 = false;

		if (!this.worldObj.isRemote)
		{
			if (this.inventory[1] != null)
			{
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(this.inventory[1]);

				if ((liquid != null) && (this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), false) == liquid.amount))
					if (liquid.getFluid() == FluidRegistry.WATER)
					{
						this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), true);

						if (this.inventory[1].stackSize > 1)
							this.inventory[1].stackSize--;
						else
							this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
					}
			}
			if ((this.inventory[2] != null) && (this.inventory[2].getItem() instanceof ItemCanister))
			{
				ItemCanister canister = (ItemCanister) this.inventory[2].getItem();
				if ((this.steamTank.getFluidAmount() >= steamPerTick) && (canister.getFluidAmount(this.inventory[2]) != canister.maxSteam))
				{
					canister.fill(this.inventory[2], new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
					this.steamTank.drain(steamPerTick, true);
				}
			}

			if ((this.getItemBurnTime(inventory[0]) > 0) && (this.furnaceBurnTime == 0) && (this.waterTank.getFluidAmount() >= waterPerTick)
					&& (this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), false) > 0))
			{
				this.currentItemBurnTime = this.furnaceBurnTime = this.getItemBurnTime(inventory[0]) / 4;

				if (this.inventory[0].stackSize == 1)
					this.inventory[0] = this.inventory[0].getItem().getContainerItem(this.inventory[0]);
				else
					--this.inventory[0].stackSize;
			}

			if ((this.furnaceBurnTime > 0) && (this.waterTank.getFluidAmount() >= waterPerTick) && (this.steamTank.getFluidAmount() < 10000))
			{
				this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
				this.furnaceBurnTime--;
				this.waterTank.drain(waterPerTick, true);
			}
			else
				this.furnaceBurnTime = 0;

			if (var1 != (this.furnaceBurnTime > 0))
			{
				var2 = true;
				BlockBaseBoiler.updateBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (var2)
			this.markDirty();
	}

	public int getScaledFuelLevel(int i)
	{
		return this.fuelTank.getFluid() != null ? (int) (((float) this.fuelTank.getFluid().amount / (float) this.fuelTank.getCapacity()) * i) : 0;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		// TODO
		return true;
		// return ((par1 == 1) && FluidContainerRegistry.isContainer(itemstack))
		// || ((par1 == 0) && (TileEntityFurnace.getItemBurnTime(itemstack) >
		// 0));
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		// TODO
		return true;
		// return (i == 0) || (i == 1);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (resource.getFluid() == FluidRegistry.WATER)
			return this.waterTank.fill(resource, doFill);
		else
			return this.fuelTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(this.steamTank.getFluid()))
			return null;
		return this.steamTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.steamTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return fluid == FluidRegistry.WATER; // TODO
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return fluid != FluidRegistry.WATER; // TODO
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.steamTank.getInfo(), this.waterTank.getInfo(), this.fuelTank.getInfo() };
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiLiquidBoiler(player.inventory, (TileLiquidBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerLiquidBoiler(player.inventory, (TileLiquidBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	protected int getItemBurnTime(ItemStack stack)
	{
		return 0;
	}
}
