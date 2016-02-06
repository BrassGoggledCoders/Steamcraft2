
package steamcraft.common.tiles;

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

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.common.utils.FluidUtils;
import steamcraft.client.gui.GuiInjector;
import steamcraft.common.tiles.container.ContainerInjector;

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
				ItemStack stack = FluidUtils.drainFluidContainer(this.buffer, this.inventory[0]);
				if ((this.inventory[0] != null) && OreDictionary.itemMatches(this.inventory[0], stack, true))
					this.inventory[0].stackSize -= stack.stackSize;
				else if (this.inventory[0] == null)
					this.inventory[0] = stack.copy();
			}
			if (this.inventory[1] != null)
			{
				ItemStack stack = FluidUtils.fillFluidContainer(this.buffer, this.inventory[1]);
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
