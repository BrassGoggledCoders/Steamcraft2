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
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.tiles.container.slot.SlotCharger;
import steamcraft.common.tiles.energy.TileCharger;
import boilerplate.api.IEnergyItem;
import boilerplate.common.baseclasses.blocks.BaseContainer;

/**
 * @author decebaldecebal
 *
 */
public class ContainerCharger extends BaseContainer
{
	private int lastBufferEnergy;

	private TileCharger tileent;

	public ContainerCharger(InventoryPlayer player, TileCharger tile)
	{
		this.tileent = tile;
		this.setTile(this.tileent);

		this.addSlotToContainer(new SlotCharger(tile, 0, 98, 22));

		int var3;

		for(var3 = 0; var3 < 3; ++var3)
			for(int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));

		for(var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);

		crafting.sendProgressBarUpdate(this, 1, this.tileent.buffer.getEnergyStored());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for(Object obj : this.crafters)
		{
			ICrafting var2 = (ICrafting) obj;

			if(this.lastBufferEnergy != this.tileent.buffer.getEnergyStored())
				var2.sendProgressBarUpdate(this, 1, this.tileent.buffer.getEnergyStored());
		}

		this.lastBufferEnergy = this.tileent.buffer.getEnergyStored();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if(par1 == 1)
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

		if((var4 != null) && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if(par2 > 0)
			{
				if(var5.getItem() instanceof IEnergyItem)
				{
					if(!this.mergeItemStack(var5, 0, 1, false))
						if((par2 >= 1) && (par2 < 28) && !this.mergeItemStack(var5, 28, 37, false))
							return null;
						else if((par2 >= 28) && (par2 < 37) && !this.mergeItemStack(var5, 1, 28, false))
							return null;
				}
				else if((par2 >= 1) && (par2 < 28) && !this.mergeItemStack(var5, 28, 37, false))
					return null;
				else if((par2 >= 28) && (par2 < 37) && !this.mergeItemStack(var5, 1, 28, false))
					return null;
			}
			else if(!this.mergeItemStack(var5, 1, 37, false))
				return null;

			if(var5.stackSize == 0)
				var4.putStack(null);
			else
				var4.onSlotChanged();

			if(var5.stackSize == var3.stackSize)
				return null;

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}
}
