package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import steamcraft.common.tiles.TileBloomery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBloomery extends Container
{
	private TileBloomery tileBloomery;
	private short lastCookTime;
	private short lastBurnTime;
	private short lastItemBurnTime;

	public ContainerBloomery(InventoryPlayer playerInv, TileBloomery tileEntity)
	{
		this.tileBloomery = tileEntity;

		// Fuel
		this.addSlotToContainer(new Slot(tileEntity, 0, 45, 53));
		// Input 1
		this.addSlotToContainer(new Slot(tileEntity, 1, 33, 17));
		// Input 2
		this.addSlotToContainer(new Slot(tileEntity, 2, 56, 17));
		// Output
		this.addSlotToContainer(new Slot(tileEntity, 3, 116, 35){
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
		});

		int i;

		for (i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (i = 0; i < 9; ++i)
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileBloomery.cookTime);
		crafting.sendProgressBarUpdate(this, 1, this.tileBloomery.burnTime);
		crafting.sendProgressBarUpdate(this, 2, this.tileBloomery.currentItemBurnTime);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.tileBloomery.cookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tileBloomery.cookTime);
			}

			if (this.lastBurnTime != this.tileBloomery.burnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tileBloomery.burnTime);
			}

			if (this.lastItemBurnTime != this.tileBloomery.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tileBloomery.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.tileBloomery.cookTime;
		this.lastBurnTime = this.tileBloomery.burnTime;
		this.lastItemBurnTime = this.tileBloomery.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2)
	{
		switch(par1)
		{
			case 0:
				this.tileBloomery.cookTime = (short) par2;
			break;
			
			case 1:
				this.tileBloomery.burnTime = (short) par2;
			break;
	
			case 2:
				this.tileBloomery.currentItemBurnTime = (short) par2;
			break;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tileBloomery.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 3)
			{
				if (!this.mergeItemStack(itemstack1, 4, 40, true))
					return null;

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 2 && par2 != 1 && par2 != 0 )
			{
				if (TileBloomery.isItemFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
						return null;
				}
				else if (par2 >= 4 && par2 < 31)
				{
					if(!this.mergeItemStack(itemstack1, 1, 3, false))
						if (!this.mergeItemStack(itemstack1, 31, 40, false))
							return null;
				}
				else if (par2 >= 31 && par2 < 40 && !this.mergeItemStack(itemstack1, 4, 31, false))
					return null;
			}
			else if (!this.mergeItemStack(itemstack1, 4, 40, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}
