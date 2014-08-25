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
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import steamcraft.common.tiles.TileCharger;
import steamcraft.common.tiles.container.slot.SlotCharger;
import boilerplate.steamapi.items.IEnergyItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 * 
 */
public class ContainerCharger extends Container
{
	private int lastTotalEnergy = 0;
	private int lastMaxEnergy = 0;
	private short lastTransferRate = 0;
	private int lastBufferEnergy;

	TileCharger tile_entity;

	public ContainerCharger(InventoryPlayer player, TileCharger tile)
	{
		this.tile_entity = tile;

		this.addSlotToContainer(new SlotCharger(tile, 0, 98, 22));

		int var3;

		for(var3 = 0; var3 < 3; ++var3)
			for(int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));

		for(var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + var3 * 18, 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);

		crafting.sendProgressBarUpdate(this, 0, this.tile_entity.buffer.getEnergyStored());
		crafting.sendProgressBarUpdate(this, 1, this.tile_entity.buffer.getMaxEnergyStored());
		crafting.sendProgressBarUpdate(this, 2, TileCharger.transferRate);
		crafting.sendProgressBarUpdate(this, 3, this.tile_entity.buffer.getEnergyStored());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for(int var1 = 0; var1 < this.crafters.size(); ++var1)
		{
			ICrafting var2 = (ICrafting) this.crafters.get(var1);

			if(this.lastTotalEnergy != this.tile_entity.buffer.getEnergyStored())
				var2.sendProgressBarUpdate(this, 0, this.tile_entity.buffer.getEnergyStored());

			if(this.lastMaxEnergy != this.tile_entity.buffer.getMaxEnergyStored())
				var2.sendProgressBarUpdate(this, 1, this.tile_entity.buffer.getMaxEnergyStored());

			if(this.lastTransferRate != TileCharger.transferRate)
				var2.sendProgressBarUpdate(this, 2, TileCharger.transferRate);

			if(this.lastBufferEnergy != this.tile_entity.buffer.getEnergyStored())
				var2.sendProgressBarUpdate(this, 3, this.tile_entity.buffer.getEnergyStored());
		}

		this.lastTotalEnergy = this.tile_entity.buffer.getEnergyStored();
		this.lastMaxEnergy = this.tile_entity.buffer.getMaxEnergyStored();
		this.lastTransferRate = TileCharger.transferRate;
		this.lastBufferEnergy = this.tile_entity.buffer.getEnergyStored();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if(par1 == 0)
			par2 = this.tile_entity.buffer.getEnergyStored();
		else if(par1 == 1)
			par2 = this.tile_entity.buffer.getMaxEnergyStored();
		else if(par1 == 2)
			TileCharger.transferRate = (short) par2;
		else if(par1 == 3)
			this.tile_entity.buffer.setEnergyStored(par2);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.tile_entity.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if(var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if(par2 > 5)
			{
				if(var5.getItem() instanceof IEnergyItem)
				{
					if(!this.mergeItemStack(var5, 0, 6, false))
						if(!this.mergeItemStack(var5, 33, 42, false))
							return null;
				}
				else if(par2 >= 6 && par2 < 33 && !this.mergeItemStack(var5, 33, 42, false))
					return null;
				else if(par2 >= 33 && par2 < 42 && !this.mergeItemStack(var5, 6, 33, false))
					return null;
			}
			else if(!this.mergeItemStack(var5, 6, 42, false))
				return null;

			if(var5.stackSize == 0)
				var4.putStack((ItemStack) null);
			else
				var4.onSlotChanged();

			if(var5.stackSize == var3.stackSize)
				return null;

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
