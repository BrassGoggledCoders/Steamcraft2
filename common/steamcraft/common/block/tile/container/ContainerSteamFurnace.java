package common.steamcraft.common.block.tile.container;

import common.steamcraft.common.block.tile.TileEntitySteamFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerSteamFurnace extends Container
{
	private TileEntitySteamFurnace furnace;
	private int cookTime;
	private int burnTime;
	private int itemBurnTime;
	private int waterLevel;

	public ContainerSteamFurnace(InventoryPlayer player, TileEntitySteamFurnace tileFurnace)
	{
		cookTime = 0;
		burnTime = 0;
		itemBurnTime = 0;
		waterLevel = 0;
		furnace = tileFurnace;
		this.addSlotToContainer(new Slot(tileFurnace, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileFurnace, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(player.player, tileFurnace, 2, 116, 35));
		this.addSlotToContainer(new Slot(tileFurnace, 3, 28, 53));

		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 9; k++)
			{
				this.addSlotToContainer(new Slot(player, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for(int j = 0; j < 9; j++)
		{
			this.addSlotToContainer(new Slot(player, j, 8 + j * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
		crafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
		crafting.sendProgressBarUpdate(this, 3, this.furnace.waterLevel);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for(int i = 0; i < crafters.size(); i++)
		{
			ICrafting crafting = (ICrafting) crafters.get(i);

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
			if(waterLevel != furnace.waterLevel)
			{
				crafting.sendProgressBarUpdate(this, 3, furnace.waterLevel);
			}
		}

		cookTime = furnace.furnaceCookTime;
		burnTime = furnace.furnaceBurnTime;
		itemBurnTime = furnace.currentItemBurnTime;
		waterLevel = furnace.waterLevel;
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
			furnace.waterLevel = j;
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
		Slot slot = (Slot) inventorySlots.get(i);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(i == 2)
			{
				this.mergeItemStack(stack1, 4, 40, true);
			} else if(i >= 4 && i < 31)
			{
				this.mergeItemStack(stack1, 31, 40, false);
			} else if(i >= 31 && i < 40)
			{
				this.mergeItemStack(stack1, 4, 31, false);
			} else
			{
				this.mergeItemStack(stack1, 4, 40, false);
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