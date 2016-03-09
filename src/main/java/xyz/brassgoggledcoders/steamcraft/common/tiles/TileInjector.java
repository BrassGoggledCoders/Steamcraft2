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
package xyz.brassgoggledcoders.steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import xyz.brassgoggledcoders.steamcraft.client.gui.GuiInjector;
import xyz.brassgoggledcoders.steamcraft.common.tiles.container.ContainerInjector;
import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.common.utils.FluidUtils;

/**
 * @author Decebaldecebal
 *
 */
public class TileInjector extends BaseTileWithInventory implements IOpenableGUI, IFluidHandler
{

	public FluidTank buffer;

	public TileInjector()
	{
		super(2);
		this.buffer = new FluidTank(10000);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (!this.worldObj.isRemote)
		{
			if (this.inventory[0] != null)
			{
				ItemStack stack = FluidUtils.drainFluidContainer(buffer, this.inventory[0]);
				if ((this.inventory[0] != null) && OreDictionary.itemMatches(this.inventory[0], stack, true))
					this.inventory[0].stackSize += stack.stackSize;
				else if (this.inventory[0] == null)
					this.inventory[0] = stack.copy();
				this.decrStackSize(0, stack.stackSize);
			}
			if (this.inventory[1] != null)
			{
				ItemStack stack = FluidUtils.fillFluidContainer(buffer, this.inventory[1]);
				if (stack != null)
				{
					if ((this.inventory[1] != null) && OreDictionary.itemMatches(this.inventory[1], stack, true))
						this.inventory[1].stackSize += stack.stackSize;
					else if (this.inventory[1] == null)
						this.inventory[1] = stack.copy();
				}
			}
		}
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		return FluidContainerRegistry.isContainer(itemstack);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.buffer.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return this.buffer.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.buffer.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.buffer.getInfo() };
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiInjector(player.inventory, (TileInjector) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerInjector(player.inventory, (TileInjector) world.getTileEntity(x, y, z));
	}
}
