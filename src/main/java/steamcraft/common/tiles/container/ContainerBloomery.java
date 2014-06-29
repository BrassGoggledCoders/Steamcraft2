package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import steamcraft.common.tiles.BloomeryRecipes;
import steamcraft.common.tiles.TileBloomery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBloomery extends Container
{
	   	private TileBloomery tileBloomery;
	    private int lastCookTime;
	    private int lastBurnTime;
	    private int lastItemBurnTime;

	    public ContainerBloomery(InventoryPlayer par1InventoryPlayer, TileBloomery par2TileEntityBloomery)
	    {
	        this.tileBloomery = par2TileEntityBloomery;
	        //Fuel
	        this.addSlotToContainer(new Slot(par2TileEntityBloomery, 0, 45, 53));
	        //Slot 1 Top
	        this.addSlotToContainer(new Slot(par2TileEntityBloomery, 1, 33, 17));
	        //Slot 2 Top
	        this.addSlotToContainer(new Slot(par2TileEntityBloomery, 2, 56, 17));
	        //Output
	        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityBloomery, 3, 116, 35));
	        int i;

	        for (i = 0; i < 3; ++i)
	        {
	            for (int j = 0; j < 9; ++j)
	            {
	                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	            }
	        }

	        for (i = 0; i < 9; ++i)
	        {
	            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
	        }
	    }

	    public void addCraftingToCrafters(ICrafting par1ICrafting)
	    {
	        super.addCraftingToCrafters(par1ICrafting);
	        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileBloomery.bloomeryCookTime);
	        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileBloomery.bloomeryBurnTime);
	        par1ICrafting.sendProgressBarUpdate(this, 2, this.tileBloomery.currentItemBurnTime);
	    }

	    /**
	     * Looks for changes made in the container, sends them to every listener.
	     */
	    public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int i = 0; i < this.crafters.size(); ++i)
	        {
	            ICrafting icrafting = (ICrafting)this.crafters.get(i);

	            if (this.lastCookTime != this.tileBloomery.bloomeryCookTime)
	            {
	                icrafting.sendProgressBarUpdate(this, 0, this.tileBloomery.bloomeryCookTime);
	            }

	            if (this.lastBurnTime != this.tileBloomery.bloomeryBurnTime)
	            {
	                icrafting.sendProgressBarUpdate(this, 1, this.tileBloomery.bloomeryBurnTime);
	            }

	            if (this.lastItemBurnTime != this.tileBloomery.currentItemBurnTime)
	            {
	                icrafting.sendProgressBarUpdate(this, 2, this.tileBloomery.currentItemBurnTime);
	            }
	        }

	        this.lastCookTime = this.tileBloomery.bloomeryCookTime;
	        this.lastBurnTime = this.tileBloomery.bloomeryBurnTime;
	        this.lastItemBurnTime = this.tileBloomery.currentItemBurnTime;
	    }

	    @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int par1, int par2)
	    {
	        if (par1 == 0)
	        {
	            this.tileBloomery.bloomeryCookTime = par2;
	        }

	        if (par1 == 1)
	        {
	            this.tileBloomery.bloomeryBurnTime = par2;
	        }

	        if (par1 == 2)
	        {
	            this.tileBloomery.currentItemBurnTime = par2;
	        }
	    }

	    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	    {
	        return this.tileBloomery.isUseableByPlayer(par1EntityPlayer);
	    }

	    /**
	     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	     */
	    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	    {
	        ItemStack itemstack = null;
	        Slot slot = (Slot)this.inventorySlots.get(par2);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (par2 == 2)
	            {
	                if (!this.mergeItemStack(itemstack1, 3, 39, true))
	                {
	                    return null;
	                }

	                slot.onSlotChange(itemstack1, itemstack);
	            }
	            else if (par2 != 1 && par2 != 0)
	            {
	                if (BloomeryRecipes.smelting().getSmeltingResult(itemstack1) != null)
	                {
	                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (TileBloomery.isItemFuel(itemstack1))
	                {
	                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (par2 >= 3 && par2 < 30)
	                {
	                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
	            {
	                return null;
	            }

	            if (itemstack1.stackSize == 0)
	            {
	                slot.putStack((ItemStack)null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }

	            if (itemstack1.stackSize == itemstack.stackSize)
	            {
	                return null;
	            }

	            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
	        }

	        return itemstack;
	    }
}
