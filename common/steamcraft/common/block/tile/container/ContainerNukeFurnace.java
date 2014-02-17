package common.steamcraft.common.block.tile.container;

import common.steamcraft.common.block.tile.TileEntityNukeFurnace;
import common.steamcraft.common.block.tile.container.slot.SlotNukeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNukeFurnace extends Container
{
	private TileEntityNukeFurnace furnace;
	private int cookTime;
	private int burnTime;
	private int itemBurnTime;
	private int furnaceHeat;

	public ContainerNukeFurnace(InventoryPlayer inventoryplayer, TileEntityNukeFurnace tileFurnace)
	{
		cookTime = 0;
		burnTime = 0;
		itemBurnTime = 0;
		furnaceHeat = 0;
		furnace = tileFurnace;
		this.addSlotToContainer(new Slot(tileFurnace, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileFurnace, 1, 56, 53));
		this.addSlotToContainer(new SlotNukeFurnace(inventoryplayer.player, tileFurnace, 2, 116, 35));

		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 9; k++)
			{
				this.addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for(int j = 0; j < 9; j++)
		{
			this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
		}
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
        crafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
        crafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
        crafting.sendProgressBarUpdate(this, 3, this.furnace.furnaceHeat);
    }

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for(int size = 0; size < crafters.size(); size++)
		{
			ICrafting crafting = (ICrafting) crafters.get(size);

			if(cookTime != furnace.furnaceCookTime)
			{
				crafting.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
			}
			if(burnTime != furnace.furnaceBurnTime)
			{
				crafting.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
			}
			if(itemBurnTime != furnace.currentItemBurnTime)
			{
				crafting.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
			}
			if(furnaceHeat != furnace.furnaceHeat)
			{
				crafting.sendProgressBarUpdate(this, 3, furnace.furnaceHeat);
			}
		}

		cookTime = furnace.furnaceCookTime;
		burnTime = furnace.furnaceBurnTime;
		itemBurnTime = furnace.currentItemBurnTime;
		furnaceHeat = furnace.furnaceHeat;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j)
	{
		if(i == 0)
		{
			furnace.furnaceCookTime = j;
		}
		if(i == 1)
		{
			furnace.furnaceBurnTime = j;
		}
		if(i == 2)
		{
			furnace.currentItemBurnTime = j;
		}
		if(i == 3)
		{
			furnace.furnaceHeat = j;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return furnace.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack stack = null;
		Slot slot = (Slot)inventorySlots.get(i);

		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if(i == 2)
			{
				this.mergeItemStack(stack1, 3, 39, true);
			} else if(i >= 3 && i < 30)
			{
				this.mergeItemStack(stack1, 30, 39, false);
			} else if(i >= 30 && i < 39)
			{
				this.mergeItemStack(stack1, 3, 30, false);
			} else
			{
				this.mergeItemStack(stack1, 3, 39, false);
			}
			if(stack1.stackSize == 0)
			{
				slot.putStack(null);
			} else
			{
				slot.onSlotChanged();
			}
			if(stack1.stackSize != stack.stackSize)
			{
				slot.putStack(stack1);
			} else
			{
				return null;
			}
		}
		
		return stack;
	}
}