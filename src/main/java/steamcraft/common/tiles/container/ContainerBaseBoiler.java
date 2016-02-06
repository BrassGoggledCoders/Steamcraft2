
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import boilerplate.common.baseclasses.blocks.BaseContainer;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.tiles.TileBaseBoiler;
import steamcraft.common.tiles.container.slot.SlotFluidContainer;
import steamcraft.common.tiles.container.slot.SlotOutput;

/**
 * @author Decebaldecebal
 *
 */
public class ContainerBaseBoiler extends BaseContainer
{
	protected TileBaseBoiler tileent;

	protected int lastBurnTime = 0;
	protected int lastItemBurnTime = 0;
	protected int lastSteamLevel = 0;
	protected int lastWaterLevel = 0;

	public ContainerBaseBoiler(InventoryPlayer player, TileBaseBoiler tile)
	{
		this.tileent = tile;
		this.setTile(this.tileent);

		this.addSlotToContainer(new Slot(tile, 0, 42, 52));
		this.addSlotToContainer(new SlotFluidContainer(tile, 1, 115, 57));
		this.addSlotToContainer(new SlotOutput(tile, 2, 149, 57));
		this.addSlotToContainer(new SlotFluidContainer(tile, 3, 149, 21));

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));

		for (var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tileent.furnaceBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.tileent.currentFuelBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.tileent.steamTank.getFluidAmount());
		par1ICrafting.sendProgressBarUpdate(this, 3, this.tileent.waterTank.getFluidAmount());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (Object obj : this.crafters)
		{
			ICrafting var2 = (ICrafting) obj;

			if (this.lastBurnTime != this.tileent.furnaceBurnTime)
				var2.sendProgressBarUpdate(this, 0, this.tileent.furnaceBurnTime);

			if (this.lastItemBurnTime != this.tileent.currentFuelBurnTime)
				var2.sendProgressBarUpdate(this, 1, this.tileent.currentFuelBurnTime);

			if (this.lastSteamLevel != this.tileent.steamTank.getFluidAmount())
				var2.sendProgressBarUpdate(this, 2, this.tileent.steamTank.getFluidAmount());

			if (this.lastWaterLevel != this.tileent.waterTank.getFluidAmount())
				var2.sendProgressBarUpdate(this, 3, this.tileent.waterTank.getFluidAmount());
		}

		this.lastBurnTime = this.tileent.furnaceBurnTime;
		this.lastItemBurnTime = this.tileent.currentFuelBurnTime;
		this.lastSteamLevel = this.tileent.steamTank.getFluidAmount();
		this.lastWaterLevel = this.tileent.waterTank.getFluidAmount();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
			this.tileent.furnaceBurnTime = par2;
		else if (par1 == 1)
			this.tileent.currentFuelBurnTime = par2;
		else if (par1 == 2)
			this.tileent.steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), par2));
		else if (par1 == 3)
			this.tileent.waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), par2));
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.tileent.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if ((var4 != null) && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 > 2)
			{
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(var5);

				if ((liquid != null) && (liquid.getFluid() == FluidRegistry.WATER))
				{
					if (!this.mergeItemStack(var5, 1, 2, false))
						return null;
				}
				else if (TileEntityFurnace.getItemBurnTime(var5) > 0)
				{
					if (!this.mergeItemStack(var5, 0, 1, false))
						if ((par2 >= 3) && (par2 < 30))
						{
							if (!this.mergeItemStack(var5, 30, 39, false))
								return null;
						}
						else if ((par2 >= 30) && (par2 < 39) && !this.mergeItemStack(var5, 3, 30, false))
							return null;
				}
				else if (var5.getItem() instanceof ItemCanister)
				{
					if (!this.mergeItemStack(var5, 2, 3, false))
						if ((par2 >= 3) && (par2 < 30))
						{
							if (!this.mergeItemStack(var5, 30, 39, false))
								return null;
						}
						else if ((par2 >= 30) && (par2 < 39) && !this.mergeItemStack(var5, 3, 30, false))
							return null;
				}
				else if ((par2 >= 3) && (par2 < 30) && !this.mergeItemStack(var5, 30, 39, false))
					return null;
				else if ((par2 >= 30) && (par2 < 39) && !this.mergeItemStack(var5, 3, 30, false))
					return null;
			}
			else if (!this.mergeItemStack(var5, 3, 39, false))
				return null;

			if (var5.stackSize == 0)
				var4.putStack(null);
			else
				var4.onSlotChanged();

			if (var5.stackSize == var3.stackSize)
				return null;

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
