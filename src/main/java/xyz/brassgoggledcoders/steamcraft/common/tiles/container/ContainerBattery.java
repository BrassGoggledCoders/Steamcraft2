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
package xyz.brassgoggledcoders.steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.steamcraft.common.tiles.container.slot.SlotBattery;
import xyz.brassgoggledcoders.steamcraft.common.tiles.energy.TileBattery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.api.IEnergyItem;
import boilerplate.common.baseclasses.blocks.BaseContainer;

/**
 * @author decebaldecebal
 *
 */
public class ContainerBattery extends BaseContainer
{
	private int lastTotalEnergy = 0;
	private int lastMaxEnergy = 0;
	private short lastTransferRate = 0;
	private int lastBufferEnergy;

	private TileBattery tileent;

	public ContainerBattery(InventoryPlayer player, TileBattery tile)
	{
		this.tileent = tile;
		this.setTile(this.tileent);

		this.addSlotToContainer(new SlotBattery(tile, 0, 98, 22));
		this.addSlotToContainer(new SlotBattery(tile, 1, 116, 22));
		this.addSlotToContainer(new SlotBattery(tile, 2, 98, 40));
		this.addSlotToContainer(new SlotBattery(tile, 3, 116, 40));

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));

		for (var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);

		crafting.sendProgressBarUpdate(this, 0, this.tileent.totalEnergy);
		crafting.sendProgressBarUpdate(this, 1, this.tileent.maxEnergy);
		crafting.sendProgressBarUpdate(this, 2, this.tileent.transferRate);
		crafting.sendProgressBarUpdate(this, 3, this.tileent.buffer.getEnergyStored());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (Object obj : this.crafters)
		{
			ICrafting var2 = (ICrafting) obj;

			if (this.lastTotalEnergy != this.tileent.totalEnergy)
				var2.sendProgressBarUpdate(this, 0, this.tileent.totalEnergy);

			if (this.lastMaxEnergy != this.tileent.maxEnergy)
				var2.sendProgressBarUpdate(this, 1, this.tileent.maxEnergy);

			if (this.lastTransferRate != this.tileent.transferRate)
				var2.sendProgressBarUpdate(this, 2, this.tileent.transferRate);

			if (this.lastBufferEnergy != this.tileent.buffer.getEnergyStored())
				var2.sendProgressBarUpdate(this, 3, this.tileent.buffer.getEnergyStored());
		}

		this.lastTotalEnergy = this.tileent.totalEnergy;
		this.lastMaxEnergy = this.tileent.maxEnergy;
		this.lastTransferRate = this.tileent.transferRate;
		this.lastBufferEnergy = this.tileent.buffer.getEnergyStored();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
			this.tileent.totalEnergy = par2;
		else if (par1 == 1)
			this.tileent.maxEnergy = par2;
		else if (par1 == 2)
			this.tileent.transferRate = (short) par2;
		else if (par1 == 3)
			this.tileent.buffer.setEnergyStored(par2);
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

			if (par2 >= 4)
			{
				if (var5.getItem() instanceof IEnergyItem)
				{
					if (!this.mergeItemStack(var5, 0, 4, false))
						if ((par2 >= 4) && (par2 < 31) && !this.mergeItemStack(var5, 31, 40, false))
							return null;
						else if ((par2 >= 31) && (par2 < 40) && !this.mergeItemStack(var5, 4, 31, false))
							return null;
				}
				else if ((par2 >= 4) && (par2 < 31) && !this.mergeItemStack(var5, 31, 40, false))
					return null;
				else if ((par2 >= 31) && (par2 < 40) && !this.mergeItemStack(var5, 4, 31, false))
					return null;
			}
			else if (!this.mergeItemStack(var5, 4, 40, false))
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
