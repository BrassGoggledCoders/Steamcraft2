package common.steamcraft.common.block.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import common.steamcraft.common.block.tile.TileEntityCompressor;
import common.steamcraft.common.block.tile.container.slot.SlotBattery;
import common.steamcraft.common.item.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCompressor extends Container
{
	protected TileEntityCompressor Tile_E;
	private int lastBurnTime = 0;
	private int lastEnergy = 0;

	public ContainerCompressor(InventoryPlayer player, TileEntityCompressor tile)
	{
		Tile_E = tile;
		this.addSlotToContainer(new Slot(tile, 0, 80, 11));
		this.addSlotToContainer(new Slot(tile, 1, 80, 54));
		this.addSlotToContainer(new SlotBattery(tile, 2, 28, 54));

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));

		for (var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + var3 * 18, 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, Tile_E.furnaceBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, (int)Tile_E.getEnergy());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int var1 = 0; var1 < crafters.size(); ++var1)
		{
			ICrafting var2 = (ICrafting) crafters.get(var1);

			if (lastBurnTime != Tile_E.furnaceBurnTime)
				var2.sendProgressBarUpdate(this, 0, Tile_E.furnaceBurnTime);
			
			if (lastEnergy != Tile_E.getEnergyScaled(1))
				var2.sendProgressBarUpdate(this, 1, (int)Tile_E.getEnergy());
		}

		lastBurnTime = Tile_E.furnaceBurnTime;
		lastEnergy = (int)Tile_E.getEnergy();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
			Tile_E.furnaceBurnTime = par2;
		else if(par1 == 1)
			Tile_E.setEnergy(par2);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return Tile_E.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) inventorySlots.get(par2);

		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 != 0 && par2 != 1 && par2 != 2)
			{
				if(SlotBattery.isValidBatteryItem(var5))
				{
					if(!this.mergeItemStack(var5, 2, 3, false))
						return null;
				}
				else if (var5.itemID == ModItems.canisterSteam.itemID)
				{
					if (!this.mergeItemStack(var5, 0, 1, false))
						 return null;
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if(!this.mergeItemStack(var5, 1, 2, false))
						if (!this.mergeItemStack(var5, 30, 39, false))
							return null;
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
					return null;
			}
			else if (!this.mergeItemStack(var5, 3, 39, false))
				return null;

			if (var5.stackSize == 0)
				var4.putStack((ItemStack) null);
			else
				var4.onSlotChanged();

			if (var5.stackSize == var3.stackSize)
				return null;

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
